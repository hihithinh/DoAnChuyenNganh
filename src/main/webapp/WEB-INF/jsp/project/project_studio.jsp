<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Kết nối người chơi nhạc</title>
		<!-- Style -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
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
		<div style="display:none" id="page-name">studio</div>
		<span id="pjName" style="display:none"><s:property value="lstProject[0].name" /></span>
		<span id="pjUser" style="display:none"><s:property value="lstProject[0].user.name" /></span>
		<span id="pjId" style="display:none"><s:property value="lstProject[0].id" /></span>
		<span id="pjStatus" style="display:none"><s:property value="lstProject[0].status" /></span>
		<div id="page">
			<div id="header">
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
				<div class="studio">
					<h2><s:property value="lstProject[0].name"/> - <s:property value="lstProject[0].user.name"/></h2>
					<iframe id="studio" width="100%" height="850" frameBorder="0" src=""></iframe>
				</div>
				<button id="btnCombile" class="btn" onclick="studio.doCombile(pjId.innerHTML)">Combile</button>
				<a href="#" class="btn btn-default" id="btnOpenUploadForm">Tham gia</a>	
			</div>
		</div>
		<script>
			var loguser = '<%= session.getAttribute("account") %>';
			if (loguser == "null") loguser = null;
			$("#btnUploadSubmit").css("display", "none");
		</script>
		<script type="text/javascript" src="/MuzConnect/resources/scripts/main.js"></script>
</body>
</html>