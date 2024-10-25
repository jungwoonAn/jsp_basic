package fileupload;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public class FileUtil {
	// 파일 업로드
	public static String uploadFile(HttpServletRequest request, String sDirectory) throws ServletException, IOException {
		
		Part part = request.getPart("attachedFile");
		String partHeader = part.getHeader("content-disposition");
		System.out.println("partHeader : " + partHeader);
		//partHeader : form-data; name="attachedFile"; filename="fall.jpg"
		
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
}
