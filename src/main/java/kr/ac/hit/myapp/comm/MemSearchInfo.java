package kr.ac.hit.myapp.comm;

public class MemSearchInfo extends MemPageInfo {
	
	//private PageInfo pageInfo; //이렇게 하면 있기는 한데 파라미터 이름이 pageInfo.page 이런 형태로 파라미터를 보내야 함 그렇게 하기 싫으니 상속하면 됩니다.
	
	private String searchType;
	private String searchWord;
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	
}