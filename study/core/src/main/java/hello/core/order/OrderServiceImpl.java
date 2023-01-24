    package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    // 근데 여기에 구현체에 의존하고 있는 코드가 있음 => DIP 위반,
    // Impl 클래스에서는 이런게 있으면 안되지
    // 결론은 나는 Impl 클래스의 코드를 절대 수정하고 싶지 않아
    // 그래서 인터페이스 에만 의존하도록 코드를 바꾸면 됨
    // 해결: discountPolicy 만 선언 => 이러면 Impl 은 interface 에만 의존
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
