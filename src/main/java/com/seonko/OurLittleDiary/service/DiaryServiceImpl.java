package com.seonko.OurLittleDiary.service;

import com.seonko.OurLittleDiary.domain.Diary;
import com.seonko.OurLittleDiary.domain.Member;
import com.seonko.OurLittleDiary.dto.DiaryDTO;
import com.seonko.OurLittleDiary.dto.DiaryMemberDTO;
import com.seonko.OurLittleDiary.repository.DiaryMemberRepository;
import com.seonko.OurLittleDiary.repository.DiaryRepository;
import com.seonko.OurLittleDiary.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class DiaryServiceImpl implements DiaryService {

    private final DiaryRepository diaryRepository;
    private final MemberRepository memberRepository;
    private final DiaryMemberRepository diaryMemberRepository;

    // 다이어리 생성
    @Override
    @Transactional
    public Diary createDiary(DiaryDTO diaryDTO) throws Exception {
        return diaryRepository.save(diaryDTO.toEntity());
    }

    // 다이어리 멤버 추가
    @Override
    @Transactional
    public Long diaryMemberSave(Diary diary, Member member) throws Exception {
        DiaryMemberDTO diaryMemberDTO = new DiaryMemberDTO();
        diaryMemberDTO.setDiary(diary);
        diaryMemberDTO.setMember(member);
        return diaryMemberRepository.save(diaryMemberDTO.toEntity()).getId();
    }

}
