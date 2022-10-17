package com.seonko.OurLittleDiary.controller;

import com.seonko.OurLittleDiary.config.GsonConfig;
import com.seonko.OurLittleDiary.domain.Diary;
import com.seonko.OurLittleDiary.domain.Member;
import com.seonko.OurLittleDiary.dto.DiaryDTO;
import com.seonko.OurLittleDiary.service.DiaryServiceImpl;
import com.seonko.OurLittleDiary.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

@RequiredArgsConstructor
@RestController
public class DiaryController {

    private final DiaryServiceImpl diaryService;
    private final MemberService memberService;

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;

    // 참여 멤버 검색
    @GetMapping("/api/diary/memberSearch")
    public List<Member> memberSearch(@RequestParam String keyword) throws Exception {
        return memberService.memberSearch(keyword);
    }

    // 다이어리 생성
    @PostMapping("/api/createDiary")
    public void createDiary(@RequestParam String diaryName, @RequestParam MultipartFile mFile, @RequestParam String addedMemberList) throws Exception {
        DiaryDTO diaryDTO = new DiaryDTO();
        diaryDTO.setDiaryName(diaryName);
        Diary diary = diaryService.createDiary(diaryDTO);

        List<Member> memberList = new GsonConfig().mapFromJsonArray(String.valueOf(addedMemberList), Member.class);
        for (Member member : memberList) {
            System.out.println(diary);
            System.out.println(member);
            diaryService.diaryMemberSave(diary, member);
        }

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
