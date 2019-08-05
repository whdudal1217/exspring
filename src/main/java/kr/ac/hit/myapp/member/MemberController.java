package kr.ac.hit.myapp.member;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.ac.hit.myapp.comm.MemSearchInfo;

@Controller
public class MemberController {

	// @Autowired @Inject @Resource 중 하나를 사용하면 스프링에 알아서 memberService가 객체로 등록 됨

	@Resource
	private MemberService memberService;

	// memAdd.jsp 를 보여주기 위한 클래스!
	@RequestMapping(value = "/member/add.do", method = RequestMethod.GET)
	//스프링 폼 태그(form:form)의 modelAttribute 사용을 위해서 빈 memberVo 객체를 모델에 저장하기 위해서 인자로 설정합니다.﻿
	public String addForm(@ModelAttribute("memberVo")memberVo vo) {
		return "member/memAdd"; // WEB-INF/views/member.memAdd.jsp 이 경로로 들어가야함 WEB-INF/views/이건 자동으로 붙여줌
	}

	@RequestMapping(value = "/member/add.do", method = RequestMethod.POST)
	public String add(@Valid @ModelAttribute("memberVo") memberVo vo, BindingResult result) {
		//@Valid를 붙이면 해당 객체 내부에 적어놓은 조건에 따라서
		//객체의 변수(속성) 값들을 검사하고, 그 결과를 다음 인자인 BindingResult 에 저장하여 전달
		if(result.hasErrors()) { //검증 결과 에러가 있는지 확인
			return "member/memAdd"; //에러가 있으면 회원가입 화면을 다시 출력함, 애드폼으로~
		}
		// 파라미터로 넘어온 값들을 받아서 데이터베이스에 추가(insert)
		memberService.insert(vo);
		//jsp 파일로 이동하는 대신 redirect: 뒤에 지정한 주소로 이동하라는 응답을 전송
		return "redirect:/member/list.do ";
	}
	
	
	@RequestMapping(value="/member/list.do" )
	public String list(Map modelMap, MemSearchInfo info) {
		//데이터베이스에서 회원목록을 조회하고
		int cnt = memberService.selectCount(info);
		info.setTotalRecordCount(cnt);
		info.makePageHtml();
		List<memberVo> list = memberService.selectList(info);
		modelMap.put("memberList", list);
		return "member/memList";
	}
	
	@RequestMapping(value = "/member/edit.do", method = RequestMethod.GET)
	public String editForm(String memId, Map modelMap, HttpSession session) {
		//db에서 꺼내오는 작업
		//로그인한 사용자(session)와 상세정보를 조회하는 사용자(memId)와 일치하는지 확인
		//로그인한 사용자인지 확인
		memberVo loginUser = (memberVo) session.getAttribute("loginUser");
		
		if(loginUser.getMemId().equals(memId) == false) {//로그인한 사용자와 조회대상 사용자가 다를때
			//return "redirect:/member/login.do";
			throw new RuntimeException("권한이 없습니다."); 
		}
		memberVo vo = memberService.select(memId);
		modelMap.put("memberVo", vo);
		return "member/memEdit"; 
	}

	@RequestMapping(value = "/member/edit.do", method = RequestMethod.POST)
	public String edit(memberVo vo, HttpSession session) {
		memberVo loginUser = (memberVo) session.getAttribute("loginUser");//로그인 한 사용자 가져옴

		if (loginUser.getMemId().equals(vo.getMemId()) == false) {// 로그인한 사용자와 조회대상 사용자가 다를때
			// return "redirect:/member/login.do";
			throw new RuntimeException("권한이 없습니다.");
		}
		int num = memberService.update(vo);
		return "redirect:/member/list.do";
	}
	
	@RequestMapping(value = "/member/del.do", method = RequestMethod.GET)
	public String delete(String memId, HttpSession session) {
		
		memberVo loginUser = (memberVo) session.getAttribute("loginUser");
		
		if (loginUser.getMemId().equals(memId) == false) {
			throw new RuntimeException("권한이 없습니다.");
		}
		int num = memberService.delete(memId);
		return"redirect:/member/list.do";
	}
	
	@RequestMapping(value="/member/login.do", method = RequestMethod.GET)
	public String loginForm() {
		return "member/login";
	}
	
	@RequestMapping(value="/member/login.do", method = RequestMethod.POST)
	public String login(memberVo vo, HttpSession session) { //스프링이 실행시 세션객체를 전달
		//사용자가 입력한 아이디/비밀번호와 일치하는 회원 정보 조회
		memberVo mvo = memberService.selectLoginUser(vo);
		if(mvo==null) { //로그인에 실패, 회원이 없는 경우 
			return "member/login"; //다시 로그인 화면 출력
		}
		//로그인이 성공 0-> 로그인한 사용자 정보를 세션에 저장
		session.setAttribute("loginUser", mvo);
		return "redirect:/bbs/list.do"; //게시판 글목록으로 이동
		//요청(한 페이지에서만 살아있음), 세션(브라우저마다 하나씩 생성), 서블릿컨텍스트(전체 웹어플리케이션에서 딱 하나 존재) 
	}
	
	@RequestMapping("/member/logout.do")
	public String logout(HttpSession session) {
		//로그아웃 == 세션에 저장된 로그인 정보를 삭제, 3가지 방법이 있음
	/*	session.setAttribute("loginUser", null); //세션에 "loginUse"라는 이름으로 null값을 저장.
		session.removeAttribute("loginUser"); //세션에 "loginUse" 속성 자체를 제거. */
		session.invalidate(); //세션 객체를 통째로 날려먹음. (다음 접속시 새로 만들어지므로 걱정 ㄴㄴ)
		/*return "member/login"; //주소창에 logout.do가 계속 남아 있으므로 주소와 jsp파일이 맞지 않으므로 안 됨.
*/		return "redirect:/member/login.do";
	}
	
	//회원 아이디를 받아서 그 회원의 사진(이미지)파일을 응답으로 전송
	@RequestMapping("/member/down.do")
	private void down(String memId,HttpServletResponse resp) throws IOException {
		memberVo vo = memberService.select(memId);
		File f = memberService.getImgFile(vo);
		//파일 f의 내용을 응답 객체에 쓰기(브라우저로 전송)
		//FileCopyUtils.copy(new FileInputStream(f), resp.getOutputStream());
		Files.copy(Paths.get(f.getAbsolutePath()), resp.getOutputStream());
		}
}