
<%@page import="kr.co.jsp.board.model.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <% 
    	long id = Long.parseLong(request.getParameter("id"));
    
    	if (BoardDAO.getInstance().delete(id)){ %>
    	<script>
    		alert("삭제가 정상 처리 되었습니다.");
    		location.href="list.jsp";
    	</script>
    <% } else { %>
    	<script>
    		alert("삭제 처리가 실패했습니다.");
    		location.href="list.jsp";
    	</script>
    <% } %>
