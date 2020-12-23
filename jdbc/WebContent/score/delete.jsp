<%@page import="kr.co.jsp.score.model.ScoreDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	//파라미터 데이터(id) 얻어오신 후 DAO에게 삭제 요청 해주시면 됩니다. (주소값 얻어오고 메서드 호출)
    	//삭제가 완료되면 score_list.jsp로 강제 이동
    	//삭제 실패해도 score_list.jsp로 이동
    %>
    
    <%
    	long id = Long.parseLong(request.getParameter("id"));
    
    	if (ScoreDAO.getInstance().delete(id)){
    		response.sendRedirect("score_list.jsp");
    	} else {
    		response.sendRedirect("socre_list.jsp");
    	}
    %>
