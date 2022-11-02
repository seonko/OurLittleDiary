package com.seonko.OurLittleDiary.repository;

import com.seonko.OurLittleDiary.domain.Diary;
import com.seonko.OurLittleDiary.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByDiaryAndContentCreateDateContaining(Diary diary, String targetDate);

}
