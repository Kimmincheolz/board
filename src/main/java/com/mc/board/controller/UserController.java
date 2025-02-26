package com.mc.board.controller;

import com.mc.board.dto.UserDto;
import com.mc.board.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/")
    public String mainPage() {
        return "index";  // index.html을 반환
    }

    // 회원가입 화면
    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("userDto", new UserDto());  // 빈 UserDto 객체를 모델에 추가
        return "user/signup";  // signup.html을 반환
    }

    // 아이디 중복 체크
    @GetMapping("/check-id")
    @ResponseBody
    public Map<String, Object> checkId(@RequestParam("userId") String userId) {
        Map<String, Object> response = new HashMap<>();
        boolean available = userService.checkIdAvailability(userId);
        response.put("available", available);
        return response;
    }

    // 이메일 중복 체크
    @GetMapping("/check-email")
    @ResponseBody
    public Map<String, Object> checkEmail(@RequestParam("email") String email) {
        Map<String, Object> response = new HashMap<>();
        boolean available = userService.checkEmailAvailability(email);
        response.put("available", available);
        return response;
    }

    // 회원가입 처리
    @PostMapping("/signup")
    public String signUp(UserDto userDto, Model model) {
        try {
            userService.insertUser(userDto);
            return "redirect:/";  // 성공 시 리디렉션
        } catch (IllegalStateException ex) {
            model.addAttribute("error", ex.getMessage());  // 예외 메시지를 모델에 추가
            return "signup";  // signup 페이지로 돌아가서 에러 메시지 표시
        }
    }

    // 로그인 화면
    @GetMapping("/login")
    public String loginPage() {
        return "user/login";  // login.html을 반환
    }

    @GetMapping("/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg","아이디 또는 비밀번호를 확인해주세요");
        return "user/login";
    }

    @GetMapping("/admin/list")
    public String userList(Model model){
        model.addAttribute("userList",userService.userList());
        return "admin/list";
    }

}
