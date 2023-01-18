package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    // 근데 memberRepository 는 다른 객체잖아 => 일단 static 이라 상관은 없긴해
    // 근데 다른 인스턴스일 수 있잖아 => 그럼 다른 객체니까
    // 이 repository 를 같은 걸로 일치 시켜야돼
    // beforeEach 에다가 memberRepository 를 memberService 를 넣어줘
    // 이러면 memberRepository 의 memberService 객체와 여기서 memberService 객체가 같아
    MemoryMemberRepository memberRepository;
    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }


    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("hello");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    // 예외가 실제로 발생하는지도 확인해야 해
    @Test
    public void 중복회원예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);

        // try catch 문으로 짜기 불편하니까 assertThrows 로 대체
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        try {
//            // 여기서 exception 이 터짐
//            memberService.join(member2);
//            // 그럼 이 fail 코드는 실행되면 안됨
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        // then
    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}