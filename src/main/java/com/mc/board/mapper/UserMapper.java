package com.mc.board.mapper;

import com.mc.board.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper

public interface UserMapper {

    List<UserDto> userList();
    //회원가입
    int insertUser(UserDto userDto);
    // 아이디 중복 여부 체크
    UserDto overlapId(String userId);
    // 이메일 중복 여부 체크
    UserDto overlapEmail(String email);
    //로그인
    UserDto loginUser(String userId);



}
