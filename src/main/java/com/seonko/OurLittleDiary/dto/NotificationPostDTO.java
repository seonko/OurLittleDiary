package com.seonko.OurLittleDiary.dto;

import lombok.Data;

@Data
public class NotificationPostDTO {

    private Long id;
    private Long memberId;
    private Long postId;
    private String notiType;

}
