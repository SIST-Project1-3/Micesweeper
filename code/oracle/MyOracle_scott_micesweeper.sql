-- 멤버 테이블 생성
CREATE TABLE MEMBER(
    ID  VARCHAR2(20) CONSTRAINT MEMBER_PK PRIMARY KEY, -- 기본키
    PW VARCHAR(20) NOT NULL, -- 패스워드는 반드시 입력
    WIN NUMBER(5) DEFAULT 0 NOT NULL, -- 승리횟수는 기본값 0, 반드시 입력
    LOSE NUMBER(5) DEFAULT 0 NOT NULL, -- 패배횟수도 기본값 0, 이하동문
    RDATE DATE DEFAULT SYSDATE NOT NULL, -- 등록일자는 기본값 SYSDATE, 반드시 입력
    IMG VARCHAR2(40) DEFAULT 'images/쥐.png' NOT NULL, -- 해당 프로필의 이미지 사진
);

-- 게시글 번호 시퀀스 생성
CREATE SEQUENCE BOARD_NO_SEQ
START WITH 1 -- 1부터 시작
INCREMENT BY 1 -- 1씩 증가
CACHE 20; -- 메모리에 미리 올려 놓을 인덱스 개수. 비정상 종료되면 LAST_NUMBER가 20씩 증가
-- 시퀀스 확인
SELECT * FROM USER_SEQUENCES;

-- 게시판 테이블 생성
CREATE TABLE BOARD(
 NO NUMBER(10)  CONSTRAINT BOARD_PK PRIMARY KEY , -- NO는 게시글 번호이며, 상단의 시퀀스를 이용하여 글번호를 생성함.
 TITLE VARCHAR(40) NOT NULL, -- 글 제목. 반드시 입력
 CONTENT VARCHAR(1000) NOT NULL, -- 글 내용 1000자 제한. 반드시 입력
 ID VARCHAR(20) NOT NULL, -- 작성자 ID 반드시 입력
 VIEWCOUNT NUMBER(5) DEFAULT 0 NOT NULL, -- 조회수 기본값 0 
 WDATE DATE DEFAULT SYSDATE NOT NULL, -- 작성일자 기본값 SYSDATE 
 COMMENTS VARCHAR2(4000), -- 댓글을 VARCHAR2 형태로 저장함. 댓글 하나는 \n로 구분하여 저장. id와 내용은 : 를 이용하여 구분
 CONSTRAINT FK_BOARD FOREIGN KEY (ID) REFERENCES MEMBER(ID) -- 작성자 외래키 설정
);

-- 이미지 테이블 생성
CREATE TABLE IMG(
  IMG VARCHAR2(40) NOT NULL  
);
-- 이미지 경로 입력
INSERT INTO IMG VALUES('images/프로필고양이1.jpg');
INSERT INTO IMG VALUES('images/프로필고양이2.jpg');
INSERT INTO IMG VALUES('images/프로필쥐1.jpg');
INSERT INTO IMG VALUES('images/프로필쥐2.png');
INSERT INTO IMG VALUES('images/고양이.png');
INSERT INTO IMG VALUES('images/쥐.png');

-- 계정 생성
INSERT INTO MEMBER VALUES('test', '1234', 0, 0, SYSDATE);
-- 계정 목록 확인
SELECT * FROM MEMBER;
-- 게시글 작성
INSERT INTO BOARD(NO, TITLE, CONTENT, ID) VALUES(BOARD_NO_SEQ.NEXTVAL, 'DB입력 테스트', '내용', 'test');
-- 게시글 목록
SELECT * FROM BOARD ORDER BY NO DESC;
-- 게시글 읽기 전 조회수 증가
UPDATE BOARD SET VIEWCOUNT = VIEWCOUNT + 1 WHERE NO = 42;
-- 게시글 읽기
SELECT * FROM BOARD WHERE NO = 1;
-- 게시글 검색
SELECT * FROM BOARD WHERE TITLE LIKE '%성%' ORDER BY NO DESC;
-- 게시글 수정
UPDATE BOARD SET TITLE = '수정', CONTENT = '수정됨 ㅎ' WHERE NO = 1;
-- 게시글 삭제
DELETE FROM BOARD WHERE NO = 25;
-- 댓글 작성
UPDATE BOARD SET COMMENTS = COMMENTS || 'test2' || ':' || '댓글입니다' || '\n' WHERE NO = 49;
-- 자바에서는 아래와 같이 \ -> \\ 로 해야함
-- UPDATE BOARD SET COMMENTS = COMMENTS || 'test2' || ':' || '댓글입니다' || '\\n' WHERE NO = 49;

-- 이미지 목록
SELECT * FROM IMG;
-- 프로필 이미지 업데이트
UPDATE MEMBER SET IMG='images/고양이' WHERE ID='test';

-- 승리 횟수 증가 
UPDATE MEMBER SET WIN = WIN+1 WHERE ID='test';
-- 패배 횟수 증가
UPDATE MEMBER SET LOSE = LOSE+1 WHERE ID='test';