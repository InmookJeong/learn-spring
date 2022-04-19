-- mvc_board Table 생성
create table mvc_board (
    bId BIGINT(4) PRIMARY KEY,
    bName VARCHAR(20),
    bTitle VARCHAR(100),
    bContent VARCHAR(300),
    bDate DATETIME DEFAULT NOW(),
    bHit BIGINT(4) DEFAULT 0,
    bGroup BIGINT(4),
    bStep BIGINT(4),
    bIndent BIGINT(4)
);
-- DB ENGINE : InnoDB
-- DB 엔진의 종류와 그 차이점에 대해 학습하자

ALTER TABLE mvc_board COMMENT = '인프런 강좌를 학습하기 위한 MVC_BOARD 테이블';

ALTER TABLE mvc_board MODIFY bHit BIGINT(4) COMMENT '조회수';

ALTER TABLE mvc_board MODIFY bGroup BIGINT(4) COMMENT '최상위 게시글을 기준으로 Group 구성(하위 댓글 포함)';

ALTER TABLE mvc_board MODIFY bStep BIGINT(4) COMMENT '댓글의 순서';

ALTER TABLE mvc_board MODIFY bIndent BIGINT(4) COMMENT '들여쓰기 - 댓글인지 댓글의 댓글인지 구분';

commit;