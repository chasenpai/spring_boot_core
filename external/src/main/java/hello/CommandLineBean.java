package hello;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommandLineBean {

    private final ApplicationArguments arguments; //해당 빈을 주입 받으면 커맨드 라인으로 입력한 값을 어디서든 사용 가능

    @PostConstruct
    public void init() {
        log.info("source = {}", List.of(arguments.getSourceArgs()));
        log.info("optionNames =  {}", arguments.getOptionNames());
        Set<String> optionNames = arguments.getOptionNames();
        for (String optionName : optionNames) {
            log.info("option args {} = {}", optionName, arguments.getOptionValues(optionName));
        }
    }

}
