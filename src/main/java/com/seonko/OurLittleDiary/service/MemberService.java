package com.seonko.OurLittleDiary.service;

import com.seonko.OurLittleDiary.domain.Member;
import com.seonko.OurLittleDiary.dto.MemberDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface MemberService{
    Long save(MemberDTO memberDTO);
    List<String> memberNicknameSearch(String keyword);
    Member getMemberByNickname(String nickname);
    Boolean checkEmailDuplicate(String email);
}
