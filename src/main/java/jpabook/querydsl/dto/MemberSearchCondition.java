package jpabook.querydsl.dto;

import lombok.Data;

/**
 * Created by Yoo Ju Jin(jujin1324@daum.net)
 * Created Date : 2021/10/06
 */

@Data
public class MemberSearchCondition {
    private String username;
    private String teamName;
    private Integer ageGoe;
    private Integer ageLoe;
}
