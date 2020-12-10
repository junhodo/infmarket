<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<link href="${pageContext.request.contextPath}/css/index.css" property='stylesheet' rel='stylesheet'
      type="text/css" media="screen" crossorigin="anonymous"/>
<a href="/admin"><div id="admin" class="w-100 text-left h6 infFontMiddle infTextColor">관리자 게시판</div></a>
<div id="mainBanner" class="w-100 text-center infFontMiddle infTextColor mb-3">
    <a href="/"><img src="${pageContext.request.contextPath}/img/logo.jpg"></a>
</div>

<div id="menu" class="w-100 infBackgroundColor text-md-center p-1 mb-3">
    <a href="/"><span class="infFontMiddle h5 mr-5 text-white">사이트 소개</span></a>
    <a href="/attendance"><span class="infFontMiddle h5 mr-5 text-white">출석 체크</span></a>
    <a href="/point"><span class="infFontMiddle h5 mr-5 text-white">포인트 충전</span></a>
    <a href="/freeboard/1"><span class="infFontMiddle h5 mr-5 text-white">자유게시판</span></a>
    <a href="/sellboard/1"><span class="infFontMiddle h5 mr-5 text-white">중고거래게시판</span></a>
    <a href="/wishlist"><span class="infFontMiddle h5 mr-5 text-white">돌려돌려돌림판</span></a>
</div>