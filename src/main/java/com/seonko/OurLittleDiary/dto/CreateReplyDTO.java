package com.seonko.OurLittleDiary.dto;

import lombok.Data;

@Data
public class CreateReplyDTO {

    private Long replyId;
    private Long postId;
    private String content;
    private String contentCreateDate;

}
