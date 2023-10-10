package memory;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

@AutoConfiguration //스프링 부트가 제공하는 자동 구성 기능 적용 애노테이션
@ConditionalOnProperty(name = "memory", havingValue = "on") //memory=on 환경 정보가 있을 때 스프링 빈 등록
public class MemoryAutoConfig {

    @Bean
    public MemoryController memoryController() {
        return new MemoryController(memoryFinder());
    }

    @Bean
    public MemoryFinder memoryFinder() {
        return new MemoryFinder();
    }

}
