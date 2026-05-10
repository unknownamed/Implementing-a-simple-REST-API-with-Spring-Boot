package com.unknown.crud.dto;

//json으로 온 data -> Dto를 통해 받고 DB의 data 일부만 담아, 요청 외 정보 노출X

import com.unknown.crud.entity.WorkoutRecord;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

//inner class로 관리 static class로 만들어서 WorkoutDto를 생성하지 않아도 inner class 생성가능
public class WorkoutDto {

    //사용자 입력 -> DB 저장 Jackson이 java 기본 생성자로 JSON data를 @Setter로 만든 메서드로 값을 자동으로 넣음
    @Getter @Setter
    public static class CreateRequest {
        private LocalDate workoutDate;
        private String workoutType;

        private Double weight;
        private Integer sets;
        private Integer reps;

        private Double distanceKm;
        private Integer durationMinutes;
    }

    @Getter @Setter
    public static class UpdateRequest {
        private LocalDate workoutDate;
        private String workoutType;

        private Double weight;
        private Integer sets;
        private Integer reps;

        private Double distanceKm;
        private Integer durationMinutes;
    }

    //Entiy(DB) -> Dto
    @Getter
    public static class Response {
        private Long id;
        private LocalDate workoutDate;
        private String workoutType;

        private Double weight;
        private Integer sets;
        private Integer reps;

        private Double distanceKm;
        private Integer durationMinutes;

        //Entiy(DB) -> Dto 값을 넣을때 쓸때마다 다른 class에서 set하는것보다 생성자로 라인수를 줄일수있음
        public Response(WorkoutRecord entity) {
            this.id = entity.getId();
            this.workoutDate = entity.getWorkoutDate();
            this.workoutType = entity.getWorkoutType();

            this.weight = entity.getWeight();
            this.sets = entity.getSets();
            this.reps = entity.getReps();

            this.distanceKm = entity.getDistanceKm();
            this.durationMinutes = entity.getDurationMinutes();
        }
    }
}
