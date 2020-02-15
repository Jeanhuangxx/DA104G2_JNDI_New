<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.equip_odlist.model.*"%>
<%@ page import="com.equip_order.model.*"%>
<%@ page import="java.util.*"%>

<%
	List<EquipOdlistVO> list = (ArrayList) request.getAttribute("list");
	EquipOrderVO equiporderVO = (EquipOrderVO)request.getAttribute("equiporderVO");
%>

<!DOCTYPE html>
<html>
<head>
<link href="<%= request.getContextPath()%>/images/twdivers.png" rel="icon">
<title>會員訂單明細</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

<style>
	table, th, td {
	    border: 1px solid #CCCCFF;
	}
	th, td {
	    padding: 5px;
	    text-align: center;
	}
	body{
		font-family: "Microsoft JhengHei UI";
		font-size: 20px;
		font-weight: bold;
	}
	.table {
		width: 1500px;
		margin:5px auto;
		background-color: white;
	}
	.table_odlist{
		width: 1000px;
		background-color: white;
	}
	.table_odlist tbody tr th{
		line-height: 40px;
	}
	.table_odlist tbody tr td{
		line-height: 40px;
	}
	
	#title_odlist{
		text-align: center;
		font-size: 28px;
		color:darkblue;
		margin-top:40px;
		margin-bottom:30px;
	}
	#price{
		font-size: 25px;
		text-align:center;
		margin-top:30px;
		margin-bottom:50px;
	}
</style>
</head>
<body>	

<div id="title_odlist">
	${equiporderVO.equip_order_no} 訂單明細
</div>

<div class="container-fluid">
<table class="table table_odlist">
	<thead class="thead-dark">
		<tr>
			<th scope="col">裝備名稱</th>
			<th scope="col" style="width:180px">購買數量</th>
			<th scope="col" style="width:180px">單價</th>
			<th scope="col" style="width:180px">小計</th>
		</tr>
	</thead>
	<tbody>
	<c:forEach var="equipodlistVO" items="${list}">
		<tr>
			<td>${equipodlistVO.equip_name}</td>
			<td>${equipodlistVO.buy_amt}</td>
			<td>${equipodlistVO.equip_price}</td>
			<td>${equipodlistVO.buy_amt * equipodlistVO.equip_price}</td>
		</tr>
	</c:forEach>
	</tbody>
</table>

<div id="price">
	總金額<font color="red">$<%=equiporderVO.getEquip_order_price()%></font>
</div>

</div>



</body>
</html>