package com.seonko.OurLittleDiary.service;

import com.seonko.OurLittleDiary.domain.Member;
import com.seonko.OurLittleDiary.dto.MemberDTO;
import com.seonko.OurLittleDiary.repository.MemberRepository;
import com.seonko.OurLittleDiary.type.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    @Override
    public Long save(MemberDTO memberDTO) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return memberRepository.save(Member.builder()
                .email(memberDTO.getEmail())
                .password(passwordEncoder.encode(memberDTO.getPassword()))
                .nickname(memberDTO.getNickname())
                .authority(Authority.USER)
                .searchable(memberDTO.getSearchable())
                .build()).getId();
    }

    // 다이어리 참여멤버 검색
    @Override
    public List<String> memberNicknameSearch(String keyword) {
        List<Member> memberList = memberRepository.findByNicknameContainingAndSearchableIsTrue(keyword);
        List<String> nicknameList = new ArrayList<>();
        for (Member member : memberList) {
            nicknameList.add(member.getNickname());
        }
        return nicknameList;
    }

    // nickname으로 멤버 찾기
    @Override
    public Member getMemberByNickname(String nickname) {
        return memberRepository.findByNickname(nickname);
    }
}
