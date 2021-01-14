drop table student_tbl;
create table student_tbl(
	stuNo number(5) PRIMARY KEY,
	password varchar2(50) not null,
	stuName varchar2(50) not null,
	grade number(1) not null,
	kor number(3) not null,
	math number(3) not null,
	eng number(3) not null,
	club_id varchar2(50)
)

-- primary key 기본값 특징 유니크 중복 불가능!

create table club_tbl( --동아리 테이블
club_id varchar2(50) primary key, --동아리 아이디
club_name varchar2(225) not null, --동아리 이름
teacher varchar2(50) not null,--담당교사이름
fee number(10) --참여비
);

create table skill_tbl(
pass number(1), -- 평가전 통과 0(미통과) 1(통과)
passDate date -- 통과 날짜
)

insert into club_tbl values('A1001', '배드민턴', '김선생', 0);
insert into club_tbl values('A1002', '봉사활동', '박선생', 0);
insert into club_tbl values('A1003', '비즈공예', '황선생', 15000);
insert into club_tbl values('A1004', '로봇만들기', '최선생', 50000);
insert into club_tbl values('A1005', '간편요리', '용선생', 30000);

--inner join 조건 : 동일한 이름의 컬럼이 있어야 한다 두개의 연결하는 테이벌이 존재해야 한다.
--iner join 은 교집합이다 : 두가지 테이블 모두 존재하는 데이터만 가져온다.

select * from student_tbl, club_tbl where student_tbl.club_id = club_tbl.club_id;

select * from student_tbl st, club_tbl ct where st.club_id = ct.club_id;

--drop table student_tbl;
-- 본인 학번 본인 이름 2학년

INSERT into STUDENT_TBL values(20104, '0619', '이유진', 2, 96, 87, 79, 'A1001');
INSERT into STUDENT_TBL values(20105, '0525', '강은진', 2, 80, 80, 80, 'A1002');
INSERT into STUDENT_TBL values(20106, '0404', '정나영', 2, 60, 70, 80, 'A1003');
INSERT into STUDENT_TBL values(20107, '0909', '조한슬', 2, 90, 87, 90, 'A1004');
INSERT into STUDENT_TBL values(20108, '0630', '박물결', 2, 40, 70, 100, 'A1005');
INSERT into STUDENT_TBL values(20110, 'tes', 'ddd', 2, 40, 70, 100, 'A1006');

select * from Student_tbl;

--select * from STUDENT_TBL where stuNo = 20104;
--INSERT into STUDENT_TBL(stuNo, password, stuName) values(20104, '0619', '이유진')
UPDATE STUDENT_TBL set stuName = '테스트', password = 'aaaa', grade = 1, kor = 65, math = 77, eng = 99 where stuNo = 20107;

select * from STUDENT_TBL;
select * from STUDENT_TBL where stuNo = 20104;

delete student_tbl; -- 테이블은 존재는 한다 : 안에 있는 모든 데이터만 사라진다
delete from student_tbl where stuNo = 20110; -- where 조건에 있는 데이터만 삭제한다.
--drop table student_tbl; --테이블 완전 삭제 : 테이블 존재하지 않음

commit;

