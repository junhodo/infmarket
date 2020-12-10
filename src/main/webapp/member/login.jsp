<%@ page import="infMarket.models.mybatis.dto.Member" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%
    Member member = (Member) request.getSession().getAttribute("auth");
    if(member != null){
        response.sendRedirect("/");
    }

%>
<!DOCTYPE html>
<script>
    function check() {
        var id = document.getElementsByName("id");
        var pw = document.getElementsByName("pw");

        if(id.value==""){
            alert("아이디를 입력해주세요");
        }
        else if(pw.value==""){
            alert("비밀번호를 입력해주세요");
        }
        else
            alert("로그인 완료!");
    }
</script>
<html>
<head>
    <%@ include file="../common/head.jsp" %>

    <title> 로그인페이지 </title>
</head>
<body>
<link href="${pageContext.request.contextPath}/css/index.css" property='stylesheet' rel='stylesheet'
      type="text/css" media="screen" crossorigin="anonymous"/>
<center class="mt-3">
    <a href="/">
    <img src="../img/logo.jpg"  align="center">
    </a>
</center>

<div class="login-page">
    <div class="form">
        <div class="login">
            <div class="login-header">
                <h3>환영합니다!</h3>
                <p>아이디와 비밀번호를 입력해주세요</p>
            </div>
        </div>
        <form class="login-form" action="/api/member/login" method="post">
            <input type="text" placeholder="id" name="id"/>
            <input type="password" placeholder="password" name="pw"/>
            <button type="submit">로그인</button>
            <p class="message">회원이 아니신가요? <a href="/register">계정을 만들어보세요!</a></p>
            <p class="message">잊어버렸나요? <a href="/forgot">계정 찾기</a></p>
        </form>
    </div>
</div>
</body>
</html>