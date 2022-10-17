package com.seonko.OurLittleDiary.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "diary_member")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DiaryMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JoinColumn(name = "diary_id", nullable = false)
    @ManyToOne
    private Diary diary;

    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne
    private Member member;

    @Column(name = "role", nullable = false)
    private String role;

    @Builder
    public DiaryMember(Diary diary, Member member) {
        this.diary = diary;
        this.member = member;
    }

}
