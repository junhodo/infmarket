<%@ page import="java.util.List" %>
<%@ page import="infMarket.models.mybatis.dao.Post.PostMapperExecutor" %>
<%@ page import="infMarket.models.mybatis.dto.Post" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%
    try{
        String uri = request.getRequestURI();
        String servletPath = request.getServletPath();
        String pageNo_ = uri.substring(servletPath.length()).split("/")[1];
        int pageNo = Integer.parseInt(pageNo_);
        PostMapperExecutor postMapperExecutor = new PostMapperExecutor();
        int totalPageNum = postMapperExecutor.getTotalPageNum();
        List<Post> posts = postMapperExecutor.selectPostsByRange((pageNo-1)*10, (pageNo-1)*10+10);
        StringBuilder postStringBuilder = new StringBuilder();
        for(Post post : posts){
            postStringBuilder.append("<tr>\n");
            postStringBuilder.append(String.format("<td scope='row'>%d</td>\n",post.getPost_id()));
            postStringBuilder.append(String.format("<td scope='row'><a href='/post/detail/%d' class='text-decoration-none text-dark'>%s</a></td>\n",post.getPost_id(),post.getTitle()));
            postStringBuilder.append(String.format("<td scope='row'>%s</td>\n",post.getMember_name()));
            postStringBuilder.append(String.format("<td scope='row'>%s</td>\n",new SimpleDateFormat("yyyy.MM.dd hh:mm").format(post.getWrite_datetime())));
            postStringBuilder.append("</tr>\n");
        }
        StringBuilder pageStringBuilder = new StringBuilder();
        for(int i = 1 ; i <= totalPageNum ; i++){
            if(i != pageNo)
                pageStringBuilder.append(String.format("<a class='mr-3 text-dark text-decoration-none' href='/freeboard/%d'> %d </a>\n",i,i));
            else
                pageStringBuilder.append(String.format("<a class='mr-3 text-decoration-none font-weight-bold text-dark' href='/freeboard/%d'>%d</a>\n",i,i));
        }

        request.setAttribute("content", postStringBuilder.toString());
        request.setAttribute("page",pageStringBuilder.toString());
    }catch (Exception e){
        response.sendRedirect("/freeboard/1");
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
            <th scope="col" style="width: 50%">제목</th>
            <th scope="col" style="width: 20%">글쓴이</th>
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
    <button class="float-right btn btn-dark" onclick="window.location.replace('/post/write')">작성</button>
</section>
</body>
</html>