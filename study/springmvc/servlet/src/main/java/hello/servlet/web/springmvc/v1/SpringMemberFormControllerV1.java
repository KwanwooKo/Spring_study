package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

//@Component
//@RequestMapping
// Controller 대신에 위의 2개 써도 되는데, Controller는 1개만 쓰니까 이게 더 편하지
@Controller     // spring이 자동으로 bean으로 등록(ComponentScan 대상이됨) + annotatation 기반 컨트롤러로 인식
public class SpringMemberFormControllerV1 {

    // 요청 정보를 매핑, 해당 URL이 호출되면 이 메서드 호출
    @RequestMapping("/springmvc/v1/members/new-form")
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }
}
