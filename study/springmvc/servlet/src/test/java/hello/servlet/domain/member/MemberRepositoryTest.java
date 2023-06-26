package hello.servlet.domain.member;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach() {
        memberRepository.clearStore();
    }


    @Test
    void save() {
        // given
        Member member = new Member("kwanwoo", 25);

        // when
        Member savedMember = memberRepository.save(member);

        // then
        Member findMember = memberRepository.findById(savedMember.getId());

        assertThat(findMember).isEqualTo(member);
    }

    @Test
    void findAll() {
        // given
        Member member1 = new Member("member1", 21);
        Member member2 = new Member("member2", 27);

        memberRepository.save(member1);
        memberRepository.save(member2);

        // when
        List<Member> memberList = memberRepository.findAll();

        // then
        assertThat(memberList.size()).isEqualTo(2);
        assertThat(memberList).contains(member1, member2);
    }
}