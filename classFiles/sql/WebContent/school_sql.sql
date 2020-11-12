create table student_tbl(
	stuNo	number(5, 0) primary key,
	passwd	varchar2(50) not null,
	stuName	varchar2(50) not null,
	birth	date not null,
	grade	number(1, 0) not null,
	kor		number(3, 0) not null,
	math	number(3, 0) not null,
	eng		number(3, 0) not null,
	clubId	varchar2(50)
);

create table club_tbl(
	clubId	varchar2(50),
	clubName	varchar2(50),
	teachar	varchar2(50),
	fee		number(10, 0)
);

create table skill_tbl(
	stuNo	number(5, 0),
	pass	number(1, 0),
	passDate Date
);

insert into student_tbl values(20001,'1111','박연미','2002/07/30',2,60,70,80,'A1001');
insert into student_tbl values(20002,'2222','김학생','2002/08/14',2,50,100,40,'A1003');
insert into student_tbl values(20003,'3333','박학생','2002/09/26',2,60,83,60,'A1001');
insert into student_tbl values(20004,'4444','이학생','2002/10/17',2,77,34,54,'A1004');
insert into student_tbl values(20005,'5555','김학생','2002/11/30',2,50,100,68,'A1002');
insert into student_tbl values(20006,'6666','곽학생','2002/12/05',2,100,100,100,NULL);
insert into student_tbl values(20007,'7777','강학생','2002/01/03',2,55,80,72,'A1003');
insert into student_tbl values(20008,'8888','복학생','2002/02/23',2,50,90,88,'A1001');
insert into student_tbl values(20009,'9999','임학생','2002/03/30',2,93,78,90,'A1004');
insert into student_tbl values(20010,'1010','한학생','2002/04/11',3,68,84,92,'A1001');
insert into student_tbl values(20011,'1111','규학생','2002/05/24',3,86,72,64,NULL);
insert into student_tbl values(20012,'1212','지학생','2002/06/15',3,84,77,43,'A1005');
insert into student_tbl values(20013,'1313','황학생','2002/07/02',3,70,88,93,'A1002');
insert into student_tbl values(20014,'1414','조학생','2002/08/05',3,67,97,40,'A1005');

insert into club_tbl values('A1001','베드민턴','김선생',0);
insert into club_tbl values('A1002','봉사활동','박선생',0);
insert into club_tbl values('A1003','비즈공예','황선생',15000);
insert into club_tbl values('A1004','로봇만들기','최선생',50000);
insert into club_tbl values('A1005','간편요리','용선생',30000);


insert into skill_tbl values(20002,0,null);
insert into skill_tbl values(20004,1,'2020/08/30');
insert into skill_tbl values(20006,1,'2020/07/30');
insert into skill_tbl values(20009,0,null);
insert into skill_tbl values(20013,1,'2020/09/30');
insert into skill_tbl values(20008,0,null);

-- 문제 1 학생 번호 최대값 출력
select max(stuNo) from student_tbl;

--문제 2 2학년 학생 중 '베드민턴' 동아리 제목과 학생이름이 출력되도록 하시오.
select club.clubName, stu.stuName from student_tbl stu join club_tbl club on stu.clubId = club.clubId where club.clubName = '베드민턴' and stu.grade = 2;
--이너 조인할 때 두가지 테이블이 공통 컬럼을 찾아라 테이블 1. 공통 컬럼 = 테이블2.공통컬럼


--문제 3 3학년 학생들 중 동아리가 없는 학생 목록 출력
select * from student_tbl where clubId is null and grade = 3;

--전체 학생 중에 동아리 가입한 학생
select * from student_tbl where clubId is not null;

--문제 4 기능반과 동아리 둘다 하고 있는 학생 목록 출력
select * from student_tbl stu, club_tbl club , skill_tbl skill where skill.stuNo = stu.stuNo and stu.clubId = club.clubId;

--문제 5번 학생 테이블에서 전체 국어영어 수학의 총점, 전체 평균을 구하시오.
select sum(kor + math + eng), round(avg(kor+eng+math) / 3, 0) from student_tbl;

--count sum avg 함수를 쓰게 되면 전체를 묶어서 반환 --> group by
--group by (count sum avg) 전체를 묶는 함수랑 같이 사용해서 각각의 그룹별로 총합, 평균, 갯수를 구할 수 있다.
select sum(kor + math + eng) as sum, round(avg(kor+eng+math) / 3, 0) as avg, stuName from student_tbl group by(stuName) order by sum desc;

-- 문제 1 3학년 중에 동아리가 있는 학생들의 동아리 제목 과 학생 성적을 출력 //join group by
select stu.stuNo, stu.stuName, round(avg((stu.kor + stu.math + stu.eng) / 3), 0) from student_tbl stu, club_tbl club WHERE stu.grade = 3 and stu.clubId = club.clubId group by(stu.stuNo, stu.stuName);

-- 문제 2 기능반 학생들 중 평가전 통과한 학생들의 이름과 평가전 통과날짜 //join
select stu.stuName, skill.passDate from student_tbl stu, skill_tbl skill where stu.stuNo = skill.stuNo and skill.pass = 1;

-- having
select stu.stuName, skill.passDate from student_tbl stu, skill_tbl skill where stu.stuNo = skill.stuNo and skill.pass = 1 group by (stuName, passDate) having round(avg((stu.kor + stu.math + stu.eng) / 3), 0) >= 60;

