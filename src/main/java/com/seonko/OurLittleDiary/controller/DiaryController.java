package com.seonko.OurLittleDiary.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.seonko.OurLittleDiary.domain.Diary;
import com.seonko.OurLittleDiary.domain.Member;
import com.seonko.OurLittleDiary.dto.DiaryDTO;
import com.seonko.OurLittleDiary.dto.MemberDTO;
import com.seonko.OurLittleDiary.service.DiaryService;
import com.seonko.OurLittleDiary.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class DiaryController {

    private final DiaryService diaryService;
    private final MemberService memberService;

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    // 참여 멤버 검색
    @GetMapping("/api/diary/memberSearch")
    public List<Member> memberSearch(@RequestParam String keyword) throws Exception {
        System.out.println(keyword);
        return memberService.memberSearch(keyword);
    }

    // 다이어리 생성
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
