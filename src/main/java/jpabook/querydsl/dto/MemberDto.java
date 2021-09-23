package jpabook.querydsl.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Yoo Ju Jin(jujin1324@daum.net)
 * Created Date : 2021/09/23
 */

@Data
@NoArgsConstructor
public class MemberDto {
    private String username;
    private int age;

    public MemberDto(String username, int age) {
        this.username = username;
        this.age = age;
    }
}
