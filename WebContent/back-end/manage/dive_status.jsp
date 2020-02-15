<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.dive.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	int dive_status;
	List<DiveVO> list = (List<DiveVO>) request.getAttribute("list");
	if (list != null) {
		if (list.size() == 0) {
			dive_status = Integer.parseInt(request.getParameter("dive_status"));
			DiveService diveSvc = new DiveService();
			list = diveSvc.getAllByStatus(dive_status);
		} else {
			dive_status = list.get(0).getDive_status();
		}
	} else {
		dive_status = Integer.parseInt(request.getParameter("dive_status"));
		DiveService diveSvc = new DiveService();
		list = diveSvc.getAllByStatus(dive_status);
	}
	pageContext.setAttribute("list", list);
	System.out.println(list.size());
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

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back-end/manage/css/lightbox.min.css">
<script
	src="<%=request.getContextPath()%>/back-end/manage/js/lightbox-plus-jquery.min.js"></script>
<script src="https://kit.fontawesome.com/43de8a2881.js"
	crossorigin="anonymous"></script>

<link
	href="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/css/bootstrap4-toggle.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/js/bootstrap4-toggle.min.js"></script>

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
	background-color: #FFFFFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
/* 	width: 1500px; */
	color: red;
	display: block;
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
.status{
width: 80px;
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

		<div class="row mb-2">
			<div class="col-12" id="table-1">
				<h2 class="mb-0">潛點狀態分類</h2>
				<h4>
					<a
						href="<%=request.getContextPath()%>/back-end/manage/dive_listall.jsp">回所有潛點列表</a>
					<a
						href="<%=request.getContextPath()%>/back-end/manage/dive_listall.jsp">
					</a>
				</h4>
			</div>
		</div>


	<FORM method="post"
		action="<%=request.getContextPath()%>/DivesiteServlet/divesite.do">
		<h5>
			<div>
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
			<th class="status">狀態</th>

		</tr>

		<%@ include file="page1copy.file"%>
		<c:forEach var="diveVO" items="${list}" begin="<%=pageIndex %>"
			end="<%=pageIndex+rowsPerPage-1 %>">

			<FORM METHOD="post"
				ACTION="<%=request.getContextPath()%>/DivesiteServlet/divesite.do"
				style="margin-bottom: 0px;">

				<tr>
					<td class="dive">${diveVO.dive_no}</td>
					<td>${diveVO.dive_name}</td>
					<td class="des">${diveVO.dive_des}</td>
					<td><a class="BRC"
						href="<%=request.getContextPath() %>/ShowPic_V1?dive_no=${diveVO.dive_no}"
						data-lightbox="example-1"><img class="example-image" id="BRC"
							src="<%=request.getContextPath() %>/ShowPic_V1?dive_no=${diveVO.dive_no}"
							alt="image-1" /></a>
							</td>

					<td class="status">
						<div class="custom-control custom-switch">
							<c:if test="${diveVO.dive_status==0}">
								<input class="diveStatus0" type="checkbox" name="dive_status"
									checked data-toggle="toggle" data-on="開放" data-off="關閉"
									data-onstyle="success">
							</c:if>
							<c:if test="${diveVO.dive_status==1}">
								<input class="diveStatus1" type="checkbox" name="dive_status"
									checked data-toggle="toggle" data-on="關閉" data-off="開放"
									data-onstyle="light" data-offstyle="success">
							</c:if>
						</div>
					</td>
					<!-- Default switch 
<input type="checkbox" data-on="開放" data-off="關閉" checked data-toggle="toggle" data-onstyle="success">-->


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

	<%@ include file="page2copy.file"%>
	</div>
	</div>
	</div>
	<!----------------------------------------------以上-------------------------------------------------------------->
	<script>
		$(document).on('change', '.diveStatus0', function(e) {
			 var index = $('.diveStatus0').index(this);
			 var dive_no = ($('.dive').eq(index).text());
			 var dive_status = this.checked ? 0 : 1;
			 
			 $.ajax({
				 url: "<%=request.getContextPath()%>/DivesiteServlet/divesite.do",
					dataType : 'text',
					data : {
						"action" : "updateDiveStatus",
						"dive_no" : dive_no,
						"dive_status" : dive_status
					},
					type : "POST",
					success : function(data) {
						setTimeout(function() {
							alert(data);
						}, 300);
					}
				});
			});
		 
		$(document).on('change', '.diveStatus1', function(e) {
			 var index = $('.diveStatus1').index(this);
			 var dive_no = ($('.dive').eq(index).text());
			 var dive_status = this.checked ? 1 : 0;
			 
			 $.ajax({
				 url: "<%=request.getContextPath()%>/DivesiteServlet/divesite.do",
										dataType : 'text',
										data : {
											"action" : "updateDiveStatus",
											"dive_no" : dive_no,
											"dive_status" : dive_status
										},
										type : "POST",
										success : function(data) {
											setTimeout(function() {
												alert(data);
											}, 300);
										}
									});
						});
	</script>

	<div>
		<footer class="text-center text-secondary">
			&copy; DA104G2 GO ! <span id="year"></span>
		</footer>
		<!-- Optional JavaScript -->
		<!-- jQuery first, then Popper.js, then Bootstrap JS -->
		<script
			src="<%=request.getContextPath()%>/back-end/manage/js/jquery-3.4.1.js"></script>
		<script
			src="<%=request.getContextPath()%>/back-end/manage/js/poper.js"></script>
		<script
			src="<%=request.getContextPath()%>/back-end/manage/js/bootstrap.min.js"></script>
		<script
			src="<%=request.getContextPath()%>/back-end/manage/js/manager.js"></script>
	</div>
</body>
</html>

