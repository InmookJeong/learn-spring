# Security

## 보안 관련 라이브러리
```
    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-config</artifactId>
        <version>3.2.5.RELEASE</version>
    </dependency>
    
    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-core</artifactId>
        <version>3.2.5.RELEASE</version>
    </dependency>
    
    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-web</artifactId>
        <version>3.2.5.RELEASE</version>
    </dependency>
    
    <dependency>
        <groupId>org.springframework.security</groupId>
        <artifactId>spring-security-taglibs</artifactId>
        <version>3.2.4.RELEASE</version>
    </dependency>
```
## 보안 관련 설정 파일 만들기
- 위치 : /WEB-INF/spring/appServlet/security-context.xml
- web.xml에 contextLocation 추가
```
    <context-param>
		<param-name>contextConfigLocation</param-name>
		<param-value>
			/WEB-INF/spring/root-context.xml
			/WEB-INF/spring/appServlet/security-context.xml
		</param-value>
	</context-param>
```

## In-Memory 인증
1. 설정 파일 작성하기
```
    <!-- 해당 URL에 대해 어떤 사용자가 접근하도록 할 것인지 설정 -->
    <security:http auto-config="true">
		<security:intercept-url pattern="/login.html*" access="ROLE_USER"/>
		<security:intercept-url pattern="/welcome.html*" access="ROLE_ADMIN"/>
	</security:http>

    <!-- 이름이 user이고 password가 123인 사용자는 ROLE_USER에 접근 가능 -->
    <!-- 이름이 admin이고 password가 123인 사용자는 ROLE_USER과 ROLE_ADMIN에 접근 가능 -->
    <security:authentication-manager>
		<security:authentication-provider>
			<security:user-service>
				<security:user name="user" password="123" authorities="ROLE_USER"/>
				<security:user name="admin" password="123" authorities="ROLE_ADMIN,ROLE_USER"/>
			</security:user-service>
		</security:authentication-provider>
	</security:authentication-manager>
```
2. web.xml에 filter 작성하기
```
    <!-- Security 적용을 위한 Filter -->
	<filter>
		<filter-name>springSecurityFilterChain</filter-name>
		<filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
	</filter>
	<filter-mapping>
		<filter-name>springSecurityFilterChain</filter-name>
		<url-pattern>/*</url-pattern>
	</filter-mapping>
```

## 로그인 페이지 생성
```
    <!-- security-context.xml -->
    <!-- login-page 추가 -->
    <security:http auto-config="true">
		<security:form-login login-page="/loginForm.html"/>
		<security:intercept-url pattern="/login.html*" access="ROLE_USER"/>
		<security:intercept-url pattern="/welcome.html*" access="ROLE_ADMIN"/>
	</security:http>
```
- 참고로 security-context.xml의 user name, password를 사용하기 위해
- form의 action에는 "j_spring_security_check"를
- ID input name에는 "j_username"을
- PW input name에는 "j_password"를 입력해준다.
    -  즉 Spring security에서 제공하는 로그인 프로세스 이용

## 로그인, 로그아웃 상태 표시
- 로그인 실패
    ```
        <!-- authentication-failure-url를 통해 로그인 실패 시 리다이렉트할 URL 추가 -->
        <security:http auto-config="true">
            <security:form-login login-page="/loginForm.html"
                    authentication-failure-url="/loginForm.html?ng"/>
            <security:intercept-url pattern="/login.html*" access="ROLE_USER"/>
            <security:intercept-url pattern="/welcome.html*" access="ROLE_ADMIN"/>
        </security:http>
    ```

- 로그아웃
    ```
        ${empty pageContext.request.userPrincipal} 이면 로그아웃 상태
        
        <!-- 아래 태그를 통해 로그아웃 기능 구현 가능 -->
        <a href="url/j_spring_security_logout">로그아웃</a>
    ```

## 보안 관련 tablibs 사용
- pom.xml에 taglibs Dependency 추가
- jsp에서 <%@ taglib url="http://www.springframework.org/security/tags" prifix="s" %> 선언해서 사용 가능
```
    <s:authentication property="name" />으로 사용자 아이디 출력 가능

    <!-- 로그인 로그아웃 상태에 대해서도 아래 코드와 같이 작성 가능 -->
    <s:authorize ifAnyGranted="ROLE_USER">
        is Log-in
    </s:authorize>

    <s:authorize ifNotGranted="ROLE_USER">
        is Log-out
    </s:authorize>
```