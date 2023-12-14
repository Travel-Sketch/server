# 게시물 기능 목록 명세서

* [x] 회원은 게시물을 등록할 수 있다. - `PostController#createPost`
  * [x] 제목, 내용을 입력 받아서 게시물을 등록한다.
  * [x] 게시물 등록시 다수의 파일을 첨부할 수 있다.
  * [x] [예외] 입력 받은 제목이 빈 문자열이면 예외가 발생한다.
  * [x] [예외] 입력 받은 제목의 길이가 50자를 초과하면 예외가 발생한다.
  * [x] [예외] 입력 받은 내용이 빈 문자열이면 예외가 발생한다.
  * [x] [예외] 첨부 파일의 업로드 파일명 길이가 100자를 초과하면 예외가 발생한다.
* [ ] 회원은 게시물에 댓글을 등록할 수 있다. - `CommentController#createComment`
* [ ] 회원은 게시물을 스크랩할 수 있다. - `ScrapController#toggleScrap`
* [ ] 회원은 스크랩한 게시물 목록을 조회할 수 있다. - `ScrapController#searchScraps`
* [ ] 모든 사용자는 게시물 목록을 조회할 수 있다. - `PostController#searchPosts`
* [ ] 모든 사용자는 게시물 상세 조회를 할 수 있다. - `PostController#searchPost`
* [ ] 게시물 작성자는 게시물 내용을 수정할 수 있다. - `PostController#modifyPost`
* [ ] 게시물 작성자는 게시물을 삭제할 수 있다. - `PostController#removePost`
* [ ] 댓글 작성자는 댓글을 삭제할 수 있다. - `CommentController#removeComment`

