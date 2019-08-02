package kr.ac.hit.myapp.bbs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.ac.hit.myapp.comm.PageInfo;
import kr.ac.hit.myapp.comm.SearchInfo;
import kr.ac.hit.myapp.member.memberVo;

@Controller
public class BbsController {
	
	@Resource //inject, autowired
	private BbsService bbsService;
	
	@RequestMapping(value = "/bbs/add.do", method=RequestMethod.GET)
	public String addForm() {
		return "bbs/bbsAdd";
	}
	
	@RequestMapping(value = "/bbs/add.do", method=RequestMethod.POST)
	public String add(BbsVo vo, HttpSession session) { //전송해온 값을 받아와서 넣는 역할
		
		memberVo loginUser = (memberVo) session.getAttribute("loginUser"); //세션 꺼내옴
		vo.setBbsWriter(loginUser.getMemId()); //로그인한 사용자 아이디로 글쓴이를 강제설정
		int num = bbsService.insert(vo);
		return "redirect:/bbs/list.do";
	}
	
	//컨트롤러 메서드의 인자로 사용자가 정의한 클래스의 객체를 받는 경우, 
	//그 객체를 뷰(jsp)에서 사용하기 위해 모델에 저장하는 방법
	//1. 모델(Map,Model,ModelMap 타입)에 이름을 부여하여 저장
	//2. 인자 앞에 @ModelAttribute("모델이름")을 붙이면 자동 저장
	//3. @ModelAttribute("모델이름")을 생략해도 모델에 자동 저장(이름은 클래스 이름 첫글자만 소문자로 바뀌어서)
	@RequestMapping(value = "/bbs/list.do")
	public String list(Map model /*2번 방식,@ModelAttribute("searchInfo")SearchInfo info*/,  /*3번 방식*/ SearchInfo info	) { 
		int cnt = bbsService.selectCount(info);
		info.setTotalRecordCount(cnt);
		info.makePageHtml();
		// 1번방식		model.put("pageInfo", info); //이걸 생략해도 괜찮긴 하지만 넣읍시다
		List <BbsVo> list = bbsService.selectList(info);

		model.put("bbsList", list); //jsp에서 사용할 이름
		return "bbs/bbsList";
	}
	
	@RequestMapping(value = "/bbs/edit.do", method=RequestMethod.GET)
	public String editForm(int bbsNo, Map model) {
		BbsVo vo = bbsService.select(bbsNo);
		for(AttachVo a : vo.getAttList()) {
			System.out.println(a.getAttOrgName());
		}
		model.put("bbsVo", vo);
		return "bbs/bbsEdit";
	}
	
	@RequestMapping(value = "/bbs/edit.do", method=RequestMethod.POST )
	public String edit(BbsVo vo, HttpSession session) {
		memberVo loginUser = (memberVo) session.getAttribute("loginUser");
		//로그인한 사용자와 수정하려는 사람의 id가 다르면 예외를 발생시킨다.
		vo.setBbsWriter(loginUser.getMemId());
		
		//DB의 값 가져오는 두가지 방법
		//1. 수정하려고 하는 게시글 정보를 DB에서 SELECT 해와서, 로그인 사용자 아이디와 게시글 작성자 아이디와 비교해서 동일하면
		//UPDATE실행
		//BbsVo bvo = bbsService.select(vo.getBbsNo());
		//if (loginUser.getMemId().equals(bvo.getBbsWriter()) == false)
		
		//2. 로그인한 사용자의 아이디 정보를 UPDATE 할 때 함께 전달하여 로그인 사용자 아이디와 게시글 작성자 아이디가 같은 
		//경우에만 UPDATE 되도록
		int num = bbsService.update(vo);
		return "redirect:/bbs/list.do";
	}
	
	@RequestMapping(value = "/bbs/del.do", method=RequestMethod.GET )
	public String delete(BbsVo vo, HttpSession session) {
		
		memberVo loginUser = (memberVo) session.getAttribute("loginUser");
	
		vo.setBbsWriter(loginUser.getMemId());
		
		int num = bbsService.delete(vo);
		return "redirect:/bbs/list.do";
	}
	
	//요청을 받으면 실행되는 컨트롤러의 메서드에서 HttpServletResponse를 인자로 받고 
	//반환타입을 void로 설정하면 스프링은 해당 메서드에서 직접 응답을 처리할 것으로 판단하여, 
	//뷰이름을 반환받을 것이라고 생각하지 않는다.
	@RequestMapping("bbs/down.do")
	public void download(int attNo, HttpServletResponse resp, HttpServletRequest req) throws  IOException {
		//DB에서 요청받은 번호의 첨부파일 정보를 가져오기
		AttachVo vo = bbsService.selectAttach(attNo);
		//해당 첨부파일을 파일시스템(하드디스크)에서 읽어서 응답으로 전송
		File f = bbsService.getAttachFile(vo);
		
		//한글 파일명을 위한 인코딩 설정
		
		String userAgent = req.getHeader("User-Agent"); //요청을 보낸 브라우저 종류를 판단하기 위한 "User-Agent" 헤더값
		//String fileName1 = URLEncoder.encode(vo.getAttOrgName(),"UTF-8").replaceAll("\\+", "%20");
		String fileName=new String(vo.getAttOrgName().getBytes("UTF-8"),"ISO-8859-1");
		if(userAgent.contains("MSIE")||userAgent.contains("Trident")	) {
			fileName=URLEncoder.encode(vo.getAttOrgName(),"UTF").replaceAll("\\+", "%");
		}
		
		resp.setContentType("application/octet-stream"); //응답 데이터의 종류
		resp.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		resp.setContentLength((int)f.length());
		
		//파일 f의 내용을 읽어서, 응답객체(resp)의 출력스트림에 쓰기
		// == 파일 내용을 응답으로 전송
		FileCopyUtils.copy(new FileInputStream(f), resp.getOutputStream());
		
	}
	
}