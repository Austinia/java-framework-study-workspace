package com.austinia.user;

import lombok.*;

@Data
@Builder // 빌더 패턴을 사용한다
@NoArgsConstructor // 기본 생성자를 만들어준다
@AllArgsConstructor // 빌더는 모든 전달인자를 생성자를 모두 포함한 생성자가 필요하다
public class User {
    private Integer id;
    private String name;
    private String password;
}
