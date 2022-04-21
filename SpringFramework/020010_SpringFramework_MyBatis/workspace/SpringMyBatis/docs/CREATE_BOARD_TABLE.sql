-- board Table 생성
create table board (
    mId BIGINT(10) PRIMARY KEY comment '숫자(시퀀스) 형식의 아이디',
    mWriter VARCHAR(100) unique not null comment '작성자',
    mContent varchar(300) not null comment '게시글 내용'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Springframework MyBatis 강의 진행을 위한 게시판 테이블';

commit;