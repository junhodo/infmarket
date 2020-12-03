<%@ page import="Sogong.IMS.model.Package" %>
<%@ page import="Sogong.IMS.dao.PackageDAO" %>
<%@ page import="java.util.HashMap" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset='utf-8'>
    <meta http-equiv='X-UA-Compatible' content='IE=edge'>
    <meta name='viewport' content='width=device-width, initial-scale=1'>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap-datepicker.css">

    <script src="https://code.jquery.com/jquery-3.2.1.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap-datepicker.js"></script>

    <link href="${pageContext.request.contextPath}/css/bootstrap.css" property='stylesheet' rel='stylesheet'
          type="text/css" media="screen"/>
    <link href="${pageContext.request.contextPath}/css/dashboard.css" property='stylesheet' rel='stylesheet'
          type="text/css" media="screen"/>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/jquery/latest/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
    <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css"/>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
    <title>상품 패키지 수정</title>
</head>
<%
    String url = request.getRequestURI();
    String servletPath = request.getServletPath();
    String packageID = url.substring(servletPath.length()).split("/")[1];
    HashMap<String,Object> conditions = new HashMap<>();
    conditions.put("packageID", packageID);
    Package aPackage = PackageDAO.getInstance().lookup(conditions)[0];
%>

<body>
<div class="container">
    <div class="row col-auto justify-content-center mt-5">
        <form action="${pageContext.request.contextPath}/packageManage/modify.do" method="POST" name="form">
            <div class="form-gro    up">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon0">상품번호</span>
                    </div>
                    <input type="text" class="form-control" placeholder="입력" name="packageID"
                           aria-describedby="basic-addon1" autocomplete="off" required value=<%=aPackage.getPackageID()%> readonly>
                </div>
            </div>
            <div class="form-group">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon1">상품패키지 명</span>
                    </div>
                    <input type="text" class="form-control" placeholder="입력" name="packageName"
                           aria-describedby="basic-addon1" autocomplete="off" required value=<%=aPackage.getPackageName()%>>
                </div>
            </div>
            <div class="form-group">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon2">가격</span>
                    </div>
                    <input type="number" class="form-control" placeholder="입력" name="price"
                           aria-describedby="basic-addon1" autocomplete="off" oninput="negativeHandler(this)" value=<%=aPackage.getPrice()%> required>
                </div>
            </div>
            <div class="form-group">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon3">분류</span>
                    </div>
                    <input type="text" class="form-control" placeholder="입력" name="type"
                           aria-describedby="basic-addon1" autocomplete="off"value=<%=aPackage.getType()%> required>
                </div>
            </div>
            <div class="form-group">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon4">회사</span>
                    </div>
                    <input type="text" class="form-control" placeholder="입력" name="company"
                           aria-describedby="basic-addon1" autocomplete="off" value=<%=aPackage.getCompany()%> required>
                </div>
            </div>
            <div class="form-group">
                <div class="input-group mb-3">
                    <div class="input-group-prepend">
                        <span class="input-group-text" id="basic-addon5">설명</span>
                    </div>
                    <textarea class="form-control" rows="3" name="explanation" aria-describedby="basic-addon7"
                              autocomplete="off" required><%=aPackage.getExplanation()%></textarea>
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-3">
                    <button type="submit" class="btn btn-secondary bg-dark">수정</button>
                </div>
                <div class="col-1"></div>
                <div class="col-3">
                    <button type="button" class="btn btn-secondary bg-dark" onclick='self.close()'>취소</button>
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>

<script>
    function negativeHandler(input){
        if(input.value < 0){
            alert("음수는 허용되지 않습니다");
            input.value = 0;
        }
    }
</script>