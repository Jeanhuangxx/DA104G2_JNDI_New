<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.photo.model.*"%>
<%@ page import="com.album.model.*"%>
<%
	AlbumVO albumVO = (AlbumVO) session.getAttribute("albumVO");
	if (albumVO == null) {
		response.sendRedirect(request.getContextPath() + "/front-end/album/albumList.jsp");
		return;
	} ;
	String album_no = albumVO.getAlbum_no();
	PhotoService photoSvc = new PhotoService();
	List<PhotoVO> photolist = photoSvc.getAll(album_no);
	request.setAttribute("photolist", photolist);
	Timestamp albumVO_time = albumVO.getAlbum_time();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String time = dateFormat.format(albumVO_time);
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
<!-- dive.css -->
<link rel=stylesheet type="text/css"
	href="<%=request.getContextPath()%>/css/album.css">
<!-- SweatAlert CSS -->
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/sweetalert.css">
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
							style="border-radius:50%;"width='40px' height='40px'></span>
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

	<!----------------------------------------相片標頭------------------------------------>
	<div class="container" id="IDX-E10B-E-01">
		<div class="row">
			<div class="col-2 d-flex justify-content-center align-items-start">
				<a
					href="<%=request.getContextPath()%><%=session.getAttribute("requestURL")%>"
					class="new-album btn btn-info">返回清單</a>
			</div>
			<div class="col-2 d-flex justify-content-center align-items-start">
				<c:if
					test="${not empty sessionScope.account && memVO.mem_type == 0 && memVO.mem_no==albumVO.mem_no}">
					<button class="btn btn-primary btn-danger" onclick="deleteAlbum()">刪除相簿</button>
				</c:if>
			</div>
			<div class="col-4 fast d-flex justify-content-center">
				<img class="title-icon-box"
					src="<%=request.getContextPath()%>/ShowPic_V1?album_no=${albumVO.album_no}"
					alt=""><%=albumVO.getAlbum_name()%>
			</div>
			<div class="col-2 d-flex justify-content-center align-items-start">
				<c:if
					test="${not empty sessionScope.account && memVO.mem_type == 0 && memVO.mem_no==albumVO.mem_no}">
					<button href="#" class="btn btn-primary btn-success "
						data-toggle="modal" data-target="#exampleModalCenter"
						id="editAlbum">編輯相簿</button>
				</c:if>
			</div>
			<div class="col-2 d-flex justify-content-center">
				<c:if
					test="${not empty sessionScope.account && memVO.mem_type == 0 && memVO.mem_no==albumVO.mem_no}">
					<a
						href="<%=request.getContextPath()%>/front-end/photo/addPhoto.jsp?album_no=${albumVO.album_no}"
						class="new-album"><img alt=""
						src="<%=request.getContextPath()%>/images/uploadphoto.png"
						style="width: 40px; height: 40px;"></a>
				</c:if>
			</div>
		</div>
	</div>

	<div align="center">
		相簿更新時間:
		<%=time%></div>


	<div class="container">
		<br>
		<!------------------------------------------ 主畫面.相片列表 ------------------------------------->
		<div class="row align-items-center"><%@ include
				file="page1.file"%>
			<div class="col-3 d-flex justify-content-end"><%@ include
					file="page2.file"%></div>
			<div class="col-6 d-flex justify-content-center" id="sm_pic">
				<c:forEach var="photoVO" items="${photolist}" begin="<%=pageIndex%>"
					end="<%=pageIndex+rowsPerPage-1%>" step="1">
					<img class="sm_pic" id="${photoVO.photo_no}" alt=""
						src="<%=request.getContextPath()%>/ShowPic_V1?photo_no=${photoVO.photo_no}">
				</c:forEach>
			</div>
			<div class="col-3 "><%@ include file="page3.file"%></div>
		</div>
	</div>

	<br>
	<!---------------------------------- 相片檢舉.修改.刪除-------------------------- -->
	<div class="row">
		<div class="col-4">
			<div class="row">
				<div class="col">
					<div class="row">
						<div class="col-6"></div>
						<div class="col-6 col d-flex justify-content-end">
							<c:if
								test="${not empty sessionScope.account && memVO.mem_type == 0 && memVO.mem_no!=albumVO.mem_no && photolist.size()!=0}">
								<button class="badge badge-dark" onclick="re()">檢舉</button>
							</c:if>
							<c:if
								test="${not empty sessionScope.account && memVO.mem_type == 0 && memVO.mem_no==albumVO.mem_no}">
								<!-- 								<input type="submit" value="修改" onclick="update()"> -->
								<button class="badge badge-dark" onclick="update()">修改</button>
							</c:if>
							<c:if
								test="${not empty sessionScope.account && memVO.mem_type == 0 && memVO.mem_no==albumVO.mem_no}">
								<button class="badge badge-warning" onclick="del()">刪除</button>
							</c:if>
						</div>
					</div>
				</div>
			</div>
			<div>
				<div class="col d-flex justify-content-end">
					<%-- 錯誤表列 --%>
					<c:if test="${not empty errorMsgs}">
						<font style="color: red">請修正以下錯誤:</font>
						<ul>
							<c:forEach var="message" items="${errorMsgs}">
								<li style="color: red">${message}</li>
							</c:forEach>
						</ul>
					</c:if>
				</div>
			</div>
			<div>
				<div class="col d-flex justify-content-end">
					<div class="photo_update" style="display: none;">
						<form action="<%=request.getContextPath()%>/photo/photo.do"
							name="form1" METHOD="post">
							<div>圖片名稱 :</div>
							<div>
								<input type="text" class="picName" name="photo_name">
							</div>
							<div>
								<div>圖片詳情:</div>
								<textarea class="picDetail" rows="10" cols="30"
									name="photo_note"></textarea>
							</div>
							<div class="col d-flex justify-content-end">
								<input type="submit" value="確認修改">
							</div>
							<input type="hidden" name="whichPage" value="<%=whichPage%>">
							<input type="hidden" class="photo_re_no" name="photo_no" value="">
							<input type="hidden" name="action" value="update">
						</form>
					</div>
					<div class="photo_re" style="display: none;">
						<form
							action="<%=request.getContextPath()%>/photo_report/photo_report.do"
							name="form1" METHOD="post">
							<div>檢舉原因 :</div>
							<div>
								<input type="text" name="photo_re_note">
							</div>
							<br>
							<div class="col d-flex justify-content-end">
								<input type="submit" value="送出">
							</div>
							<input type="hidden" name="action" value="insert_report"><input
								type="hidden" name="whichPage" value="<%=whichPage%>"><input
								class="photo_re_no" type="hidden" name="photo_no" value="">
						</form>
					</div>
				</div>
			</div>
			<div>
				<!-- 刪除圖片 -->
				<div class="photo_del" style="display: none;">
					<form action="<%=request.getContextPath()%>/photo/photo.do"
						id="delPic" name="form1" METHOD="post">
						<input type="hidden" name="whichPage" value="<%=whichPage%>">
						<input type="hidden" name="action" value="delete_pic"> <input
							class="photo_re_no" type="hidden" name="photo_no" value="">
					</form>
				</div>
				<!-- 刪除相簿 -->
				<div class="album_del" style="display: none;">
					<form action="<%=request.getContextPath()%>/album/album.do"
						id="delAlbum" METHOD="post" name="form1">
						<input type="hidden" name="action" value="delete"><input
							type="hidden" name="album_no" value="<%=albumVO.getAlbum_no()%>">
					</form>
				</div>
			</div>
		</div>
		<!------------------------------------------ 相片大圖 ------------------------------------->
		<div class="col-4" style="padding-bottom: 20px;">
			<div class="show_big">
				<img alt=""
					src="<%=request.getContextPath()%>/images/photo_icon.png">
			</div>
		</div>
		<!------------------------------------------ 相片資訊 ------------------------------------->
		<div class="col-4">
			<div class="row">
				<div class="col">
					<c:forEach var="photoVO" items="${photolist}" step="1">
						<div class="detail${photoVO.photo_no} detail"
							style="display: none">
							<div class="picInfo">相片名稱 :</div>
							<div class="picTitle">${photoVO.photo_name}</div>
							<div class="picInfo">相片詳情 :</div>
							<div class="picNote">${photoVO.photo_note}</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>

	<!------------------------- 相簿修改彈窗---------------- ------------------>

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
					<%-- 錯誤表列 --%>
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
									<td>相簿名稱:</td>
									<td><input type="TEXT" name="album_name" size="20"
										value="<%=(albumVO == null) ? "" : albumVO.getAlbum_name()%>" /></td>
									<td rowspan="3"><div id="showpic">
											<img style="width: 200px; height: 170px;" alt=""
												src="<%=request.getContextPath()%>/ShowPic_V1?album_no=<%=albumVO.getAlbum_no()%>">
										</div></td>
								</tr>
								<tr>
									<td>相簿封面:</td>
									<td><input type="file" name="album_pic" id="album_pic">
									</td>
								</tr>
								<tr>
									<td>相簿簡介:</td>
									<td><textarea name="album_note"
											style="width: 300px; height: 100px;"><%=(albumVO == null) ? "" : albumVO.getAlbum_note()%></textarea></td>
								</tr>
							</tbody>
						</TAble>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Close</button>
							<input type="hidden" name="action" value="update"> <input
								type="hidden" name="album_no" value="<%=albumVO.getAlbum_no()%>">
							<input type="hidden" name="whichPage" value="<%=whichPage%>">
							<button type="submit" class="btn btn-primary">修改相簿</button>
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
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/sweetalert.min.js"></script>
	<script src="<%=request.getContextPath()%>/dive.js"></script>
	<script src="<%=request.getContextPath()%>/js/changePicShow.js"></script>
	<script type="text/javascript">
		var a;
		var b;
		var c;
		var first_src = $("#sm_pic img:first").attr("src");
		var first_id = $("#sm_pic img:first").attr("id");
		var path = "${pageContext.request.contextPath}";
		$(document).ready(function() {
			if (first_src != null) {
				$(".show_big img").attr("src", first_src);
			}
			$(".photo_re_no").attr("value", first_id);
			$(".detail" + first_id).show();

			if ($("#errorMsgs_album").has("li").length != 0) {
				$("#editAlbum").click();
			}
		})

		$(".sm_pic").click(function(e) {
			a = e.target.id;

			$(".show_big img").attr("src", path + "/ShowPic_V1?photo_no=" + a);

			$(".detail").hide();
			$(".detail" + a).show();

			$(".photo_re_no").attr("value", a);

			b = $(".detail" + a).find(".picTitle").text();
			c = $(".detail" + a).find(".picNote").text();

			$(".picName").val($(".detail" + a).find(".picTitle").text());
			$(".picDetail").val($(".detail" + a).find(".picNote").text());

		})

		function re() {
			$(".photo_re").show();
		}

		function update() {
			$(".photo_update").show();
		}

		function del() {
			// 			$(".photo_del").show();
			swal({
				title : "確定刪除嗎?",
				text : "你將無法恢復原本圖片！",
				type : "warning",
				showCancelButton : true,
				confirmButtonColor : "#DD6B55",
				confirmButtonText : "確認删除！",
				cancelButtonText : "取消删除！",
				closeOnConfirm : false,
				closeOnCancel : false
			}, function(isConfirm) {
				if (isConfirm) {
					delPic.submit();
					swal("删除！", "你的圖片已被删除。", "success");
				} else {
					swal("取消！", "", "error");
				}
			});
		}

		function deleteAlbum() {
			swal({
				title : "確定刪除嗎?",
				text : "你將無法恢復相簿！",
				type : "warning",
				showCancelButton : true,
				confirmButtonColor : "#DD6B55",
				confirmButtonText : "確認删除！",
				cancelButtonText : "取消删除！",
				closeOnConfirm : false,
				closeOnCancel : false
			}, function(isConfirm) {
				if (isConfirm) {
					delAlbum.submit();
					swal("删除！", "你的圖片已被删除。", "success");
				} else {
					swal("取消！", "", "error");
				}
			});
		}
	</script>
</body>
</html>