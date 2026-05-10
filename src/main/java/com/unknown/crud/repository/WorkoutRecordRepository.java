package com.unknown.crud.repository;

import com.unknown.crud.entity.WorkoutRecord;
import org.springframework.data.jpa.repository.JpaRepository;

//Sql 쉽게 쓰려고 interface로만 만듬, Class JpaRepository만 상속받으면 Spring boot가 실제 클래스와 객체 생성

//<받을 record 객체, record 관리를 위한 PK>
public interface WorkoutRecordRepository extends JpaRepository<WorkoutRecord, Long> {
    WorkoutRecord findBy(Long id);
}
