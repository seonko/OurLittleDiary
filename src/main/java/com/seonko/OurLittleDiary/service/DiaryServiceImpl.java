package com.seonko.OurLittleDiary.service;

import com.seonko.OurLittleDiary.config.auth.PrincipalDetails;
import com.seonko.OurLittleDiary.domain.Diary;
import com.seonko.OurLittleDiary.domain.DiaryMember;
import com.seonko.OurLittleDiary.domain.Member;
import com.seonko.OurLittleDiary.domain.Post;
import com.seonko.OurLittleDiary.dto.CreatePostDTO;
import com.seonko.OurLittleDiary.dto.DiaryDTO;
import com.seonko.OurLittleDiary.dto.DiaryMemberDTO;
import com.seonko.OurLittleDiary.repository.DiaryMemberRepository;
import com.seonko.OurLittleDiary.repository.DiaryRepository;
import com.seonko.OurLittleDiary.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    private final PostRepository postRepository;

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

    // 다이어리 글 작성 및 수정
    @Transactional
    @Override
    public void createPost(@AuthenticationPrincipal PrincipalDetails principalDetails, CreatePostDTO createPostDTO) throws Exception {
        postRepository.save(Post.builder()
                        .id(createPostDTO.getPostId())
                        .diary(diaryRepository.findById(createPostDTO.getDiaryId()).orElseThrow())
                        .member(principalDetails.getMember())
                        .title(createPostDTO.getTitle())
                        .content(createPostDTO.getContent())
                        .contentCreateDate(createPostDTO.getContentCreateDate())
                .build());
    }

    // 다이어리 Post 리스트
    @Override
    public List<Post> diaryPostList(Long diaryId, String targetDate) throws Exception {
        Diary diary = diaryRepository.findById(diaryId).orElseThrow();
        return postRepository.findByDiaryAndContentCreateDateContaining(diary, targetDate);
    }

    // 다이어리 글 보기
    @Override
    public Post readPost(Long postId) throws Exception {
        return postRepository.findById(postId).orElseThrow();
    }

    // 다이어리 글 삭제
    @Override
    public void deletePost(Long postId) throws Exception {
        postRepository.deleteById(postId);
    }

}
