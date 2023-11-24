# 회원 기능 목록 명세서

* [x] 회원 등록(가입)을 할 수 있다. - `AccountController#join`
  * [x] 이메일, 비밀번호, 이름, 생년월일, 성별, 닉네임을 입력 받아 회원 등록(가입)을 한다. - `MemberService#createMember`
  * [x] 이메일 도메인은 naver, gmail, kakao 3가지를 지원한다.
  * [x] [예외] 입력 받은 이메일이 빈 문자열이면 예외가 발생한다.
  * [x] [예외] 입력 받은 이메일의 길이가 100자를 초과하면 예외가 발생한다.
  * [x] [예외] 입력 받은 이메일이 이메일 형식이 아니라면 예외가 발생한다. - `MemberCustomValid#validEmail`
  * [x] [예외] 입력 받은 이메일 도메인이 서비스에서 지원하지 않는 도메인이라면 예외가 발생한다. - `MemberCustomValid#validEmail`
  * [x] [예외] 입력 받은 이메일이 이미 사용 중이라면 예외가 발생한다. - `MemberService#checkDuplicationForEmail`
  * [x] [예외] 입력 받은 비밀번호가 최소 8자, 최대 20자를 벗어나면 예외가 발생한다.
  * [x] [예외] 입력 받은 비밀번호에 영어, 숫자, 특수문자가 단 하나라도 존재하지 않으면 예외가 발생한다. - `MemberCustomValid#validPwd`
  * [x] [예외] 입력 받은 이름이 빈 문자열이면 예외가 발생한다.
  * [x] [예외] 입력 받은 이름의 길이가 10자를 초과하면 예외가 발생한다.
  * [x] [예외] 입력 받은 이름에 한글 이외의 문자가 존재하면 예외가 발생한다. - `MemberCustomValid#validName`
  * [x] [예외] 입력 받은 생년월일의 길이가 10자가 아니라면 예외가 발생한다.
  * [x] [예외] 입력 받은 생년월일에 숫자와 하이픈 이외의 문자가 존재하면 예외가 발생한다.
  * [x] [예외] 입력 받은 생년월일이 현재 시간으로부터 미래라면 예외가 발생한다.
  * [x] [예외] 입력 받은 성별의 길이가 1자가 아니라면 예외가 발생한다.
  * [x] [예외] 입력 받은 성별이 `M`, `F` 이외의 문자가 입력되면 예외가 발생한다. - `MemberCustomValid#validGender`
  * [x] [예외] 입력 받은 닉네임이 빈 문자열이면 예외가 발생한다.
  * [x] [예외] 입력 받은 닉네임의 길이가 10자를 초과하면 예외가 발생한다.
  * [x] [예외] 입력 받은 닉네임에 한글, 영어, 숫자 이외의 문자가 존재하면 예외가 발생한다. - `MemberCustomValid#validNickname`
  * [x] [예외] 입력 받은 닉네임이 이미 사용 중이라면 예외가 발생한다. - `MemberService#checkDuplicationForNickname`
* [ ] 회원 이메일 중복 확인을 위해 인증 번호 요청을 보낼 수 있다. - `AccountController#requestAuthenticationNumber`
* [ ] 회원 이메일 중복 확인을 위해 발급 받은 인증 번호가 일치하는지 확인할 수 있다. - `AccountController#checkAuthenticationNumber`
* [ ] 회원은 로그인을 할 수 있다. - `AccountController#login`
* [ ] 회원은 로그아웃을 할 수 있다.
* [ ] 회원은 본인의 회원 정보를 조회할 수 있다. - `MemberQueryController#searchMemberInfo`
* [ ] 회원 비밀번호를 수정할 수 있다. - `MemberController#modifyPwd`
* [ ] 회원 닉네임을 수정할 수 있다. - `MemberController#modifyNickname`
* [ ] 회원 삭제(탈퇴)를 할 수 있다. - `MemberController#withdrawal`
