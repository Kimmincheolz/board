package com.mc.board;

import com.mc.board.dto.UserDto;
import com.mc.board.mapper.UserMapper;
import com.mc.board.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;


import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UsersTest {
    @Autowired
    private UserService userService;
    private UserMapper usersMapper;
    private PasswordEncoder passwordEncoder;


    @Test
    @DisplayName("유저 테스트")
    public void list(){
        List<UserDto> list = userService.userList();

        System.out.println(list);
    }

    @Test
    @DisplayName("유저 추가 테스트")
    public void insert(){
        UserDto userDto = new UserDto();
        userDto.setUserId("test3");
        userDto.setUserName("테스트3");
        userDto.setPassword("1234");
        userDto.setEmail("test@test3");

        int result = userService.insertUser(userDto);
        System.out.println(result);

    }

}
