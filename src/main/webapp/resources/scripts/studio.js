var dialog, form;
function doCombile(id) {
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
dialog = $( "#dialog-form" ).dialog({
	autoOpen: false,
	height: 400,
	width: 350,
	modal: true,
	buttons: {
       Cancel: function() {
    	   dialog.dialog( "close" );
       }
	},
	close: function() {
    	 form[ 0 ].reset();
       allFields.removeClass( "ui-state-error" );
	}
});

$( "#btnUpload" ).button().on( "click", function() {
	dialog.dialog( "open" );
});