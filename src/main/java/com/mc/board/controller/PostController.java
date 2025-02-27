package com.mc.board.controller;

import com.mc.board.dto.CommentDto;
import com.mc.board.dto.PostDto;
import com.mc.board.dto.LikeDto;
import com.mc.board.service.CommentService;
import com.mc.board.service.PostService;
import com.mc.board.service.LikeService;  // 추가
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private LikeService likeService;  // LikeService 주입

    @Autowired
    private CommentService commentService;



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

        boolean likedByUser = likeService.isLikedByUser(postId, loggedInUserId); // 사용자가 좋아요한 상태 확인

        List<CommentDto> commentList = commentService.commentList(postId);

        model.addAttribute("post", postDto);
        model.addAttribute("loggedInUserId", loggedInUserId);
        model.addAttribute("likedByUser", likedByUser); // 좋아요 상태 전달
        model.addAttribute("commentList", commentList); // 댓글 목록 전달
        postService.viewUpdate(postId);
        return "post/postDetail";
    }

    @PostMapping("/post/{postId}/addComment")
    public String addComment(@PathVariable("postId") int postId, @RequestParam("content") String content, HttpSession session) {
        String userId = (String) session.getAttribute("userId");

        // CommentDto 객체 생성
        CommentDto commentDto = new CommentDto();
        commentDto.setPostId(postId);
        commentDto.setUserId(userId);
        commentDto.setContent(content);

        // 댓글 추가
        commentService.insertComment(commentDto);

        return "redirect:/post/" + postId;  // 댓글 추가 후 게시글 상세 페이지로 리다이렉트
    }





    @PostMapping("/post/{postId}/deleteComment/{commentId}")
    public String deleteComment(@PathVariable("postId") int postId, @PathVariable("commentId") int commentId, HttpSession session) {
        String userId = (String) session.getAttribute("userId");

        CommentDto commentDto = new CommentDto();
        commentDto.setPostId(postId);
        commentDto.setCommentId(commentId);
        commentDto.setUserId(userId);

        int result = commentService.deleteComment(commentDto);

        if (result > 0) {
            return "redirect:/post/" + postId;  // 삭제 후 게시글 상세 페이지로 리다이렉트
        } else {
            return "redirect:/post/" + postId + "?error=댓글 삭제 실패";  // 오류 메시지와 함께 리다이렉트
        }
    }




    // 게시글 수정 페이지
    @GetMapping("/post/edit/{postId}")
    public String editPostPage(@PathVariable("postId") int postId, Model model, HttpSession session) {
        PostDto postDto = postService.findPost(postId);
        String loggedInUserId = (String) session.getAttribute("userId");

        if (loggedInUserId != null && loggedInUserId.equals(postDto.getUserId())) {
            model.addAttribute("post", postDto);
            return "post/postEditForm";  // 수정 폼으로 이동
        }

        return "redirect:/post/post";  // 목록 페이지로 리다이렉트
    }

    // 게시글 수정 처리
    @PostMapping("/post/edit/{postId}")
    public String updatePost(@PathVariable("postId") int postId, PostDto postDto, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        postDto.setPostId(postId);
        postDto.setUserId(userId);

        postService.updatePost(postDto);
        return "redirect:/post/post";  // 수정된 게시글 목록 페이지로 리다이렉트
    }

    // 게시글 삭제 처리
    @PostMapping("/post/delete/{postId}")
    public String deletePost(@PathVariable("postId") int postId, HttpSession session) {
        String userId = (String) session.getAttribute("userId");

        PostDto postDto = new PostDto();
        postDto.setPostId(postId);
        postDto.setUserId(userId);

        int result = postService.deletePost(postDto);

        if (result > 0) {
            return "redirect:/post/post";  // 삭제 후 게시글 목록 페이지로 리다이렉트
        } else {
            return "redirect:/post/post?error=삭제 실패";  // 목록 페이지로 리다이렉트하고 에러 메시지 추가
        }
    }

    // 좋아요 추가
    @PostMapping("/post/like/{postId}")
    public String insertLike(@PathVariable("postId") int postId, HttpSession session) {
        String userId = (String) session.getAttribute("userId");

        LikeDto likeDto = new LikeDto();
        likeDto.setUserId(userId);
        likeDto.setPostId(postId);

        likeService.insertLike(likeDto); // 좋아요 추가

        return "redirect:/post/{postId}";  // 게시글 상세 페이지로 리다이렉트
    }

    // 좋아요 취소
    @PostMapping("/post/unlike/{postId}")
    public String deleteLike(@PathVariable("postId") int postId, HttpSession session) {
        String userId = (String) session.getAttribute("userId");

        LikeDto likeDto = new LikeDto();
        likeDto.setUserId(userId);
        likeDto.setPostId(postId);

        likeService.deleteLike(likeDto); // 좋아요 취소

        return "redirect:/post/{postId}";  // 게시글 상세 페이지로 리다이렉트
    }
}
