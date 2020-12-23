package kr.co.jsp.board.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BoardDAO implements IBoardDAO {
	
	DataSource ds;
	
	//싱글톤 생성
	//1. 생성자에 private 지정
	private BoardDAO() {
		//커넥션 풀 연결 방법
		//InitialContext객체 생성 > 저 객체에 context.xml에 작성한 설정파일이 저장됨
		
		try {
			InitialContext ct = new InitialContext();
			ds = (DataSource) ct.lookup("java:comp/env/jdbc/mysql");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	//2. 클래스 내부에서 스스로의 객체를 단 1개만 생성
	private static BoardDAO boardDAO = new BoardDAO();
	
	//3. 외부에서 객체 요구 시 공개된 메서드를 통해 주소값 리턴
	public static BoardDAO getInstance() {
		if(boardDAO == null) {
			boardDAO = new BoardDAO();
		}
		return boardDAO;
	}
	
	//커넥션 객체를 생성하여 리턴해주는 유틸 메서드 선언
//	private Connection getConnection() throws Exception {
//		String url="jdbc:mysql://localhost:3306/jsp_practice?serverTimezone=Asia/Seoul";
//		String uid="jsp";
//		String upw="jsp";
//		
//		return DriverManager.getConnection(url,uid,upw);
//	}
	
	////////////////////////////////////////////////////////////////////////////////////
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	@Override
	public boolean insert(Board article) {
		String sql = "INSERT INTO board (writer,title,content) VALUES (?,?,?)";
		boolean flag = false;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2, article.getTitle());
			pstmt.setString(3, article.getContent());
			
			int rn = pstmt.executeUpdate();
			
			if(rn==1) {
				flag = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn!=null)conn.close();
				if(pstmt!=null)pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return flag;
	}
	
	@Override
	public List<Board> selectAll() {
		List<Board> articles = new ArrayList<>();
		String sql = "SELECT * FROM board ORDER BY board_id ASC";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Board article = new Board(
						rs.getLong("board_id"),
						rs.getString("writer"),
						rs.getString("title"),
						rs.getString("content"),
						rs.getDate("create_at")
						);
				articles.add(article);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn!=null)conn.close();
				if(pstmt!=null)pstmt.close();
				if(rs!=null)rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return articles;
	}

	@Override
	public Board selectOne(long id) {
		String sql = "SELECT * FROM board WHERE board_id=?";
		Board article = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				article = new Board(
						rs.getLong("board_id"),
						rs.getString("writer"),
						rs.getString("title"),
						rs.getString("content"),
						rs.getDate("create_at")
						);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn!=null)conn.close();
				if(pstmt!=null)pstmt.close();
				if(rs!=null)rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return article;
	}

	@Override
	public boolean Update(Board article) {
		boolean flag = false;
		String sql = "UPDATE board set writer=?,title=?,content=? WHERE board_id=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, article.getWriter());
			pstmt.setString(2, article.getTitle());
			pstmt.setString(3, article.getContent());
			pstmt.setLong(4, article.getBoardId());
			
			if(pstmt.executeUpdate()==1) {
				flag = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn!=null)conn.close();
				if(pstmt!=null)pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return flag;
	}

	@Override
	public boolean delete(long id) {
		boolean flag = false;
		String sql = "DELETE FROM board WHERE board_id=?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			
			if(pstmt.executeUpdate()==1) {
				flag = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn!=null)conn.close();
				if(pstmt!=null)pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return flag;
	}

	
}
