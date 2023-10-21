package hello.order.v1;

import hello.order.OrderService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@RequiredArgsConstructor
public class OrderServiceV1 implements OrderService {

    private final MeterRegistry registry;

    private AtomicInteger stock = new AtomicInteger(100);

    /**
     * Counter 메트릭
     * - 단조롭게 증가하는 단일 누적 측정
     * - 단일 값이고 누적이므로 전체 값을 포함
     * - 값을 증가하거나 0으로 초기화 하는 것만 가능
     * - 마이크로미터에서 값을 감소시키는 기능도 지원하지만 목적에 맞지 않다
     */
    @Override
    public void order() {

        log.info("주문");
        stock.decrementAndGet();

        Counter.builder("my.order")
                .tag("class", this.getClass().getName())
                .tag("method", "order") //tag 를 통해 구분
                .description("order")
                .register(registry) //등록해야 실제로 동작한다
                .increment();
    }

    /**
     * 액추에이터 - /actuator/metrics/my.order
     * 프로메테우스 포맷 - my_order_total (_total 은 관례상 붙임)
     * PromQL - increase(my_order_total{method="order"}[1m])
     */
    @Override
    public void cancel() {

        log.info("취소");
        stock.incrementAndGet();

        Counter.builder("my.order")
                .tag("class", this.getClass().getName())
                .tag("method", "cancel")
                .description("cancel")
                .register(registry)
                .increment();
    }

    @Override
    public AtomicInteger getStock() {
        return stock;
    }

}
