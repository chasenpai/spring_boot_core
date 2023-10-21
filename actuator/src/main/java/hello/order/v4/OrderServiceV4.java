package hello.order.v4;

import hello.order.OrderService;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Timed("my.order") //타입에도 적용가능 - 모든 public 메서드에 적용된다
@Slf4j
@RequiredArgsConstructor
public class OrderServiceV4 implements OrderService {
    private AtomicInteger stock = new AtomicInteger(100);

//    @Timed("my.order")
    @Override
    public void order() {
        log.info("주문");
        sleep(1000);
        stock.decrementAndGet();
    }

//    @Timed("my.order")
    @Override
    public void cancel() {
        log.info("취소");
        sleep(1000);
        stock.incrementAndGet();
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
