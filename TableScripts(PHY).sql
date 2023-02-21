-- WEB 계정 생성
CREATE USER PHY IDENTIFIED BY PHY;
GRANT RESOURCE, CONNECT TO PHY;

--------------- USER 관련 테이블 ---------------

CREATE TABLE MEMBER (
    NO NUMBER PRIMARY KEY,
    ID VARCHAR2(30) NOT NULL UNIQUE,
    PASSWORD VARCHAR2(100) NOT NULL,
    ROLE VARCHAR2(10) DEFAULT 'ROLE_USER',
    NAME VARCHAR2(15) NOT NULL,
    PHONE VARCHAR2(13),
    EMAIL VARCHAR2(100),
    ADDRESS VARCHAR2(100),
    STATUS VARCHAR2(1) DEFAULT 'Y' CHECK(STATUS IN('Y', 'N')),
    ENROLL_DATE DATE DEFAULT SYSDATE,
    MODIFY_DATE DATE DEFAULT SYSDATE
);

CREATE SEQUENCE SEQ_UNO;


COMMENT ON COLUMN MEMBER.NO IS '회원번호';
COMMENT ON COLUMN MEMBER.ID IS '회원아이디';
COMMENT ON COLUMN MEMBER.PASSWORD IS '회원비밀번호';
COMMENT ON COLUMN MEMBER.ROLE IS '회원타입';
COMMENT ON COLUMN MEMBER.NAME IS '회원명';
COMMENT ON COLUMN MEMBER.PHONE IS '전화번호';
COMMENT ON COLUMN MEMBER.EMAIL IS '이메일';
COMMENT ON COLUMN MEMBER.ADDRESS IS '주소';
COMMENT ON COLUMN MEMBER.STATUS IS '상태값(Y/N)';
COMMENT ON COLUMN MEMBER.ENROLL_DATE IS '회원가입일';
COMMENT ON COLUMN MEMBER.MODIFY_DATE IS '정보수정일';


-------------------관리자 삽입 추가--------------
INSERT INTO MEMBER (
    NO, 
    ID, 
    PASSWORD, 
    ROLE,
    NAME, 
    PHONE, 
    EMAIL, 
    ADDRESS,
    STATUS,
    ENROLL_DATE, 
    MODIFY_DATE
) VALUES(
    SEQ_UNO.NEXTVAL, 
    'admin', 
    '1234', 
    'ROLE_ADMIN', 
    '관리자', 
    '010-0000-0000', 
    'admin@iei.or.kr', 
    '서울시 강남구 역삼동',
    DEFAULT,
    DEFAULT,
    DEFAULT
);

---------------------회원 삽입 추가------------
INSERT INTO MEMBER (
    NO, 
    ID, 
    PASSWORD, 
    ROLE,
    NAME, 
    PHONE, 
    EMAIL, 
    ADDRESS,
    STATUS,
    ENROLL_DATE, 
    MODIFY_DATE
) VALUES(
    SEQ_UNO.NEXTVAL, 
    'test1', 
    '1234', 
    'ROLE_USER', 
    '김회원', 
    '010-1234-5678', 
    'user@iei.or.kr', 
    '서울시 강남구 역삼동',
    DEFAULT,
    DEFAULT,
    DEFAULT
);


COMMIT;

--------------- 1. Board 관련 테이블 ---------------

CREATE TABLE BOARD (   
    NO NUMBER,
    WRITER_NO NUMBER, 
    TITLE VARCHAR2(50), 
    CONTENT VARCHAR2(2000), 
    ORIGINAL_FILENAME VARCHAR2(100), 
    RENAMED_FILENAME VARCHAR2(100), 
    READCOUNT NUMBER DEFAULT 0, 
    STATUS VARCHAR2(1) DEFAULT 'Y' CHECK (STATUS IN('Y', 'N')),
    CREATE_DATE DATE DEFAULT SYSDATE, 
    MODIFY_DATE DATE DEFAULT SYSDATE,
    REPLYCOUNT NUMBER DEFAULT 0, 
    SECRET_CHECK VARCHAR2(1) DEFAULT 'Y' CHECK (SECRET_CHECK IN('Y', 'N')),
    CONSTRAINT PK_BOARD_NO PRIMARY KEY(NO),
    CONSTRAINT FK_BOARD_WRITER FOREIGN KEY(WRITER_NO) REFERENCES MEMBER(NO) ON DELETE SET NULL
);


COMMENT ON COLUMN BOARD.NO IS '게시글번호';
COMMENT ON COLUMN BOARD.WRITER_NO IS '게시글작성자';
COMMENT ON COLUMN BOARD.TITLE IS '게시글제목';
COMMENT ON COLUMN BOARD.CONTENT IS '게시글내용';
COMMENT ON COLUMN BOARD.ORIGINAL_FILENAME IS '첨부파일원래이름';
COMMENT ON COLUMN BOARD.RENAMED_FILENAME IS '첨부파일변경이름';
COMMENT ON COLUMN BOARD.READCOUNT IS '조회수';
COMMENT ON COLUMN BOARD.STATUS IS '상태값(Y/N)';
COMMENT ON COLUMN BOARD.CREATE_DATE IS '작성날짜';
COMMENT ON COLUMN BOARD.MODIFY_DATE IS '수정날짜';
COMMENT ON COLUMN BOARD.REPLYCOUNT IS '댓글수';
COMMENT ON COLUMN BOARD.SECRET_CHECK IS '비밀글여부';


CREATE SEQUENCE SEQ_BOARD_NO;

INSERT INTO BOARD VALUES(SEQ_BOARD_NO.NEXTVAL, 1, '게시글 1',  '이 게시글은 영국에서 시작해서...', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', SYSDATE, SYSDATE, DEFAULT, 'Y');

COMMIT;

--- 게시글 임의로 ---
BEGIN
    FOR N IN 1..23
    LOOP
        INSERT INTO BOARD VALUES(SEQ_BOARD_NO.NEXTVAL, 1, '게시글 ' || SEQ_BOARD_NO.CURRVAL , '이 게시글은 영국에서 시작해서..' ||  SEQ_BOARD_NO.CURRVAL, null, null, DEFAULT, 'Y', SYSDATE, SYSDATE, DEFAULT, 'Y');
    END LOOP;

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN ROLLBACK;
END;


------------------------- REPLY 관련 테이블 (Board용)-------------------------
CREATE TABLE REPLY(
  NO NUMBER PRIMARY KEY,
  BOARD_NO NUMBER,
  WRITER_NO NUMBER,
  CONTENT VARCHAR2(400),
  STATUS VARCHAR2(1) DEFAULT 'Y' CHECK (STATUS IN ('Y', 'N')),
  CREATE_DATE DATE DEFAULT SYSDATE,
  MODIFY_DATE DATE DEFAULT SYSDATE,
  FOREIGN KEY (BOARD_NO) REFERENCES BOARD,
  FOREIGN KEY (WRITER_NO) REFERENCES MEMBER
);

CREATE SEQUENCE SEQ_REPLY_NO;

COMMENT ON COLUMN "REPLY"."NO" IS '댓글번호';
COMMENT ON COLUMN "REPLY"."BOARD_NO" IS '댓글이작성된게시글';
COMMENT ON COLUMN "REPLY"."WRITER_NO" IS '댓글작성자';
COMMENT ON COLUMN "REPLY"."CONTENT" IS '댓글내용';
COMMENT ON COLUMN "REPLY"."STATUS" IS '상태값(Y/N)';
COMMENT ON COLUMN "REPLY"."CREATE_DATE" IS '댓글올린날짜';
COMMENT ON COLUMN "REPLY"."MODIFY_DATE" IS '댓글수정날짜';

COMMIT;


--------------- 2. Notice 관련 테이블 ---------------

CREATE TABLE NOTICE (   
    NO NUMBER,
    WRITER_NO NUMBER, 
    TITLE VARCHAR2(50), 
    CONTENT VARCHAR2(2000), 
    ORIGINAL_FILENAME VARCHAR2(100), 
    RENAMED_FILENAME VARCHAR2(100), 
    READCOUNT NUMBER DEFAULT 0, 
    STATUS VARCHAR2(1) DEFAULT 'Y' CHECK (STATUS IN('Y', 'N')),
    CREATE_DATE DATE DEFAULT SYSDATE, 
    MODIFY_DATE DATE DEFAULT SYSDATE,
    REPLYCOUNT NUMBER DEFAULT 0, 
    SECRET_CHECK VARCHAR2(1) DEFAULT 'Y' CHECK (SECRET_CHECK IN('Y', 'N')),
    CONSTRAINT PK_NOTICE_NO PRIMARY KEY(NO),
    CONSTRAINT FK_NOTICE_WRITER FOREIGN KEY(WRITER_NO) REFERENCES MEMBER(NO) ON DELETE SET NULL
);

COMMENT ON COLUMN NOTICE.NO IS '게시글번호';
COMMENT ON COLUMN NOTICE.WRITER_NO IS '게시글작성자';
COMMENT ON COLUMN NOTICE.TITLE IS '게시글제목';
COMMENT ON COLUMN NOTICE.CONTENT IS '게시글내용';
COMMENT ON COLUMN NOTICE.ORIGINAL_FILENAME IS '첨부파일원래이름';
COMMENT ON COLUMN NOTICE.RENAMED_FILENAME IS '첨부파일변경이름';
COMMENT ON COLUMN NOTICE.READCOUNT IS '조회수';
COMMENT ON COLUMN NOTICE.STATUS IS '상태값(Y/N)';
COMMENT ON COLUMN NOTICE.CREATE_DATE IS '작성날짜';
COMMENT ON COLUMN NOTICE.MODIFY_DATE IS '수정날짜';
COMMENT ON COLUMN NOTICE.REPLYCOUNT IS '댓글수';
COMMENT ON COLUMN NOTICE.SECRET_CHECK IS '비밀글여부';

CREATE SEQUENCE SEQ_NOTICE_NO;

INSERT INTO NOTICE VALUES(SEQ_NOTICE_NO.NEXTVAL, 1, '게시글 1',  '이 게시글은 영국에서 시작해서...', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', SYSDATE, SYSDATE, DEFAULT, 'Y');

COMMIT;


---------------------- 3. 1:1 문의게시판 (Qna 테이블) ----------------

CREATE TABLE QNABOARD (   
    NO NUMBER,
    WRITER_NO NUMBER, 
    TITLE VARCHAR2(50) NOT NULL, 
    CONTENT VARCHAR2(2000) NOT NULL, 
    ORIGINAL_FILENAME VARCHAR2(100), 
    RENAMED_FILENAME VARCHAR2(100), 
    READCOUNT NUMBER DEFAULT 0, 
    STATUS VARCHAR2(1) DEFAULT 'Y' CHECK (STATUS IN('Y', 'N')),
    CREATE_DATE DATE DEFAULT SYSDATE, 
    MODIFY_DATE DATE DEFAULT SYSDATE,
    REPLYCOUNT NUMBER DEFAULT 0, 
    SECRET_CHECK VARCHAR2(1) DEFAULT 'Y' CHECK (SECRET_CHECK IN('Y', 'N')),
    CONSTRAINT PK_QNABOARD_NO PRIMARY KEY(NO),
    CONSTRAINT FK_QNABOARD_WRITER FOREIGN KEY(WRITER_NO) REFERENCES MEMBER(NO) ON DELETE SET NULL
);


COMMENT ON COLUMN QNABOARD.NO IS '게시글번호';
COMMENT ON COLUMN QNABOARD.WRITER_NO IS '게시글작성자';
COMMENT ON COLUMN QNABOARD.TITLE IS '게시글제목';
COMMENT ON COLUMN QNABOARD.CONTENT IS '게시글내용';
COMMENT ON COLUMN QNABOARD.ORIGINAL_FILENAME IS '첨부파일원래이름';
COMMENT ON COLUMN QNABOARD.RENAMED_FILENAME IS '첨부파일변경이름';
COMMENT ON COLUMN QNABOARD.READCOUNT IS '조회수';
COMMENT ON COLUMN QNABOARD.STATUS IS '상태값(Y/N)';
COMMENT ON COLUMN QNABOARD.CREATE_DATE IS '작성날짜';
COMMENT ON COLUMN QNABOARD.MODIFY_DATE IS '수정날짜';
COMMENT ON COLUMN QNABOARD.REPLYCOUNT IS '댓글수';
COMMENT ON COLUMN QNABOARD.SECRET_CHECK IS '비밀글여부';


CREATE SEQUENCE SEQ_QNABOARD_NO;

INSERT INTO QNABOARD VALUES(SEQ_QNABOARD_NO.NEXTVAL, 1, '게시글 1',  '이 게시글은 영국에서 시작해서...', '원본파일명.txt', '변경된파일명.txt', DEFAULT, 'Y', SYSDATE, SYSDATE, DEFAULT, 'Y');

COMMIT;

--- 게시글 임의로 ---
BEGIN
    FOR N IN 1..6
    LOOP
        INSERT INTO QNABOARD VALUES(SEQ_QNABOARD_NO.NEXTVAL, 1, '게시글 ' || SEQ_QNABOARD_NO.CURRVAL , '이 게시글은 영국에서 시작해서..' ||  SEQ_QNABOARD_NO.CURRVAL, null, null, DEFAULT, 'Y', SYSDATE, SYSDATE, DEFAULT, 'Y');
    END LOOP;

    COMMIT;
EXCEPTION
    WHEN OTHERS THEN ROLLBACK;
END;


---------------------- 4. FAQ 테이블 (자주묻는질문) ----------------

CREATE TABLE FAQ (   
    NO NUMBER,
    WRITER_NO NUMBER, 
    TITLE VARCHAR2(50) NOT NULL, 
    CONTENT VARCHAR2(2000) NOT NULL,
    STATUS VARCHAR2(1) DEFAULT 'Y' CHECK (STATUS IN('Y', 'N')),
    CONSTRAINT PK_FAQ_NO PRIMARY KEY(NO),
    CONSTRAINT FK_FAQ_WRITER FOREIGN KEY(WRITER_NO) REFERENCES MEMBER(NO) ON DELETE SET NULL
);


COMMENT ON COLUMN FAQ.NO IS '게시글번호';
COMMENT ON COLUMN FAQ.WRITER_NO IS '게시글작성자';
COMMENT ON COLUMN FAQ.TITLE IS '게시글제목';
COMMENT ON COLUMN FAQ.CONTENT IS '게시글내용';
COMMENT ON COLUMN FAQ.STATUS IS '상태값(Y/N)';


CREATE SEQUENCE SEQ_FAQ_NO;

COMMIT;






---------------------- 센터 테이블 ----------------

CREATE TABLE GYM (
    NO NUMBER PRIMARY KEY,
    GYM_NAME VARCHAR2(100) NOT NULL UNIQUE,
    ADDRESS VARCHAR2(200) NOT NULL,
    GYM_PHONE VARCHAR2(30) NOT NULL,
    CONTENT VARCHAR2(1000),
    TIME VARCHAR2(200) NOT NULL,
    IMG VARCHAR2(500) DEFAULT 'defaultimg.jpg' NOT NULL,
    THUMB VARCHAR2(500) DEFAULT 'defaultthumb.jpg' NOT NULL,
    CATE VARCHAR2(100) NOT NULL,
    STATUS VARCHAR2(1) DEFAULT 'Y' CHECK (STATUS IN('Y', 'N')),
    CEO_PHONE VARCHAR2(30) NOT NULL,
    CEO_EMAIL VARCHAR2(100) NOT NULL
);

COMMENT ON COLUMN GYM.NO IS '센터번호';
COMMENT ON COLUMN GYM.GYM_NAME IS '센터이름';
COMMENT ON COLUMN GYM.ADDRESS IS '센터주소';
COMMENT ON COLUMN GYM.GYM_PHONE IS '센터휴대폰번호';
COMMENT ON COLUMN GYM.CONTENT IS '센터소개';
COMMENT ON COLUMN GYM.TIME IS '운영시간';
COMMENT ON COLUMN GYM.IMG IS '센터상세이미지';
COMMENT ON COLUMN GYM.THUMB IS '센터섬네일이미지';
COMMENT ON COLUMN GYM.CATE IS '카테고리';
COMMENT ON COLUMN GYM.STATUS IS '판매여부';
COMMENT ON COLUMN GYM.CEO_PHONE IS '대표전화번호';
COMMENT ON COLUMN GYM.CEO_EMAIL IS '대표이메일';


-- 데이터에 & 넣을 수 있게 설정하는 구문 (세션마다)
SET DEFINE OFF;


-- GYM 테이블 초기셋팅
INSERT INTO GYM (
    NO, 
    GYM_NAME, 
    ADDRESS, 
    GYM_PHONE,
    CONTENT, 
    TIME, 
    IMG, 
    THUMB,
    CATE,
    STATUS,
    CEO_PHONE, 
    CEO_EMAIL
) VALUES(
    1, 
    '선릉 코어짐', 
    '서울특별시 강남구 테헤란로48길 10 우정에쉐르2 지하 1층0502-1191-0113', 
    '0001-0000-0001', 
    'CORE GYM 고객 캐어 중심 휘트니스 핵심가치!', 
    '[평 일] 06:00 ~ 23:00', 
    'gymimg1.jpg',
    'thumb_gymimg1.jpg',
    '헬스,G.X',
    DEFAULT,
    '010-001-1234', 
    'test1@semi.com'
);

CREATE SEQUENCE SEQ_GYM
       INCREMENT BY  1
       START WITH 41;
       
COMMIT;


-- 추가 INSERT 추후사용
--INSERT INTO GYM (
--    NO, 
--    GYM_NAME, 
--    ADDRESS, 
--    GYM_PHONE,
--    CONTENT, 
--    TIME, 
--    IMG, 
--    THUMB,
--    CATE,
--    STATUS,
--    CEO_PHONE, 
--    CEO_EMAIL
--) VALUES(
--    SEQ_GYM.NEXTVAL, 
--    '서초 웰스필라테스', 
--    '서울특별시 서초구 양재대로2길 116-6 서초프라자 5층', 
--    '0001-1111-0042', 
--    '양재천 VIEW 필라테스!', 
--    '[평 일] 10:00 ~ 22:00', 
--    DEFAULT,
--    DEFAULT,
--    '필라테스',
--    DEFAULT,
--    '010-111-0042', 
--    'test41@semi.com'
--);
--
--COMMIT;


---------------------센터 좋아요 테이블 ----------------
-----------------(GYM,MEMBER 테이블 생성 필수)--------

CREATE TABLE LOVE (
    NO NUMBER PRIMARY KEY,
    GYM_NO NUMBER,
    MEMBER_NO NUMBER,
    FOREIGN KEY (GYM_NO) REFERENCES GYM,
    FOREIGN KEY (MEMBER_NO) REFERENCES MEMBER
);

COMMENT ON COLUMN LOVE.NO IS '좋아요번호';
COMMENT ON COLUMN LOVE.GYM_NO IS '센터번호';
COMMENT ON COLUMN LOVE.MEMBER_NO IS '회원번호';

CREATE SEQUENCE SEQ_LOVE;

INSERT INTO LOVE (NO, GYM_NO, MEMBER_NO)
            VALUES(SEQ_LOVE.NEXTVAL, 1, 2);

COMMIT;

----------------------리뷰  테이블 ----------------
-----------------(MEMBER, GYM 테이블 생성 필수)--------

CREATE TABLE REVIEW (
    NO NUMBER PRIMARY KEY,
    CONTENT VARCHAR2(400) NOT NULL,
    CREATE_DATE DATE DEFAULT SYSDATE,
    MODIFY_DATE DATE DEFAULT SYSDATE,
    STATUS VARCHAR2(1) DEFAULT 'Y' CHECK (STATUS IN('Y', 'N')),
    GRADE NUMBER CHECK (GRADE IN(1, 2, 3, 4, 5)),
    GYM_NO NUMBER,
    WRITER_NO NUMBER,
    FOREIGN KEY (GYM_NO) REFERENCES GYM,
    FOREIGN KEY (WRITER_NO) REFERENCES MEMBER
);

COMMENT ON COLUMN REVIEW.NO IS '리뷰번호';
COMMENT ON COLUMN REVIEW.CONTENT IS '내용';
COMMENT ON COLUMN REVIEW.CREATE_DATE IS '작성날짜';
COMMENT ON COLUMN REVIEW.MODIFY_DATE IS  '수정날짜';
COMMENT ON COLUMN REVIEW.STATUS IS '상태';
COMMENT ON COLUMN REVIEW.GRADE IS '평점';
COMMENT ON COLUMN REVIEW.GYM_NO IS '센터번호';
COMMENT ON COLUMN REVIEW.WRITER_NO IS '회원번호';

CREATE SEQUENCE SEQ_REVIEW;

INSERT INTO REVIEW (NO, CONTENT, CREATE_DATE, MODIFY_DATE, STATUS, GRADE, GYM_NO, WRITER_NO)
            VALUES(SEQ_REVIEW.NEXTVAL, '좋아요', DEFAULT, DEFAULT, DEFAULT, 5, 1, 2);

COMMIT;

---------------------리뷰 좋아요 테이블 ----------------
-----------------(REVIEW(GYM),MEMBER 테이블 생성 필수)--------

CREATE TABLE REVIEWLIKE (
    NO NUMBER PRIMARY KEY,
    REV_NO NUMBER,
    MEMBER_NO NUMBER,
    FOREIGN KEY (REV_NO) REFERENCES REVIEW,
    FOREIGN KEY (MEMBER_NO) REFERENCES MEMBER
);

COMMENT ON COLUMN REVIEWLIKE.NO IS '리뷰좋아요번호';
COMMENT ON COLUMN REVIEWLIKE.REV_NO IS '리뷰번호';
COMMENT ON COLUMN REVIEWLIKE.MEMBER_NO IS '회원번호';

CREATE SEQUENCE SEQ_REVIEWLIKE;

INSERT INTO REVIEWLIKE (NO, REV_NO, MEMBER_NO)
            VALUES(SEQ_REVIEWLIKE.NEXTVAL, 1, 2);

COMMIT;
