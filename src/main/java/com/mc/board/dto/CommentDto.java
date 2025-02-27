package com.mc.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private int commentId;

    private int postId;

    private String userId;

    private String content;

    private LocalDateTime createdAt;
}
