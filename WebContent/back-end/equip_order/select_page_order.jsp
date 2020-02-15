<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

	body{
		font-family: "Microsoft JhengHei UI";
		font-size: 22px;
		font-weight: bold;
	}
	.li{
		margin-top:30px;
		margin-left:50px;
	}
	
	.form-control.memNo, .form-control.equipOrderNo{
		display:inline;
		width:auto;
	}
</style>
</head>
<!--------------------------以上--------------------------------->
<body>
	<jsp:include page="/back-end/manage/admNav.jsp" />
	<!-----------------------------------------此區塊放自己的資料----------------------------------------------------->
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>



		<!-- 審核剛成立訂單 -->
		<div class="li">
			●<a
				href='<%=request.getContextPath()%>/back-end/equip_order/newOrder.jsp'>
				審核</a>訂單
		</div>


		<!-- 查詢所有訂單 -->
		<div class="li">
			●<a
				href='<%=request.getContextPath()%>/back-end/equip_order/listAllOrder.jsp'>
				查詢</a>所有訂單
		</div>


		<!-- 手動查會員訂單 -->
		<FORM method="post"
			action="<%=request.getContextPath()%>/order/equip_order.do">
			<div class="li">
				● 依會員編號查詢 (如M000001) <input type="text" name="mem_no"
					value="M000001" class="form-control memNo"> <input type="hidden" name="action"
					value="getMem">
				<button type="submit" class="btn btn-success">送出</button>
			</div>
		</FORM>


		<!-- 下拉選單查單筆訂單 -->
		<jsp:useBean id="equipOrderSvc" scope="page"
			class="com.equip_order.model.EquipOrderService" />
		<FORM method="post"
			action="<%=request.getContextPath()%>/order/equip_order.do">
			<div class="li">
				● 選擇訂單編號 <select size="1" name="equip_order_no" class="form-control equipOrderNo">
					<c:forEach var="equiporderVO" items="${equipOrderSvc.all}">
						<option value="${equiporderVO.equip_order_no}">${equiporderVO.equip_order_no}
					</c:forEach>
				</select> <input type="hidden" name="action" value="getOne_For_Display">
				<button type="submit" class="btn btn-success">送出</button>
			</div>
		</FORM>















	</div>
	</div>
	</div>
	<!----------------------------------------------以上-------------------------------------------------------------->

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