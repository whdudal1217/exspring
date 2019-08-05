package kr.ac.hit.myapp.comm;

public class MemPageInfo {
	// 사용자로부터 입력받는 값들
	private int page = 1; // 현재 페이지 번호
	private int size = 5; // 한 페이지당 게시되는 ☆게시물 건 수
	private int pageSize = 5; // 페이지 리스트(링크)에 게시되는 ☆페이지 건수
	private int totalRecordCount; // 전체 게시물 건 수

	// 입력받은 값들을 사용하여 계산하는 값들
	private int totalPageCount; // 총 페이지 개수
	private int firstPageNoOnPageList; // 페이지 리스트의 첫 페이지 번호
	private int lastPageNoOnPageList; // 페이지 리스트의 마지막 페이지 번호
	private int firstRecordIndex; // DB에서 조회해 올 첫번째 게시물 번호
	private int lastRecordIndex; // DB에서 조회해 올 마지막 게시물 번호

	private String pageHtml; // 화면에 출력할 페이지 리스트(링크)

	public void makePageHtml() {

		totalPageCount = ((totalRecordCount - 1) / size) + 1;
		firstPageNoOnPageList = ((page - 1) / pageSize) * pageSize + 1;
		System.out.println(firstPageNoOnPageList);
		lastPageNoOnPageList = firstPageNoOnPageList + pageSize - 1;
		if (lastPageNoOnPageList > totalPageCount) {
			lastPageNoOnPageList = totalPageCount;
		}
		firstRecordIndex = (page - 1) * size;
		lastRecordIndex = page * size;

		
		String html = "";
	//교수님 
		if(firstPageNoOnPageList !=1 ) {
			html += "<a href='#' onclick='goPage(1);' > 처음 </a>"; //처음을 아예 안 나오게 하셨습니다.
		}
		if(firstPageNoOnPageList !=1) {
			html += "<a href='#' onclick='goPage(" + (firstPageNoOnPageList - 1) + ");' > 이전 </a>";
		}else {
			html += "<a> 이전 </a>";
		}
		for (int i = firstPageNoOnPageList; i <= lastPageNoOnPageList; i++) {
			if(i==page) {
				html += "<a> " + i + "</a>";
			}
			else {
				html += "<a href='#' onclick='goPage(" + i + ");' > " + i + "</a>";				
			}
		}
		if(lastPageNoOnPageList < totalRecordCount) {
			html += "<a href='#' onclick='goPage(" + (lastPageNoOnPageList+1) + ");' > 다음 </a>";
		}else {
			html += "<a> 다음 </a>";
		}
		if(lastPageNoOnPageList < totalRecordCount) {
			html += "<a href='#' onclick='goPage(" + totalPageCount + ");' > 마지막 </a>";
		}
		pageHtml = html;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

	public int getTotalPageCount() {
		return totalPageCount;
	}

	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}

	public int getFirstPageNoOnPageList() {
		return firstPageNoOnPageList;
	}

	public void setFirstPageNoOnPageList(int firstPageNoOnPageList) {
		this.firstPageNoOnPageList = firstPageNoOnPageList;
	}

	public int getLastPageNoOnPageList() {
		return lastPageNoOnPageList;
	}

	public void setLastPageNoOnPageList(int lastPageNoOnPageList) {
		this.lastPageNoOnPageList = lastPageNoOnPageList;
	}

	public int getFirstRecordIndex() {
		return firstRecordIndex;
	}

	public void setFirstRecordIndex(int firstRecordIndex) {
		this.firstRecordIndex = firstRecordIndex;
	}

	public int getLastRecordIndex() {
		return lastRecordIndex;
	}

	public void setLastRecordIndex(int lastRecordIndex) {
		this.lastRecordIndex = lastRecordIndex;
	}

	public String getPageHtml() {
		return pageHtml;
	}

	public void setPageHtml(String pageHtml) {
		this.pageHtml = pageHtml;
	}
	
	
}