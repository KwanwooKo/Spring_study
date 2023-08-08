package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction trx = em.getTransaction();
        trx.begin();

        try {

            // 영속
            Member member = em.find(Member.class, 150L);
            member.setName("AAAAA");

            // 특정 entity만 준영속 상태로 전환
//            em.detach(member);          // trx commit할 때 반영하지 않음 => update query가 나가지 않아

            em.clear();                   // 영속성 context를 전체 초기화

            Member member2 = em.find(Member.class, 150L);       // 이 경우에 find query 다시 나옴

            System.out.println("=======================");
            trx.commit();
        } catch (Exception e) {
            trx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
