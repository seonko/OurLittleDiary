package com.seonko.OurLittleDiary.domain;

import javax.persistence.*;

@Entity(name = "notification_post")
public class NotificationPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne
    private Member member;

    @JoinColumn(name = "post_id", nullable = false)
    @ManyToOne
    private Post post;

    @Column(name = "noti_type", nullable = false)
    private String notiType;

}
