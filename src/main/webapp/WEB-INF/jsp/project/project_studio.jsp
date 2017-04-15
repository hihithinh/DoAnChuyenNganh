<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Kết nối người chơi nhạc</title>
		<script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
		<script type="text/javascript" src="http://www.jeasyui.com/easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="resources/scripts/studio.js"></script>
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
		<h2><s:property value="lstProject[0].name"/> - <s:property value="lstProject[0].user.name"/></h2>
		<iframe id="studio" width="100%" height="850" src=""></iframe>
		
		<input type="button" id="btnLogin" value="Đăng nhập" onclick="doCombile(lstProject[0].id)"/>
		
		<span id="pjName" style="display:none"><s:property value="lstProject[0].name" /></span>
		<span id="pjUser" style="display:none"><s:property value="lstProject[0].user.name" /></span>
		<span id="pjId" style="display:none"><s:property value="lstProject[0].id" /></span>
		<span id="pjStatus" style="display:none"><s:property value="lstProject[0].status" /></span>
		<script>
		//Xu ly title
		var rev = "fwd";
		function titlebar(val) {
			var msg  = pjName.innerHTML + "-" + pjUser.innerHTML;
			var res = " ";
			var speed = 100;
			var pos = val;
					
			msg = "   ♩♬♪♫ "+msg+" ♩♪♫♬";
			var le = msg.length;
			if(rev == "fwd"){
				if(pos < le){
					pos = pos+1;
					scroll = msg.substr(0,pos);
					document.title = scroll;
					timer = window.setTimeout("titlebar("+pos+")",speed);
				} else {
					rev = "bwd";
					timer = window.setTimeout("titlebar("+pos+")",speed);
				}
			} else {
				if(pos > 0){
					pos = pos-1;
					var ale = le-pos;
					scrol = msg.substr(ale,le);
					document.title = scrol;
					timer = window.setTimeout("titlebar("+pos+")",speed);
				} else {
					rev = "fwd";
					timer = window.setTimeout("titlebar("+pos+")",speed);
				}    
			}
		}
		titlebar(0);
		
		function geturl(){
			$.ajax({
				url: "getProjectDetail",
				type : "post",
				dateType:"json",
				contentType:"application/json",
				data: JSON.stringify({"pid":pjId.innerHTML}),
				success : function (res) {
					var url='https://viewsync.net/watch?';
					var firstvideo = true;
					for(var i = 0; i < res.lstDetail.length; i++) {
						if(res.lstDetail[i].video_type == 1 || pjStatus.innerHTML == "0" || pjStatus.innerHTML == 0) {
							if(firstvideo) {
								url = url + 'v=' +res.lstDetail[i].video.link + '&t=0';
								firstvideo = false;
							} else {
								url = url + '&v=' + res.lstDetail[i].video.link + '&t=0';
							}
						}
					}
					url = url +'&mode=solo';
					document.getElementById('studio').src = url;
				},
				error:function(){
					alert("Xảy ra lỗi khi lấy dữ liệu");	
				}
			});
		}
		
		$( document ).ready(geturl());
		</script>
</body>
</html>