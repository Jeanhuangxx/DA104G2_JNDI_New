
function initMap() {
    var map = new google.maps.Map(document.getElementById('map'), {
        center: {
            lat: 25.03,
            lng: 121.512
        },
        zoom: 10
    });

    google.maps.event.addDomListener(document.getElementById('addMarkerBtnId'), 'click', function (evt) {
        var marker = new google.maps.Marker({
            position: {
                lat: parseFloat(document.getElementById('lat').value),
                lng: parseFloat(document.getElementById('lng').value)
            },
            draggable: true,
            map: map

        });
    });

    google.maps.event.addListener(map, 'click', function (event) {
        var location = event.latLng;
        lat.value = location.lat();
        lng.value = location.lng();
    });

    //滑鼠點擊新建座標，並且只會留下一個
    var marker;

    google.maps.event.addListener(map, 'click', function (event) {

        placeMarker(event.latLng);

    });

    function placeMarker(location) {

        if (marker == null) {
            marker = new google.maps.Marker({
                position: location,
                map: map
            
            });
        } else {
            marker.setPosition(location);
        }
    }

//    function showAddress(location ,id){
//        var geocoder = new google.maps.Geocoder();
//        let lat = parseFloat(location.lat());
//        let lng = parseFloat(location.lon());
//
//        var coder = new google.maps.latlng(lat, lng);
//        geocoder.geocode({'latLng':coder},(result, status)=>{
//            if(status === google.maps.GeocoderStatus.OK){
//                if(document.getElementById(id)!=null){
//                    document.getElementById(id).value=results[0].formatted_address;             
//                }
//                document.getElementById('current').value = results[0].formatted_address;
//                address.result[0].formatted_address;
//            }else{
//                window.alert(status);
//            }
//        })
//    }
}