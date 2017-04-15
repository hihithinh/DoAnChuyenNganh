<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<html>
	<head>
		<title>Ứng dụng kết nối người chơi nhạc</title>
		<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
		<%-- <sj:head jqueryui="true" jquerytheme="cupertino"/> --%>
		<script type="text/javascript" src="http://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>
    	<script type="text/javascript" src="resources/scripts/main.js"></script>
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
    	<%-- <s:form action="fileUpload" method="post" enctype="multipart/form-data">
		   <s:file name="myFile" accept="video/*"/>
		   <s:submit value="upload"/>
		</s:form> --%>
		<s:form action="fileUpload" method="post" enctype="multipart/form-data">
	        <s:file name="fileDoc" label="Choose file to upload" />
	        <s:submit value="upload" align="center" />
		</s:form>
		<div id="content" class="content">
		</div>
		<script type="text/javascript">
			/* $('.fileUpload').fileUploader({
		          autoUpload: false,
		          buttonUpload: '#px-submit',
		          buttonClear: '#px-clear',
			}); */
		</script>
	</body>
</html>