<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.album.model.*"%>
<%
	String mem_no = (String) session.getAttribute("mem_no");
	AlbumService albumSvc = new AlbumService();
	List<AlbumVO> albumlist = albumSvc.getByMem(mem_no);
	pageContext.setAttribute("albumlist", albumlist);
	AlbumVO albumVO = (AlbumVO) request.getAttribute("albumVO");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
<!-- <link rel="stylesheet" -->
<%-- 	href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css"> --%>
<!-- dive.css -->
<link rel=stylesheet type="text/css"
	href="<%=request.getContextPath()%>/css/album.css">
<style type="text/css">
/* .card-deck { */
/* 	width: 1140px; */
/* } */
</style>
<title>相簿</title>
</head>
<body>

	<div id="albumList">
		<br>
		<!------------------------------------------ 相簿列表 ------------------------------>
		<%@ include file="page1.file"%>
		<div class="card-deck">
			<c:forEach var="albumVO" items="${albumlist}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>" step="1">
				<%-- 				<c:if test="${memVO.mem_no==albumVO.mem_no}"> --%>
				<div class="card" style="margin-bottom: 15px;">
					<form method="post"
						action="<%=request.getContextPath()%>/album/album.do">
						<div class="card_img">
							<input type="hidden" name="album_no" value="${albumVO.album_no}">
							<input type="hidden" name="action" value="getOne_For_Display">
							<input type="hidden" name="requestURL"
								value="<%=request.getServletPath()%>"> 
							<input
								class="inputImg"
								type="image"
								src="<%=request.getContextPath()%>/ShowPic_V1?album_no=${albumVO.album_no}">
						</div>
						<div class="card-body">
							<h5 class="card-title">${albumVO.album_name}</h5>
							<p class="card-text">${albumVO.album_note}</p>
						</div>
					</form>
				</div>
				<%-- 				</c:if> --%>
			</c:forEach>
		</div>
		<br> <br>
		<div class="row">
			<div class="col-5 d-flex justify-content-end">
				<%
					if (rowsPerPage < rowNumber) {
				%>
				<%
					if (pageIndex >= rowsPerPage) {
				%>
				<A href="<%=request.getRequestURI()%>?whichPage=1"
					class="badge badge-pill badge-success">至第一頁</A>&nbsp; <A
					href="<%=request.getRequestURI()%>?whichPage=<%=whichPage - 1%>"
					class="badge badge-pill badge-success">上一頁 </A>&nbsp;
				<%
					}
				%>
			</div>
			<div class="col-2 d-flex justify-content-center">
				<%
					if (pageNumber > 1) {
				%>

				<FORM name="pageForm" METHOD="POST"
					ACTION="<%=request.getRequestURI()%>">
					<select name="pages" size="1" onchange="submit();">
						<%
							for (int i = 1; i <= pageNumber; i++) {
						%>
						<option value="<%=i%>">跳至第<%=i%>頁
							<%
							}
						%>
						
					</select>
				</FORM>
				<%
					}
				%>
			</div>
			<div class="col-5 d-flex justify-content-start">
				<%
					if (pageIndex < pageIndexArray[pageNumber - 1]) {
				%>
				<A href="<%=request.getRequestURI()%>?whichPage=<%=whichPage + 1%>"
					class="badge badge-pill badge-success">下一頁 </A>&nbsp; <A
					href="<%=request.getRequestURI()%>?whichPage=<%=pageNumber%>"
					class="badge badge-pill badge-success">至最後一頁</A>&nbsp;
				<%
					}
				%>
				<%
					}
				%>
			</div>
		</div>

		<br> <br>



	</div>


	<!----------------------------------------新建相簿彈窗---------------------------------------->
	<div class="modal fade" id="exampleModalCenter" tabindex="-1"
		role="dialog" aria-labelledby="#btn" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered modal-lg"
			role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalCenterTitle">相簿資訊</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<ul id="errorMsgs_album">
						<c:if test="${not empty errorMsgs_album}">
							<c:forEach var="message" items="${errorMsgs_album}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</c:if>
					</ul>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/album/album.do" name="form1"
						enctype="multipart/form-data">
						<TAblE>
							<tbody>
								<tr>
									<td>相簿名稱:<font style="color: red">*</font></td>
									<td><input type="TEXT" name="album_name" size="20"
										value="<%=(albumVO == null) ? "" : albumVO.getAlbum_name()%>" /></td>
									<td rowspan="3"><div id="showpic"></div></td>
								</tr>
								<tr>
									<td>相簿封面:<font style="color: red">*</font></td>
									<td><input type="file" name="album_pic" id="album_pic">
									</td>
								</tr>
								<tr>
									<td>相簿簡介:<font style="color: red">*</font></td>
									<td><textarea name="album_note"
											style="width: 300px; height: 100px;"><%=(albumVO == null) ? "" : albumVO.getAlbum_note()%></textarea></td>
								</tr>
							</tbody>
						</TAble>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Close</button>
							<input type="hidden" name="action" value="insert">
							<button type="submit" class="btn btn-primary">新增相簿</button>
						</div>
					</FORm>
				</div>
			</div>
		</div>
	</div>

	<!-----------------------------------footer---------------------------------------->

	<!-- 	<script -->
	<%-- 		src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.4.1.min.js"></script> --%>
	<!-- 	<script -->
	<%-- 		src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script> --%>
	<!-- 	<script -->
	<%-- 		src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script> --%>
	<%-- 	<script src="<%=request.getContextPath()%>/dive.js"></script> --%>
	<script src="<%=request.getContextPath()%>/js/changePicShow.js"></script>

	<script type="text/javascript">
// 		$(document).load(function() {
// 			if ($("#errorMsgs_album").has("li").length != 0) {
// 				$("#newAlbum").click();
// 			}
// 		})
	
		var whichPage=$("#whichPage");
		var option=$("#whichPage option");
		
		if(option.length>1){
			if(<%=request.getParameter("whichPage")%>==null){
				document.getElementsByName("whichPage")[0].value = "1";
			}else if(<%=request.getParameter("whichPage")%><1){
				document.getElementsByName("whichPage")[0].value = "1";
			}else if(<%=request.getParameter("whichPage")%>>option.length){
				document.getElementsByName("whichPage")[0].value = option.length;
			}else{
				document.getElementsByName("whichPage")[0].value = "<%=request.getParameter("whichPage")%>
		";
			}
		}
	</script>

</body>
</html>