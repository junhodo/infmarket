<%@ page import="infMarket.models.mybatis.dao.Member.MemberMapperExecutor" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%
    MemberMapperExecutor memberMapperExecutor = new MemberMapperExecutor();
    List<Member> members = memberMapperExecutor.selectTopThreeLevelMember();
    StringBuilder stringBuilder = new StringBuilder();
    for(int i = 0 ; i < members.size() ; i++){
        stringBuilder.append(String.format("<div class=\"infFontSmall text-dark mt-3\" style=\"font-size: 3rem\">%d등 %s</div>",i+1, members.get(i).getName()));
    }
    request.setAttribute("rank", stringBuilder.toString());
%>


<!DOCTYPE html>
<html>
<%@ include file="../common/head.jsp" %>

<body class="p-5">
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/memberSection.jsp" %>
<section id="contentSection" class="col-sm-10 d-inline-block float-left infSectionMinHeight shadow p-3 bg-white rounded text-center">
    <div class="infFontMiddle infTextColor" style="font-size: 5rem">출석 체크는 1일 1회 한정!</div>
    <div class="infFontSmall text-dark mt-3" style="font-size: 4rem">인프마켓의 출석왕</div>
    ${rank}
</section>
</body>
</html>