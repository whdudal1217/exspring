package kr.ac.hit.myapp.reply;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReplyVo {
	  private int repNo;
	  private String repContent;
	  private String repWriter;
	  //잭슨 라이브러리를 사용하여 json으로 변환할 때, 이 변수의 값을 원하는 형태의 문자열로
	  @JsonFormat
      (shape = JsonFormat.Shape.STRING, pattern = "yyyy년MM월dd일 hh시mm분")
	  private Date repDate;
	  private String repBbsNo;
	  
	public int getRepNo() {
		return repNo;
	}
	public void setRepNo(int repNo) {
		this.repNo = repNo;
	}
	public String getRepContent() {
		return repContent;
	}
	public void setRepContent(String repContent) {
		this.repContent = repContent;
	}
	public String getRepWriter() {
		return repWriter;
	}
	public void setRepWriter(String repWriter) {
		this.repWriter = repWriter;
	}
	public Date getRepDate() {
		return repDate;
	}
	public void setRepDate(Date repDate) {
		this.repDate = repDate;
	}
	public String getRepBbsNo() {
		return repBbsNo;
	}
	public void setRepBbsNo(String repBbsNo) {
		this.repBbsNo = repBbsNo;
	}
	  
	  
}
