package com.mc.board.controller;

import com.mc.board.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CommentController {
    @Autowired
    CommentService commentService;


}
