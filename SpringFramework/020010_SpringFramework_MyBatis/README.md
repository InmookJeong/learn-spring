# MyBatis

## MyBatis 사용을 위한 기본 설정
- pom.xml에 MyBatis를 사용하기 위한 Dependency 추가
    - 버전은 자신의 프로젝트에 맞게 알아서 설정
```
    <!-- MyBatis 사용을 위한 Dependency 추가 -->
    <!-- https://mvnrepository.com/artifact/org.springframework/spring-jdbc -->
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-jdbc</artifactId>
        <version>4.1.4.RELEASE</version>
    </dependency>
    
    <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>3.2.8</version>
    </dependency>
    
    <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis-spring -->
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis-spring</artifactId>
        <version>1.2.2</version>
    </dependency>
```

## MyBatis를 이용을 위한 스프링 설정파일
```
    <beans:bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
        <beans:property name="dataSource" ref="dataSource" />
        <beans:property name="mapperLocations" value="classpath:kr/study/dev_mook/dao/mapper/*.xml" />
    </beans:bean>

    <beans:bean id="sqlSession" class="org.mybatis.spring.SqlSessionTemplate">
        <beans:constructor-arg index="0" ref="sqlSessionFactory"/ >
    </beans:bean>
```

## SqlSession Autowired
```
    @Autowired
    private SqlSession sqlSession;
```

## Mapper XML 생성
1. xml 파일 생성
2. mybatis 곤련 doctype 복사 붙여넣기 후 쿼리 작성

## MyBatis를 이용한 글작성 및 삭제
```
    mapper에서 Parameter 앞에 #, $ 붙일 수 있는데...
    # : String이나 Int 등의 데이터를 컬럼의 타입에 맞춰서 변환
    $ : String이 오면 그대로 데이터베이스에 바로 저장
```