package kr.ac.hit.myapp.bbs;

public class AttachVo {

	  private int attNo;
	  private String attOrgName; 
	  public String getAttOrgName() {
		return attOrgName;
	}
	public void setAttOrgName(String attOrgName) {
		this.attOrgName = attOrgName;
	}
	private String attNewName; //UUID로 만든 파일 이름
	  private int attBbsNo;
	  
	public int getAttNo() {
		return attNo;
	}
	public void setAttNo(int attNo) {
		this.attNo = attNo;
	}
	
	public String getAttNewName() {
		return attNewName;
	}
	public void setAttNewName(String attNewName) {
		this.attNewName = attNewName;
	}
	public int getAttBbsNo() {
		return attBbsNo;
	}
	public void setAttBbsNo(int attBbsNo) {
		this.attBbsNo = attBbsNo;
	}
	  
}
