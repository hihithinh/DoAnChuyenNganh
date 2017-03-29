var user;
//Xu ly login
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
				user = res.user;
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

function loadNewsFeed() {
	$.ajax({
		url: "pjnewsfeed",
		type : "post",
		dateType:"json",
		contentType:"application/json",
		data: JSON.stringify({"user_id":user.id}),
		success : function (res) {
			alert(res)
		},
		error:function(){
			alert("Xảy ra lỗi khi đăng nhập");	
		}
	});
}

