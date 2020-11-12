-- 현재 계정(HR)에 있는 모든 테이블을 보여주기
SELECT * FROM TAB;

--  테이블 만들어 오류 발생할 때 테이블이 2번 생성이 안되게 삭제하기
DROP TABLE MEMBER_TBL;

-- 오라클 버전 테이블 만들기
CREATE TABLE MEMBER_TBL (
	MEMNO	NUMBER(4),
	NAME	VARCHAR2(30),
	ID		VARCHAR2(30),
	PASS	VARCHAR2(30),
	BIRTH	NUMBER(4,0),
	GENDER	VARCHAR2(10),
	JOB		VARCHAR2(30),
	CITY	CHAR(10),
	JOINDATE	DATE,
	CONSTRAINT PK_MEMBER_TBL PRIMARY KEY(MEMNO)
);
-- 트랜젝션의 처리 과정을 데이터베이스에 반영하기 위해서, 변경된 내용울 모두 영구 저장한다.
-- COMMIT 수행화면, 하나의 트랜젝션 과정을 종료하게 된다.
-- INSERT, UPDATE, DELETE 작업 과정을 종료하게 된다.
-- 이전 데이터가 완전히 UPDATE된다.
-- 별표
COMMIT

INSERT INTO MEMBER_TBL VALUES(1001, '홍길동', 'HONG1', '1234', 2002, 'M', '학생','서울',SYSDATE)
INSERT INTO MEMBER_TBL VALUES(1002, '고길동', 'HONG2', '1234', 2010, 'F', '교사','성남',SYSDATE);
INSERT INTO MEMBER_TBL VALUES(1003, '강길동', 'HONG3', '1234', 2009, 'M', '학생','용인',SYSDATE);
INSERT INTO MEMBER_TBL VALUES(1004, '장길동', 'HONG4', '1234', 2008, 'F', '가수','수원',SYSDATE);

INSERT INTO MEMBER_TBL(memno, name, id, pass, birth, gender, job, city, joindate) VALUES(1005, '김길동','HONG5','1234',2001,'M','공무원', '용인', SYSDATE)
-- 해당 테이블 목록 보기 정렬 칼럼 지정  오름차순, 내림차순
SELECT * FROM MEMBER_TBL order by MEMNO ASC;
-- 해당 테이블의 가장 큰 값 가져오기
SELECT MAX(MEMNO) FROM MEMBER_TBL;
-- 지정된 MEMNO 값에 해당하는 칼럼 삭제하기
DELETE FROM MEMBER_TBL WHERE MEMNO = 1005;
--  지정된 MEMNO값에 해당하는 칼럼 값들 수정하기
UPDATE MEMBER_TBL SET pass='1111', birth=2010, gender='M', job='회사원', city='성남' WHERE memno = 1002;