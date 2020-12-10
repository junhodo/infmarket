<%@ page import="java.util.List" %>
<%@ page import="infMarket.models.mybatis.dao.Post.PostMapperExecutor" %>
<%@ page import="infMarket.models.mybatis.dto.Post" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="infMarket.models.mybatis.dto.SellPost" %>
<%@ page import="infMarket.models.mybatis.dao.SellPost.SellPostMapperExecutor" %>
<%@ page import="infMarket.models.mybatis.dao.Member.MemberMapperExecutor" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%
    try{
        String uri = request.getRequestURI();
        String servletPath = request.getServletPath();
        String postNo = uri.substring(servletPath.length()).split("/")[1];
        SellPostMapperExecutor sellPostMapperExecutor = new SellPostMapperExecutor();
        SellPost sellPost = sellPostMapperExecutor.selectSellPost(Integer.parseInt(postNo));
        MemberMapperExecutor memberMapperExecutor = new MemberMapperExecutor();
        Member writer = memberMapperExecutor.selectMember(sellPost.getMember_id());
        request.setAttribute("rating", Math.round(writer.getSeller_rating()*100)/100.0);
        request.setAttribute("title",sellPost.getItem_name());
        request.setAttribute("text",sellPost.getItem_description());
        request.setAttribute("price",sellPost.getPrice());
        request.setAttribute("seller_name", sellPost.getMember_name());
        request.setAttribute("date", new SimpleDateFormat("yyyy.MM.dd hh:mm").format(sellPost.getWrite_datetime()));
        request.setAttribute("sellPostId", sellPost.getSell_post_id());
        if(sellPost.getImage_url() != null) {
            request.setAttribute("img", String.format("<img src='%s' class='img-thumbnail rounded mx-auto d-block'>", sellPost.getImage_url()));
        }
        else
            request.setAttribute("img", "사진 없음");
        Member member = (Member) request.getSession().getAttribute("auth");
        if(member != null && member.getMember_id().equals(sellPost.getMember_id())) {
            request.setAttribute("myButton", String.format(("<div class='mt-3'>\n<button id=\"delete\" class=\"float-right btn btn-dark\">삭제</button>\n " +
                    "<button id=\"modify\" class=\"float-right btn btn-dark mr-2\" onclick=\"window.location.replace('%s')\">수정</button>\n</div>"), "/sell/modify/" + sellPost.getSell_post_id()));
            request.setAttribute("isMyPost", true);
            request.setAttribute("sellPostId", sellPost.getSell_post_id());
        }else if(member != null && sellPost.getStatus().equals("판매중")){
            request.setAttribute("clientButtons", "<div class='mt-3'>\n<button id=\"purchase\" class=\"float-right btn btn-dark\">구매</button>\n " +
                    "<button id=\"addWish\" class=\"float-right btn btn-dark mr-2\">찜하기</button>\n</div>");
        }
    }catch (Exception e){
        PrintWriter printWriter = response.getWriter();
        response.setContentType("text/html;charset=UTF-8");
        printWriter.println("<script>alert('비정상적인 접근!'); location.href='/sellboard/1';</script>");
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
        <div class="w-100"><span id="title" class="h4">${title}</span><span id="price" class="h6 ml-2">${price}원</span></div>
        <div>
            <span>작성자 : ${seller_name} | </span>
            <span>작성일 : ${date} | </span>
            <span>평점 : ${rating}</span>
        </div>
    </div>
    <div class="p-2 border-bottom">${img}</div>
    <div class="p-2 border-bottom">${text}</div>
    ${myButton}
    ${clientButtons}

</section>
</body>
</html>

<c:if test="${isMyPost == true}" var="isMyPost" scope="session">
    <script>
        $("#delete").click(function(){
            $.ajax({
                url  : '/api/sell/delete',
                type : 'post',
                data : {sellPostId: ${sellPostId}},
                success: function(xhr, textStatus, error){
                    alert("성공적으로 삭제되었습니다")
                    window.location.replace("/sellboard/1")
                },
                error: function(xhr, textStatus, error){
                    alert("삭제에 실패했습니다")
                }
            })
        });
    </script>
</c:if>



<c:if test="${not empty clientButtons}" var="isMyPost" scope="session">
    <script>
        $("#purchase").click(function(){
            $.ajax({
                url  : '/api/sell/purchase',
                type : 'post',
                data : {sellPostId: ${sellPostId}},
                success: function(xhr, textStatus, error){
                    $("#exampleModal").modal('show')
                },
                error: function(xhr, textStatus, error){
                    alert("구매에 실패했습니다, 가격을 확인해주세요")
                }
            })
        });
    </script>

    <script>
        $("#addWish").click(function(){
            $.ajax({
                url  : '/api/wish/add',
                type : 'post',
                data : {sellPostId: ${sellPostId}},
                success: function(xhr, textStatus, error){
                    alert("등록되었습니다.")
                },
                error: function(xhr, textStatus, error){
                    if(xhr.status == 400)
                        alert("비정상적인 접근!")
                    else if(xhr.status == 401)
                        alert("이미 추가된 물품입니다!")
                }
            })
        });
    </script>


    <%@ include file="./evalSeller.jsp" %>
</c:if>

