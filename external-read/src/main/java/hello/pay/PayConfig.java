package hello.pay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Slf4j
@Configuration
public class PayConfig {

    @Bean
    @Profile("default") //외부 설정을 사용해서 각 환경마다 서로 다른 빈을 등록하게 할 수 있다 - @Conditional
    public LocalPayClient localPayClient() {
        log.info("local pay 빈 등록");
        return new LocalPayClient();
    }

    @Bean
    @Profile("prod")
    public ProdPayClient prodPayClient() {
        log.info("prod pay 빈 등록");
        return new ProdPayClient();
    }

}
