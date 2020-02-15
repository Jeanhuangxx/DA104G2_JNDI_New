<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.photo_report.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adm.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	AdmVO admVO = (AdmVO) request.getSession().getAttribute("admVO");
	PhotoReportService photoReportSvc = new PhotoReportService();
	List<PhotoReportVO> listNoReport = photoReportSvc.getByStatus(0);
	pageContext.setAttribute("listNoReport", listNoReport);
	List<PhotoReportVO> listReadyReport = photoReportSvc.getByStatus(1);
	pageContext.setAttribute("listReadyReport", listReadyReport);
	List<PhotoReportVO> listBlockReport = photoReportSvc.getByStatus(2);
	pageContext.setAttribute("listBlockReport", listBlockReport);

	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	Map<Integer, String> photoReportStatus = (HashMap<Integer, String>) application
			.getAttribute("photoReportStatus");
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
	href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/manage.css">
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

/*  table  */
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
 	width: 100%; 
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #CCCCFF;
}

th, td {
	padding : 5px;
	text-align: center;
	padding: 5px;
}

.my-custom-scrollbar {
	position: relative;
	height: 500px;
	overflow: auto;
}

.table-wrapper-scroll-y {
	padding-top: 30px;
	display: block;
}
</style>
<!--------------------------以上--------------------------------->

</head>

<body>

	<script type="text/javascript">
		window.onload = function() {
			if ("${param.status}" == "uncheck") {
				$('a[href="#nav-home"]').tab('show');
			} else if ("${param.photo_re_status}" == "2") {
				$('a[href="#nav-contact"]').tab('show');
			} else if ("${param.photo_re_status}" == "1") {
				$('a[href="#nav-profile"]').tab('show');
			}
		};
	</script>
	<jsp:include page="/back-end/manage/admNav.jsp" />
	<!-----------------------------------------此區塊放自己的資料----------------------------------------------------->
	<nav>
		<div class="nav nav-tabs" id="nav-tab" role="tablist">
			<a class="nav-item nav-link active" id="nav-home-tab"
				data-toggle="tab" href="#nav-home" role="tab"
				aria-controls="nav-home" aria-selected="true">未審核</a> <a
				class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab"
				href="#nav-profile" role="tab" aria-controls="nav-profile"
				aria-selected="false">不封鎖</a> <a class="nav-item nav-link"
				id="nav-contact-tab" data-toggle="tab" href="#nav-contact"
				role="tab" aria-controls="nav-contact" aria-selected="false">已封鎖</a>
		</div>
	</nav>



	<div class="tab-content" id="nav-tabContent">
		<div class="tab-pane fade show active" id="nav-home" role="tabpanel"
			aria-labelledby="nav-home-tab">
			<div class="table-wrapper-scroll-y my-custom-scrollbar">
				<table>
					<tr>
						<th>檢舉時間</th>
						<th>檢舉編號</th>
						<th>相片編號</th>
						<th>會員編號</th>
						<th>檢舉原因</th>
						<th>圖片</th>
						<th>審核狀態</th>
						<th>審核</th>
					</tr>
					<c:if test="${listNoReport!=null}">
						<c:forEach var="photoReportVO" items="${listNoReport}">
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/photo_report/photo_report.do"
								style="margin-bottom: 0px;">
								<tr
									${(photoReportVO.photo_no==param.photo_no) ? 'bgcolor=#CCCCFF':''}>
									<td>${photoReportVO.photo_re_time}</td>
									<td>${photoReportVO.photo_re_no}</td>
									<td>${photoReportVO.photo_no}</td>
									<td>${photoReportVO.mem_no}</td>
									<td>${photoReportVO.photo_re_note}</td>
									<td><div class="showPic">
											<img alt="" id="${photoReportVO.photo_re_no}" class=""
												src="<%=request.getContextPath()%>/ShowPic_V1?photo_no=${photoReportVO.photo_no}"
												width="30px" height="30px">
										</div></td>
									<td><select name="photo_re_status">
											<option name="photo_re_status" value="0">未審核</option>
											<option name="photo_re_status" value="1">無罪</option>
											<option name="photo_re_status" value="2">有罪</option>
									</select></td>
									<td><input type="submit" value="修改"> <input
										type="hidden" name="photo_re_no"
										value="${photoReportVO.photo_re_no}"> <input
										type="hidden" name="photo_no"
										value="${photoReportVO.photo_no}"><input type="hidden"
										name="action" value="update"><input type="hidden"
										name="status" value="uncheck"></td>
								</tr>
							</FORM>
						</c:forEach>
					</c:if>
				</table>
			</div>
		</div>
		<div class="tab-pane fade" id="nav-profile" role="tabpanel"
			aria-labelledby="nav-profile-tab">
			<div class="table-wrapper-scroll-y my-custom-scrollbar">
				<table>
					<tr>
						<th>檢舉時間</th>
						<th>檢舉編號</th>
						<th>相片編號</th>
						<th>會員編號</th>
						<th>檢舉原因</th>
						<th>圖片</th>
						<th>審核狀態</th>
						<th>審核</th>
					</tr>
					<c:if test="${listReadyReport!=null}">
						<c:forEach var="photoReportVO" items="${listReadyReport}">
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/photo_report/photo_report.do"
								style="margin-bottom: 0px;">
								<tr
									${(photoReportVO.photo_no==param.photo_no) ? 'bgcolor=#CCCCFF':''}>
									<td>${photoReportVO.photo_re_time}</td>
									<td>${photoReportVO.photo_re_no}</td>
									<td>${photoReportVO.photo_no}</td>
									<td>${photoReportVO.mem_no}</td>
									<td>${photoReportVO.photo_re_note}</td>
									<td><div class="showPic">
											<img alt="" id="${photoReportVO.photo_re_no}" class=""
												src="<%=request.getContextPath()%>/ShowPic_V1?photo_no=${photoReportVO.photo_no}"
												width="30px" height="30px">
										</div></td>
									<td><select name="photo_re_status">
											<option name="photo_re_status" value="1" selected="selected">無罪</option>
											<option name="photo_re_status" value="2">有罪</option>
									</select></td>
									<td><input type="submit" value="修改"> <input
										type="hidden" name="photo_re_no"
										value="${photoReportVO.photo_re_no}"> <input
										type="hidden" name="photo_no"
										value="${photoReportVO.photo_no}"><input type="hidden"
										name="action" value="update"><input type="hidden"
										name="status" value="alive"></td>
								</tr>
							</FORM>
						</c:forEach>
					</c:if>
				</table>
			</div>
		</div>
		<div class="tab-pane fade" id="nav-contact" role="tabpanel"
			aria-labelledby="nav-contact-tab">
			<div class="table-wrapper-scroll-y my-custom-scrollbar">
				<table>
					<tr>
						<th>檢舉時間</th>
						<th>檢舉編號</th>
						<th>相片編號</th>
						<th>會員編號</th>
						<th>檢舉原因</th>
						<th>圖片</th>
						<th>審核狀態</th>
						<th>審核</th>
					</tr>
					<c:if test="${listBlockReport!=null}">
						<c:forEach var="photoReportVO" items="${listBlockReport}">
							<FORM METHOD="post"
								ACTION="<%=request.getContextPath()%>/photo_report/photo_report.do"
								style="margin-bottom: 0px;">
								<tr
									${(photoReportVO.photo_no==param.photo_no) ? 'bgcolor=#CCCCFF':''}>
									<td>${photoReportVO.photo_re_time}</td>
									<td>${photoReportVO.photo_re_no}</td>
									<td>${photoReportVO.photo_no}</td>
									<td>${photoReportVO.mem_no}</td>
									<td>${photoReportVO.photo_re_note}</td>
									<td><div class="showPic">
											<img alt="" id="${photoReportVO.photo_re_no}" class=""
												src="<%=request.getContextPath()%>/ShowPic_V1?photo_no=${photoReportVO.photo_no}"
												width="30px" height="30px">
										</div></td>
									<td><select name="photo_re_status">
											<option name="photo_re_status" value="1">無罪</option>
											<option name="photo_re_status" value="2" selected="selected">有罪</option>
									</select></td>
									<td><input type="submit" value="修改"> <input
										type="hidden" name="photo_re_no"
										value="${photoReportVO.photo_re_no}"> <input
										type="hidden" name="photo_no"
										value="${photoReportVO.photo_no}"><input type="hidden"
										name="action" value="update"><input type="hidden"
										name="status" value="die"></td>
								</tr>
							</FORM>
						</c:forEach>
					</c:if>
				</table>
			</div>
		</div>
	</div>
	</div>
	<!----------------------------------------------以上-------------------------------------------------------------->
	</div>
	</div>


	<div id="outerdiv"
		style="position: fixed; top: 0; left: 0; background: rgba(0, 0, 0, 0.7); z-index: 2; width: 100%; height: 100%; display: none;">
		<div id="innerdiv" style="position: absolute;">
			<img id="bigimg" style="border: 5px solid #fff;" src="" />
		</div>
	</div>
	<!-- <div class="login"><button class="btn btn-outline-info">管理員登入</button></div> -->
	<footer class="text-center text-secondary">
		&copy; DA104G2 GO ! <span id="year"></span>
	</footer>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script
		src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.4.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/manager.js"></script>
	<script type="text/javascript">
		(function($) {

			$.fn.hoverpulse = function(options) {
				// in 1.3+ we can fix mistakes with the ready state
				if (this.length == 0) {
					if (!$.isReady && this.selector) {
						var s = this.selector, c = this.context;
						$(function() {
							$(s, c).hoverpulse(options);
						});
					}
					return this;
				}

				var opts = $.extend({}, $.fn.hoverpulse.defaults, options);

				// parent must be relatively positioned
				this.parent().css({
					position : 'relative'
				});
				// pulsing element must be absolutely positioned
				this.css({
					position : 'absolute',
					top : (-15 + 'px'),
					left : (5 + 'px')
				});

				this.each(function() {
					var $this = $(this);
					var w = $this.width(), h = $this.height();
					$this.data('hoverpulse.size', {
						w : parseInt(w),
						h : parseInt(h)
					});
				});

				// bind hover event for behavior
				return this.hover(
				// hover over
				function() {
					var $this = $(this);
					$this.parent().css('z-index', opts.zIndexActive);

					var size = $this.data('hoverpulse.size');
					var w = size.w, h = size.h;
					$this.stop().animate({
						top : ('-' + opts.size + 'px'),
						left : ('-' + (opts.size+300) + 'px'),
						height : (h + 3 * opts.size) + 'px',
						width : (w + 4 * opts.size) + 'px'
					}, opts.speed);
				},
				// hover out
				function() {
					var $this = $(this);
					var size = $this.data('hoverpulse.size');
					var w = size.w, h = size.h;

					$this.stop().animate({
						top : (-15 + 'px'),
						left : (5 + 'px'),
						height : (h + 'px'),
						width : (w + 'px')
					}, opts.speed, function() {
						$this.parent().css('z-index', opts.zIndexNormal);
					});
				});
			};

			$.fn.hoverpulse.defaults = {
				size : 20,
				speed : 200,
				zIndexActive : 100,
				zIndexNormal : 1
			};

		})(jQuery);

		$(document).ready(function() {
			$('div.showPic img').hoverpulse({
				size : 120, // number of pixels to pulse element (in each direction)
				speed : 400
			// speed of the animation 
			});
		});
	</script>
	</div>
</body>

</html>