package com.austinia.usermanagement;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is; // 자동완성에 안 떠서 직접 입력
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*; // 해당 객체의 모든 멤버를 static으로 임포트
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status; // 자동완성에 안 떠서 직접 입력


@SpringBootTest // 스프링 부트 테스트를 할 것
@AutoConfigureMockMvc // 컨트롤러를 가짜 오브젝트를 만들어서 테스트 수행을 할 것
public class UserControllerTests {
    @Autowired // 생명력을 불어넣어 주기
    MockMvc mockMvc;

    @MockBean // 가짜빈
    private UserController userController; // main에 구현했다.

    @Test // 테스트을 위한 메서드
    public void list() throws Exception { //perform의 예외처리를 넘겨버림
//        mockMvc.perform(MockMvcRequestBuilders.get("/api/list")); 자주 쓰는 구문이니 객체를 쓰지 않도록 static으로 임포트 하자
        List<User> users = new ArrayList<>();
//        User user = new User();
//        user.setId(id);
//        user.setName(name); new 하지 않아도 되는 Lombok 라이브러리를 사용해보자
        int id = 1;
        String name = "Austin";
        User user = User.builder().id(id).name(name).build(); // Lombok을 이용함
        users.add(user);
        given(userController.list()).willReturn(users); // 유저컨트롤러의 list메서드를 호출하면 users를 리턴할 거다.
        mockMvc.perform(get("/api/list"))
                .andExpect(status().isOk()) // 200
                .andExpect(jsonPath("$", hasSize(1))) // $ 루트
                .andExpect(jsonPath("$[0].id", is(id))) // id가 특정 아이디와 동일한가
                .andExpect(jsonPath("$[0].name", is(name))); // name이 특정 이름과 동일한가
    }
}
