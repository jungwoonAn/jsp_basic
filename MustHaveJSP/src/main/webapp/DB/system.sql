-- 테이블 스페이스 조회
select tablespace_name, status, contents from dba_tablespaces;

-- 테이블 스페이스 가용 공간 조회
select tablespace_name, sum(bytes), max(bytes) from dba_free_space group by tablespace_name;

-- musthave계정이 사용하는 테이블 스페이스 조회
select username, default_tablespace from dba_users where username in upper('musthave');

-- musthave계정이 USERS 테이블 스페이스에 데이터를 입력할 수 있도록 5M 용량을 할당
alter user musthave quota 5m on users;