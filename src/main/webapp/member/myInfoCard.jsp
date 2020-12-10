<%@ page import="infMarket.models.mybatis.dto.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Member member = (Member) request.getSession().getAttribute("auth");
    request.setAttribute("name",member.getName());
    request.setAttribute("point",member.getPoint());
    request.setAttribute("level",member.getLevel());

%>
<div class="w-100 text-center">
    <div id="title" class="h4">${name}님 환영합니다</div>
    <div id="info" class="mt-2">
        <div id="point" class="infFontSmall">보유 포인트 : ${point}P</div>
        <div id="level" class="infFontSmall">회원 등급 : Lv${level}</div>
    </div>
    <div id="links" class="mt-2">
        <span id="my-page" class="mr-3"><a href="/mypage" class="infTextColor infFontSmall">회원정보수정</a></span>
        <span id="logout"><a href="/api/member/logout" class="infTextColor infFontSmall">로그아웃</a></span>
    </div>

    <button class="login-button mt-2" onclick="window.location.replace('/api/member/attendance')">
        출석 체크
    </button>


</div>