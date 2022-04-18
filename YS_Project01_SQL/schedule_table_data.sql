--일정 관리 프로그램

--1. 테이블 생성 
create table schedule (
    title       varchar2(50 char) default '내 일정',
    
    sdate       DATE default sysdate,
                
    memo        varchar2(300 char) default '.',
    
    no          number
                primary key
);


commit;

--2. 작동 방법
insert into schedule (title, memo) values('test', 'test memo');
select * from schedule;
select * from schedule where no = ?; -- 스케쥴 번호로 검색
update schedule set title = ?, memo = ?, sdate = sysdate where no = ?;
delete from schedule where no = ?;



commit;
rollback;



-- 3.테이블 삭제
drop table schedule12;


-- 4. 테스트
insert into schedule (sdate)
values ('2007/09/09');

insert into schedule
values ('어버이날', '2021/05/08', '부산');

select title, to_char(sdate, 'yyyy/mm/dd') as SDATE, memo from schedule
where to_char(sdate, 'yyyy/mm/dd') = '2021/12/20';

delete from schedule
where to_char(sdate, 'yyyy/mm/dd') = '2021/12/20';

insert into schedule
values (56444, '2021/12/20', 'bla');
insert into schedule
values ('yahoo', '2021/12/23', 'test');
insert into schedule
values ('','2021/12/20','');

insert into schedule
values ('test', '2021/12/20', '');
insert into schedule
values (,,);

delete from schedule
where to_char(sdate, 'yyyy/mm/dd') = '2021/12/22';

delete from schedule
where title = '내 일정';

delete from schedule
where memo = '.';

delete from schedule
where memo = 'null';

select no from schedule 
where sdate = '2021/12/22';

TRUNCATE table schedule12;    --데이터 잘라내기




