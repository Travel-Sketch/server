# 여행 계획 기능 목록 명세서

* [x] 회원은 여행 계획을 등록 할 수 있다. - `PlanController#createPlan`
  * [x] 제목, 관광지 정보를 입력 받아 계획을 등록한다. - `PlanService#createPlan`
  * [x] [예외] 입력 받은 제목이 빈 문자열이면 예외가 발생한다. 
  * [x] [예외] 입력 받은 제목이 50자를 초과하면 예외가 발생한다.
  * [x] [예외] 입력 받은 관광지가 빈 배열이면 예외가 발생한다.
  * [ ] [예외] 입력 받은 관광지 정보가 10개를 초과하면 예외가 발생한다.
  * [x] [예외] 입력 받은 관광지 정보와 일치하는 데이터가 존재하지 않으면 예외가 발생한다.
* [ ] 회원은 다른 회원이 등록한 계획 목록을 조회할 수 있다. - `PlanQueryController#searchPlans`
* [ ] 회원은 다른 회원이 등록한 계획을 상세 조회할 수 있다. - `PlanQueryController#searchPlan`
* [ ] 본인이 등록한 계획을 수정할 수 있다. - `PlanController#modifyPlan`
* [ ] 본인이 등록한 계획을 삭제할 수 있다. - `PlanController#removePlan`