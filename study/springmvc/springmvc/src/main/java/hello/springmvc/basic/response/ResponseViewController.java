package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello!");

        return mav;
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello!");
        // 이거 prefix, suffix 이상하지 않아? => 스프링 부트가 default로 /templates/~~~.html 로 맞춰줘
        return "response/hello";
    }

    /**
     * 근데 이건 너무 불명확하고
     * 이걸 쓰려면 만족해야 하는 조건들이 있어서
     * 안 쓰는 걸 추천
     */
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello!");
    }



}
