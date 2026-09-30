# Spring Workout API 실행 GIF

원본 프로젝트를 JDK 17과 저장소의 Gradle 9.4.1 Wrapper로 빌드하고, 생성된 Spring Boot 4.0.5 JAR를 실행했습니다. GIF는 실제 HTTP 요청 본문·응답 본문·상태 코드를 1280×720 프레임으로 구성한 기록입니다.

## 빌드와 실행

```powershell
.\gradlew.bat --no-daemon bootJar
java -jar build/libs/CRUD-0.0.1-SNAPSHOT.jar --server.port=18080
```

캡처에는 로컬 H2 메모리 DB와 `http://127.0.0.1:18080`을 사용했습니다.

## 확인한 요청

| 순서 | 요청 | 확인한 결과 |
| --- | --- | --- |
| 1 | GET `/api/workouts` | 200, 빈 배열 |
| 2 | POST `/api/workouts` | 201, 새 ID 및 거리 5.0 |
| 3 | GET `/api/workouts` | 200, 생성한 기록 1개 |
| 4 | PATCH `/api/workouts/{id}` | 200, 거리 8.0 / 시간 48 |
| 5 | GET `/api/workouts/{id}` | 200, 변경 값 유지 |
| 6 | DELETE `/api/workouts/{id}` | 204, 응답 본문 없음 |
| 7 | GET `/api/workouts` | 200, 빈 배열 |

생성과 수정에 사용한 필드는 `workoutDate`, `workoutType`, `distanceKm`, `durationMinutes`입니다. 현재 PATCH 구현에 맞춰 수정할 때 날짜와 운동 종류도 함께 전송했습니다. Java 소스와 빌드 설정은 변경하지 않았습니다.
