package com.seonko.OurLittleDiary.repository;

import com.seonko.OurLittleDiary.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);
    // 다이어리 참여멤버 검색
    List<Member> findByNicknameContaining(String keyword);
    Optional<Member> findById(Long id);
}
