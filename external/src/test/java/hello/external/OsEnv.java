package hello.external;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public class OsEnv {

    /**
     * OS 환경 변수 설정
     * - OS 환경 변수는 해당 OS를 사용하는 모든 프로그램에서 읽을 수 있다
     * - 다른 외부 설정과 비교해서 사용 범위가 기장 넓다
     * - 일종의 전역 변수
     */
    public static void main(String[] args) {
        
        Map<String, String> getenv = System.getenv(); //전체 OS 환경 변수를 조회

        for (String key : getenv.keySet()) {
            log.info("env {} = {}", key, System.getenv(key)); //특정 OS 환경 변수를 조회
        }
    }

}
