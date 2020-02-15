<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adm.model.*"%>
<%@ page import="com.fun.model.*"%>
<%@ page import="com.pe.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	//AdmVO admVO = (AdmVO) request.getAttribute("admVO");
	AdmVO admVO = (AdmVO) session.getAttribute("admVO");
	pageContext.setAttribute("admVO", admVO);
	PeService peSvc = new PeService();
	List<PeVO> peList = peSvc.getOnePe(admVO.getAdm_no());
	pageContext.setAttribute("peList", peList);

	FunService funSvc = new FunService();
	List<FunVO> funList = funSvc.getAll();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<style type="text/css">
/* body { */
/* 	width: 1700px !important; */
/* } */
</style>
</head>
<body>
	<div id="bgimg">
		<nav
			class="navbar navbar-expand-sm navbar-light bg-light fixed-top justify-content-between"
			id="nav">
			<a class="navbar-brand"
				href="<%=request.getContextPath()%>/back-end/manage/manage.jsp">
				<img src="<%=request.getContextPath()%>/images/twdivers-light.png"
				id="n_img" class="navbar-brand" href="#">
			</a>

			<h1>後台管理</h1>

			<div class="navbar-nav">
				<a class="nav-item nav-link mr-3 h0"
					href="<%=request.getContextPath()%>/admlogin/LoginHandler.do?action=logOut">
					<c:if test="${admVO.adm_img!=null}">
						<img
							src="<%=request.getContextPath() %>/ShowPic_V1?adm_no=${admVO.adm_no}"
							id="n_img">${admVO.adm_name}
					</c:if> <c:if test="${admVO.adm_img==null}">
						<img src="<%=request.getContextPath()%>/images/tomcat.png"
							id="n_img">${admVO.adm_name}
					</c:if>
					<button type="button" class="btn btn-outline-warning" id="btn_log">LOGOUT</button>
				</a>
			</div>
		</nav>
		<div class="main">
			<div class="row no-gutters">
				<div class="col-2">
					<ul class="menu list-group text-center mt-0 ml-4">

						<c:forEach var="peVO2" items="${peList}">
							<c:if test="${peVO2.fun_no eq 'F001'}">
								<li class="list-group-item list-group-item-success d-block">
									<a class="h-100 d-block"
									href="<%=request.getContextPath()%>/back-end/manage/manage_select.jsp">管理員系統</a>

									<ul class="list-group text-center">
										<li class="list-group-item list-group-item-success"><a
											class=""
											href="<%=request.getContextPath()%>/back-end/manage/manage_select.jsp">管理員資料</a></li>
										<li class="list-group-item list-group-item-success"><a
											class=""
											href="<%=request.getContextPath()%>/back-end/manage/manage_add.jsp">新增管理員</a></li>
									</ul>
								</li>
							</c:if>

							<c:if test="${peVO2.fun_no eq 'F003'}">
								<li class="list-group-item list-group-item-success d-block"><a
									class="h-100 d-block" href="#">會員管理</a>
									<ul class="list-group text-center">
										<li class="list-group-item list-group-item-success"><a
											class="h-100 d-block"
											href="<%=request.getContextPath()%>/back-end/manage/member_listallstores.jsp">潛店會員</a></li>

									</ul></li>
							</c:if>

							<c:if test="${peVO2.fun_no eq 'F004'}">
								<li class="list-group-item list-group-item-success d-block"><a
									class="h-100 d-block" href="#">商城管理</a>
									<ul class="list-group text-center">

										<li class="list-group-item list-group-item-success"><a
											class="h-100 d-block"
											href="<%=request.getContextPath()%>/back-end/equip_order/select_page_order.jsp">訂單管理</a></li>

										<li class="list-group-item list-group-item-success"><a
											class="h-100 d-block"
											href="<%=request.getContextPath()%>/back-end/equipment/select_page.jsp">裝備管理</a></li>

									</ul></li>
							</c:if>

							<c:if test="${peVO2.fun_no eq 'F006'}">
								<li class="list-group-item list-group-item-success d-block"><a
									class="h-100 d-block" href="#">檢舉審核</a>
									<ul class="list-group text-center">
										<li class="list-group-item list-group-item-success"><a
											class="h-100 d-block" href="<%=request.getContextPath()%>/group/group_report.do?group_re_status=0&action=oncheck">揪團檢舉</a>
										<li class="list-group-item list-group-item-success"><a
											class="h-100 d-block"
											href="<%=request.getContextPath()%>/back-end/tour_report/tour_report.jsp">行程檢舉</a>
										<li class="list-group-item list-group-item-success"><a
											class="h-100 d-block"
											href="<%=request.getContextPath()%>/back-end/photo_report/photo_report_select_page.jsp">相片檢舉</a>
									</ul></li>
							</c:if>

							<c:if test="${peVO2.fun_no eq 'F007'}">
								<li class="list-group-item list-group-item-success d-block"><a
									class="h-100 d-block" href="#">潛點資訊維護</a>
									<ul class="list-group text-center">
										<li class="list-group-item list-group-item-success"><a
											class="h-100 d-block"
											href="<%=request.getContextPath()%>/back-end/manage/dive_listall.jsp">潛點開放</a></li>
									</ul></li>
							</c:if>

						</c:forEach>
					</ul>
				</div>
				<div class="col-8 offset-1">
</body>
</html>