<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adm.model.*"%>
<%@ page import="com.fun.model.*"%>
<%@ page import="com.pe.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	AdmVO admVO = (AdmVO) request.getAttribute("admVO");

	FunService funSvc = new FunService();
	List<FunVO> funlist = funSvc.getAll();
	pageContext.setAttribute("funlist", funlist);

	PeService peSvc = new PeService();
	List<PeVO> pelist = peSvc.getAll();
	pageContext.setAttribute("pelist", pelist);
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
	background: rgb(255, 255, 255, 0.5);
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

.img-circle {
	border-radius: 50%;
}
</style>
</head>
<!--------------------------以上--------------------------------->

<body>
	<jsp:include page="/back-end/manage/admNav.jsp" />
	<!-----------------------------------------此區塊放自己的資料----------------------------------------------------->
		<div class="row">
			<!-- Column -->

			<div class="col-lg-3 ml-5">
				<div class="card" style="padding-bottom:10px;">
					<div class="card-block">
						<center class="m-t-30">
							<br> <br> <img
								src="<%=request.getContextPath() %>/ShowPic_V1?adm_no=${admVO.adm_no}"
								class="img-circle" width="200" />
							<h4 class="card-title m-t-10">
								<br>${admVO.adm_name}
							</h4>
							<h6 class="card-subtitle">
								<br>DA104G2 GO!<br> <br> <br>
							</h6>
							<div class="row text-center justify-content-md-center">
								<div class="col-4">
									<i class="icon-people"></i> <font class="font-medium"></font></a>
								</div>
								<div class="col-4">
									<i class="icon-picture"></i> <font class="font-medium"></font></a>
								</div>
							</div>
							<a
										href="<%=request.getContextPath()%>/back-end/manage/manage_listall.jsp"
										class="btn btn-success mt-2">所有管理員</a> <a
										href="<%=request.getContextPath()%>/back-end/manage/manage_select.jsp"
										class="btn btn-success mt-2">查詢管理員</a> <a
										href="<%=request.getContextPath()%>/back-end/manage/manage_add.jsp"
										class="btn btn-success mt-2">新增管理員</a>
						</center>
					</div>
				</div>
			</div>

			<!-- Column -->
			<div class="col-7">
				<div class="card">
					<div class="card-block">
						<form class="form-horizontal form-material" METHOD="post"
							ACTION="<%=request.getContextPath()%>/AdmServlet/adm.do"
							name="form1" enctype="multipart/form-data">

							<div class="form-group">

								<tr>
									<td><br> <bt class="ml-3">管理員編號 :</td>
								</tr>

								<tr>
									<td>${admVO.adm_no}</td>

								</tr>
							</div>

							<div class="form-group">
								<label class="col-md-12">管理員ID</label>
								<div class="col-md-12">
									<input type="text" name="adm_id" placeholder="${admVO.adm_id}"
										class="form-control form-control-line" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-12">管理員密碼</label>
								<div class="col-md-12">
									<input type="text" name="adm_psw"
										placeholder="${admVO.adm_psw}"
										class="form-control form-control-line" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-12">姓名</label>
								<div class="col-md-12">
									<input type="text" name="adm_name"
										placeholder="${admVO.adm_name}"
										class="form-control form-control-line" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-12">手機</label>
								<div class="col-md-12">
									<input type="text" name="adm_tel"
										placeholder="${admVO.adm_tel}"
										class="form-control form-control-line" />
								</div>
							</div>
							<div class="form-group">
								<label for="example-email" class="col-md-12">Email</label>
								<div class="col-md-12">
									<input type="email" name="adm_email"
										placeholder="${admVO.adm_email}"
										class="form-control form-control-line" id="example-email">
								</div>
							</div>

							<jsp:useBean id="AdmSvc" scope="page"
								class="com.adm.model.AdmService" />


							<div class="form-group">
							<label><h5 class="col-sm-12">管理員權限</h5></label>
								
								<div class="col-sm-12">
									<td><c:forEach var="pe" items="${pelist}">
											<c:forEach var="funVO" items="${funlist}">
												<c:if test="${pe.adm_no eq admVO.adm_no}">
													<c:if test="${pe.fun_no eq funVO.fun_no}">	
			【${funVO.fun_name}】<br>
													</c:if>
												</c:if>
											</c:forEach>
										</c:forEach></td>
								</div>
							</div>


							<div class="form-group">
								<div class="col-sm-12">
									
								</div>
							</div>

						</form>
					</div>
				</div>
			</div>
			<!-- Column -->


		</div>
		<!----------------------------------------------以上-------------------------------------------------------------->
	</div>
	</div>
	<!-- <div class="login"><button class="btn btn-outline-info">管理員登入</button></div> -->
	<footer class="text-center text-secondary" style="height: 86px;">
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