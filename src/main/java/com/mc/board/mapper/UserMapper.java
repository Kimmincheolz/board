package com.mc.board.mapper;

import com.mc.board.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper

public interface UserMapper {

    List<UserDto> userList();

    int insertUser(UserDto userDto);

    UserDto overlapId(String userId);

    UserDto overlapEmail(String email);

    UserDto loginUser(String userId);



}
