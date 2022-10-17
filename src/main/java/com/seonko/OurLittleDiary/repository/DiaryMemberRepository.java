package com.seonko.OurLittleDiary.repository;

import com.seonko.OurLittleDiary.domain.DiaryMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryMemberRepository extends JpaRepository<DiaryMember, Long> {

}