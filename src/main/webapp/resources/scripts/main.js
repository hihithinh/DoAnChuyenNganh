function Login() {
	user = null;
	$.ajax({
		url: "Login",
		type : "post",
		dateType:"json",
		contentType:"application/json",
		data: JSON.stringify({"account":document.getElementById('account').value,"password":document.getElementById('password').value}),
		success : function (res) {
			if(res.loginState) {
				$('#login').hide();
				document.getElementById('hello').style.color = "#000000";
				$('#hello').text("Xin chào, " + titleCase(res.user.name));
			} else {
				document.getElementById('hello').style.color = "#ff0000";
				$('#hello').text("Tài khoản hoặc mật khẩu sai!");
				document.getElementById('account').value = "";
				document.getElementById('password').value = "";
			}
		},
		error:function(){
			alert("Xảy ra lỗi khi đăng nhập");	
		}
	});
}


//Xu ly title
var rev = "fwd";
function titlebar(val) {
	var msg  = "Ứng dụng kết nối người chơi nhạc";
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

//Xu ly Proper case cho String
function titleCase(str) {
	  var newstr = str.split(" ");
	  for(i=0;i<newstr.length;i++){
	    if(newstr[i] == "") continue;
	    var copy = newstr[i].substring(1).toLowerCase();
	    newstr[i] = newstr[i][0].toUpperCase() + copy;
	  }
	   newstr = newstr.join(" ");
	   return newstr;
}  

//Xu ly noi dung cho view
var str;
function loadNewsFeed(userId) {
	str = "";
	$.ajax({
		url: "pjnewsfeed",
		type : "post",
		dateType:"json",
		contentType:"application/json",
		data: JSON.stringify({"user_id":userId}),
		success : function (res) {
			for(var i=0; i<res.lstProject.length; i++) {

				str = str
					+"<div class=\"project\">"
						+"<div class=\"present\"><iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/";
				
				for(var j=0; j<res.lstDetail.length; j++) {
					if(res.lstDetail[j].project.id==res.lstProject[i].id){
						if(res.lstProject[i].status == 1 && res.lstDetail[j].video_type == 2) {
							str = str + res.lstDetail[j].video.link;
						} else if(res.lstDetail[j].video_type == 0) {
							str = str + res.lstDetail[j].video.link;
						}
					}
				}
				
				str = str	+"\" frameborder=\"0\" allowfullscreen></iframe></div>"
						+"<div class=\"pjinfo\">"
							+"<div class=\"pjname\">"+res.lstProject[i].name+"</div>"
							+"<div class=\"pjuser\">"+res.lstProject[i].user.name+"</div>"
							+"<div class=\"pjdescription\">"+res.lstProject[i].description+"</div>"
							+"<button class=\"btnStudio\"><a target=\"_blank\" href=\"getProjectStudio?pid=" + res.lstProject[i].id
							+"\" style=\"text-decoration: none\">Mở trong Studio</a></button>"
						+"</div>"
					+"</div>";
				
			}
			$(".content").append(str);
		},
		error:function(){
			alert("Xảy ra lỗi khi đăng nhập");	
		}
	});
}

$( document ).ready( function(){
	if(loguser != null) {
		$('#login').hide();
		document.getElementById('hello').style.color = "#000000";
		$('#hello').text("Xin chào, " + titleCase(loguser.name));
	}
	loadNewsFeed(loguser == null ? "U1" : loguser.id);
});

//2. This code loads the IFrame Player API code asynchronously.
var tag = document.createElement('script');
tag.src = "https://www.youtube.com/iframe_api";
var firstScriptTag = document.getElementsByTagName('script')[0];
firstScriptTag.parentNode.insertBefore(tag, firstScriptTag);

// 3. This function creates an <iframe> (and YouTube player)
//    after the API code downloads.
var player;
function onYouTubeIframeAPIReady() {
    player = new YT.Player('ytplayer', {
      height: '300',
      width: '560',
      videoId: 'M7lc1UVf-VE',
    });
}