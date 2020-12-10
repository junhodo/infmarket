<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<div id="login" class="w-100 shadow p-3 bg-white rounded">
    <%
        if(request.getSession().getAttribute("auth") == null){ %>
    <%@ include file="./loginButton.jsp" %>
    <%}
    else{%>
    <%@ include file="./myInfoCard.jsp" %>
    <%}
    %>
</div>`