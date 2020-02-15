<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tour.model.*"%>
<%@ page import="com.tour_like.model.*"%>
<%@ page import="com.mem.model.*"%>
<%
	// 	String mem_no = ((MemVO) session.getAttribute("memVO")).getMem_no();
	String mem_no = "";
	if (session.getAttribute("memVO") != null) {
		mem_no = ((MemVO) session.getAttribute("memVO")).getMem_no();
	}
	TourVO tourVO = (TourVO) request.getAttribute("tourVO");
	TourLikeService tourLikeSvc = new TourLikeService();
	List<TourLikeVO> tourList = tourLikeSvc.getListFromMem(mem_no);

	//抓取有無被追蹤
	boolean findTourLike = false;
	for (TourLikeVO tList : tourList) {
		System.out.println(tList.getTour_no()+tourVO.getTour_no());
		if (tList.getTour_no().contains(tourVO.getTour_no())) {
			findTourLike = true;
			break;
		} else {
			findTourLike = false;
		}
	}

// 	request.getSession().setAttribute("URI", request.getRequestURI());
	pageContext.setAttribute("tourList", tourList);
%>
<!doctype html>
<html lang="en">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link
	href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">
<!-- dive.css -->
<link rel=stylesheet type="text/css"
	href="<%=request.getContextPath()%>/css/dive.css">
<!-- 自行設定的CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/travel-detail.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/font-awesome-4.7.0/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<!-- jQuery -->
<script
	src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.4.1.min.js"></script>

<link href="<%=request.getContextPath()%>/images/twdivers.png"
	rel="icon">
<style type="text/css">
.navbar-nav .nav-item .nav-link {
	color: #FFFFFF;
	font-size: 26px;
	font-weight: bold;
	font-family: "Microsoft JhengHei UI";
}

.navbar-expand-lg {
	background-color: #000000;
}
</style>
<title>潛進台灣Taiwan Divers</title>
</head>

<script>
var map, geocoder;

function initMap() {
geocoder = new google.maps.Geocoder();
map = new google.maps.Map(document.getElementById('map'), {
    zoom: 17
});

var address = document.getElementById("storeLoc").innerText;
geocoder.geocode( { 'address': address}, function(results, status) {
    if (status == 'OK') {
    map.setCenter(results[0].geometry.location);
    var marker = new google.maps.Marker({
        map: map,
        position: results[0].geometry.location
    });
    } else {
    console.log(status);
    }
});
}

</script>

<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService" />
<jsp:useBean id="tourReportSvc" scope="page"
	class="com.tour_report.model.TourReportService" />
<jsp:useBean id="tourOrderSvc" scope="page"
	class="com.tour_order.model.TourOrderService" />
<jsp:useBean id="tourRoomSvc" scope="page"
	class="com.tour_room.model.TourRoomService" />
<jsp:useBean id="tourImgSvc" scope="page"
	class="com.tour_image.model.TourImageService" />
<jsp:useBean id="roomImgSvc" scope="page"
	class="com.room_image.model.RoomImageService" />


<body>
	<header>
		<!-- navigation bar -->
		<nav class="navbar navbar-expand-lg" id="nav">
			<a href="<%=request.getContextPath()%>/index.jsp"> <img
				src="<%=request.getContextPath()%>/images/twdivers-light.png"
				id="n_img" class="navbar-brand"
				href="<%=request.getContextPath()%>/index.html">
			</a>
			<button class="navbar-toggler navbar-light" type="button"
				data-toggle="collapse" data-target="#navbarNav"
				aria-controls="navbarNav" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/front-end/group/listAllGroup.jsp">潛點揪團</a></li>
					<li class="nav-item"><a class="nav-link"
						href="<%=request.getContextPath()%>/front-end/travel-page/travel.jsp">行程</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/front-end/equipment/equip_shop.jsp">裝備</a></li>
					<li class="nav-item"><a class="nav-link" href="<%=request.getContextPath()%>/front-end/album/albumList.jsp">相簿</a></li>
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

		<div data-toggle="modal" data-target="#exampleModal">
			<img src="<%=request.getContextPath()%>/images/confused.png"
				id="fixedbutton">
		</div>
		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true" id="reportBtn">
			<div class="modal-dialog" role="document">
				<div class="modal-content" style="width: 600px;">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">問題回報</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<form>
							<div class="form-group">

								<!-- 尚未檢舉過 -->
								<c:if
									test="${tourReportSvc.getReportbyMemTour(memVO.mem_no, tourVO.tour_no).tour_re_status != 0 && memVO.mem_type != 1}">
									<label for="message-text" class="col-form-label">問題描述:</label>
									<textarea class="form-control" id="tour_re_note"
										id="message-text" required></textarea>
									<p id="pleaseInsert" style="color: red;">${errorMsgs}</p>
							</div>
						</form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-dismiss="modal">關閉</button>
						<button type="button" id="sendReport" class="btn btn-primary">傳送</button>
					</div>
					</c:if>

					<!-- 已檢舉但未審核 -->
					<c:if
						test="${tourReportSvc.getReportbyMemTour(memVO.mem_no, tourVO.tour_no).tour_re_status == 0}">
						<p class="reportReply text-center">
							你的問題相關人員正在處理，請稍安勿躁。
						<br>	
						<img
								src="<%=request.getContextPath()%>/images/reReport.gif"
								style="height: 100px; width: 100px;">
						</p>
				</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">關閉</button>
			</div>
			</c:if>
			
			<c:if
				test="${not empty sessionScope.account && memVO.mem_type == 1}">
				<p class="reportReply text-center">非會員帳號。
				<br>
				<img src="<%=request.getContextPath()%>/images/reReport.gif" style="height: 100px; width: 100px;">
				</p>
			</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-dismiss="modal">關閉</button>
			</div>
			</c:if>

		</div>
		</div>
		</div>

		<!-- 標題 評價 地點 加入追蹤 -->
		<div class="container-fluid" id="topic_area">
			<div class="row align-items-center">

				<div class="col-md-8 offset-md-1">
					<p class="topic">${tourVO.tour_name}</p>
					<div class="top_review_div">
						<span class="top_review_span"> <span> <img id="star"
								src="<%=request.getContextPath()%>/images/star.png">
						</span> <a href="#" class="top_review_score">
								${tourOrderSvc.getAvgTourScore(tourVO.tour_no)}
								(${tourOrderSvc.getCountbyReviewNote(tourVO.tour_no)}) </a>
						</span> <span class="top_place_span"> <a href="#">${tourVO.tour_place}</a>
						</span>
					</div>
				</div>
				<div class="top_add_fav_div mx-auto">
					<span class="top_add_fav_span">
						<form method="post"
							action="<%=request.getContextPath()%>/tour_like/tourLike.do">

							<!-- 							若有登入有追蹤的話顯示實心 有登入無追蹤顯示空心 -->
							<c:if test="${not empty sessionScope.account && memVO.mem_type != 1}">
								<input type="hidden" name="tour_no"
									value="${tourVO.tour_no}">
									<c:if test="<%= findTourLike%>">
									<button type="submit" class="btn top_add_fav" name="action"
										value="cancelFollow" id="unfollowTrip">
										<i class="fa fa-heart" style="font-size: 28px; color: red;"></i>
									</button>
								</c:if>
								<c:if test="<%= !findTourLike%>">
									<button type="submit" class="btn top_add_fav" name="action"
										value="followTrip" id="followTrip">
										<i class="fa fa-heart-o" style="font-size: 28px; color: red;"></i>
									</button>
								</c:if>
								
							</c:if>

						</form>
					</span>
				</div>
			</div>
		</div>

		<!-- slideshow -->
		<div class="container-fluid">
			<div class="row justify-content-center">
				<div class="col-md-9">
					<div id="carouselExampleIndicators" class="carousel slide "
						data-ride="carousel" data-interval="4000">

						<div class="carousel-inner">
							<c:forEach var="tourImage"
								items="${tourImgSvc.getImgbyTourNo(tourVO.tour_no)}">
								<div class="carousel-item">
									<img class="picShow d-block w-100"
										src="<%=request.getContextPath()%>/ShowPic_V1?tour_img_no=${tourImage.tour_img_no}">
								</div>
							</c:forEach>
						</div>

						<a class="carousel-control-prev" href="#carouselExampleIndicators"
							role="button" data-slide="prev"> <span
							class="carousel-control-prev-icon" aria-hidden="true"></span> <span
							class="sr-only">Previous</span>
						</a> <a class="carousel-control-next"
							href="#carouselExampleIndicators" role="button" data-slide="next">
							<span class="carousel-control-next-icon" aria-hidden="true"></span>
							<span class="sr-only">Next</span>
						</a>
					</div>
				</div>
			</div>
		</div>
	</header>


	<!-- 特 日 房 地 評 -->
	<div class="container-fluid">
		<div class="row justify-content-start">
			<div class="col-md-11 offset-md-1">
				<nav id="navbar-example2" class="navbar navbar-light">
					<ul class="nav nav-pills">
						<li class="nav-item"><a class="hightlights nav-link"
							href="#highlights">行程特色</a></li>
						<li class="nav-item"><a class="date nav-link" href="#date">日期
								/ 價格</a></li>
						<li class="nav-item"><a class="map nav-link" href="#loc">店家地點</a>
						</li>
						<li class="nav-item"><a class="reviews nav-link"
							href="#reviews">評論</a></li>
					</ul>
				</nav>
			</div>
		</div>


		<div data-target="#navbar-example2">
			<div class="row justify-content-start">

				<div class="col-md-3 offset-md-2">
					<p class="highlights_cap" id="highlights">行程特色</p>
				</div>
				<div class="col-md-5">
					<div class="highlights_list">
						<ul class="lists">
							<li class="list1">位於斯米蘭群島的東北21公里海中，是一大塊玄武岩，峭壁上有許多美麗珊瑚，
								著名景觀是豹鯊及各種礁鯊常出現在30~40公尺深的海裡。</li>
							<li class="list2">各式各樣的熱帶魚穿梭不停，常見護士鯊Nurse
								Sharks，豹鯊和魚，也有機會觀賞鯨鯊。</li>
							<li class="list3">斯米蘭群島由九個小島組成，九個小島裡，4號Koh Miang和8號Koh
								Similan最為著名，尤其是8號島受白淨細緻的海灘所圍繞，
								島上還有一顆矗立在山頂，鬼斧神工般的大岩石，而爬上這顆「象頭岩」上，即可飽覽整座島的遼闊美景。</li>
						</ul>
					</div>
				</div>

			</div>

			<hr class="line1">

			<!-- date n price caption -->
			<div class="row">
				<div class="col-md-9 offset-md-1">
					<p class="date_cap" id="date">日期 / 價格</p>
				</div>
			</div>

			<!-- date n price navbar -->
			<div class="row justify-content-center">
				<div class="col-md-9">
					<div class="row dp_nav text-center">
						<div class="col-md-2">
							<span class="start_date">出發日期</span>
						</div>
						<div class="col-md-2">
							<span class="end_date">回程日期</span>
						</div>
						<div class="col-md-2">
							<span class="dives_amt">潛水支數</span>
						</div>
						<div class="col-md-3">
							<span class="room">房間</span>
						</div>
						<div class="col-md-3">
							<span class="price">價格</span>
						</div>
					</div>
				</div>
			</div>


			<!-- date n price detail -->
			<div class="row justify-content-center">
				<div class="col-md-9">
					<div class="row dp_detail text-center align-items-center">
						<div class="col-md-2">
							<span class="start_date">${tourVO.tour_bgn_date}</span>
						</div>
						<div class="col-md-2">
							<span class="end_date">${tourVO.tour_end_date}</span>
						</div>
						<div class="col-md-2">
							<span class="dives_amt">${tourVO.tour_dives}</span>
						</div>
						<div class="col-md-4" style="padding-left: 0px;">
							<div class="row room_detail align-items-center">
								<div class="col-md-6">
									<div id="carouselExampleControls2" class="carousel slide"
										data-ride="carousel">
										<div class="carousel-inner">
											<c:forEach var="roomImage"
												items="${roomImgSvc.getRoomImageList(tourRoomSvc.getByTourNo(tourVO.tour_no).tour_room_no)}">
												<div class="carousel-item">
													<img
														src="<%=request.getContextPath()%>/ShowPic_V1?room_img_no=${roomImage.room_img_no}"
														class="room_img d-block w-100">
												</div>
											</c:forEach>
										</div>
										<a class="carousel-control-prev"
											href="#carouselExampleControls2" role="button"
											data-slide="prev"> <span
											class="carousel-control-prev-icon" aria-hidden="true"></span>
											<span class="sr-only">Previous</span>
										</a> <a class="carousel-control-next"
											href="#carouselExampleControls2" role="button"
											data-slide="next"> <span
											class="carousel-control-next-icon" aria-hidden="true"></span>
											<span class="sr-only">Next</span>
										</a>
									</div>
								</div>
								<div class="room_info col-md-6">
									<ul class="room_info_lists">
										<li><i class="fa fa-user"></i> <span>容納人數: <span>${tourRoomSvc.getByTourNo(tourVO.getTour_no()).room_ppl}</span>人
										</span></li>
										<li><i class="fa fa-bed"></i> <span>床型: <span>${applicationScope.tourRoomBedSize[tourRoomSvc.getByTourNo(tourVO.getTour_no()).bed_size]}</span></span>
										</li>
										<li><i class="fa fa-shower"></i> <span>獨立衛浴: <span>${applicationScope.tourRoomPrivBath[tourRoomSvc.getByTourNo(tourVO.getTour_no()).room_priv_br]}</span></span>
										</li>
										<li><i class="fa fa-snowflake-o"></i> <span>空調: <span>${applicationScope.tourRoomAircon[tourRoomSvc.getByTourNo(tourVO.getTour_no()).room_aircon]}</span></span>
										</li>
									</ul>
								</div>
							</div>
						</div>
						<div class="col-md-1 mb-0" style="padding: 0;">
							<span class="price">$${tourVO.tour_price}</span>
							<c:if test="${empty tourOrderSvc.getTourByMemTour(tourVO.tour_no, memVO.mem_no).tour_no && tourVO.tour_max_num - tourOrderSvc.getnumberOfAttd(tourVO.tour_no) > 0 && memVO.mem_type != 1}">
							<a href="<%=request.getContextPath()%>/tour/tour.do?action=checkout&tour_no=${tourVO.tour_no}">
								<input type="hidden" name="tour_no" value="${tourVO.tour_no}">
								<input type="submit" value="預約" class="submit btn mt-0">
							</a>
							</c:if>
							<c:if test="${not empty tourOrderSvc.getTourByMemTour(tourVO.tour_no, memVO.mem_no).tour_no}">
								<button type="button" class="submit btn mt-0" disabled>已購買</button>
							</c:if>
							<br>
							<c:if test="${tourVO.tour_max_num - tourOrderSvc.getnumberOfAttd(tourVO.tour_no) <= 0}">
								<p class="full mt-0">已額滿</p>
							</c:if>
							<c:if test="${memVO.mem_type == 1}">
								<p class="full mt-0">非會員帳號</p>
							</c:if>
						</div>
					</div>
				</div>
			</div>



			<div class="row justify-content-center">
				<div class="dp_nav2 col-md-9"></div>
			</div>

			<hr class="line1">

			<!-- Place -->
			<div class="row">
				<div class="col-md-9 offset-md-2">
					<p class="loc_cap" id="loc">店家地點</p>
				</div>
			</div>

			<div class="row align-items-center justify-content-start">
				<div class="col-md-4 offset-md-2">
					<div id="map"></div>
				</div>
				<div class="col-md-6">
					<div class="store_loc">
						<div class="store_name">
							<span class="st_nm">店家名稱:</span>
							<div style="margin-top: 20px;">
								<span class="st_loc">${memSvc.getOneMem(tourVO.getMem_no()).mem_name}</span>
							</div>
						</div>
						<div class="store_add">
							<span class="st_add_cap">店家地址:</span>
							<div style="margin-top: 20px;">
								<span class="st_add_dtl" id="storeLoc">${memSvc.getOneMem(tourVO.getMem_no()).mem_add}</span>
							</div>
						</div>
					</div>
				</div>
			</div>

			<hr class="line1">

			<!-- Reviews -->
			<div class="row">
				<div class="col-md-9 offset-md-2">
					<span class="rev_cap" id="reviews">評論</span> <span> <img
						id="star_b" src="<%=request.getContextPath()%>/images/star.png">
					</span>
					<span class="rev_score_b">${tourOrderSvc.getAvgTourScore(tourVO.tour_no)}</span>
					<span class="rev_ppl">(共有
					<span>
					<c:if test="${tourOrderSvc.getCountbyReviewNote(tourVO.tour_no) == null}">
					0
					</c:if> <c:if
								test="${tourOrderSvc.getCountbyReviewNote(tourVO.tour_no) != null}">
					${tourOrderSvc.getCountbyReviewNote(tourVO.tour_no)}
					</c:if>
					</span> 則評論)
					</span>
				</div>
			</div>
			<c:if test="${tourOrderSvc.getCountbyReviewNote(tourVO.tour_no) == 0}">
				<div class="row justify-content-start justify-content-center"
				style="margin-top: 60px; margin-bottom: 60px;">
				<div class="col-md-8 offset-md-2">
					<div class="row align-items-center text-center">
						<div class="col-md-8">
							<img src="<%=request.getContextPath()%>/images/no_review.gif" style="height: 350px; width: 350px;">
						</div>
					</div>
				</div>

			</c:if>

			<c:forEach var="tourReviewList" items="${tourOrderSvc.getReviewList(tourVO.tour_no)}">
			<c:if test="${tourReviewList.tour_rev_note != null}">
			<div class="row justify-content-start"
				style="margin-top: 60px; margin-bottom: 60px;">
				<div class="col-md-8 offset-md-2">
					<div class="row align-items-center">
						<div class="col-md-1">
							<div class="mem_img">
							<c:if test="${memVO.mem_img == null}">
								<img class="mem_pic" src="<%=request.getContextPath()%>/images/mem_pic.png">
							</c:if>
							<c:if test="${memVO.mem_img != null}">
								<img class="mem_pic" src="<%=request.getContextPath()%>/ShowPic_V1?mem_no=${memVO.mem_no}">
							</c:if>
							</div>
						</div>
						<div class="col-md-3">
							<div class="mem_dtl">
								<span class="mem_name">${memSvc.getOneMem(tourReviewList.mem_no).mem_name}</span>
								<div>
									<span class="rev_date">評論於<fmt:formatDate value="${tourReviewList.tour_order_date}" pattern="yyyy-MM-dd"/></span>
								</div>
							</div>
						</div>
						<div class="col-md-8">
							<span class="rev_note">${tourReviewList.tour_rev_note}</span>
						</div>
					</div>
				</div>
			</div>

					<hr class="line2a">
				</c:if>
			</c:forEach>
		</div>

	</div>



	<footer>
		<div class="logo">
			<img src="<%=request.getContextPath()%>/images/twdivers.png"
				class="logo_img" alt="tw_logo">
		</div>
		<!-- 揪 行 裝 社 -->
		<ul class="nav justify-content-center" id="footer">
			<li class="nav-item"><a class="nav-link" href="#">潛點揪團</a></li>
			<li class="nav-item"><a class="nav-link"
				href="<%=request.getContextPath()%>/front-end/travel-page/travel.jsp">行程</a>
			</li>
			<li class="nav-item"><a class="nav-link" href="#">裝備</a></li>
			<li class="nav-item"><a class="nav-link" href="#">社群</a></li>
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



	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<!-- dive.js -->
	<!-- <script src="../dive.js"></script> -->
	<!-- google map js -->
	<script src="<%=request.getContextPath()%>/js/travel-detail.js"></script>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyABb9jgU2GykVDoJbqqXkQc5P5BG_xUaiY&callback=initMap"
		async defer>
	</script>
	<script
		src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>

</body>
<script type="text/javascript">
$(document).ready(function(){
	$("#sendReport").click(function(){
		
		if($.trim($("#tour_re_note").val()) == ""){
			$("#tour_re_note").focus();
			$("#pleaseInsert").text("請輸入問題");
		} else{
			$.ajax({
				url: "<%=request.getContextPath()%>/tour_report/tourReport.do",
				type: "POST",
				data: {
					action: "sendReport",
					tour_no: "${tourVO.tour_no}",
					mem_no: "${memVO.mem_no}",
					tour_re_note: $("#tour_re_note").val()
				},
				
				success: function(data){
					$("#pleaseInsert").text("");
					Swal.fire({
		  	  				  text: '已回報你的問題',
		  	  				  icon: 'success',
		  	  				  onClose: () => {location.reload()}
					});
					$("#reportBtn").modal('hide');
				}
				
			});
		}
			
	})
	
	$('#carouselExampleIndicators').find('.carousel-item').first().addClass('active');
	$('#carouselExampleControls2').find('.carousel-item').first().addClass('active');


})
</script>

</html>