package kr.study.dev_mook.aop.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

/**
 * 공통 기능을 수행할 Class라는 것을 명시하기 위해 @Aspect 작성
 * 	- XML을 이용할 경우 applicationCTX.xml에 AOP 적용 및 동작에 대해 작성한 후 관련 Class, Method를 생성해야 함
 * 		- 2중으로 작성
 * 	- Annotation을 이용할 경우 아래와 같이 Class, Method에 Annotation만 작성하면 되므로 한 번 작성을 통해 AOP 활용 가능
 * @author jeong
 *
 */
@Aspect
public class LogAop_2 {
	
	/*
	 * Aspect는 Pointcut 역할을 하는 Method를 가지고 있어야 하므로
	 * 해당 Method에 @Pointcut 명시
	 * 	- Pointcut의 속성에는 적용 범위(대상) 작성
	 */
	@Pointcut("within(kr.study.dev_mook.model.*)")
	private void pointcutMethod() {
	}
	
	/*
	 * Advice 종류 작성; @Around
	 * @Around가 적용될 pointcut-ref를 속성에 작성
	 */
	@Around("pointcutMethod()")
	public Object loggerAop(ProceedingJoinPoint joinpoint) throws Throwable {
		
		String signatureStr = joinpoint.getSignature().toShortString();
		System.out.println("[Annotation AOP] " + signatureStr + " is start.");
		long startTime = System.currentTimeMillis();
		
		try {
			Object obj = joinpoint.proceed();
			return obj;
		} finally {
			long endTime = System.currentTimeMillis();
			System.out.println("[Annotation AOP] " + signatureStr + " is finished.");
			System.out.println("[Annotation AOP] " + signatureStr + " 경과시간 : " + (endTime-startTime));
		}
	}
	
	/*
	 * Pointcut을 이용하지 않고 아래와 같이 바로 Advice를 작성할 수 있음
	 * 작성 시 Advice 속성에 적용 범위(대상)을 작성
	 */
	@Before("within(kr.study.dev_mook.model.*)")
	public void beforeAdvice() {
		System.out.println("[Annotation AOP] Call beforeAdvice()~!");
	}

}
