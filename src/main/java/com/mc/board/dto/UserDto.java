package com.mc.board.dto;

import com.mc.board.constant.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDto {

    private  Long id;

    private String userId;

    private String userName;

    private String password;

    private String email;

    private LocalDateTime createdAt;

    private Role role;



}
