package kr.ac.hit.myapp.member;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.management.RuntimeErrorException;
import javax.swing.text.rtf.RTFEditorKit;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.hit.myapp.comm.MemSearchInfo;

//@Component(역할이 없을 때) @Controller(CONTROLLER) @Service(SERVICE) @Repository(DAO) 
//이 넷중에 하나만 등록하면 스프링이 new해서 가지게 됨

@Service
public class MemberServiceImpl implements MemberService {

	@Resource
	private MemberDao memberDao;
	
	private String uploadImgDir ="C:/Temp/profile";
	{
		//uploadDir에 지정된 디렉토리가 없으면 만들기
		new File(uploadImgDir).mkdirs();
	}
	
	@Transactional
	@Override
	public int insert(memberVo vo) {
		
		MultipartFile f = vo.getMemImgFile();
		if(f!=null && f.isEmpty()==false) {
			System.out.println(f.getOriginalFilename() + ":" + f.getSize() ); 
			String orgName = f.getOriginalFilename(); // xxx.xx.png 이렇게 생겼으면 .뒤의 확장자를 가져와야함
			int idx = orgName.lastIndexOf('.');// .확장자의 위치를 찾기 위함
		    String ext = idx<0? "" :orgName.substring(idx); //마지막 .의 위치(확장자)를 찾아서 떼어줌,  인덱스가 0보다 작으면 확장자가 없단 뜻이므로 빈문자를 반환한다.
			String newFileName = vo.getMemId() + ext; //저장하는 파일명.확장자
			File saveFile = new File(uploadImgDir, newFileName);
			try {
				f.transferTo(saveFile); //MultipartFile f의 내용을 saveFile에 복사, MultipartFile의 메소드 사용
				/*Files.copy(f.getInputStream(), Paths.get(saveFile.getAbsolutePath())); //JAVA API를 사용해서 f의 내용을 saveFile에 복사
				FileCopyUtils.copy(f.getInputStream(), new FileOutputStream(saveFile)); //SPRING을 사용해서 f의 내용을 saveFile에 복사 */			
				vo.setMemImg(newFileName); //하드디스크에 저장한 파일명
				} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
				throw new RuntimeException("프로필 이미지 저장 실패", e); //이미지가 없으면 메세지가 뜨게 하고, e를 넣어줍니다
			}
			
		}
		int num = memberDao.insert(vo);
		return num;
	}

	@Override
	public List<memberVo> selectList(MemSearchInfo info) {
		
		return memberDao.selectList(info);
	}

	@Override
	public memberVo select(String memId) {
		memberVo vo = memberDao.select(memId);
		return vo;
	}

	@Override
	public int update(memberVo vo) {
		int num = memberDao.update(vo);
		return num;
	}

	@Override
	public int delete(String memId) {
		 int num =  memberDao.delete(memId);
		 return num;
	}

	@Override
	public memberVo selectLoginUser(memberVo vo) {
		return memberDao.selectLoginUser(vo);
	}

	@Override
	public File getImgFile(memberVo vo) {
		return new File(uploadImgDir, vo.getMemImg());
	}

	@Override
	public int selectCount(MemSearchInfo info) {
		return memberDao.selectCount(info);
	}

}