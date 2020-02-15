<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="com.adm.model.*"%>
<%
	AdmVO admVO = (AdmVO) session.getAttribute("admVO");
	//AdmServlet.java(Concroller), 存入req的admVO物件
%>

<!doctype html>
<html lang="en">

<head>
<link href="<%= request.getContextPath()%>/images/twdivers.png" rel="icon">
<title>潛進台灣 GO !</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back-end/manage/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back-end/manage/css/manage.css">
<link
	href="https://fonts.googleapis.com/css?family=Noto+Serif+TC:700&display=swap"
	rel="stylesheet">
<script src="https://kit.fontawesome.com/43de8a2881.js"
	crossorigin="anonymous"></script>

<!---------------背景css(可以換成自己的)---------------------->
<style type="text/css">
body {
/* 	width: 2000px; */
	background-image: url("<%=request.getContextPath()%>/images/memBg.png");
	background-size: cover;
	background-position: center;
	background-repeat: no-repeat;
	background-attachment: fixed;
}

#bgimg {
	background: rgb(255, 255, 255, 0.8);
}

/* #nav { */
/* 	opacity: 0.8; */
/* } */

#n_img {
	height: 70px;
	width: 70px;
/* 	border-radius: 50%; */
}

.navbar-nav .nav-item .nav-link {
	font-size: 26px;
	font-weight: bold;
	font-family: "Microsoft JhengHei UI";
}

table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
	border: 3px ridge Gray;
	height: 80px;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
/* .table{ */
/* 		margin-top:50px; */
/* 		margin-left:200px; */
/* 	} */

.form-control.manageSelect{
	display: inline;
	width: auto;
}
</style>
</head>
<!--------------------------以上--------------------------------->


<body>
	<jsp:include page="/back-end/manage/admNav.jsp"/>
				<!-----------------------------------------此區塊放自己的資料----------------------------------------------------->
					<div class="table">

					<h2>查詢管理員 :</h2>
					<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color: red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
					
						<ul><h3>
							<li class="mt-2 mb-2"><a href='manage_listall.jsp'>所有管理員列表</a></li>
							<li class="mt-2 mb-2">
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/AdmServlet/adm.do">
									<b>輸入管理員編號 (如A001):</b> <input class="form-control manageSelect" type="text" name="adm_no">
									<input type="hidden" name="action" value="getOne_For_Display">
									<input type="submit" value="送出" class="btn btn-primary">
								</FORM>
							</li>
							<jsp:useBean id="admSvc" scope="page"
								class="com.adm.model.AdmService" />
							<li class="mt-2 mb-2">
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/AdmServlet/adm.do">
									<b>選擇管理員ID:</b> <select size="1" name="adm_no" class="form-control manageSelect">
										<c:forEach var="admVO" items="${admSvc.all}">
											<option value="${admVO.adm_no}">${admVO.adm_id}
										</c:forEach>
									</select> <input type="hidden" name="action" value="getOne_For_Display">
									<input type="submit" value="送出" class="btn btn-primary">
								</FORM>
							</li>
							<li class="mt-2 mb-2">
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/AdmServlet/adm.do">
									<b>選擇管理員姓名:</b> <select size="1" name="adm_no" class="form-control manageSelect">
										<c:forEach var="admVO" items="${admSvc.all}">
											<option value="${admVO.adm_no}">${admVO.adm_name}
										</c:forEach>
									</select> <input type="hidden" name="action" value="getOne_For_Display">
									<input type="submit" value="送出" class="btn btn-primary">
								</FORM>
							</li>
						</h3></ul>
				
					<h2>
						<a href='manage_add.jsp'>新增管理員</a>
					</h2>
				</div>
</div>
				<!----------------------------------------------以上-------------------------------------------------------------->
			</div>
		</div>
		<!-- <div class="login"><button class="btn btn-outline-info">管理員登入</button></div> -->
		<footer class="text-center text-secondary">
			&copy; DA104G2 GO ! <span id="year"></span>
		</footer>
		<!-- Optional JavaScript -->
		<!-- jQuery first, then Popper.js, then Bootstrap JS -->
		<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
		<script
			src="<%=request.getContextPath()%>/back-end/manage/js/poper.js"></script>
		<script
			src="<%=request.getContextPath()%>/back-end/manage/js/bootstrap.min.js"></script>
		<script
			src="<%=request.getContextPath()%>/back-end/manage/js/manager.js"></script>
	</div>

</body>
</html>