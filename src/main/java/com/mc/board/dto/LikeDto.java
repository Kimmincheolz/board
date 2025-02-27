package com.mc.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeDto {

    private int likeId;

    private String userId;

    private int postId;

    private LocalDateTime createdAt;

}
