package com.mc.board.service;

import com.mc.board.dto.UserDto;
import com.mc.board.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserService {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDto>userList(){
        return userMapper.userList();
    }

    //회원 가입
    public int insertUser(UserDto userDto){
        this.overlapId(userDto.getUserId());
        this.overlapEmail(userDto.getEmail());
        String encodedPassword = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encodedPassword);
        return userMapper.insertUser(userDto);
    }

    // 아이디 중복 여부 체크
    public boolean checkIdAvailability(String userId) {
        UserDto existingUser = userMapper.overlapId(userId);
        return existingUser == null;  // null이면 사용 가능
    }

    // 이메일 중복 여부 체크
    public boolean checkEmailAvailability(String email) {
        UserDto existingUser = userMapper.overlapEmail(email);
        return existingUser == null;  // null이면 사용 가능
    }


    public void overlapId(String userId){
        UserDto findId = userMapper.overlapId(userId);
        if(findId != null){

            throw new IllegalStateException("중복된 아이디 입니다");
        }
    }
    public void overlapEmail(String email){
        UserDto findId = userMapper.overlapEmail(email);
        if(findId != null){

            throw new IllegalStateException("중복된 이메일 입니다");
        }
    }

}
