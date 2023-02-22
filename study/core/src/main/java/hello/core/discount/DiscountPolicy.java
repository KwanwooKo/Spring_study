package hello.core.discount;

import hello.core.member.Member;

// 구현체와 추상화는 같은 패키지에 묶어놓도록 하자
public interface DiscountPolicy {

    /**
     * @return 할인 대상 금액
     */
    int discount(Member member, int price);
}
