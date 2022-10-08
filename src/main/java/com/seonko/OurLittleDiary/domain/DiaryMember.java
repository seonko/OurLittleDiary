package com.seonko.OurLittleDiary.domain;

import javax.persistence.*;

@Entity(name = "diary_member")
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

}
