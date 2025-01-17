select * from member;
select * from board;

-- board테이블 데이터 추가
insert into board values(seq_board_num.nextval, '지금은 봄입니다', '봄의 왈츠', 'test01', sysdate, 0);
insert into board values(seq_board_num.nextval, '지금은 여름입니다', '여름 향기', 'test01', sysdate, 0);
insert into board values(seq_board_num.nextval, '지금은 가을입니다', '가을 동화', 'test01', sysdate, 0);
insert into board values(seq_board_num.nextval, '지금은 겨울입니다', '겨울 연가', 'test01', sysdate, 0);

-- 모든 게시물 수
select count(*) from board;

-- 검색 조회
select count(*) from board where title like '%지금은%';

-- 모든 게시물 조회
select * from board;

-- 검색 조회
select * from board where title like '%지금은%';

-- 선택한 글 아이디 일치하면 조회
select B.*, M.name from member M, board B where M.id=B.id and num=7;

-- rownum
select * from member;
select id, pass, rownum from member;

select * from (
	select Tb.*, rownum rNum from (
		select * from board order by num desc
	)Tb
) where rNum between 1 and 10;

select * from (
	select Tb.*, rownum rNum from (
		select * from board where title like '%페이징 처리%' order by num desc
	)Tb
) where rNum between 11 and 20;
