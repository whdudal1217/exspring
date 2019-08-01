package kr.ac.hit.myapp.comm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.ac.hit.myapp.member.memberVo;

//스프링의 인터셉터를 구현하기 위해서는 HandlerInterceptor 인터페이스를 구현.
//HandlerInterceptor를 직접 구현하면 필요없는 메소드까지 모두 구현해야 하기 때문에,
//이걸 미리 구현해둔 HandlerInterceptorAdapter를 통해 원하는 메소드만 골라서 구현한다.
//필요한 메서드만 재정의(Override)하여 구현
public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	//컨트롤러(@Controller)의 메서드(@RequestMapping)가 실행되기 전에 먼저 실행
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object arg2) throws Exception {
		System.out.println("LoginInterceptor:preHandle");
		
		HttpSession session = req.getSession(); //현재 요청의 세션객체 가져오기
		//로그인한 사용자인지 확인
		memberVo vo = (memberVo) session.getAttribute("loginUser");
		if(vo==null) {
			res.sendRedirect(req.getContextPath() + "/member/login.do");
			return false; //이후의 인터셉터나 컨트롤러는 실행하지 않는다
		}	
		return true;// 인터셉터 이후에 실행되는 컨트롤러가 있을텐데 그것을 실행할지 말지를 결정하는 return
	}
	
	/*//컨트롤러의 메서드가 실행된 후에 실행
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		System.out.println("LoginInterceptor:postHandle");
		
	}

	//컨트롤러가 결과를 출력 (뷰 렌더링(jsp출력) 까지 모두 끝난 후에 실행
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("LoginInterceptor:afterCompletion");
		
	}*/

}
