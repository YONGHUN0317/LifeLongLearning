package com.src.data.model.response

/**
 * 주요 응답 객체
 *
 * @property response API 응답의 메인 바디
 */
data class LifeLongRes(
    val response: LifeLongResponseBody
)

/**
 * API 응답의 주요 바디
 *
 * @property header 응답의 헤더 정보
 * @property body 응답의 본문 정보
 */
data class LifeLongResponseBody(
    val header: LifeLongHeader,
    val body: LifeLongBody
)

/**
 * API 응답의 헤더 정보
 *
 * @property resultCode 결과 코드
 * @property resultMsg 결과 메시지
 * @property type 응답의 타입
 */
data class LifeLongHeader(
    val resultCode: String,
    val resultMsg: String,
    val type: String
)

/**
 * API 응답의 본문 정보
 *
 * @property items 강좌 아이템 리스트
 */
data class LifeLongBody(
    val items: List<LifeLongItem>
)

/**
 * 강좌의 세부 정보
 *
 * @property lctreNm 강좌 이름
 * @property instrctrNm 강사 이름
 * @property edcStartDay 시작 날짜
 * @property edcEndDay 종료 날짜
 * @property edcStartTime 시작 시간
 * @property edcColseTime 종료 시간
 * @property lctreCo 강좌 설명
 * @property edcTrgetType 대상 유형
 * @property edcMthType 교육 방법
 * @property operDay 운영 일자
 * @property edcPlace 장소
 * @property psncpa 참가 인원
 * @property lctreCost 강좌 비용
 * @property edcRdnmadr 주소
 * @property operInstitutionNm 운영 기관 이름
 * @property operPhoneNumber 연락처
 * @property rceptStartDate 접수 시작 날짜
 * @property rceptEndDate 접수 종료 날짜
 * @property rceptMthType 접수 방법
 * @property slctnMthType 선택 방법
 * @property homepageUrl 홈페이지 주소
 * @property oadtCtLctreYn 외부 강좌 여부
 * @property pntBankAckestYn 은행 인증 여부
 * @property lrnAcnutAckestYn 학습 계좌 인증 여부
 * @property referenceDate 참고 날짜
 * @property insttCode 기관 코드
 */
data class LifeLongItem(
    val lctreNm: String,
    val instrctrNm: String,
    val edcStartDay: String,
    val edcEndDay: String,
    val edcStartTime: String,
    val edcColseTime: String,
    val lctreCo: String,
    val edcTrgetType: String,
    val edcMthType: String,
    val operDay: String,
    val edcPlace: String,
    val psncpa: String,
    val lctreCost: String,
    val edcRdnmadr: String,
    val operInstitutionNm: String,
    val operPhoneNumber: String,
    val rceptStartDate: String,
    val rceptEndDate: String,
    val rceptMthType: String,
    val slctnMthType: String,
    val homepageUrl: String,
    val oadtCtLctreYn: String,
    val pntBankAckestYn: String,
    val lrnAcnutAckestYn: String,
    val referenceDate: String,
    val insttCode: String
)
