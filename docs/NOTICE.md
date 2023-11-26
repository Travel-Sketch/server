# 공지사항 기능 목록 명세서

* [ ] 관리자는 공지사항을 등록할 수 있다. - `NoticeController#createNotice`
  * [ ] 이메일, 제목, 내용을 입력 받아 공지사항 등록을 한다.
  * [ ] [예외] 입력 받은 제목이 빈 문자열이면 예외가 발생한다.
  * [ ] [예외] 입력 받은 제목의 길이가 50자를 초과하면 예외가 발생한다.
  * [ ] [예외] 입력 받은 내용이 빈 문자열이면 예외가 발생한다.
* [ ] 모든 사용자는 공지사항을 조회할 수 있다. - `NoticeController#searchNotices`
* [ ] 모든 사용자는 공지사항을 상세 조회할 수 있다. - `NoticeController#searchNotice`
* [ ] 관리자는 공지사항을 수정할 수 있다. - `NoticeController#modifyNotice`
* [ ] 관리자는 공지사항을 삭제할 수 있다. - `NoticeController#removeNotice`