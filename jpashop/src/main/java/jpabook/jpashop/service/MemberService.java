package jpabook.jpashop.service;

import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.domain.Member;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)  // trx 안에서 데이터 변경이 돼야하기 때문에
@RequiredArgsConstructor    // final로 된 변수들만 생성자로 생성
public class MemberService {

//    @Autowired  // Spring bean 에 있는 memberRepository를 가져옴
    private final MemberRepository memberRepository;

    /**
     * setter injection => test code 같은 거 작성할 때
     * memberRepository를 지정해서 사용 가능
     * 근데 별로 안좋아 => logic 이 실행되는 도중에 repository를 바꿀 일이 없어
     * 이렇게 냅두면 logic 실행 중에 repository 변경 가능해서 문제
     * => 그래서 요즘 쓰는건 생성자 주입
     */
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    /**
     * 생성자 injection
     * memberRepository도 지정가능하고, logic 실행 도중에 repository가 바뀔일도 없어서 좋음
     * 생성자가 하나 밖에 없는 경우에는 알아서 Autowired 적용
     */
//    @Autowired
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    /**
     * 회원 가입
     */
    @Transactional      // default readOnly = false
    public Long join(Member member) {
        validateDuplicateMember(member);        // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 회원 전체 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long id) {
        return memberRepository.findOne(id);
    }
}
