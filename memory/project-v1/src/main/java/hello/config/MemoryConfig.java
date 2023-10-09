package hello.config;

import memory.MemoryController;
import memory.MemoryFinder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MemoryConfig {

    /**
     * 외부 라이브러리를 직접 만들고 프로젝트에 라이브러리로 불러서 적용
     * 라이브러리를 사용하는 클라이언트 개발자 입장에선 라이브러리 내부의 어떤 빈을
     * 등록해야 하는지 알아야 하고 또 하나하나 빈들을 등록해야 한다
     * -> 스프링 부트 자공 구성으로 해결 가능능     */
    @Bean
    public MemoryFinder memoryFinder() {
        return new MemoryFinder();
    }

    @Bean
    public MemoryController memoryController() {
        return new MemoryController(memoryFinder());
    }

}
