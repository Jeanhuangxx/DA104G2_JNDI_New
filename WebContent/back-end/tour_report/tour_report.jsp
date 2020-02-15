<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.tour_report.model.*"%>

<%
	TourReportService tourReportSvc = new TourReportService();
	List<TourReportVO> list = tourReportSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<!doctype html>
<html lang="en">

<head>
<link href="<%= request.getContextPath()%>/images/twdivers.png" rel="icon">
<title>潛進台灣 GO !</title>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<script src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.4.1.min.js"></script>
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
</style>
</head>
<jsp:useBean id="tourSvc" scope="page"
	class="com.tour.model.TourService" />
<jsp:useBean id="admSvc" scope="page" class="com.adm.model.AdmService" />
<!--------------------------以上--------------------------------->
<body>
	<jsp:include page="/back-end/manage/admNav.jsp" />
	<!-----------------------------------------此區塊放自己的資料----------------------------------------------------->
	<table class="table text-center" id="ReportTable">
		<thead class="thead-dark">
			<tr>
				<th scope="col">行程編號</th>
				<th scope="col">行程名稱</th>
				<th scope="col">檢舉人</th>
				<th scope="col">檢舉時間</th>
				<th scope="col">檢舉事由</th>
				<th scope="col">處理狀態</th>
				<th scope="col">審核者</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="tourReportVO" items="${list}" varStatus="count">
				<tr>
					<th scope="row">
					<a
						href="<%=request.getContextPath()%>/tour/tour.do?action=getOneTour&tour_no=${tourReportVO.tour_no}">${tourReportVO.tour_no}</a></th>
					<td>${tourSvc.getOneTour(tourReportVO.tour_no).tour_name}</td>
					<td>${tourReportVO.mem_no}</td>
					<td><fmt:formatDate value="${tourReportVO.tour_re_time}"
							type="both" dateStyle="short" timeStyle="short" /></td>
					<td class="breakword">${tourReportVO.tour_re_note}</td>
					<td>${applicationScope.tourReportStatus[tourReportVO.tour_re_status]}</td>
					<td><c:if test="${tourReportVO.tour_re_status == 0}">
							<button type="button" class="btn btn-warning reportCheck"
								data-toggle="modal" data-target="#reportCheck${count.index}">進行審核</button>
						</c:if> <c:if test="${tourReportVO.tour_re_status != 0}">
							<p class="mb-0"
								style="font-weight: bold; color: rgb(7, 105, 32);">${admVO.adm_name}</p>
						</c:if>
						<div class="modal fade" id="reportCheck${count.index}"
							tabindex="-1" role="dialog"
							aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
							<div class="modal-dialog modal-dialog-centered" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title"
											id="exampleModalCenterTitle${count.index}">檢舉詳情</h5>
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<div class="modal-body">
										<p>行程編號: ${tourReportVO.tour_no}</p>
										<p>行程名稱:
											${tourSvc.getOneTour(tourReportVO.tour_no).tour_name}</p>
										<p>被檢舉店家:
											${memSvc.getOneMem(tourSvc.getOneTour(tourReportVO.tour_no).mem_no).mem_name}</p>
										<p>檢舉人編號: ${tourReportVO.mem_no}</p>
										<p>
											檢舉時間:
											<fmt:formatDate value="${tourReportVO.tour_re_time}"
												type="both" dateStyle="short" timeStyle="short" />
										</p>
										<p>檢舉事由: ${tourReportVO.tour_re_note}</p>
									</div>
									<div class="modal-footer">
										<input type="hidden" name="tour_status"
											value="${tourSvc.getOneTour(tourReportVO.tour_no).tour_status}">
										<input type="hidden" name="tour_re_no"
											value="${tourReportVO.tour_re_no}">
										<button type="button" class="btn btn-success"
											id="passReport${count.index}">通過</button>
										<button type="button" class="btn btn-danger"
											id="rejectReport${count.index}">未通過</button>
									</div>
								</div>
							</div>
						</div></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<!----------------------------------------------以上-------------------------------------------------------------->
	</div>
	</div>
	</div>
	<footer class="text-center text-secondary">
		&copy; DA104G2 GO ! <span id="year"></span>
	</footer>
	<script src="<%=request.getContextPath()%>/back-end/manage/js/poper.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/manage/js/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/back-end/manage/js/manager.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>

<script type="text/javascript">

<c:forEach var="tourReportVO" items="${list}" varStatus="count">

	$("#passReport${count.index}").click(function(){
		$.ajax({
			url: "<%=request.getContextPath()%>/tour_report/tourReport.do",
			type: "POST",
			data: {
				action: "passReport",
				tour_status: "${tourSvc.getOneTour(tourReportVO.tour_no).tour_status}",
				tour_re_status: "${tourReportVO.tour_re_status}",
				tour_no: "${tourReportVO.tour_no}",
				mem_no: "${tourReportVO.mem_no}"
					
			},
			success: function(data){
				Swal.fire({
	  	  				 text: '狀態更新成功',
	  	  				 icon: 'success',
	  	  				 onClose: () => {location.reload()}
				});
				$("#reportCheck${count.index}").modal('hide');
			}

		});
		
		
		
	})
	
	$("#rejectReport${count.index}").click(function() {
		$.ajax({
			url: "<%=request.getContextPath()%>/tour_report/tourReport.do",
			type: "POST",
			data: {
				action: "rejectReport",
				tour_status: "${tourSvc.getOneTour(tourReportVO.tour_no).tour_status}",
				tour_re_status: "${tourReportVO.tour_re_status}",
				tour_no: "${tourReportVO.tour_no}",
				mem_no: "${tourReportVO.mem_no}"
					
			},
			success: function(data){
				Swal.fire({
	  	  				 text: '狀態更新成功',
	  	  				 icon: 'success',
	  	  				  onClose: () => {location.reload()}
				});
				$("#reportCheck${count.index}").modal('hide');
<%-- 				$("#ReportTable").load("<%= request.getContextPath()%>/back-end/report/tour_report.jsp"); --%>
					
			}

		});
	})
	
</c:forEach>



</script>
</body>
</html>