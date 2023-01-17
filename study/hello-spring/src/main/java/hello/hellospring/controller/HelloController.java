package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    // Web Application 에서 hello 가 들어오면 이 메서드를 실행 => 그니까 url 에서 루트 폴더 기준으로 hello 를 치면 return 에 있는 html 파일이 랜더링
    @GetMapping("hello")
    public String hello(Model model) {

        model.addAttribute("data", "hello!!");

        // return 의 이름이 hello 를 해줬는데 이게 resources/templates 의 hello.html 이야
        // 그래서 hello.html 을 랜더링 한다는 소리
        // 이걸 viewResolver 가 resources:templates/ + ${ViewName} + .html 을 랜더링 시켜줘 => 이거 Thymeleaf 템플릿 엔진이 처리해줘
        return "hello";
    }

    @GetMapping("hello-mvc")
    // RequestParam: 외부(웹사이트)에서 parameter 를 받을 예정 => URL 에서 받아왔네, 이러려면 url 에서 무조건 parameter 가 필요
    public String helloMvc(@RequestParam("name") String name, Model model) {
        // 앞에 거가 key, 뒤에가 value
        model.addAttribute("name", name);
        return "hello-template";
    }

    // 두개만 생각 => API 방식으로 내리냐, html 로 내리냐
    @GetMapping("hello-string")
    // http 통신 프로토콜의 body 에 이 내용을 그대로 넣어주겠다 => 와 이거 네트워크 내용 모르면 망하네
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        // html 이 아니라 이 코드를 그대로 내려줌 => 궁금하면 페이지 소스 확인으로 확인해보면 차이 확인 가능
        // 이거 그래서 객체 형태로 나와 (JSON)
        return "hello " + name; // hello spring
    }


    @GetMapping("hello-api")
    // default 로 json 으로 반환, 물론 xml 방식도 가능하지만 요즘은 무조건 json
    // ResponseBody 가 붙어있으면 객체를 넘기는거라 viewResolver 한테 넘기지 않고
    // HttpMessageConverter 가 받아서 JsonConverter StringConverter 둘 중 하나를 이용해서 넘김
    // 객체처리: MappingJackson2HttpMessageConverter(Json format 으로 바꿔주는 유명한 친구) 가 처리
    // 문자처리: HttpMessageConverter 가 처리
    // 실무에서 거의 손대지 않습니다
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        // 어? 처음으로 객체를 넘겼네? => json 형태로 데이터를 넘김, 이게 api 방식
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}