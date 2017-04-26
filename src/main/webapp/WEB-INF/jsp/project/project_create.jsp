<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Kết nối người chơi nhạc</title>
		<link rel="stylesheet" href="resources/styles/main.css">
		<!-- Script -->
		<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
		<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
		<script type="text/javascript" src="http://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="http://www.google.com/jsapi"></script>
		<script src="https://apis.google.com/js/client.js?onload=googleApiClientReady"></script>
	</head>
	<body>
		<script>
	  		window.fbAsyncInit = function() {
	    		FB.init({
	      			appId      : '1253610298021820',
	      			xfbml      : true,
	      			version    : 'v2.8'
	    		});
	    		FB.AppEvents.logPageView();
	  		};
	
	  		(function(d, s, id){
	     		var js, fjs = d.getElementsByTagName(s)[0];
	     		if (d.getElementById(id)) {return;}
	     			js = d.createElement(s); js.id = id;
	     			js.src = "//connect.facebook.net/en_US/sdk.js";
	     			fjs.parentNode.insertBefore(js, fjs);
	   			}(document, 'script', 'facebook-jssdk'));
		</script>
		<script>
	        function googleApiClientReady(){
                gapi.client.setApiKey('AIzaSyARvwirFktEIi_BTaKcCi9Ja-m3IEJYIRk');
                gapi.client.load('youtube', 'v3', function() {
                        studio.search();
                });
	        }
	 </script>
		<script src="https://apis.google.com/js/platform.js?onload=onLoadCallback" async defer></script>
		<div style="display:none" id="page">create_studio</div>
		<div id="header" class="header">
			<div id="btnHome" class="btnHome"></div>
			<div id="btnConfig" class="btnConfig"></div>
			<div id="user" class="user">
				<div id="login" class="rfloat">
					Tài khoản:
					<input type="text" id="account" name="account" size="20" />
					Mật khẩu:
					<input type="password" id="password" name="password"  size="20" />
					<input type="button" id="btnLogin" value="Đăng nhập" onclick="home.Login()"/>
				</div>
				<div id="hello"></div>
			</div>
		</div>
		<input type="text" id="txtSearch" name="txtSearch" />
		<input type="submit" id="btnSearch" value="search" disabled="disabled" onclick="googleApiClientReady()"/>
		<div id="searchContent" style="margin-left:auto; margin-right:auto"></div>
		<div class="studio"></div>
		<iframe class="studio" id="studio" frameBorder="0" src=""></iframe>
		
		<script>var loguser = JSON.parse('<%= session.getAttribute("username") %>');</script>
		<script type="text/javascript" src="resources/scripts/main.js"></script>
</body>
</html>