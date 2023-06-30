package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController     // 일반 controller를 쓰면 view가 랜더링 돼서 이거 막으려고 RestController 씀
public class LogTestController {

    // @Slf4j 사용하면 이 코드 안 써도 돼
//    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

//        System.out.println("name = " + name);

        // log level 순서 위에 있는 것이 더 상위 레벨
        // 운영 서버에서는 출력되는 내용들을 조절할 수 있어 => application.properties 에서 설정
        // default level = info
        // 왜 {} 써서 처리하냐? => + 를 써서 처리하면 연산이 발생해버려, log 쓰는데 연산 쓸거야?
        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info(" info log={}", name);
        log.warn(" warn log={}", name);
        log.error("error log={}", name);

        return "ok";
    }
}
