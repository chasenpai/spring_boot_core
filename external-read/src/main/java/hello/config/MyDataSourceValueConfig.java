package hello.config;

import hello.datasource.MyDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.List;

@Slf4j
@Configuration
public class MyDataSourceValueConfig {

    /**
     * @Value
     * - 외부 설정값을 편리하게 주입 받을 수 있다
     * - 내부에서는 Environment 를 사용한다
     * - ${} 표현식으로 외부 설정의 키 값을 주면 원하는 값을 주입 받을 수 있다
     * - 필드와 파라미터 둘다 사용할 수 있다
     * - 하지만 Environment 와 마찬가지로 외부 설정 하나하나 키 값을 입력받아야 하는 단점이 있다
     */
//    @Value("${my.datasource.url}")
    @Value("${my.datasource.url:local.db.com}") //: 으로 key 가 없는 경우 기본 값 설정가능
    private String url;

    @Value("${my.datasource.user-id}")
    private String userId;

    @Value("${my.datasource.password}")
    private String password;

    @Value("${my.datasource.etc.max-connection}")
    private int maxConnection;

    @Value("${my.datasource.etc.timeout}")
    private Duration timeout;

    @Value("${my.datasource.etc.options}")
    private List<String> options;

    @Bean
    public MyDataSource myDataSource1() {
        return new MyDataSource(url, userId, password, maxConnection, timeout, options);
    }

    @Bean
    public MyDataSource myDataSource2(
            @Value("${my.datasource.url:local.db.com}") String url,
            @Value("${my.datasource.user-id}") String userId,
            @Value("${my.datasource.password}") String password,
            @Value("${my.datasource.etc.max-connection}") int maxConnection,
            @Value("${my.datasource.etc.timeout}") Duration timeout,
            @Value("${my.datasource.etc.options}") List<String> options
    ) {
        return new MyDataSource(url, userId, password, maxConnection, timeout, options);
    }

}
