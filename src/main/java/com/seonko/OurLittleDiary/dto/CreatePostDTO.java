package com.seonko.OurLittleDiary.dto;

import lombok.Data;

@Data
public class CreatePostDTO {

    private Long postId;
    private Long diaryId;
    private String title;
    private String content;
    private String contentCreateDate;

}
