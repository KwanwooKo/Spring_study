package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor // 생성자를 만들어줌, final 이 붙은 멤버변수들을 다 생성자로 설정 => 생성자 코드도 사라짐
public class OrderServiceImpl implements OrderService {

    // 근데 여기에 구현체에 의존하고 있는 코드가 있음 => DIP 위반,
    // Impl 클래스에서는 이런게 있으면 안되지
    // 결론은 나는 Impl 클래스의 코드를 절대 수정하고 싶지 않아
    // 그래서 인터페이스 에만 의존하도록 코드를 바꾸면 됨
    // 해결: discountPolicy 만 선언 => 이러면 Impl 은 interface 에만 의존 => AppConfig 에서 해당 interface 에 구현체 주입
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    // 생성자를 통해서만 의존관계가 주입됨 => 불변
    // + 생성자를 쓰면 변수를 final 로 지정할 수 있다
    // 그래서 set 이용해서 필드 injection 은 사용하면 안됨
    // 유일하게 사용해도 되는 곳? => 테스트 코드
    // 생성자가 딱 1개만 있으면 자동으로 @Autowired 가 생성된다
    // Lombok을 이용하면 생성자 코드도 필요 없어짐
    // 같은 interface 에 대해 bean 이 여러개 존재하면 Qualifier 를 이용해서 구분할 수 있다
    // 정확히는 모든 클래스에 대해 적용이 가능하지만, 경험적으로 같은 interface 에서 찾는게 더 좋다
    public OrderServiceImpl(MemberRepository memberRepository, /*@Qualifier("mainDiscountPolicy")*/ @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
