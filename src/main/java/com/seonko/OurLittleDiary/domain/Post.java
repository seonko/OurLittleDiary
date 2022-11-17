package com.seonko.OurLittleDiary.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "post")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
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

    @Column(name = "content_create_date")
    @CreatedDate
    private String contentCreateDate;

    @Column(name = "reply_count")
    private int replyCount;

    @OneToMany(mappedBy = "post", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<Reply> replies = new ArrayList<>();

    @PrePersist
    public void onPrePersist() {
        this.contentCreateDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Builder
    public Post(Long id, Diary diary, String title, String content, Member member, int replyCount, String contentCreateDate) {
        this.id = id;
        this.diary = diary;
        this.title = title;
        this.content = content;
        this.member = member;
        this.replyCount = replyCount;
        this.contentCreateDate = contentCreateDate;
    }

}
