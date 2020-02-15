<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>

<%
	request.getSession().setAttribute("URI", request.getRequestURI());
	MemVO memVO = (MemVO) session.getAttribute("memVO");
%>
<!DOCTYPE html>
<html>
<head>
<style>
		body{
			font-family: "Microsoft JhengHei UI";
		}
		#floating-panel {
			border-radius: 10px;
			position: fixed;
			top: 12%;
			left: 38%;
			z-index: 30;
			background-color: #fdfeff9e;
			padding: 14px;
			text-align: center;
			line-height: 30px;
			width: 500px;
		}
		#map {
			height: 89.8%;
			width: 100%;
			float: left;
			background-color:gray;
			margin-top: 5%;
		}
		
		#nav{
			background-color: black;
		}
		
		#search{
			display: inline-block;
		}
		
		#diveplace{
			width: auto;
			display: inline;
		}
</style>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/vendors/bootstrap/css/bootstrap.min.css">
<!-- dive.css -->
<link rel=stylesheet type="text/css" href="<%=request.getContextPath()%>/dive.css">
<link href="<%= request.getContextPath()%>/images/twdivers.png" rel="icon">
<title>潛進台灣Taiwan Divers</title>
</head>

<body>
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
										width='50px' height='50px'>
								</span>
								<span>${memVO.mem_name}</span>
							</a>
						</c:when>
						<c:otherwise>
							<a class="user_name"
								href="<%=request.getContextPath()%>/front-end/memberCenter/memDetail.jsp"><span>
									<img alt=""
									src="<%=request.getContextPath()%>/images/mem_pic.png"
									width='40px' height='40px'>
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
							width='40px' height='40px'></span>
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




<div class="container-fluid h-100 pl-0 pr-0">
	<div id="floating-panel">
	<span style="font-size:20px;">潛點搜尋</span>
		<select id="diveplace"  class="diveplace form-control" data-value="墾丁潛場" name="weather_area_no">
		  <option value="">選擇區域</option>
			<option data-index="0" value="WA00001">墾丁</option>
			<option data-index="1" value="WA00002">蘭嶼</option>
			<option data-index="2" value="WA00003">綠島</option>
			<option data-index="3" value="WA00004">東北角</option>
			<option data-index="4" value="WA00005">北海岸</option>
			<option data-index="5" value="WA00006">澎湖</option>
			<option data-index="6" value="WA00007">花東</option>
		</select>

<!-- 		潛點搜尋:<input class="searchPlace" value='' id="search" name='dive_no' type='text' placeholder='請輸入潛點名稱'> -->
<!-- 		<button type='button' onclick="searchDive();">搜尋</button><br> -->
		
		  <input class="searchPlace" value='' id="search" name="dive_no" type="text" placeholder="請輸入潛點名稱">
		  <button type="button" onclick="searchDive();" >搜尋</button>
		
		
		<br>
		<a href='<%=request.getContextPath()%>/front-end/dive/addDiveMap.jsp'>我想推薦新潛點</a>
		
	</div>
	<div id="map"></div>
</div>
	<script
		src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.4.1.min.js"></script>
	<script>
	var JadeMountainNationalPark = {
			lat : 23.4964744,
			lng : 121.0323328
		};
      var map;
      var search;
      var dive_id;
      var markers = [];
      var searchResult;
      var iconBase = 'https://maps.google.com/mapfiles/kml/shapes/';
      
      //新增潛點
      
      <% 
      	int isInserted = 0;
      	if(request.getAttribute("isInserted")!=null){
     	  	isInserted = (int)request.getAttribute("isInserted");      		
      	}
      %>
   if(<%=isInserted%>==1){
 	  alert("感謝您的推薦，等待管理員審核完畢將顯示在地圖上");
   }
      function initMap() {
    	// 初始化地圖
        map = new google.maps.Map(document.getElementById('map'), {
          center: JadeMountainNationalPark,
          zoom: 7.5,
          mapTypeId: 'satellite'
        });

        // 初始化潛點
        getJSON('<%=request.getContextPath()%>/dive/DiveServlet.do?action=test', callback);    
      }
      
      //單一潛點搜尋
      function searchDive(){
    	  search=$(".searchPlace").val();
    	  if(search != ""){
    		  deleteMarkers();
    		  getJSON('<%=request.getContextPath()%>/dive/DiveServlet.do?action=search&dive_name='+search, callback);
    	}
      }
      //區域搜尋
      $('.diveplace').change(function() {
    	  var multipleValues = $( ".diveplace" ).val() || [];

    	   	  deleteMarkers();
        	  getJSON('<%=request.getContextPath()%>/dive/DiveServlet.do?action=searchByArea&weather_area_no='+multipleValues, callback);
		});

         
      //呼叫AJAX，在地圖上插入潛點
      function getJSON(url, callback) {
        var xhr = new XMLHttpRequest();
        xhr.open('GET', url, true);
        xhr.responseType = 'json';
        xhr.onload = function() {
          var status = xhr.status;
          if (status === 200) {
            callback(null, xhr.response);

          } else {
            callback(status, xhr.response);
          }
        };
        xhr.send();
      }

      //在地圖上插入潛點
      function callback(err, data) {  
    	  
    	  if (err != null) {
          alert('Something went wrong: ' + err);
        } else {
console.log("I got the data");
        //加入潛點marker
        for(var i = 0; i < data.length; i++){
console.log("data:"+ data);
        	dive_id = data[i].dive_no;
console.log("dive_no:"+dive_id);
console.log("dive_lang:"+data[i].dive_lang);

        	var latLng = new google.maps.LatLng(data[i].dive_lang, data[i].dive_lat);

        	addMarker(latLng, data[i]);
        	
        	
        	
      	
        }
        
          }
      }
	var z=false;     
	var host = window.location.host;
	var path = window.location.pathname;
	var webCtx = path.substring(0, path.indexOf('/', 1));
      // Adds a marker to the map and push to the array.
      function addMarker(latLng, result) {
        var marker = new google.maps.Marker({
          position: latLng,
          map: map,
          animation: google.maps.Animation.DROP
        });
         markers.push(marker);

        //Show info Window
        var infowindow = new google.maps.InfoWindow({
            content: "<h2>"+result.dive_name+"</h2><div><p><b>描述: </b>"+result.dive_des+"</p><p><b> </b><img src='<%=request.getContextPath()%>/ShowPic_V1?dive_no="
								+ result.dive_no
								+ "' width=350px height=200px>"
								+ "</p><p><b>位置: </b>{"
								+ result.dive_lat
								+ ","
								+ result.dive_lang
								+ "}</p>"
								+ "<p><b><a href=http:\\\\"+host+webCtx+"/group/group.do?action=getOne_For_Dive&dive_no="+result.dive_no+">揪團潛水去</a> </b></p></div>"
					});
			marker.addListener('click', function() {
				z=!z;
				if(z){
					infowindow.open(map, marker);
				}else{
					infowindow.close(map, marker);
				}
			});
		}

		// Deletes all markers in the array by removing references to them.
		function deleteMarkers() {
			setMapOnAll(null);
			markers = [];
		}

		// Sets the map on all markers in the array.
		function setMapOnAll(map) {
			for (var i = 0; i < markers.length; i++) {
				markers[i].setMap(map);
			}
		}

		// window.onload = initMap;  //測試用
	</script>
	<script async defer
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAWhufVJmMRTbPgK6H8pfnk5sDP3kf8S8E&callback=initMap"></script>




	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<!-- dive.js -->
	<script
		src="<%=request.getContextPath()%>/vendors/jquery/jquery-3.4.1.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/popper/popper.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/vendors/bootstrap/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/dive.js"></script>
	<script type="text/javascript">

	</script>
</body>
</html>