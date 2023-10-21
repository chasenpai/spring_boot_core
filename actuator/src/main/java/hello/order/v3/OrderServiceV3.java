package hello.order.v3;

import hello.order.OrderService;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RequiredArgsConstructor
public class OrderServiceV3 implements OrderService {

    private final MeterRegistry registry;

    private AtomicInteger stock = new AtomicInteger(100);

    /**
     * Timer 메트릭
     * - 카운터와 유사하지만 실행 시간도 같이 측정해준다
     * - second_count 누적 실행 수
     * - second_sum 실행 시간의 합
     * - second_max 최대 실행 시간(gauge)
     */
    @Override
    public void order() {

        Timer timer = Timer.builder("my.order")
                .tag("class", this.getClass().getName())
                .tag("method", "order")
                .description("order")
                .register(registry);

        timer.record(() -> {
            log.info("주문");
            sleep(1000);
            stock.decrementAndGet();
        });
    }

    /**
     * 액추에이터 - /actuator/metrics/my.order
     * 프로메테우스 포맷
     *  - my_order_seconds_count
     *  - my_order_seconds_sum
     *  - my_order_seconds_max
     * PromQL
     * - 누적 실행 수 increase(my_order_seconds_count{method="order"}[1m])
     * - 최대 실행 시간 my_order_seconds_max
     * - 평균 실행 시간 increase(my_order_seconds_sum[1m]) /  increase(my_order_seconds_count[1m])
     */
    @Override
    public void cancel() {

        Timer timer = Timer.builder("my.order")
                .tag("class", this.getClass().getName())
                .tag("method", "cancel")
                .description("cancel")
                .register(registry);

        timer.record(() -> {
            log.info("취소");
            sleep(1000);
            stock.incrementAndGet();
        });
    }

    @Override
    public AtomicInteger getStock() {
        return stock;
    }

    private void sleep(long time) {
        try {
            Thread.sleep(time + new Random().nextInt(200));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
