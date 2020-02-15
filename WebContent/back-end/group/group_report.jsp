<%@ page import="com.group_report.model.*"%>
<%@ page import="com.group.model.*"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>

<%
	List<Group_reportVO> list = (List<Group_reportVO>)request.getAttribute("list");
	if(list==null){
		Group_reportService group_reSvc = new Group_reportService();
		list = group_reSvc.getAll();
		pageContext.setAttribute("list", list);
	}
%>
<jsp:useBean id="groupSvc" scope="page" class="com.group.model.GroupService" />
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
#switch{
	float:left;
}
#oncheck{
	background-color: #4CAF50;
    border: none;
    color: white;
    padding: 15px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
}
#checked{
	background-color: #4CAF50;
    border: none;
    color: white;
    padding: 15px 32px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 16px;
    margin: 4px 2px;
    cursor: pointer;
}
</style>
</head>
<!--------------------------以上--------------------------------->
<body>
		<jsp:include page="/back-end/manage/admNav.jsp" />
<!-----------------------------------------此區塊放自己的資料----------------------------------------------------->

				
        	<div class="d-sm-flex align-items-center justify-content-between mb-4">
<!-- 				<span class="tour_report">揪團檢舉</span>  -->
				
				<span>
					<form id="switch" METHOD="post" action="<%=request.getContextPath()%>/group/group_report.do"><input type="hidden" name="group_re_status" value="0"><button id="oncheck" name="action" value="oncheck">未審核檢舉</button></form>
					<form id="switch" METHOD="post" action="<%=request.getContextPath()%>/group/group_report.do"><input type="hidden" name="group_re_status" value="1"><button id="checked" name="action" value="checked">已審核檢舉</button></form>
				</span>
			</div>
			<%@ include file="groupAllPage1.file"%>
			<table class="table text-center" id="ReportTable">
			  <thead class="thead-dark">
			    <tr>
			      <th scope="col">揪團編號</th>
			      <th scope="col">揪團名稱</th>
			      <th scope="col">檢舉人</th>
			      <th scope="col">檢舉時間</th>
			      <th scope="col">檢舉事由</th>
			      <th scope="col">審核結果</th>
			      <th scope="col">審核狀態</th>
			    </tr>
			  </thead>
			  <tbody>
			  <c:forEach var="group_reVO" items="${list}" varStatus="count" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			    
			    <tr>
			      <th scope="row">${group_reVO.group_no}</th> 
			      <td><a href="<%=request.getContextPath()%>/group/group.do?action=getOne_For_Display&group_no=${group_reVO.group_no}">${groupSvc.getOneGroup(group_reVO.group_no).group_name}</a></td>
			      <td>${group_reVO.mem_no}</td>
			      <td><fmt:formatDate value="${group_reVO.group_re_time}" type="both" dateStyle="short" timeStyle="short"/></td>
			      <td class="breakword">${group_reVO.group_re_note}</td>
			      <td>
			      <c:choose>
			      		<c:when test="${group_reVO.group_re_status==0 }"><span id="status${count.index}">未審核</span></c:when>
			      		<c:when test="${group_reVO.group_re_status==1 }"><span id="status${count.index}">通過</span></c:when>
			      		<c:when test="${group_reVO.group_re_status==2 }"><span id="status${count.index}">不通過</span></c:when> 
			      </c:choose>
			      </td>
			      <td id="check-status${count.index}">
			      		<c:if test="${group_reVO.group_re_status == 0}">
			      		<button id="check${count.index}" type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter${count.index}">進行審核</button>
						</c:if>
						<c:if test="${group_reVO.group_re_status != 0}">已審核</c:if>
							<!-- Modal -->
							<div class="modal fade" id="exampleModalCenter${count.index}" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
							  <div class="modal-dialog modal-dialog-centered" role="document">
							    <div class="modal-content">
							      <div class="modal-header">
							        <h5 class="modal-title" id="exampleModalCenterTitle">檢舉內容</h5>
							        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
							          <span aria-hidden="true">&times;</span>
							        </button>
							      </div>
							      <div class="modal-body"> 
							        <p>揪團編號: ${group_reVO.group_no}</p>
										<p>揪團名稱: ${groupSvc.getOneGroup(group_reVO.group_no).group_name}</p>										
										<p>檢舉人編號: ${group_reVO.mem_no}</p>
										<p>檢舉時間: <fmt:formatDate value="${group_reVO.group_re_time}" type="both" dateStyle="short" timeStyle="short"/></p>
										<p>檢舉事由: ${group_reVO.group_re_note}</p>
							      </div>
							      <div class="modal-footer">
							        <form METHOD="post" action="<%=request.getContextPath()%>/group/group_report.do">
							  		     <input type="hidden" name="group_re_no" value="${group_reVO.group_re_no}">
							  		     <input type="hidden" name="group_re_status" value="1">
							  		     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			    						 <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			    						 <input type="hidden" name="action" value="check">               <!--送出當前是第幾頁給Controller-->
							  		     <button type="submit" class="btn btn-success">通過</button>
									 </form>
							      <form METHOD="post" action="<%=request.getContextPath()%>/group/group_report.do">
							  		     <input type="hidden" name="group_re_no" value="${group_reVO.group_re_no}">
							  		     <input type="hidden" name="group_re_status" value="2">
							  		     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			    						 <input type="hidden" name="whichPage"	value="<%=whichPage%>">               <!--送出當前是第幾頁給Controller-->
			    						 <input type="hidden" name="action" value="check">               <!--送出當前是第幾頁給Controller-->
							        	 <button class="btn btn-danger">不通過</button>
							        </form>
							      </div>
							    </div>
							  </div>
							</div>	
				 </td>
			    </tr>
			           
			    </c:forEach>
			  </tbody>
			</table>
			  <div><%@ include file="groupAllPage2.file"%></div>

				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
<!----------------------------------------------以上-------------------------------------------------------------->
				</div>
			</div>
		</div>

		<footer class="text-center text-secondary">
			&copy; DA104G2 GO ! <span id="year"></span>
		</footer>
		<!-- Optional JavaScript -->
		<!-- jQuery first, then Popper.js, then Bootstrap JS -->
		<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
		<script
			src="<%=request.getContextPath()%>/back-end/manage/js/poper.js"></script>
		<script
			src="<%=request.getContextPath()%>/back-end/manage/js/bootstrap.min.js"></script>
		<script
			src="<%=request.getContextPath()%>/back-end/manage/js/manager.js"></script>
	</div>

</body>
</html>