package jpabook.querydsl.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Yoo Ju Jin(jujin1324@daum.net)
 * Created Date : 2021/09/09
 */

@ActiveProfiles("test")
@SpringBootTest
@Transactional
@Commit
class MemberTest {
    @Autowired
    EntityManager em;

    @Test
    void testEntity() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);

        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);

        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);

        em.flush();
        em.clear();

        List<Member> foundMembers = em.createQuery("select m from Member m left join fetch m.team", Member.class)
                .getResultList();
        foundMembers.forEach(member -> {
            System.out.println("member.getUsername() = " + member.getUsername());
            System.out.println("member.getAge() = " + member.getAge());
            System.out.println("member.getTeam().getName() = " + member.getTeam().getName());
        });
    }
}
