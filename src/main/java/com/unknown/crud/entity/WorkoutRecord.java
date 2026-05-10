package com.unknown.crud.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

//DB를 Java코드로 불러옴

@Entity//JPA의 자동화를 위해 필요 DB데이터와 실제 연동됨(CRUD작업 동시 발생)
@Getter// 데이터를 받아오는 접근자
@NoArgsConstructor(access = AccessLevel.PROTECTED)// 생성자를 자동 호출, 개발자가 짠 코드가 접근 못하고 JPA만 접근가능해서 개발자의 실수(인자 없는 자바 기본 생성자를 사용하는 것)막음
public class WorkoutRecord {
    //DB DDL문법이랑 똑같음

    @Id//DB PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)//레코드 저장시 id 번호 순차적 1씩
    private  Long id;//PK

    //날짜 필수 입력
    @Column(nullable =false)//DB NOT NULL 속성
    private LocalDate workoutDate;

    //운동 종류 필수 입력
    @Column(nullable =false)
    private String workoutType;

    //필수 입력X - 헬스 - NULL 가능
    private Double weight;
    private Integer sets;
    private Integer reps;

    //필수 입력X - 달리기
    private Double distanceKm;
    private Integer durationMinutes;

    //입력을 위한 생성자
    @Builder
    public WorkoutRecord(LocalDate workoutDate, String workoutType, Double weight, Integer sets, Integer reps, Double distanceKm, Integer durationMinutes) {
        this.workoutDate = workoutDate;
        this.workoutType = workoutType;
        this.weight = weight;
        this.sets = sets;
        this.reps = reps;
        this.distanceKm = distanceKm;
        this.durationMinutes = durationMinutes;
    }

    public void update(LocalDate workoutDate, String workoutType, Double weight, Integer sets, Integer reps, Double distanceKm, Integer durationMinutes) {
        this.workoutDate = workoutDate;
        this.workoutType = workoutType;
        this.weight = weight;
        this.sets = sets;
        this.reps = reps;
        this.distanceKm = distanceKm;
        this.durationMinutes = durationMinutes;
    }
}
