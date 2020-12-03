<%--
  Created by IntelliJ IDEA.
  User: djh20
  Date: 2020-05-15
  Time: 오후 10:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta charset="utf-8">
  </head>
  <body>

  <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
    <div class="chartjs-size-monitor"
         style="position: absolute; left: 0px; top: 0px; right: 0px; bottom: 0px; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;">
      <div class="chartjs-size-monitor-expand"
           style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;">
        <div style="position:absolute;width:1000000px;height:1000000px;left:0;top:0"></div>
      </div>
      <div class="chartjs-size-monitor-shrink"
           style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;">
        <div style="position:absolute;width:200%;height:200%;left:0; top:0"></div>
      </div>
    </div>

    <div class="row ml-2">
      <%-- 화면 이름 --%>
      <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
        <h1 class="h2">판매 상품 관리</h1>
      </div>
    </div>
  </main>

  <style>
    .contents{
      margin: 2vh 0 0 2vw;
    }
  </style>
  <%@ include file="../index.jsp" %>

  <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4 contents">
    <%@ include file="PackageLookup.jsp" %>
  </main>
  <button type="button" class="btn btn-secondary bg-dark mb-sm-n3" style="float: right;margin-right: 2vw" onclick="javascript:enrollPopup()">추가</button>

  <script>
    function enrollPopup() {
      var url = "${pageContext.request.contextPath}/packageEnroll"
      var name = "enroll popup"
      var option = "scrollbars=no,resizable=no,status=no,location=no,toolbar=no,menubar=no,width=400,height=600,left=100,top=100"
      var child = window.open(url, name, option);
      child.focus();
      child.onload = function () {
        var wid = child.document.body.offsetWidth + 50;
        var hei = child.document.body.offsetHeight + 200;        //30 과 40은 넉넉하게 하려는 임의의 값임
        child.resizeTo(wid, hei);
      };
    }
  </script>
  </body>
</html>
