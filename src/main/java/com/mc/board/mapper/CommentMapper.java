package com.mc.board.mapper;

import com.mc.board.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentMapper {

    List<CommentDto>commentList(int postId);

    int insertComment(CommentDto commentDto);

    int deleteComment(CommentDto commentDto);


}
