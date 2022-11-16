package com.seonko.OurLittleDiary.service;

import com.seonko.OurLittleDiary.domain.Diary;
import com.seonko.OurLittleDiary.domain.Member;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

public interface DiaryService {

    Diary createDiary(String diaryName) throws Exception;
    void saveThumbnail(Diary diary, MultipartFile mFile);
    Long diaryMemberSave(Diary diary, Member member, String role) throws Exception;
    HashMap<String, Object> diaryList(Long memberId) throws Exception;

}
