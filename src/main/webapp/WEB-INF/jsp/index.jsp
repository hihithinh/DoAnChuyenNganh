<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
		<title>Ứng dụng kết nối người chơi nhạc</title>
		<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
		<script type="text/javascript" src="http://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>
    	<script type="text/javascript" src="resources/scripts/main.js"></script>
    	<script type="text/javascript" src="resources/scripts/player.js"></script>
		<link rel="stylesheet" href="resources/styles/main.css">
	</head>
 
	<body>
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
			<input type="button" id="btnGetContent" value="Lấy nội dung" onclick="loadNewsFeed()"/>
			<div id="project" class="project">
				<div id="view">
					<div id="ytplayer"></div>
					<div id="pjinfo">
						<div id="pjname"></div>
						<div id="user"></div>
						<div id="description"></div>
						<input id="btnStudio" type="button" value="Change site" onClick="geturl()" />
					</div>
				</div>
				<iframe id="studio" width="100%" height="850" src=""></iframe>
			</div>
		</div>
	</body>
</html>