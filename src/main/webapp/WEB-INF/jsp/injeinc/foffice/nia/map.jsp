<!-- #include virtual = "/dbcon.asp" -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% idx= request("idx") 
   srText="대구광역시 동구 첨단로 53"  
   strSQL="select com_addr from tbcompany_info where comidx='"& idx &"' "
   Set dbList = DB.execute (strSQL, 0)
   if dbList.eof or dbList.bof then 
   else
	  srText=Replace(Replace(dbList("com_addr"),"'",""),"&","")
   end if
   dbList.close 
   Set dbList=Nothing

   set DB = nothing
   set OraSession = nothing
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="euc-kr">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <title>간단한 지도 표시하기</title>
    <script src="./js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="./js/base.js"></script>
    <script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=liw055akw6&submodules=panorama,geocoder,drawing,visualization"></script>
    <link rel="stylesheet" type="text/css" href="./css/base.css" />
    <style type="text/css">
.search { position:absolute;z-index:1000;top:20px;left:20px; }
.search #address { width:150px;height:20px;line-height:20px;border:solid 1px #555;padding:5px;font-size:12px;box-sizing:content-box; }
.search #submit { height:30px;line-height:30px;padding:0 10px;font-size:12px;border:solid 1px #555;border-radius:3px;cursor:pointer;box-sizing:content-box; }
</style>
</head>
<body>
<div id="wrap" class="section">
    
    <div id="map" style="width:100%;height:380px;">
    </div>
    <code id="snippet" class="snippet"></code>
</div>

<script>

    var map = new naver.maps.Map("map", {
        center: new naver.maps.LatLng(35.8811123, 128.7296288), //배움나라 위치(초기값)
        zoom: 18,
        mapTypeControl: true,
		scaleControl : true,
		logoControl : false,
		mapDataControl : true,
		zoomControl : true,
		minZoom : 6
    });

    var infoWindow = new naver.maps.InfoWindow({
        anchorSkew: true
    });

    map.setCursor('pointer');

    function searchCoordinateToAddress(latlng) {

        infoWindow.close();

        naver.maps.Service.reverseGeocode({
            coords: latlng,
            orders: [
      naver.maps.Service.OrderType.ADDR,
      naver.maps.Service.OrderType.ROAD_ADDR
    ].join(',')
        }, function (status, response) {
            if (status === naver.maps.Service.Status.ERROR) {
                if (!latlng) {
                    return alert('ReverseGeocode Error, Please check latlng');
                }
                if (latlng.toString) {
                    return alert('ReverseGeocode Error, latlng:' + latlng.toString());
                }
                if (latlng.x && latlng.y) {
                    return alert('ReverseGeocode Error, x:' + latlng.x + ', y:' + latlng.y);
                }
                return alert('ReverseGeocode Error, Please check latlng');
            }

            var address = response.v2.address,
        htmlAddresses = [];

            if (address.jibunAddress !== '') {
                htmlAddresses.push('[지번 주소] ' + address.jibunAddress);
            }

            if (address.roadAddress !== '') {
                htmlAddresses.push('[도로명 주소] ' + address.roadAddress);
            }

            infoWindow.setContent([
      '<div style="padding:10px;min-width:200px;line-height:150%;">',
      '<h4 style="margin-top:5px;">검색 좌표</h4><br />',
      htmlAddresses.join('<br />'),
      '</div>'
    ].join('\n'));

            infoWindow.open(map, latlng);
        });
    }

    function searchAddressToCoordinate(address) {
        naver.maps.Service.geocode({
            query: address
        }, function (status, response) {
            if (status === naver.maps.Service.Status.ERROR) {
                if (!address) {
                    return alert('Geocode Error, Please check address');
                }
                return alert('Geocode Error, address:' + address);
            }
            if (response.v2.meta.totalCount === 0) {
                return alert('No result.');
            }

            var htmlAddresses = [],
            item = response.v2.addresses[0],
            point = new naver.maps.Point(item.x, item.y);
            
            if (address.jibunAddress !== '') {
                htmlAddresses.push('[지번 주소] ' + item.jibunAddress);
            }

            if (address.roadAddress !== '') {
                htmlAddresses.push('[도로명 주소] ' + item.roadAddress);
            }

            infoWindow.setContent([
      '<div style="padding:10px;min-width:200px;line-height:100%;">',
      '<h4 style="margin-top:5px;">검색 주소 : ' + address + '</h4><br />',
      htmlAddresses.join('<br />'),
      '</div>'
    ].join('\n'));

            map.setCenter(point);
            //infoWindow.open(map, point);

            var marker = new naver.maps.Marker({
                position: new naver.maps.LatLng(item.y, item.x),
                map: map
            });

			 naver.maps.Event.addListener(marker, "click", function(e){
			 if (infoWindow.getMap())
			   {
				 infoWindow.close();
			   }else{
				  infoWindow.open(map, marker);
			   }
			 });

        });
    }

	
    function initGeocoder() {
        if (!map.isStyleMapReady) {
            return;
        }

        //map.addListener('click', function (e) {
        //    searchCoordinateToAddress(e.coord);
        //});

        //$('#address').on('keydown', function (e) {
        //    var keyCode = e.which;
        //    if (keyCode === 13) { // Enter Key
        //        searchAddressToCoordinate($('#address').val());
        //    }
        //});

        //$('#submit').on('click', function (e) {
        //    e.preventDefault();
        //    searchAddressToCoordinate($('#address').val());
        //});

        searchAddressToCoordinate('<%=srText%>');
    }

    naver.maps.onJSContentLoaded = initGeocoder;
    naver.maps.Event.once(map, 'init_stylemap', initGeocoder);
    
	
</script>
</body>
</html>
