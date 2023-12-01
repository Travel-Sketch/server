# QnA 기능 목록 명세서

* [x] 회원은 질문을 등록할 수 있다. - `QnaController#createQuestion`
  * [x] 이메일, 질문 유형, 제목, 내용, 비밀번호를 입력 받아 질문을 등록한다. - `QnaService#createQuestion`
  * [x] [예외] 입력 받은 질문 유형이 null이면 예외가 발생한다.
  * [x] [예외] 입력 받은 제목이 빈 문자열이면 예외가 발생한다.
  * [x] [예외] 입력 받은 제목의 길이가 50자를 초과하면 예외가 발생한다.
  * [x] [예외] 입력 받은 내용이 빈 문자열이면 예외가 발생한다.
  * [x] [예외] 입력 받은 비밀번호의 길이가 4자가 아니라면 예외가 발생한다. - `QnaCustomValid#validPwd`
  * [x] [예외] 입력 받은 비밀번호가 숫자 이외의 문자가 존재하면 예외가 발생한다. - `QnaCustomValid#validPwd`
* [x] 관리자는 답글을 등록할 수 있다. - `QnaController#createAnswer`
  * [x] 이메일, 답글을 입력 받아 답변을 등록한다. - `QnaService#createAnswer`
  * [x] [예외] 입력 받은 답글이 빈 문자열이면 예외가 발생한다.
  * [x] [예외] 입력 받은 아이디와 일치하는 QnA가 존재하지 않으면 예외가 발생한다.
  * [x] [예외] 이미 답변이 등록된 QnA라면 예외가 발생한다.
* [x] 회원은 QnA 목록을 조회할 수 있다. - `QnaQueryController#searchQnas`
  * [x] 페이지 번호, 검색 쿼리를 입력 받아 QnA를 조회한다. - `QnaQueryService#searchQnas`
  * [x] 페이지 번호의 기본값은 1이다.
  * [x] 검색 쿼리의 기본값은 빈 문자열이다.
  * [x] [예외] 입력 받은 페이지 번호가 0이하라면 예외가 발생한다.
* [x] 회원은 QnA 상세 조회할 수 있다. - `QnaQueryController#searchQna`
  * [x] 아이디, 비밀번호를 입력 받아 QnA를 상세 조회한다. - `QnaQueryService#searchQna`
  * [x] [예외] 입력 받은 아이디와 일치하는 QnA가 존재하지 않으면 예외가 발생한다.
  * [x] [예외] 암호가 걸려있는 QnA인 경우 비밀번호가 일치하지 않으면 예외가 발생한다.
  * [x] [예외] 입력 받은 아이디로 조회된 결과가 삭제된 QnA라면 예외가 발생한다.
* [ ] 회원은 QnA 삭제할 수 있다. - `QnaController#removeQna`
  * [ ] 아이디를 입력 받아 QnA를 삭제한다.
  * [ ] [예외] 입력 받은 아이디와 일치하는 QnA가 존재하지 않으면 예외가 발생한다. 