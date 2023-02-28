package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor // 자동으로 생성자 생성
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    // log-demo 가 호출되는 순간 logDemo 메서드 호출
    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        String requestURL = request.getRequestURL().toString();

        System.out.println("myLogger = " + myLogger.getClass());
        // 이거 해주는 이유 => log 에 출처 남기려고
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        // 현재 쓰레드를 멈춤
        Thread.sleep(1000);
        logDemoService.logic("testId");
        return "OK";
    }
}
