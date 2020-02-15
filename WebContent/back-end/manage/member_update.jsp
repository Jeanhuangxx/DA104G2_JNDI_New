<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>

<%
	MemVO memVO = (MemVO) request.getAttribute("memVO");
	//MemServlet.java (Controller) 存入req的memVO物件 (包括幫忙取出的admVO, 也包括輸入資料錯誤時的admVO物件)
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
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/manage.css">
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
	width: 80px;
}

.navbar-nav .nav-item .nav-link {
	font-size: 26px;
	font-weight: bold;
	font-family: "Microsoft JhengHei UI";
}

table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}

table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>
</head>
<!--------------------------以上--------------------------------->

<body>
	<jsp:include page="/back-end/manage/admNav.jsp" />
	<!-----------------------------------------此區塊放自己的資料----------------------------------------------------->

		<table id="table-1">
			<tr>
				<td>
					<h3>會員資料修改 - update_adm_input.jsp</h3>
					<h4>
						<a
							href="<%=request.getContextPath()%>/back-end/manage/member_select.jsp">
							<img src="<%=request.getContextPath()%>/adm/images/back1.gif"
							width="100" height="32" border="0"> 回首頁
						</a>
					</h4>
				</td>
			</tr>
		</table>

		<h3>資料修改:</h3>

		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請修正以下錯誤:</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>

		<FORM METHOD="post"
			ACTION="<%=request.getContextPath()%>/MemServlet/mem.do" name="form1"
			enctype="multipart/form-data">
			<table>
				<tr>
					<td>會員編號 :<font color=red></font></td>
					<td><%=memVO.getMem_no()%></td>
				</tr>
				<tr>
					<td>會員姓名 :</td>
					<td><input type="TEXT" name="mem_name" size="45"
						value="<%=memVO.getMem_name()%>" /></td>
				</tr>
				<tr>
					<td>會員ID :</td>
					<td><input type="TEXT" name="mem_id" size="45"
						value="<%=memVO.getMem_id()%>" /></td>
				</tr>
				<tr>
					<td>會員密碼 :</td>
					<td><input type="TEXT" name="mem_psw" size="45"
						value="<%=memVO.getMem_psw()%>" /></td>
				</tr>
				<tr>
					<td>會員性別 :</td>
					<td><select name="mem_general_gen" size="1">
							<c:forEach var="memVO" items="${memSvc.all}">
								<option value="${memVO.mem_no}">${memVO.mem_general_gen}
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>會員生日 :</td>
					<td><input type="TEXT" name="mem_general_bd" size="45"
						value="<%=memVO.getMem_general_bd()%>" /></td>
				</tr>
				<tr>
					<td>會員電話 :</td>
					<td><input type="TEXT" name="mem_tel" size="45"
						value="<%=memVO.getMem_tel()%>" /></td>
				</tr>
				<tr>
					<td>會員EMAIL :</td>
					<td><input type="TEXT" name="mem_email" size="45"
						value="<%=memVO.getMem_email()%>" /></td>
				</tr>
				<tr>
					<td>會員地址 :</td>
					<td><input type="TEXT" name="mem_add" size="45"
						value="<%=memVO.getMem_add()%>" /></td>
				</tr>

				<jsp:useBean id="memSvc" scope="page"
					class="com.mem.model.MemService" />
				<tr>
					<td>會員照片 :</td>
					<td><font color=red><b>*必選</b></font><input type="file"
						name="mem_img"></td>
				</tr>
				<tr>
					<td>會員權限 :</td>
					<td><input type="TEXT" name="mem_per" size="45"
						value="<%=memVO.getMem_per()%>" /></td>
				</tr>
				<tr>
					<td>會員類型 :</td>
					<td><input type="TEXT" name="mem_type" size="45"
						value="<%=memVO.getMem_type()%>" /></td>
				</tr>
				<tr>
					<td>營業登記證 :</td>
					<td><input type="file" name="mem_store_business" size="45" /></td>
				</tr>
				<tr>
					<td>店家負責人 :</td>
					<td><input type="TEXT" name="mem_store_owner" size="45"
						value="<%=memVO.getMem_store_owner()%>" /></td>
				</tr>
				<tr>
					<td>統一編號 :</td>
					<td><input type="TEXT" name="mem_store_taxid" size="45"
						value="<%=memVO.getMem_store_taxid()%>" /></td>
				</tr>

			</table>
			<br> <input type="hidden" name="action" value="update">
			<input type="hidden" name="mem_no" value="<%=memVO.getMem_no()%>">
			<input type="submit" value="送出修改">
		</FORM>

	</div>
	<!----------------------------------------------以上-------------------------------------------------------------->
</body>
</html>