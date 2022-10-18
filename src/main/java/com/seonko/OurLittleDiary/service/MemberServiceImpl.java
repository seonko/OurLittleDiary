package com.seonko.OurLittleDiary.service;

import com.seonko.OurLittleDiary.domain.Member;
import com.seonko.OurLittleDiary.dto.MemberDTO;
import com.seonko.OurLittleDiary.repository.MemberRepository;
import com.seonko.OurLittleDiary.type.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username).orElse(null);

        if (member == null) {
            throw new UsernameNotFoundException(username);
        }

        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getAuthority().toString())
                .build();
    }

    @Transactional
    @Override
    public Long save(MemberDTO memberDTO) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return memberRepository.save(Member.builder()
                .email(memberDTO.getEmail())
                .password(passwordEncoder.encode(memberDTO.getPassword()))
                .nickname(memberDTO.getNickname())
                .authority(Authority.USER)
                .searchable(Boolean.FALSE)
                .build()).getId();
    }

    // 다이어리 참여멤버 검색
    @Override
    public List<Member> memberSearch(String keyword) {
        return memberRepository.findByNicknameContaining(keyword);
    }
}
