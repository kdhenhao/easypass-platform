<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/common/head.jsp"%>
</head>
<body class="${cookie.bodyClass.value}">
	<div class="container body">
		<div class="main_container">
			<%@ include file="/WEB-INF/common/left.jsp"%>
			<%@ include file="/WEB-INF/common/top.jsp"%>
			<!-- page content -->
			<div class="right_col" role="main" id="main">
				<div class="x_panel">
					<div class="x_title">
						<h2>帐号管理</h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<form role="form" id="searchForm">
							<div class="container">
								<div class="row">
									<div class="col-lg-3">
										<input class="form-control input-sm" placeholder="帐号" name="map['username']"/>
									</div>
									<div class="col-lg-3">
										<input class="form-control input-sm" placeholder="帐号名称" name="map['fullName']"/>
									</div>
									<div class="col-lg-3">
										<select class="form-control input-sm" name="map['roleId']">
											<option value="">--请选择--</option>
											<c:forEach items="${roles}" var="opt">
												<option value="${opt.id}">${opt.text}</option>
											</c:forEach>
										</select>
									</div>
									<div class="col-lg-3">
										<button type="button" class="btn btn-success btn-sm" onclick="add();">新增</button>
										<button type="button" class="btn btn-primary btn-sm" onclick="search();">搜索</button>
									</div>
								</div>
							</div>
						</form>
						<div class="table-responsive">
							<table id="users" class="table table-bordered table-striped table-hover" 
							  cellspacing="0" width="100%">
								<thead>
									<tr class="mytableHead">
										<th>角色名称</th>
										<th>账号</th>
										<th>账号名称</th>
										<th>手机号码</th>
										<th>备注</th>
										<th>操作</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
				</div>
			</div>
			<!-- /page content -->
			<%@ include file="/WEB-INF/common/foot.jsp"%>
		</div>
	</div>
	
	<div id="userModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="false"
		aria-hidden="true">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="modelTitle">新增用户</h4>
				</div>
				<div class="modal-body">
					<form role="form" novalidate id="userForm" class="form-horizontal">
						<div class="form-group item">
						    <label for="fullName2" class="col-sm-3 control-label">帐号名称：</label>
						    <div class="col-sm-6">
						    	<input type="hidden" value="" name="id" id="userId">
						    	<input type="text" class="form-control required" id="fullName2" name="fullName" 
						    		placeholder="帐号名称" required='required' data-validate-length-range="0,20">
						    </div>
						</div>
						<div class="form-group item">
						    <label for="username2" class="col-sm-3 control-label">帐号：</label>
						    <div class="col-sm-6">
						    	<input type="text" class="form-control required" id="username2" name="username" 
						    		placeholder="帐号" required="required" data-validate-length-range="5,10">
						    </div>
						</div>
						<div class="form-group item">
						    <label for="password1" class="col-sm-3 control-label">密码：</label>
						    <div class="col-sm-6">
						    	<input type="password" class="form-control required" id="password1" name="password"
						    		placeholder="密码" required="required" data-validate-length-range="6,10">
						    </div>
						</div>
						<div class="form-group item">
						    <label for="password2" class="col-sm-3 control-label">确认密码：</label>
						    <div class="col-sm-6">
						    	<input type="password" class="form-control required" id="password2"
						    		placeholder="再次输入" required="required" data-validate-linked='password'>
						    </div>
						</div>
						<div class="form-group item">
						    <label for="roleId2" class="col-sm-3 control-label">角色：</label>
						    <div class="col-sm-6">
						    	<select class="form-control required" id="roleId2" name="roleId" required="required">
						    		<option value="">--请选择--</option>
									<c:forEach items="${roles}" var="opt">
										<option value="${opt.id}">${opt.text}</option>
									</c:forEach>
								</select>
						    </div>
						</div>
						<div class="form-group item">
						    <label for="phone" class="col-sm-3 control-label">手机号码：</label>
						    <div class="col-sm-6">
						    	<input type="text" class="form-control optional required" id="phone" name="phone" pattern="phone"
						    		placeholder="手机号码" >
						    </div>
						</div>
						<div class="form-group item">
						    <label for="email" class="col-sm-3 control-label">邮箱：</label>
						    <div class="col-sm-6">
						    	<input type="email" class="form-control required" id="email" name="email"
						    		placeholder="email" data-validate-length-range="0,20">
						    </div>
						</div>
						<div class="form-group item">
						    <label for="remark" class="col-sm-3 control-label">备注：</label>
						    <div class="col-sm-6">
						    	<input type="text" class="form-control" id="remark" name="remark"
						    		placeholder="备注" data-validate-length-range="0,100">
						    </div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" onclick="closeModal();">取消</button>
					<button type="button" class="btn btn-success" onclick="saveUser();">保存</button>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/common/script.jsp"%>
	<script>
	$(function(){
		
		$("#users").DataTable({
		    ajax: {
		        url: ctx+'admin/systemUser/findUsers',
		        type: 'POST',
		        data: function(d){$.extend(d,serializeObject($("#searchForm")))}
		    },
		    columns: [
                { "data":'roleName'},    
		        { "data":'username'},
		        { "data":'fullName'},
		        { "data":'phone',},
		        { "data":'remark'},
		        { "data":'id',"render":optRender}
		    ]
		});
		$("#searchForm input").keydown(function(event){
			if(event.keyCode == 13){
				search();
			}
		});
		$("#searchForm select").change(search);
		
	});
	function optRender(data, type, row, meta){
		var but = '<a class="btn btn-info btn-xs" href="javascript:modify('+meta.row+')"><i class="fa fa-pencil"></i>编辑</a>';
		but = but + '<a class="btn btn-primary btn-xs" href="javascript:resetPw('+row.id+',\''+row.fullName+'\')"><i class="fa fa-minus"></i>重置密码</a>';
		but = but + '<a class="btn btn-danger btn-xs" href="javascript:deleteById('+row.id+',\''+row.fullName+'\')"><i class="fa fa-remove"></i>删除</a>';
		return but;
	}
	function search(){
		$('#users').DataTable().ajax.reload(function(d){$.extend(d,serializeObject($("#searchForm")))}, true);;
	}
	function searchCurrentPage(){
		$('#users').DataTable().ajax.reload(function(d){$.extend(d,serializeObject($("#searchForm")))}, false);;
	}
	function closeModal(){
		$('#userModal').modal("hide");
	}
	
	function add(){
		$("#password1").attr("required",true);
		$("#password2").attr("required",true);
		$("#password1").parent().parent().show();
		$("#password2").parent().parent().show();
		
		$("#userForm input").val("").parent().siblings(".alert").remove();
		$("#userForm input").parents(".item").removeClass("bad");
		$('#userModal').modal("show");
	}
	function modify(index){
		$("#password1").attr("required",false);
		$("#password2").attr("required",false);
		$("#password1").parent().parent().hide();
		$("#password2").parent().parent().hide();

		var row = $('#users').DataTable().rows().data()[index];
		$("#userId").val(row.id);
		$("#fullName2").val(row.fullName);
		$("#username2").val(row.username);
		$("#roleId2").val(row.roleId);
		$("#phone").val(row.phone);
		$("#email").val(row.email);
		$("#remark").val(row.remark);
		
		$("#userForm input").parent().siblings(".alert").remove();
		$("#userForm input").parents(".item").removeClass("bad");
		$('#userModal').modal("show");
	}
	
	function deleteById(id,name){
		swal({ "title": "", "text": "确定要删除该用户" ,"type": "warning","showCancelButton":true},function(isConfirm){
			if(isConfirm){
				$.ajax({
					url: ctx+"admin/systemUser/deleteById?id="+id+"&time="+(new Date()).getTime(),
					dataType: "json",
					success: function(result){
						if(result.status == "0"){
							swal({title:'',text:"保存成功"},function(){
								searchCurrentPage();
							});
						}else{
							swal({title:'',text:result.msg,type:"error"});
						}
					}
				});
			}
		});
	}
	
	function resetPw(id,name){
		swal({ "title": "", "text": "确定要重置该用户密码为“123456”吗?" ,"type": "warning","showCancelButton":true},function(isConfirm){
			if(isConfirm){
				$.ajax({
					url: ctx+"admin/systemUser/resetPassword.htm?id="+id+"&name="+name+"&time="+(new Date()).getTime(),
					dataType: "json",
					success: function(result){
						if(result.status == "0"){
							swal({title:'',text:"保存成功"},function(){
								searchCurrentPage();
							});
						}else{
							swal({title:'',text:result.msg,type:"error"});
						}
					}
				});
			}
		});
	}
	
	function saveUser(){
		if( !validator.checkAll( $("#userForm") ) ){
			return;
		}
		swal({"title":"","text":"正在处理……请稍后","showConfirmButton":false});
		$.ajax({
			url: ctx+"admin/systemUser/saveUser?time="+(new Date()).getTime(),
			dataType: "json",
			data:$("#userForm").serialize(),
			success: function(result){
				if(result.status == "0"){
					swal({title:'',text:"保存成功"},function(){
						$('#userModal').modal('hide');
						search();
					});
				}else{
					swal({title:'',text:result.msg,type:"error"});
				}
			}
		});
	}
	</script>
</body>
</html>