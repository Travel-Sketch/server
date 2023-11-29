# 게시물 기능 목록 명세서

* [ ] 회원은 게시물을 등록할 수 있다. - `PostController#createPost`
  * [ ] 제목, 내용을 입력 받아서 게시물을 등록한다.
  * [ ] 첨부 파일을 선택적으로 입력할 수 있다.
  * [ ] 장소를 선택적으로 입력할 수 있다.
* [ ] 회원은 게시물에 댓글을 등록할 수 있다. - `CommentController#createComment`
* [ ] 회원은 게시물을 스크랩할 수 있다. - `ScrapController#toggleScrap`
* [ ] 회원은 스크랩한 게시물 목록을 조회할 수 있다. - `ScrapController#getScrap`
* [ ] 모든 사용자는 게시물 목록을 조회할 수 있다. - `PostController#getPostList`
* [ ] 모든 사용자는 게시물 상세 조회를 할 수 있다. - `PostController#getPostDetail`
* [ ] 게시물 작성자는 게시물 내용을 수정할 수 있다. - `PostController#updatePost`
* [ ] 게시물 작성자는 게시물을 삭제할 수 있다. - `PostController#deletePost`
* [ ] 댓글 작성자는 댓글을 삭제할 수 있다. - `CommentController#deleteComment`