package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA: A사용자 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);
        // ThreadB: B사용자 20000원 주문
        int userBPrice = statefulService2.order("userB", 20000);

        // ThreadA: 사용자A 주문 금액 조회
//        int price = statefulService1.getPrice(); // 이거 ACID 원칙 때 헀던거네 ㅋㅋ
//        System.out.println("price = " + price);

        /**
         * 실제 쓰레드 내용으로 하진 않았지만,
         * 쓰레드가 반영되면 결국에 commit 되지 않으면 내용이 누적되지 않고,
         * 마지막 내용이 덮어 씌워버리기 때문에 원하는 값이 나오지 않음
         * => 그렇기 때문에 스프링은 항상 무상태(stateless)로 설계해야 함
         */
//        assertThat(statefulService1.getPrice()).isEqualTo(20000);
        assertThat(userAPrice).isNotEqualTo(userBPrice);
    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }

}