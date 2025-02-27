package com.mc.board.service;

import com.mc.board.dto.CommentDto;
import com.mc.board.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    public List<CommentDto> commentList(int postId){
        return commentMapper.commentList(postId);
    }

    public int insertComment(CommentDto commentDto){
        return commentMapper.insertComment(commentDto);
    }



    public int deleteComment(CommentDto commentDto){
        return commentMapper.deleteComment(commentDto);
    }




}
