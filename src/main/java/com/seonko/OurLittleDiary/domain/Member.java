package com.seonko.OurLittleDiary.domain;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nickname", nullable = false, unique = true)
    private String nickname;

    @Column(name = "authority", nullable = false)
    private String authority;

    @Column(name = "provider_id")
    private String providerId;

    @Column(name = "create_date", nullable = false)
    @CreatedDate
    private LocalDate createDate;

    @Column(name = "last_access_date", nullable = false)
    @CreatedDate
    private LocalDate lastAccessDate;

    @Column(name = "searchable", nullable = false)
    private Boolean searchable;

    @Builder
    public Member(String email, String password, String nickname, String authority, Boolean searchable) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.authority = authority;
        this.searchable = searchable;
    }

}
