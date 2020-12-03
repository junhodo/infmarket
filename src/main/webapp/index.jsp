<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <meta name='viewport' content='width=device-width, initial-scale=1'>

    <title>인프 마켓</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-datepicker.css">

    <script src="https://code.jquery.com/jquery-3.2.1.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-datepicker.js"></script>

    <link href="${pageContext.request.contextPath}/css/bootstrap.css" property='stylesheet' rel='stylesheet'
          type="text/css" media="screen"/>
    <link href="${pageContext.request.contextPath}/css/index.css" property='stylesheet' rel='stylesheet'
          type="text/css" media="screen"/>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css"/>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
</head>
<body class="p-5">
    <div id="admin" class="w-100 text-left h6 infFontMiddle infTextColor">관리자 게시판</div>
    <div id="mainBanner" class="w-100 text-center infFontMiddle infTextColor mb-3">
        <img src="../img/logo.jpg">
    </div>

    <div id="menu" class="w-100 infBackgroundColor text-md-center p-1 mb-3">
        <span class="infFontMiddle h5 mr-5 text-white">사이트 소개</span>
        <span class="infFontMiddle h5 mr-5 text-white">출석 체크</span>
        <span class="infFontMiddle h5 mr-5 text-white">자유게시판</span>
        <span class="infFontMiddle h5 mr-5 text-white">중고거래게시판</span>
        <span class="infFontMiddle h5 mr-5 text-white">돌려돌려돌림판</span>
    </div>

    <section id="contentSection" class="col-sm-10 d-inline-block float-left infSectionMinHeight shadow p-3 bg-white rounded">
        <layout:block name="contentSection">
        </layout:block>
    </section>

    <section id="memberSection" class="col-sm-2 d-inline float-right infSectionMinHeight">
        <%@ include file="member/memberSection.jsp" %>
    </section>

</body>
</html>