package com.mc.board.mapper;

import com.mc.board.dto.LikeDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LikeMapper {
    int insertLike(LikeDto likeDto);

    int deleteLike(LikeDto likeDto);

    int isLikedByUser(@Param("postId") int postId, @Param("userId") String userId);
}
