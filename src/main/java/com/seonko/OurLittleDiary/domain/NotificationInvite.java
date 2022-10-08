package com.seonko.OurLittleDiary.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "notification_invite")
public class NotificationInvite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne
    private Member member;

    @JoinColumn(name = "diary_id", nullable = false)
    @ManyToOne
    private Diary diary;

}
