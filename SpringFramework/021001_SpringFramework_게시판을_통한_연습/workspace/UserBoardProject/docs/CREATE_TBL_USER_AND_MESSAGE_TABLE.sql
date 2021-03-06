CREATE TABLE tbl_user (
	uid varchar(50) NOT NULL,
    upw varchar(50) NOT NULL,
    uname varchar(100) NOT NULL,
    upoint int NOT NULL DEFAULT 0,
    primary key(uid)
);

CREATE TABLE tbl_message (
	mid int NOT NULL auto_increment,
    targetid varchar(50) NOT NULL,
    sender varchar(50) NOT NULL,
    message text NOT NULL,
    opendate timestamp,
    senddate timestamp NOT NULL DEFAULT now(),
    primary key(mid)
);

alter table tbl_message add constraint fk_usertarget
foreign key (targetid) references tbl_user(uid);

alter table tbl_message add constraint fk_usersender
foreign key (targetid) references tbl_user(uid);

commit;

-- 자동로그인 구현을 위한 테이블 변경
alter table tbl_user add column sessionkey varchar(50) not null default 'none';

alter table tbl_user add column sessionlimit timestamp;