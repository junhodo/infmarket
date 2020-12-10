<%@ page import="java.util.List" %>
<%@ page import="infMarket.models.mybatis.dao.Post.PostMapperExecutor" %>
<%@ page import="infMarket.models.mybatis.dto.Post" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%
    try{
        String uri = request.getRequestURI();
        String servletPath = request.getServletPath();
        String postNo = uri.substring(servletPath.length()).split("/")[1];
        PostMapperExecutor postMapperExecutor = new PostMapperExecutor();
        Post post = postMapperExecutor.selectPost(Integer.parseInt(postNo));
        request.setAttribute("title",post.getTitle());
        request.setAttribute("text",post.getText());
        request.setAttribute("name", post.getMember_name());
        request.setAttribute("date", new SimpleDateFormat("yyyy.MM.dd hh:mm").format(post.getWrite_datetime()));
        Member member = (Member) request.getSession().getAttribute("auth");
        if(member != null && member.getMember_id().equals(post.getMember_id())){
            request.setAttribute("myButton", String.format(("<div class='mt-3'>\n<button id=\"delete\" class=\"float-right btn btn-dark\">삭제</button>\n " +
                    "<button id=\"modify\" class=\"float-right btn btn-dark mr-2\" onclick=\"window.location.replace('%s')\">수정</button>\n</div>"),"/post/modify/" + post.getPost_id()));
            request.setAttribute("isMyPost", true);
            request.setAttribute("postId", post.getPost_id());
        }
    }catch (Exception e){
        PrintWriter printWriter = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        printWriter.println("<script>alert('비정상적인 접근!'); location.href='/freeboard/1';</script>");
        e.printStackTrace();
    }
%>


<!DOCTYPE html>
<html>
<%@ include file="../common/head.jsp" %>

<body class="p-5">
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/memberSection.jsp" %>
<section id="contentSection" class="col-sm-10 d-inline-block float-left infSectionMinHeight shadow p-3 bg-white rounded justify-content-center infFontSmall">
    <div class="border-bottom">
        <div id="title" class="w-100 h4">${title}</div>
        <div>
            <span>작성자 : ${name} | </span>
            <span>작성일 : ${date}</span>
        </div>
    </div>
    <div class="p-2 border-bottom">${text}</div>
    ${myButton}
</section>
</body>
</html>

<c:if test="${isMyPost == true}" var="isMyPost" scope="session">
    <script>
        $("#delete").click(function(){
            $.ajax({
                url  : '/api/post/delete',
                type : 'post',
                data : {postId: ${postId}},
                success: function(xhr, textStatus, error){
                    alert("성공적으로 삭제되었습니다")
                    window.location.replace("/freeboard/1")
                },
                error: function(xhr, textStatus, error){
                    alert("삭제에 실패했습니다")
                }
            })
        });
    </script>
</c:if>



