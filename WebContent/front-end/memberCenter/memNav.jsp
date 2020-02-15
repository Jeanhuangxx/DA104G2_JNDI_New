<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link href="<%=request.getContextPath()%>/css/admin.css"
	rel="stylesheet">

</head>

<body id="page-top">

	<!-- Sidebar -->
	<ul class="navbar-nav sidebar sidebar-light accordion"
		id="accordionSidebar">
		<a
			class="sidebar-brand d-flex align-items-center justify-content-center"
			href="<%=request.getContextPath()%>/index.jsp"> <!-- 潛水店名 -->
			<div class="sidebar-brand-text mx-3 sd_name">會員中心</div>
		</a>
		<li class="nav-item"><a class="nav-link"
			href="<%=request.getContextPath()%>/front-end/memberCenter/memDetail.jsp">
				<i class="fas fa-fw fa-user-friends"></i> <span class="trav_mang">會員資料</span>
		</a></li>
		<li class="nav-item"><a class="nav-link collapsed"
			href="<%=request.getContextPath()%>/group/group.do?action=mygroup&mem_no=${memVO.mem_no}"
			data-target="#collapseBootstrap3" aria-expanded="true"
			aria-controls="collapseBootstrap"> <i
				class="fas fa-fw fa-handshake"></i> <span class="trav_mang">我的揪團</span>
		</a></li>
		<li class="nav-item"><a class="nav-link collapsed"
			href="<%=request.getContextPath()%>/front-end/memberCenter/member_myTrip.jsp"
			data-target="#collapseBootstrap4" aria-expanded="true"
			aria-controls="collapseBootstrap"> <i
				class="fas fa-fw fa-list-alt"></i> <span class="trav_mang">我的行程</span>
		</a></li>
		<li class="nav-item"><a class="nav-link collapsed"
			href="<%=request.getContextPath()%>/front-end/memberCenter/member_myOrder.jsp"
			data-target="#collapseBootstrap5" aria-expanded="true"
			aria-controls="collapseBootstrap"> <i
				class="fas fa-fw fa-toolbox"></i> <span class="trav_mang">我的裝備</span>
		</a></li>
		<li class="nav-item"><a class="nav-link collapsed"
			href="<%=request.getContextPath()%>/front-end/memberCenter/member_myAlbum.jsp"
			data-target="#collapseBootstrap6" aria-expanded="true"
			aria-controls="collapseBootstrap6"> <i class="fas fa-images"></i>
				<span class="trav_mang">我的相簿</span>
		</a></li>
		<li class="nav-item"><a class="nav-link collapsed"
			href="<%=request.getContextPath()%>/front-end/memberCenter/member_travel_like.jsp"
			data-target="#collapseBootstrap2" aria-expanded="true"
			aria-controls="collapseBootstrap2"> <i class="fas fa-thumbs-up"></i>
				<span class="trav_mang">我的追蹤</span>
		</a></li>
		<hr class="sidebar-divider">

	</ul>
	<!-- Sidebar -->
</body>

</html>