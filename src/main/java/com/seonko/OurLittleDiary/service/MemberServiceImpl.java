package com.seonko.OurLittleDiary.service;

import com.seonko.OurLittleDiary.domain.Member;
import com.seonko.OurLittleDiary.dto.MemberDTO;
import com.seonko.OurLittleDiary.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Member> memberEntityWrapper = memberRepository.findByEmail(email);
        Member memberEntity = memberEntityWrapper.orElse(null);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new User(memberEntity.getEmail(), memberEntity.getPassword(), authorities);
    }

    @Transactional
    @Override
    public Long save(MemberDTO memberDTO) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return memberRepository.save(Member.builder()
                .email(memberDTO.getEmail())
                .password(passwordEncoder.encode(memberDTO.getPassword()))
                .nickname(memberDTO.getNickname())
                .authority("ROLE_USER")
                .searchable(memberDTO.getSearchable())
                .build()).getId();
    }

    // 다이어리 참여멤버 검색
    @Override
    public List<Member> memberSearch(String keyword) {
        return memberRepository.findByNicknameContaining(keyword);
    }
}
