package com.mc.board.service;

import com.mc.board.dto.LikeDto;
import com.mc.board.mapper.LikeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    @Autowired
    LikeMapper likeMapper;

    public int insertLike(LikeDto likeDto){
        return likeMapper.insertLike(likeDto);
    }
    public int deleteLike(LikeDto likeDto){
        return likeMapper.deleteLike(likeDto);
    }
    public boolean isLikedByUser(int postId, String userId) {
        return likeMapper.isLikedByUser(postId, userId) > 0;
    }
}
