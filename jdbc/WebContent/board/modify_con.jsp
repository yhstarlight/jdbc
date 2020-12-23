<%@page import="kr.co.jsp.board.model.BoardDAO"%>
<%@page import="kr.co.jsp.board.model.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%
    	/*
    		- 필요한 파라미터 값을 불러와서 그것을 토대로
    		Board객체를 하나 생성하세요.
    		
    		- BoardDAO의 update()를 이용하여 수정한 객체를 DB에 넣어주시고
    		결과가 성공이라면 해당 글 상세보기 페이지로 이동시켜주세요. (sendRedirect)
    		결과가 실패라면 list.jsp로 리다이렉팅해주세요.
    	*/
    	
    	request.setCharacterEncoding("UTF-8");
    	long id = Long.parseLong(request.getParameter("boardId"));
    	
	    Board article = new Board();
	    article.setBoardId(id);
	    article.setWriter(request.getParameter("writer"));
	    article.setContent(request.getParameter("content"));
	    article.setTitle(request.getParameter("title"));
	    
	    if(BoardDAO.getInstance().Update(article)){
	    	response.sendRedirect("content.jsp?id="+id);
	    } else {
	    	response.sendRedirect("list.jsp");
	    }
    %>