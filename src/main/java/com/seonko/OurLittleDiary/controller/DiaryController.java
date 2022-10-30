package com.seonko.OurLittleDiary.controller;

import com.seonko.OurLittleDiary.util.GsonUtil;
import com.seonko.OurLittleDiary.domain.Diary;
import com.seonko.OurLittleDiary.domain.DiaryMember;
import com.seonko.OurLittleDiary.domain.Member;
import com.seonko.OurLittleDiary.dto.DiaryDTO;
import com.seonko.OurLittleDiary.service.DiaryServiceImpl;
import com.seonko.OurLittleDiary.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RequiredArgsConstructor
@RestController
public class DiaryController {

    private final DiaryServiceImpl diaryService;
    private final MemberService memberService;

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
        diaryService.saveThumbnail(diary, mFile);

        List<Member> memberList = new GsonUtil().mapFromJsonArray(String.valueOf(addedMemberList), Member.class);
        for (Member member : memberList) {
            diaryService.diaryMemberSave(diary, member);
        }
    }

    // 다이어리 리스트
    @GetMapping("/api/diaryList")
    public List<Diary> diaryList(@RequestParam Long memberId) throws Exception {
        List<DiaryMember> diaryMemberList = diaryService.diaryMemberList(memberId);
        List<Diary> diaryList = new ArrayList<>();
        for (DiaryMember diaryMember : diaryMemberList) {
            diaryList.add(diaryMember.getDiary());
        }
        return diaryList;
    }

}
