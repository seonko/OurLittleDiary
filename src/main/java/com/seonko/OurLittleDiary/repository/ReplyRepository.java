package com.seonko.OurLittleDiary.repository;

import com.seonko.OurLittleDiary.domain.Post;
import com.seonko.OurLittleDiary.domain.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    List<Reply> findByPost(Post post);

}
