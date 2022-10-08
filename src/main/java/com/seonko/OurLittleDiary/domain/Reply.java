package com.seonko.OurLittleDiary.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "reply")
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JoinColumn(name = "post_id", nullable = false)
    @ManyToOne
    private Post post;

    @Column(name = "content", nullable = false)
    private String content;

    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne
    private Member member;

    @Column(name = "content_create_date", nullable = false)
    private LocalDateTime contentCreateDate;

}
