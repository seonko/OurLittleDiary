package com.seonko.OurLittleDiary.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Builder
@Entity(name = "member")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Column(name = "profile_image")
    private String profileImage;

}
