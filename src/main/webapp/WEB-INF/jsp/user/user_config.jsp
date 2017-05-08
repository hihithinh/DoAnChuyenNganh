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
		<link rel="stylesheet" href="/MuzConnect/resources/styles/layout-default-latest.css">
		<link rel="stylesheet" href="/MuzConnect/resources/styles/main.css">
		<!-- Script -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-layout/1.4.3/jquery.layout_and_plugins.min.js"></script>
		<script type="text/javascript" src="/MuzConnect/resources/scripts/jquery.layout-latest.js"></script>
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
		<div style="display:none" id="page">profile</div>
		<div id="header" class="header">
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
			<div id="userInfo" class="userInfo" style="float:left">
				<img src="<s:property value="user.avatar" />" />
				<div id="userName" class="userName">
					<h2><s:property value="user.name" /></h2>
					<p>Level: <s:property value="user.user_type.name" /></p>
				</div>
				Giới tính: <s:property value="user.gender" /><br>
				Ngày sinh: <s:property value="user.birthday" /><br>
				Địa chỉ: <s:property value="user.address" /><br>
				Ngày tạo: <s:property value="user.created_day" /><br>
				Khả năng âm nhạc: <s:property value="user.ability" /><br>
				Giới thiệu: <s:property value="user.description" /><br>
			</div>
			<div style="float:left;overflow: hidden;min-width: 800px;">
				<ul class="nav nav-tabs" id="profileTab">
					<li class="active"><a href="#createdPJ">Dự án đã tạo</a></li>
					<li><a href="#joinedPJ">Dự án tham gia</a></li>
					<li><a href="#video">Video</a></li>
				</ul>
				 
				<div class="tab-content">
					<div class="tab-pane active" id="createdPJ">
							<div class="btn-group">
							<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
								Sắp xếp
								<span class="caret"></span>
							</a>
							<ul class="dropdown-menu">
								<li><a href="#">Choice1</a></li>
								<li><a href="#">Choice2</a></li>
								<li><a href="#">Choice3</a></li>
								<li class="divider"></li>
								<li><a href="#">Choice..</a></li>
							</ul>
						</div>
							<hr>
							Home content...</div>
					<div class="tab-pane" id="joinedPJ">
							<div class="btn-group">
							<a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
								Sắp xếp
								<span class="caret"></span>
							</a>
							<ul class="dropdown-menu">
								<li><a href="#">Choice1</a></li>
								<li><a href="#">Choice2</a></li>
								<li><a href="#">Choice3</a></li>
								<li class="divider"></li>
								<li><a href="#">Choice..</a></li>
							</ul>
						</div>
							<hr>
							Content here...</div>
					<div class="tab-pane" id="video">Messages...</div>
				</div>
			</div>
		</div>
		
		<script>
		var loguser = JSON.parse('<%= session.getAttribute("username") %>');
		</script>
		<script type="text/javascript" src="/MuzConnect/resources/scripts/main.js"></script>
	</body>
</html>