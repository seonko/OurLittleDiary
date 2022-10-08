package com.seonko.OurLittleDiary.dto;

import lombok.Data;

@Data
public class NotificationInviteDTO {

    private Long id;
    private Long diaryId;
    private Long memberId;

}
