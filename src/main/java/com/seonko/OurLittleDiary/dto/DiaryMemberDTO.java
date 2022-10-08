package com.seonko.OurLittleDiary.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DiaryMemberDTO {

    private Long id;
    private Long diaryId;
    private Long memberId;
    private String role;

}
