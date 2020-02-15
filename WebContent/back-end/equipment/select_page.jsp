<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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

	body{
		font-family: "Microsoft JhengHei UI";
		font-size: 22px;
		font-weight: bold;
	}
	.li{
		margin-top:30px;
		margin-left:50px;
	}
	
	.form-control.equipStatus{
		display: inline;
		width: auto;
	}
</style>
</head>
<!--------------------------以上--------------------------------->
<body>
		<jsp:include page="/back-end/manage/admNav.jsp" />
<!-----------------------------------------此區塊放自己的資料----------------------------------------------------->
					<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color: red">請修正以下錯誤：</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
					<!-- 新增裝備 -->
					<div class="li">
						●<a
							href='<%=request.getContextPath()%>/back-end/equipment/addEquip.jsp'>
							新增</a>裝備
					</div>
					<!-- 查詢所有裝備 -->
					<div class="li">
						●<a
							href='<%=request.getContextPath()%>/back-end/equipment/listAllEquip.jsp'>
							查詢</a>所有裝備
					</div>
					<!-- 查詢上架/下架裝備 -->
					<FORM method="post"
						action="<%=request.getContextPath()%>/equip/equipment.do">
						<div class="li">
							● 查詢 <select name="equip_status" class="form-control equipStatus">
								<option value="1">上架</option>
								<option value="0">下架</option>
							</select>裝備
							<button type="submit" class="btn btn-success">送出</button>
							<input type="hidden" name="action" value="getStatus">
						</div>
					</FORM>
					<!-- 手動查單筆裝備 -->


					<!-- 下拉選單查單筆裝備 - 名稱 -->
					
				</div>
			</div>
		</div>
<!----------------------------------------------以上-------------------------------------------------------------->

		<footer class="text-center text-secondary">
			&copy; DA104G2 GO ! <span id="year"></span>
		</footer>
		<!-- Optional JavaScript -->
		<!-- jQuery first, then Popper.js, then Bootstrap JS -->
		<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
		<script
			src="<%=request.getContextPath()%>/back-end/manage/js/poper.js"></script>
		<script
			src="<%=request.getContextPath()%>/back-end/manage/js/bootstrap.min.js"></script>
		<script
			src="<%=request.getContextPath()%>/back-end/manage/js/manager.js"></script>
	</div>

</body>
</html>