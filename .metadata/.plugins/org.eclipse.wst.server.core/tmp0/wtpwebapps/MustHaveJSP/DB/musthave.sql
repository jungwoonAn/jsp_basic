-- member 테이블 생성
create table member(
    id varchar2(10) not null primary key,
    pass varchar2(10) not null,
    name varchar2(30) not null,
    regidate date default sysdate not null
);

-- board 테이블 생성
create table board(
    num number not null,
    title varchar2(200) not null,
    content varchar2(2000) not null,
    id varchar2(10) not null,
    postdate date default sysdate not null,
    visitcount number(6),
    constraint board_mem_fk foreign key(id) references member(id)
);

-- board 테이블 id컬럼에 외래키 설정 없이 테이블 만든 경우
-- 외래키 설정 변경
alter table board add
constraint board_mem_fk foreign key(id) references member(id);

-- board 테이블 num컬럼(일련번호) 시퀀스 객체 생성
create sequence seq_board_num
    increment by 1  -- 1씩 증가
    start with 1    -- 시작값 1
    minvalue 1      -- 최솟값 1
    nomaxvalue      -- 최댓값은 무한대
    nocycle         -- 순환하지 않음
    nocache;        -- 캐시 안 함
    
commit;

-- member테이블에 데이터 입력
insert into member(id, pass, name) values('test01', '1234', '테스트01');

-- board테이블에 데이터 입력
insert into board values(seq_board_num.nextval, '제목1','내용1','test01',sysdate,0);

commit;

-- 테이블에 입력된 데이터 조회
select * from member;

select * from board;