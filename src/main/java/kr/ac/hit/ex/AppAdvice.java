package kr.ac.hit.ex;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//중간에 끼워넣을 코드 작성하려면 스프링이 이 아이를 알고 있어야함
@Aspect //스프링 AOP 관련 설정 및 코드를 담고 있는 객체임을 표시
@Component //이 객체를 스프링에 등록
public class AppAdvice {
	
	//kr.ac.hit.ex 패키지의 모든 클래스의 모든 메소드(파라미터상관없음)의 실행 전에 이 메소드를 실행
	@Before("execution(* kr.ac.hit.ex.*.*(..))") //aspectj 표현방식
	public void beforeMethod(JoinPoint joinPoint) { 
		//이 메소드가 끼어들어가서 실행되는 지점에 대한 정보를 joinPoint 인자가 갖고있음
	System.out.println("메소드 시작 : " + joinPoint.getSignature().getName());//실행중인 메소드 이름
	}

	//kr.ac.hit.ex 패키지의 모든 클래스의 모든 메소드(파라미터상관없음)의 실행 전에 이 메소드를 실행
	@After("execution(* kr.ac.hit.ex.*.*(..))") //aspectj 표현방식
	public void afterMethod(JoinPoint joinPoint) { 
		//이 메소드가 끼어들어가서 실행되는 지점에 대한 정보를 joinPoint 인자가 갖고있음
	System.out.println("메소드 끝 : " + joinPoint.getSignature().getName());//실행중인 메소드 이름
}
}
