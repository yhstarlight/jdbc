<%@page import="kr.co.jsp.score.model.ScoreVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.jsp.score.model.ScoreDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	List<ScoreVO> scoreList = ScoreDAO.getInstance().list();
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>학생들의 전체 성적 조회</h1>
	
	<form action="search.jsp">
		<input type="text" name="keyword" placeholder="검색할 이름 입력">
		<button type="submit">검색</button>
	</form>
	<br>
	
	
	<table border="1">
		<tr>
			<th>이름</th>
			<th>국어</th>
			<th>영어</th>
			<th>수학</th>
			<th>총점</th>
			<th>평균</th>
			<th>비고</th>
		</tr>
		<% for(ScoreVO s : scoreList) { %>
		<tr>
			<td><%=s.getName() %></td>
			<td><%=s.getKor() %></td>
			<td><%=s.getEng() %></td>
			<td><%=s.getMath() %></td>
			<td><%=s.getTotal() %></td>
			<td><%=s.getAverage() %></td>
			<td><a href="delete.jsp?id=<%= s.getId()%>">삭제</a></td>
		</tr>
		<% } %>
	</table>
	<br>
	<a href="insert_form.jsp">새로운 점수 등록하기</a>
</body>
</html>