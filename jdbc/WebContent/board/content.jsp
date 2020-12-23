<%@page import="kr.co.jsp.board.model.BoardDAO"%>
<%@page import="kr.co.jsp.board.model.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    //파라미터값 얻어와서 selectOne()
    
    Board article = 
    BoardDAO.getInstance().selectOne(Long.parseLong(request.getParameter("id")));
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1><%=article.getBoardId() %>번 게시물 내용</h1>
	
	<p>
		# 글 번호 : <%=article.getBoardId() %> <br>
		# 작성자 : <%=article.getWriter() %> <br>
		# 제목 : <%=article.getTitle() %> <br>
		# 내용 : <textarea rows="5" readonly><%=article.getContent() %></textarea> <br>
		# 작성일 : <%=article.getDate() %>
	</p>
	
	<a href="list.jsp">글 목록보기</a>
	<a href="modify.jsp?id=<%=article.getBoardId()%>">글 수정하기</a>

</body>
</html>