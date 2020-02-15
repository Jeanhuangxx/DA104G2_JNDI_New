<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.Date" %>
<%@ page import="java.time.*"%>
<%@ page import="com.tour.model.*" %>
<%@ page import="com.tour_like.model.*"%>
<%@ page import="com.mem.model.*" %>

<%
	TourService tourSvc = new TourService();
	TourLikeService tourLikeSvc = new TourLikeService();

	//透過mem_no取得tour資料
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	String mem_no = memVO.getMem_no();
	List<TourLikeVO> list = tourLikeSvc.getListFromMem(mem_no);

	List<TourVO> listTourVO = new ArrayList<TourVO>();
	for (TourLikeVO tLikeVO : list) {
		listTourVO.add(tourSvc.getOneTour(tLikeVO.getTour_no()));
	}
	pageContext.setAttribute("listTourVO", listTourVO);

	LocalDate now;
	LocalDate afterPlus = LocalDate.parse("2020-01-08");
	now = LocalDate.now();
	System.out.println(now.until(afterPlus).getDays());


%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link href="<%=request.getContextPath()%>/images/twdivers.png"
	rel="icon">
<title>會員後台</title>
<script
	src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.4.1.min.js"></script>
<link rel=stylesheet type="text/css"
	href="<%=request.getContextPath()%>/css/dive.css">
<link
	href="<%=request.getContextPath()%>/vendors/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/store-trip.css"
	rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/store.css"
	rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/member.css"
	rel="stylesheet">


</head>

<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
<body id="page-top">
	<div id="wrapper">
		<!-- Sidebar -->
		<jsp:include page="/front-end/memberCenter/memNav.jsp" />
		<!-- Sidebar -->

		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<!-- TopBar -->
				<nav
					class="navbar navbar-expand navbar-light bg-navbar topbar mb-4 static-top">
					<button id="sidebarToggleTop"
						class="btn btn-link rounded-circle mr-3">
						<i class="fa fa-bars"></i>
					</button>
					<ul class="navbar-nav ml-auto">


						<div class="topbar-divider d-none d-sm-block"></div>
						<li class="nav-item dropdown no-arrow">
							<a
								class="nav-link dropdown-toggle" href="#" id="userDropdown"
								role="button" data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">
							<c:if test="${memVO.mem_img != null}">
							<img
								class="img-profile rounded-circle"
								src="<%=request.getContextPath()%>/ShowPic_V1?mem_no=${memVO.mem_no}"
								style="max-width: 60px">
							</c:if>
							<c:if test="${memVO.mem_img == null}">
							<img
								class="img-profile rounded-circle"
								src="<%=request.getContextPath()%>/images/mem_pic.png"
								style="max-width: 60px">
							</c:if>
							<span class="ml-2 d-none d-lg-inline text-white small">${memVO.mem_name}</span>
						</a>
							<div
								class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
								aria-labelledby="userDropdown">
								<div class="dropdown-divider"></div>
								<a class="dropdown-item" href="<%=request.getContextPath()%>/member/MemberServlet.do?action=logout"> <i
									class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>登出
								</a>
							</div></li>
					</ul>
				</nav>
				<!-- Topbar -->

				<!-- Container Fluid-->
				<div class="container-fluid" id="container-wrapper">
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<span class="trip_manage">追蹤行程</span>
					</div>

					<div class="tab-content" id="nav-tabContent">
						<div class="tab-pane fade show active" id="nav-home"
							role="tabpanel" aria-labelledby="nav-home-tab">
							<div>
								<table class="table">
									<thead>
										<tr>
											<th scope="col">行程名稱</th>
											<th scope="col">價格</th>
											<th scope="col">出發日期</th>
											<th scope="col">回程日期</th>
											<th scope="col">報名截止日</th>
											<th scope="col">狀態</th>
											<th scope="col"></th>
											<th scope="col"></th>
										</tr>
									</thead>
									<tbody>
										<%@ include file="/front-end/page1.file"%>
										<c:set var="now" scope="page" value="<%= now%>"/>
						                <c:forEach var="tourVO" items="${listTourVO}">
						                <c:set var="tour_stop_date" value="${tourVO.tour_stop_date.toLocalDate()}"/>
						                  <tr>
						                    <th scope="row">${tourVO.tour_name}</th>
						                    <td>${tourVO.tour_price}</td>
						                    <td>${tourVO.tour_bgn_date}</td>
						                    <td>${tourVO.tour_end_date}</td>
						                    <td>${tourVO.tour_stop_date}</td>
						                    <td>
						                    	<c:if test="${now.until(tour_stop_date).getDays() >= 5}">尚可報名</c:if>
						                    	<c:if test="${now.until(tour_stop_date).getDays() <= 4 && now.until(tour_stop_date).getDays() > 0}">剩餘${now.until(tour_stop_date).getDays()}天</c:if>
						                    	<c:if test="${now.until(tour_stop_date).getDays() <= 0}">報名截止</c:if>
						                    </td>
						                    <form method="post" action="<%= request.getContextPath()%>/tour/tour.do">
						                    <td>
						                    	<input type="hidden" name="tour_no" value="${tourVO.tour_no}">
						                    	<input type="hidden" name="action" value="getOneTour">
						                    	<input class="btn viewMore" type="submit" value="查看詳情">
						                    </td>
						                    </form>
						                    <form method="post" action="<%= request.getContextPath()%>/tour_like/tourLike.do">
						                    <td>
						                    	<input type="hidden" name="tour_no" value="${tourVO.tour_no}">
						                    	<input type="hidden" name="action" value="cancelFollowMem">
						                    	<button type="submit" class="btn deleteFav">取消追蹤</button>
						                    </td>
						                    </form>
						                  </tr>
						                  </c:forEach>
									</tbody>
								</table>
							</div>
						</div>

					</div>
				</div>
				<!---Container Fluid-->
			</div>
			<jsp:include page="/front-end/memberCenter/memFooter.jsp" />
		</div>
	</div>

	<script
		src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/jquery-easing/jquery.easing.min.js"></script>
	<script src="<%=request.getContextPath()%>/js/ruang-admin.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
</body>

</html>

