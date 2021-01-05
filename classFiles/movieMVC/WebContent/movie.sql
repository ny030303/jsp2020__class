drop table movie;
drop table schedule;
drop table Room;
drop table ticket;
drop table member;

CREATE TABLE movie(
	movieNo number primary key, -- 영화 번호
	movieName VARCHAR2(20), -- 영화  제목
	category number, -- 장르
	runtime number, -- 120분
	img VARCHAR2(50), -- 이미지 파일 불러오기 위한 제목
	info VARCHAR2(200) -- 영화정보
);

CREATE TABLE member(
	id		VARCHAR2(20) PRIMARY KEY, --아이디
	pw		VARCHAR2(20), -- 비밀번호
	email	VARCHAR2(50), -- 이메일
	tel		VARCHAR2(20), -- 전화번호
	birth	date
);

CREATE TABLE schedule(
	schNo	number	primary key, --스케줄 번호
	movieNo	number, -- 영화관 번호
	runDay	date, -- 상영 날짜
	runtime	number, -- 상영 시간 : 몇분 짜리 영화
	roomNo	number -- 상영관 번호
);

CREATE TABLE Room( --상영관
	roomNo	NUMBER, -- 상영관 번호
	schNo	NUMBER, -- 스케줄 번호
	seatCnt NUMBER -- 상영관 내에 예매된 좌석 카운트
);

CREATE TABLE ticket(
	ticketNo NUMBER PRIMARY KEY, -- 티켓 번호
	bookDate date, -- 결제한 날짜
	schNo	NUMBER, -- 스케줄 번호
	seatNo	NUMBER, -- 내가 선택한 좌석 번호
	id		VARCHAR2(20) -- 회원 아이디
);

-- 01 액선 02 로멘스 03 코미디 04 스릴러 05 애니메이션
insert into movie values(10000,'어벤저스',01,120,'1.jpg','재밌다 ');
insert into movie values(10001,'노팅힐',02, 120 , '2.jpg','감동적이다 ');
insert into movie values(10002,'아이언맨',01, 120 , '3.jpg','멋있따');
insert into movie values(10003,'겨울왕국2',05, 130 , '4.jpg','재밌다 ');
insert into movie values(10004,'엑시트',03, 140 , '5.jpg','킬링타임 ');
insert into movie values(10005,'반도',04, 155 , '6.jpg','잘생겼다 ');
insert into movie values(10006,'23아이덴티디',04, 150 , '7.jpg','꿀잼');

insert into member values('test','1234','test@test.com','010-1234-1234','2002-05-12');
insert into member values('admin','admin','admin@admin.com','010-1234-1234','2002-05-12');
 --sysdate varchar2 -> date 
 --스케줄이 추가된 만큼 room 도 추가해야함
insert into schedule values(1,10000,TO_DATE('2020/11/11 11:50','yyyy/mm/dd hh24:mi'),120,1);
insert into schedule values(2,10000,TO_DATE('2020/11/11 1:50','yyyy/mm/dd hh24:mi'),120,1);
insert into schedule values(3,10000,TO_DATE('2020/11/11 3:10','yyyy/mm/dd hh24:mi'),120,1);
insert into schedule values(4,10000,TO_DATE('2020/11/11 8:50','yyyy/mm/dd hh24:mi'),120,1);

                          --좌석 번호 : 1 - 20 
insert into ticket values(1,SYSDATE,1,15,'test');
insert into ticket values(2,SYSDATE,1,14,'test');
insert into ticket values(3,SYSDATE,1,13,'test');
-- ticket최대값 +1한 ticketNo로 티켓을 추가
insert into ticket values((select NVL(max(ticketNo),0) from ticket)+1,SYSDATE,2,16,'test');

delete from ticket where schNo= 4;
select * from room ;
                  --상영관 --스케줄번호 --예매좌석 카운트 
insert into room values(1,1,3);
insert into room values(1,2,0);
insert into room values(1,3,0);
insert into room values(1,4,0);
--티켓이 insert 될 때마다 seatCnt(예매한 좌석수 ) 갯수도 증가해야한다 
update room set seatCnt = seatCnt +1 where schNo = 1;






select movieNo, roomNo, runDay, bookDate, seatNo, id from ticket tt, schedule st where tt.schNo = st.schNo and st.schNo = 1;

select mt.movieNo, roomNo, runDay, bookDate, seatNo, id from ticket tt, schedule st, movie mt where tt.schNo = st.schNo and st.schNo = 1 and st.movieNo = mt.movieNo;
select mt.movieNo, roomNo, runDay, bookDate, seatNo, id from ticket tt, schedule st, movie mt where tt.schNo = st.schNo and st.schNo = ? and st.movieNo = mt.movieNo;
select schNo, movieNo, runtime, roomNo, to_char(runDay, 'yyyy/mm/dd hh24:mi') runDay from schedule;
SELECT * FROM member where id = 'test' and pw = '2';

SELECT * FROM movie NATURAL JOIN schedule WHERE movieNo = 10000;

SELECT movieName, category, img, mt.runtime, info, schNo, roomNo, runDay FROM movie mt, schedule st 
WHERE mt.movieNo = st.movieNo and mt.movieNo = 10000;
--decode(컬럼이름,값, 참값, 거짓값)
SELECT schno, moviename, decode(category, 01,'액선',02,'로맨스',03,'코미디',04,'스릴러','애니메이션'), 
mt.runtime, img, info, to_char(runDay, 'mm/dd')as 날짜 ,
to_char(runDay, 'hh24:mi')as 상영시간, roomNo FROM movie mt, schedule st 
WHERE mt.movieNo = st.movieNo and mt.movieNo = 10000;


--회원아이디,예매한 날짜, 상영관번호 , 상영날짜,영화제목, 영화시간

SELECT ct.id, to_char(ct.bookDate, 'mm/dd') 예매한날짜, ct.seatNo 예매좌석, st.roomNo, to_char(st.runDay, 'mm/dd') 상영날짜,
mt.movieNo, mt.movieName, to_char(st.runDay, 'hh24:mi') 영화시간, mt.img
FROM movie mt, schedule st, ticket ct
WHERE mt.movieNo = st.movieNo and st.schNo = ct.schNo and ct.id = 'test';


