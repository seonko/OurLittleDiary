package com.seonko.OurLittleDiary.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class MemberDTO {

    private Long id;
    private String email;
    private String password;
    private String nickname;
    private String authority;
    private String providerId;
    private LocalDate createDate;
    private Boolean searchable;
    private String profileImage;

}
