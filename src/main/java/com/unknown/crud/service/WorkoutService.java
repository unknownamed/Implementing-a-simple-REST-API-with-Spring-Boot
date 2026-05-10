package com.unknown.crud.service;

import com.unknown.crud.dto.WorkoutDto;
import com.unknown.crud.entity.WorkoutRecord;
import com.unknown.crud.repository.WorkoutRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    //sql용 객체
    private final WorkoutRecordRepository workoutRecordRepository;

    //Create - 입력 데이터 저장
    //@Service가 사용하는 것은 Entity자체가 아니라 dto라 반환타입이 Response임
    @Transactional
    public WorkoutDto.Response createWorkout(WorkoutDto.CreateRequest request) {

        //record 사용자가 직접 넣은 data -> ID값 존재X
        //빌드 패턴 - Entity에 만들어 놓은 @builder이 붙은 생성자를 사용해서 앞에 .workoutDate라고 이름표를 달아서  직관성, 순서X, 안쓴값 자동 NULL
        WorkoutRecord record= WorkoutRecord.builder()
                .workoutDate(request.getWorkoutDate())
                .workoutType(request.getWorkoutType())
                .weight(request.getWeight())
                .sets(request.getSets())
                .reps(request.getReps())
                .distanceKm(request.getDistanceKm())
                .durationMinutes(request.getDurationMinutes())
                .build();//마지막 표시

        //DB 저장 -> ID값이 채워진 객체 반환
        WorkoutRecord savedRecord = workoutRecordRepository. save(record);

        //프론트 엔드를 위한 객체반환 - 방금 저장한것의 ID를 알려줘야 해당 객체를 구별해서 프론트엔드가 요청 가능
        //REST API의 표준 반환법 - POST 성공시 상태 코드 201 + body에 객체 전체
        return new WorkoutDto.Response(savedRecord);
    }

    //Update - 저장 데이터 수정
    @Transactional
    public WorkoutDto.Response updateWorkout(Long id, WorkoutDto.UpdateRequest request) {
        WorkoutRecord record = workoutRecordRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 기록 없음. ID: " + id));//에러 이름표 + 설명문

        record.update(
                request.getWorkoutDate(),
                request.getWorkoutType(),
                request.getWeight(),
                request.getSets(),
                request.getReps(),
                request.getDistanceKm(),
                request.getDurationMinutes()
        );
        //Entity 객체의 값이 바뀌면 JPA의 durity checking으로 Update 쿼리 실행

        return new WorkoutDto.Response(record);
    }

    //Delete - 전체 데이터 삭제
    @Transactional
    public void deleteAllWorkout() {
        workoutRecordRepository.deleteAllInBatch();//deleteAll의 경우 record 개수만큼 쿼리문이 필요해서 사용X -> deleteAllInBatch = 테이블 전체 삭제
    }

    //Delete - 단일 데이터 삭제
    @Transactional
    public void deleteWorkout(Long id) {
        WorkoutRecord record = workoutRecordRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 기록 없음. ID: " + id));

        workoutRecordRepository.delete(record);
    }

    //Read - 전체 저장 조회
    @Transactional(readOnly = true)//readOnly 옵션을 사용하면 더 빠름 다른 불필요 동작 안함
    public List<WorkoutDto.Response> getAllWorkouts() {
        return workoutRecordRepository.findAll().stream()//stream -> data 하나씩 순차적으로 전부 꺼냄
                .map(WorkoutDto.Response::new)//map은 괄호 안의 내용으로 변환 -> 생성자로 Dto 객체 만들어라
                .collect(Collectors.toList());//stream된거 다시 모으기 -> toList - 리스트로 만들기
    }

    //Read - 단일 record 조회
    @Transactional(readOnly = true)
    public WorkoutDto.Response getWorkout(Long id) {
        WorkoutRecord record = workoutRecordRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 기록 없음. ID: " + id));

        return new WorkoutDto.Response(record);
    }
}
