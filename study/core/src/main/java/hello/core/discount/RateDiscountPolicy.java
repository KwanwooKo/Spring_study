package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@MainDiscountPolicy  // 내가 만든 annotation
//@Qualifier("mainDiscountPolicy")
// bean 이 여러개 여도 이 클래스를 최우선적으로 선택한다
@Primary
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    // 할인 되는 금액을 반환
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
