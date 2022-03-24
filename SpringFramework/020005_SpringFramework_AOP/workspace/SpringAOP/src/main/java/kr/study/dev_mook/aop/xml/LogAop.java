package kr.study.dev_mook.aop.xml;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 공통 기능을 할 AOP Class
 * @author jeong
 */
public class LogAop {
	
	public Object loggerAop(ProceedingJoinPoint joinpoint) throws Throwable {
		
		String signatureStr = joinpoint.getSignature().toShortString();
		System.out.println(signatureStr + " is start.");
		long startTime = System.currentTimeMillis();
		
		try {
			Object obj = joinpoint.proceed();
			return obj;
		} finally {
			long endTime = System.currentTimeMillis();
			System.out.println(signatureStr + " is finished.");
			System.out.println(signatureStr + " 경과시간 : " + (endTime-startTime));
		}
	}
}
