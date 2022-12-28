package com.austinia.usermanagement;

// 롬복을 이용한다.
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data // getter setter가 필요없어진다.
@Builder // 편하게 오브젝트 생성가능
@NoArgsConstructor // 빌더를 사용하기 위해선 자동으로 생성자를 만들어줘야 하는데
@AllArgsConstructor // 프레임워크에서 유저를 세팅할대는 빌더로 세팅하지 않아서 이 두 가지는 해줘야 한다.
@Entity(name = "userinfo") // 엔티티 선언에 테이블 네임을 정해줘야 한다.
public class User {
    @Id // 엔티티에는 반드시 아이디가 있어야 한다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB에서 자동으로 알아서 ID를 생성해주게 한다.
    private Integer id;
    private String name;
    private String password; // field라고 한다.


}
