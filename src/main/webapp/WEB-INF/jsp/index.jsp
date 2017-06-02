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
		<div style="display:none" id="page-name">home</div>
		<div id="page">
			<div id="header">
			</div>
			<div id="modalLogin" class="modal fade" tabindex="-1" role="dialog">
				<div class="modal-dialog" style="width:350px">
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
			<div id="body">
				<div class="home">
					<div>
						<ul id="content">
						
						</ul>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript" src="resources/scripts/main.js"></script>
		<script type="text/javascript">
			var loguser = JSON.parse('<%= session.getAttribute("username") %>');
		</script>
	</body>
</html>