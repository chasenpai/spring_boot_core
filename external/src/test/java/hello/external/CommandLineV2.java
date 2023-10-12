package hello.external;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;

import java.util.List;
import java.util.Set;

@Slf4j
public class CommandLineV2 {

    public static void main(String[] args) {
        //--url=devdb --username=dev_user --password=dev_pw mode=on
        for (String arg : args) {
            log.info("arg {}", arg);
        }

        //스프링이 제공하는 커맨드 라인 옵션 인수 - key&value 형식으로 파싱
        ApplicationArguments appArgs = new DefaultApplicationArguments(args);
        log.info("SourceArgs = {}", List.of(appArgs.getSourceArgs())); // -- O
        log.info("NonOptionArgs = {}", appArgs.getNonOptionArgs()); // -- X
        log.info("OptionNames = {}", appArgs.getOptionNames());

        Set<String> optionNames = appArgs.getOptionNames();
        for (String optionName : optionNames) {
            log.info("option arg {} = {}", optionName, appArgs.getOptionValues(optionName));
        }

        List<String> url = appArgs.getOptionValues("url");
        List<String> username = appArgs.getOptionValues("username");
        List<String> password = appArgs.getOptionValues("password");
        List<String> mode = appArgs.getOptionValues("mode");
        log.info("url = {}", url);
        log.info("username = {}", username);
        log.info("password = {}", password);
        log.info("mode = {}", mode); //null
    }

}
