package kr.ac.hit.myapp.reply;

import java.util.Date;

public class ReplyVo {
	  private int repNo;
	  private String repContent;
	  private String repWriter;
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
