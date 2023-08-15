package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity     // JPA가 관리하는 객체
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

//    @Column(name = "TEAM_ID")
//    private Long teamId;

    // 누가 1이고 누가 n인지 정확하게
    // 여기서는 Member == n / Team == 1
    // 이 클래스 입장에서 몇 대 몇인지 고려
    // 그래서 Member 객체가 Team 객체에 어떤 관계가 있는지 알려줘야 해
    // TEAM_ID가 그 역할
    // Team 객체와 team_id column을 mapping
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @OneToOne
    @JoinColumn(name = "LOCKER_ID")
    private Locker locker;

    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

}