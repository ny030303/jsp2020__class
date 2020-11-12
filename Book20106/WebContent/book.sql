create table book_tbl(
bcode number(5) not null primary key, --도서코드 
btitle varchar2(30), -- 책이름
bwriter varchar2(30), -- 저자
bpub number(4), --출판사코드
bprice number(10) not null, --가격
bdate date --출간날짜
);

DROP TABLE book_tbl;

SELECT * FROM BOOK_TBL;


insert into book_tbl values(10100,'자바킹','강길동',1001,12000, '2020/11/02');
insert into book_tbl values(10101,'알고리듬','남길동',1002,18000, '2020/05/05');
insert into book_tbl values(10102,'스프링두','서길동',1003,23000, '2019/08/03');
insert into book_tbl values(10103,'파이썬','홍길동',1004,9000, '2019/10/11');

