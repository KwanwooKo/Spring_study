package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// Spring Bean 들을 쭉 끌어오는 거, @Component 가 붙은 모든 클래스를 빈으로 등록
@ComponentScan(
        //basePackages = "hello.core.member", // 이렇게 하면 member 만 component scan 의 대상이 된다
        // 가져오는 거에 기준을 둬서 가져오는 거, 보통 Configuration 을 제외하진 않지만, 기존에 작성했던 코드들을 수정하지 않으려고 filter 를 적용함
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
    // basePackages 를 지정하지 않으면 이 클래스가 있는 패키지를 다 찾는다 => hello.core
    // 그래서 권장하는 방법은 설정 정보 클래스를 프로젝트 최상단에 두는 것.

//    @Bean(name = "memoryMemberRepository")
//    MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
}
