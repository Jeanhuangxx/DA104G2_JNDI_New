<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adm.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	AdmVO admVO = (AdmVO) request.getAttribute("admVO");

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
	<div class="row">
		<!-- Column -->
		<div class="col-lg-3 ml-5">
			<div class="card">
				<div class="card-block pb-3">
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
							class="btn btn-success">所有管理員</a> <a
							href="<%=request.getContextPath()%>/back-end/manage/manage_select.jsp"
							class="btn btn-success">查詢管理員</a>
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
							<label class="col-md-12 mt-2">ID</label>
							<div class="col-md-12">
								<input type="text" name="adm_id" placeholder="ex:ADM001"
									class="form-control form-control-line adm_id"
									value="<%=(admVO == null) ? "" : admVO.getAdm_id()%>" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-12">姓名</label>
							<div class="col-md-12">
								<input type="text" name="adm_name" placeholder="ex:NAME01"
									class="form-control form-control-line adm_name"
									value="<%=(admVO == null) ? "" : admVO.getAdm_name()%>" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-12">手機</label>
							<div class="col-md-12">
								<input type="text" name="adm_tel" placeholder="ex:0900000001"
									class="form-control form-control-line adm_tel"
									value="<%=(admVO == null||admVO.getAdm_tel()==null) ? "" : admVO.getAdm_tel()%>" />
							</div>
						</div>
						<div class="form-group">
							<label for="example-email" class="col-md-12">Email</label>
							<div class="col-md-12">
								<input type="email" name="adm_email"
									placeholder="ex:ADM001@gmail.com"
									class="form-control form-control-line adm_email" id="example-email"
									value="<%=(admVO == null) ? "" : admVO.getAdm_email()%>" />
							</div>
						</div>

						<jsp:useBean id="admSvc" scope="page"
							class="com.adm.model.AdmService" />

						<div class="form-group">
							<label class="col-md-12">照片</label>
							<div class="col-md-12">
								<input type="file" name="adm_img" placeholder="請選擇照片檔案"
									class="form-control form-control-line"
									value="<%=(admVO == null) ? "" : admVO.getAdm_img()%>">
							</div>
						</div>

						<div class="form-group" style="padding-left:15px;">
							<label class="col-md-12 pl-0">管理員權限</label> <input checked=""
								type="checkbox" name="funno" value="F001"
								aria-label="Should this synchronize your files?"
								aria-hidden="true" class="labelauty" id="labelauty-01"
								style="display: none;"> <label for="labelauty-01"
								tabindex="0" role="checkbox" aria-checked="true"
								aria-label="Should this synchronize your files?"> <span
								class="labelauty-unchecked-image"></span> <span
								class="labelauty-unchecked">管理員系統</span> <span
								class="labelauty-checked-image"></span> <span
								class="labelauty-checked">管理員系統</span></label> <input checked=""
								type="checkbox" name="funno" value="F002"
								aria-label="Should this synchronize your files?"
								aria-hidden="true" class="labelauty" id="labelauty-02"
								style="display: none;"> <label for="labelauty-02"
								tabindex="0" role="checkbox" aria-checked="true"
								aria-label="Should this synchronize your files?"> <span
								class="labelauty-unchecked-image"></span> <span
								class="labelauty-unchecked">一般會員</span> <span
								class="labelauty-checked-image"></span> <span
								class="labelauty-checked">一般會員</span></label> <input checked=""
								type="checkbox" name="funno" value="F003"
								aria-label="Should this synchronize your files?"
								aria-hidden="true" class="labelauty" id="labelauty-03"
								style="display: none;"> <label for="labelauty-03"
								tabindex="0" role="checkbox" aria-checked="true"
								aria-label="Should this synchronize your files?"> <span
								class="labelauty-unchecked-image"></span> <span
								class="labelauty-unchecked">潛店會員</span> <span
								class="labelauty-checked-image"></span> <span
								class="labelauty-checked">潛店會員</span></label> <input checked=""
								type="checkbox" name="funno" value="F004"
								aria-label="Should this synchronize your files?"
								aria-hidden="true" class="labelauty" id="labelauty-04"
								style="display: none;"> <label for="labelauty-04"
								tabindex="0" role="checkbox" aria-checked="true"
								aria-label="Should this synchronize your files?"> <span
								class="labelauty-unchecked-image"></span> <span
								class="labelauty-unchecked">訂單管理</span> <span
								class="labelauty-checked-image"></span> <span
								class="labelauty-checked">訂單管理</span></label> <input checked=""
								type="checkbox" name="funno" value="F005"
								aria-label="Should this synchronize your files?"
								aria-hidden="true" class="labelauty" id="labelauty-05"
								style="display: none;"> <label for="labelauty-05"
								tabindex="0" role="checkbox" aria-checked="true"
								aria-label="Should this synchronize your files?"> <span
								class="labelauty-unchecked-image"></span> <span
								class="labelauty-unchecked">裝備管理</span> <span
								class="labelauty-checked-image"></span> <span
								class="labelauty-checked">裝備管理</span></label> <input checked=""
								type="checkbox" name="funno" value="F006"
								aria-label="Should this synchronize your files?"
								aria-hidden="true" class="labelauty" id="labelauty-06"
								style="display: none;"> <label for="labelauty-06"
								tabindex="0" role="checkbox" aria-checked="true"
								aria-label="Should this synchronize your files?"> <span
								class="labelauty-unchecked-image"></span> <span
								class="labelauty-unchecked">檢舉審核</span> <span
								class="labelauty-checked-image"></span> <span
								class="labelauty-checked">檢舉審核</span></label> <input checked=""
								type="checkbox" name="funno" value="F007"
								aria-label="Should this synchronize your files?"
								aria-hidden="true" class="labelauty" id="labelauty-07"
								style="display: none;"> <label for="labelauty-07"
								tabindex="0" role="checkbox" aria-checked="true"
								aria-label="Should this synchronize your files?"> <span
								class="labelauty-unchecked-image"></span> <span
								class="labelauty-unchecked">潛點資訊維護</span> <span
								class="labelauty-checked-image"></span> <span
								class="labelauty-checked">潛點資訊維護</span></label>


						</div>


						<div class="form-group">
							<div class="col-sm-12">
								<input type="hidden" name="action" value="insert">
								<!--  <input	type="submit" value="新增"> -->
								<button class="btn btn-success" type="submit">新增</button>
								<button class="btn btn-success quickInfo" type="button">神奇小按鈕</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<!-- Column -->
	</div>
	<!-- Row -->


	</div>
	</div>
	<!-- Row -->
	</div>
	<!-- Main -->
	<!----------------------------------------------以上-------------------------------------------------------------->
	<!-- <div class="login"><button class="btn btn-outline-info">管理員登入</button></div> -->
	<footer class="text-center text-secondary">
		&copy; DA104G2 GO ! <span id="year"></span>
	</footer>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->

	<script src="<%=request.getContextPath()%>/back-end/manage/js/poper.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/manage/js/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/manage/js/manager.js"></script>
	</div>


	<script>
		// 		$(function() {
		// 			$('.button-checkbox')
		// 					.each(
		// 							function() {

		// 								// Settings
		// 								var $widget = $(this), $button = $widget
		// 										.find('button'), $checkbox = $widget
		// 										.find('input:checkbox'), color = $button
		// 										.data('color'), settings = {
		// 									on : {
		// 										icon : 'glyphicon glyphicon-check'
		// 									},
		// 									off : {
		// 										icon : 'glyphicon glyphicon-unchecked'
		// 									}
		// 								};

		// 								// Event Handlers
		// 								$button.on('click', function() {
		// 									$checkbox.prop('checked', !$checkbox
		// 											.is(':checked'));
		// 									$checkbox.triggerHandler('change');
		// 									updateDisplay();
		// 								});
		// 								$checkbox.on('change', function() {
		// 									updateDisplay();
		// 								});

		// 								// Actions
		// 								function updateDisplay() {
		// 									var isChecked = $checkbox.is(':checked');

		// 									// Set the button's state
		// 									$button.data('state', (isChecked) ? "on"
		// 											: "off");

		// 									// Set the button's icon
		// 									$button
		// 											.find('.state-icon')
		// 											.removeClass()
		// 											.addClass(
		// 													'state-icon '
		// 															+ settings[$button
		// 																	.data('state')].icon);

		// 									// Update the button's color
		// 									if (isChecked) {
		// 										$button.removeClass('btn-default')
		// 												.addClass(
		// 														'btn-' + color
		// 																+ ' active');
		// 									} else {
		// 										$button.removeClass(
		// 												'btn-' + color + ' active')
		// 												.addClass('btn-default');
		// 									}
		// 								}

		// 								// Initialization
		// 								function init() {

		// 									updateDisplay();

		// 									// Inject the icon if applicable
		// 									if ($button.find('.state-icon').length == 0) {
		// 										$button.prepend('<i class="state-icon '
		// 												+ settings[$button
		// 														.data('state')].icon
		// 												+ '"></i> ');
		// 									}
		// 								}
		// 								init();
		// 							});
		// 		});
		
		
		$(".quickInfo").click(function(){
			$(".adm_id").val("adm015");
			$(".adm_name").val("章魚哥");
			$(".adm_tel").val("0900123456");
			$(".adm_email").val("martinezchen02@gmail.com");
		})
		
		
	</script>

</body>
</html>