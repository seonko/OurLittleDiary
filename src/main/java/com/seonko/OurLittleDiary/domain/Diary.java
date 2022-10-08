package com.seonko.OurLittleDiary.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "diary")
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "diary_name", nullable = false)
    private String diaryName;

    @Column(name = "diary_create_date", nullable = false)
    private LocalDateTime diaryCreateDate;

    @Column(name = "thumbnail")
    private String thumbnail;

}
