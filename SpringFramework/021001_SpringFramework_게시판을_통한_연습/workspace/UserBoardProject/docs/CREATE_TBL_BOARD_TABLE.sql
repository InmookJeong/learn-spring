CREATE TABLE tbl_board (
	bno int not null auto_increment,
    title varchar(200) not null,
    content text null,
    writer varchar(50) not null,
    regdate timestamp not null default now(),
    viewCnt int default 0,
    primary key (bno)
);

commit;

-- 댓글의 숫자를 의미하는 컬럼 추가
ALTER TABLE tbl_board ADD COLUMN replycnt int default 0;