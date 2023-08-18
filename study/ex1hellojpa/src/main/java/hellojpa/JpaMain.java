package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Set;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction trx = em.getTransaction();
        trx.begin();

        try {

            Member member = new Member();
            member.setUsername("member1");
            member.setHomeAddress(new Address("homeCity", "street", "10000"));

            member.getFavoriteFoods().add("치킨");
            member.getFavoriteFoods().add("족발");
            member.getFavoriteFoods().add("피자");

            member.getAddressHistory().add(new AddressEntity("old1", "street", "10000"));
            member.getAddressHistory().add(new AddressEntity("old2", "street", "10000"));

            em.persist(member);

            // DB에만 존재, 메모리에는 없어
            em.flush();
            em.clear();

            System.out.println("====================== START ======================");
            Member findMember = em.find(Member.class, member.getId());

            /**
             * 값 타입 Collection은 언제 써?
             * 값을 더이상 추적할 필요가 없는 데이터의 경우
             * 그러면 주소는 뭘 써야될까?
             * -> 무조건 Entity
             *
             * 값 타입은 값 타입이라 판단될 때만 사용해야돼
             */


            // homeCity -> newCity
//            findMember.getHomeAddress().setCity("newCity");       // 절대 이렇게 쓰면 안돼
//            Address a = findMember.getHomeAddress();
//            findMember.setHomeAddress(new Address("newCity", a.getStreet(), a.getZipcode()));

            // 치킨 -> 한식
//            findMember.getFavoriteFoods().remove("치킨");     // Collection의 값만 변경되어도 db에서 실제값이 같이 변경돼
//            findMember.getFavoriteFoods().add("한식");

            // equals, hashCode 로 삭제해
//            findMember.getAddressHistory().remove(new AddressEntity("old1", "street", "10000"));
//            findMember.getAddressHistory().add(new AddressEntity("newCity1", "street", "10000"));

            trx.commit();
        } catch (Exception e) {
            trx.rollback();
        } finally {
            em.close();
        }


        emf.close();
    }
}
