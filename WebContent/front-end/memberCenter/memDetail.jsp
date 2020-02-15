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
							</c:if> 
							<span class="ml-2 d-none d-lg-inline text-white small">${memVO.mem_name}</span>
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
					<div>
						<a href="<%=request.getContextPath()%>/front-end/memberCenter/updateMem.jsp">
							<button class="btn btn-primary">修改資料</button>
						</a>
						<p class="card-title text-center memDetailInfo mb-3">《 會 員 資 訊 》</p>
					</div>
					<form class="form-signin" method="post"
						action="<%=request.getContextPath()%>/member/MemberServlet.do"
						name="form1" enctype="multipart/form-data">
						<div class="row memInfoDiv mx-auto">
							<div class="col-6">
								<p class="pplInfo">個人資料</p>
								<div class="row">
									<div class="col-6" style="line-height:25px">
										<p class="memInsideInfo">姓名</p>
										<p class="memInsideInfo">會員帳號&nbsp;&nbsp;</p>
										<p class="memInsideInfo">會員密碼&nbsp;&nbsp;</p>
										<p class="memInsideInfo">性別</p>
										<p class="memInsideInfo">生日</p>
									</div>
									<div class="col-6">
										<p>${memVO.mem_name}</p>
										<p>${memVO.mem_id}</p>
										<input type="password" id="pw" class="form-control memPw"
												placeholder="Password" value="${memVO.mem_psw}"
												readonly="readonly">
										<p>${memGenderStatus.get(memVO.mem_general_gen)}</p>
										<p>${memVO.mem_general_bd}</p>
									</div>
								</div>
							</div>
							<div class="col-6 text-center my-auto">
								<c:choose>
									<c:when test="${memVO.mem_img!=null}">
										<img alt=""
											src="<%=request.getContextPath()%>/ShowPic_V1?mem_no=${memVO.mem_no}"
											width="250px" height="250px">
									</c:when>
									<c:otherwise>
										<img alt=""
											src="<%=request.getContextPath()%>/images/mem_pic.png"
											width="250px" height="250px">
									</c:otherwise>
								</c:choose>
							</div>
						</div>
<!-- 						<hr class="my-4"> -->
						<div class="row memConDiv mx-auto">
							<div class="col-12">
								<p class="pplInfo">聯絡資料</p>
								<div class="row">
									<div class="col-3" style="line-height:25px">
										<p class="memInsideInfo">電子信箱</p>
										<p class="memInsideInfo">手機</p>
										<p class="my-style-selector">
											<label class="memInsideInfo">地址</label>
										</p>
									</div>
									<div class="col-6" style="line-height:25px">
										<p>${memVO.mem_email}</p>
										<p>0${memVO.mem_tel}</p>
										<p>${memVO.mem_add}</p>
									</div>
								</div>	
							</div>
						</div>
					</form>
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