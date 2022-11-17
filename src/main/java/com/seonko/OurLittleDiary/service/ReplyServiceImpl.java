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
        return postRepository.findById(postId).get().getReplies();
    }

    // Post Reply 작성
    @Transactional
    @Override
    public void createReply(@AuthenticationPrincipal PrincipalDetails principalDetails, CreateReplyDTO createReplyDTO) throws Exception {
        Post post = postRepository.findById(createReplyDTO.getPostId()).orElseThrow();
        post.addReplyCount();
        postRepository.save(post);
        replyRepository.save(Reply.builder()
                        .id(createReplyDTO.getReplyId())
                        .post(post)
                        .content(createReplyDTO.getContent())
                        .member(principalDetails.getMember())
                        .contentCreateDate(createReplyDTO.getContentCreateDate())
                .build());
    }

    // Post Reply 수정
    @Override
    public void updateReply(@AuthenticationPrincipal PrincipalDetails principalDetails, CreateReplyDTO createReplyDTO) throws Exception {
        Post post = postRepository.findById(createReplyDTO.getPostId()).orElseThrow();
        replyRepository.save(Reply.builder()
                .id(createReplyDTO.getReplyId())
                .post(post)
                .content(createReplyDTO.getContent())
                .member(principalDetails.getMember())
                .contentCreateDate(createReplyDTO.getContentCreateDate())
                .build());
    }

    // Post Reply 삭제
    @Override
    public void deleteReply(Long replyId) throws Exception {
        Reply reply = replyRepository.findById(replyId).orElseThrow();
        Post post = postRepository.findById(reply.getPost().getId()).orElseThrow();
        post.deleteReplyCount();
        postRepository.save(post);
        replyRepository.deleteById(replyId);
    }

}
