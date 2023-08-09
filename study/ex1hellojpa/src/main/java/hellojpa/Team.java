package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "TEAM_ID")
    private Long id;
    private String name;


    // 얘는 JoinColumn을 쓰면 안돼
    // DB에서 Foreign key를 누구로 mapping 할건지 알아야 해
    // 그래서 둘 중 하나로만 foreign key로 mapping 해야돼 (Foreign key의 owner)
    // 여기서는 owner를 member로 설정, 그러면 남은 team은 주인이 아니지
    // 그래서 mappedBy로 주인 지정
    // members에는 조회만 가능, db에 값을 집어넣으려면 owner를 통해서 넣어야 해
    // 그러면 누구를 owner로 설정? => 외래 키가 있는 곳을 owner로 설정
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<>();       // new ArrayList 해주는 게 관례 => null pointer 방지

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addMember(Member member) {
        member.setTeam(this);
        members.add(member);
    }
}
