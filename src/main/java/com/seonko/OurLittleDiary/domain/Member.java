package com.seonko.OurLittleDiary.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "member")
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

    @Column(name = "provider_id", nullable = false)
    private String providerId;

    @Column(name = "create_date", nullable = false)
    private LocalDate createDate;

    @Column(name = "searchable", nullable = false)
    private Boolean searchable;

    @Column(name = "profile_image")
    private String profileImage;

}
