<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<html>
	<head>
		<title>Ứng dụng kết nối người chơi nhạc</title>
		<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.js"></script>
		<%-- <sj:head jqueryui="true" jquerytheme="cupertino"/> --%>
		<script type="text/javascript" src="http://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>
    	<script type="text/javascript" src="resources/scripts/main.js"></script>
		<link rel="stylesheet" href="resources/styles/main.css">
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
		<div id="header" class="header">
			<div id="btnHome" class="btnHome"></div>
			<div id="btnConfig" class="btnConfig"></div>
			<div id="user" class="user">
				<div id="login" class="rfloat">
					Tài khoản:
					<input type="text" id="account" name="account" size="20" />
					Mật khẩu:
					<input type="password" id="password" name="password"  size="20" />
					<input type="button" id="btnLogin" value="Đăng nhập" onclick="Login()"/>
				</div>
				<div id="hello"></div>
				</div>
		</div>
		<div id="content" class="content">
		</div>
		<!-- <input type="hidden" value=${sessionScope.username} id="sessionUser"/> -->
		<script type="text/javascript">
			var loguser = JSON.parse('<%= session.getAttribute("username") %>');
		</script>
	</body>
</html>