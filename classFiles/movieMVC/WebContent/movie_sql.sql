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

insert into movie values(10000,'어벤저스',01,120,'1.jpg','재밌다 ');
insert into movie values(10001,'노팅힐',02, 120 , '2.jpg','감동적이다 ');
insert into movie values(10002,'아이언맨',01, 120 , '3.jpg','멋있따');
insert into movie values(10003,'겨울왕국2',05, 130 , '4.jpg','재밌다 ');
insert into movie values(10004,'엑시트',03, 140 , '5.jpg','킬링타임 ');
insert into movie values(10005,'반도',04, 155 , '6.jpg','잘생겼다 ');
insert into movie values(10006,'23아이덴티디',04, 150 , '7.jpg','꿀잼');

insert into member values('test','1234','test@test.com','010-1234-1234','2002-05-12');
                                           --sysdate varchar2 -> date 
insert into schedule values(1,10000,TO_DATE('2020/11/11 11:50','yyyy/mm/dd hh24:mi'),120,1);
insert into schedule values(2,10000,TO_DATE('2020/11/11 1:50','yyyy/mm/dd hh24:mi'),120,1);
insert into schedule values(3,10000,TO_DATE('2020/11/11 3:10','yyyy/mm/dd hh24:mi'),120,1);
insert into schedule values(4,10000,TO_DATE('2020/11/11 8:50','yyyy/mm/dd hh24:mi'),120,1);

                          --좌석 번호 : 1 - 20 
insert into ticket values(1,SYSDATE,1,15,'test');
insert into ticket values(2,SYSDATE,1,14,'test');

                  --상영관 --스케줄번호 --예매좌석 카운트 
insert into room values(1,1,1);

--티켓이 insert 될 때마다 seatCnt(예매한 좌석수 ) 갯수도 증가해야한다
 
update room set seatCnt = seatCnt +1 where schNo = 1; 