<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ include file="../common/head.jsp" %>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
<body class="p-5">
<%@ include file="../common/header.jsp" %>
<%@ include file="../common/memberSection.jsp" %>
<section id="contentSection" class="col-sm-10 d-inline-block float-left infSectionMinHeight shadow p-3 bg-white rounded">
    <div class="input-group input-group-lg mb-lg-5">
        <div class="input-group-prepend">
            <span class="input-group-text" id="inputGroup-sizing-lg">제목</span>
        </div>
        <input type="text" id="post_title" class="form-control" aria-label="Large" aria-describedby="inputGroup-sizing-sm" placeholder="5자 이상 작성해주세요">
    </div>
    <div id="text" aria-placeholder="5자 이상 작성해주세요"></div>
    <button id="submit" class="float-right btn btn-dark">등록</button>
</section>
</body>
</html>

<script>
    $(document).ready(function() {
        $('#text').summernote({
            placeholder: '5자 이상 입력해주세요'
        });
    });



    $("#submit").click(function(){
        console.log($("#post_title").val())
        $.ajax({
            url  : '/api/post/write',
            type : 'post',
            data : {title: $("#post_title").val(), text: $('#text').summernote('code')},
            success: function(xhr, textStatus, error){
                alert("성공적으로 등록되었습니다")
                window.location.replace("/freeboard/1")
            },
            error: function(xhr, textStatus, error){
                alert("등록에 실패했습니다, 제목과 내용의 길이를 확인해주세요")
            }
        })
    });
</script>