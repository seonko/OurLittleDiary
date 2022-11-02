package com.seonko.OurLittleDiary.service;

import com.seonko.OurLittleDiary.config.auth.PrincipalDetails;
import com.seonko.OurLittleDiary.domain.Diary;
import com.seonko.OurLittleDiary.domain.DiaryMember;
import com.seonko.OurLittleDiary.domain.Member;
import com.seonko.OurLittleDiary.domain.Post;
import com.seonko.OurLittleDiary.dto.CreatePostDTO;
import com.seonko.OurLittleDiary.dto.DiaryDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DiaryService {

    Diary createDiary(DiaryDTO diaryDTO) throws Exception;
    void saveThumbnail(Diary diary, MultipartFile mFile);
    Long diaryMemberSave(Diary diary, Member member) throws Exception;
    List<DiaryMember> diaryMemberList(Long memberId) throws Exception;
    Post createPost(PrincipalDetails principalDetails, CreatePostDTO createPostDTO) throws Exception;
    List<Post> diaryPostList(Long diaryId, String targetDate) throws Exception;

}
