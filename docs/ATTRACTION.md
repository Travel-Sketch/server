# 관광지 기능 목록 명세서

* [x] 시도 목록을 조회할 수 있다. - `AttractionQueryController#searchSidos`
  * [x] 모든 시도 목록을 조회한다. - `AreaQueryService#searchSidos`
* [x] 구군 목록을 조회할 수 있다. - `AttractionQueryController#searchGuguns`
  * [x] 시도 코드를 입력 받아 구군 목록을 조회한다. - `AreaQueryService#searchGuguns`
  * [ ] [예외] 조회 결과가 빈 배열이면 예외가 발생한다.
* [x] 관광지 목록을 조회할 수 있다. - `AttractionQueryController#searchAttractions`
* [ ] 관광지를 상세 조회할 수 있다. - `AttractionQueryController#searchAttraction`
