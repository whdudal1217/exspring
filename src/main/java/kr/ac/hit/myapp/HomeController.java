package kr.ac.hit.myapp;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
//스프링 웹 앱에서 특정 주소로 요청을 보내면 실행될 코드를 담고 있는 클래스 
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	
	//"/" 주소로 get방식의 요청이 오면 이 메소드를 실행, 메소드를 여기에 추가 할 때마다 리퀘스트매핑을 붙여줘야함
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		//Locale = 현재 애플리케이션의 지역 정보(언어, 국가)를 받을 수 있다
		/*logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);*/
		//스프링에서 컨트롤러의 데이터를 jsp로 전달하기 위해서는
		//컨트롤러 메서드의 파라미터로 Map, Model, ModelMap 타입의 객체를 받은 후,
		//그 객체에 데이터를 원하는 이름을 저장하면, jsp에서 그 이름으로 저장했던 데이터를 꺼내서 사용 가능
		//model.addAttribute("serverTime", formattedDate );
		//모델 객체에 "servetTime"이라는 이름으로 formattedDate를 저장
		//jsp에서는 ${serverTime}이라는 표현으로 꺼내서 사용 가능
		
		return "redirect:member/list.do";
		//return "home";
		//☆★ 스프링 컨트롤러의 메서드에서 문자열(string)을 반환하면, 스프링은 이것을 뷰이름(jsp_더 자세한 부분이 있지만 jsp라고 생각)으로 해석
		//foward를 스프링이 알아서 해줌 즉 return에 jsp파일이름만 적어주면 알아서 파일을 뷰로 넘겨줌~
		//스프링 설정 파일(servlet-context.xml)에 등록된InternalResourceViewResolver에 설정 접두어(prefix)와 접미어(suffix)를 반환된 뷰이름 앞 뒤로
		//붙여서 만든 jsp파일로 이동(forward)
	}
	
}
