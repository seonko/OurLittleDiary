package com.seonko.OurLittleDiary.dto;

import com.seonko.OurLittleDiary.domain.Diary;
import com.seonko.OurLittleDiary.domain.DiaryMember;
import com.seonko.OurLittleDiary.domain.Member;
import lombok.Data;

@Data
public class DiaryMemberDTO {

    private Long id;
    private Diary diary;
    private Member member;
    private String role;

    public DiaryMember toEntity() {
        return DiaryMember.builder()
                .diary(diary)
                .member(member)
                .role(role)
                .build();
    }

}
