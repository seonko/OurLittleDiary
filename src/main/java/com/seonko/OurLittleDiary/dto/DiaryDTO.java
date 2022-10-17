package com.seonko.OurLittleDiary.dto;

import com.seonko.OurLittleDiary.domain.Diary;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Data
public class DiaryDTO {

    private Long id;

    @NotEmpty(message = "다이어리 이름은 필수 입니다.")
    private String diaryName;

    private LocalDateTime diaryCreateDate;

    public Diary toEntity() {
        Diary build = Diary.builder()
                .diaryName(diaryName)
                .build();
        return build;
    }

//    @Builder DiaryDTO(Long id, String diaryName, LocalDateTime diaryCreateDate) {
//        this.id = id;
//        this.diaryName = diaryName;
//        this.diaryCreateDate = diaryCreateDate;
//    }

}
