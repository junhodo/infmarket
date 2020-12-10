<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
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
        <input type="text" id="sell_post_title" class="form-control" aria-label="Large" aria-describedby="inputGroup-sizing-sm" placeholder="5자 이상 작성해주세요">
    </div>
    <div id="text" aria-placeholder="5자 이상 작성해주세요"></div>
    <div class="input-group mt-3">
        <input type="number" class="form-control" name="price" id="price" required>
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
    <button id="submit" class="float-right btn btn-dark">등록</button>
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
            url: '/api/sell/write',
            type: 'post',
            data: {title:$("#sell_post_title").val(), text:$('#text').summernote('code'), price : $('#price').val()},
            success: function(xhr, textStatus, error){
                alert("성공적으로 등록되었습니다")
                window.location.replace("/sellboard/1")
            },
            error: function(xhr, textStatus, error){
                alert("등록에 실패했습니다 입력을 확인해주세요")
            }
        })
        $("#imageForm").submit()
    });
</script>