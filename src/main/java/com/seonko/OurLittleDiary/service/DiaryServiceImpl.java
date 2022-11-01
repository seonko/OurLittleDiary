package com.seonko.OurLittleDiary.service;

import com.seonko.OurLittleDiary.config.auth.PrincipalDetails;
import com.seonko.OurLittleDiary.domain.Diary;
import com.seonko.OurLittleDiary.domain.DiaryMember;
import com.seonko.OurLittleDiary.domain.Member;
import com.seonko.OurLittleDiary.domain.Post;
import com.seonko.OurLittleDiary.dto.CreatePostDTO;
import com.seonko.OurLittleDiary.dto.DiaryDTO;
import com.seonko.OurLittleDiary.dto.DiaryMemberDTO;
import com.seonko.OurLittleDiary.dto.PostDTO;
import com.seonko.OurLittleDiary.repository.DiaryMemberRepository;
import com.seonko.OurLittleDiary.repository.DiaryRepository;
import com.seonko.OurLittleDiary.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.util.List;

@RequiredArgsConstructor
@Service
public class DiaryServiceImpl implements DiaryService {

    @Value("${spring.servlet.multipart.location}")
    private String uploadPath;
    private final DiaryRepository diaryRepository;
    private final DiaryMemberRepository diaryMemberRepository;
    private final PostRepository postRepository;

    // 다이어리 생성
    @Override
    @Transactional
    public Diary createDiary(DiaryDTO diaryDTO) throws Exception {
        return diaryRepository.save(diaryDTO.toEntity());
    }

    // 썸네일 저장
    @Override
    public void saveThumbnail(Diary diary, MultipartFile mFile) {
        String thumbnail = diary.getId().toString();
        try {
            File file = new File(uploadPath + "diary/" + thumbnail);
            file.delete();
            mFile.transferTo(new File(uploadPath + "diary/" + thumbnail +".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    // 다이어리 멤버 리스트
    @Override
    public List<DiaryMember> diaryMemberList(Long memberId) throws Exception {
        return diaryMemberRepository.findByMemberId(memberId);
    }

    // 다이어리 글 작성
    @Override
    public Post createPost(PrincipalDetails principalDetails, CreatePostDTO createPostDTO) throws Exception {
        PostDTO postDTO = new PostDTO();
        postDTO.setDiary(diaryRepository.findById(createPostDTO.getDiaryId()).orElseThrow());
        postDTO.setMember(principalDetails.getMember());
        postDTO.setTitle(createPostDTO.getTitle());
        postDTO.setContent(createPostDTO.getContent());
        postDTO.setReplyCount(0);
        return postRepository.save(postDTO.toEntity());
    }

}
