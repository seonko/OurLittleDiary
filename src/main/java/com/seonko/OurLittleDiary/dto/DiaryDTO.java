package com.seonko.OurLittleDiary.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class DiaryDTO {

    private Long id;
    private String diaryName;
    private LocalDateTime diaryCreateDate;
    private String thumbnail;

}
