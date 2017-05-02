<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>Kết nối người chơi nhạc</title>
		<!-- Style -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="/MuzConnect/resources/styles/main.css">
		<!-- Script -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>	
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
		<div style="display:none" id="page">user_config</div>
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
		<div id="content" class="content">
			<div id="userInfo" class="userInfo">
				<img src="<s:property value="user.avatar" />" />
				<div id="userName" class="userName">
					<h2><s:property value="user.name" /></h2>
					<p>Level: <s:property value="user.user_type.name" /></p>
				</div>
				Giới tính: <s:property value="user.user_type.name" /><br>
				Ngày sinh: <s:property value="user.gender" /><br>
				Địa chỉ: <s:property value="user.address" /><br>
				Ngày tạo: <s:property value="user.created_day" /><br>
				Khả năng âm nhạc: <s:property value="user.ability" /><br>
				Giới thiệu: <s:property value="user.description" /><br>
			</div>
		</div>
		
		<script>
		var loguser = JSON.parse('<%= session.getAttribute("username") %>');
		</script>
		<script type="text/javascript" src="/MuzConnect/resources/scripts/main.js"></script>
	</body>
</html>