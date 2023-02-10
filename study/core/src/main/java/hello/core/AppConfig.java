package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Design Pattern: Factory method
// 이렇게 AppConfig 를 사용하면서 좋아지는 점 => 사용 영역의 코드를 아예 수정하지 않아도 작동한다, 구성 영역의 코드만 수정하면 됨
@Configuration  // 설정정보, BeanDefinition 생성
public class AppConfig {

    /*
     * @Configuration 을 빼면 어떻게 될까?
     * => cglib 사용하지 않고 AppConfig 클래스 자체를 사용함
     * 그럼 이게 뭐가 달라짐? => 다른 Singleton 이 깨져버림, 예를들어 memberRepository 가 여러개가 되어버림
     *
     * @Bean => method 에서 반환된 객체를 spring container 에 저장 (singleton 방식으로)
     * @Bean memberService -> new MemoryMemberRepository()
     * @Bean orderService -> new MemoryMemberRepository()
     * => 이러면 MemoryMemberRepository 를 두번 호출함
     */

    @Bean
    public MemberService memberService() {
        // 역할을 사용하고, 구현이 역할 안에 들어가게끔 설정
        System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        // return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }

}
