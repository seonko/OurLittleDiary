package com.seonko.OurLittleDiary.service;

import com.seonko.OurLittleDiary.config.auth.PrincipalDetails;
import com.seonko.OurLittleDiary.domain.Diary;
import com.seonko.OurLittleDiary.domain.Member;
import com.seonko.OurLittleDiary.domain.Post;
import com.seonko.OurLittleDiary.dto.CreatePostDTO;
import com.seonko.OurLittleDiary.dto.DiaryDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

public interface DiaryService {

    Diary createDiary(DiaryDTO diaryDTO) throws Exception;
    void saveThumbnail(Diary diary, MultipartFile mFile);
    Long diaryMemberSave(Diary diary, Member member) throws Exception;
    HashMap<String, Object> diaryList(Long memberId) throws Exception;
    void createPost(PrincipalDetails principalDetails, CreatePostDTO createPostDTO) throws Exception;
    List<Post> diaryPostList(Long diaryId, String targetDate) throws Exception;
    Post readPost(Long postId) throws Exception;
    void deletePost(Long postId) throws Exception;

}
