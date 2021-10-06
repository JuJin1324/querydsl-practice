package jpabook.querydsl.repository.pure.jpa;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jpabook.querydsl.dto.MemberSearchCondition;
import jpabook.querydsl.dto.MemberTeamDto;
import jpabook.querydsl.dto.QMemberTeamDto;
import jpabook.querydsl.entity.Member;
import jpabook.querydsl.entity.QTeam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static jpabook.querydsl.entity.QMember.member;
import static jpabook.querydsl.entity.QTeam.*;

/**
 * Created by Yoo Ju Jin(jujin1324@daum.net)
 * Created Date : 2021/10/06
 */

@Repository
@RequiredArgsConstructor
public class MemberJpaRepository {
    private final EntityManager   entityManager;
    private final JPAQueryFactory queryFactory;

    public void save(Member member) {
        entityManager.persist(member);
    }

    public List<Member> findAll() {
        return queryFactory
                .selectFrom(member)
                .fetch();
    }

    public List<Member> findByUsername(String username) {
        return queryFactory
                .selectFrom(member)
                .where(member.username.eq(username))
                .fetch();
    }

    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(
                queryFactory
                        .selectFrom(member)
                        .where(member.id.eq(id))
                        .fetchOne()
        );
    }

    public List<MemberTeamDto> searchByBuilder(MemberSearchCondition condition) {
        BooleanBuilder builder = new BooleanBuilder();
        if (StringUtils.hasText(condition.getUsername())) {
            builder.and(member.username.eq(condition.getUsername()));
        }
        if (StringUtils.hasText(condition.getTeamName())) {
            builder.and(team.name.eq(condition.getTeamName()));
        }
        if (condition.getAgeGoe() != null) {
            builder.and(member.age.goe(condition.getAgeGoe()));
        }
        if (condition.getAgeLoe() != null) {
            builder.and(member.age.loe(condition.getAgeLoe()));
        }
        return queryFactory
                .select(new QMemberTeamDto(
                        member.id.as("memberId"),
                        member.username,
                        member.age,
                        team.id.as("teamId"),
                        team.name.as("teamName")))
                .from(member)
                .leftJoin(member.team, team)
                .where(builder)
                .fetch();
    }
}
