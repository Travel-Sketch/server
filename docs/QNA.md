# QnA 기능 목록 명세서

* [x] 회원은 질문을 등록할 수 있다. - `QnaController#createQuestion`
  * [x] 이메일, 질문 유형, 제목, 내용, 비밀번호를 입력 받아 질문을 등록한다. - `QnaService#createQuestion`
  * [x] [예외] 입력 받은 질문 유형이 null이면 예외가 발생한다.
  * [x] [예외] 입력 받은 제목이 빈 문자열이면 예외가 발생한다.
  * [x] [예외] 입력 받은 제목의 길이가 50자를 초과하면 예외가 발생한다.
  * [x] [예외] 입력 받은 내용이 빈 문자열이면 예외가 발생한다.
  * [x] [예외] 입력 받은 비밀번호의 길이가 4자가 아니라면 예외가 발생한다. - `QnaCustomValid#validPwd`
  * [x] [예외] 입력 받은 비밀번호가 숫자 이외의 문자가 존재하면 예외가 발생한다. - `QnaCustomValid#validPwd`
* [ ] 관리자는 답글을 등록할 수 있다. - `QnaController#createAnswer`
* [ ] 회원은 QnA 목록을 조회할 수 있다. - `QnaQueryController#searchQnas`
* [ ] 회원은 QnA 상세 조회할 수 있다. - `QnaQueryController#searchQna`
* [ ] 회원은 QnA 삭제할 수 있다. - `QnaController#removeQna`