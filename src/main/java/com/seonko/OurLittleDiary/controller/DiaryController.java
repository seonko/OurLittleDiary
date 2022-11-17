package com.seonko.OurLittleDiary.controller;

import com.seonko.OurLittleDiary.config.auth.PrincipalDetails;
import com.seonko.OurLittleDiary.domain.Post;
import com.seonko.OurLittleDiary.domain.Reply;
import com.seonko.OurLittleDiary.dto.CreatePostDTO;
import com.seonko.OurLittleDiary.dto.CreateReplyDTO;
import com.seonko.OurLittleDiary.service.PostService;
import com.seonko.OurLittleDiary.service.ReplyService;
import com.seonko.OurLittleDiary.util.GsonUtil;
import com.seonko.OurLittleDiary.domain.Diary;
import com.seonko.OurLittleDiary.domain.Member;
import com.seonko.OurLittleDiary.service.DiaryServiceImpl;
import com.seonko.OurLittleDiary.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RequiredArgsConstructor
@RestController
public class DiaryController {

    private final DiaryServiceImpl diaryService;
    private final MemberService memberService;
    private final PostService postService;
    private final ReplyService replyService;

    // 참여 멤버 검색
    @GetMapping("/api/diary/memberSearch")
    public List<String> memberSearch(@RequestParam String keyword) throws Exception {
        return memberService.memberNicknameSearch(keyword);
    }

    // 다이어리 생성
    @PostMapping("/api/createDiary")
    public void createDiary(@AuthenticationPrincipal PrincipalDetails principalDetails ,@RequestParam String diaryName, @RequestParam MultipartFile mFile, @RequestParam String addedMemberList) throws Exception {
        Diary diary = diaryService.createDiary(diaryName);
        diaryService.saveThumbnail(diary, mFile);

        List<String> nicknameList = new GsonUtil().mapFromJsonArray(String.valueOf(addedMemberList), String.class);
        for (String nickname : nicknameList) {
            String role;
            Member member = memberService.getMemberByNickname(nickname);
            if (principalDetails.getMember().getId() == member.getId()) {
                role = "OWNER";
            } else {
                role = "MEMBER";
            }
            diaryService.diaryMemberSave(diary, member, role);
        }
    }

    // 다이어리 리스트
    @GetMapping("/api/diaryList")
    public HashMap<String, Object> diaryList(@RequestParam Long memberId) throws Exception {
        return diaryService.diaryList(memberId);
    }

    // 다이어리 글 작성 및 수정
    @PostMapping("/api/post/write")
    public void createPost(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody CreatePostDTO createPostDTO) throws Exception {
        postService.createPost(principalDetails, createPostDTO);
    }

    // 다이어리 Post 리스트
    @GetMapping("/api/postList")
    public List<Post> postList(@RequestParam Long diaryId, @RequestParam String postDay) throws Exception {
        return postService.diaryPostList(diaryId, postDay);
    }

    // 다이어리 글 보기
    @GetMapping("/api/getPost")
    public Post getPost(@RequestParam Long postId) throws Exception {
        return postService.readPost(postId);
    }

    // 다이어리 글 삭제
    @DeleteMapping("/api/deletePost/{postId}")
    public void deletePost(@PathVariable Long postId) throws Exception {
        postService.deletePost(postId);
    }

    // Post 댓글 리스트
    @GetMapping("/api/replyList")
    public List<Reply> replyList(@RequestParam Long postId) throws Exception {
        return replyService.postReplyList(postId);
    }

    // Post 댓글 작성
    @PostMapping("/api/reply/write")
    public void createReply(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody CreateReplyDTO createReplyDTO) throws Exception {
        replyService.createReply(principalDetails, createReplyDTO);
    }

    // Post 댓글 수정
    @PutMapping("/api/reply/update")
    public void updateReply(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody CreateReplyDTO createReplyDTO) throws Exception {
        replyService.updateReply(principalDetails, createReplyDTO);
    }

    // Post 댓글 삭제
    @DeleteMapping("/api/reply/delete")
    public void deleteReply(@RequestParam Long replyId) throws Exception {
        replyService.deleteReply(replyId);
    }

}
