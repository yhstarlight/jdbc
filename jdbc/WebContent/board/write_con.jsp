<%@page import="kr.co.jsp.board.model.BoardDAO"%>
<%@page import="kr.co.jsp.board.model.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%
    /*
    	1. 사용자의 입력값을 입력하세요. 그리고 객체로 포장하세요.
    	2. DAO 클래스의 주소값을 받아 DB에 내용을 삽입하는 메서드를 호출하세요. (insert)
    	3. 글 등록 성공시 list.jsp로 리다이렉팅
    	4. 글 등록 실패시 write.jsp 리다이렉팅
    */   
    request.setCharacterEncoding("UTF-8");
    
    Board article = new Board();
    article.setWriter(request.getParameter("writer"));
    article.setContent(request.getParameter("content"));
    article.setTitle(request.getParameter("title"));
    
    if (BoardDAO.getInstance().insert(article)){
    	response.sendRedirect("list.jsp");
    } else {
    	response.sendRedirect("write.jsp");
    }
    
    %>