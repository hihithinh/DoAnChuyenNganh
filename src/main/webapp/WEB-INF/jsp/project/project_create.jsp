<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Kết nối người chơi nhạc</title>
		<!-- Style -->
		<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
		<link rel="stylesheet" href="/MuzConnect/resources/styles/main.css">
		<!-- Script -->
		<script type="text/javascript" src="http://www.google.com/jsapi"></script>
		<script src="https://apis.google.com/js/client.js?onload=googleApiClientReady"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>	
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		<script>
	        function googleApiClientReady(){
                gapi.client.setApiKey('AIzaSyARvwirFktEIi_BTaKcCi9Ja-m3IEJYIRk');
                gapi.client.load('youtube', 'v3', function() {
                        studio.search();
                });
	        }
	 </script>
		<script src="https://apis.google.com/js/platform.js?onload=onLoadCallback" async defer></script>
	</head>
	<body>
		<div style="display:none" id="page-name">create_studio</div>

		<div id="page">
			<div id="header">
			</div>
			<div id="modalLogin" class="modal fade" tabindex="-1" role="dialog">
				<div class="modal-dialog" style="width:350px">
					<div class="modal-content">
						<div class="modal-header">
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
			<div id="uploadFormModal" class="modal fade" tabindex="-1" role="dialog">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">×</button>
							<h3>Hãy đăng video nào!</h3>
						</div>
						<div class="modal-body">
							<s:form action="fileUpload" method="post" enctype="multipart/form-data">
					          <s:file name="fileDoc" label="Chọn file" accept="video/*"/>
					          <s:hidden name="pid" value="" id="pid" />
					          <s:hidden name="uid" value="" id="uid" />
							  <s:label key="Loại nhạc cụ" />
					          <s:select label="Instrument" list="#{'T300':'Hát', 'T301':'Guitar', 'T302':'Piano', 'T303':'Organ', 'T304':'Violin', 'T305':'Trống', 'T306':'Ukulele', 'T307':'Harmonica'}" name="instrument" value="1" />
					          <s:label key="Lời nhắn đến Host" />
					          <s:textarea name="description" label="Mô tả" cols="50" rows="10" />
					          <s:submit value="Đăng Video" align="left" id="btnUploadSubmit" cssClass="btnUploadFileSubmit"/>
					      </s:form>
							</div>
						<div class="modal-footer">
							<button class="btn" data-dismiss="modal">Hủy</button>
					      	<button class="btn btn-primary">Đăng</button>
						</div>
					</div>
				</div>
			</div>
			<div id="body">
				<div class="create-studio">
					<div id="pickBackingTrackModal" class="modal fade" tabindex="-1" role="dialog">
					  <div class="modal-dialog">
					      <div class="modal-content">
					        <div class="modal-header">
					          <button type="button" class="close" data-dismiss="modal">×</button>
					          <h3>Chọn nhạc nền</h3>
					        </div>
					        <div class="modal-body">
					            <div class="container">
						          	<ul class="nav nav-tabs" id="myTab" style="border-bottom: '0px'">
						              <li class="active"><a href="#search">Home</a><div class="selection" style="display:none">1</div></li>
						              <li><a href="#input">Profile</a><div class="selection" style="display:none">2</div></li>
						          	</ul>
						           
						          	<div class="tab-content">
						            	<div class="tab-pane active" id="search">
						              		<input type="text" id="txtSearch" size="40" name="txtSearch" />
											<a href="#" id="btnSearch" class="btn btn-default" onclick="googleApiClientReady()">Tìm kiếm</a>
											<div id="searchContent" style="margin-left:auto; margin-right:auto"></div>
						            	</div>
						            	<div class="tab-pane" id="input">
						              		<input type="text" id="txtInput" size="40" name="txtInput" />
						              		<a href="#" id="btnInput" class="btn btn-default" onclick="studio.createStudio()">Kiểm tra đường dẫn</a>
						              		<div id="inputContent" style="margin-left:auto; margin-right:auto"></div>
										</div>
						          	</div>
						        </div>
					        </div>
					        <div class="modal-footer">
					            <button class="btn" data-dismiss="modal">Đóng</button>
					            <button class="btn" onclick="studio.pickBackingTrack()">OK</button>
					        </div>
					      </div>
					  </div>
					</div>
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
							<h3>Tạo dự án mới</h3>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label for="pjName">Tên dự án (bắt buộc):</label>
								<input type="text" class="form-control" id="pjName">
							</div>
							<div class="form-group">
								<label for="artName">Nghệ sĩ:</label>
								<input type="text" class="form-control" id="artName">
							</div>
							<div class="form-group">
								<label for="openPickBackingTrackModal">Nhạc nền:</label>
								<input type="button" class="form-control" id="openPickBackingTrackModal">
								<div id="bktrackreview"></div>
							</div>
							<div class="form-group">
								<label for="mtype">Thể loại âm nhạc:</label>
								<select class="form-control" id="mtype">
									<option>Nhạc trẻ</option>
								</select>
							</div>
							<div class="form-group">
								<label for="needInstrument">Cần những nhạc cụ nào (Đè phím Ctrl để chọn nhiều mục):</label>
								<select multiple class="form-control" id="needInstrument">
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
								<label for="pjDescription">Mô tả về dự án:</label>
								<textarea class="form-control" rows="5" id="pjDescription" style="resize:none"></textarea>
							</div>
						</div>
						<div class="modal-footer">
							<button class="btn" onclick="studio.setBackingTrack()">Tạo dự án</button>
						</div>
						</div>
					</div>
					<iframe id="studio" width="100%" height="850" frameBorder="0" src=""></iframe>
				</div>
			</div>
		</div>
		<script>
			var loguser = '<%= session.getAttribute("account") %>';
			var user = '<%= session.getAttribute("user") %>';
			if (loguser == "null") loguser = null;
			$(function(){
			    $( "#datepicker" ).datepicker({
			    	
			    });
		  	});
			if(!loguser) {
				$('#modalLogin').modal({show:true})
			} 
		</script>
		<script type="text/javascript" src="/MuzConnect/resources/scripts/main.js"></script>
</body>
</html>