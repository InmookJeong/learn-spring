package kr.study.dev_mook.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/* Component - Spring Bean으로 등록 */
/* AOP 기능을 하는 클래스임을 선언 */
@Component
@Aspect
public class SampleAdvice {
	
	private static final Logger logger = LoggerFactory.getLogger(SampleAdvice.class);
	
	/*
	 * Target Method가 실행되기 전에 @Before Method를 먼저 실행
	 * () 안에 execution으로 시작하는 구문이 Pointcut을 지정하는 문법
	 *   - kr.study.dev_mook.service.MessageService로 시작하는 모든 클래스의 모든 메소드가 동작하기 전에 해당 AOP 메소드 실행
	 */
	@Before("execution(* kr.study.dev_mook.service..MessageService*.*(..))")
	public void startLog(JoinPoint joinPoint) {
		System.out.println("########## HERE.....");
		logger.info("-------------------------");
		logger.info("##### Call Before Advice");
		logger.info(Arrays.toString(joinPoint.getArgs()));
		logger.info("-------------------------");
	}
	
	@Around("execution(* kr.study.dev_mook.service..MessageService*.*(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
		long startTime = System.currentTimeMillis();
		// 전달되는 파라미터 확인하기
		logger.info(Arrays.toString(pjp.getArgs()));
		
		Object result = pjp.proceed();
		
		long endTime = System.currentTimeMillis();
		logger.info(pjp.getSignature().getName() + " : " + (endTime-startTime));
		logger.info("=====================================================");
		
		return result;
	}
	
}
