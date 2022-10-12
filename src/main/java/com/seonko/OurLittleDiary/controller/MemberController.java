package com.seonko.OurLittleDiary.controller;

import com.seonko.OurLittleDiary.dto.MemberDTO;
import com.seonko.OurLittleDiary.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/api/singUp")
    public Long insertMamber(MemberDTO memberDto) throws Exception {
        return memberService.save(memberDto);
    }
}
