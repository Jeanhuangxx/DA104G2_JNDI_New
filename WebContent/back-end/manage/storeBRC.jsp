<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	Map<Integer, String> memTypeStatus = (Map<Integer, String>)application.getAttribute("memTypeStatus");
	List<MemVO> list = (ArrayList) request.getAttribute("list");
%>

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
<link
	href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css"
	rel="stylesheet">
<script
	src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back-end/manage/css/lightbox.min.css">
<script
	src="<%=request.getContextPath()%>/back-end/manage/js/lightbox-plus-jquery.min.js"></script>
<script src="https://kit.fontawesome.com/43de8a2881.js"
	crossorigin="anonymous"></script>

<link
	href="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/css/bootstrap4-toggle.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/gh/gitbrent/bootstrap4-toggle@3.6.1/js/bootstrap4-toggle.min.js"></script>

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

#table-1 {
	background-color: #FFFFFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
/* width: 1500px; */
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}

.table {
/* 	width: 1500px; */
	background-color:white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding: 5px;
	text-align: center;
}

#BRC {
	height: 100px;
	width: 80px;
}

.form-control.memPer{
	display: inline;
	width: auto;
}
</style>
</head>
<!--------------------------以上--------------------------------->

<body>
	<jsp:include page="/back-end/manage/admNav.jsp" />
	<!-----------------------------------------此區塊放自己的資料----------------------------------------------------->

<!-- 		<table id="table-1"> -->
<!-- 			<tr> -->
<!-- 				<td> -->
				<div class="row" >
					<div class="col-12 pt-3 pb-3" id="table-1">
						<h2>潛店會員權限分類</h2>
						<h4>
							<a
								href="<%=request.getContextPath()%>/back-end/manage/member_listallstores.jsp">回所有潛店會員列表</a>
							
							</a>
						</h4>
					</div>
				</div>
<!-- 				</td> -->
<!-- 			</tr> -->
<!-- 		</table> -->

		<FORM method="post"
			action="<%=request.getContextPath()%>/BackMemServlet/mem.do">
			<h5>
				<div class="mt-3">
					潛店會員權限 <select name="mem_per" class="form-control memPer">
						<option name="mem_per" value="0">  正常   </option>
						<option name="mem_per" value="1">  停權   </option>
					</select>
					<button type="submit" class="btn btn-primary">送出</button>
					<input type="hidden" name="action" value="getAllStoresPER">
				</div>
			</h5>
		</FORM>


		<table class="table table-hover">
			<tr>
				<th>編號</th>
				<th>名稱</th>
				<th>帳號</th>
				<th>密碼</th>

				<th>電話</th>
				<th>EMAIL</th>
				<th>地址</th>

				<th>會員權限</th>
				<th>會員類型</th>
				<th>營業登記證</th>
				<th>店家負責人</th>
				<th>統一編號</th>

			</tr>

			<%@ include file="page1.file"%>
			<c:forEach var="memVO" items="${list}" begin="<%=pageIndex %>"
				end="<%=pageIndex+rowsPerPage-1 %>">

				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/BackMemServlet/mem.do"
					style="margin-bottom: 0px;">

					<tr>
						<td class="mem">${memVO.mem_no}</td>
						<td>${memVO.mem_name}</td>
						<td>${memVO.mem_id}</td>
						<td>${memVO.mem_psw}</td>

						<td>${memVO.mem_tel}</td>
						<td>${memVO.mem_email}</td>
						<td>${memVO.mem_add}</td>

						<td><c:if test="${memVO.mem_per==0}">
								<input class="cbStatus" type="checkbox" name="mem_status"
									checked data-toggle="toggle" data-on="正常" data-off="停權"
									data-onstyle="success" data-offstyle="danger">
							</c:if> <c:if test="${memVO.mem_per==1}">
								<input class="cbStatus2" type="checkbox" name="mem_status_2"
									checked data-toggle="toggle" data-on="停權" data-off="正常"
									data-onstyle="danger" data-offstyle="success">
							</c:if></td>

						<td>${memTypeStatus.get(memVO.mem_type)}</td>

						<td width="80px" height="100px"><a class="BRC"
							href="<%=request.getContextPath() %>/ShowPic_V1?mem_no=${memVO.mem_no}&mem_per=1"
							data-lightbox="example-1"><img class="example-image" id="BRC"
								src="<%=request.getContextPath() %>/ShowPic_V1?mem_no=${memVO.mem_no}&mem_per=1"
								alt="image-1" /></a></td>

						<td>${memVO.mem_store_owner}</td>
						<td>${memVO.mem_store_taxid}</td>

					</tr>
				</FORM>
			</c:forEach>
		</table>
		<%@ include file="page2.file"%>
	</div>
	</div>
	</div>
	<!----------------------------------------------以上-------------------------------------------------------------->
	<script>
		
		 $(document).on('change', '.cbStatus', function(e) {
			 var index = $('.cbStatus').index(this);
			 var mem_no = ($('.mem').eq(index).text());
			 var mem_status = this.checked ? 0 : 1;
			 
			 $.ajax({
				 url: "<%=request.getContextPath()%>/BackMemServlet/mem.do",
					dataType : 'text',
					type : "POST",
					data : {
						"action" : "updateStoreBRC",
						"mem_no" : mem_no,
						"mem_status" : mem_status
					},
					success : function(data) {
						setTimeout(function() {
							alert(data);
						}, 500);
					}
				});
			});
		 
		 
		 $(document).on('change', '.cbStatus2', function(e) {
			 var index = $('.cbStatus2').index(this);
			 var mem_no = ($('.mem').eq(index).text());
			 var mem_status = this.checked ? 1 : 0;
			 
			 $.ajax({
				 url: "<%=request.getContextPath()%>/BackMemServlet/mem.do",
					dataType : 'text',
					type : "POST",
					data : {
						"action" : "updateStoreBRC",
						"mem_no" : mem_no,
						"mem_status" : mem_status
				},
				
				success : function(data) {
					setTimeout(function() {
						alert(data);
					}, 500);
				}
			});
		});
	</script>

	<div>

		<footer class="text-center text-secondary">
			&copy; DA104G2 GO ! <span id="year"></span>
		</footer>
		<!-- Optional JavaScript -->
		<!-- jQuery first, then Popper.js, then Bootstrap JS -->
		<script
			src="<%=request.getContextPath()%>/back-end/manage/js/jquery-3.4.1.js"></script>
		<script
			src="<%=request.getContextPath()%>/back-end/manage/js/poper.js"></script>
		<script
			src="<%=request.getContextPath()%>/back-end/manage/js/bootstrap.min.js"></script>
		<script
			src="<%=request.getContextPath()%>/back-end/manage/js/manager.js"></script>
	</div>
</body>
</html>

