<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script>
    function find() {
        var name = document.getElementsByName("name");
        var date = document.getElementsByName("date");
        var phonenum = document.getElementsByName("phonenum");
        if(name.value==""){
            alert("이름을 입력해주세요");
        }
        else if(date.value==""){
            alert("이름을 입력해주세요");
        }
        else if(phonenum.value==""){
            alert("이름을 입력해주세요");
        }
        else
            alert("아이디 : ??? \n비밀번호 : ???");
    }
</script>
<html>
<head>
    <%@ include file="../common/head.jsp" %>
    <title> 계정생성 페이지 </title>
</head>
<body>
<body>
<div class="login-page">
    <div class="form">
        <div class="login">
            <div class="login-header">
                <h2>계정 찾기</h2>
                <p>정보를 입력해주세요!</p>
            </div>
        </div>
        <form class="login-form">
            이름<input type="text" placeholder="name" name="name"/>
            생년월일<input type="date" name="date"/>
            전화번호<input type="text" placeholder="01012345678" name="phonenum"/>
            <button onclick="find();">계정 찾기</button>

        </form>
    </div>
</div>
</body>
</body>
</html>