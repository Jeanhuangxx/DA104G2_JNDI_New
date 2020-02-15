<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>

<%
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	pageContext.setAttribute("memVO", memVO);
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
<title>潛水店家後台</title>
<link rel=stylesheet type="text/css"
	href="<%=request.getContextPath()%>/css/dive.css">
<link
	href="<%=request.getContextPath()%>/vendors/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/store.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/store_info.css"
	rel="stylesheet">
<link href="<%=request.getContextPath()%>/css/admin.css"
	rel="stylesheet">

</head>

<body id="page-top">
	<div id="wrapper">
		<!-- Sidebar -->
		<ul class="navbar-nav sidebar sidebar-light accordion"
			id="accordionSidebar">
			<a
				class="sidebar-brand d-flex align-items-center justify-content-center"
				href="<%=request.getContextPath()%>/index.jsp"> <!-- 潛水店名 -->
				<div class="sidebar-brand-text mx-3 sd_name">潛店後台</div>
			</a>
			<li class="nav-item"><a class="nav-link collapsed"
				href="<%=request.getContextPath()%>/front-end/store/store_trip.jsp"
				data-target="#collapseBootstrap" aria-expanded="true"
				aria-controls="collapseBootstrap"> <i
					class="far fa-fw fa-window-maximize"></i> <span class="trav_mang">行程管理</span>
					<!-- 上架在此  button-->
			</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<%=request.getContextPath()%>/front-end/store/store_trip_order.jsp">
					<i class="fab fa-fw fa-wpforms"></i> <span class="trav_mang">行程訂單管理</span>
			</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<%=request.getContextPath()%>/front-end/store/store_info.jsp">
					<i class="fas fa-fw fa-palette"></i> <span class="trav_mang">店家資料修改</span>
			</a></li>
			<hr class="sidebar-divider">

		</ul>
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
						<li class="nav-item dropdown no-arrow mx-1"><a
							class="nav-link dropdown-toggle" href="#" id="alertsDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <i class="fas fa-bell fa-fw"></i> <span
								class="badge badge-danger badge-counter">3+</span>
						</a>
							<div
								class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="alertsDropdown">
								<h6 class="dropdown-header">Alerts Center</h6>
								<a class="dropdown-item d-flex align-items-center" href="#">
									<div class="mr-3">
										<div class="icon-circle bg-success">
											<i class="fas fa-donate text-white"></i>
										</div>
									</div>
									<div>
										<div class="small text-gray-500">December 7, 2019</div>
										$290.29 has been deposited into your account!
									</div>
								</a>
							</div></li>
						<li class="nav-item dropdown no-arrow mx-1"><a
							class="nav-link dropdown-toggle" href="#" id="messagesDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <i class="fas fa-envelope fa-fw"></i>
								<span class="badge badge-warning badge-counter">2</span>
						</a>
							<div
								class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="messagesDropdown">
								<h6 class="dropdown-header">客戶消息</h6>

								<a class="dropdown-item d-flex align-items-center" href="#">
									<div class="dropdown-list-image mr-3">
										<img class="rounded-circle" src="../images/mem_pic.png"
											style="max-width: 60px" alt="">
										<div class="status-indicator bg-success"></div>
									</div>
									<div class="font-weight-bold">
										<div class="text-truncate">Hi there! I am wondering if
											you can help me with a problem I've been having.</div>
										<div class="small text-gray-500">Jack · 58m</div>
									</div>
								</a> <a class="dropdown-item d-flex align-items-center" href="#">
									<div class="dropdown-list-image mr-3">
										<img class="rounded-circle" src="../images/mem_pic.png"
											style="max-width: 60px" alt="">
										<div class="status-indicator bg-default"></div>
									</div>
									<div>
										<div class="text-truncate">Am I a good boy? The reason I
											ask is because someone told me that people say this to all
											dogs, even if they aren't good...</div>
										<div class="small text-gray-500">小花 · 2w</div>
									</div>
								</a>
							</div></li>

						<div class="topbar-divider d-none d-sm-block"></div>
						<li class="nav-item dropdown no-arrow"><a
							class="nav-link dropdown-toggle" href="#" id="userDropdown"
							role="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false"> <img
								class="img-profile rounded-circle"
								src="<%=request.getContextPath()%>/images/store.png"
								style="max-width: 60px"> <span
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

					<!-- *****************修改區塊 ********************* -->
					<div class="col-md-10 mx-auto" style="margin-bottom: 24px;">
						<div class="card card-signin my-8">
							<div class="card-body">
								<p class="storeInfo_cap card-title text-center">店家資料修改</p>
								<%-- 錯誤表列 --%>
								<c:if test="${not empty errorMsgs}">
									<font style="color: red">請修正以下錯誤:</font>
									<ul>
										<c:forEach var="message" items="${errorMsgs}">
											<li style="color: red">${message}</li>
										</c:forEach>
									</ul>
								</c:if>
								<div class="forgotpw">
									<form class="form-signin" method="post"
										action="<%=request.getContextPath()%>/member/MemberServlet.do"
										name="form1" enctype="multipart/form-data">
										<div class="row">
											<div class="col-6">
												<label for="inputname" class="name">名稱&nbsp;&nbsp;<font
													style="color: red">*</font></label>
												<p>
													<input type="text" name="mem_name" id="inputname"
														class="form-control" placeholder="Name" autofocus
														value="<%=(memVO == null) ? "" : memVO.getMem_name()%>">
												</p>
												<label for="inputaccount" class="name">店家帳號&nbsp;&nbsp;<font
													style="color: red">*</font></label> <span id="showthesame"></span>
												<p>${ memVO.mem_id}</p>

												<label for="pw" class="name">店家密碼&nbsp;&nbsp;<font
													style="color: red">*</font></label>
												<p>
													<input type="password" id="pw" name="pw"
														class="form-control" placeholder="Password" value="">
												</p>
												<label for="ckpw" class="name">確認密碼&nbsp;&nbsp;<font
													style="color: red">*</font></label> <span id="showError"></span>
												<p>
													<input type="password" name="mem_psw" id="ckpw"
														class="form-control" placeholder="Password" value="">
												</p>

											</div>
											<div class="col-6">
												<label for="mem_store_owner" class="name">店家負責人&nbsp;&nbsp;<font
													style="color: red">*</font></label>
												<p>
													<input type="text" name="mem_store_owner"
														id="mem_store_owner" class="form-control" autofocus
														placeholder="Owner"
														value="<%=(memVO == null) ? "" : memVO.getMem_store_owner()%>">
												</p>
												<label for="mem_store_taxid" class="name">店家統一編號&nbsp;&nbsp;<font
													style="color: red">*</font></label>
												<p>
													<input type="text" name="mem_store_taxid"
														id="mem_store_taxid" class="form-control" autofocus
														placeholder="TacID"
														value="<%=(memVO == null) ? "" : memVO.getMem_store_taxid()%>">
												</p>
												<label for="cellphone" class="name">手機&nbsp;&nbsp;<font
													style="color: red">*</font></label>
												<p>
													<input type="text" name="mem_tel" id="cellphone"
														class="form-control" autofocus placeholder="Phone"
														value="0<%=(memVO == null||memVO.getMem_tel()==null) ? "" : memVO.getMem_tel()%>">
												</p>
												<label for="email" class="name">電子信箱&nbsp;&nbsp;<font
													style="color: red">*</font></label>
												<p>
													<input type="email" name="mem_email" id="email"
														class="form-control" autofocus placeholder="E-mail"
														value="<%=(memVO == null) ? "" : memVO.getMem_email()%>">
												</p>
											</div>
										</div>
										<div class="my-style-selector">
											<label for="Address" class="name">地址&nbsp;&nbsp;<font
												style="color: red">*</font></label>
											
											<p><div id="twzipcode"></div>
												<input type="text" name="mem_add" id="address"
													class="form-control" autofocus placeholder="Address"
													value="<%=(memVO == null) ? "" : memVO.getMem_add()%>">
											</p>
										</div>

										<label for="mem_store_business" class="name">營業登記證&nbsp;&nbsp;<font
											style="color: red">*</font></label>
										<div id="showphoto"></div>
										<input type="file" name="mem_store_business"
											class="uplaodfile" id="mem_pic" value=""
											style="border-radius: 0px">

										<hr class="my-4">
										<input type="hidden" name="action" value="">
										<button class="btn btn-lg btn-info btn-block text-uppercase"
											type="submit">送出</button>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
				<footer>
					<!-- 揪 行 裝 社 -->
					<ul class="nav justify-content-center" id="footer">
						<li class="nav-item"><a class="nav-link"
							href="<%=request.getContextPath()%>/front-end/group/listAllGroup.jsp">潛點揪團</a>
						</li>
						<li class="nav-item"><a class="nav-link"
							href="<%=request.getContextPath()%>/front-end/travel-page/travel.jsp">行程</a>
						</li>
						<li class="nav-item"><a class="nav-link"
							href="<%=request.getContextPath()%>/front-end/equipment/equip_shop.jsp">裝備</a>
						</li>
						<li class="nav-item"><a class="nav-link"
							href="<%=request.getContextPath()%>/front-end/album/albumList.jsp">社群</a>
						</li>
					</ul>

					<!-- fake icon -->
					<ul class="nav justify-content-center" id="icon">
						<li class="nav-item"><svg id="fb" width="25px" height="25px"
								viewBox="0 0 96.124 96.123">
                  <path
									d="M72.089,0.02L59.624,0C45.62,0,36.57,9.285,36.57,23.656v10.907H24.037c-1.083,0-1.96,0.878-1.96,1.961v15.803
                    c0,1.083,0.878,1.96,1.96,1.96h12.533v39.876c0,1.083,0.877,1.96,1.96,1.96h16.352c1.083,0,1.96-0.878,1.96-1.96V54.287h14.654
                    c1.083,0,1.96-0.877,1.96-1.96l0.006-15.803c0-0.52-0.207-1.018-0.574-1.386c-0.367-0.368-0.867-0.575-1.387-0.575H56.842v-9.246
                    c0-4.444,1.059-6.7,6.848-6.7l8.397-0.003c1.082,0,1.959-0.878,1.959-1.96V1.98C74.046,0.899,73.17,0.022,72.089,0.02z" />
                </svg></li>
						<li class="nav-item"><svg id="ig" width="25px" height="25px"
								viewBox="0 0 512 512">
                  <path
									d="M352,0H160C71.648,0,0,71.648,0,160v192c0,88.352,71.648,160,160,160h192c88.352,0,160-71.648,160-160V160
                    C512,71.648,440.352,0,352,0z M464,352c0,61.76-50.24,112-112,112H160c-61.76,0-112-50.24-112-112V160C48,98.24,98.24,48,160,48
                    h192c61.76,0,112,50.24,112,112V352z" />
                  <path
									d="M256,128c-70.688,0-128,57.312-128,128s57.312,128,128,128s128-57.312,128-128S326.688,128,256,128z M256,336
                    c-44.096,0-80-35.904-80-80c0-44.128,35.904-80,80-80s80,35.872,80,80C336,300.096,300.096,336,256,336z" />
                  <circle cx="393.6" cy="118.4" r="17.056" />
                </svg></li>
						<li class="nav-item"><svg id="twit" width="25px"
								height="25px" viewBox="0 0 512 512">
                  <path
									d="M512,97.248c-19.04,8.352-39.328,13.888-60.48,16.576c21.76-12.992,38.368-33.408,46.176-58.016
                    c-20.288,12.096-42.688,20.64-66.56,25.408C411.872,60.704,384.416,48,354.464,48c-58.112,0-104.896,47.168-104.896,104.992
                    c0,8.32,0.704,16.32,2.432,23.936c-87.264-4.256-164.48-46.08-216.352-109.792c-9.056,15.712-14.368,33.696-14.368,53.056
                    c0,36.352,18.72,68.576,46.624,87.232c-16.864-0.32-33.408-5.216-47.424-12.928c0,0.32,0,0.736,0,1.152
                    c0,51.008,36.384,93.376,84.096,103.136c-8.544,2.336-17.856,3.456-27.52,3.456c-6.72,0-13.504-0.384-19.872-1.792
                    c13.6,41.568,52.192,72.128,98.08,73.12c-35.712,27.936-81.056,44.768-130.144,44.768c-8.608,0-16.864-0.384-25.12-1.44
                    C46.496,446.88,101.6,464,161.024,464c193.152,0,298.752-160,298.752-298.688c0-4.64-0.16-9.12-0.384-13.568
                    C480.224,136.96,497.728,118.496,512,97.248z" />
                </svg></li>
						<li class="nav-item"><svg id="yt" width="25px" height="25px"
								viewBox="0 0 512 512">
                  <path
									d="M490.24,113.92c-13.888-24.704-28.96-29.248-59.648-30.976C399.936,80.864,322.848,80,256.064,80
                    c-66.912,0-144.032,0.864-174.656,2.912c-30.624,1.76-45.728,6.272-59.744,31.008C7.36,138.592,0,181.088,0,255.904
                    C0,255.968,0,256,0,256c0,0.064,0,0.096,0,0.096v0.064c0,74.496,7.36,117.312,21.664,141.728
                    c14.016,24.704,29.088,29.184,59.712,31.264C112.032,430.944,189.152,432,256.064,432c66.784,0,143.872-1.056,174.56-2.816
                    c30.688-2.08,45.76-6.56,59.648-31.264C504.704,373.504,512,330.688,512,256.192c0,0,0-0.096,0-0.16c0,0,0-0.064,0-0.096
                    C512,181.088,504.704,138.592,490.24,113.92z M192,352V160l160,96L192,352z" />
                </svg></li>
					</ul>
				</footer>
			</div>
		</div>

		<!-- Scroll to top
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a> -->

		<script
			src="<%=request.getContextPath()%>/vendors/jquery/jquery.min.js"></script>
		<script
			src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.bundle.min.js"></script>
		<script
			src="<%=request.getContextPath()%>/vendors/jquery-easing/jquery.easing.min.js"></script>
		<script src="<%=request.getContextPath()%>/js/ruang-admin.min.js"></script>
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
		
		</script>
</body>

</html>