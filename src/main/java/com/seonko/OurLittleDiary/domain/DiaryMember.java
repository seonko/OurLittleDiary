package com.seonko.OurLittleDiary.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "diary_member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiaryMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "diary_id", nullable = false)
    private Long diaryId;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "role", nullable = false)
    private String role;

}
