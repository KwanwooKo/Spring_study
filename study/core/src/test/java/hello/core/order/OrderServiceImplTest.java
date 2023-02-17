package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createOrder() {
        OrderService orderService = new SetterOrderServiceImpl();
        orderService.createOrder(1L, "itemA", 10000);
    }

    static class SetterOrderServiceImpl implements OrderService {
        private MemberRepository memberRepository;
        private DiscountPolicy discountPolicy;

        @Autowired
        public void setMemberRepository(MemberRepository memberRepository) {
            this.memberRepository = memberRepository;
        }

        @Autowired
        public void setDiscountPolicy(DiscountPolicy discountPolicy) {
            this.discountPolicy = discountPolicy;
        }

        @Override
        public Order createOrder(Long memberId, String itemName, int itemPrice) {
            return null;
        }
    }
}