<%@ page import="java.util.List" %>
<%@ page import="infMarket.models.mybatis.dao.Post.PostMapperExecutor" %>
<%@ page import="infMarket.models.mybatis.dto.Post" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="infMarket.models.mybatis.dao.SellPost.SellPostMapperExecutor" %>
<%@ page import="infMarket.models.mybatis.dto.SellPost" %>
<%@ page import="infMarket.models.mybatis.dto.Member" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%
    try{
        Member member = (Member) request.getSession().getAttribute("auth");
        if(member == null || member.getAuthority().equals("ADMIN") == false){
            throw new Exception();
        }
        PostMapperExecutor postMapperExecutor = new PostMapperExecutor();
        List<Post> posts = postMapperExecutor.selectAllPost();
        StringBuilder postStringBuilder = new StringBuilder();
        for(Post post : posts){
            postStringBuilder.append("<tr>\n");
            postStringBuilder.append(String.format("<td scope='row'>%d</td>\n",post.getPost_id()));
            postStringBuilder.append(String.format("<td scope='row'><a href='/post/detail/%d' class='text-decoration-none text-dark'>%s</a></td>\n",post.getPost_id(),post.getTitle()));
            postStringBuilder.append(String.format("<td scope='row'>%s</td>\n",post.getMember_name()));
            postStringBuilder.append(String.format("<td scope='row'>%s</td>\n",new SimpleDateFormat("yyyy.MM.dd hh:mm").format(post.getWrite_datetime())));
            postStringBuilder.append(String.format("<td scope='row'><button class=\"btn btn-dark\" onclick=\"deletePost(%d)\">삭제</button></td>\n", post.getPost_id()));
            postStringBuilder.append("</tr>\n");
        }
        request.setAttribute("post_content", postStringBuilder.toString());

        StringBuilder sellPostStringBuilder = new StringBuilder();
        SellPostMapperExecutor sellPostMapperExecutor = new SellPostMapperExecutor();
        List<SellPost> sellPosts = sellPostMapperExecutor.selectAllSellPost();
        for(SellPost sellPost : sellPosts){
            sellPostStringBuilder.append("<tr>\n");
            sellPostStringBuilder.append(String.format("<td scope='row' class=\"align-middle\">%d</td>\n",sellPost.getSell_post_id()));
            if(sellPost.getImage_url() != null)
                sellPostStringBuilder.append(String.format("<td scope='row' class=\"align-middle\"><img src='%s' class=\"img-thumbnail\"></td>\n",sellPost.getImage_url()));
            else
                sellPostStringBuilder.append(String.format("<td scope='row' class=\"align-middle\">사진 없음</th>\n",sellPost.getImage_url()));
            sellPostStringBuilder.append(String.format("<td scope='row' class=\"align-middle\"><a href='/sell/detail/%d' class='text-decoration-none text-dark'>%s</a></td>\n",sellPost.getSell_post_id(),sellPost.getItem_name()));
            sellPostStringBuilder.append(String.format("<td scope='row' class=\"align-middle\">%s</td>\n",sellPost.getPrice()));
            sellPostStringBuilder.append(String.format("<td scope='row' class=\"align-middle\">%s</td>\n",sellPost.getStatus()));
            sellPostStringBuilder.append(String.format("<td scope='row' class=\"align-middle\">%s</td>\n",sellPost.getMember_name()));
            sellPostStringBuilder.append(String.format("<td scope='row' class=\"align-middle\">%s</td>\n",new SimpleDateFormat("yyyy.MM.dd hh:mm").format(sellPost.getWrite_datetime())));
            sellPostStringBuilder.append(String.format("<td scope='row' class='align-middle'><button class=\"btn btn-dark\" onclick=\"deleteSellPost(%d)\">삭제</button></td>\n", sellPost.getSell_post_id()));
            sellPostStringBuilder.append("</tr>\n");
        }
        request.setAttribute("sell_post_content", sellPostStringBuilder.toString());

    }catch (Exception e){
        PrintWriter printWriter = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        printWriter.println("<script>alert('비정상적인 접근!'); location.href='/';</script>");
        e.printStackTrace();
    }

%>


<!DOCTYPE html>
<html>
<%@ include file="../common/head.jsp" %>
<link href="${pageContext.request.contextPath}/css/index.css" property='stylesheet' rel='stylesheet'
      type="text/css" media="screen" crossorigin="anonymous"/>
<body class="p-5">
<section id="postList" class="col-sm-12 d-inline-block float-left shadow p-3 bg-white rounded text-center">
    <div class="infFontLarge infTextColor h3 mb-3">자유 게시글 관리</div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col" style="width: 10%">번호</th>
            <th scope="col" style="width: 50%">제목</th>
            <th scope="col" style="width: 10%">글쓴이</th>
            <th scope="col" style="width: 20%">작성일</th>
            <th scope="col" style="width: 10%">삭제</th>


        </tr>
        </thead>
        <tbody>
        ${post_content}
        </tbody>
    </table>
</section>

<section id="sellPostList" class="col-sm-12 d-inline-block float-left shadow p-3 bg-white rounded text-center">
    <div class="infFontLarge infTextColor h3 mb-3">판매 게시글 관리</div>
    <table class="table table-striped">
        <thead>
        <tr>
            <th scope="col" style="width: 10%">번호</th>
            <th scope="col" style="width: 10%">사진</th>
            <th scope="col" style="width: 20%">제품명</th>
            <th scope="col" style="width: 10%">가격</th>
            <th scope="col" style="width: 10%">상태</th>
            <th scope="col" style="width: 10%">글쓴이</th>
            <th scope="col" style="width: 10%">작성일</th>
            <th scope="col" style="width: 10%">삭제</th>
        </tr>
        </thead>
        <tbody>
        ${sell_post_content}
        </tbody>
    </table>
</section>
</body>
</html>

<script>
    function deletePost(postId){
        $.ajax({
            url  : '/api/post/delete',
            type : 'post',
            data : {postId: postId},
            success: function(xhr, textStatus, error){
                alert("성공적으로 삭제되었습니다")
                window.location.replace("/admin")
            },
            error: function(xhr, textStatus, error){
                alert("삭제에 실패했습니다")
            }
        })
    }
    function deleteSellPost(sellPostId){
        $.ajax({
            url  : '/api/sell/delete',
            type : 'post',
            data : {postId: sellPostId},
            success: function(xhr, textStatus, error){
                alert("성공적으로 삭제되었습니다")
                window.location.replace("/admin")
            },
            error: function(xhr, textStatus, error){
                alert("삭제에 실패했습니다")
            }
        })
    }
</script>