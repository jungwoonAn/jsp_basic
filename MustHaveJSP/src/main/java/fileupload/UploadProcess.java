package fileupload;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/13_FileUpload/UploadProcess.do")
@MultipartConfig(
    maxFileSize = 1024*1024*1,  // 개별 파일 최대 크기 1M
    maxRequestSize = 1024*1024*10  // 전체 파일 크기 10M
)
public class UploadProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String saveDirectory = getServletContext().getRealPath("/Uploads");
			// C:\Works\JSP_Servlet\jsp\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\MustHaveJSP\Uploads
			String originalFileName = FileUtil.uploadFile(request, saveDirectory);  // 파일 업로드
			String savedFileName = FileUtil.renameFile(saveDirectory, originalFileName);  // 파일명 변경
			
			insertMyfile(request, originalFileName, savedFileName);  // DB에 저장
			
			response.sendRedirect("FileList.jsp");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void insertMyfile(HttpServletRequest request, String oFileName, String sFileName) {
		String title = request.getParameter("title");
		
		String[] cateArray = request.getParameterValues("cate");
		StringBuffer cateBuf = new StringBuffer();		
		if(cateArray == null) {
			cateBuf.append("선택한 항목이 없음");
		}else {
			for(String s : cateArray) {
				cateBuf.append(s + ", ");
			}
		}
		
		MyFileVO fVo = new MyFileVO();
		fVo.setTitle(title);
		fVo.setCate(cateBuf.toString());
		fVo.setOfile(oFileName);
		fVo.setSfile(sFileName);
		
		MyFileDAO fDao = new MyFileDAO();
		fDao.insertFile(fVo);
		fDao.close();
	}

}
