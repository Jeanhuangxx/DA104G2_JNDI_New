<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.mem.model.MemVO"%>
<%@page import="com.equip_order.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
MemVO memVO = (MemVO) session.getAttribute("memVO");
EquipOrderService equipOrderSvc = new EquipOrderService();
List<EquipOrderVO> list = equipOrderSvc.getMem(memVO.getMem_no());
pageContext.setAttribute("list", list);
%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link href="<%=request.getContextPath()%>/images/twdivers.png"
	rel="icon">
<title>會員訂單</title>
<script
	src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.4.1.min.js"></script>
<link rel=stylesheet type="text/css"
	href="<%=request.getContextPath()%>/css/dive.css">
<link
	href="<%=request.getContextPath()%>/vendors/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/member.css"
	rel="stylesheet">
	
	<style>
	table, th, td {
    	border: 1px solid #CCCCFF;
    	font-weight: bold;
	}
 	th, td { 
     	padding: 5px; 
     	text-align: center; 
    	font-weight: bold; 
	}
	body {
		font-family: "Microsoft JhengHei UI";
		font-size: 20px;
		
	}
	.table {
/* 		width: 1000px; */
		margin:5px auto;
		background-color: white;
		font-weight: bold;
	}
	.table tbody tr th {
		line-height: 50px;
		font-weight: bold;
	}
	.table tbody tr td {
		line-height: 50px;
		font-weight: bold;
	}
	#title{
		text-align: center;
		font-size: 30px;
		color:darkblue;
		margin-bottom:30px;
	}
	#homeImg{
		margin-top:10px;
		margin-left:10px;
		font-weight: bold;	
	}
</style>

</head>

<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
<body id="page-top">
	<div id="wrapper">
		<!-- Sidebar -->
		<jsp:include page="/front-end/memberCenter/memNav.jsp" />
		<!-- Sidebar -->
		
		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<!-- TopBar -->
				<nav
					class="navbar navbar-expand navbar-light bg-navbar topbar mb-4 static-top">
					<button id="sidebarToggleTop"
						class="btn btn-link rounded-circle mr-3">
						<i class="fa fa-bars"></i>
					</button>
					<ul class="navbar-nav ml-auto">


						<div class="topbar-divider d-none d-sm-block"></div>
						<li class="nav-item dropdown no-arrow"><a
							class="nav-link dropdown-toggle" href="#" id="userDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <c:if test="${memVO.mem_img != null}">
							<img
								class="img-profile rounded-circle"
								src="<%=request.getContextPath()%>/ShowPic_V1?mem_no=${memVO.mem_no}"
								style="max-width: 60px">
							</c:if>
							<c:if test="${memVO.mem_img == null}">
							<img
								class="img-profile rounded-circle"
								src="<%=request.getContextPath()%>/images/mem_pic.png"
								style="max-width: 60px">
							</c:if> <span
								class="ml-2 d-none d-lg-inline text-white small">${memVO.mem_name}</span>
						</a>
							<div
								class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="userDropdown">
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="<%=request.getContextPath()%>/member/MemberServlet.do?action=logout"> <i
									class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>登出
								</a>
							</div></li>
					</ul>
				</nav>
				<!-- Topbar -->

				<!-- Container Fluid-->
				<div class="container-fluid" id="container-wrapper">
					<div class="d-sm-flex align-items-center justify-content-between mb-4">
<%-- 						<span><font color="blue">${memVO.mem_name}</font>您好，您的購買紀錄如下</span> --%>
						<div id="title">裝備訂單</div>
					</div>
					<table class="table">
					<thead class="thead-dark">
						<tr>
							<th scope="col">訂單編號</th>
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
					<c:forEach var="equiporderVO" items="${list}">
					<tbody>
						<tr>
							<td scope="row">${equiporderVO.equip_order_no}</td>
							<td>${equiporderVO.cus_name}</td>
							<td>0${equiporderVO.cus_tel}</td>
							<td>${equiporderVO.cus_add}</td>
							<td>$${equiporderVO.equip_order_price}</td>
							<td><fmt:formatDate value="${equiporderVO.equip_order_time}" pattern="yyyy-MM-dd HH:mm"/></td>
							<td>${equiporderVO.equip_note}</td>
							<td>
								<c:if test="${equiporderVO.equip_order_status == 1}">
									<font color="blue">處理完成</font>
								</c:if>
								<c:if test="${equiporderVO.equip_order_status == 0}">
									<font color="red">處理中</font>
								</c:if>
							</td>
							<td>${equiporderVO.equip_shipping_date}</td>
							<td>
								<FORM method="post" action="<%=request.getContextPath()%>/odlist/equip_odlist.do">
									<button type="submit" class="btn btn-success">查看明細</button>
									<input type="hidden" name="equip_order_no" value="${equiporderVO.equip_order_no}">
							     	<input type="hidden" name="action" value="getOneOdlist_front">
								</FORM>
							</td>	
						</tr>
					</tbody>
					</c:forEach>
				</table>
				<%if (request.getAttribute("equiporderVO")!=null){%>
	<jsp:include page="/front-end/equip_odlist/mem_odlist.jsp" />
<%} %>
<!-- 					<div> -->
<%-- 						<jsp:include page="/front-end/equip_order/mem_order.jsp" /> --%>
<!-- 					</div> -->
			</div>
					<!---Container Fluid-->
				</div>
				<jsp:include page="/front-end/memberCenter/memFooter.jsp" />
			</div>
		</div>

		<!-- Scroll to top
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a> -->

		<script
			src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.bundle.min.js"></script>
		<script
			src="<%=request.getContextPath()%>/vendors/jquery-easing/jquery.easing.min.js"></script>
		<script src="<%=request.getContextPath()%>/js/ruang-admin.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
</body>
</html>