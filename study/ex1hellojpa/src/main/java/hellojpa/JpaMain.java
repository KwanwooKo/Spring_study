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
            member.setName("ZZZZZ");

//            em.persist(member); => 이건 쓰면 안돼(객체처럼 다루기 위해 쓰기 때문에 그냥 call by reference 랑 같다고 생각)

            System.out.println("=================================");

            trx.commit();
        } catch (Exception e) {
            trx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
