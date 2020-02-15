<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	MemService memSvc = new MemService();
	List<MemVO> list = memSvc.getAll();
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
<link rel="icon" href="images/高麗菜.png" type="image/gif" sizes="72x72">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/manage.css">
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
	background-color: #CCCCFF;
	border: 2px solid black;
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

table {
	width: 1800px;
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
</style>
</head>
<!--------------------------以上--------------------------------->

<body>
	<jsp:include page="/back-end/manage/admNav.jsp" />
	<!-----------------------------------------此區塊放自己的資料----------------------------------------------------->

		<table id="table-1">
			<tr>
				<td>
					<h3>所有潛店會員資料 - listAllStores.jsp</h3>
					<h4>
						<a
							href="<%=request.getContextPath()%>/back-end/manage/member_select.jsp">
							<img src="<%=request.getContextPath()%>/adm/images/back1.gif"
							width="100" height="32" border="0"> 回首頁 //權限用切換按鈕
							//營業登記證用跳窗秀大圖 //確認用跳窗顯示成功 //權限可分類2種狀態
						</a>
					</h4>
				</td>
			</tr>
		</table>

		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>

		<table>
			<tr>
				<th>編號</th>
				<th>名稱</th>
				<th>帳號</th>
				<th>密碼</th>

				<th>電話</th>
				<th>EMAIL</th>
				<th>地址</th>
				<th>會員權限</th>
				<th>會員類型</th>

				<th>營業登記證</th>
				<th>店家負責人</th>
				<th>統一編號</th>
				<th></th>
				<th></th>

			</tr>
			<%@ include file="page1.file"%>
			<c:forEach var="memVO" items="${list}" begin="<%=pageIndex %>"
				end="<%=pageIndex+rowsPerPage-1 %>">


				<tr>
					<td>${memVO.mem_no}</td>
					<td>${memVO.mem_name}</td>
					<td>${memVO.mem_id}</td>
					<td>${memVO.mem_psw}</td>

					<td>${memVO.mem_tel}</td>
					<td>${memVO.mem_email}</td>
					<td>${memVO.mem_add}</td>

					<td>${memVO.mem_per}</td>
					<td>${memVO.mem_type}</td>
					<td>${memVO.mem_store_business}</td>
					<td>${memVO.mem_store_owner}</td>
					<td>${memVO.mem_store_taxid}</td>

					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/MemServlet/mem.do"
							style="margin-bottom: 0px;">
							<input type="submit" value="確認"> <input type="hidden"
								name="mem_no" value="${memVO.mem_no}"> <input
								type="hidden" name="action" value="getOne_For_Update">
						</FORM>
					</td>
					<td>
						<FORM METHOD="post"
							ACTION="<%=request.getContextPath()%>/AdmServlet/adm.do"
							style="margin-bottom: 0px;">
							<input type="submit" value="送出"> <input type="hidden"
								name="adm_no" value="${admVO.adm_no}"> <input
								type="hidden" name="action" value="delete">
						</FORM>
					</td>
				</tr>
			</c:forEach>
		</table>
		<%@ include file="page2.file"%>
	</div>
	<!----------------------------------------------以上-------------------------------------------------------------->

	</div>
	</div>
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

