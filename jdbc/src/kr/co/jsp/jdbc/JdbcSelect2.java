package kr.co.jsp.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class JdbcSelect2 {

	public static void main(String[] args) {
		
		/*
		 * 회원의 ID를 입력받아 해당 ID의 회원정보를 모두 출력하는
		 * JDBC 프로그래밍 코드를 작성하세요.
		 */
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("검색하고 싶은 ID를 입력하세요 : ");
		String id = sc.next();
		
		String url = "jdbc:mysql://localhost:3306/jsp_practice?serverTimezone=Asia/Seoul";
		String uid = "jsp";
		String upw = "jsp";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM members WHERE id='"+id+"'";
		//String sql = "SELECT * FROM members WHERE id=?";
		

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(url,uid,upw);
			pstmt = conn.prepareStatement(sql);
			//pstmt.setString(1,id);
			rs = pstmt.executeQuery();	
			
			if(rs.next()) {
				String pw = rs.getString("pw");
				String name = rs.getString("name");
				String email = rs.getString("email");
				
				System.out.printf("# 아이디: %s\n# 비밀번호: %s\n# 이름: %s\n# 이메일: %s\n",id,pw,name,email);
			} else {
				System.out.println("회원 정보가 없습니다.");
			}	
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();pstmt.close(); rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			} 
		}
		
	}

}
