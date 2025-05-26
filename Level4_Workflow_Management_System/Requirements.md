# 파자 개요: 전자계약 워크플로우 관리 시스템 (4레벨)

## 토메인 개요

기업 고객이 사용하는 전자계약 플랫폼에서, 계약 초안 작성 → 검토자 승인 → 최종 계약서 서명까지의 워크플로우를 관리할 수 있는 API를 개발한다.

## 1. 기능 설명

### 1.1 계약 생성

* 기업 사용자가 계약 초안을 생성
* 최초 생성 시 상태는 `DRAFT`

### 1.2 계약 상태 변경

* 상태 변경 가능 목록:

  * `DRAFT` → `REVIEW`
  * `REVIEW` → `APPROVED`
  * `APPROVED` → `SIGNED`
* 중간 상태를 건너뛰는 전이는 불허
* 변경자, 변경 시간 기록

### 1.3 계약 상세 조회

* 계약 ID로 계약 정보 + 상태 변경 히스토리 조회

### 1.4 기업별 계약 목록 조회

* 로그인한 기업 사용자가 속한 기업의 계약 목록 조회
* `?status=REVIEW` 같은 상태 필터링 지원

### 1.5 계약 삭제

* 계약 상태가 `DRAFT`인 경우에만 삭제 가능
* 삭제 시 실제 데이터는 삭제하지 않고 `DELETED` 상태로 마킹하여 논리 삭제

### 1.6 계약 서명자 추가

* 계약에 서명자를 추가할 수 있음 (이름, 이메일 포함)
* 계약 상태가 `REVIEW` 이상인 경우에는 서명자 추가 불가

## 2. API 명세

### \[POST] `/contracts`

계약 초안 생성

객체:

```json
{
  "title": "계약서 제목",
  "content": "계약 부문",
  "companyId": 1001
}
```

회신 (201 Created):

```json
{
  "contractId": 101,
  "status": "DRAFT"
}
```

---

### \[PATCH] `/contracts/{contractId}/status`

계약 상태 변경

객체:

```json
{
  "newStatus": "REVIEW",
  "changedBy": "user_123"
}
```

회신 (200 OK):

```json
{
  "contractId": 101,
  "previousStatus": "DRAFT",
  "newStatus": "REVIEW",
  "changedAt": "2025-05-22T14:00:00Z"
}
```

실패 (400 Bad Request):

```json
{
  "error": "InvalidStatusTransition",
  "message": "Cannot change status from APPROVED to REVIEW"
}
```

---

### \[GET] `/contracts/{contractId}`

계약 상세 조회 (상태 히스토리 포함)

회신 (200 OK):

```json
{
  "contractId": 101,
  "title": "계약서 제목",
  "content": "계약 부문",
  "status": "SIGNED",
  "history": [
    {
      "from": "DRAFT",
      "to": "REVIEW",
      "changedBy": "user_123",
      "changedAt": "2025-05-20T10:00:00Z"
    }
  ]
}
```

---

### \[GET] `/companies/{companyId}/contracts?status=REVIEW`

기업별 계약 목록 조회

회신 (200 OK):

```json
[
  {
    "contractId": 101,
    "title": "계약서 제목",
    "status": "REVIEW"
  }
]
```

---

### \[DELETE] `/contracts/{contractId}`

계약 삭제 (논리 삭제)

회신 (204 No Content)

실패 (400 Bad Request):

```json
{
  "error": "InvalidContractStatus",
  "message": "Only DRAFT contracts can be deleted."
}
```

---

### \[POST] `/contracts/{contractId}/signers`

계약 서명자 추가

객체:

```json
{
  "name": "홍길동",
  "email": "hong@example.com"
}
```

회신 (201 Created):

```json
{
  "signerId": 501,
  "contractId": 101
}
```

실패 (400 Bad Request):

```json
{
  "error": "InvalidContractStatus",
  "message": "Cannot add signers to a contract not in DRAFT status."
}
```

## 3. 에러 및 검증 규칙

* 상태 전이는 순차적으로만 가능
* 존재하지 않는 contractId, companyId 요청 경우 404
* contract은 companyId와 가존되어야 함
* changedBy는 null 될 수 없음
* content는 최소 10자 이상
* DRAFT 상태가 아닌 계약은 삭제 불가
* REVIEW 이상 상태 계약에 서명자 추가 불가

## 4. 테스트 컷

1. 계약 초안 생성 성공
2. 생성 후 상태는 DRAFT인지 확인
3. DRAFT → REVIEW 상태 변경 성공
4. DRAFT → APPROVED 시도 → 실패
5. 상태 변경 히스토리 포함 조회
6. 존재하지 않는 contractId 조회 → 404
7. status 필터 적용 기업별 계약 목록 조회
8. 빈 content로 계약 생성 → 400 발행
9. DRAFT 계약 삭제 성공
10. REVIEW 계약 삭제 시도 → 실패
11. DRAFT 계약에 서명자 추가 성공
12. REVIEW 계약에 서명자 추가 시도 → 실패
