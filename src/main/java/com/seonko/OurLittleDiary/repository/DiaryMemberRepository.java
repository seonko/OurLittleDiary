package com.seonko.OurLittleDiary.repository;

import com.seonko.OurLittleDiary.domain.DiaryMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiaryMemberRepository extends JpaRepository<DiaryMember, Long> {

    List<DiaryMember> findByMemberId(Long memberId);

    List<DiaryMember> findByDiaryId(Long diaryId);

}
