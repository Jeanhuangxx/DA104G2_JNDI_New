<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.dive.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  DiveVO diveVO = (DiveVO) request.getAttribute("diveVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<link href="<%= request.getContextPath()%>/images/twdivers.png" rel="icon">
<title>潛點訊息</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>員工資料 - ListOneEmp.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>名稱</th>
		<th>描述</th>
		<th>圖片</th>
		<th>緯度</th>
		<th>經度</th>
	</tr>
	<tr>
		<td><%=diveVO.getDive_name()%></td>
		<td><%=diveVO.getDive_des()%></td>
		<td><%=diveVO.getDive_img()%></td>
		<td><%=diveVO.getDive_lat()%></td>
		<td><%=diveVO.getDive_lang()%></td>
		<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dive/DiveServlet.do" style="margin-bottom: 0px;">
			     <input type="submit" value="核可">
			     <input type="hidden" name="dive_no"  value="${diveVO.dive_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
		</td>
		<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/dive/DiveServlet.do" style="margin-bottom: 0px;">
			     <input type="submit" value="駁回">
			     <input type="hidden" name="dive_no"  value="${diveVO.dive_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
		</td>
	</tr>
</table>

</body>
</html>