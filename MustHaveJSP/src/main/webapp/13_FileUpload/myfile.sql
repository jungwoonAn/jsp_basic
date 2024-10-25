-- myfile 테이블 생성
create table myfile (
	idx number primary key,
	title varchar2(200) not null,
	cate varchar2(30),
	ofile varchar2(100) not null,
	sfile varchar2(30) not null,
	postdate date default sysdate not null
);

-- myfile 테이블 num컬럼(일련번호) 시퀀스 객체 생성
create sequence seq_myfile_num;
    
commit;

-- myfile 테이블 조회
select * from myfile;