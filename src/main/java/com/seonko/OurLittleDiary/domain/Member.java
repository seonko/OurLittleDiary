package com.seonko.OurLittleDiary.domain;

import com.seonko.OurLittleDiary.type.Authority;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

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
    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Column(name = "provider_id")
    private String providerId;

    @Column(name = "create_date", nullable = false)
    @CreatedDate
    private LocalDateTime createDate;

    @Column(name = "last_access_date", nullable = false)
    @CreatedDate
    private LocalDateTime lastAccessDate;

    @Column(name = "searchable", nullable = false)
    private Boolean searchable;

    @Builder
    public Member(String email, String password, String nickname, Authority authority, Boolean searchable) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.authority = authority;
        this.searchable = searchable;
    }
}
