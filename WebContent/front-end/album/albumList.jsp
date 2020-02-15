<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.album.model.*"%>
<%
	request.getSession().setAttribute("URI", request.getRequestURI());
	AlbumService albumSvc = new AlbumService();
	List<AlbumVO> albumlist = albumSvc.getByStatus();
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
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css">
<!-- dive.css -->
<link rel=stylesheet type="text/css"
	href="<%=request.getContextPath()%>/dive.css">
<link rel=stylesheet type="text/css"
	href="<%=request.getContextPath()%>/css/album.css">
<style type="text/css">
body{
	background-color: #d9f8f8;
}

</style> 
<link href="<%= request.getContextPath()%>/images/twdivers.png" rel="icon">
<title>相簿</title>
</head>
<body>
	<!-- navigation bar -->
	<nav class="navbar navbar-expand-lg fixed-top" id="nav">
		<a href="<%=request.getContextPath()%>/index.jsp"><img
			src="<%=request.getContextPath()%>/images/twdivers-light.png"
			id="n_img" class="navbar-brand"></a>
		<button class="navbar-toggler navbar-light" type="button"
			data-toggle="collapse" data-target="#navbarNav"
			aria-controls="navbarNav" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link"
					href="<%=request.getContextPath()%>/front-end/group/listAllGroup.jsp">潛點揪團</a></li>
				<li class="nav-item"><a class="nav-link"
					href="<%=request.getContextPath()%>/front-end/travel-page/travel.jsp">行程</a></li>
				<li class="nav-item"><a class="nav-link"
					href="<%=request.getContextPath()%>/front-end/equipment/equip_shop.jsp">裝備</a></li>
				<li class="nav-item"><a class="nav-link"
					href="<%=request.getContextPath()%>/front-end/album/albumList.jsp">相簿</a></li>
			</ul>
			<c:choose>
				<c:when
					test="${not empty sessionScope.account && memVO.mem_type == 0}">
					<c:choose>
						<c:when test="${memVO.mem_img!=null}">
							<a class="user_name"
								href="<%=request.getContextPath()%>/front-end/memberCenter/memDetail.jsp">
								<span>
										<img alt=""
										src="<%=request.getContextPath()%>/ShowPic_V1?mem_no=${memVO.mem_no}"
										style="border-radius:50%;" width='40px' height='40px'>
								</span>
								<span>${memVO.mem_name}</span>
							</a>
						</c:when>
						<c:otherwise>
							<a class="user_name"
								href="<%=request.getContextPath()%>/front-end/memberCenter/memDetail.jsp"><span>
									<img alt=""
									src="<%=request.getContextPath()%>/images/mem_pic.png"
									style="border-radius:50%;" width='40px' height='40px'>
							</span>
							<span>${memVO.mem_name}</span>
							</a>
						</c:otherwise>
					</c:choose>
					<a
						href="<%=request.getContextPath()%>/member/MemberServlet.do?action=logout"><button
							type="button" class="btn" id="btn_out" data-toggle="modal"
							data-target="#exampleModalCenter">登出</button></a>
				</c:when>
				<c:when
					test="${not empty sessionScope.account && memVO.mem_type == 1}">
					<a class="user_name"
						href="<%=request.getContextPath()%>/front-end/store/store.jsp"><span><img
							alt="" src="<%=request.getContextPath()%>/images/store.png"
							style="border-radius:50%;" width='40px' height='40px'></span>
							<span>${memVO.mem_name}</span>
					</a>
					<a
						href="<%=request.getContextPath()%>/member/MemberServlet.do?action=logout"><button
							type="button" class="btn" id="btn_out" data-toggle="modal"
							data-target="#exampleModalCenter">登出</button></a>
				</c:when>
				<c:when test="${empty sessionScope.account}">
					<a class="user_name"
						href="<%=request.getContextPath()%>/front-end/member/login.jsp">
						<button type="button" class="btn btn-outline-warning" id="btn_log"
							data-toggle="modal" data-target="#exampleModalCenter">登入</button>
					</a>
				</c:when>
			</c:choose>
		</div>
	</nav>


	<!------------------------------------相簿標頭------------------------------->
	<div class="container" id="IDX-E10B-E-01">
		<div class="row pt-4">
			<div class="col-3"></div>
			<div class="col-6 fast">
				<img class="title-icon-box"
					src="<%=request.getContextPath()%>/images/album_icon.png" alt="">相簿
			</div>
			<div class="col-3 pt-0">
				<c:if
					test="${not empty sessionScope.account && memVO.mem_type == 0}">
					<a href="#" class="btn btn-success btn-block " data-toggle="modal"
						data-target="#exampleModalCenter" id="newAlbum">建立新相簿</a>
				</c:if>
			</div>
		</div>
	</div>


	<div class="container" id="albumList" style="min-height: 66%;">
		<br>
		<!------------------------------------------ 相簿列表 ------------------------------>
		<%@ include file="page1.file"%>
		<div class="card-deck">
			<c:forEach var="albumVO" items="${albumlist}" begin="<%=pageIndex%>"
				end="<%=pageIndex+rowsPerPage-1%>" step="1">
				<div class="card" style="margin-bottom: 15px;">
					<form method="post"
						action="<%=request.getContextPath()%>/album/album.do">
						<div class="card_img">
							<input type="hidden" name="album_no" value="${albumVO.album_no}">
							<input type="hidden" name="action" value="getOne_For_Display">
							<input type="hidden" name="requestURL"
								value="<%=request.getServletPath()%>"> 
							<input
								type="image"
								src="<%=request.getContextPath()%>/ShowPic_V1?album_no=${albumVO.album_no}">
						</div>
						<div class="card-body">
							<h5 class="card-title">${albumVO.album_name}</h5>
							<p class="card-text">${albumVO.album_note}</p>
						</div>
					</form>
				</div>
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

				<FORM name="pageForm" METHOD="GET"
					ACTION="<%=request.getRequestURI()%>">
					<select name="whichPage" size="1" onchange="submit();"
						id="whichPage">
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
	<footer>
		<div class="logo">
			<img src="<%=request.getContextPath()%>/images/twdivers.png"
				class="logo_img" alt="tw_logo">
		</div>
		<!-- 揪 行 裝 社 -->
		<ul class="nav justify-content-center" id="footer">
			<li class="nav-item"><a class="nav-link"
				href="<%=request.getContextPath()%>/front-end/group/listAllGroup.jsp">潛點揪團</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<%=request.getContextPath()%>/front-end/travel-page/travel.jsp">行程</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<%=request.getContextPath()%>/front-end/equipment/equip_shop.jsp">裝備</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<%=request.getContextPath()%>/front-end/album/albumList.jsp">社群</a></li>
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
			<li class="nav-item"><svg id="twit" width="25px" height="25px"
					viewBox="0 0 512 512">
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
	<script
		src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.4.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/dive.js"></script>
	<script src="<%=request.getContextPath()%>/js/changePicShow.js"></script>

	<script type="text/javascript">
		$(document).ready(function() {
			if ($("#errorMsgs_album").has("li").length != 0) {
				$("#newAlbum").click();
			}
		})
		
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
				document.getElementsByName("whichPage")[0].value = "<%=request.getParameter("whichPage")%>";
			}
		}
	</script>

</body>
</html>