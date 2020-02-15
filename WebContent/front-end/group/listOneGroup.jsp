<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.group.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.group_message.model.*"%>
<%@ page import="com.group_join.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	GroupVO groupVO = (GroupVO) request.getAttribute("groupVO");
	MemVO memVO = (MemVO) session.getAttribute("memVO");
	Group_messageService group_meSvc = new Group_messageService();
	List<Group_messageVO> list = group_meSvc.getOneForGroup(groupVO.getGroup_no());
	request.setAttribute("list", list);
	
	
	pageContext.setAttribute("groupVO", groupVO);
	long long_now =  new java.util.Date().getTime();
	request.setAttribute("long_now", long_now);
	
	
	Group_joinService group_joSvc = new Group_joinService();
	List<Group_joinVO> list_join = group_joSvc.getOneGroup(groupVO.getGroup_no());
	for(int i = 0; i<list_join.size(); i++){
		Group_joinVO gj  = list_join.get(i);
		if(gj.getGroup_jo_status()!=0){
			list_join.remove(i);
			i--;
		}
	}
	
	request.setAttribute("list_join", list_join);
	
%>
<jsp:useBean id="group_joinSvc" scope="page" class="com.group_join.model.Group_joinService" />

<html lang="zh-Hant">

<head>
<!-- Required meta tags -->
<meta charset=" utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<script src="https://kit.fontawesome.com/9fbcc321af.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css">
<link rel=stylesheet type="text/css" href="<%=request.getContextPath()%>/dive.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/listOneGroup.css">
<title>揪團詳情</title>
<style type="text/css">
body{
    background-image: url(https://images.unsplash.com/photo-1507525428034-b723cf961d3e?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1353&q=80);
    padding-right: 0px;
    background-size: cover;
    background-repeat: no-repeat;
}
.group_des {
/*  height: 300px;	
    /* margin: 150px auto; */
    /* font-family: 微軟正黑體修正; */
<%--     background-image: url(<%=request.getContextPath()%>/images/listone.jpg); --%>
/*     background-repeat: no-repeat; */
/*     background-size: 100% 100%; */
/*     background-position: center;     */
} 
</style>
</head>

<body onload="clock();return true">
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


	<div class="container">
		<div class="row mb-2">
			<div class="col-md-12">
				<div
					class="row no-gutters border rounded overflow-hidden flex-md-row shadow-sm h-md-250 position-relative"
					id="main-top">


					<div>
						<div class="col-auto d-none d-lg-block">
							<img
								src="<%=request.getContextPath()%>/ShowPic_V1?group_no=<%=groupVO.getGroup_no()%>"
								class="group_photo">
						</div>
						<c:if test="${memVO.mem_no!=groupVO.mem_no}">
							<div class="report col-auto d-none d-lg-block">
								<button type="button" class="btn btn-primary"
									data-toggle="modal" data-target="#exampleModal"
									data-whatever="@mdo">
									<i class="far fa-hand-paper"></i>我要檢舉
								</button>
							</div>
						</c:if>
						<!--檢舉跳窗 -->
						<div class="modal fade" id="exampleModal" tabindex="-1"
							role="dialog" aria-labelledby="exampleModalLabel"
							aria-hidden="true">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="exampleModalLabel">向管理員檢舉揪團</h5>
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
									</div>
									<div class="modal-body">
										<form>
											<div class="contianer">
												<div class="form-group row">
													<div class="col-2 re_div">
														<span class="re_span"><img class="_3hj5 img"
															src="https://www.facebook.com/images/assets_DO_NOT_HARDCODE/facebook_icons/badge-admin_filled_24_artillery-cherry.png"></span>
													</div>
													<div class="col-9">
														<span>告訴管理員這則留言有什麼問題。其他人不會看到你的姓名或檢舉內容。</span>
													</div>
												</div>
											</div>
											<div class="form-group"> 
												<label for="message-text" class="col-form-label">Message:</label>
												<textarea class="form-control" id="message-text" required></textarea>
											</div>
										</form>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-dismiss="modal">Close</button>
										<button type="button" class="btn btn-primary" id="re_button"
											data-dismiss="modal">Send message</button>
									</div>
								</div>
							</div>
						</div>

					</div>
					<div class="col p-4 d-flex flex-column position-static">
						<h3 class="Wtitile">${groupVO.group_name }</h3>
						<div class="context">
							<jsp:useBean id="memSvc" scope="page"
								class="com.mem.model.MemService" />
							<span>發起人 </span> <span><%=memSvc.getOneMem(groupVO.getMem_no()).getMem_name()%></span>
						</div>
						<div class="context">
							<jsp:useBean id="diveSvc" scope="page"
								class="com.dive.model.DiveService" />
							<span>潛水地點 <%=diveSvc.getOneDive(groupVO.getDive_no()).getDive_name()%></span>
						</div>
						<div class="context">
							<span>活動期間 </span> <span>
							<fmt:formatDate value="${groupVO.group_begin_time}" type="both" dateStyle="short" timeStyle="short"/>
							－<fmt:formatDate value="${groupVO.group_end_time}" type="both" dateStyle="short" timeStyle="short"/></span>
						</div>
						<div class="context">
							<span>集合地點 ${groupVO.group_mp}</span>
						</div>
						<div class="context">
							<span>連絡電話 </span> <span>${groupVO.group_tel }</span>
						</div>
						<div class="context" id="allpicture">
							<span>參加者 </span>
							<c:forEach var="group_joinVO"
								items="<%=group_joinSvc.getOneGroup(groupVO.getGroup_no())%>">
								<c:if test="${group_joinVO.group_jo_status==1}">
									<img id="${group_joinVO.mem_no}" class="participant"
										src="<%=request.getContextPath()%>/ShowPic_V1?mem_no=${group_joinVO.mem_no}">
								</c:if>
							</c:forEach>
						</div>
						<div class="context">
							<span>尚可報名人數 </span><input readonly="readonly" type="text"
								id="maxnum"
								value="${groupVO.group_max_num-groupVO.group_tour_num}">
						</div>


						<div id="clock" style="width: 250px">
							<form name="formnow">
								<font size="2" face="新細明體" id="time"> <br> 報名截止<br>
									<input type="text" name="dd" size=4 id="dd" disabled> 天 <input
									type="text" name="hh" size=2 id="hh" disabled> 小時 <input
									type="text" name="mm" size=2 id="mm" disabled> 分 <input
									type="text" name="ss" size=2 id="ss" disabled> 秒

								</font>
							</form>
						</div>						

						<!-- 變動按鈕 -->
						<div id="change">	
							<c:choose>
							<c:when test="${long_now>groupVO.group_tour_stop_time.getTime()}">
									
								</c:when>							
							
								<c:when test="${ groupVO.getMem_no() == memVO.getMem_no() && groupVO.group_status==0}">
									<form METHOD="post" action="<%=request.getContextPath()%>/group/group.do">
										<input id="WsBuyBtn" value="修改揪團" type="submit">
										<input type="hidden" name="group_no" value="${groupVO.group_no }">
										<input type="hidden" name="action" value="getOne_For_Update">
									</form>
								</c:when>
								
								<c:when test="${groupVO.getMem_no() == memVO.getMem_no() && groupVO.group_status==3}">
								</c:when>
								
								<c:when test="${groupVO.getMem_no() == memVO.getMem_no() && groupVO.group_status!=0}">
									<input id="disabled" value="揪團已成立" type="submit" disabled="disabled">										
								</c:when>
								

								<c:when test="${groupVO.group_status==1}">
									<input id="disabled" value="已結束報名" type="submit" disabled="disabled">
								</c:when>

								<c:when test="${group_joinVO.group_jo_status == 0}">
									<input id="WsBuyBtn" type="submit" value="取消報名">
								</c:when>
								<c:when test="${group_joinSvc.getOneGroup_join(groupVO.group_no, memVO.mem_no).group_jo_status==1&&groupVO.group_status==0}">
									<input id="WsBuyBtn" value="退出揪團" type="submit">
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${group_joinSvc.getOneGroup_join(groupVO.group_no, memVO.mem_no).group_jo_status==2}">
											<input id="disabled" value="無法報名" type="submit" disabled="disabled">
										</c:when>
										<c:otherwise>
											<input id="WsBuyBtn" value="報名揪團" type="submit">
										</c:otherwise>
									</c:choose>

								</c:otherwise>
							</c:choose>
						</div>

<!-- 						開團人選項 -->
<%-- 						<c:if test="${groupVO.mem_no==memVO.mem_no}"> --%>
<%-- 							<c:if test="${groupVO.group_status==0}"> --%>
<!-- 								<form METHOD="post" -->
<%-- 									action="<%=request.getContextPath()%>/group/group.do"> --%>
<!-- 									<input id="WsBuyBtn2" value="關閉揪團" type="submit"><input -->
<%-- 										type="hidden" name="group_no" value="${groupVO.group_no }"><input --%>
<%-- 										type="hidden" name="mem_no" value="${memVO.mem_no }"><input --%>
<!-- 										type="hidden" name="action" value="delete"> -->
<!-- 								</form> -->
<%-- 							</c:if> --%>
<%-- 							<c:if test="${groupVO.group_status==2}"> --%>
<!-- 								<form METHOD="post" -->
<%-- 									action="<%=request.getContextPath()%>/group/group.do"> --%>
<!-- 									<input id="WsBuyBtn2" value="開啟揪團" type="submit"><input -->
<%-- 										type="hidden" name="group_no" value="${groupVO.group_no }"><input --%>
<%-- 										type="hidden" name="mem_no" value="${memVO.mem_no }"><input --%>
<!-- 										type="hidden" name="action" value="open"> -->
<!-- 								</form> -->
<%-- 							</c:if> --%>
<%-- 						</c:if> --%>
					</div>
				</div>
			</div>

		</div>

		<!-- 揪團詳情 -->
		<div class="group_des"> 
		<div class="group_bar">
			<h5><span class="mallListTab">揪團詳情</span></h5>
		</div>
		<hr>
			<div id="des">${groupVO.group_des}</div>
		</div>

		<!-- 留言 -->
		<div class="group_message">
			<div class="editer_main">
				<span>留言</span>
			</div>
		</div>
<hr>
		<div id="allmessage">
			<c:forEach var="group_meVO" items="${list}">
				<div class="message-content">
					<img class="participant" src="<%=request.getContextPath()%>/ShowPic_V1?mem_no=${group_meVO.mem_no}">　
						<font id="onemessage"><font id="memname">${memSvc.getOneMem(group_meVO.mem_no).mem_name}</font>　${group_meVO.group_me_note}</font>
						<font id="metime"><fmt:formatDate value="${group_meVO.group_me_time}" type="both" dateStyle="short" timeStyle="short"/></font>
				</div>
			</c:forEach>

		</div>
<hr>
		<div class="add_message">
			<div class="add_message_top">
				<div class="add_message_title">
					<h3 class="question">我要提問</h3>
				</div>
					<div class="pb-3">
						<textarea id="message_info" name="group_me_note" rows="4" cols="70" required></textarea><br><br>
						<input id="submitbtn" class="reply_sent" type="submit" value="確認送出" data-status="1">
					</div>
			</div>
		</div>
	</div>


	<!--審核列表 -->
	<c:if test="${list_join.size()!=0}">
		<c:if test="${groupVO.getMem_no() == memVO.getMem_no() && groupVO.group_status==0}">
			<div id="check" class="btn-group">
				<button type="button" class="btn btn-secondary dropdown-toggle"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">揪團審核</button>
				<div class="dropdown-menu dropdown-menu-right" id="tab">
					<div class="title">申請者 通過 不通過</div>
					<c:forEach var="group_joinVO" items="${group_joinSvc.getOneGroup(groupVO.group_no)}">
						<c:if test="${group_joinVO.group_jo_status == 0}">
							<div id="test" class="test">
								<font class="and">${memSvc.getOneMem(group_joinVO.mem_no).mem_name}</font>
								<div class="and">
									<button type="button" id="ok" class="dropdown-item"
										name="${groupVO.group_no}"
										value="${memSvc.getOneMem(group_joinVO.mem_no).mem_no}">
										<i id="${groupVO.group_no}" class="fas fa-check-square"></i>
									</button>
								</div>
								<div class="and">
									<button type="button" id="no" class="dropdown-item"
										name="${groupVO.group_no}"
										value="${memSvc.getOneMem(group_joinVO.mem_no).mem_no}">
										<i id="${groupVO.group_no}" class="fas fa-times-circle"></i>
									</button>
								</div>
							</div>
						</c:if>
					</c:forEach>
				</div>
			</div>
		</c:if>
	</c:if>
	<footer>
		<img src="<%=request.getContextPath()%>/images/twdivers.png"
			class="logo_img" alt="tw_logo">

		<!-- 揪 行 裝 社 -->
		<ul class="nav justify-content-center" id="footer">
			<li class="nav-item"><a class="nav-link" href="#">潛點揪團</a></li>
			<li class="nav-item"><a class="nav-link" href="#">行程</a></li>
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
	<script src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.4.1.min.js"></script>
	<script src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
	<script src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/dive.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>


	<!-- 報名倒數計時 -->
	<script>
		var DifferenceHour = -1;
		var DifferenceMinute = -1;
		var DifferenceSecond = -1;
		var Tday = new Date("${groupVO.group_tour_stop_time}");
		var daysms = 24 * 60 * 60 * 1000;
		var hoursms = 60 * 60 * 1000;
		var Secondms = 60 * 1000;
		var microsecond = 1000;

		function clock() {
			var time = new Date();
			var hour = time.getHours();
			var minute = time.getMinutes();
			var second = time.getSeconds();
			var timevalue = "" + ((hour > 12) ? hour - 12 : hour);
			timevalue += ((minute < 10) ? ":0" : ":") + minute;
			timevalue += ((second < 10) ? ":0" : ":") + second;
			timevalue += ((hour > 12) ? " PM" : " AM");
			//  document.formnow.now.value = timevalue
			var convertHour = DifferenceHour;
			var convertMinute = DifferenceMinute;
			var convertSecond = DifferenceSecond;
			var Diffms = Tday.getTime() - time.getTime();
			DifferenceHour = Math.floor(Diffms / daysms);
			Diffms -= DifferenceHour * daysms;
			DifferenceMinute = Math.floor(Diffms / hoursms);
			Diffms -= DifferenceMinute * hoursms;
			DifferenceSecond = Math.floor(Diffms / Secondms);
			Diffms -= DifferenceSecond * Secondms;
			var dSecs = Math.floor(Diffms / microsecond);
			if (convertHour != DifferenceHour)
				document.formnow.dd.value = DifferenceHour;
			if (convertMinute != DifferenceMinute)
				document.formnow.hh.value = DifferenceMinute;
			if (convertSecond != DifferenceSecond)
				document.formnow.mm.value = DifferenceSecond;
			document.formnow.ss.value = dSecs;
			//  document.formnow.Tnow.value= DifferenceHour  DifferenceMinute + DifferenceSecond + dSecs
			setTimeout("clock()", 1000);
			if((Tday-time)/1000<0){				
				var highestTimeoutId = setTimeout(";");
				for (var i = 0 ; i < highestTimeoutId ; i++) {
				    clearTimeout(i);
				}	
				   var time = document.getElementById('time');
				   time.parentNode.removeChild(time);

				   document.getElementById("clock").innerHTML="報名時間已過";
				   document.getElementById("clock").style.background = 'red';	
				   document.getElementById("clock").style.fontSize = '15px';
			}
		}
	</script>
	<script>
	var a;
	var b;
	var c;
	var x;
	var y;
	var z;
	var d;
	var dd;
	var mem_no;//存放mem_no給取照片用
	var mem_no1;
	var maxnum;
	$("#tab").click(function (e) {
	    x = $(e.target).parent().parent("#test");
	    xx = $(e.target).parent().parent().parent("#test");
	    a = $(e.target).parent().find("#ok").val();
	    b = $(e.target).parent().find("#no").val();
	    y = $(e.target).parent("#ok").val();
	    z = $(e.target).parent("#no").val();
	    c = e.target;
	    d = c.name;
	    dd = c.id;
	    maxnum = $("#maxnum").val();
	    if(maxnum>0){
	    if (a != undefined) {
	        mem_no = $(e.target).parent().find("#ok").val();
	        $.ajax({
	            url: "<%=request.getContextPath()%>/group/group_join.do",
	            type: "POST",
	            data: {
	                mem_no: $(e.target).parent().find("#ok").val(),
	                group_no: d,
	                action: "check"
	            },
	            success: function (data) {
	                showNames(data); 
	                okemail(data);
	                
	            }
	        });
	    } else if (b != undefined) {
	        mem_no1 = $(e.target).parent().find("#no").val();
	        $.ajax({
	            url: "<%=request.getContextPath()%>/group/group_join.do",
	            type: "POST",
	            data: {
	                mem_no1: $(e.target).parent().find("#no").val(),
	                group_no: d,
	                action: "check"
	            },
	            success: function (data) {
	                showNames(data);
	                noemail(data);
	            }
	        });
	    }

	    else if (y != undefined) {
	        mem_no = $(e.target).parent().parent().find("#ok").val();
	        $.ajax({
	            url: "<%=request.getContextPath()%>/group/group_join.do",
	            type: "POST",
	            data: {
	                mem_no: $(e.target).parent().parent().find("#ok").val(),
	                group_no: dd,
	                action: "check"
	            },
	            success: function (data) {
	                showNames1(data);
	                okemail(data);
	            }
	        });
	    }

	    else if (z != undefined) {
	        mem_no1 = $(e.target).parent().parent().find("#no").val();
	        $.ajax({
	            url: "<%=request.getContextPath()%>/group/group_join.do",
	            type: "POST",
	            data: {
	                mem_no1: $(e.target).parent().parent().find("#no").val(),
	                group_no: dd,
	                action: "check"
	            },
	            success: function (data) {
	                showNames1(data);
	                noemail(data);
	            }
	        });
	    }
	    }
	});
	//a、b用方法
	function showNames(c) {
	    if (c == "ok") {
// 	        x.hide();
	        x.remove();
	    }
	    if($(".test").length==0){
	    	$('#check').remove();
	    }
	}
	//y、z用方法
	function showNames1(c) {
	    if (c == "ok") {
// 	        xx.hide();
	        xx.remove();
	    }  if($(".test").length==0){
	    	$('#check').remove();
	    }
	}
	
	function okemail(data){
        $.ajax({
            url: "<%=request.getContextPath()%>/group/group_join.do",
            type: "POST",
            data: {
                mem_no: mem_no,
                group_no: "${groupVO.group_no}",
                action: "okemail"
            }
        });
	}

	function noemail(data){
        $.ajax({
            url: "<%=request.getContextPath()%>/group/group_join.do",
            type: "POST",
            data: {
                mem_no1: mem_no1,
                group_no: "${groupVO.group_no}",
                action: "noemail"
            }
        });
	}
	//審核揪團時下拉視窗不會跳掉
	$("#tab").on("click", function (e) {
	    e.stopPropagation();
	})


//審核通過後修改尚可報名人數、新增參團者照片
	var ok1;
	var ok2;
	var no1; 
	var no2;
	$("#tab").click(function (e) { 
		maxnum = $("#maxnum").val(); 
	    ok1 = $(e.target).parent().find("#ok").val();//審核ok外層Button
	    ok2 = $(e.target).parent().parent().find("#ok").val();//檢舉審核ok內層i
	    no1 = $(e.target).parent().find("#no").val();//審核no外層Button
	    no2 = $(e.target).parent().parent().find("#no").val();//檢舉審核no內層i

	    var path = "<%=request.getContextPath()%>/ShowPic_V1"
	    var method = "?mem_no=";
	    var src = path + method + mem_no;
	    var ul = document.getElementById("allpicture");
	    // 　　	    新增 img
	    var img = document.createElement("img");
	    // 　　　設定 img 屬性，如 id
	    img.setAttribute("class", "participant");
	    img.setAttribute("id", "${memVO.mem_no}");
	    //  	　設定 img 圖片地址 
	    img.src = src;
	    if (no1 == undefined && no2 == undefined) {
	        $("#maxnum").val($("#maxnum").val() - 1);
	        ul.appendChild(img);
	    } else if (no1 == undefined && ok2 != undefined) {
	        $("#maxnum").val($("#maxnum").val() - 1);
	        ul.appendChild(img);
	    }
	    if(maxnum-1 == 0 && ok2 != undefined && no1 == undefined){
	    	 Swal.fire('揪團人數已達上限！', '可以開心出團囉～～～～', 'success');
				$('#check').remove();
				$('#change').empty();
				$('#change').append('<input id="disabled" value="揪團已成立" type="submit" disabled="disabled">')
	    } 
	
// 		else{
// 		Swal.fire({
// 			  icon: 'error',
// 			  title: '人數已達上限...',
// 			  text: '已無法再加入參團者!'  
// 			});
// 			$('#check').remove();
// 			$('#change').empty();
// 			$('#change').append('<input id="disabled" value="揪團已成立" type="submit" disabled="disabled">')
// 	}
		}); 


//	 	檢舉
	$("#re_button").click(function () {
	var group_message = $('#message-text').val();
	var mem_re = "${memVO.mem_no}123";
	if(mem_re.length>7){
if(group_message!= ""){	
	$.ajax({
	        url: "<%=request.getContextPath()%>/group/group_report.do",
	        type: "POST",
	        data: {
	            group_no: "${groupVO.group_no}",
	            mem_no: "${memVO.mem_no}",
	            group_re_note: $("#message-text").val(),
	            action: "insert"
	        },
	        success: function (data) {
	        	Swal.fire('Good!Good!Good!', '檢舉成功！', 'success')
	        }
	    });
}else{
	Swal.fire({
		  icon: 'error',
		  title: '請輸入檢舉內容！',
		});
}
	}else{
		let timerInterval
		Swal.fire({
		  title: '請先登入!',
		  html: '<b></b> 秒後為您跳轉登入畫面.',
		  timer: 3000,
		  timerProgressBar: true,
		  onBeforeOpen: () => {
		    Swal.showLoading()
		    timerInterval = setInterval(() => {
		      Swal.getContent().querySelector('b')
		        .textContent = Math.ceil(Swal.getTimerLeft()/ 1000)
		    }, 100)
		  },
		  onClose: () => {
		    clearInterval(timerInterval)
		  }
		}).then((result) => {
		  if (
		    /* Read more about handling dismissals below */
		    result.dismiss === Swal.DismissReason.timer
		  ) {
		    console.log('I was closed by the timer') // eslint-disable-line
	document.location.href="<%=request.getContextPath()%>/front-end/member/login.jsp";
		  }
		})
	}
	});



	var mem;
	var a;
	a = $("#WsBuyBtn").val();
	$("#WsBuyBtn").click(function (e) {
	    // 		退出揪團、取消報名
	    if (a === "取消報名" || a === "退出揪團") {
	        $.ajax({
	            url: "<%=request.getContextPath()%>/group/group_join.do",
	            type: "POST",
	            data: {
	                mem_no: "${memVO.mem_no}",
	                group_no: "${groupVO.group_no}",
	                action: "delete"
	            },
	            success: function (data) {
	                cancel(data);

	            }
	        });
	    } else if (a === "報名揪團") {
	    	mem = "${memVO.mem_no}123";
			if(mem.length>7){
	    	$.ajax({
	            url: "<%=request.getContextPath()%>/group/group_join.do",
	            type: "POST",
	            data: {
	                mem_no: "${memVO.mem_no}",
	                group_no: "${groupVO.group_no}",
	                action: "insert"
	            },
	            success: function (data) {
	                add(data);

	            }
	        });
	    }else{
			mem = "${memVO.mem_no}123";
			if(mem.length<7){
				let timerInterval
				Swal.fire({
				  title: '請先登入!',
				  html: '<b></b> 秒後為您跳轉登入畫面.',
				  timer: 3000,
				  timerProgressBar: true,
				  onBeforeOpen: () => {
				    Swal.showLoading()
				    timerInterval = setInterval(() => {
				      Swal.getContent().querySelector('b')
				        .textContent = Math.ceil(Swal.getTimerLeft()/ 1000)
				    }, 100)
				  },
				  onClose: () => {
				    clearInterval(timerInterval)
				  }
				}).then((result) => {
				  if (
				    /* Read more about handling dismissals below */
				    result.dismiss === Swal.DismissReason.timer
				  ) {
				    console.log('I was closed by the timer') // eslint-disable-line
			document.location.href="<%=request.getContextPath()%>/front-end/member/login.jsp";
				  }
				})
			}
	    }
			}
				
			
	    //報名揪團

	});
	//取消報名、退出揪團  
	function cancel() {
	    if ($("#WsBuyBtn").val() == "退出揪團") {
	        // 				$("#maxnum").val(parseInt($("#maxnum").val()) + 1);
	        Swal.fire('Good!Good!Good!', '退出成功!!', 'success');
	        // 				$("#${memVO.mem_no}").remove();
	    } else if ($("#WsBuyBtn").val() == "取消報名") {
	        Swal.fire('Good!Good!Good!', '取消成功!!', 'success');
	    }
	    $("#WsBuyBtn").val("報名揪團");
	    a = $("#WsBuyBtn").val();
	};

	//報名揪團方法
	function add() {
	    Swal.fire('Good!Good!Good!', '報名成功!!', 'success');
	    $("#WsBuyBtn").val("取消報名");
	    a = $("#WsBuyBtn").val();
	};
	</script>
	
	<script>
	$("#submitbtn").click(function(){
		if($("#message_info").val().length == 0){
			Swal.fire({
				  icon: 'warning',
				  title: 'Oops...',
				  text: '請輸入留言!',
				})
			return; 
 		}
	    	var mem_me = "${memVO.mem_no}123";
			if(mem_me.length>7){
		$.ajax({
			 type: "GET",
			 url: "<%=request.getContextPath()%>/group/group_me.do",
			 data: {
				 action:"insert",
				 mem_no:'${memVO.mem_no}',
				 group_no:'${groupVO.group_no}',
				 group_me_note:$("#message_info").val()				  
			 },
			 dataType: "json",
			 success: function (data){
				 $("#message_info").val("");
				 var str = '<div class="message-content">'
				         + "<img class='participant' src='<%=request.getContextPath()%>/ShowPic_V1?mem_no=${memVO.mem_no}'>"
				         +"　 "+ "<font id='onemessage'><font id='memname'>"+data.mem_name+"　"+"</font>"+data.group_me_note+"</font>"	 
				         + "<font id='metime'>"+data.group_me_time+"</font>"
				         + "</div>";
				 $('#allmessage').append(str);				 
			 }
			 
		});	
			}else{
				let timerInterval
				Swal.fire({
				  title: '請先登入!',
				  html: '<b></b> 秒後為您跳轉登入畫面.',
				  timer: 3000,
				  timerProgressBar: true,
				  onBeforeOpen: () => {
				    Swal.showLoading()
				    timerInterval = setInterval(() => {
				      Swal.getContent().querySelector('b')
				        .textContent = Math.ceil(Swal.getTimerLeft()/ 1000)
				    }, 100)
				  },
				  onClose: () => {
				    clearInterval(timerInterval)
				  }
				}).then((result) => {
				  if (
				    /* Read more about handling dismissals below */
				    result.dismiss === Swal.DismissReason.timer
				  ) {
				    console.log('I was closed by the timer') // eslint-disable-line
			document.location.href="<%=request.getContextPath()%>/front-end/member/login.jsp";
				  }
				})
			}
	});
	</script>

</body>

</html>