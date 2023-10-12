package hello.external;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommandLineV1 {

    public static void main(String[] args) {
        //커맨드 라인 인수 - key&value 형식이 아니라서 불편하다
        for (String arg : args) {
            log.info("arg {}", arg);
        }
    }

}
