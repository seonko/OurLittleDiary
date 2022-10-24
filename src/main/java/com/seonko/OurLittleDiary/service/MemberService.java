package com.seonko.OurLittleDiary.service;

import com.seonko.OurLittleDiary.domain.Member;
import com.seonko.OurLittleDiary.dto.MemberDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface MemberService{
    Long save(MemberDTO memberDTO);
    List<Member> memberSearch(String keyword);
}
