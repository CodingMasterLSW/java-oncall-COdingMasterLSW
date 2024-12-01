## 개발자 비상 근무

### 입력
- [x] 비상 근무를 배정할 월과 시작 요일을 입력받는다.
- [x] 평일 근무 순번대로 사원 닉네임을 입력받는다
- [x] 휴일 근무 순번대로 사원 닉네임을 입력받는다.
  - Exception
  - [x] 공백을 입력할 경우, 예외를 발생시킨다.
  - [ ] 사원 닉네임이 중복된다면, 예외를 발생시킨다.
  - [ ] 근무자의 닉네임은 5자를 초과할 경우, 예외를 발생시킨다.
  - [ ] 최소 5명, 최대 35명의 근무자가 아닐 경우, 예외를 발생시킨다.
  - [ ] 평일 순번 또는 휴일 순번의 입력 값이 올바르지 않은 경우, '평일 순번'부터 다시 입력받는다.

### 근무자 배정 
- [ ] 비상 근무자는 평일 순번, 휴일 순번에 각각 1회 편성되어야 한다.
- [ ] 근무자가 연속 2일 근무하게 되는 상황에는, 다음 근무자와 순서를 바꿔 편성한다.


### 근무자 출력
- [ ] 월, 일, 요일, 근무자 명을 출력한다.
  - [ ] 평일이 공휴일인 경우, (휴일) 메세지도 함께 출력한다.
