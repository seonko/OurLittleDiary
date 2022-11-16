package com.seonko.OurLittleDiary.service;

import com.seonko.OurLittleDiary.config.auth.PrincipalDetails;
import com.seonko.OurLittleDiary.domain.Post;
import com.seonko.OurLittleDiary.domain.Reply;
import com.seonko.OurLittleDiary.dto.CreateReplyDTO;
import com.seonko.OurLittleDiary.repository.PostRepository;
import com.seonko.OurLittleDiary.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReplyServiceImpl implements ReplyService{

    private final PostRepository postRepository;
    private final ReplyRepository replyRepository;

    // Post Reply 리스트
    @Override
    public List<Reply> postReplyList(Long postId) throws Exception {
        Post post = postRepository.findById(postId).orElseThrow();
        return replyRepository.findByPost(post);
    }

    // Post Reply 작성 및 수정
    @Transactional
    @Override
    public void createReply(@AuthenticationPrincipal PrincipalDetails principalDetails, CreateReplyDTO createReplyDTO) throws Exception {
        replyRepository.save(Reply.builder()
                        .id(createReplyDTO.getReplyId())
                        .post(postRepository.findById(createReplyDTO.getPostId()).orElseThrow())
                        .content(createReplyDTO.getContent())
                        .member(principalDetails.getMember())
                        .contentCreateDate(createReplyDTO.getContentCreateDate())
                .build());
    }

    // Post Reply 삭제
    @Override
    public void deleteReply(Long replyId) throws Exception {
        replyRepository.deleteById(replyId);
    }

}
