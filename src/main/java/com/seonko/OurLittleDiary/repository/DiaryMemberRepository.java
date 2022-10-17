package com.seonko.OurLittleDiary.repository;

import com.seonko.OurLittleDiary.domain.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiaryMemberRepository extends JpaRepository<Diary, Long> {

}
