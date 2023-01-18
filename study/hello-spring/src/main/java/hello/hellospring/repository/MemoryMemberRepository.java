package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    // 1. 우선 save 를 해야하니까 저장소가 필요하겠네? => 그래서 일단 저장소부터 만들어
    private static Map<Long, Member> store = new HashMap<>();
    // 2. 회원 id 를 순차적으로 부여해줘야 하니까 이거 필요하지
    private static long sequence = 0L;

    // member 에 이름만 정해진 객체가 들어올거고 여기다가 id만 세팅해주면 됨(상황 가정)
    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // null 일 수 있기 때문에 null이어도 반환이 가능하도록
        // 클라이언트 에서 null 의 여부가 중요하기 떄문인듯? flutter 생각해보면
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // store 에 해당하는 Member 들을 전체 탐색
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
