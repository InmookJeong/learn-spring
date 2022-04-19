# Transaction 학습하기

## Transaction 개념

- 논리적 단위로 한 부분의 작업이 완료되었다 하더라도 다른 부분이 작업이 완료되지 않을 경우 전체 취소되는 것
- 완료된 것 : Commit
- 취소된 것 : Rollback

- PlatformTransactionManager(Interface)
    - DataSourceTransactionManager를 이용하여 Transaction 처리
    - TransactionDefinition definition = new DefaultTransactionDefinition();
    - TransactionStatus status = transactionMamager.getTranaction(definition);
        - bean 등록
        ```
            <beans:bean name="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
                    <beans:property name="dataSource" ref="dataSource" />
            </beans:bean>
        ```
    - try~catch에서 
        - try 부분
            - 정상적으로 동작하였을 경우 transactionManager.commit(status);
        - catch 부분
            - 비정상적으로 동작하였을 경우 transactionManager.rollback(status);

- TransactionTemplate사용
    - d
        - bean 등록
        ```
            <beans:bean name="transactionTemplate" class="org.springframework.support.TransactionTemplate">
                    <beans:property name="transactionManager" ref="transactionManager" />
            </beans:bean>
        ```

## Transaction 전파 속성

- 2개 이상의 트랜잭션이 작동할 때, 기존의 트랜잭션에 참여하는 방법을 결정하는 속성
- PROPAGATION_REQUIRED(0)
    - Default : 전체처리
- PROPAGATION_SUPPORTS(1)
    - 기존 트랜잭션에 의존
- PROPAGATION_MANDATORY(2)
    - 트랜잭션에 꼭 포함되어야 함
        - 트랜잭션이 있는 곳에서 호출해야 함
- PROPAGATION_REQUIRES_NEW(3)
    - 각각 트랜잭션 처리(별도의 트랜잭션 처리)
- PROPAGATION_NOT_SUPPORTED(4)
    - 트랜잭션에 포함 하지 않음
        - 트랜잭션이 없는 것과 동일 함
- PROPAGATION_NEVER(5)
    - 트랜잭션에 절대 포함 하지 않음
        - 트랜잭션이 없는 곳에서 호출하면 에러 발생