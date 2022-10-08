package com.seonko.OurLittleDiary.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReplyDTO {

    private Long id;
    private Long postId;
    private String content;
    private String writer;
    private LocalDateTime contentCreateDate;

}
