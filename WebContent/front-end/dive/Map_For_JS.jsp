<!DOCTYPE html>

<html>
<head>
<script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
<link href="<%= request.getContextPath()%>/images/twdivers.png" rel="icon">
<title>Simple Map</title>
<meta name="viewport" content="initial-scale=1.0">
<meta charset="utf-8">
<style>
/* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
#map {
	height: 100%;
}
/* Optional: Makes the sample page fill the window. */
html, body {
	height: 100%;
	margin: 0;
	padding: 0;
}
</style>
</head>
<body>
	<div id="map"></div>
	

	<script>
		var map;

		function initMap() {
			// The location of taiwan
			var JadeMountainNationalPark = {
				lat : 23.4964744,
				lng : 121.0323328
			};
			// The map, centered at 玉山國家公園
			var map = new google.maps.Map(document.getElementById('map'), {
				zoom : 8,
				center : JadeMountainNationalPark
			});
			// The marker, positioned at 玉山國家公園
			var marker = new google.maps.Marker({
				position : JadeMountainNationalPark,
				map : map
			});
			// When the user clicks the marker, an info window opens.
			var contentString = '<div id="content">'
					+ '<div id="siteNotice">'
					+ '</div>'
					+ '<h1 id="firstHeading" class="firstHeading">玉山國家公園</h1>'
					+ '<div id="bodyContent">'
					+ '<p><b>玉山國家公園</b>, also referred to as <b>Ayers Rock</b>, is a large '
					+ 'sandstone rock formation in the southern part of the '
					+ 'Northern Territory, central Australia. It lies 335&#160;km (208&#160;mi) '
					+ 'south west of the nearest large town, Alice Springs; 450&#160;km '
					+ '(280&#160;mi) by road. Kata Tjuta and Uluru are the two major '
					+ 'features of the Uluru - Kata Tjuta National Park. Uluru is '
					+ 'sacred to the Pitjantjatjara and Yankunytjatjara, the '
					+ 'Aboriginal people of the area. It has many springs, waterholes, '
					+ 'rock caves and ancient paintings. Uluru is listed as a World '
					+ 'Heritage Site.</p>'
					+ '<p>相關揪團:  <a href="https://en.wikipedia.org/w/index.php?title=Uluru&oldid=297882194">'
					+ 'https://en.wikipedia.org/w/index.php?title=Uluru</a> '
					+ '(last visited June 22, 2009).</p>'
					+ '<p>相關潛水行程: <a href="https://en.wikipedia.org/w/index.php?title=Uluru&oldid=297882194">'
					+ 'https://en.wikipedia.org/w/index.php?title=Uluru</a> '
					+ '(last visited June 22, 2009).</p>' + '</div>' + '</div>';
			var infowindow = new google.maps.InfoWindow({
				content : contentString
			});
			//彈出info視窗
			marker.addListener('click', function() {
				infowindow.open(map, marker);
			});
			// This event listener calls addMarker() when the map is clicked.
			//增加潛點mark
			//         google.maps.event.addListener(map, "click",
			//            //callback Function 
			//            function(event) {
			//               // console.log(event.latLng.lat(), event.latLng.lng());
			//               addMarker(event.latLng, map);
			//               //當點擊地圖時，呼叫ajax來回傳LanLag到Controller
			//               var xhr = new XMLHttpRequest();
			//               //設定好回呼函數
			//               //所有Ajax引勤的回應都會呼叫onload屬性   
			//               xhr.onload = function (){
			//                   if( xhr.status == 200){
			//                     console.log(event.latLng.lat(), event.latLng.lng());

			//                   }else{
			//                     alert( xhr.status );
			//                   }
			//               };//onload 

			//             //建立好Get連接 //重點!!!!!!
			//             var url= "http://localhost:8081/DA104G2/dive/DiveServlet.do";
			//             xhr.open("post",url,true); 
			//             //送出請求 
			//             xhr.send("name=1111");

			//         });
			google.maps.event.addListener(map, "click",
			//callback Function 
			function(event) {
				//
				addMarker(event.latLng, map);
				//
				console.log(event.latLng.lat(), event.latLng.lng());
				//
				$.ajax({
					url : "http://localhost:8081/DA104G2/dive/DiveServlet.do",
					type : "POST",
					data : {
						lat : event.latLng.lat(),
						lng : event.latLng.lng()
					},
			
				});

			});

			// Adds a marker to the map.
			function addMarker(location, map) {
				// Add the marker at the clicked location, and add the next-available label
				// from the array of alphabetical characters.
				var marker = new google.maps.Marker({
					position : location,
					map : map
				});
			}//intiMap()
		}
	</script>

	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAWhufVJmMRTbPgK6H8pfnk5sDP3kf8S8E&callback=initMap"
		async defer></script>
</body>
</html>