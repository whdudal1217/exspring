package kr.ac.hit.myapp.member;

import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class memberVo {
	@NotNull @Size(min=4,max=50)
	private String memId;
	@NotNull @Size(min=4,max=50)
	private String memPass;
	@NotNull @Size(min=2,max=50)
	private String memName;
	@NotNull @Digits(integer=10,fraction=0) @PositiveOrZero //정수 10자리의 0혹은 양수
	private int memPoint;
	
	private String memImg; //DB 테이블의 컬럼값을 주고 받기 위한 변수
	private MultipartFile memImgFile; //받는 파일은 꼭 jsp의 name=""과 같게 해야 합니다, 폼에서 전송되는 파일을 받기위한 변수
	
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemPass() {
		return memPass;
	}
	public void setMemPass(String memPass) {
		this.memPass = memPass;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public int getMemPoint() {
		return memPoint;
	}
	public void setMemPoint(int memPoint) {
		this.memPoint = memPoint;
	}
	public MultipartFile getMemImgFile() {
		return memImgFile;
	}
	public void setMemImgFile(MultipartFile memImgFile) {
		this.memImgFile = memImgFile;
	}
	public String getMemImg() {
		return memImg;
	}
	public void setMemImg(String memImg) {
		this.memImg = memImg;
	}
	
	
}
