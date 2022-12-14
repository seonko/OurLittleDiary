package com.seonko.OurLittleDiary.dto;

import com.seonko.OurLittleDiary.domain.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberDTO {

    private Long id;
    private String email;
    private String password;
    private String nickname;
    private String authority;
    private String providerId;
    private LocalDate createDate;
    private Boolean searchable;

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .nickname(nickname)
                .build();
    }
}
