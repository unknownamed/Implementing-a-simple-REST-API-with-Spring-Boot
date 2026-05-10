package com.unknown.crud.controller;

import com.unknown.crud.dto.WorkoutDto;
import com.unknown.crud.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//Rest Api 입구
@RequestMapping("/api/workouts")// URL 매핑
@RequiredArgsConstructor//SQL
public class WorkoutApiController {

    private final WorkoutService workoutService;

    //POST -> 201 Created
    //입력 내용 저장 (POST) HTTP POST요청에 대한 응답
    @PostMapping
    public ResponseEntity<WorkoutDto.Response> create(@RequestBody WorkoutDto.CreateRequest request) {
        WorkoutDto.Response response = workoutService.createWorkout(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);//POST 응답 규칙 지키기, 상태코드 201 + 바디(전체 응답내용)를 객체에 담아보냄
    }

    //GET PATCH(입력 데이터로 바뀌고 나머지 속성 그대로) PUT(입력 데이터로 바뀌고 나머지 속성 DEFALUT/NULL) -> 200 OK
    //전체 조회 (GET)
    @GetMapping
    public ResponseEntity<List<WorkoutDto.Response>> getAll() {
        List<WorkoutDto.Response> responses = workoutService.getAllWorkouts();

        return ResponseEntity.ok(responses);//status().body()의 단축형 -> 200 ok + 객체리스트
    }

    //단일 조회 (GET)
    @GetMapping("/{id}")
    public ResponseEntity<WorkoutDto.Response> get(@PathVariable Long id) {
        WorkoutDto.Response response = workoutService.getWorkout(id);

        return ResponseEntity.ok(response);
    }

    //수정 (PATCH) - 부분 수정에 적합
    @PatchMapping("/{id}")
    public ResponseEntity<WorkoutDto.Response> update(@PathVariable Long id, @RequestBody WorkoutDto.UpdateRequest request) {
        WorkoutDto.Response response = workoutService.updateWorkout(id, request);

        return ResponseEntity.ok(response);
    }

    //DELETE -> 204 No Content
    //전체 삭제 (DELETE)
    @DeleteMapping
    public ResponseEntity<Void> deleteALL() {
        workoutService.deleteAllWorkout();

        return ResponseEntity.noContent().build();//상태코드 204, 응답 줄때 상태코드를 담을 객체를 만들어 넣음
    }

    //단일 삭제 (DELETE)
    @DeleteMapping("/{id}")//URL에 적힌 api/workout다음에 적힌것
    public ResponseEntity<Void> delete(@PathVariable Long id) {//URL에 적힌 ID값 넣음
        workoutService.deleteWorkout(id);
        return ResponseEntity.noContent().build();//상태코드 204
    }
}
