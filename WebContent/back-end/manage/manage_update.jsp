<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adm.model.*"%>
<%@ page import="com.fun.model.*"%>
<%@ page import="com.pe.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	//AdmVO admVO = (AdmVO) request.getAttribute("admVO");
	AdmVO admVO = (AdmVO) session.getAttribute("admVO");

	FunService funSvc = new FunService();
	List<FunVO> funlist = funSvc.getAll();
	pageContext.setAttribute("funlist", funlist);

	//PeService peSvc = new PeService();
	//List<PeVO> pelist = peSvc.getAll();
	//pageContext.setAttribute("pelist", pelist);
	List<String> pelist = (List<String>) request.getAttribute("pelist");
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

<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script
	src="<%=request.getContextPath()%>/back-end/manage/js/jquery-labelauty.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/manage/css/jquery-labelauty.css">

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

input.labelauty+label>span.labelauty-unchecked-image {
	background-image: url(<%=request.getContextPath()%>/back-end/manage/images/input-unchecked.png);
}

input.labelauty+label>span.labelauty-checked-image {
	background-image: url(<%=request.getContextPath()%>/back-end/manage/images/input-checked.png);
}
</style>
</head>

<!--------------------------以上--------------------------------->

<body>
	<jsp:include page="/back-end/manage/admNav.jsp" />
	<!-----------------------------------------此區塊放自己的資料----------------------------------------------------->
	<div class="col-10.5">
		<div class="row">
			<!-- Column -->
			<div class="col-lg-3 ml-5">
				<div class="card">
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
						</center>
					</div>
				</div>
			</div>

			<!-- Column -->
			<div class="col-lg-6">
				<div class="card">
					<div class="card-block">

						<form class="form-horizontal form-material" METHOD="post"
							ACTION="<%=request.getContextPath()%>/AdmServlet/adm.do"
							name="form1" enctype="multipart/form-data">

							<%-- 錯誤表列 --%>
							<c:if test="${not empty errorMsgs}">
								<font style="color: red">請修正以下錯誤:</font>
								<ul>
									<c:forEach var="message" items="${errorMsgs}">
										<li style="color: red">${message}</li>
									</c:forEach>
								</ul>
							</c:if>

							<div class="form-group">
								<tr>
									<td><br> <bt>管理員編號 :</td>

									<td>${admVO.adm_no}</td>

								</tr>
							</div>

							<div class="form-group">
								<label class="col-md-12">管理員ID</label>
								<div class="col-md-12">
									<input type="text" name="adm_id" placeholder="${admVO.adm_id}"
										class="form-control form-control-line" value="${admVO.adm_id}" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-12">管理員密碼</label>
								<div class="col-md-12">
									<input type="text" name="adm_psw"
										placeholder="${admVO.adm_psw}"
										class="form-control form-control-line"
										value="${admVO.adm_psw}" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-md-12">姓名</label>
								<div class="col-md-12">
									<input type="text" name="adm_name"
										placeholder="${admVO.adm_name}"
										class="form-control form-control-line"
										value="${admVO.adm_name}" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-12">手機</label>
								<div class="col-md-12">
									<input type="text" name="adm_tel"
										placeholder="${admVO.adm_tel}"
										class="form-control form-control-line"
										value="${admVO.adm_tel}" />
								</div>
							</div>
							<div class="form-group">
								<label for="example-email" class="col-md-12">Email</label>
								<div class="col-md-12">
									<input type="email" name="adm_email"
										placeholder="${admVO.adm_email}"
										class="form-control form-control-line" id="example-email"
										value="${admVO.adm_email}" />
								</div>
							</div>

							<jsp:useBean id="admSvc" scope="page"
								class="com.adm.model.AdmService" />

							<div class="form-group">
								<label class="col-md-12">照片</label>
								<div class="col-md-12">
									<input type="file" name="adm_img" placeholder="請選照片檔案"
										class="form-control form-control-line"
										value="${admVO.adm_img}" />
								</div>
							</div>

							<div class="form-group ml-3">
								<label class="col-sm-12 pl-0"><h5>管理員權限</h5></label> <input
									checked="off" type="checkbox" name="funno" value="F001"
									aria-label="Should this synchronize your files?"
									aria-hidden="true" class="labelauty" id="labelauty-01"
									style="display: none;"
									<c:forEach var="pe" items="${pelist}">
		<c:if test="${pe eq 'F001'}">checked</c:if>
	</c:forEach>>
								<label for="labelauty-01" tabindex="0" role="checkbox"
									aria-checked="true"
									aria-label="Should this synchronize your files?"> <span
									class="labelauty-unchecked-image"></span> <span
									class="labelauty-unchecked">管理員系統</span> <span
									class="labelauty-checked-image"></span> <span
									class="labelauty-checked">管理員系統</span></label> <input checked=""
									type="checkbox" name="funno" value="F002"
									aria-label="Should this synchronize your files?"
									aria-hidden="true" class="labelauty" id="labelauty-02"
									style="display: none;"
									<c:forEach var="pe" items="${pelist}">
		<c:if test="${pe eq 'F002'}">checked</c:if>
	</c:forEach>>
								<label for="labelauty-02" tabindex="0" role="checkbox"
									aria-checked="true"
									aria-label="Should this synchronize your files?"> <span
									class="labelauty-unchecked-image"></span> <span
									class="labelauty-unchecked">一般會員</span> <span
									class="labelauty-checked-image"></span> <span
									class="labelauty-checked">一般會員</span></label> <input checked=""
									type="checkbox" name="funno" value="F003"
									aria-label="Should this synchronize your files?"
									aria-hidden="true" class="labelauty" id="labelauty-03"
									style="display: none;"
									<c:forEach var="pe" items="${pelist}">
		<c:if test="${pe eq 'F003'}">checked</c:if>
	</c:forEach>>
								<label for="labelauty-03" tabindex="0" role="checkbox"
									aria-checked="true"
									aria-label="Should this synchronize your files?"> <span
									class="labelauty-unchecked-image"></span> <span
									class="labelauty-unchecked">潛店會員</span> <span
									class="labelauty-checked-image"></span> <span
									class="labelauty-checked">潛店會員</span></label> <input checked=""
									type="checkbox" name="funno" value="F004"
									aria-label="Should this synchronize your files?"
									aria-hidden="true" class="labelauty" id="labelauty-04"
									style="display: none;"
									<c:forEach var="pe" items="${pelist}">
		<c:if test="${pe eq 'F003'}">checked</c:if>
	</c:forEach>>
								<label for="labelauty-04" tabindex="0" role="checkbox"
									aria-checked="true"
									aria-label="Should this synchronize your files?"> <span
									class="labelauty-unchecked-image"></span> <span
									class="labelauty-unchecked">訂單管理</span> <span
									class="labelauty-checked-image"></span> <span
									class="labelauty-checked">訂單管理</span></label> <input checked=""
									type="checkbox" name="funno" value="F005"
									aria-label="Should this synchronize your files?"
									aria-hidden="true" class="labelauty" id="labelauty-05"
									style="display: none;"
									<c:forEach var="pe" items="${pelist}">
		<c:if test="${pe eq 'F003'}">checked</c:if>
	</c:forEach>>
								<label for="labelauty-05" tabindex="0" role="checkbox"
									aria-checked="true"
									aria-label="Should this synchronize your files?"> <span
									class="labelauty-unchecked-image"></span> <span
									class="labelauty-unchecked">裝備管理</span> <span
									class="labelauty-checked-image"></span> <span
									class="labelauty-checked">裝備管理</span></label> <input checked=""
									type="checkbox" name="funno" value="F006"
									aria-label="Should this synchronize your files?"
									aria-hidden="true" class="labelauty" id="labelauty-06"
									style="display: none;"
									<c:forEach var="pe" items="${pelist}">
		<c:if test="${pe eq 'F003'}">checked</c:if>
	</c:forEach>>
								<label for="labelauty-06" tabindex="0" role="checkbox"
									aria-checked="true"
									aria-label="Should this synchronize your files?"> <span
									class="labelauty-unchecked-image"></span> <span
									class="labelauty-unchecked">檢舉審核</span> <span
									class="labelauty-checked-image"></span> <span
									class="labelauty-checked">檢舉審核</span></label> <input checked=""
									type="checkbox" name="funno" value="F007"
									aria-label="Should this synchronize your files?"
									aria-hidden="true" class="labelauty" id="labelauty-07"
									style="display: none;"
									<c:forEach var="pe" items="${pelist}">
		<c:if test="${pe eq 'F003'}">checked</c:if>
	</c:forEach>>
								<label for="labelauty-07" tabindex="0" role="checkbox"
									aria-checked="true"
									aria-label="Should this synchronize your files?"> <span
									class="labelauty-unchecked-image"></span> <span
									class="labelauty-unchecked">潛點資訊維護</span> <span
									class="labelauty-checked-image"></span> <span
									class="labelauty-checked">潛點資訊維護</span></label> 
											

							</div>
							<div class="form-group">
								<div class="col-sm-12">
									<input type="hidden" name="adm_no" value="${admVO.adm_no}">
									<input type="hidden" name="action" value="update">
									<button type="submit" class="btn btn-success" >送出修改</button>
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