package utils;

public class BoardPage {
	public static String pagingStr(int totalCount, int pageSize, int blockPage, 
			int pageNum, String reqUrl) {
		
		String pagingStr = "";
		
		// 3단계 : 전체 페이지 수 계산
		int totalPages = (int)(Math.ceil((double) totalCount/pageSize));
		
		// 4단계 : '이전 페이지 블록 바로가기' 출력
		int pageTemp = (((pageNum - 1) / blockPage) * blockPage) + 1;
		if(pageTemp != 1) {
			pagingStr += "<li class='page-item'>\r\n"
					+ "      <a class='page-link' href='"+ reqUrl +"?pageNum=1'>&laquo;</a>\r\n"
					+ "    </li>";			
			pagingStr += "&nbsp;";
			pagingStr += "<li class='page-item'>\r\n"
					+ "      <a class='page-link' href='"+ reqUrl +"?pageNum="+ (pageTemp - 1) +"'>이전</a>"
					+ "    </li>"; 
		}
		
		// 5단계 : 각 페이지 번호 출력
		int blockCount = 1;
		while(blockCount <= blockPage && pageTemp <= totalPages) {
			if(pageTemp == pageNum) {
				// 현재 페이지는 active
				pagingStr += "&nbsp;";
				pagingStr += "<li class='page-item active'>\r\n"
						+ "      <a class='page-link' href='#'>"+ pageTemp +"</a>"
						+ "    </li>";
				pagingStr += "&nbsp;";
			}else {
				pagingStr += "&nbsp;";
				pagingStr += "<li class='page-item'>\r\n"
						+ "      <a class='page-link' href='"+ reqUrl +"?pageNum="+ pageTemp +"'>"+ pageTemp +"</a>"
						+ "    </li>";
				pagingStr += "&nbsp;";
			}
			pageTemp++;
			blockCount++;
		}
		
		// 6단계 : '다음 페이지 블록 바로가기' 출력
		if(pageTemp <= totalPages) {
			pagingStr += "<li class='page-item'>\r\n"
					+ "      <a class='page-link' href='"+ reqUrl +"?pageNum="+ pageTemp +"'>다음</a>"
					+ "    </li>";
			pagingStr += "&nbsp;";
			pagingStr += "<li class='page-item'>\r\n"
					+ "      <a class='page-link' href='"+ reqUrl +"?pageNum="+ totalPages +"'>&raquo;</a>\r\n"
					+ "    </li>";	
		}
		
		return pagingStr;
	}
}