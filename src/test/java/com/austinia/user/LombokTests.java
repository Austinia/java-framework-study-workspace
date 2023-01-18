//package com.austinia.user;
//
//import org.junit.jupiter.api.Test;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.core.Is.is;
//
//public class LombokTests {
//    @Test
//    public void equals(){
//        // builder 패턴은 value들을 메서드를 통해서 정의할 수 있게 한다
//        User user = User.builder().id(1).name("Updated Hulk").password("1234").build();
//        User user2 = User.builder().id(1).name("Updated Hulk").password("1234").build();
//
//        assertThat(user, is(user2));
//
//    }
//}
