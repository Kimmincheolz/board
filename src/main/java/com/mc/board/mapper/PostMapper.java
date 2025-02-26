package com.mc.board.mapper;

import com.mc.board.dto.PostDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {

    List<PostDto>postList();

    int insertPost(PostDto postDto);

    PostDto findPost(int postId);

    int viewUpdate(@Param("postId") int postId);

    int updatePost(PostDto postDto);


}
