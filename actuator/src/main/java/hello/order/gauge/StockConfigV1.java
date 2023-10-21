package hello.order.gauge;

import hello.order.OrderService;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class StockConfigV1 {

    @Bean
    public MyStockMetric myStockMetric(OrderService orderService, MeterRegistry registry) {
        return new MyStockMetric(orderService, registry);
    }

    /**
     * Gauge 메트릭
     * - 임의로 오르내릴 수 있는 단일 숫자 값을 나타내는 메트릭
     * - 값의 현재 상태를 보는데 사용
     * - 값이 증가하거나 감소할 수 있음
     */
    @RequiredArgsConstructor
    public static class MyStockMetric {

        private final OrderService orderService;

        private final MeterRegistry registry;

        @PostConstruct
        public void init() {
            Gauge.builder("my.stock", orderService, service -> { //외부에서 메트릭을 확인할 때 마다 해당 함수를 호출
                log.info("stock gauge call");
                return service.getStock().get();
            }).register(registry);
        }

    }

}
