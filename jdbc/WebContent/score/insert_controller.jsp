<%@page import="kr.co.jsp.score.model.ScoreDAO"%>
<%@page import="kr.co.jsp.score.model.ScoreVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    <%
    request.setCharacterEncoding("UTF-8");
    
    String name = request.getParameter("name");
    
    ScoreVO score = new ScoreVO();
    
    score.setName(request.getParameter("name"));
    score.setKor(Integer.parseInt(request.getParameter("kor")));
    score.setEng(Integer.parseInt(request.getParameter("eng")));
    score.setMath(Integer.parseInt(request.getParameter("math")));
    score.setTotal();
    score.setAverage();
    
    ScoreDAO dao = ScoreDAO.getInstance();
    boolean flag = dao.insert(score);
    
    if(flag){
    	response.sendRedirect("insert_sucess.jsp");
    } else {
    	response.sendRedirect("insert_form.jsp");
    }
    
    /*
    1. 폼 데이터를 얻어오기
    2. DAO 클래스에게 DB에 입력값을 넣어달라고 얘기하기 전에
   	객체화 시켜서 보내줘야합니다.
   	3. 객체화를 완료했으면, DAO 클래스의 매서드에게 주소값을 달라고 요청
   	4. 주소값 받았으면 DAO클래스의 메서드를 실행
    */
    %>