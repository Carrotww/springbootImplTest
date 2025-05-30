# 📘 도서 리뷰 시스템 - 요구사항 명세

## 1. 기능 설명

사용자는 웹사이트에서 도서에 대한 리뷰를 작성하고, 수정하고, 삭제할 수 있다.
각 리뷰에는 평점(1\~5), 제목, 내용이 포함된다.
리뷰는 해당 도서에 연결되며, 사용자는 한 도서당 하나의 리뷰만 작성할 수 있다.
리뷰 목록은 도서 상세 페이지에서 확인 가능하며, 평점 평균도 함께 제공된다.
관리자는 부적절한 리뷰를 신고 받아 리뷰를 삭제하거나 숨길 수 있다.
사용자는 리뷰에 좋아요를 누를 수 있다.
모든 기능은 JWT 기반 인증을 전제로 하며, 회원가입과 로그인 기능을 통해 토큰을 발급받아야 한다.

---

## 2. API 명세

### 🔹 회원가입 API

* **URL:** `POST /api/signup`
* **Request Body:**

```json
{
  "email": "user@example.com",
  "password": "secure1234",
  "nickname": "책사랑"
}
```

* **Response (201 Created):**

```json
{
  "userId": 5,
  "email": "user@example.com",
  "nickname": "책사랑"
}
```

### 🔹 로그인 API

* **URL:** `POST /api/login`
* **Request Body:**

```json
{
  "email": "user@example.com",
  "password": "secure1234"
}
```

* **Response (200 OK):**

```json
{
  "accessToken": "jwt-token-string",
  "tokenType": "Bearer"
}
```

### 🔹 리뷰 작성 API

* **URL:** `POST /api/books/{bookId}/reviews`
* **Request Header:**

  * Authorization: Bearer {JWT}
* **Request Body:**

```json
{
  "title": "리뷰 제목",
  "content": "리뷰 내용",
  "rating": 4
}
```

* **Response (201 Created):**

```json
{
  "id": 12,
  "bookId": 3,
  "userId": 5,
  "title": "리뷰 제목",
  "content": "리뷰 내용",
  "rating": 4,
  "createdAt": "2025-05-22T10:12:00"
}
```

### 🔹 리뷰 수정 API

* **URL:** `PUT /api/reviews/{reviewId}`
* **Request Header:**

  * Authorization: Bearer {JWT}
  * 
* **Request Body:**

```json
{
  "title": "수정된 제목",
  "content": "수정된 내용",
  "rating": 5
}
```

* **Response (200 OK):**

```json
{
  "id": 12,
  "title": "수정된 제목",
  "content": "수정된 내용",
  "rating": 5,
  "updatedAt": "2025-05-22T11:00:00"
}
```

### 🔹 리뷰 삭제 API

* **URL:** `DELETE /api/reviews/{reviewId}`
* **Request Header:**

  * Authorization: Bearer {JWT}
* **Response (204 No Content):** 본문 없음

### 🔹 리뷰 목록 조회 API

* **URL:** `GET /api/books/{bookId}/reviews`
* **Query Params:** `page`, `size`
* **Response (200 OK):**

```json
{
  "averageRating": 4.3,
  "reviews": [
    {
      "id": 11,
      "userId": 5,
      "title": "재밌는 책이에요",
      "rating": 5,
      "createdAt": "2025-05-20T10:00:00"
    }
  ]
}
```

### 🔹 특정 사용자의 리뷰 조회 API

* **URL:** `GET /api/users/{userId}/reviews`
* **Query Params:** `page`, `size`
* **Response (200 OK):**

```json
{
  "reviews": [
    {
      "id": 12,
      "bookId": 3,
      "title": "재미있었음",
      "rating": 4,
      "createdAt": "2025-05-22T10:12:00"
    }
  ]
}
```

### 🔹 도서별 리뷰 평균 평점 조회 API

* **URL:** `GET /api/books/{bookId}/rating`
* **Response (200 OK):**

```json
{
  "bookId": 3,
  "averageRating": 4.3,
  "reviewCount": 25
}
```

### 🔹 리뷰 좋아요 API

* **URL:** `POST /api/reviews/{reviewId}/like`
* **Request Header:**

  * Authorization: Bearer {JWT}
* **Response (200 OK):**

```json
{
  "likes": 15
}
```

### 🔹 리뷰 좋아요 취소 API

* **URL:** `DELETE /api/reviews/{reviewId}/like`
* **Request Header:**

  * Authorization: Bearer {JWT}
* **Response (200 OK):**

```json
{
  "likes": 14
}
```

### 🔹 리뷰 신고 API

* **URL:** `POST /api/reviews/{reviewId}/report`
* **Request Header:**

  * Authorization: Bearer {JWT}
* **Request Body:**

```json
{
  "reason": "욕설이 포함되어 있습니다."
}
```

* **Response (201 Created):**

```json
{
  "reportId": 9,
  "reviewId": 12,
  "status": "PENDING"
}
```

### 🔹 관리자 리뷰 숨김 API

* **URL:** `PUT /api/admin/reviews/{reviewId}/hide`
* **Request Header:**

  * Authorization: Bearer {ADMIN\_JWT}
* **Response (200 OK):**

```json
{
  "reviewId": 12,
  "hidden": true
}
```

---

## 3. 검증 및 에러 규칙

* 평점(`rating`)은 1\~5 사이의 정수여야 함.
* 제목, 내용은 null 또는 공백일 수 없음.
* 동일한 사용자가 동일한 도서에 대해 리뷰를 두 번 작성할 수 없음 → `409 Conflict`
* 존재하지 않는 도서나 리뷰 ID → `404 Not Found`
* 리뷰 작성자와 요청 사용자가 다를 경우 수정/삭제 불가 → `403 Forbidden`
* 인증 토큰이 없는 경우 → `401 Unauthorized`
* 좋아요는 한 리뷰당 사용자당 1회만 가능, 중복 시 → `409 Conflict`
* 신고 사유가 공백이면 → `400 Bad Request`
* 이메일 중복 회원가입 시도 → `409 Conflict`
* 로그인 실패 (비밀번호 불일치 등) → `401 Unauthorized`

---

## 4. 테스트 케이스

### ✅ 성공 케이스

* 회원가입, 로그인 후 JWT 발급
* 사용자가 도서에 대해 유효한 리뷰 작성
* 리뷰 작성 후 목록 조회 시 해당 리뷰 포함
* 리뷰 수정 시 내용과 평점 변경 반영
* 리뷰 삭제 후 목록에서 제외됨
* 리뷰에 좋아요 추가/취소 가능
* 리뷰 신고 가능
* 관리자가 리뷰 숨김 처리 가능

### ❌ 실패 케이스

* rating이 0 또는 6일 경우 → `400 Bad Request`
* 제목이 빈 문자열일 경우 → `400 Bad Request`
* 동일한 도서에 이미 리뷰 작성된 경우 다시 작성 시도 → `409 Conflict`
* 다른 사용자의 리뷰를 수정 또는 삭제 시도 → `403 Forbidden`
* 잘못된 JWT 또는 미인증 상태 → `401 Unauthorized`
* 중복 좋아요 요청 → `409 Conflict`
* 신고 사유가 빈 문자열일 경우 → `400 Bad Request`
* 잘못된 이메일/비밀번호로 로그인 시도 → `401 Unauthorized`
* 중복 이메일로 회원가입 시도 → `409 Conflict`
