package com.mc.board.controller;

import com.mc.board.dto.PostDto;
import com.mc.board.service.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    // 게시글 목록 페이지
    @GetMapping("/post/post")
    public String postList(Model model) {
        model.addAttribute("postList", postService.postList());
        return "post/post";
    }

    // 게시글 작성 페이지
    @GetMapping("/post/postInsert")
    public String postInsertPage() {
        return "post/postForm";
    }

    // 게시글 작성 처리
    @PostMapping("/post/postInsert")
    public String postInsert(PostDto postDto, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        postDto.setUserId(userId); // user_id를 PostDto에 설정

        postService.insertPost(postDto);
        return "redirect:/post/post";  // 게시글 작성 후 게시글 목록 페이지로 리다이렉트
    }

    // 게시글 상세 보기
    @GetMapping("/post/{postId}")
    public String findPost(@PathVariable("postId") int postId, Model model, HttpSession session) {
        PostDto postDto = postService.findPost(postId);
        String loggedInUserId = (String) session.getAttribute("userId");

        model.addAttribute("post", postDto);
        model.addAttribute("loggedInUserId", loggedInUserId);
        postService.viewUpdate(postId);
        return "post/postDetail";
    }

    // 게시글 수정 페이지
    @GetMapping("/post/edit/{postId}")
    public String editPostPage(@PathVariable("postId") int postId, Model model, HttpSession session) {
        // 게시글 정보를 가져옴
        PostDto postDto = postService.findPost(postId);
        String loggedInUserId = (String) session.getAttribute("userId");

        // 수정할 게시글 정보와 로그인된 사용자 ID를 모델에 추가
        if (loggedInUserId != null && loggedInUserId.equals(postDto.getUserId())) {
            model.addAttribute("post", postDto);
            return "post/postEditForm";  // 수정 폼으로 이동
        }

        // 로그인된 사용자와 게시글 작성자가 다르면 수정 페이지에 접근하지 못하도록 처리
        return "redirect:/post/post";  // 목록 페이지로 리다이렉트
    }

    // 게시글 수정 처리
    @PostMapping("/post/edit/{postId}")
    public String updatePost(@PathVariable("postId") int postId, PostDto postDto, HttpSession session) {
        // 세션에서 사용자 ID 가져오기
        String userId = (String) session.getAttribute("userId");
        postDto.setPostId(postId);
        postDto.setUserId(userId);

        // 게시글 업데이트 서비스 호출
        postService.updatePost(postDto);

        return "redirect:/post/post";  // 수정된 게시글 상세 페이지로 리다이렉트
    }


}
