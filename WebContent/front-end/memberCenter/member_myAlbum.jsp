<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@page import="com.mem.model.*"%>

<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
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
<title>會員後台</title>
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
<style type="text/css">
.trip_manage {
	font-size: 30px;
	font-family: "Microsoft JhengHei UI";
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
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<span class="trip_manage">我的相簿</span>
					</div>
					<jsp:include page="/front-end/album/ownList.jsp" />
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