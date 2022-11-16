package com.seonko.OurLittleDiary.service;

import com.seonko.OurLittleDiary.domain.Diary;
import com.seonko.OurLittleDiary.domain.DiaryMember;
import com.seonko.OurLittleDiary.domain.Member;
import com.seonko.OurLittleDiary.dto.DiaryDTO;
import com.seonko.OurLittleDiary.dto.DiaryMemberDTO;
import com.seonko.OurLittleDiary.repository.DiaryMemberRepository;
import com.seonko.OurLittleDiary.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DiaryServiceImpl implements DiaryService {

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;
    private final DiaryRepository diaryRepository;
    private final DiaryMemberRepository diaryMemberRepository;

    // 다이어리 생성
    @Override
    @Transactional
    public Diary createDiary(String diaryName) throws Exception {
        DiaryDTO diaryDTO = new DiaryDTO();
        diaryDTO.setDiaryName(diaryName);
        return diaryRepository.save(diaryDTO.toEntity());
    }

    // 썸네일 저장
    @Override
    public void saveThumbnail(Diary diary, MultipartFile mFile) {
        String thumbnail = diary.getId().toString();
        try {
            File file = new File(uploadPath + "images/diary/" + thumbnail);
            file.delete();
            mFile.transferTo(new File(uploadPath + "images/diary/" + thumbnail +".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 다이어리 멤버 추가
    @Override
    @Transactional
    public Long diaryMemberSave(Diary diary, Member member, String role) throws Exception {
        DiaryMemberDTO diaryMemberDTO = new DiaryMemberDTO();
        diaryMemberDTO.setDiary(diary);
        diaryMemberDTO.setMember(member);
        diaryMemberDTO.setRole(role);
        return diaryMemberRepository.save(diaryMemberDTO.toEntity()).getId();
    }

    // 다이어리 리스트
    @Override
    public HashMap<String, Object> diaryList(Long memberId) throws Exception {
        HashMap<String, Object> result = new HashMap<>();
        List<DiaryMember> diaryMemberList = diaryMemberRepository.findByMemberId(memberId);
        List<Diary> diaryList = new ArrayList<>();
        for (DiaryMember diaryMember : diaryMemberList) {
            diaryList.add(diaryMember.getDiary());
        }
        result.put("diaryList", diaryList);
        List<List> partMemberList = new ArrayList<>();
        for (Diary diary : diaryList) {
            partMemberList.add(diaryMemberRepository.findByDiaryId(diary.getId()));
        }
        result.put("partMemberList", partMemberList);
        return result;
    }

}
