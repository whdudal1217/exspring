package kr.ac.hit.myapp;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.junit.runner.Request;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.hit.myapp.bbs.BbsService;
import kr.ac.hit.myapp.bbs.BbsVo;
import kr.ac.hit.myapp.member.memberVo;

@Controller
public class TestController {

	@RequestMapping("/test/a.do") // /test/a.do로 요청이 오면 여기 실행 후 화면출력 할 jsp를 vies 밑에 만듭니다
	public String aaa() {
		return "test";
	}
	
	//@RequestMapping("/test/b.do") 
	public String bbb(int x, @RequestParam("y") int b, Map m) {//전송이 되어 오는 파라미터 이름과 동일한 이름의 변수를 인자로 선언하여 파라미터 
		//값을 받을 수 있다.
		//전송되어오는 파라미터 이름과 동일한 이름의 변수를 인자로 선언하여 파라미터 값을 받을 수 있다. (변수타입에 맞게 자동으로 형변환)
		System.out.println(x);
		System.out.println(b);
		//그냥 x+y 하면 둘 다 문자열이라 안 되지만 스프링은 int로 바꿔서 가능하다
		int sum = x + b;
		
		//컨트롤러 메서드의 인자로 Map, Model, ModelMap 타입의 변수를 선언하고,
		//해당 Map, Model, ModelMap 변수에 데이터를 저장하여 jsp에 전달 가능
		//jsp 파일로 저장할 데이터를 담는 모델에 "total"이라는 이름으로 sum을 저장, 모델에 데이터를 담는 명령어는 .put
		//jsp 파일에서 ${total}표현으로 sum 값 사용 가능
		m.put("total", sum);
		
		return "result";
	}
	
	//파라미터 값을 받기 위해 사용한 객체를 jsp에서 사용하고 싶으면,
	//@ModelAttribute("PointVo")를 붙이고 jsp에서 사용할 이름을 지정
	//@ModelAttribute() 생략시 타입이름(첫 글자만 소문자로 변환)으로 모델에 자동 저장.
	@RequestMapping("/test/b.do") 
	public String ccc(//@ModelAttribute("PointVo") 
			PointVo vo, Map m ) {
		//사용자가 정의한 클래스 타입의 인자를 선언하면.
		//스프링이 메서드를 실행할 때 해당 클래스의 객체를 생성하고,
		//객체의 속성(변수)명과 동일한 이름의 파라미터 값을 저장하여 전달해준다.
		
		int sum = vo.getX() + vo.getY();
		
		//jsp 파일로 저장할 데이터를 담는 모델에 "total"이라는 이름으로 sum을 저장, 모델에 데이터를 담는 명령어는 .put
		//jsp 파일에서 ${total}표현으로 sum 값 사용 가능
		m.put("total", sum);
		
		return "result";
	}
	@Resource
	private BbsService bbsService;
	
	@RequestMapping("/test/auto.do")
	public String auto(HttpSession session) {
		BbsVo vo = new BbsVo();
	
		for(int i = 0; i<50;i++) {
			vo.setBbsTitle("제목" + System.nanoTime());
			vo.setBbsContent("");
			memberVo loginUser = (memberVo) session.getAttribute("loginUser");
			bbsService.insert(vo);
		}
		return "redirect:/";
	}
}


