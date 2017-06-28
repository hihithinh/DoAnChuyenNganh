<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div style="padding: 0" class="container-fluid content">
    <div style="padding: 0" class="row">
        <div style="padding: 0" class="col-xs-24 col-sm-24 col-md-12 col-lg-12 " >
	        <div style="padding: 0" class="panel panel-info">
	            <div style="padding: 0" class="panel-heading">
					<h3 class="panel-title" style="font-size: 20px"><s:property value="user.name" /></h3>
	            </div>
	            <div style="padding: 0" class="panel-body">
					<div style="padding: 0" class="row">
						<div style="padding: 0" class="col-md-6 col-lg-6 " align="center"> 
							<img alt="User Pic" src="<s:url value="/resources/user/%{user.avatar}"/>" class="img-circle img-responsive userPic"> 
						</div>
						<div style="padding: 0" class=" col-md-18 col-lg-18 "> 
							<table class="table table-user-information">
								<tbody>
									<tr>
										<td>Giới tính:</td>
										<td><s:property value="user.gender" /></td>
									</tr>
									<tr>
										<td>Ngày sinh:</td>
										<td><s:property value="user.birthday" /></td>
									</tr>
									<tr>
										<td>Địa chi:</td>
										<td><s:property value="user.address" /></td>
									</tr>
									<tr>
										<td>Loại tài khoản:</td>
										<td><s:property value="user.user_type.name" /></td>
									</tr>
									<tr>
										<td>Ngày tạo:</td>
										<td><s:property value="user.created_day" /></td>
									</tr>
									<tr>
										<td>Email:</td>
										<td><s:property value="user.email" /></td>
									</tr>
									<tr>
										<td>Khả năng âm nhạc:</td>
										<td><s:property value="user.ability" /></td>
									</tr>
									<tr>
										<td>About me:</td>
										<td><s:property value="user.description" /></td>
									</tr>
								</tbody>
							</table>
						</div>
	            	</div>
					<div style="padding: 0" class="panel-footer">
                        <span class="pull-right">
                          <button class="btn" data-dismiss="modal">Đóng</button>
                          <button class="btn btn-primary">Cập nhật thông tin</button>
                        </span>
					</div>
				</div>
	        </div>
		</div>
    </div>
</div>
