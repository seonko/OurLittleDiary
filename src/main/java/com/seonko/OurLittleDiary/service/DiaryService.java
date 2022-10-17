package com.seonko.OurLittleDiary.service;

import com.seonko.OurLittleDiary.domain.Diary;
import com.seonko.OurLittleDiary.dto.DiaryDTO;
import com.seonko.OurLittleDiary.dto.DiaryMemberDTO;
import com.seonko.OurLittleDiary.dto.MemberDTO;

public interface DiaryService {

    Diary createDiary(DiaryDTO diaryDTO) throws Exception;
//    Long diaryMemberSave(DiaryMemberDTO diaryMemberDTO, DiaryDTO diaryDTO, MemberDTO memberDTO) throws Exception;

}
