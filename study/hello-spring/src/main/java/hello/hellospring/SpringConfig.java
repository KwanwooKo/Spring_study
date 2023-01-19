package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 이렇게 설정파일을 따로 만들어서 쓰는 이유는?
 * @Service, @Controller, @Repository 같은 정형적인 컴포넌트는 그냥 써도 되지만
 * 사용하기 힘든 컴포넌트는 직접 만들어줘야 함
 * 그리고 설정 파일만 변경해주면 코드 수정없이 의존성 관계를 쉽게 변경할 수 있기 때문에 자주 씀
 */

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
