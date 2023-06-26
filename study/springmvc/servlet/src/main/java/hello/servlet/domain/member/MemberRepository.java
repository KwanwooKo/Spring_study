package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    private static final MemberRepository instance = new MemberRepository();

    public static MemberRepository getInstance() {
        return instance;
    }

    private MemberRepository() {
    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        // 이렇게 반환해도, call by reference 이기 때문에 값을 변경하면 알아서 값이 변경됨
        // 그러면 왜 ArrayList를 새로 할당해서 반환하냐?
        // => store 자체를 보호하기 위해서
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }

}
