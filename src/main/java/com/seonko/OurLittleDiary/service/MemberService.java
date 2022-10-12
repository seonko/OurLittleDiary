package com.seonko.OurLittleDiary.service;

import com.seonko.OurLittleDiary.dto.MemberDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {
    Long save(MemberDTO memberDTO);
}
