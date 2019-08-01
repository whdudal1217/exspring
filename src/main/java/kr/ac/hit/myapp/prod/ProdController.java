package kr.ac.hit.myapp.prod;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//requestMapping을 컨트롤러 클래스 최상위에 올려놓으면 안 쪽의 모든 메소드들의 경로를 같게 지정할 수 있습니다.
//이 컨트롤러 클래스의 메서드들에 공통적인 경로를 클래스에 지정

@Controller
@RequestMapping("/prod/")
public class ProdController {
	
	@Resource //inject, autowired
	private ProdService prodService;
	
	@RequestMapping(value = "add.do", method=RequestMethod.GET)
	public String addForm() {
		return "prod/prodAdd";
	}
	
	@RequestMapping(value = "add.do", method=RequestMethod.POST)
	public String add(ProdVo vo) { //전송해온 값을 받아와서 넣는 역할
		int num = prodService.insert(vo);
		return "redirect:/prod/list.do";
	}
	
	@RequestMapping(value = "list.do")
	public String list(Map model) { 
		List <ProdVo> list = prodService.selectList();
		model.put("prodList", list); //jsp에서 사용할 이름,
		return "prod/prodList";
	}
	
	@RequestMapping(value = "edit.do", method=RequestMethod.GET)
	public String editForm(int prodNo, Map model) {
		ProdVo vo = prodService.select(prodNo);
		model.put("prodVo", vo);
		return "prod/prodEdit";
	}
	
	@RequestMapping(value = "edit.do", method=RequestMethod.POST )
	public String edit(ProdVo vo) {
		int num = prodService.update(vo);
		return "redirect:/prod/list.do";
	}
	
	@RequestMapping(value = "del.do", method=RequestMethod.GET )
	public String delete(int prodNo) {
		int num = prodService.delete(prodNo);
		return "redirect:/prod/list.do";
	}
	
}