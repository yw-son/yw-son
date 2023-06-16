package file;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class FileDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private DataSource ds = null;
	
	public FileDAO() {
		try {
			Context context = new InitialContext();
			ds = (DataSource) context.lookup("java:comp/env/jdbc/fileDB");
		}catch(NamingException e) {
			e.printStackTrace();
		}
	}
	
	public void insertFileData(FileData fileData) {
		try {
			conn = ds.getConnection();
			String sql = "INSERT INTO file_table (file_name, file_path, file_size) VALUES (?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fileData.getFileName());
			pstmt.setString(2, fileData.getFilePath());
			pstmt.setLong(3, fileData.getFileSize());
			
			pstmt.executeUpdate();
			System.out.println("File data inserted successfully!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void saveFile(FileData fileData) {
		
		
		try {
			// 데이터베이스 커넥션 툴을 이용해 데이터베이스 연결
			conn = ds.getConnection();
			
			String sql="INSERT INTO files(filename, filepath, filesize) VALUES (?, ?, ?)";
			
			// SQL문 준비
			pstmt = conn.prepareStatement(sql);
			
			// SQL문 전송 전 ? 매개변수 값 설정
			pstmt.setString(1, fileData.getFileName());
			pstmt.setString(2, fileData.getFilePath());
			pstmt.setLong(3, fileData.getFileSize());
			
			// SQL 전송
			pstmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}


	
