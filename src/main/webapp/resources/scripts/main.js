//Xu ly title
var rev = "fwd";
function titlebar(val) {
	var msg  = document.getElementById('page').innerHTML == "studio" ? document.getElementById('pjName').innerHTML + "-" + document.getElementById('pjUser').innerHTML : "Ứng dụng kết nối người chơi nhạc";
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

$( document ).ready( function(){
	if(loguser != null) {
		$('#login').hide();
		document.getElementById('hello').style.color = "#000000";
		$('#hello').text("Xin chào, " + home.titleCase(loguser.name));
		if(document.getElementById('page').innerHTML == "studio") {
			document.getElementById("btnAlertLogin").style.display = "none";
			document.getElementById('uid').value = loguser.id;
		}
	} else if(document.getElementById('page').innerHTML == "studio") {
		document.getElementById("btnUploadSubmit").style.display = "none";
	}
	if(document.getElementById('page').innerHTML == "home") {
		home.loadNewsFeed(loguser == null ? "U2" : loguser.id);
	} else if(document.getElementById('page').innerHTML == "studio") {
		studio.geturl();
	} else if(document.getElementById('page').innerHTML == "create_studio") {
		studio.handleAPILoaded();
	}
	$(window).resize(function() {
		console.log($(window).width());
		if($(window).width() < 1000) {
			$('#content').css({"margin-left": "auto", "margin-right": "auto"});
			$('#pjinfo').css({"margin-left": "0px", "margin-top": "300px"});
			$('#btnStudio').css({"bottom": "", "right": "5px", "top": "0px"});
		} else if($(window).width() < 1300) {
			$('#content').css({"margin-left": "auto", "margin-right": "auto"});
		} else {
			$('#content').css({"margin-left": "150px", "margin-right": "150px"});
			$('#pjinfo').css({"margin-left": "400px", "margin-top": "0px"});
			$('#btnStudio').css({"bottom": "10px", "right": "10px", "top": ""});
		}
	});
});

var home = {
	Login: function () {
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
					$('#hello').text("Xin chào, " + home.titleCase(res.user.name));
					if(document.getElementById('page').innerHTML == "studio") {
						document.getElementById('uid').value = res.user.id;
						document.getElementById("btnAlertLogin").style.display = "none";
						document.getElementById("btnUploadSubmit").style.display = "block";
					}
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
	},
	//Xu ly Proper case cho String
	titleCase: function (str) {
		  var newstr = str.split(" ");
		  for(i=0;i<newstr.length;i++){
		    if(newstr[i] == "") continue;
		    var copy = newstr[i].substring(1).toLowerCase();
		    newstr[i] = newstr[i][0].toUpperCase() + copy;
		  }
		   newstr = newstr.join(" ");
		   return newstr;
	},
	//Xu ly noi dung cho view
	loadNewsFeed: function (userId) {
		var str = "";
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
					
					var now = (new Date()).getTime();
					var update_day = new Date(res.lstProject[i].update_day.replace("T", " "));
					if(update_day.getTime() <= 60000) {
						var pjtime = "mới đây";
					} else if(update_day.getTime() < 3600000) {
						var pjtime = (Math.floor((now - update_day) / 3600000)) + "phút trước";
					} else if(update_day.getTime() < 86400000) {
						var pjtime = (Math.floor((now - update_day) / 86400000)) + "tiếng trước";
					} else {
						var pjtime = update_day.getDate() + " tháng " + update_day.getMonth() + " " + update_day.getYear()
										+ " lúc " + update_day.getHours() + ":" + update_day.getMinutes();
					}
					
					str = str	+"\" frameborder=\"0\" allowfullscreen></iframe></div>"
							+"<div class=\"pjinfo\">"
								+"<div class=\"pjname\">"+res.lstProject[i].name+"</div>"
								+"<div class=\"pjtime\">Cập nhật "+ pjtime +"</div>"
								+"<div class=\"pjuser\">"+res.lstProject[i].user.name+"</div>"
								+"<br><div class=\"pjdescription\">"+res.lstProject[i].description+"</div>"
								+"<button class=\"btnStudio\"><a target=\"_blank\" href=\"studio/" + res.lstProject[i].id
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
	},
}

var studio = {
	doCombile: function (id) {
		user = null;
		$.ajax({
			url: "doCombile",
			type : "post",
			dateType:"json",
			contentType:"application/json",
			data: JSON.stringify({"pid":id}),
			success : function (res) {
				//xu ly JSON tra ve khi Combile thanh cong
			},
			error:function(){
				alert("Xảy ra lỗi khi đăng nhập");	
			}
		});
	},
	geturl: function () {
		document.getElementById('pid').value = document.getElementById('pjId').innerHTML;
		$.ajax({
			url: "/MuzConnect/getProjectDetail",
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
	},
	checkLogin: function () {
		alert('Bạn phải đăng nhập trước!');
	},
	// After the API loads, call a function to enable the search box.
	handleAPILoaded: function () {
	  $('#btnSearch').attr('disabled', false);
	},

	searchResult: null,
	
	search: function () {
	  var q = $('#txtSearch').val();
	  var request = gapi.client.youtube.search.list({
	    q: q,
	    part: 'id, snippet',
        type: 'video'
	  });

	  request.execute(function(response) {
	    var str = JSON.stringify(response.result);
	    studio.searchResult = response.result;
	    var htmlstr = '<div id="selected" style="font-color:white"></div><form><input type="button" value="ok cái này" onclick="studio.setBackingTrack()"/>';
	    for (var i = 0; i < response.result.items.length; i++) {
	    	htmlstr = htmlstr+'<div id="ytItem'+i+'"><iframe width="560" height="315" src="https://www.youtube.com/embed/' + response.result.items[i].id.videoId +'" frameborder="0" allowfullscreen></iframe></div>';
	    	htmlstr = htmlstr+'<input type="radio" name="item" value="'+response.result.items[i].id.videoId+'" onclick="studio.checkRadioBKT('+i+')" />';
	    }
	    htmlstr = htmlstr + '</form>'
	    $('#searchContent').html(htmlstr);
	  });
	},
	checkRadioBKT: function(itemNumber) {
		$('#selected').html('Đã chọn:'+studio.searchResult.items[itemNumber].snippet.title);
		studio.searchResult.checked = itemNumber;
	},
	setBackingTrack: function() {
		$.ajax({
			url: "createStudio",
			type : "post",
			dateType:"json",
			contentType:"application/json",
			data: JSON.stringify({"backingTrack":{"user":{"id":"U1"},"link":studio.searchResult.items[studio.searchResult.checked].id.videoId, "instrument":{"id":"T299"}, "description": loguser.id}}),
			success : function (res) {
				window.location.replace("getProjectStudio?pid="+res.pid);
			},
			error:function(){
				alert("Xảy ra lỗi khi lấy dữ liệu");	
			}
		});
	}
}









/*//2. This code loads the IFrame Player API code asynchronously.
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
}*/