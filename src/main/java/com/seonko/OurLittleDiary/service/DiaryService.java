package com.seonko.OurLittleDiary.service;

import com.seonko.OurLittleDiary.domain.Diary;
import com.seonko.OurLittleDiary.domain.Member;
import com.seonko.OurLittleDiary.dto.DiaryDTO;

public interface DiaryService {

    Diary createDiary(DiaryDTO diaryDTO) throws Exception;
    Long diaryMemberSave(Diary diary, Member member) throws Exception;

}
