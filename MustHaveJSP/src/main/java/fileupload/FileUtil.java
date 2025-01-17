package fileupload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class FileUtil {
	// 파일 업로드
	public static String uploadFile(HttpServletRequest request, String sDirectory) 
			throws ServletException, IOException {
		
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
	public static List<String> multipleFile(HttpServletRequest request, String sDirectory) 
			throws IOException, ServletException {
		
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
	
	// 명시한 파일을 찾아 다운로드
	public static void download(HttpServletRequest request, HttpServletResponse response,
			String directory, String sfileName, String ofileName) {
		
		String sDirectory = request.getServletContext().getRealPath(directory);
		
		try {
			// 파일을 찾아 입력 스트림 생성
			File file = new File(sDirectory, sfileName);
			InputStream inStream = new FileInputStream(file);
			
			// 한글 파일명 깨짐 방지
			ofileName = URLEncoder.encode(ofileName, "utf-8").replaceAll("\\+","%20");
			
			// 파일 다운로드용 응답 헤더 설정
			response.reset();  // 응답 헤더 초기화
			response.setContentType("application/octet-stream");  // 파일 다운로드 창 띄우기 위한 콘텐츠 타입
			response.setHeader("Content-Disposition", 
					"attachment; filename=\"" + ofileName + "\"");  // 파일 다운로드창에 원본 파일명 출력
			response.setHeader("Content-Length", ""+ file.length());
			
			// 출력 스트림 초기화
			//out.clear();
			
			// response 내장 객체로부터 새로운 출력 스트림 생성
			OutputStream outStream = response.getOutputStream();
			
			// 출력 스트림에 파일 내용 출력
			byte b[] = new byte[(int)file.length()];
			int readBuffer = 0;
			while((readBuffer = inStream.read(b)) > 0){
				outStream.write(b, 0, readBuffer);
			}
			
			// 입/출력 스트림 닫음
			inStream.close();
			outStream.close();
		}catch(FileNotFoundException e){
			System.out.println("파일을 찾을 수 없습니다.");
			e.printStackTrace();
		}catch(Exception e){
			System.out.println("예외가 발생하였습니다.");
			e.printStackTrace();
		}
	}
	
	// 지정한 게시물의 파일을 삭제
	public static void deleteFile(HttpServletRequest request, String directory, String filename) {
		String sDirectory = request.getServletContext().getRealPath(directory);
		
		File file = new File(sDirectory + File.separator + filename);
		if(file.exists()) {
			file.delete();
		}
	}
}
