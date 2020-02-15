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
					<h5 class="card-title text-center memDetailInfo mb-3">《 會員資料修改 》</h5>
					<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color: red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
					<form class="form-signin" method="post"
						action="<%=request.getContextPath()%>/member/MemberServlet.do"
						name="form1" enctype="multipart/form-data">
						<div class="row memInfoDiv mx-auto">
							<div class="col-6">
								<p class="pplInfo">個人資料</p>
								<div class="row">
									<div class="col-6" style="line-height:25px">
										<p><label for="inputname">姓名</label></p>
										<p>會員帳號&nbsp;&nbsp;</p>
										<label for="pw" style="margin-bottom: 22px;">會員密碼&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<button class="btn change_psw" type="button">修改密碼</button>
										</label>
										<label for="ckpw" hidden="none" id="lb_ckpw" style="margin-bottom: 16px;">確認密碼&nbsp;&nbsp;<font
										style="color: red;">*</font></label>
										<div id="showError"></div>

										<p style="margin-bottom: 16px;">性別</p>
										<p><label for="inputDate">生日</label></p>
									</div>
									<div class="col-6">
										<p>
											<input type="text" name="mem_name" id="inputname"
												class="form-control" placeholder="Name" autofocus
												value="${memVO.mem_name}">
										</p>
										
										<p>${memVO.mem_id}</p>
										<p>
											<input type="password" id="pw" class="form-control"
												placeholder="" value="${memVO.mem_psw}" autofocus
												disabled="disabled">
										</p>
										<p>
											<input type="password" hidden="none" name="mem_psw" id="ckpw"
												class="form-control mb-3" placeholder="" value="${memVO.mem_psw}"
												autofocus>
										</p>

										<p class="sex">
											<label for="choose"><input type="radio" id="choose"
												name="mem_general_gen" value="0" checked>不願透露</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
											<label for="male"><input type="radio" id="male"
												name="mem_general_gen" value="1">男生</label>
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label for="female"><input
												type="radio" id="female" name="mem_general_gen" value="2">女生
											</label>
										</p>
										<p>
											<input type="date" name="mem_general_bd" id="birth"
												class="form-control" autofocus
												value="${memVO.mem_general_bd }">
										</p>
									</div>
								</div>
							</div>
									<div class="col-6 text-center my-auto">
										<div id="showpic">
											<c:choose>
												<c:when test="${memVO.mem_img!=null}">
													<img alt="" id="showphoto"
														src="<%=request.getContextPath()%>/ShowPic_V1?mem_no=${memVO.mem_no}"
														width="250px" height="250px">
												</c:when>
												<c:otherwise>
													<img alt="" id="showphoto"
														src="<%=request.getContextPath()%>/images/mem_pic.png"
														width="250px" height="250px">
												</c:otherwise>
											</c:choose>
										</div>
										<div>
											<label for="mem_img">
											<div class="changeImg" style="border: 3px solid black; border-radius: 10px; margin-top:10px; width: 100px;" class="d-flex justify-content-center">選擇大頭貼</div>
													</label>
													<input
												type="file" name="mem_img" class="uplaodfile" id="mem_img"
												value="" style="border-radius: 0px; display: none;">
										</div>
									</div>
									
							
						</div>

						<div class="row memConDiv mx-auto">
							<div class="col-12">
								<p class="pplInfo">聯絡資料</p>
								<div class="row mb-4">
									<div class="col-3" style="line-height:35px">
										<label for="email">電子信箱&nbsp;&nbsp;<font
										style="color: red">*</font></label>
										<p></p>
										<label for="cellphone">手機</label>
										<p></p>
										<label for="Address">地址</label>
									</div>
									<div class="col-5" style="line-height:25px">
										<p>
											<input type="email" name="mem_email" id="email"
												class="form-control" autofocus placeholder="E-mail"
												value="${memVO.mem_email}">
										</p>
										<p>
											<input type="text" name="mem_tel" id="cellphone"
												class="form-control" autofocus placeholder="Phone"
												value="<%=(memVO == null||memVO.getMem_tel()==null) ? "" : memVO.getMem_tel()%>">
										</p>
											<div class="my-style-selector">
									
										<span>
											<!-- Normal -->
											<div id="twzipcode"></div>
										</span>
										<span>
											<input type="text" name="mem_add" id="address"
												class="form-control" autofocus placeholder="Address"
												value="${memVO.mem_add }">
										</span>
									</div>
									</div>
								</div>


							</div>
								<input type="hidden" name="action" value="updaetForGeneral">
								<input type="hidden" name="mem_no" value="${memVO.mem_no}">
								<button class="btn btn-lg btn-info btn-block text-uppercase"
									type="submit">送出</button>
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
	<script src="<%=request.getContextPath()%>/vendors/TWzipcode-master/jquery.twzipcode.min.js"></script>
	<script type="text/javascript">
		
		//取得會員地址資料
		$( document ).ready(function(){
				$('#twzipcode').twzipcode();
				var addressStr;
				var country;
				var district;
				var addressStr;
				var length;
				//切割${memVO.mem_add }"字串成縣市、鄉鎮、地址
					addressStr = "${memVO.mem_add }";
					//縣市
					country = addressStr.substr(0,3);
					//鄉政
					district = addressStr.substring(3,6);
					//地址
					length = addressStr.length
					addressStr = addressStr.substring(6,length);

				//放入縣市、鄉鎮預設值(twzipcode下拉選項)
					$("#twzipcode").twzipcode('set', {
					    'county': country,
					    'district': district,
					    'zipcode': 110
					});

				//放入地址預設值
					$('#address').val(addressStr);
				//將twzipcode的縣市、鄉鎮轉換成QueryString
					var qs = $("#twzipcode").twzipcode('serialize');
		});
	
	
		$(".change_psw").click(function() {
			$("#pw").removeAttr("disabled");
			$("#pw").val("");
			$("#ckpw").removeAttr("hidden");
			$("#lb_ckpw").removeAttr("hidden");
			$("#ckpw").val("");
		})

		$(".uplaodfile")
				.change(
						function(e) {
							let mem_img = document.getElementById("mem_img");
							let exAllowed = [ "bmp", "gif", "png", "jpg", "ico" ];
							let fileStr = e.target.value.toLowerCase();
							let fileName, fileExt;

							let dot = fileStr.lastIndexOf(".");
							fileExt = fileStr.substr(dot + 1);

							if (exAllowed.indexOf(fileExt) == -1) {
								document.getElementById("showpic").innerHTML = "<font class='warn' color='red'>&nbsp格式不合！<br>&nbsp請您再挑一張吧。</font><br>";
							} else {
								let photo = URL
										.createObjectURL(mem_img.files[0]);
								document.getElementById("showpic").innerHTML = "<img src ="
					+ photo + " width='380px' height='280px'>";
							}
						})
	</script>
</body>
</html>