package com.austinia.user;

import lombok.*;

import javax.persistence.*;

@Data
@Builder // 빌더 패턴을 사용한다
@NoArgsConstructor // 기본 생성자를 만들어준다
@AllArgsConstructor // 빌더는 모든 전달인자를 생성자를 모두 포함한 생성자가 필요하다
@Entity(name = "userinfo") // 테이블과 이름이 같으면 괄호 생략 가능
public class User {
    // 모든 엔티티(테이블)는 ID가 필요하고, 데이터베이스의 룰을 따라 자동생성 됩니다
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name") // 필드 이름이 똑같으면 생략 가능
    private String name;
    private String password;
}
