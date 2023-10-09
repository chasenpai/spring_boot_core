package hello.config;

import memory.MemoryCondition;
import memory.MemoryController;
import memory.MemoryFinder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
//@Conditional(MemoryCondition.class)
@ConditionalOnProperty(name = "memory", havingValue = "on") //환경 정보에 memory = on 조건이 참이면 동작한다 위와 동일
/**
 * 스프링이 제공하는 @Conditional 들
 *
 * @ConditionalOnClass & @ConditionalOnMissingClass
 * - 클래스가 있거나 없는 경우
 *
 * @ConditionalOnBean & , @ConditionalOnMissingBean
 * - 빈이 등록되어 있거나 없는 경우
 *
 * @ConditionalOnProperty
 * - 환경 정보가 있는 경우
 *
 * @ConditionalOnResource
 * - 리소스가 있는 경우
 *
 * @ConditionalOnWebApplication & @ConditionalOnNotWebApplication
 * - 웹 애플리케이션이거나 아닌 경우
 *
 * @ConditionalOnExpression
 * - SpEl 표현식에 만족할 경우
 */
public class MemoryConfig {

    @Bean
    public MemoryController memoryController() {
        return new MemoryController(memoryFinder());
    }

    @Bean
    public MemoryFinder memoryFinder() {
        return new MemoryFinder();
    }

}
