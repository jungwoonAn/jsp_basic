package fileupload;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public class FileUtil {
	// 파일 업로드
	public static String uploadFile(HttpServletRequest request, String sDirectory) throws ServletException, IOException {
		
		Part part = request.getPart("ofile");
		String partHeader = part.getHeader("content-disposition");
		System.out.println("partHeader : " + partHeader);
		//partHeader : form-data; name="ofile"; filename="fall.jpg"
		
		String[] phArr = partHeader.split("filename=");
		String originalFileName = phArr[1].trim().replace("\"", "");
		System.out.println(originalFileName);  // fall.jpg
		
		if(!originalFileName.isEmpty()) {
			part.write(sDirectory + File.separator + originalFileName);
			                     // 윈도우: \, 리눅스: / 
		}
		
		return originalFileName;
	}
	
	// 파일명 변경
	public static String renameFile(String sDirectory, String fileName) {
		String ext = fileName.substring(fileName.lastIndexOf("."));  // 확장자명
		String now = new SimpleDateFormat("yyyMMdd_HmsS").format(new Date());
		String newFileName = now + ext;
		
		File oldFile = new File(sDirectory + File.separator + fileName);
		File newFile = new File(sDirectory + File.separator + newFileName);
		oldFile.renameTo(newFile);
		
		return newFileName;
	}

	// multiple 속성 추가로 2개 이상의 파일 업로드
	public static List<String> multipleFile(HttpServletRequest request, String sDirectory) throws IOException, ServletException {
		List<String> listFileName = new ArrayList<String>();  // 원분파일명 ArrayList로 저장
		Collection<Part> parts = request.getParts();  // 전송된 폼값을 받아 Collection<Part> 타입으로 저장
		
		for(Part part: parts) {
			if(!part.getName().equals("ofile"))  // 폼값 중 ofile이 아니면 건너띄고
				continue;
			
			String partHeader = part.getHeader("content-disposition");  // 헤더에서 파일명 추출
			System.out.println(partHeader);
			String[] phArr = partHeader.split("filename=");
			String originalFileName = phArr[1].trim().replace("\"", "");
			
			if(!originalFileName.isEmpty()) {
				part.write(sDirectory + File.separator + originalFileName);
			}
			listFileName.add(originalFileName);
		}
		
		return listFileName;
	}	
}