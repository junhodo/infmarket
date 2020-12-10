<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">판매자 평가</h5>
            </div>
            <div class="d-flex justify-content-center my-4 w-75 m-auto">
                <form class="range-field w-100">
                    <input id="score" class="border-0 w-100" type="range" min="0" max="5" />
                </form>
                <span class="font-weight-bold text-primary ml-2 mt-1 valueSpan"></span>
            </div>
            <div class="modal-footer">
                <button type="button" id="submit_eval" class="btn btn-primary">평가</button>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function() {
        const $valueSpan = $('.valueSpan');
        const $value = $('#score');
        $valueSpan.html($value.val());
        $value.on('input change', () => {

            $valueSpan.html($value.val());
        });
    });

    $("#submit_eval").click(function(){
        $.ajax({
            url  : '/api/sell/eval',
            type : 'post',
            data : {sellPostId: ${sellPostId}, score: $('#score').val()},
            success: function(xhr, textStatus, error){
                alert("성공적으로 등록되었습니다")
                window.location.replace("/sellboard/1")
            },
            error: function(xhr, textStatus, error){
                alert("평가 등록에 실패했습니다.")
            }
        })
    });
</script>
