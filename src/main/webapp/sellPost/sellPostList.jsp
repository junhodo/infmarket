<%@ page import="java.util.List" %>
<%@ page import="infMarket.models.mybatis.dao.Post.PostMapperExecutor" %>
<%@ page import="infMarket.models.mybatis.dto.Post" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="infMarket.models.mybatis.dao.SellPost.SellPostMapperExecutor" %>
<%@ page import="infMarket.models.mybatis.dto.SellPost" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%
    try{
        String uri = request.getRequestURI();
        String servletPath = request.getServletPath();
        String pageNo_ = uri.substring(servletPath.length()).split("/")[1];
        int pageNo = Integer.parseInt(pageNo_);
        SellPostMapperExecutor sellPostMapperExecutor = new SellPostMapperExecutor();
        int totalPageNum = sellPostMapperExecutor.getTotalPageNum();
        List<SellPost> sellPosts = sellPostMapperExecutor.selectSellPostsByRange((pageNo-1)*10, (pageNo-1)*10+10);
        StringBuilder postStringBuilder = new StringBuilder();
        for(SellPost sellPost : sellPosts){
            postStringBuilder.append("<tr>\n");
            postStringBuilder.append(String.format("<td scope='row' class=\"align-middle\">%d</td>\n",sellPost.getSell_post_id()));
            if(sellPost.getImage_url() != null)
                postStringBuilder.append(String.format("<td scope='row' class=\"align-middle\"><img src='%s' class=\"img-thumbnail\"></td>\n",sellPost.getImage_url()));
            else
                postStringBuilder.append(String.format("<td scope='row' class=\"align-middle\">사진 없음</th>\n",sellPost.getImage_url()));
            postStringBuilder.append(String.format("<td scope='row' class=\"align-middle\"><a href='/sell/detail/%d' class='text-decoration-none text-dark'>%s</a></td>\n",sellPost.getSell_post_id(),sellPost.getItem_name()));
            postStringBuilder.append(String.format("<td scope='row' class=\"align-middle\">%s</td>\n",sellPost.getPrice()));
            postStringBuilder.append(String.format("<td scope='row' class=\"align-middle\">%s</td>\n",sellPost.getStatus()));
            postStringBuilder.append(String.format("<td scope='row' class=\"align-middle\">%s</td>\n",sellPost.getMember_name()));
            postStringBuilder.append(String.format("<td scope='row' class=\"align-middle\">%s</td>\n",new SimpleDateFormat("yyyy.MM.dd hh:mm").format(sellPost.getWrite_datetime())));
            postStringBuilder.append("</tr>\n");
        }
        StringBuilder pageStringBuilder = new StringBuilder();
        for(int i = 1 ; i <= totalPageNum ; i++){
            if(i != pageNo)
                pageStringBuilder.append(String.format("<a class='mr-3 text-dark text-decoration-none' href='/sellboard/%d'> %d </a>\n",i,i));
            else
                pageStringBuilder.append(String.format("<a class='mr-3 text-decoration-none font-weight-bold text-dark' href='/sellboard/%d'>%d</a>\n",i,i));
        }

        request.setAttribute("content", postStringBuilder.toString());
        request.setAttribute("page",pageStringBuilder.toString());

        Member member = (Member) request.getSession().getAttribute("auth");
        if(member != null)
            request.setAttribute("login", true);
    }catch (Exception e){
        response.sendRedirect("/sellboard/1");
        e.printStackTrace();
    }

%>


<!DOCTYPE html>
<html>
<%@ include file="../common/head.jsp" %>

<body class="p-5">
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/memberSection.jsp" %>
<section id="contentSection" class="col-sm-10 d-inline-block float-left infSectionMinHeight shadow p-3 bg-white rounded text-center">
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col" style="width: 10%">번호</th>
            <th scope="col" style="width: 10%">사진</th>
            <th scope="col" style="width: 20%">제품명</th>
            <th scope="col" style="width: 10%">가격</th>
            <th scope="col" style="width: 10%">상태</th>
            <th scope="col" style="width: 10%">글쓴이</th>
            <th scope="col" style="width: 20%">작성일</th>

        </tr>
        </thead>
        <tbody>
        ${content}
        </tbody>
    </table>
    <div>
        ${page}
    </div>
    <c:if test="${login == true}" var="isMyPost" scope="session">
        <button class="float-right btn btn-dark" onclick="window.location.replace('/sell/write')">작성</button>
    </c:if>
</section>
</body>
</html>
