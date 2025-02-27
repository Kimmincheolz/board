package com.mc.board.service;

import com.mc.board.dto.PostDto;
import com.mc.board.mapper.PostMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostMapper postMapper;

    public List<PostDto> postList(){
        return postMapper.postList();
    }
    public int insertPost(PostDto postDto){
        return postMapper.insertPost(postDto);
    }
    public PostDto findPost(int postId){
        return postMapper.findPost(postId);
    }

    public void viewUpdate(int postId){
        postMapper.viewUpdate(postId);
    }

    public int updatePost(PostDto postDto){
        return postMapper.updatePost(postDto);
    }

    public int deletePost(PostDto postDto){
        return postMapper.deletePost(postDto);
    }

}
