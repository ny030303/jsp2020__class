--테이블 생성
create table book_tbl(
bcode number(5) not null primary key, --도서코드 
btitle varchar2(30), -- 책이름
bwriter varchar2(30), -- 저자
bpub number(4), --출판사코드
bprice number(10) not null, --가격
bdate date --출간날짜
);

--테이블 삭제
DROP TABLE book_tbl;
-- 테이블 내용 가져오는 sql
SELECT * FROM BOOK_TBL;
--BCODE를 기준으로 오름차순하여 테이블 내용 가져오는 sql (ASC 생략한 sql)
SELECT * FROM BOOK_TBL ORDER BY BCODE;
--샘플 데이터 추가 입력하는 sql
insert into book_tbl values(10100,'자바킹','강길동',1001,12000, '2020-11-02');
insert into book_tbl values(10101,'알고리듬','남길동',1002,18000, '2020-05-05');
insert into book_tbl values(10102,'스프링두','서길동',1003,23000, '2019-08-03');
insert into book_tbl values(10103,'파이썬','홍길동',1004,9000, '2019-10-11');

-- bcode의 최대값을 가져오는 sql
SELECT MAX(BCODE) FROM BOOK_TBL;
--입력한 값을 book_tbl에 추가하는 sql
insert into book_tbl(bcode,btitle,bwriter,bpub,bprice,bdate) values(10106,'테스트','정길순',1004,98000,'2019-10-11');
--BCODE를 기준으로 오름차순하여  테이블 내용 가져오는 sql (ASC 쓴 sql)
SELECT * FROM BOOK_TBL ORDER BY BCODE ASC;
--book_tbl에서 BCODE가 10106 인 컬럼 삭제하는 sql
DELETE FROM BOOK_TBL WHERE BCODE = 10106;
--book_tbl에서 BCODE가 10106 인 컬럼 가져오는 sql
SELECT * FROM BOOK_TBL WHERE BCODE = 10106;
--book_tbl에서 BCODE가 10106 인 컬럼의 속성을 각각 수정하는(update) sql
UPDATE BOOK_TBL SET BCODE=10106, Btitle='테스트222', bwriter='이길순', bpub=1003, bprice=5000, bdate='2019/12/11' WHERE bcode = 10106;