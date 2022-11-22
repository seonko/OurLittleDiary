package com.seonko.OurLittleDiary.service;

import com.seonko.OurLittleDiary.config.auth.PrincipalDetails;
import com.seonko.OurLittleDiary.domain.Diary;
import com.seonko.OurLittleDiary.domain.Post;
import com.seonko.OurLittleDiary.dto.CreatePostDTO;
import com.seonko.OurLittleDiary.repository.DiaryRepository;
import com.seonko.OurLittleDiary.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final DiaryRepository diaryRepository;
    private final PostRepository postRepository;

    // 다이어리 글 작성
    @Transactional
    @Override
    public void createPost(@AuthenticationPrincipal PrincipalDetails principalDetails, CreatePostDTO createPostDTO) throws Exception {
        postRepository.save(Post.builder()
                        .diary(diaryRepository.findById(createPostDTO.getDiaryId()).orElseThrow())
                        .member(principalDetails.getMember())
                        .title(createPostDTO.getTitle())
                        .content(createPostDTO.getContent())
                        .datetime(createPostDTO.getDatetime())
                .build());
    }

    // 다이어리 글 수정
    @Override
    public void updatePost(@AuthenticationPrincipal PrincipalDetails principalDetails, CreatePostDTO createPostDTO) throws Exception {
        postRepository.save(Post.builder()
                        .id(createPostDTO.getPostId())
                        .diary(diaryRepository.findById(createPostDTO.getDiaryId()).orElseThrow())
                        .member(principalDetails.getMember())
                        .title(createPostDTO.getTitle())
                        .content(createPostDTO.getContent())
                        .contentCreateDate(createPostDTO.getContentCreateDate())
                        .datetime(createPostDTO.getDatetime())
                        .replyCount(createPostDTO.getReplyCount())
                .build());
    }

    // 다이어리 Post 리스트
    @Override
    public List<Post> diaryPostList(Long diaryId, String targetDate) throws Exception {
        Diary diary = diaryRepository.findById(diaryId).orElseThrow();
        return postRepository.findByDiaryAndDatetime(diary, targetDate);
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
