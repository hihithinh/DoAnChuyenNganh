<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="/MuzConnect/resources/styles/main.css">	

<!-- javascript -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<body>
<!-- <div id="myModal" class="modal fade" tabindex="-1" role="dialog"> -->
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">
				<div class="container-fluid content">
				    <div class="row">
				        <div class="col-xs-24 col-sm-24 col-md-12 col-lg-12 " >
					        <div class="panel panel-info">
					            <div class="panel-heading">
									<h3 class="panel-title"><s:property value="user.name" /></h3>
					            </div>
					            <div class="panel-body">
									<div class="row">
										<div class="col-md-6 col-lg-6 " align="center"> 
											<img alt="User Pic" src="<s:url value="/resources/user/%{user.avatar}"/>" class="img-circle img-responsive userPic"> 
										</div>
										<div class=" col-md-18 col-lg-18 "> 
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
									<div class="panel-footer">
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
			</div>
		</div>
	</div>
<!-- </div> -->
</body>
</html>