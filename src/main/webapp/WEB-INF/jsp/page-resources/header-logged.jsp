<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div>
	<div id="leftheader">
		<a href="/MuzConnect" class="logo"><img src="/MuzConnect/resources/styles/images/icon-home.png" alt=""></a>
		<div class="search" style="margin-top: 12px; padding-top: 0">
			<input type="text" class="search-input" maxlength="100" placeholder="SEARCH"/>
			<button type="submit" id="search-btn"><img src="/MuzConnect/resources/styles/images/icon-search.png" alt=""></button>
		</div>
		<div> Dự án mới </div>
		<div> Đã tham gia </div>
	</div>
	<div id="rightheader">
		<a href="#" id="btnLogout" onclick="home.Logout()"> Đăng xuất</a>
		<a href="#">${user.name}</a>
		<span id="nof-num" style="position: absolute;">9</span>
		<a style="margin-top: -6px; position: absolute; margin-left: -40px; ">
			<img src="/MuzConnect/resources/styles/images/new-email-notification.png" alt="">
		</a>
	</div>
</div>
<script>
$('#btnModalLogin').click(function(){
	$('#modalLogin').modal({show:true})
});
</script>