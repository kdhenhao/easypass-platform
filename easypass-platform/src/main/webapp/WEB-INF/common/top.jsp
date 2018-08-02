<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="top_nav">
	<div class="nav_menu">
		<nav>
			<div class="nav toggle">
				<a id="menu_toggle"><i class="fa fa-bars"></i></a>
			</div>
			<ul class="nav navbar-nav navbar-right">
				<li class="">
					<a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
						<!-- <img src="${domainImg}/${sessionScope.sessionUser.avatar}" alt=""> -->${sessionScope.login_user.fullName}&nbsp;<span class="fa fa-angle-down"></span>
					</a>
					<ul class="dropdown-menu dropdown-usermenu pull-right">
						<li><a data-toggle="modal" href="#passwordModal"><i class="fa fa-user pull-right"></i>修改密码</a></li>
						<li><a href="${ctx}logout"><i class="fa fa-sign-out pull-right"></i>退出</a></li>
					</ul>
				</li>
				<!-- <li role="presentation" class="dropdown">
					<a href="javascript:;" class="dropdown-toggle info-number" data-toggle="dropdown"
						aria-expanded="false"> <i class="fa fa-envelope-o"></i> <span class="badge bg-green">6</span>
					</a>
					<ul id="menu1" class="dropdown-menu list-unstyled msg_list"
						role="menu">
						<li><a> <span class="image"><img
									src="${ctx}resources/images/img.jpg" alt="Profile Image" /></span>
								<span> <span>John Smith</span> <span class="time">3
										mins ago</span>
							</span> <span class="message"> Film festivals used to be
									do-or-die moments for movie makers. They were where... </span>
						</a></li>
						<li><a> <span class="image"><img
									src="${ctx}resources/images/img.jpg" alt="Profile Image" /></span>
								<span> <span>John Smith</span> <span class="time">3
										mins ago</span>
							</span> <span class="message"> Film festivals used to be
									do-or-die moments for movie makers. They were where... </span>
						</a></li>
						<li><a> <span class="image"><img
									src="${ctx}resources/images/img.jpg" alt="Profile Image" /></span>
								<span> <span>John Smith</span> <span class="time">3
										mins ago</span>
							</span> <span class="message"> Film festivals used to be
									do-or-die moments for movie makers. They were where... </span>
						</a></li>
						<li><a> <span class="image"><img
									src="${ctx}resources/images/img.jpg" alt="Profile Image" /></span>
								<span> <span>John Smith</span> <span class="time">3
										mins ago</span>
							</span> <span class="message"> Film festivals used to be
									do-or-die moments for movie makers. They were where... </span>
						</a></li>
						<li>
							<div class="text-center">
								<a> <strong>See All Alerts</strong> <i
									class="fa fa-angle-right"></i>
								</a>
							</div>
						</li>
					</ul>
				</li> -->
			</ul>
		</nav>
	</div>
</div>
<div id="passwordModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="false"
	aria-hidden="true">
	<div class="modal-dialog modal-md">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">×</span>
				</button>
				<h4 class="modal-title" id="myModalLabel2">修改密码</h4>
			</div>
			<div class="modal-body">
				<form role="form" novalidate id="passwordForm" class="form-horizontal">
					<div class="form-group item">
					    <label for="oldPassword" class="col-sm-3 control-label">原始密码：</label>
					    <div class="col-sm-6">
					    	<input type="hidden" name="username" value="${sessionScope.login_user.username}"/>
					    	<input type="password" class="form-control" id="currentPwd" name="currentPwd" placeholder="原始密码" required='required'>
						</div>
					</div>
					<div class="form-group item">
					    <label for="newPassword" class="col-sm-3 control-label">新密码：</label>
					    <div class="col-sm-6">
					    	<input type="password" class="form-control" id="newPwd" name="newPwd" placeholder="新密码" required="required" data-validate-length-range="6,10">
						</div>
					</div>
					<div class="form-group item">
					    <label for="newPassword2" class="col-sm-3 control-label">再次确认：</label>
					    <div class="col-sm-6">
					    	<input type="password" class="form-control" id="newPassword2" placeholder="确认密码" data-validate-linked='newPwd' required='required'>
						</div>
					</div>
				</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
				<button type="button" class="btn btn-success" onclick="updatePassword();">保存</button>
			</div>
		</div>
	</div>
</div>
<script>
function updatePassword(){
	if( !validator.checkAll( $("#passwordForm") ) ){
		return;
	}
	swal({"title":"","text":"正在处理……请稍后","showConfirmButton":false});
	$.ajax({
		"url": ctx+"admin/systemUser/updatePwd",
		"data": $("#passwordForm").serialize(),
		"dataType":"json",
		"success":function(result){
			if(result.status == "0"){
				swal({title:'',text:"修改成功"},function(){
					$('#passwordModal').modal('hide');
				});
			}else{
				swal({title:'',text:result.msg,type:"error"});
			}
		}
	});
}
</script>