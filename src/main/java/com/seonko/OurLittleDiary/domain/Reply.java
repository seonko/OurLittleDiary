package com.seonko.OurLittleDiary.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity(name = "reply")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@DynamicUpdate
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JoinColumn(name = "post_id", nullable = false)
    @ManyToOne
    @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "id")
    private Post post;

    @Column(name = "content", nullable = false)
    private String content;

    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne
    private Member member;

    @Column(name = "content_create_date")
    private String contentCreateDate;

    @PrePersist
    public void onPrePersist() {
        this.contentCreateDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    @Builder
    public Reply(Long id, Post post, String content, Member member, String contentCreateDate) {
        this.id = id;
        this.post = post;
        this.content = content;
        this.member = member;
        this.contentCreateDate = contentCreateDate;
    }

}
