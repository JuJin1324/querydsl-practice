package jpabook.querydsl.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.*;

/**
 * Created by Yoo Ju Jin(jujin1324@daum.net)
 * Created Date : 2021/09/09
 */

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Team {
    @Id
    @GeneratedValue
    @Column(name = "team_id")
    private Long   id;
    private String name;

    @OneToMany(mappedBy = "team", fetch = LAZY)
    private final List<Member> members = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }
}
