package com.seonko.OurLittleDiary.controller;

import com.seonko.OurLittleDiary.dto.MemberDTO;
import com.seonko.OurLittleDiary.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

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
}
