package com.mc.board.service;

import com.mc.board.constant.Role;
import com.mc.board.dto.UserDto;
import com.mc.board.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService {

    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto user =userMapper.loginUser(username);

        if(user == null){
            throw new UsernameNotFoundException("유저가 없습니다");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();

        if("admin".equals(user.getRole().toString())){
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.toString()));
        }else {
            authorities.add(new SimpleGrantedAuthority(Role.USER.toString()));
        }

        return User.builder()
                .username(user.getUserId())
                .password(user.getPassword())
                .roles(user.getRole().toString())
                .build();
    }
}
