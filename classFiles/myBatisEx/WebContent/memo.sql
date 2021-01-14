create table memo (
	idx number primary key,
	name varchar2(50) not null,
	password varchar2(50) not null,
	memo varchar2(200) not null,
	writeDate TIMESTAMP default sysdate
	-- 바로바로 생성된 날짜가 들어가게 만들어주는 것
);

create sequence memo_idx_seq;

insert into memo(idx, name, password, memo) 
values(memo_idx_seq.nextval, '테스트','1234', 'testtest');

select * from memo;