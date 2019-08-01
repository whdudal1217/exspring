package kr.ac.hit.ex;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppMain {
	public static void main(String[] args) {
		/*
		 * App app = new App();
		 * 
		 * app.act();
		 */

		// ApplicationContext == BeanContainer == 스프링의 실체 == 설정파일에 등록된 자바 클래스 객체를 생성하여
		// 보관하는 컨테이너 생성
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/kr/ac/hit/ex/context.xml");
		// 스프링(ApplicationContext)에 "a"란 이름으로 등록되어 있는 객체를 가져오기
		App app = (App) context.getBean("a");
		app.act();

	}
}
