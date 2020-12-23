package kr.co.jsp.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class MembersManager {
	
	private static Scanner sc = new Scanner(System.in);
	private static Connection conn;
	private static PreparedStatement pstmt;
	private static ResultSet rs;

	public static void main(String[] args) throws Exception {
	
		while(true) {
			System.out.println("\n### 회원 관리 프로그램 ###");
			System.out.println("# 1. 회원 정보 등록하기");
			System.out.println("# 2. 전체 회원 정보 조회하기");
			System.out.println("# 3. 개인 회원 정보 조회하기");
			System.out.println("# 4. 회원 정보 수정하기");
			System.out.println("# 5. 회원 정보 삭제하기");
			System.out.println("# 6. 프로그램 종료");
			
			System.out.println("# 메뉴를 입력하세요.");
			System.out.println("> ");
			
			int menu = sc.nextInt();
			
			switch(menu) {
			case 1: 
				insert();
				break;
			case 2: 
				selectAll();
				break;
			case 3: 
				selectOne();
				break;
			case 4: 
				update();
				break;
			case 5: 
				delete();
				break;
			case 6: 
				System.out.println("프로그램을 종료합니다.");
				sc.close();
				System.exit(0);
			default:
				System.out.println("메뉴를 잘못 입력하셨습니다.");
			} //end switch
		} //end while true
	}//end main
	
	//Connection 객체를 제공하는 메서드
	private static Connection getConnection() throws Exception {
		String url="jdbc:mysql://localhost:3306/jsp_practice?serverTimezone=Asia/Seoul";
		String uid="jsp";
		String upw="jsp";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		return DriverManager.getConnection(url,uid,upw);
	}
	
	//1. 회원 정보를 INSERT하는 메서드
	private static void insert() {
		System.out.println("회원 정보를 입력하세요.");
		System.out.print("# 아이디: ");
		String id = sc.next();
		
		System.out.print("# 비밀번호: ");
		String pw = sc.next();
		
		System.out.print("# 이름: ");
		String name = sc.next();
		
		System.out.print("# 이메일: ");
		String email = sc.next();
		
		String sql = "INSERT INTO members VALUES(?,?,?,?)";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, email);	
			
			int rn = pstmt.executeUpdate();
			
			if (rn==1) {
				System.out.println("회원정보가 성공적으로 입력되었습니다.");
			} else {
				System.out.println("회원정보 입력을 실패했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close(); pstmt.close(); 
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	//2. 전체 회원 정보를 조회하는 메서드
	private static void selectAll() {
		String sql = "SELECT * FROM members ORDER BY name ASC";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("======= 전체 회원 정보 =======");
			while(rs.next()) {
				System.out.println("# 아이디: "+rs.getString("id"));
				System.out.println("# 비밀번호: "+rs.getString("pw"));
				System.out.println("# 이름: "+rs.getString("name"));
				System.out.println("# 이메일: "+rs.getString("email"));
				System.out.println("========================");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close(); pstmt.close(); rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	//3. 개인 회원 정보를 조회하는 메서드
	private static void selectOne() {
		System.out.println("조회할 ID를 입력하세요 > ");
		String id = sc.next();
		String sql="SELECT * FROM members WHERE id=?";
				
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
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
				conn.close(); pstmt.close(); rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}		
	}
	
	//4. 회원 정보 수정하기 (수정할 아이디 입력받고 나서 이름, 이메일만 수정)
	private static void update(){
		System.out.println("수정할 ID를 입력하세요 > ");
		String id = sc.next();
		System.out.println("새로운 이름을 입력하세요 > ");
		String name = sc.next();
		System.out.println("새로운 이메일을 입력하세요 > ");
		String email = sc.next();
		String sql = "UPDATE members SET name=?, email=? WHERE id=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.setString(3, id);

			int rn = pstmt.executeUpdate();
			
			if(rn==1) {
				System.out.println("성공적으로 회원정보가 수정되었습니다.");
			} else {
				System.out.println("회원정보 수정을 실패했습니다.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close(); pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}	
	}
	
	//5.회원 정보 삭제
	private static void delete() {
		System.out.println("삭제할 회원정보 ID를 입력하세요 > ");
		String id = sc.next();
		String sql = "DELETE FROM members WHERE id=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			int rn = pstmt.executeUpdate();
			
			if(rn==1) {
				System.out.println("성공적으로 회원 정보가 삭제되었습니다");
			} else {
				System.out.println("회원정보 삭제를 실패했습니다.");
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close(); pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}	
	}
}//end class
