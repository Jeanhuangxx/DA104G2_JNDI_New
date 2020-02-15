<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.equipment.model.*"%>

<%
	EquipmentVO equipmentVO = (EquipmentVO) request.getAttribute("equipmentVO"); 
	Integer equip_status = (Integer) request.getAttribute("equip_status");
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




	table, th, td {
    	border: 0px solid #CCCCFF;
	}
	body{
		font-family: "Microsoft JhengHei UI";
		font-size: 20px;
		font-weight: bold;
	}
	.table{
		margin:20px auto 100px;
		width:650px;
	}
	.table th{
		width:220px;
	}
	.table td{
		width:400px;
	}
	.errPosition{
		position:relative;
		left:30%;
	}
	#title{
		text-align: center;
		font-size: 35px;
		color:darkblue;
	}
	#homeImg{
		margin-top:10px;
		margin-left:10px;	
	}
	.left{
		text-align: center;
	}
	.right {
		text-align: left;
	}
	
</style>
</head>
<!--------------------------以上--------------------------------->
<body>
		<jsp:include page="/back-end/manage/admNav.jsp" />
<!-----------------------------------------此區塊放自己的資料----------------------------------------------------->

				
<div id="title">裝備詳情</div>


<%-- 錯誤表列 --%>
<div class="errPosition">
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
</div>


<FORM method="post" action="<%=request.getContextPath()%>/equip/equipment.do" enctype="multipart/form-data">

<div class="container-fluid">
<table class="table">
	<thead class="thead-dark">
	<tr>
		<th scope="col"></th>
		<th scope="col" style="padding-right:200px">資料詳情</th>
	</tr>
	</thead>
	<tbody>
	<tr>
		<th scope="row" class="left">裝備編號</th>
		<td class="right"><%=equipmentVO.getEquip_no()%></td>     
	</tr>
	<tr>
		<th scope="row" class="left">裝備名稱</th>
		<td class="right"><%=equipmentVO.getEquip_name()%></td>     
	</tr>
	<tr>
		<th scope="row" class="left">裝備描述</th>
		<td class="right"><%=equipmentVO.getEquip_des()%></td>    
	</tr>
	<tr>
		<th scope="row" class="left">裝備預覽圖1</th>
		<td class="right"><img src="<%=request.getContextPath()%>/ShowPic_V1?equip_no=${equipmentVO.equip_no}&equip_img=1" width="110px"></td>    
	</tr>
	<tr>
		<th scope="row" class="left">裝備預覽圖2</th>
		<td class="right"><img src="<%=request.getContextPath()%>/ShowPic_V1?equip_no=${equipmentVO.equip_no}&equip_img=2" width="110px"></td>    
	</tr>
	<tr>
		<th scope="row" class="left">裝備預覽圖3</th>
		<td class="right"><img src="<%=request.getContextPath()%>/ShowPic_V1?equip_no=${equipmentVO.equip_no}&equip_img=3" width="110px"></td>    
	</tr>
	<tr>
		<th scope="row" class="left">狀態</th>
		<td class="right"><%=(equipmentVO.getEquip_status() == 1) ? "上架" : "下架"%></td>     
	</tr>
	<tr>
		<th scope="row" class="left">價格</th>
		<td class="right">$<%=equipmentVO.getEquip_price()%></td>    
	</tr>
	<tr>
		<th scope="row" class="left">更新日期</th>
		<td class="right"><%=equipmentVO.getEquip_update()%></td>    
	</tr>
	<tr>
		<th scope="row"></th>
		<td style="padding-left:320px"><button type="submit" class="btn btn-success">修改</button></td>    
	</tr>
	</tbody>

</table>
</div>

<input type="hidden" name="action" value="getOne_For_Update">
<input type="hidden" name="equip_no" value="<%=equipmentVO.getEquip_no()%>">
<input type="hidden" name="equip_status" value="${equip_status}">
<input type="hidden" name="requestURL" value="<%=request.getAttribute("requestURL")%>">
<input type="hidden" name="whichPage" value="<%=request.getAttribute("whichPage")%>">
</FORM>				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
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