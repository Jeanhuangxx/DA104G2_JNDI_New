<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.equipment.model.*"%>

<%
	List<EquipmentVO> list = (ArrayList) request.getAttribute("list"); 
	Integer equip_status = (Integer) request.getAttribute("status");
	if (list == null) {
		equip_status = Integer.parseInt(request.getParameter("status"));
		EquipmentService equipSvc = new EquipmentService();
		list = equipSvc.getStatus(equip_status);
	}
	
	pageContext.setAttribute("list", list);
	pageContext.setAttribute("equip_status", equip_status);
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
		font-size: 20px;
		font-weight: bold;
	}
	.table{
/* 		width: 1000px; */
		margin:10px auto;
	}
	.table tbody tr th{
		line-height: 50px;
	}
	.table tbody tr td{
		line-height: 50px;
	}
	.headPosition{
		margin-left:180px;
		margin-top:10px;
		margin-bottom:30px;
	}
	.pagePosition{
		text-align: center;
	}
	#title{
		text-align: center;
		font-size: 35px;
		color:darkblue;
		margin-bottom:30px;
	}
</style>
</head>
<!--------------------------以上--------------------------------->
<body>
		<jsp:include page="/back-end/manage/admNav.jsp" />
<!-----------------------------------------此區塊放自己的資料----------------------------------------------------->

				
<div id="title">
${equip_status == 1 ? "上架" : "下架"}裝備
</div>

<div class="headPosition"><%@ include file="page1.file" %></div>


<div class="container-fluid">
<table class="table">
	<thead class="thead-dark">
		<tr>
			<th scope="col">裝備編號</th>
			<th scope="col">裝備名稱</th>
			<th scope="col">狀態</th>
			<th scope="col">價格</th>
			<th scope="col">更新日期</th>
			<th scope="col"></th>
		</tr>
	</thead>

	<c:forEach var="equipmentVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>"> 
	<tbody>
		<tr  ${(equipmentVO.equip_no==param.equip_no) ? 'bgcolor=#FEDA6E':''}>
			<th scope="row">${equipmentVO.equip_no}</th>
			<td>${equipmentVO.equip_name}</td>
			<td>${equipmentVO.equip_status == 1 ? "上架" : "下架"}</td>
			<td>${equipmentVO.equip_price}</td>
			<td>${equipmentVO.equip_update}</td>
			<td>
				<FORM method="post" action="<%=request.getContextPath()%>/equip/equipment.do">
					<button type="submit" class="btn btn-success">詳情</button>
					<input type="hidden" name="equip_no" value="${equipmentVO.equip_no}">
					<input type="hidden" name="equip_status" value="${equip_status}">
					<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
					<input type="hidden" name="whichPage" value="<%=whichPage%>">
					<input type="hidden" name="action" value="getOne_For_Detail">
				</FORM>  
			</td>
		</tr>
	</tbody>
	</c:forEach>
</table>
<div class="pagePosition"><%@ include file="page3.file" %></div>
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
		<script
			src="<%=request.getContextPath()%>/back-end/manage/js/poper.js"></script>
		<script
			src="<%=request.getContextPath()%>/back-end/manage/js/bootstrap.min.js"></script>
		<script
			src="<%=request.getContextPath()%>/back-end/manage/js/manager.js"></script>
	</div>

</body>
</html>