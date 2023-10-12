package hello.external;

import lombok.extern.slf4j.Slf4j;

import java.util.Properties;

@Slf4j
public class JavaSystemProperties {

    public static void main(String[] args) {

        //자바 시스템 속성
        //-Durl=devdb -Dusername=dev_user -Dpassword=dev_pwd
        //jar 빌드시 java -Durl=devdb -Dusername=dev_user -Dpassword=dev_pw -jar app.jar
        Properties properties = System.getProperties();
        for (Object key : properties.keySet()) {
            log.info("prop {} = {}", key, System.getProperty(String.valueOf(key)));
        }

        String url = System.getProperty("url");
        log.info("url = {}", url);

        String username = System.getProperty("username");
        log.info("username = {}", username);

        String password = System.getProperty("password");
        log.info("password = {}", password);
    }

}
