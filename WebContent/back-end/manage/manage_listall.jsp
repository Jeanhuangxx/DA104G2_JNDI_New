<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adm.model.*"%>
<%@ page import="com.fun.model.*"%>
<%@ page import="com.pe.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
AdmVO admVO = (AdmVO) request.getAttribute("admVO");
List<String> List= (List<String>) request.getAttribute("List");

    AdmService admSvc = new AdmService();
	List<AdmVO> list = admSvc.getAll();
	pageContext.setAttribute("list", list);
	
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
<link rel="icon" href="images/高麗菜.png" type="image/gif" sizes="72x72">
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
table {
/* 	width: 1500px; */
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}
</style>
<!--------------------------以上--------------------------------->

</head>

<body>
	<jsp:include page="/back-end/manage/admNav.jsp" />
				<!-----------------------------------------此區塊放自己的資料----------------------------------------------------->
<!-- 					<table id="table-1"> -->
<!-- 						<tr> -->
<!-- 							<td> -->
<!-- 								<h3>所有管理員列表 </h3> -->
<!-- 								<h4> -->
<!-- 									<a -->
<%-- 										href="<%=request.getContextPath()%>/back-end/manage/manage_select.jsp"> --%>
<!-- 									</a> -->
<!-- 								</h4> -->
<!-- 							</td> -->
<!-- 						</tr> -->
<!-- 					</table> -->

				
					<h2>所有管理員列表 </h2>
					<table class="table table-hover">
						<thead>

							<tr>
								<th>管理員編號</th>
								<th>照片</th>
								<th>管理員ID</th>
								<th>管理員密碼</th>
								<th>姓名</th>
								<th>手機</th>
								<th>EMAIL</th>
								<th>權限</th>
								<th>修改</th>
								<th>刪除</th>
							</tr>
						</thead>
							<%@ include file="page1.file"%>
							<c:forEach var="admVO" items="${list}" begin="<%=pageIndex %>"
								end="<%=pageIndex+rowsPerPage-1 %>">
						<tbody>
							<tr>
								<td>${admVO.adm_no}</td>
								<td><img
									src="<%=request.getContextPath() %>/ShowPic_V1?adm_no=${admVO.adm_no}"
									width="80px"></td>
								<td>${admVO.adm_id}</td>
								<td>${admVO.adm_psw}</td>
								<td>${admVO.adm_name}</td>
								<td>${admVO.adm_tel}</td>
								<td>${admVO.adm_email}</td>
								
								<td>
								<c:forEach var="funVO" items="${funlist}">
								<c:forEach var="peVO" items="${pelist}">
								<c:if test="${peVO.adm_no eq admVO.adm_no}">
								<c:if test="${peVO.fun_no eq funVO.fun_no}">
								【${funVO.fun_name}】<br>
								
								</c:if>
								</c:if>
								</c:forEach>
								</c:forEach>
								</td>
								
								<td>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/AdmServlet/adm.do"
										style="margin-bottom: 0px;">
										<input type="submit" value="修改" class="btn btn-warning"> <input type="hidden"
											name="adm_no" value="${admVO.adm_no}"> <input
											type="hidden" name="action" value="getOne_For_Update">
									</FORM>
								</td>
								<td>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/AdmServlet/adm.do"
										style="margin-bottom: 0px;">
										<input type="submit" value="刪除" class="btn btn-danger"> <input type="hidden"
											name="adm_no" value="${admVO.adm_no}"> <input
											type="hidden" name="action" value="delete">
									</FORM>
								</td>
							</tr>
						</tbody>
						</c:forEach>
					</table>
					<%@ include file="page2.file"%>
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
		<script
			src="<%=request.getContextPath()%>/back-end/manage/js/poper.js"></script>
		<script
			src="<%=request.getContextPath()%>/back-end/manage/js/bootstrap.min.js"></script>
		<script
			src="<%=request.getContextPath()%>/back-end/manage/js/manager.js"></script>
	</div>
</body>

</html>