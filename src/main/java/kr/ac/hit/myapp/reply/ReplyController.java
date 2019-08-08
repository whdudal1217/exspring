package kr.ac.hit.myapp.reply;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.ac.hit.myapp.bbs.BbsVo;
import kr.ac.hit.myapp.member.memberVo;

@Controller
public class ReplyController {
	@Resource
	private ReplyService replyService;
	/*
	@RequestMapping(value="/reply/add.do", method=RequestMethod.POST)
	public String add(ReplyVo vo, HttpSession session) { //전송해온 값을 받아와서 넣는 역할
		
		memberVo loginUser = (memberVo) session.getAttribute("loginUser"); //세션 꺼내옴
		vo.setRepWriter(loginUser.getMemId()); //로그인한 사용자 아이디로 글쓴이를 강제설정
		int num = replyService.insert(vo);
		return "";
	}*/
	@ResponseBody //이 메서드의 반환값이 뷰(jsp)이름이 아니라, 반환값 자체가 응답 데이터로 전송
	@RequestMapping(value="/reply/add.do")
	public Map<String, Object> add(ReplyVo vo, HttpSession session) {
		memberVo loginUser = (memberVo) session.getAttribute("loginUser");
		vo.setRepWriter(loginUser.getMemId());
		int num = replyService.insert(vo);
		Map<String, Object> resultMap = new HashMap<String,Object>();
		resultMap.put("result", num);
		return resultMap; //{"result" : 1} java<-> json 을 해주는 라이브러리 jackson과 gson
	}
	
	@RequestMapping(value="/reply/list.do")
	@ResponseBody
	public List<ReplyVo> list(String repBbsNo) {
		List<ReplyVo> list = replyService.selectList(repBbsNo);
		return list;
	}
	@ResponseBody
	@RequestMapping(value="/reply/del.do")
	public Map<String, Object> del(ReplyVo vo, HttpSession session) {
		memberVo loginUser = (memberVo) session.getAttribute("loginUser");
		vo.setRepWriter(loginUser.getMemId());
		int num = replyService.delete(vo);
		Map<String, Object> resultMap = new HashMap<String,Object>();
		resultMap.put("result", num);
		return resultMap; //{"result" : 1} java<-> json 을 해주는 라이브러리 jackson과 gson
	}
}
