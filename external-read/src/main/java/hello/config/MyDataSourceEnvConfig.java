package hello.config;

import hello.datasource.MyDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.time.Duration;
import java.util.List;

@Slf4j
@Configuration
public class MyDataSourceEnvConfig {

    private final Environment env;

    public MyDataSourceEnvConfig(Environment env) {
        this.env = env;
    }

    /**
     * Environment
     * - 외부 설정의 종류와 관계없이 코드 안에서 일관성 있게 외부 설정을 조회 가능
     * - 향후 properties 파일을 사용하지 않고 커맨드 라인 옵션 인수나 자바 시스템 속성으로 변경해도
     * - 애플리케이션의 코드를 그대로 유지할 수 있다
     * - 하지만 Environment 를 직접 주입받고 값을 꺼내는 과정을 반복해야 한다
     */
    @Bean
    public MyDataSource myDataSource() {

        String url = env.getProperty("my.datasource.url");
        String username = env.getProperty("my.datasource.user-id");
        String password = env.getProperty("my.datasource.password");
        int maxConnection = env.getProperty("my.datasource.etc.max-connection", Integer.class); //문자 > 숫자 변환
        Duration timeout = env.getProperty("my.datasource.etc.timeout", Duration.class); //Duration 객채로 변환
        List<String> options = env.getProperty("my.datasource.etc.options", List.class); //List 변환

        return new MyDataSource(url, username, password, maxConnection, timeout, options);
    }

}
