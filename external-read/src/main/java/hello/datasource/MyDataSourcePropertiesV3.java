package hello.datasource;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import org.hibernate.validator.constraints.time.DurationMax;
import org.hibernate.validator.constraints.time.DurationMin;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Getter
@ConfigurationProperties("my.datasource")
@Validated //자바 표준 검증기
public class MyDataSourcePropertiesV3 {

    /**
     * @ConfigurationProperties
     * - 외부 설정을 객체로 편리하게 변환해서 사용할 수 있다
     * - 외부 설정의 계층을 객체로 편리하게 표현할수 있다
     * - 외부 설정을 타입 세이프하게 사용할 수 있다
     * - 검증기 적용으로 더욱 안전하게 사용 가능
     */

    @NotEmpty
    private String url;

    @NotEmpty
    private String userId;

    @NotEmpty
    private String password;

    private Etc etc;

    public MyDataSourcePropertiesV3(String url, String userId, String password, Etc etc) {
        this.url = url;
        this.userId = userId;
        this.password = password;
        this.etc = etc;
    }

    @Getter
    public static class Etc {

        @Min(1)
        @Max(999)
        private int maxConnection;

        @DurationMin(seconds = 1)
        @DurationMax(seconds = 60)
        private Duration timeout;

        private List<String> options = new ArrayList<>();

        public Etc(int maxConnection, Duration timeout, List<String> options) {
            this.maxConnection = maxConnection;
            this.timeout = timeout;
            this.options = options;
        }

    }

}
