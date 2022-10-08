package com.seonko.OurLittleDiary.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDTO {

    private Long id;
    private Long diaryId;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime contentCreateDate;
    private Long replyCount;

}
