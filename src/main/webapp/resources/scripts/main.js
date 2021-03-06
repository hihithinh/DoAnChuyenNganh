$('#btnModalLogin').on('click', function() {
	$('#modalLogin').modal({show:true})
});
$('#myTab a').click(function (e) {
	 e.preventDefault();
	 $(this).tab('show');
});

$('#btnOpenUploadForm').on('click', function() {
	if(loguser) {
		$('#uploadFormModal').modal({show:true})
	} else {
		$('#modalLogin').modal({show:true})
	}
});
$('#uploadFormModal').find("button.btn-primary").click(function(){
	$("#btnUploadSubmit").click();
});
$('#btnOpenCreateUser').on('click', function() {
	$('#modalLogin').modal('hide')
	$("#createUserModal").modal({show:true})
});
$('#openPickBackingTrackModal').on('click', function() {
	$("#pickBackingTrackModal").modal({show:true})
});

$( document ).ready( function(){
	$.ajax({
		url: "/MuzConnect/get-header",
		type : "get",
		success : function (res) {
			document.getElementById('header').innerHTML = res;
			if(loguser && document.getElementById('page-name').innerHTML == "studio") {
				document.getElementById('uid').value = $("#userID").html();
			}
		}
	});
	if(loguser && document.getElementById('page-name').innerHTML == "studio") {
		document.getElementById('pid').value = document.getElementById('pjId').innerHTML;
	}
	if(document.getElementById('page-name').innerHTML == "home") {
		home.loadNewsFeed(loguser == null ? "U2" : loguser.id);
	} else if(document.getElementById('page-name').innerHTML == "studio") {
		studio.geturl();
	} else if(document.getElementById('page-name').innerHTML == "create_studio") {
		studio.handleAPILoaded();
	} else if(document.getElementById('page-name').innerHTML == "profile") {
		user.loadProjectbyOwnerId((loguser == null || loguser == undefined) ? null : loguser.id);
		user.loadProjectbyJoinedUserId((loguser == null || loguser == undefined) ? null : loguser.id);
	}
	$(window).resize(function() {
		console.log($(window).width());
		if($(window).width() < 1000) {
			$('.more').css({"top": 0, "bottom": ""});
		} else {
			$('.more').css({"top": "", "bottom": 0});
		}
	});
});
var user = {
	getUserSimpleInfo: function (userId) {
		$.ajax({
			url: "/MuzConnect/user/" + userId,
			type : "get",
			success : function (res) {
				$("#userSimpleInfo").find("div.modal-body").html(res);
				$('#userSimpleInfo').modal({show:true})
			},
			error:function(){
				alert("Xảy ra lỗi khi tải dữ liệu dự án");	
			}
		});
	},
	loadProjectbyOwnerId: function (currUser) {
		var str = "";
		var user = window.location.href.split("/")[window.location.href.split("/").length - 1];
		$.ajax({
			url: "/MuzConnect/pjProfileOwner",
			type : "post",
			dateType:"json",
			contentType:"application/json",
			data: JSON.stringify({"currUser":currUser, "gettingUser":user}),
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
								+"<button class=\"btnStudio\"><a target=\"_blank\" href=\"/MuzConnect/studio/" + res.lstProject[i].id
								+"\" style=\"text-decoration: none\">Mở trong Studio</a></button>"
							+"</div>"
						+"</div>";
					
				}
				document.getElementById('createdPJcontent').innerHTML = str;
			},
			error:function(){
				alert("Xảy ra lỗi khi tải dữ liệu dự án");	
			}
		});
	},
	loadProjectbyJoinedUserId: function (currUser) {
		var str = "";
		var user = window.location.href.split("/")[window.location.href.split("/").length - 1];
		$.ajax({
			url: "/MuzConnect/pjProfileJoinUser",
			type : "post",
			dateType:"json",
			contentType:"application/json",
			data: JSON.stringify({"currUser":currUser, "gettingUser":user}),
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
				document.getElementById('joinedPJcontent').innerHTML = str;
			},
			error:function(){
				alert("Xảy ra lỗi khi tải dữ liệu dự án");	
			}
		});
	}
}
var home = {
	Login: function () {
		user = null;
		$.ajax({
			url: "/MuzConnect/Login",
			type : "post",
			dateType:"json",
			contentType:"application/json",
			data: JSON.stringify({"account":document.getElementById('account').value,"password":document.getElementById('password').value}),
			success : function (res) {
				if(res.loginState) {
					$('#modalLogin').modal("toggle");
					if(document.getElementById('page').innerHTML == "studio") {
						document.getElementById('uid').value = res.user.id;
					}
					window.location.reload();
				} else {
					$('#logStatus').css("color", "#ff0000");
					$('#logStatus').text("Tài khoản hoặc mật khẩu sai!");
				}
			},
			error:function(){
				alert("Xảy ra lỗi khi đăng nhập");	
			}
		});
	},
	Logout: function () {
		user = null;
		$.ajax({
			url: "/MuzConnect/Logout",
			type : "get",
			success : function (res) {
				loguser = null;
				window.location.reload();
			},
			error:function(){
				alert("Xảy ra lỗi khi đăng xuất");	
			}
		});
	},
	createUser: function () {
		var abilityString = "";
		for (var i = 0; i < $("#usrAbility").val().length; i++) {
			abilityString = abilityString + $("#usrAbility").val()[i] + ", "
		}
		abilityString = abilityString.substring(0, abilityString.length - 2);
		var newUser = {
				account: $("#usrAccount").val(),
				password: $("#usrPassword").val(),
				fbtoken: null,
				ggtoken: null,
				name: $("#usrName").val() || $("#usrAccount").val(),
				address: $("#usrAddress").val() || "Không có",
				avatar: "default.png",
				user_type: {
					id: "T102"
				},
				email: $("#usrEmail").val(),
				status: 1,
				gender: $("#usrGender").val(),	
				ability: abilityString,
				birthday: new Date($("#datepicker").val()),
				description: $("#usrDescription").val() || null
		}
		$.ajax({
			url: "/MuzConnect/createUser",
			type : "post",
			dateType:"json",
			contentType:"application/json",
			data: JSON.stringify({"user":newUser}),
			success : function (res) {
				if(res.loginState) {
					$('#modalLogin').modal("toggle");
					if(document.getElementById('page').innerHTML == "studio") {
						document.getElementById('uid').value = res.user.id;
					}
					window.location.reload();
				}
			},
			error:function(){
				alert("Xảy ra lỗi khi tạo User");	
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
						+'<li>'
							+'<div class="abc">'
								+'<iframe width="560" height="315" src="https://www.youtube.com/embed/';
					
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
					
					str = str	+'?rel=0&amp;showinfo=0" frameborder="0" allowfullscreen></iframe>'
							+'</div>'
							+'<div>'
								+'<h1>'+res.lstProject[i].name+'</h1>'
								+'<p style="float:left;color:#cccccc">'+pjtime+'</p>'
								+'<a href="#" style="float:right;color:#cccccc;" onclick="user.getUserSimpleInfo(\''+res.lstProject[i].user.account+'\' )"><b>'+res.lstProject[i].user.name+'</b></a><br/>'
								+'<p>'+res.lstProject[i].description+'</p>'
								+'<a href="studio/'+res.lstProject[i].id+'" class="more btn">Mở Studio</a>'
							+'</div>'
						+'</li>';
				}
				str = str + '<div id="userSimpleInfo" style="padding: 0" class="modal fade" tabindex="-1" role="dialog">\
								<div style="padding: 0" class="modal-dialog">\
									<div style="padding: 0" class="modal-content">\
										<div style="padding: 0" class="modal-body">\
										</div>\
									</div>\
								</div>\
							</div>'
				document.getElementById('content').innerHTML += str;
			},
			error:function(){
				alert("Xảy ra lỗi khi lấy New feed");	
			}
		});
	},
}

var studio = {
	doCombile: function () {
		user = null;
		$.ajax({
			url: "/MuzConnect/doCombile",
			type : "post",
			dateType:"json",
			contentType:"application/json",
			data: JSON.stringify({"pid": $("#pjId").text()}),
			success : function (res) {
				//xu ly JSON tra ve khi Combile thanh cong
			},
			error:function(){
				alert("Xảy ra lỗi khi combine");	
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
	    var htmlstr = '<div id="selected" style="font-color:white"></div><form>';
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
	createStudio: function() {
		str = $("#txtInput").val();
		url = '<iframe width="560" height="315" src="https://www.youtube.com/embed/'+str+'" frameborder="0" allowfullscreen></iframe>';
		$("#inputContent").html(url);
	},
	url: null,
	pickBackingTrack: function() {
		studio.url = "";
		if($("#body").find("li.active").find("div.selection").text() == 1) {
			studio.url = studio.searchResult.items[studio.searchResult.checked].id.videoId;
		} else if($("#body").find("li.active").find("div.selection").text() == 2){
			studio.url = $("#txtInput").val();
		}
		$("#bktrackreview").html('<iframe width="560" height="315" src="https://www.youtube.com/embed/'+studio.url+'" frameborder="0" allowfullscreen></iframe>');
		$("#pickBackingTrackModal").modal("hide")
	},
	setBackingTrack: function() {
		var backingTrack = {
				user: {
					id: $("#userID").text()
				},
				link: studio.url,
				instrument: {
					id: "T299"
				},
				description: $("#userID").text()
		}
		var newProject = {
				name: $("#pjName").val(),
				user: {
					id: $("#userID").text()
				},
				artist_name: $("#artName").val(),
				music_type: {
					id: "T201"
				},
				description: $("#pjDescription").val()
		}
		for (var i = 0; i < $("#needInstrument").val().length; i++) {
			if($("#needInstrument").val()[i].toLowerCase() == "piano")
				newProject.needPiano = 1;
			else if($("#needInstrument").val()[i].toLowerCase() == "organ")
				newProject.needOrgan = 1;
			else if($("#needInstrument").val()[i].toLowerCase() == "hát")
				newProject.needVocal = 1;
			else if($("#needInstrument").val()[i].toLowerCase() == "guitar")
				newProject.needGuitar = 1;
			else if($("#needInstrument").val()[i].toLowerCase() == "Violin")
				newProject.needViolon = 1;
			else if($("#needInstrument").val()[i].toLowerCase() == "Trống")
				newProject.needDrum = 1;
			else if($("#needInstrument").val()[i].toLowerCase() == "Ukulele")
				newProject.needUkulele = 1;
			else if($("#needInstrument").val()[i].toLowerCase() == "Harmonica")
				newProject.needHarmonica = 1;
		}
		$.ajax({
			url: "createStudio",
			type : "post",
			dateType:"json",
			contentType:"application/json",
			data: JSON.stringify({"backingTrack": backingTrack, "newProject": newProject}),
			success : function (res) {
				window.location.replace("studio/"+res.pid);
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