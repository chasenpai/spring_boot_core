package hello.datasource;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.List;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyDataSource {

    private String url;

    private String userId;

    private String password;

    private int maxConnection;

    private Duration timeout;

    private List<String> options;

    @PostConstruct
    public void init() {
        log.info("url = {}", url);
        log.info("userId = {}", userId);
        log.info("password = {}", password);
        log.info("maxConnection = {}", maxConnection);
        log.info("timeout = {}", timeout);
        log.info("options = {}", options);
    }

}
