<%@page import="com.mem.model.MemVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	MemVO memVO = (MemVO) request.getSession().getAttribute("memVO");
	pageContext.setAttribute("memVO", memVO);
%>

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
<link rel=stylesheet type="text/css"
	href="<%=request.getContextPath()%>/css/memDetail.css">
<title>Login</title>

</head>




<body>
	<!-- navigation bar -->
	<nav class="navbar navbar-expand-lg fixed-top" id="nav">
		<a href="<%=request.getContextPath()%>/index.jsp"><img
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
			<c:choose>
				<c:when
					test="${not empty sessionScope.account && memVO.mem_type == 0}">
					<c:choose>
						<c:when test="${memVO.mem_img!=null}">
							<a class="user_name"
								href="<%=request.getContextPath()%>/front-end/memberCenter/memDetail.jsp">
								<span>
										<img alt=""
										src="<%=request.getContextPath()%>/ShowPic_V1?mem_no=${memVO.mem_no}"
										style="border-radius:50%;" width='40px' height='40px'>
								</span>
								<span>${memVO.mem_name}</span>
							</a>
						</c:when>
						<c:otherwise>
							<a class="user_name"
								href="<%=request.getContextPath()%>/front-end/memberCenter/memDetail.jsp"><span>
									<img alt=""
									src="<%=request.getContextPath()%>/images/mem_pic.png"
									style="border-radius:50%;" width='40px' height='40px'>
							</span>
							<span>${memVO.mem_name}</span>
							</a>
						</c:otherwise>
					</c:choose>
					<a
						href="<%=request.getContextPath()%>/member/MemberServlet.do?action=logout"><button
							type="button" class="btn" id="btn_out" data-toggle="modal"
							data-target="#exampleModalCenter">登出</button></a>
				</c:when>
				<c:when
					test="${not empty sessionScope.account && memVO.mem_type == 1}">
					<a class="user_name"
						href="<%=request.getContextPath()%>/front-end/store/store.jsp"><span><img
							alt="" src="<%=request.getContextPath()%>/images/store.png"
							style="border-radius:50%;"width='40px' height='40px'></span>
							<span>${memVO.mem_name}</span>
					</a>
					<a
						href="<%=request.getContextPath()%>/member/MemberServlet.do?action=logout"><button
							type="button" class="btn" id="btn_out" data-toggle="modal"
							data-target="#exampleModalCenter">登出</button></a>
				</c:when>
				<c:when test="${empty sessionScope.account}">
					<a class="user_name"
						href="<%=request.getContextPath()%>/front-end/member/login.jsp">
						<button type="button" class="btn btn-outline-warning" id="btn_log"
							data-toggle="modal" data-target="#exampleModalCenter">登入</button>
					</a>
				</c:when>
			</c:choose>
		</div>
	</nav>



	<!--====================側邊欄區塊===========================  -->

	<div class="row no-gutters">
		<div class="col-3 list">
			<ul class="menu list-group text-right w-50 mt-4 ml-3">
				<li class="list-group-item list-group-item-success d-block"><a
					class="h-100 d-block" href="#">會員</a>
					<ul class="list-group text-right">
						<li class="list-group-item list-group-item-success"><a
							class=""
							href="<%=request.getContextPath()%>/front-end/member/updateMem.jsp">資訊修改</a></li>
					</ul></li>
				<li class="list-group-item list-group-item-success d-block"><a
					class="h-100 d-block" href="#">個人社群</a>
					<ul class="list-group text-right">
						<li class="list-group-item list-group-item-success"><a
							class="h-100 d-block" href="#">個人相簿</a></li>
						<li class="list-group-item list-group-item-success"><a
							class="h-100 d-block" href="#">個人文章</a></li>
					</ul></li>
				<li class="list-group-item list-group-item-success d-block"><a
					class="h-100 d-block" href="#">購物車</a>
					<ul class="list-group text-right">
						<li class="list-group-item list-group-item-success"><a
							class="h-100 d-block" href="#">訂單查詢</a></li>
						<li class="list-group-item list-group-item-success"><a
							class="h-100 d-block" href="#">追蹤清單</a></li>
					</ul></li>
				<li class="list-group-item list-group-item-success d-block"><a
					class="h-100 d-block" href="#">行程/揪團</a>
					<ul class="list-group text-right">
						<li class="list-group-item list-group-item-success"><a
							class="h-100 d-block" href="#">???</a>
						<li class="list-group-item list-group-item-success"><a
							class="h-100 d-block" href="#">???</a></li>
					</ul></li>
			</ul>
		</div>
		<!-- *****************修改區塊 ********************* -->
		<div class="col-6">
			<div class="card card-signin my-8">
				<div class="card-body">
					<h5 class="card-title text-center">《 會員資料修改 》</h5>
					<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color: red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
					<div class="forgotpw" class="container">
						<form class="form-signin" method="post"
							action="<%=request.getContextPath()%>/member/MemberServlet.do"
							name="form1" enctype="multipart/form-data">
							<div class="row">
								<div class="col-6">
									<label for="inputname">名稱&nbsp;&nbsp;<font
										style="color: red">*</font></label>
									<p>
										<input type="text" name="mem_name" id="inputname"
											class="form-control" placeholder="Name" autofocus
											value="<%=(memVO == null) ? "" : memVO.getMem_tel()%>">
									</p>
									<label for="inputaccount">店家帳號&nbsp;&nbsp;<font
										style="color: red">*</font></label> <span id="showthesame"></span>
									<p>${ memVO.mem_id}</p>

									<label for="pw">店家密碼&nbsp;&nbsp;<font
										style="color: red">*</font></label>
									<p>
										<input type="password" id="pw" name="pw" class="form-control"
											placeholder="Password" value="">
									</p>
									<label for="ckpw">確認密碼&nbsp;&nbsp;<font
										style="color: red">*</font></label> <span id="showError"></span>
									<p>
										<input type="password" name="mem_psw" id="ckpw"
											class="form-control" placeholder="Password" value="">
									</p>

								</div>
								<div class="col-6">
									<label for="mem_store_owner">店家負責人&nbsp;&nbsp;<font
										style="color: red">*</font></label>
									<p>
										<input type="text" name="mem_store_owner" id="mem_store_owner"
											class="form-control" autofocus placeholder="Owner"
											value="<%=(memVO == null) ? "" : memVO.getMem_store_owner()%>">
									</p>
									<label for="mem_store_taxid">店家統一編號&nbsp;&nbsp;<font
										style="color: red">*</font></label>
									<p>
										<input type="text" name="mem_store_taxid" id="mem_store_taxid"
											class="form-control" autofocus placeholder="TacID"
											value="<%=(memVO == null) ? "" : memVO.getMem_store_taxid()%>">
									</p>
									<label for="cellphone">手機&nbsp;&nbsp;<font
										style="color: red">*</font></label>
									<p>
										<input type="text" name="mem_tel" id="cellphone"
											class="form-control" autofocus placeholder="Phone"
											value="<%=(memVO == null) ? "" : memVO.getMem_tel()%>">
									</p>
									<label for="email">電子信箱&nbsp;&nbsp;<font
										style="color: red">*</font></label>
									<p>
										<input type="email" name="mem_email" id="email"
											class="form-control" autofocus placeholder="E-mail"
											value="<%=(memVO == null) ? "" : memVO.getMem_email()%>">
									</p>
								</div>
							</div>
							<div class="my-style-selector">
								<label for="Address">地址&nbsp;&nbsp;<font
									style="color: red">*</font></label>
								<P>
									<select id="city" name="city"
										style="width: 140px; height: 38px"></select> <select
										style="width: 140px; height: 38px" id="district"
										name="district"></select>
								</P>
								<p>
									<input type="text" name="mem_add" id="address"
										class="form-control" autofocus placeholder="Address"
										value="<%=(memVO == null) ? "" : memVO.getMem_add()%>">
								</p>
							</div>

							<label for="mem_store_business">營業登記證&nbsp;&nbsp;<font
								style="color: red">*</font></label>
							<div id="showphoto"></div>
							<input type="file" name="mem_store_business" class="uplaodfile"
								id="mem_pic" value="" style="border-radius: 0px">

							<hr class="my-4">
							<input type="hidden" name="action" value="">
							<button class="btn btn-lg btn-info btn-block text-uppercase"
								type="submit">送出</button>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div class="col-3"></div>
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



	<script type="text/javascript">
		$(document).ready(function() {
			$("ul.menu > li > a").click(function(e) {
				e.preventDefault();
				let el = e.target.nodeName;
				if (el != "A") {
					return;
				}
				// $(this).next().toggleClass("next-menu",3000);
				// $(this).parent().siblings().find("ul").removeClass("next-menu",3000);
				$(this).next().slideToggle(500);
				$(this).parent().siblings().find('ul').slideUp(500);

			});
			// copy right year update
			(function() {
				let year = new Date().getFullYear();
				$("#year").text(year);
			}());
		})

		$(".change_psw").click(function() {
			$("#pw").removeAttr("disabled");
			$("#pw").val("");
			$("#ckpw").removeAttr("hidden");
			$("#lb_ckpw").removeAttr("hidden");
			$("#ckpw").val("");
		})

		// 		function ckthesame(e) {
		// 			let pw = document.getElementById("pw");
		// 			let ckwp = document.getElementById("ckpw");
		// 			let showError = document.getElementById("showError");

		// 			if (pw.value === ckwp.value && ckwp != "") {
		// 				showError.innerHTML = "<font style='color:green;font-weight:Bold;font-size:small'>確認OK!</font>";
		// 			} else {
		// 				showError.innerHTML = "<font style='color:red;font-weight:Bold;font-size:small'>密碼不一致</font>";
		// 				ckwp.value = "";
		// 			}
		// 		}
		// 		document.getElementById("mem_pic").onchange = ckthesame;
		// 		document.getElementById("pw").onchange = ckthesame;
	</script>
</body>

</html>