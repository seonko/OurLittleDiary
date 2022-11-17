package com.seonko.OurLittleDiary.dto;

import com.seonko.OurLittleDiary.domain.Diary;
import com.seonko.OurLittleDiary.domain.Member;
import com.seonko.OurLittleDiary.domain.Post;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDTO {

    private Long id;
    private Diary diary;
    private String title;
    private String content;
    private Member member;
    private LocalDateTime contentCreateDate;
    private int replyCount;

    public Post toEntity() {
        return Post.builder()
                .diary(diary)
                .title(title)
                .content(content)
                .member(member)
                .build();
    }

}
