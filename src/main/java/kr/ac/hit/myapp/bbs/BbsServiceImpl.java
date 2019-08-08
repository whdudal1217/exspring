package kr.ac.hit.myapp.bbs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.hit.myapp.comm.PageInfo;
import kr.ac.hit.myapp.comm.SearchInfo;

@Service
public class BbsServiceImpl implements BbsService {

	@Resource
	private BbsDao bbsDao;
	@Resource
	private AttachDao attachDao;
	private String uploadDir="C:/Temp/upload";{
		//uploadDir에 지정된 디렉토리가 없으면 만들기
		new File(uploadDir).mkdirs();
	}
	
	@Transactional//이 메서드를 하나의 트랜잭션으로 묶어서 실행 실행 중 예외가 발생하면 db를 메서드 실행 전 상태로 롤백
	@Override
	public int insert(BbsVo vo) {
		//게시글 정보 저장
		int num = bbsDao.insert(vo);
		if(vo.getUploadList() == null) return num;
		List<MultipartFile> uploadList = vo.getUploadList();
		for (MultipartFile f : uploadList) {
			if(f.isEmpty()) continue; //파일을 선택하지 않은 경우(빈 파일인 경우) for문을 처음부터 다시 돌아가게 함, 첨부파일 저장 작업을 하지 않음
		
			System.out.println(f.getOriginalFilename() + ":" + f.getSize() ); // 파일이 잘나오는지 이름을 찍어봄
			//파일을 서버의 파일시스템(하드디스크)에 저장
			String newFileName = UUID.randomUUID().toString(); //거의 중복될 확률이 없는 문자열
			File saveFile = new File(uploadDir, newFileName);//저장할 파일의 정보
			try {
				//f의 파일 내용을 읽어서, saveFile에 저장(복사)
				Files.copy(f.getInputStream(), Paths.get(saveFile.getAbsolutePath())); //자바
				/*FileCopyUtils.copy(f.getInputStream(), new FileOutputStream(saveFile)); //스프링*/
			} catch (IOException e) {
				e.printStackTrace();
			}
			//저장한 파일 정보를 DB attach 테이블에 추가 (attachDao, attachVo
			
			AttachVo avo = new AttachVo();
			avo.setAttOrgName(f.getOriginalFilename());
			avo.setAttNewName(newFileName);
			avo.setAttBbsNo(vo.getBbsNo());
			attachDao.insert(avo);
		}
		return num;
	}

	@Override
	public List<BbsVo> selectList(SearchInfo info) {
		return bbsDao.selectList(info);
	}

	@Override
	public BbsVo select(int bbsNo) {
		return bbsDao.select(bbsNo);
		
	}

	@Override
	public int update(BbsVo vo) {
		return bbsDao.update(vo);
	}

	@Override
	public int delete(BbsVo vo) {
		return bbsDao.delete(vo);
	}

	@Override
	public AttachVo selectAttach(int attNo) {
		return attachDao.select(attNo);
	}

	@Override
	public File getAttachFile(AttachVo vo) {
		return new File(uploadDir, vo.getAttNewName());
	}

	@Override
	public int selectCount(SearchInfo info) {
		return bbsDao.selectCount(info);
	}

}
