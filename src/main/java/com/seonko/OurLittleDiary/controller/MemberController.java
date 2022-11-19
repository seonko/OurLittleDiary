package com.seonko.OurLittleDiary.controller;

import com.seonko.OurLittleDiary.dto.MemberDTO;
import com.seonko.OurLittleDiary.service.MailService;
import com.seonko.OurLittleDiary.service.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;
    private final MailService mailService;

    // 회원가입
    @PostMapping("/api/signUp")
    public Long insertMember(@RequestBody MemberDTO memberDTO) throws Exception {
        return memberService.save(memberDTO);
    }

    // 아이디 중복 체크
    @GetMapping("/api/checkEmailDuplicate/{email}")
    public Boolean checkEmailDuplicate(@PathVariable String email) {
        return memberService.checkEmailDuplicate(email);
    }

    // 이메일 전송
    @GetMapping("/api/sendEmail/{email}")
    public String sendEmail(@PathVariable String email) throws Exception{
        return mailService.sendMessage(email);
    }

    // 닉네임 중복 체크
    @GetMapping("/api/checkNicknameDuplicate/{nickname}")
    public Boolean checkNicknameDuplicate(@PathVariable String nickname) throws Exception {
        return memberService.checkNicknameDuplicate(nickname);
    }
}
