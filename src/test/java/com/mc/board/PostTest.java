package com.mc.board;

import com.mc.board.dto.PostDto;
import com.mc.board.mapper.PostMapper;
import com.mc.board.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;

import java.util.List;

@SpringBootTest
public class PostTest {
    @Autowired
    PostService postService;
    PostMapper postMapper;


    @Test
    @DisplayName("게시글 목록 테스트")
    public void list(){
        List<PostDto> list=  postService.postList();

        System.out.println(list);
    }
    @Test
    @DisplayName("게시글 등록 테스트")
    public void insert(){
        PostDto postDto = new PostDto();
        postDto.setUserId("test2");
        postDto.setTitle("등록  테스트");
        postDto.setContent("등록 테스트중 입니다");

        int result =  postService.insertPost(postDto);
        System.out.println(result);
    }
    @Test
    @DisplayName("게시글 확인하기")
    public void find(){
        int postId = 15;

        PostDto postDto = postService.findPost(postId);

        System.out.println(postDto);

    }

}
