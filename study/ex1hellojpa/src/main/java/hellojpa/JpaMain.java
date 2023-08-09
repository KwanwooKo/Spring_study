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

            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            em.persist(member);

            // 연관관계 편의 메소드
            // 연관관계 편의 메소드는 양쪽에 쓰면 안되고 한쪽에만 적용
            team.addMember(member);

            // 양방향 연관관계에서 항상 양쪽에 값을 설정하자
            // JPA 입장에서 db에서는 적용되지만, 메모리 상에서는 이걸 확인할 수가 없어
            // 근데 이거 개발자가 한줄한줄 다 쓰는게 사람이다 보니 까먹을 수도 있어
            // => 연관관계 편의 메소드 작성 => setTeam 에서 add 를 같이 적용
//            team.getMembers().add(member);

//            em.flush();
//            em.clear();

            Team findTeam = em.find(Team.class, team.getId());      // 1차 캐시
            List<Member> members = findTeam.getMembers();

            System.out.println("==========================");
            for (Member m : members) {
                System.out.println("m.name = " + m.getUsername());
            }
            System.out.println("==========================");

            trx.commit();
        } catch (Exception e) {
            trx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
