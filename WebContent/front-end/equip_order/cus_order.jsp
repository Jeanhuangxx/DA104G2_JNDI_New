<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<% 
	session.removeAttribute("shoppingcart");
	session.removeAttribute("amount");

%>
<!DOCTYPE html>
<html>
<head>
<title>訂單成立</title>

<style>
	body{
		font-family: "Microsoft JhengHei UI";
		font-size: 30px;
		font-weight: bold;
		background-color: #f9f9f9;
	}
	.orderdeal{
		text-align:center;
		margin-top:300px;
	}
	.home{
		text-align:center;
	}
		
		
</style>
</head>
<body>

	<div class="orderdeal">您的訂單已成立！</div>
	<div class="home">
		<a href="<%=request.getContextPath()%>/index.jsp">
		<img src="<%=request.getContextPath()%>/images/home.png" width="100" height="100"></a>
	</div>




</body>
</html>