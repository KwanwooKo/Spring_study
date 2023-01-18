package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * 테스트 작성 시 주의사항
 * 각각의 테스트는 끝났을 때 테스트 클래스의 멤버변수를 clear 시켜줘야 해 => AfterEach
 * 오히려 테스트 코드를 먼저 작성해야됨 => 테스트 주도 개발(TDD)
 */
public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 테스트 하나가 종료될때마다 이 함수를 실행
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member resultById = repository.findById(member.getId()).get();
        // 이거 2개를 동시에 사용 불가능 => 경로가 달라서
//        Assertions.assertEquals(member, resultById);
        assertThat(member).isEqualTo(resultById);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result1 = repository.findByName("spring1").get();
        assertThat(result1).isEqualTo(member1);

        Member result2 = repository.findByName("spring2").get();
        assertThat(result2).isEqualTo(member2);

    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}














