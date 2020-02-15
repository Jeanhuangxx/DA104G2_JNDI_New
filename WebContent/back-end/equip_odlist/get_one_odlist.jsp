<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.equip_odlist.model.*"%>
<%@ page import="com.equip_order.model.*"%>
<%@ page import="java.util.*"%>

<%
	List<EquipOdlistVO> list = (ArrayList) request.getAttribute("list");
	EquipOrderVO equiporderVO = (EquipOrderVO) request.getAttribute("equiporderVO");
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
/* 		width: 900px; */
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
		font-size: 32px;
		color:darkblue;
		margin-bottom:10px;
	}
	#price{
		font-size: 20px;
		text-align:center;
		margin-top:30px;
	}


</style>
</head>
<!--------------------------以上--------------------------------->
<body>
	<jsp:include page="/back-end/manage/admNav.jsp" />
	<!-----------------------------------------此區塊放自己的資料----------------------------------------------------->

	<div id="title">
		<%=equiporderVO.getEquip_order_no()%>訂單明細
	</div>
	<div class="container-fluid">
		<table class="table">
			<thead class="thead-dark">
				<tr>
					<th scope="col">裝備名稱</th>
					<th scope="col">購買數量</th>
					<th scope="col">單價</th>
					<th scope="col">小計</th>
				</tr>
			</thead>
			<c:forEach var="equipodlistVO" items="${list}">
				<tbody>
					<tr>
						<td>${equipodlistVO.equip_name}</td>
						<td>${equipodlistVO.buy_amt}</td>
						<td>${equipodlistVO.equip_price}</td>
						<td>${equipodlistVO.buy_amt * equipodlistVO.equip_price}</td>
					</tr>
			</c:forEach>
			</tbody>
		</table>
		<div id="price">
			總金額<font color="red">$<%=equiporderVO.getEquip_order_price()%></font>
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