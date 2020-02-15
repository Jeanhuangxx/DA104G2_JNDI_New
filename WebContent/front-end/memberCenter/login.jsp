<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css">
<!-- dive.css -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/login.css">
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/dive.css"> --%>

<title>Login</title>
</head>
<body>
	<!-- ======================NAVBAR============================= -->
	<nav class="navbar navbar-expand-lg fixed-top" id="nav">
		<a href="<%=request.getContextPath()%>/front-end/index.jsp"><img
			src="<%=request.getContextPath()%>/images/twdivers-light.png"
			id="n_img" class="navbar-brand"></a>
		<button class="navbar-toggler navbar-light" type="button"
			data-toggle="collapse" data-target="#navbarNav"
			aria-controls="navbarNav" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link"
					href="<%=request.getContextPath()%>/front-end/group/listAllGroup.jsp">潛點揪團</a></li>
				<li class="nav-item"><a class="nav-link"
					href="<%=request.getContextPath()%>/front-end/travel-page/travel.jsp">行程</a></li>
				<li class="nav-item"><a class="nav-link"
					href="<%=request.getContextPath()%>/front-end/equipment/equip_shop.jsp">裝備</a></li>
				<li class="nav-item"><a class="nav-link"
					href="<%=request.getContextPath()%>/front-end/album/albumList.jsp">相簿</a></li>
			</ul>

			<c:if test="${empty sessionScope.account}">
				<a class="user_name"
					href="<%=request.getContextPath()%>/front-end/member/login.jsp">
					<button type="button" class="btn btn-outline-warning" id="btn_log"
						data-toggle="modal" data-target="#exampleModalCenter">登入</button>
				</a>
			</c:if>

			<c:if test="${not empty sessionScope.account && memVO.mem_type == 0}">
				<a class="user_name"
					href="<%=request.getContextPath()%>/front-end/travel-page/travel.jsp"><span>${memVO.mem_name}</span></a>
				<a
					href="<%=request.getContextPath()%>/member/MemberServlet.do?action=logout"><button
						type="button" class="btn" id="btn_out" data-toggle="modal"
						data-target="#exampleModalCenter">登出</button></a>
			</c:if>

			<c:if test="${not empty sessionScope.account && memVO.mem_type == 1}">
				<a class="user_name"
					href="<%=request.getContextPath()%>/front-end/store/store.jsp"><span>${memVO.mem_name}</span></a>
				<a
					href="<%=request.getContextPath()%>/member/MemberServlet.do?action=logout"><button
						type="button" class="btn" id="btn_out" data-toggle="modal"
						data-target="#exampleModalCenter">登出</button></a>
			</c:if>

		</div>
	</nav>


	<!--====================登入區塊===========================  -->
	<div class="container logging">
		<div class="row">
			<div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
				<div class="card card-signin my-5">
					<div class="card-body">
						<h5 class="card-title text-center">會員登入</h5>
						<div class="forgotpw">
							<form class="form-signin"
								ACTION="<%=request.getContextPath()%>/member/MemberServlet.do">
								<label for="inputaccount">帳號</label>
								<p>
									<input type="text" name="inputAccount" id="inputaccount"
										class="form-control" placeholder="Account" required autofocus>
								</p>
								<label for="inputPassword">密碼</label>
								<p>
									<input type="password" id="inputPassword" class="form-control"
										name="inputPassword" placeholder="Password" required>
								</p>
								<div class="custom-control custom-checkbox mb-3" align="center">
									<input type="checkbox" class="custom-control-input"
										id="customCheck1"> <label class="custom-control-label"
										for="customCheck1">記住密碼</label>
								</div>
								<div style="height: 50px"></div>
								<p align="center">
									<label> <a
										href='<%=request.getContextPath()%>/front-end/member/addMem.jsp'>
											一般會員註冊</a> / <a href='addStore.jsp'> 店家註冊</a>
									</label>
								</p>
								<input type="hidden" name="action" value="login">
								<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
									in</button>
							</form>
							<!--做監聽器-->
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-----------------------------------footer---------------------------------------->
	<footer>
		<div class="logo">
			<a href="<%=request.getContextPath()%>/index.jsp"><img
				src="<%=request.getContextPath()%>/images/twdivers.png"
				class="logo_img" alt="tw_logo"></a>
		</div>
	</footer>
	<script
		src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.4.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>