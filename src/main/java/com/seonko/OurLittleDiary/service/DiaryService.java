package com.seonko.OurLittleDiary.service;

import com.seonko.OurLittleDiary.domain.Diary;
import com.seonko.OurLittleDiary.domain.Member;
import com.seonko.OurLittleDiary.dto.DiaryDTO;
import org.springframework.web.multipart.MultipartFile;

public interface DiaryService {

    Diary createDiary(DiaryDTO diaryDTO) throws Exception;
    void saveThumbnail(Diary diary, MultipartFile mFile);
    Long diaryMemberSave(Diary diary, Member member) throws Exception;

}
