package com.seonko.OurLittleDiary.repository;

import com.seonko.OurLittleDiary.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
