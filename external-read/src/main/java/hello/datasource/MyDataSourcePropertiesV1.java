package hello.datasource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Data //자바빈 프로퍼티 방식 사용 - setter 를 사용하기 때문에 실수할 수 있다
@ConfigurationProperties("my.datasource") //외부 설정을 주입 받는 객체 - 타입 세이프
public class MyDataSourcePropertiesV1 {

    private String url;
    private String userId;
    private String password;
    private Etc etc;

    @Data
    public static class Etc {

        private int maxConnection; //케밥 표기법을 카멜 케이스로 변환 해줌

        private Duration timeout;

        private List<String> options = new ArrayList<>();

    }

}
