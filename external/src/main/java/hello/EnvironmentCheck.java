package hello;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EnvironmentCheck {

    private final Environment env; //특정 외부 설정에 종속되지 않고 일관성 있게 key=value 형식의 외부 설정에 접근 가능

    public EnvironmentCheck(Environment env) {
        this.env = env;
    }

    @PostConstruct
    public void init() {
        String url = env.getProperty("url");
        String user = env.getProperty("user");
        String password = env.getProperty("password");
        log.info("env url = {}", url);
        log.info("env user = {}", user);
        log.info("env password = {}", password);

        //우선순위
        //1. @TestPropertySource
        //2. 커맨드 라인 옵션 인수
        //3. 자바 시스템 속성
        //4. OS 환경변수
        //5. properties
        //더 유연하고 범위가 좁은 것이 우선권을 가진다
    }

}
