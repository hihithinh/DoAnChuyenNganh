<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<html>
	<head>
		<title>Ứng dụng kết nối người chơi nhạc</title>
		<!-- css -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="resources/styles/main.css">	
		
		<!-- javascript -->
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
		<div style="display:none" id="page">home</div>
		<div id="header" class="container-fluid header">
			<div id="btnHome" class="btnHome"></div>
			<div id="btnConfig" class="btnConfig"></div>
			<div id="user" class="user">
				<div id="login" class="rfloat">
					<a href="#" class="btn btn-default" id="btnModalLogin">Đăng Nhập</a>
				</div>
				<div id="hello"></div>
				</div>
		</div>
		<div id="modalLogin" class="modal fade" tabindex="-1" role="dialog">
			<div class="modal-dialog" style="width:300px">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">×</button>
						<h3>Đăng nhập</h3>
					</div>
					<div class="modal-body">
						<div id="logStatus" class="login_input"></div>
						<div class="login_input">
							<span>Account: </span>
							<input type="text" id="account" name="account" class="form-control">
						</div>
						<div class="login_input">
							<span>Password: </span>
							<input type="password" id="password" name="password" class="form-control">
						</div>
					</div>
					<div class="modal-footer">
						<button class="btn" onclick="home.Login()">Đăng nhập</button>
					</div>
				</div>
			</div>
		</div>
		<div id="content" class="content">
		</div>
		<script type="text/javascript" src="resources/scripts/main.js"></script>
		<script type="text/javascript">
			var loguser = JSON.parse('<%= session.getAttribute("username") %>');
		</script>
	</body>
</html>