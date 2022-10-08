package com.seonko.OurLittleDiary.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JoinColumn(name = "diary_id", nullable = false)
    @ManyToOne
    private Diary diary;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne
    private Member member;

    @Column(name = "content_create_date", nullable = false)
    private LocalDateTime contentCreateDate;

    @Column(name = "reply_count", nullable = false)
    private Long replyCount;

}
