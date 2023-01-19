package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    // new 로 생성하면 안 좋음 => 다른데서 모든 곳에서 new 를 호출하게 되니까
    // 그래서 하나만 생성하도록 좋은 것들은 하나만 사용 => 스프링 컨테이너에서 가져다 씀
    private final MemberService memberService;

    // 이렇게 하면 스프링 컨테이너에 있는 MemberService 를 가져다 쓸 수 있음
    // 그런데 아직 스프링 컨테이너에 등록이 안되어 있음 => 그래서 Service 와 Repository annotation 을 추가 해줌
    // @Component 를 추가하면 스프링 빈으로 자동 등록된다
    // @Controller, @Service, @Repository 가 등록된 이유는 해당 annotation 이 @Component 를 가지고 있기 때문
    // 이 컴포넌트 스캔은 같은 패키지 내부에 대해서만 적용됨 => 그래서 HelloSpringApplication 이 들어있는 패키지에 대해서만 적용
    // 스프링 빈 은 싱글톤으로 등록된다 => 유일하게 하나만 등록 => 그럼 모든 곳에서 같은 인스턴스를 사용
    // @Autowired 를 이용한 의존성 주입이 많은데 생성자 주입이 좋음 => 제일 처음에만 필요하기 때문에 + 의존관계가 runtime 중에 변경될 일이 없기 떄문
    // 그래서 그냥 생성자 주입 씁시다
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    // form 에서 post 방식으로 넘어온 데이터를 받을 때
    // 그래서 parameter(form) 에 해당 내용이 들어왔어
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        System.out.println("member.getName() = " + member.getName());
        memberService.join(member);
        // home 화면으로 돌려
        return "redirect:/";
    }
}
