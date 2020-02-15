<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tour_order.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.tour.model.*"%>

<%
TourService tourSvc = new TourService();
TourOrderService tourOrderSvc = new TourOrderService();

//透過mem_no取得tour資料
MemVO memVO = (MemVO) session.getAttribute("memVO");
String mem_no = memVO.getMem_no();
List<TourOrderVO> list = tourOrderSvc.getTourOrderList(mem_no);

List<TourVO> listTourVO = new ArrayList<TourVO>();
for (TourOrderVO tOrderVO : list) {
	listTourVO.add(tourSvc.getOneTour(tOrderVO.getTour_no()));
}

pageContext.setAttribute("listTourVO", listTourVO);

%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link href="<%= request.getContextPath()%>/images/twdivers.png" rel="icon">
  <title>會員後台</title>
  <script src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.4.1.min.js"></script>
  <link rel=stylesheet type="text/css" href="<%= request.getContextPath()%>/css/dive.css">
  <link href="<%= request.getContextPath()%>/vendors/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href="<%= request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
  <link href="<%=request.getContextPath()%>/css/store-trip.css" rel="stylesheet" type="text/css">
  <link href="<%=request.getContextPath()%>/css/member.css" rel="stylesheet" type="text/css">
  <link href="<%=request.getContextPath()%>/css/rating.css" rel="stylesheet" type="text/css">
 
  
</head>


<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemService"/>
<body id="page-top">
  <div id="wrapper">
    <!-- Sidebar -->
	<jsp:include page="/front-end/memberCenter/memNav.jsp"/>
    <!-- Sidebar -->
    
    <div id="content-wrapper" class="d-flex flex-column">
      <div id="content">
        <!-- TopBar -->
        <nav class="navbar navbar-expand navbar-light bg-navbar topbar mb-4 static-top">
          <button id="sidebarToggleTop" class="btn btn-link rounded-circle mr-3">
            <i class="fa fa-bars"></i>
          </button>
          <ul class="navbar-nav ml-auto">
           
          
            <div class="topbar-divider d-none d-sm-block"></div>
            <li class="nav-item dropdown no-arrow">
              <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown"
                aria-haspopup="true" aria-expanded="false">
                <c:if test="${memVO.mem_img != null}">
                <img class="img-profile rounded-circle" src="<%=request.getContextPath()%>/ShowPic_V1?mem_no=${memVO.mem_no}" style="max-width: 60px">
                <span class="ml-2 d-none d-lg-inline text-white small">${memVO.mem_name}</span>
              	</c:if>
              	<c:if test="${memVO.mem_img == null}">
                <img class="img-profile rounded-circle" src="<%=request.getContextPath()%>/images/mem_pic.png" style="max-width: 60px">
                <span class="ml-2 d-none d-lg-inline text-white small">${memVO.mem_name}</span>
              	</c:if>
              </a>
              <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="<%=request.getContextPath()%>/member/MemberServlet.do?action=logout">
                  <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>登出
                </a>
              </div>
            </li>
          </ul>
        </nav>
        <!-- Topbar -->

        <!-- Container Fluid-->
        				<div class="container-fluid" id="container-wrapper">
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<span class="trip_manage">行程訂單</span>
					</div>

					<nav>
						<div class="nav nav-tabs" id="nav-tab" role="tablist">
							<a class="nav-item nav-link active all_trip" id="nav-home-tab"
								data-toggle="tab" href="#nav-home" role="tab"
								aria-controls="nav-home" aria-selected="true">所有行程</a> <a
								class="nav-item nav-link listed_trip" id="nav-profile-tab"
								data-toggle="tab" href="#nav-profile" role="tab"
								aria-controls="nav-profile" aria-selected="false">已出團</a> <a
								class="nav-item nav-link unlisted_trip" id="nav-contact-tab"
								data-toggle="tab" href="#nav-contact" role="tab"
								aria-controls="nav-contact" aria-selected="false">未出團</a>
						</div>
					</nav>

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
											<th scope="col">狀態</th>
											<th scope="col"></th>
											<th scope="col"></th>
										</tr>
									</thead>
									<tbody>
										<c:set var="now" value="<%=new java.util.Date()%>" />
										<c:forEach var="tourVO" items="${listTourVO}" varStatus="count">
											<tr>
												<th scope="row">${tourVO.tour_name}</th>
												<td>${tourVO.tour_price}</td>
												<td>${tourVO.tour_bgn_date}</td>
												<td>${tourVO.tour_end_date}</td>
												<td><c:choose>
														<c:when test="${tourVO.tour_bgn_date > now}">未出團</c:when>
														<c:when test="${tourVO.tour_bgn_date < now}">已出團</c:when>
													</c:choose></td>

												<td>
													<form method="post" action="<%= request.getContextPath()%>/tour/tour.do">
													<input type="hidden" name="tour_no" value="${tourVO.tour_no}">
													<input type="hidden" name="action" value="getOneTour">
													<input class="btn viewMore" type="submit" value="查看詳情"></td>
													</form>
												</td>
												<td><c:if test="${tourVO.tour_bgn_date < now}">
														<button type="button" class="btn viewReview"
															data-toggle="modal" data-target="#review${count.index}">評價</button>
													</c:if>
													<div class="modal fade" id="review${count.index}"
														tabindex="-1" role="dialog"
														aria-labelledby="exampleModalCenterTitle"
														aria-hidden="true">
														<div class="modal-dialog modal-dialog-centered"
															role="document">
															<div class="modal-content">
																<div class="modal-header">
																	<h5 class="modal-title" id="exampleModalCenterTitle${count.index}">給予評價</h5>
																	<button type="button" class="close"
																		data-dismiss="modal" aria-label="Close">
																		<span aria-hidden="true">&times;</span>
																	</button>
																</div>
																<div class="modal-body">
																		<div class="row">
																			<div class="col-12">
																				<h6>進行評分:</h6>
																				<fieldset class="rating" id="rating${count.index}">
																					<input type="radio" id="star5${count.index}" name="rating"
																						value="5" /><label class="full" for="star5${count.index}"></label>
																					<input type="radio" id="star4${count.index}" name="rating"
																						value="4" /><label class="full" for="star4${count.index}"></label>
																					<input type="radio" id="star3${count.index}" name="rating"
																						value="3" /><label class="full" for="star3${count.index}"></label>
																					<input type="radio" id="star2${count.index}" name="rating"
																						value="2" /><label class="full" for="star2${count.index}"></label>
																					<input type="radio" id="star1${count.index}" name="rating"
																						value="1" /><label class="full" for="star1${count.index}"></label>
																				</fieldset>
																			</div>
																		</div>
																		<label for="message-text" class="col-form-label">給予評論:</label>
																		<textarea class="form-control" name="reviewArea"
																			id="message-text${count.index}"></textarea>
																			<div id="pleaseInsertSth${count.index}" style="color:red;"></div>
																</div>
																<div class="modal-footer">
																	<input type="hidden" name="action" value="sendReview">
																	<input type="hidden" name="tour_no"
																		value="${tourVO.tour_no}">
																	<button type="button" class="btn btn-primary" id="updateReview${count.index}">送出評分</button>
																</div>
															</div>
														</div>
													</div></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<div class="tab-pane fade" id="nav-profile" role="tabpanel"
							aria-labelledby="nav-profile-tab">
							<div>
								<table class="table">
									<thead>
										<tr>
											<th scope="col">行程名稱</th>
											<th scope="col">價格</th>
											<th scope="col">出發日期</th>
											<th scope="col">回程日期</th>
											<th scope="col">狀態</th>
											<th scope="col"></th>
											<th scope="col"></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="tourVO" items="${listTourVO}" varStatus="count">
											<c:if test="${tourVO.tour_bgn_date < now}">
												<tr>
													<th scope="row">${tourVO.tour_name}</th>
													<td>${tourVO.tour_price}</td>
													<td>${tourVO.tour_bgn_date}</td>
													<td>${tourVO.tour_end_date}</td>
													<td>已出團</td>
													<td>
														<form method="post" action="<%= request.getContextPath()%>/tour/tour.do">
														<input type="hidden" name="tour_no" value="${tourVO.tour_no}">
														<input type="hidden" name="action" value="getOneTour">
														<input class="btn viewMore" type="submit" value="查看詳情"></td>
														</form>
													<td>
														<button type="button" class="btn viewReview"
															data-toggle="modal" data-target="#reviewAB${count.index}">評價</button>
														<div class="modal fade" id="reviewAB${count.index}"
															tabindex="-1" role="dialog"
															aria-labelledby="exampleModalCenterTitle"
															aria-hidden="true">
															<div class="modal-dialog modal-dialog-centered"
																role="document">
																<div class="modal-content">
																	<div class="modal-header">
																		<h5 class="modal-title" id="exampleModalCenterTitleB${count.index}">給予評價</h5>
																		<button type="button" class="close"
																			data-dismiss="modal" aria-label="Close">
																			<span aria-hidden="true">&times;</span>
																		</button>
																	</div>
																	<div class="modal-body">
																			<div class="row">
																				<div class="col-12">
																					<h6>進行評分:</h6>
																					<fieldset class="rating" id="ratingB${count.index}">
																						<input type="radio" id="star5B${count.index}" name="rating"
																							value="5" /><label class="full" for="star5B${count.index}"></label>
																						<input type="radio" id="star4B${count.index}" name="rating"
																							value="4" /><label class="full" for="star4B${count.index}"></label>
																						<input type="radio" id="star3B${count.index}" name="rating"
																							value="3" /><label class="full" for="star3B${count.index}"></label>
																						<input type="radio" id="star2B${count.index}" name="rating"
																							value="2" /><label class="full" for="star2B${count.index}"></label>
																						<input type="radio" id="star1B${count.index}" name="rating"
																							value="1" /><label class="full" for="star1B${count.index}"></label>
																					</fieldset>
																				</div>
																			</div>
																			<label for="message-text" class="col-form-label">給予評論:</label>
																			<textarea class="form-control" name="reviewArea"
																				id="message-textB${count.index}"></textarea>
																			<div id="pleaseInsertSthB${count.index}" style="color:red;"></div>
																	</div>
																	<div class="modal-footer">
																		<input type="hidden" name="action" value="sendReview">
																		<input type="hidden" name="tour_no"
																			value="${tourVO.tour_no}">
																		<button type="button" class="btn btn-primary" id="updateReviewB${count.index}">送出評分</button>
																	</div>
																</div>
															</div>
														</div>
													</td>
												</tr>
											</c:if>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
						<div class="tab-pane fade" id="nav-contact" role="tabpanel"
							aria-labelledby="nav-contact-tab">
							<div>
								<table class="table">
									<thead>
										<tr>
											<th scope="col">行程名稱</th>
											<th scope="col">價格</th>
											<th scope="col">出發日期</th>
											<th scope="col">回程日期</th>
											<th scope="col">狀態</th>
											<th scope="col"></th>
											<th scope="col"></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="tourVO" items="${listTourVO}">
											<tr>
												<th scope="row">${tourVO.tour_name}</th>
												<td>${tourVO.tour_price}</td>
												<td>${tourVO.tour_bgn_date}</td>
												<td>${tourVO.tour_end_date}</td>
												
												<td>
													<form method="post" action="<%= request.getContextPath()%>/tour/tour.do">
													<input type="hidden" name="tour_no" value="${tourVO.tour_no}">
													<input type="hidden" name="action" value="getOneTour">
													<input class="btn viewMore" type="submit" value="查看詳情">
													</form>
												</td>
												
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
		<jsp:include page="/front-end/memberCenter/memFooter.jsp"/>
    </div>
  </div>

  <script src="<%= request.getContextPath()%>/vendors/bootstrap/js/bootstrap.bundle.min.js"></script>
  <script src="<%= request.getContextPath()%>/vendors/jquery-easing/jquery.easing.min.js"></script>
  <script src="<%= request.getContextPath()%>/js/ruang-admin.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
</body>

<script type="text/javascript">

<c:forEach var="tourVO" items="${listTourVO}" varStatus="count">

	$("#updateReview${count.index}").click(function(){
		
		//20200107
		if($.trim($("#message-text${count.index}").val()) == "" && !$("input[name='rating']:checked").val()){
			$("#pleaseInsertSth${count.index}").text("請勿空白");
			
		} else{
			$.ajax({
				url: "<%=request.getContextPath()%>/tour_order/tourOrder.do",
				type: "POST",
				data: {
					action: "sendReview",
					tour_rev: $("#rating${count.index} input[name=rating]:checked").val(),
					tour_rev_note: $("#message-text${count.index}").val(),
					mem_no: "${memVO.mem_no}",
					tour_no: "${tourVO.tour_no}",
				},
				
				success: function(data){
					$("#pleaseInsertSth${count.index}").text("");
					Swal.fire(
		  	  				  '感謝評價',
		  	  				  'success'
		  	  				);
					$("#review${count.index}").modal('hide');
				}
			});
		}
		
	})
	
	
	$("#updateReviewB${count.index}").click(function(){
		
		
		if(($.trim($("#message-text${count.index}").val()) == "") || ($(":radio[name='rating']:checked").val() == null)){
			$("#pleaseInsertSthB${count.index}").text("請勿空白");
			
		} else{
			$.ajax({
				url: "<%=request.getContextPath()%>/tour_order/tourOrder.do",
				type: "POST",
				data: {
					action: "sendReview",
					tour_rev: $("#ratingB${count.index} input[name=rating]:checked").val(),
					tour_rev_note: $("#message-textB${count.index}").val(),
					mem_no: "${memVO.mem_no}",
					tour_no: "${tourVO.tour_no}",
				},
				
				success: function(data){
					$("#pleaseInsertSthB${count.index}").text("");
					Swal.fire(
		  	  				  '感謝評價',
		  	  				  'success'
		  	  				);
					$("#reviewAB${count.index}").modal('hide');
				}
			});
		}
		
	})
	
</c:forEach>



</script>

</html>