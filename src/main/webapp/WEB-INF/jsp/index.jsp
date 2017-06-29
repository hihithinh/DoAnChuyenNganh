<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<html>
	<head>
		<title>Trang chủ</title>
		<!-- css -->
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="resources/styles/main.css">	
		
		<!-- javascript -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
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
							<span>Bạn chưa có tài khoản?</span> <a href="#" id="btnOpenCreateUser">Đăng ký</a>
							<button class="btn" onclick="home.Login()">Đăng nhập</button>
						</div>
					</div>
				</div>
			</div>
			<div id="createUserModal" class="modal fade" tabindex="-1" role="dialog">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">×</button>
							<h3>Tạo tài khoản</h3>
						</div>
						<div class="modal-body" style="overflow-y: scroll; height: 500px">
							<div class="form-group">
								<label for="usrAccount">Tên đăng nhập (bắt buộc):</label>
								<input type="text" class="form-control" id="usrAccount">
							</div>
							<div class="form-group">
								<label for="usrPassword">Mật khẩu (bắt buộc):</label>
								<input type="password" class="form-control" id="usrPassword">
							</div>
							<div class="form-group">
								<label for="usrName">Tên hiển thị:</label>
								<input type="text" class="form-control" id="usrName">
							</div>
							<div class="form-group">
								<label for="usrAddress">Địa chỉ:</label>
								<input type="text" class="form-control" id="usrAddress">
							</div>
							<div class="form-group">
								<label for="usrEmail">Email:</label>
								<input type="text" class="form-control" id="usrAddress">
							</div>
							<div class="form-group">
								<label for="usrGender">Giới tính:</label>
								<select class="form-control" id="usrGender">
									<option>Nam</option>
									<option>Nữ</option>
								</select>
							</div>
							<div class="form-group">
								<label for="usrAbility">Khả năng âm nhạc (Đè phím Ctrl để chọn nhiều mục):</label>
								<select multiple class="form-control" id="usrAbility">
									<option>Hát</option>
									<option>Guitar</option>
									<option>Piano</option>
									<option>Organ</option>
									<option>Violin</option>
									<option>Trống</option>
									<option>Ukulele</option>
									<option>Harmonica</option>
								</select>
							</div>
							<div class="form-group">
								<label for="datepicker">Ngày sinh:</label>
								<input type="text" class="form-control" id="datepicker">
							</div>
							<div class="form-group">
								<label for="usrDescription">Mô tả về bản thân:</label>
								<textarea class="form-control" rows="5" id="usrDescription" style="resize:none"></textarea>
							</div>
						</div>
						<div class="modal-footer">
							<button class="btn" data-dismiss="modal">Hủy</button>
							<button class="btn btn-primary" onclick="home.createUser()">Đăng ký</button>
						</div>
					</div>
				</div>
			</div>
			<div id="body">
				<div class="home">
					<div>
						<ul id="content"></ul>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript">
			var loguser = '<%= session.getAttribute("account") %>';
			if (loguser == "null") loguser = null;
			$(function(){
			    $( "#datepicker" ).datepicker({
			    	
			    });
		  	});
		</script>
		<script type="text/javascript" src="resources/scripts/main.js"></script>
	</body>
</html>