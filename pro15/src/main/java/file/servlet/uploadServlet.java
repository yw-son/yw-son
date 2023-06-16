package file.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import file.FileDAO;
import file.FileData;


@WebServlet("/upload")
public class uploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 파일이 업로드될 경로를 저장하는 상수
	private static final String UPLOAD_DIRECTORY = "C:\\Users\\lenovo\\Desktop\\JSP2\\pro15\\src\\main\\webapp";
	
	// 최대 업로드 크기를 지정하는 상수
	// 1KB = 1024bit
	// 1024KB = 1MB
	// 1024MB = 1GB
	private static final int MAX_UPLOAD_SIZE = 10 * 1024 * 1024; // 10MB
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		// Multipart 요청을 처리하기 위해 MultipartRequest 객체 생성
		// 파일 업로드와 관련된 정보를 얻을 수 있는 객체
		MultipartRequest multipartRequest = 
				new MultipartRequest(request, UPLOAD_DIRECTORY, MAX_UPLOAD_SIZE, "UTF-8",
						new DefaultFileRenamePolicy());
		// request : 요청으로 들어온 내장 객체
		// saveDirectory : 서버의 파일 저장 경로
		// maxPostsize : 파일의 최대 크기(바이트 단위) 설정 - 설정 크기를 초과하면 IOException 발생
		// encoding : 인코딩 유형 설정
		// policy : 파일명 변경 정책 설정(saveDirectory에 파일명이 중복되는 경우 덮어쓰기 여부 설정)
		
		// 업로드된 파일의 서버 내의 파일명을 가지고 옴
		String fileName = multipartRequest.getFilesystemName("file");
		// getFilesystemName(String name) - String(반환값) - 사용자가 설정하여 서버에 실제로 업로드된 파일명을 반
		// >> 파일명 중복시 변경된 파일명으로 반환
		
		// getOriginalFileName(String name) - String(반환값) - 사용자가 업로드한 실제 파일명을 반환
		// >> 파일명 중복시 변경 전 파일명으로 반환
		// String filRealeName = multipartRequest.getOriginalFileName("file");
		
		// 파일의 저장 경로 생성
		String filePath = UPLOAD_DIRECTORY + File.separator + fileName;
		
		// 업로드된 파일이 실제로 있다면 크기를 계산
		long fileSize = new File(filePath).length();
		
		// 파일 정보를 담는 FileData 객체 생성
		FileData fileData = new  FileData(fileName, filePath, fileSize);
		
		// 파일 데이터를 저장하기 위한 FileDAO 객체 생성
		FileDAO fileDAO = new FileDAO();
		
		// 파일 데이터를 저장하는 메서드 saveFile() 실행 - 메타데이터를 DB에 저장하는 로직 실행
		fileDAO.saveFile(fileData);
		
		response.sendRedirect("upload.jsp");
;	}

}
