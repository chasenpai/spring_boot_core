package memory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

@Slf4j
public class MemoryCondition implements Condition {

    /**
     * Condition
     * - 특정 조건을 만족하는지 구별하는 기능으로 스프링 자동 구성에서 주로 사용
     * - matches 메서드가 true 를 반환하면 동작한다
     */
    @Override
    public boolean matches(ConditionContext context, //스프링 컨테이너, 환경 정보
                           AnnotatedTypeMetadata metadata) { //애노테이션 메타 정보

        //VM Option -Dmemory=on
        String memory = context.getEnvironment().getProperty("memory");
        log.info("memory condition = {}", memory);

        return "on".equals(memory);
    }

}
