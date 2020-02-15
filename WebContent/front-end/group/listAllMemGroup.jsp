<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.group.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.group_join.model.*"%>
<%@ page import="java.util.*"%>
<%
	String mem_no = (String) session.getAttribute("mem_no");
	List<Group_joinVO> list = (List<Group_joinVO>) request.getAttribute("group_joinList");
	if(list==null){
		Group_joinService group_joinSvc = new Group_joinService();
		GroupService groupSvc = new GroupService();				
			list = group_joinSvc.getAllForMem(mem_no);
			for (int i = 0; i < list.size(); i++) {
				Group_joinVO gj = list.get(i);
				GroupVO groupVO = groupSvc.getOneGroup(gj.getGroup_no());
				if (!groupVO.getMem_no().equals(mem_no) || groupVO.getGroup_status()!=0) {
					list.remove(i);
					i--;
				}
			}
		}
	request.setAttribute("list", list);
%>
<jsp:useBean id="groupSvc" class="com.group.model.GroupService" />
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
<!-- Required meta tags -->
<meta charset=" utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- awesome -->
<script src="https://kit.fontawesome.com/9fbcc321af.js"></script>
<!-- Bootstrap CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css">
<!-- dive.css -->
<link rel=stylesheet type="text/css" href="<%=request.getContextPath()%>/dive.css">
<!-- 自行設定的CSS -->
<link rel=stylesheet type="text/css" href="<%=request.getContextPath()%>/css/listAllMemGroup.css">
<!-- 日期 -->
<link rel=stylesheet type="text/css" href="<%=request.getContextPath()%>/css/jquery.datetimepicker.css">
<!-- 套版用CSS -->
<link rel=stylesheet type="text/css" href="<%=request.getContextPath()%>/css/sb-admin-2.min.css">
<style>
body{
    background-image: url(https://images.unsplash.com/photo-1544082043-6acb5b6810ea?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=838&q=80);
    background-size: cover;
    background-repeat: no-repeat;
    }
#wrapper {
	margin: 150px;
/* 	margin-bottom:0; */
	min-height: calc(100vh - (75px + 167.5px))
}

.card {
	margin: 30px 0 0 20px;
	width: 97%;
	height: 200px;
	font-size: 20px;
}

.cardbody {
	margin: auto 0;
}

#wrapper #content-wrapper {
	width: 80%;
	margin: auto;
}

.group_photo {
	height: 130px;
	width: 200px;
}

.Wtitile {
	color: #666;
	font-size: 20px;
	font-weight: bold;
	line-height: 22px;
	word-break: break-all;
	word-wrap: break-word;
	white-space: normal;
	overflow: hidden;
}

.photodive {
	float: left;
	margin-right: 20px;
}

.photobody {
	padding-left: 0;
	margin-top: 8px;
}

.mem_photo {
	border-radius: 50%;
	box-shadow: 0 0 0 2px #F9E9BC;
	width: 30px;
	height: 30px;
}

#wrapper {
	display: flex;
}
</style>

<title>我的揪團</title>

</head>

<body>

	<div id="wrapper">
		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">
				<nav class="navbar navbar-expand-lg	 navbar-dark bg-dark">
					<div class="collapse navbar-collapse" id="navbarNav">
						<ul class="navbar-nav">
							<li class="nav-item"><a class="nav-link"
								href="<%=request.getContextPath()%>/group/group.do?action=mygroup&mem_no=${memVO.mem_no}">我開立的揪團</a></li>
							<li class="nav-item"><a class="nav-link"
								href="<%=request.getContextPath()%>/group/group.do?action=checking&mem_no=${memVO.mem_no}">報名中揪團</a></li>
							<li class="nav-item"><a class="nav-link"
								href="<%=request.getContextPath()%>/group/group.do?action=history&mem_no=${memVO.mem_no}">歷史揪團</a></li>
						</ul>
					</div>
				</nav>

				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>


				<c:if test="${empty errorMsgs}">
					<div class="total_group"><%@ include
							file="memAllGroupPage1.file"%></div>
					<c:forEach var="group_joinVO" items="${list}"
						begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">

						<div class="row">
							<!-- Border Left Utilities -->
							<div class="col-12">
								<div class="card mb-4 py-3 border-left-primary">
									<div class="card-body">
										<!-- 取出memSvc -->
										<jsp:useBean id="memSvc" class="com.mem.model.MemService" />
										<jsp:useBean id="diveSvc" class="com.dive.model.DiveService" />
										<c:forEach var="groupVO" items="${groupSvc.all }">
											<c:if test="${groupVO.group_no==group_joinVO.group_no }">
												<div class="photodive">
													<img src="<%=request.getContextPath()%>/ShowPic_V1?group_no=${groupVO.group_no}" class="group_photo">
												</div>
												<div class="row">
													<div class="col-12">
														<div class="row">
															<div class="col-1.5">
																<div>
																	<font>揪團名稱:</font>
																</div>
																<div>
																	<font>發起人:</font>
																</div>
																<div>
																	<font>潛水點:</font>
																</div>
															</div>
															<div class="col-3">
																<div>
																	<font class="Wtitile"> ${groupVO.group_name} </font>
																</div>
																<c:forEach var="memVO" items="${memSvc.all}">
																	<c:if test="${memVO.mem_no == groupVO.mem_no}">
																		<div>
																			<font class="Wtitile">${memVO.mem_name}</font>
																		</div>
																	</c:if>
																</c:forEach>
																<c:forEach var="diveVO" items="${diveSvc.all}">
																	<c:if test="${diveVO.dive_no == groupVO.dive_no}">
																		<div>
																			<font class="Wtitile">${diveVO.dive_name}</font>
																		</div>
																	</c:if>
																</c:forEach>
															</div>
															<div class="col-1.5">
																<div>
																	<font>活動時間:</font>
																</div>
																<div>
																	<font>集合地點:</font>
																</div>
																<div>
																	<font>揪團狀態:</font>
																</div>
															</div>
															<div class="col-6">
																<div>
																	<font class="Wtitile">${groupVO.group_begin_time }</font>
																</div>
																<div>
																	<font class="Wtitile">${groupVO.group_mp }</font>
																</div>
																<div>
																	<c:choose>
																		<c:when test="${groupVO.group_status==0}">
																			<font class="Wtitile">報名中</font>
																		</c:when>
																		
																		<c:when test="${groupVO.group_status==1}">
																			<font class="Wtitile">已完成</font>
																		</c:when>
																		
																		<c:when test="${groupVO.group_status==2}">
																			<font class="Wtitile">團主已取消</font>
																		</c:when>
																		
																		<c:when test="${groupVO.group_status==3}">
																			<font class="Wtitile">被檢舉下架</font>
																		</c:when>
																	</c:choose>
																</div>
															</div>

														</div>
													</div>

													<jsp:useBean id="group_joinSvc2" class="com.group_join.model.Group_joinService" />
													<div class="col-12 photobody">
														<div class="row">
															<div class="col-9">
																<font>參團者:
																 <c:forEach var="group_joinVO2" items="${group_joinSvc2.getOneGroup(group_joinVO.group_no)}">
																		<c:if test="${group_joinVO2.group_jo_status==1}">
																		<img src="<%=request.getContextPath()%>/ShowPic_V1?mem_no=${group_joinVO2.mem_no}" class="mem_photo">
																		</c:if>
																 </c:forEach></font>
															</div>
															<div class="col-3">
																<form METHOD="post" action="<%=request.getContextPath()%>/group/group.do" id="cancel">
																	<input id="disabled" type="submit" value="查看詳情">
																	<input type="hidden" name="mem_no" value="${memVO.mem_no}">
																	<input type="hidden" name="group_no" value="${groupVO.group_no}">
																	<input type="hidden" name="action" value="getOne_For_Display">
																</form>
																<c:if test="${group_joinVO.group_jo_status==0&&groupVO.group_status==0&&groupVO.mem_no!=memVO.mem_no}">
																	<form METHOD="post" action="<%=request.getContextPath()%>/group/group_join.do">
																		<input id="disabled" type="submit" value="取消報名">
																		<input type="hidden" name="mem_no" value="${memVO.mem_no }">
																		<input type="hidden" name="group_no" value="${groupVO.group_no }">
																		<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>"> 
																		<input type="hidden" name="action" value="cancel">
																	</form>
																</c:if>
																<!-- 變動按鈕 -->
																	<c:choose>
																		<c:when test="${groupVO.getMem_no() == memVO.getMem_no()&&groupVO.group_status==0}">
																				<form METHOD="post" action="<%=request.getContextPath()%>/group/group.do">
																					<input id="WsBuyBtn" value="修改揪團" type="submit">
																					<input type="hidden" name="group_no" value="${groupVO.group_no }">
																					<input type="hidden" name="action" value="getOne_For_Update">
																				</form> 
																			</c:when>
																		<c:when test="${group_joinVO.group_jo_status==1&&groupVO.group_status==0}">
																			<form METHOD="post" action="<%=request.getContextPath()%>/group/group_join.do">
																				<input id="WsBuyBtn" value="退出揪團" type="submit">
																				<input type="hidden" name="group_no" value="${groupVO.group_no }">
																				<input type="hidden" name="mem_no" value="${memVO.mem_no }">
																				<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
																				<input type="hidden" name="action" value="quit">
																			</form>
																		</c:when>
																	</c:choose> 
															</div>
														</div>
													</div>
												</div>
											</c:if>
										</c:forEach>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
					<!-- /.container-fluid -->
					<!-- 換頁 -->
					<div><%@ include file="memAllGroupPage2.file"%></div>
				</c:if>
			</div>
		</div>

	</div>
	<!-- End of Page Wrapper -->


	<script src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.4.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/dive.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.datetimepicker.full.js"></script>
	<script src="<%=request.getContextPath()%>/js/sb-admin-2.min.js"></script>
</body>

</html>