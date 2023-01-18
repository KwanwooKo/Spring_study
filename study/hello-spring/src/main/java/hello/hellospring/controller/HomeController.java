package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    // 원래 welcome page 가 static 에 index.html 을 찾는데
    // 여기서 welcome page 를 이미 mapping 했기 때문에 이걸 호출
    // 즉 Controller 에 해당하는 것들을 먼저 찾고 없으면 static 을 찾음
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
