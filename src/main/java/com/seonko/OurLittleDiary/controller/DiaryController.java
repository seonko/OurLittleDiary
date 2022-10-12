package com.seonko.OurLittleDiary.controller;

import com.seonko.OurLittleDiary.domain.Diary;
import com.seonko.OurLittleDiary.dto.DiaryDTO;
import com.seonko.OurLittleDiary.service.DiaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RequiredArgsConstructor
@RestController
public class DiaryController {

    private final DiaryService diaryService;

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    @PostMapping("/api/createDiary")
    public void createDiary(@RequestParam String diaryName, @RequestParam MultipartFile mFile) throws Exception {
        DiaryDTO diaryDTO = new DiaryDTO();
        diaryDTO.setDiaryName(diaryName);
        Diary diary = diaryService.createDiary(diaryDTO);
        String thumbnail = diary.getId().toString();
        try {
            File file = new File(uploadPath + "diary/" + thumbnail);
            file.delete();
            mFile.transferTo(new File(uploadPath + "diary/" + thumbnail +".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
