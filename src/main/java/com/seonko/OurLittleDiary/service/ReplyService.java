package com.seonko.OurLittleDiary.service;

import com.seonko.OurLittleDiary.config.auth.PrincipalDetails;
import com.seonko.OurLittleDiary.domain.Reply;
import com.seonko.OurLittleDiary.dto.CreateReplyDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

public interface ReplyService {

    List<Reply> postReplyList(Long postId) throws Exception;
    void createReply(PrincipalDetails principalDetails, CreateReplyDTO createReplyDTO) throws Exception;
    void updateReply(@AuthenticationPrincipal PrincipalDetails principalDetails, CreateReplyDTO createReplyDTO) throws Exception;
    void deleteReply(Long replyId) throws Exception;

}
