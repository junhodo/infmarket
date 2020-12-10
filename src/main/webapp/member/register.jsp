<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
                <h2>인프마켓 회원가입</h2>
                <p>아이디와 비밀번호를 입력해주세요</p>
            </div>
        </div>
        <form class="login-form" action="/api/member/register" >
            이름<input type="text" placeholder="name" name="name" required/>
            성별<select type="text" placeholder="male" name="gender" class="w-100 login-form" required>
            <option value="0" selected="selected">남</option>
            <option value="1">여</option>
            </select>

            생년월일<input type="date" name="birth_date" required/>
            전화번호<input type="text" placeholder="01012345678" name="phone_number" required/>
            이메일<input type="text" placeholder="l0603js@gmail.com" name="email" required/>
            아이디<input type="text" placeholder="id" name="member_id" required/>
            비밀번호<input type="password" placeholder="password" name="password" required/>
            <button type="submit">회원가입하기</button>

        </form>
    </div>
</div>
</body>
</body>
</html>