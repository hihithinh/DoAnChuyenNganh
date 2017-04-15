function Login(id) {
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
}