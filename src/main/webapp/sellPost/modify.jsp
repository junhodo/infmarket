<%@ page import="infMarket.models.mybatis.dao.Post.PostMapper" %>
<%@ page import="infMarket.models.mybatis.dao.Post.PostMapperExecutor" %>
<%@ page import="infMarket.models.mybatis.dto.Post" %>
<%@ page import="infMarket.models.mybatis.dao.SellPost.SellPostMapper" %>
<%@ page import="infMarket.models.mybatis.dao.SellPost.SellPostMapperExecutor" %>
<%@ page import="infMarket.models.mybatis.dto.SellPost" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%
    try{
        String uri = request.getRequestURI();
        String servletPath = request.getServletPath();
        String postId_ = uri.substring(servletPath.length()).split("/")[1];
        int sellPostId = Integer.parseInt(postId_);
        Member member = (Member) request.getSession().getAttribute("auth");
        SellPostMapperExecutor sellPostMapperExecutor = new SellPostMapperExecutor();
        SellPost sellPost = sellPostMapperExecutor.selectSellPost(sellPostId);
        if(sellPost.getMember_id().equals(member.getMember_id())){
            request.setAttribute("title",sellPost.getItem_name());
            request.setAttribute("text",sellPost.getItem_description());
            request.setAttribute("sellPostId",sellPost.getSell_post_id());
            request.setAttribute("price",sellPost.getPrice());
        }else{
            throw new Exception();
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
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script>
<body class="p-5">
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/memberSection.jsp" %>
<section id="contentSection" class="col-sm-10 d-inline-block float-left infSectionMinHeight shadow p-3 bg-white rounded">
    <div class="input-group input-group-lg mb-lg-5">
        <div class="input-group-prepend">
            <span class="input-group-text" id="inputGroup-sizing-lg">상품명</span>
        </div>
        <input type="text" id="sell_post_title" class="form-control" aria-label="Large" aria-describedby="inputGroup-sizing-sm" placeholder="5자 이상 작성해주세요" value="${title}">
    </div>
    <div id="text" aria-placeholder="5자 이상 작성해주세요">${text}</div>
    <div class="input-group mt-3">
        <input type="number" class="form-control" name="price" id="price" required value="${price}">
        <div class="input-group-append">
            <span class="input-group-text">Point</span>
        </div>
    </div>
    <form id="imageForm" enctype="multipart/form-data">
        <div class="input-group mb-3 mt-3">
            <div class="custom-file">
                <input type="file" class="custom-file-input" id="inputFile" name="imageFile" accept="image/x-png,image/gif,image/jpeg"/>
                <label class="custom-file-label" for="inputFile">제품 사진</label>
            </div>
        </div>
    </form>
    <button id="submit" class="float-right btn btn-dark">수정</button>
</section>
</body>
</html>

<script>
    $('#inputFile').on('change',function(){
        //get the file name
        var fileName = $(this).val();
        //replace the "Choose a file" label
        $(this).next('.custom-file-label').html(fileName);
    })

    $(document).ready(function() {
        $('#text').summernote({
            placeholder: '5자 이상 입력해주세요'
        });
    });



    $("#submit").click(function(){
        $("#imageForm").ajaxForm({
            url: '/api/sell/modify/',
            type: 'post',
            data: {sellPostId: ${sellPostId}, title:$("#sell_post_title").val(), text:$('#text').summernote('code'), price : $('#price').val()},
            success: function(xhr, textStatus, error){
                alert("성공적으로 수정되었습니다")
                window.location.replace("/sellboard/1")
            },
            error: function(xhr, textStatus, error){
                alert("수정에 실패했습니다 입력을 확인해주세요")
            }
        })
        $("#imageForm").submit()
    });
</script>