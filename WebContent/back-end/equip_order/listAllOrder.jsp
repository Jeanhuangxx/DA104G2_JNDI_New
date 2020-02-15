<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.equip_order.model.*"%>

<%
	EquipOrderService equipOrderSvc = new EquipOrderService();
	List<EquipOrderVO> list = equipOrderSvc.getAll();
	pageContext.setAttribute("list",list);

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


	table, th, td {
	    border: 1px solid #CCCCFF;
	}
	th, td {
	    padding: 5px;
	    text-align: center;
	}
	body{
		font-family: "Microsoft JhengHei UI";
		font-size: 17px;
		font-weight: bold;
	}
	.table{
 		width: 1350px;
		margin:10px auto;
	}
	.table tbody tr th{
		line-height: 50px;
	}
	.table tbody tr td{
		line-height: 50px;
	}
	#title{
		text-align: center;
		font-size: 35px;
		color:darkblue;
		margin-bottom:10px;
	}
	.headPosition{
		margin-left:20px;
		margin-bottom:20px;
	}
	.pagePosition{
		text-align: center;
 		margin:10px auto; 
	}
</style>
</head>
<!--------------------------以上--------------------------------->
<body>
	<jsp:include page="/back-end/manage/admNav.jsp" />
	<!-----------------------------------------此區塊放自己的資料----------------------------------------------------->

	<div id="title">所有訂單</div>


	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<div class="headPosition"><%@ include file="page1.file"%></div>


	<div class="container-fluid">
		<table class="table">
			<thead class="thead-dark">
				<tr>
					<th scope="col">訂單編號</th>
					<th scope="col">會員編號</th>
					<th scope="col">收件人姓名</th>
					<th scope="col">收件人電話</th>
					<th scope="col">收件人地址</th>
					<th scope="col">總金額</th>
					<th scope="col">訂單時間</th>
					<th scope="col">備註</th>
					<th scope="col">處理狀態</th>
					<th scope="col">出貨日期</th>
					<th scope="col"></th>
				</tr>
			</thead>

			<c:forEach var="equiporderVO" items="${list}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>">
				<tbody>
					<tr>
						<th scope="row">${equiporderVO.equip_order_no}</th>
						<td>${equiporderVO.mem_no}</td>
						<td>${equiporderVO.cus_name}</td>
						<td>0${equiporderVO.cus_tel}</td>
						<td>${equiporderVO.cus_add}</td>
						<td>${equiporderVO.equip_order_price}</td>
						<td><fmt:formatDate value="${equiporderVO.equip_order_time}"
								pattern="yyyy-MM-dd HH:mm" /></td>
						<td>${equiporderVO.equip_note}</td>
						<td><c:if test="${equiporderVO.equip_order_status == 1}">
								<font color="blue">處理完成</font>
							</c:if> <c:if test="${equiporderVO.equip_order_status == 0}">
								<font color="red">處理中</font>
							</c:if></td>
						<td>${equiporderVO.equip_shipping_date}</td>
						<td>
							<FORM method="post"
								action="<%=request.getContextPath()%>/odlist/equip_odlist.do">
								<button type="submit" class="btn btn-success">訂單明細</button>
								<input type="hidden" name="equip_order_no"
									value="${equiporderVO.equip_order_no}"> <input
									type="hidden" name="action" value="getOneOdlist">
							</FORM>
						</td>
					</tr>
				</tbody>
			</c:forEach>
		</table>
	</div>

	<div class="container-fluid">
		<div class="row">
			<div class="pagePosition"><%@ include file="page2.file"%></div>
		</div>
	</div>

















	<!----------------------------------------------以上-------------------------------------------------------------->
	</div>
	</div>
	</div>

	<footer class="text-center text-secondary">
		&copy; DA104G2 GO ! <span id="year"></span>
	</footer>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/manage/js/poper.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/manage/js/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/manage/js/manager.js"></script>
	</div>

</body>
</html>