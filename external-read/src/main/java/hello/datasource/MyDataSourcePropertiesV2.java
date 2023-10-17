package hello.datasource;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Getter
@ConfigurationProperties("my.datasource")
public class MyDataSourcePropertiesV2 {

    private String url;
    private String userId;
    private String password;
    private Etc etc;

    //setter 대신 생성자를 사용하는 방식
    //@DefaultValue 값이 없어도 객체를 생성 해줌
    //생성자가 둘 이상일 경우 사용할 생성자에 @ConstructorBinding 사용
    public MyDataSourcePropertiesV2(String url, String userId, String password,  @DefaultValue Etc etc) {
        this.url = url;
        this.userId = userId;
        this.password = password;
        this.etc = etc;
    }

    @Getter
    public static class Etc {

        private int maxConnection;

        private Duration timeout;

        private List<String> options = new ArrayList<>();

        public Etc(int maxConnection, Duration timeout, @DefaultValue("DEFAULT") List<String> options) {
            this.maxConnection = maxConnection;
            this.timeout = timeout;
            this.options = options;
        }

    }

}
