<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.dive.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	Map<Integer, String> diveStatus = (Map<Integer, String>) application.getAttribute("diveStatus");
	DiveService diveSvc = new DiveService();
	List<DiveVO> list = diveSvc.getAll();
	pageContext.setAttribute("list", list);
%>

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

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back-end/manage/css/lightbox.min.css">
<script
	src="<%=request.getContextPath()%>/back-end/manage/js/lightbox-plus-jquery.min.js"></script>

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

#table-1 {
/* 	width: 1500px; */
	background-color: #FFFFFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: white;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}

.table {
/* 	width: 1500px; */
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}
.des{
width: 800px;
}

#BRC {
	height: 110px;
	width: 150px;
}

.form-control.diveStatus{
	display: inline;
	width: auto;
}
</style>
</head>
<!--------------------------以上--------------------------------->

<body>
	<jsp:include page="/back-end/manage/admNav.jsp" />
	<!-----------------------------------------此區塊放自己的資料----------------------------------------------------->
			<div class="row">
				<div class="col-12" id="table-1">
					<h2 class="mb-0 pt-2 pb-2">所有潛點資訊</h2>
					<h4>
						<a
							href="<%=request.getContextPath()%>/back-end/manage/dive_listall.jsp">
	
						</a>
					</h4>
				</div>
			</div>


	<FORM method="post"
		action="<%=request.getContextPath()%>/DivesiteServlet/divesite.do">
		<h5>
			<div class="mt-2">
				潛點狀態分類 <select name="dive_status" class="form-control diveStatus">
					<option name="dive_status" value="0">開放中</option>
					<option name="dive_status" value="1">關閉中</option>
				</select>
				<button type="submit" class="btn btn-primary">送出</button>
				<input type="hidden" name="action" value="getAllByStatus">
			</div>
		</h5>
	</FORM>


	<table class="table table-hover">
		<tr>
			<th>編號</th>
			<th>名稱</th>
			<th class="des">描述</th>
			<th>圖片</th>
			<th>狀態</th>

		</tr>

		<%@ include file="page1.file"%>
		<c:forEach var="diveVO" items="${list}" begin="<%=pageIndex %>"
			end="<%=pageIndex+rowsPerPage-1 %>">

			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/DivesiteServlet/divesite.do"
				style="margin-bottom: 0px;">

				<tr>
					<td>${diveVO.dive_no}</td>
					<td>${diveVO.dive_name}</td>
					<td class="des">${diveVO.dive_des}</td>
					<td><a class="BRC"
						href="<%=request.getContextPath() %>/ShowPic_V1?dive_no=${diveVO.dive_no}"
						data-lightbox="example-1"><img class="example-image" id="BRC"
							src="<%=request.getContextPath() %>/ShowPic_V1?dive_no=${diveVO.dive_no}"
							alt="image-1" /></a></td>

					<td>${diveStatus.get(diveVO.dive_status)}</td>

					<!--  <td>
										<button type="submit" value="修改"></button> 
										<input
										type="hidden" name="dive_no" value="${diveVO.dive_no}"> 
										<input
										type="hidden" name="action" value="">
									</td>-->
				</tr>
			</FORM>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>
	</div>
	</div>
	</div>
	<!----------------------------------------------以上-------------------------------------------------------------->

	<div>
		<footer class="text-center text-secondary">
			&copy; DA104G2 GO ! <span id="year"></span>
		</footer>
		<!-- Optional JavaScript -->
		<!-- jQuery first, then Popper.js, then Bootstrap JS -->
		<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
		<script src="js/poper.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/manager.js"></script>
	</div>
</body>
</html>