package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;


/**
 * 이렇게 설정파일을 따로 만들어서 쓰는 이유는?
 * @Service, @Controller, @Repository 같은 정형적인 컴포넌트는 그냥 써도 되지만
 * 사용하기 힘든 컴포넌트는 직접 만들어줘야 함
 * 그리고 설정 파일만 변경해주면 코드 수정없이 의존성 관계를 쉽게 변경할 수 있기 때문에 자주 씀
 */

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    // 여기서 MemberRepository 의 구현체를 변경해주면 됨
//    @Bean
//    public MemberRepository memberRepository() {
//         이러면 db 가 바뀔 때마다 코드를 수정하는 게 아니라 그냥 코드를 새로 작성하고 이거만 수정하면 되니까
//         유지보수가 좋아짐
//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }
}
