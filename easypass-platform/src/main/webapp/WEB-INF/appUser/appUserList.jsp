<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/common/head.jsp"%>
</head>
<body class="nav-md">
	<div class="container body">
		<div class="main_container">
			<%@ include file="/WEB-INF/common/left.jsp"%>
			<%@ include file="/WEB-INF/common/top.jsp"%>
			<!-- page content -->
			<div class="right_col" role="main" id="main">
				<div class="x_panel">
					<div class="x_title">
						<h2>APP用户管理</h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<form role="form" id="searchForm">
							<div class="container">
								<div class="row">
									<div class="col-lg-2">
										<input class="form-control input-sm" placeholder="用户名" name="userName"/>
									</div>
									<div class="col-lg-2" >
										<input class="form-control input-sm" placeholder="电话" name="phone"/>
									</div>
									<div class="col-lg-2" >
										<input class="form-control input-sm" placeholder="邮箱" name="email"/>
									</div>
									<div class="col-lg-2">
										<select name="sex" class="form-control input-sm">
											<option value="">--请选择性别--</option>
											<option value="0">未知</option>
											<option value="1">男</option>
											<option value="2">女</option>
										</select>
									</div>
									<div class="col-lg-2">
										<select name="status" class="form-control input-sm" id="status">
											<option value="">--请选择账号状态--</option>
											<option value="0">启用</option>
											<option value="1">禁用</option>
										</select>
									</div>
									<div class="col-lg-2 ">
<!-- 										<button type="button" class="btn btn-success btn-sm" onclick="addBtn();">新增</button> -->
										<button type="button" class="btn btn-primary btn-sm" onclick="search();">搜索</button>
									</div>
								</div>
							</div>
						</form>
						<div class="table-responsive">
							<table id="appUsers" class="table table-bordered table-striped table-hover" 
							  cellspacing="0" width="100%">
								<thead>
									<tr class="mytableHead">
										<th>用户名</th>
										<th>性别</th>
										<th>电话</th>
										<th>邮箱</th>
										<th>专业</th>
										<th>状态</th>
										<th>创建时间</th>
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
	
	<div id="appUserModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="false" 
		aria-hidden="true" >
		<div class="modal-dialog modal-md" style="width: 900px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					
					<h4 class="modal-title" id="modelTitle">
						<!--<c:if test="${appUser.id !=null }">编辑APP用户信息</c:if>
						<c:if test="${appUser.id ==null }">新增APP用户信息</c:if>-->
					</h4>
				</div>
				<div class="modal-body">
					<form role="form" novalidate id="userForm" class="form-horizontal">
						<div class="form-group item" >
						    <label for="fullName2" class="col-sm-3 control-label">用户名：</label>
						    <div class="col-sm-6">
						    	<input type="hidden" value="" name="id" id="id">
						    	<input type="text" class="form-control required" id="userName" name="userName" 
						    		placeholder="请输入用户名" required='required' data-validate-length-range="0,15">
						    </div>
						    <div class="col-sm-3">
						    	<label class="control-label" style="color: #ccc;">*必填项</label>
						     </div>
						</div>
						<div class="form-group item">
						    <label for="password1" class="col-sm-3 control-label">性别：</label>
						    <div class="col-sm-6  control-label" style="text-align:left;">
						    	<select name="sex" class="form-control input-sm" id="sex">
									<option value="">--请选择性别--</option>
									<option value="0">未知</option>
									<option value="1">男</option>
									<option value="2">女</option>
								</select>
						    </div>
<!-- 						    <div class="col-sm-3"> -->
<!-- 						    	<label class="control-label" style="color: #ccc;">*必填项</label> -->
<!-- 						     </div> -->
						</div>
						<div class="form-group item" >
						    <label for="fullName2" class="col-sm-3 control-label">专业：</label>
						    <div class="col-sm-6">
						    	<select class="form-control" name="major" id="major" >
									<option value="">--请选择专业--</option>
									<c:if test="${classifyList!=null}">
										<c:forEach items="${classifyList}" var="classify">
											<option value="${classify.id}" <c:if test="${course.classifyId==classify.id}">selected="selected"</c:if>>${classify.name}</option>
										</c:forEach>
									</c:if>
								</select>
						    </div>
<!-- 						    <div class="col-sm-3"> -->
<!-- 						    	<label class="control-label" style="color: #ccc;">*必填项</label> -->
<!-- 						     </div> -->
						</div>
						<div class="form-group item">
						    <label for="username2" class="col-sm-3 control-label">电话：</label>
						    <div class="col-sm-6">
						    	<input type="text" class="form-control" id="phone2" name="phone" 
						    		placeholder="请输入电话号码" >
						    </div>
<!-- 						    <div class="col-sm-3"> -->
<!-- 						    	<label class="control-label" style="color: #ccc;">*必填项</label> -->
<!-- 						     </div> -->
						</div>
						<div class="form-group item">
						    <label for="username2" class="col-sm-3 control-label">邮箱：</label>
						    <div class="col-sm-6">
						    	<input type="email" class="form-control" id="email" name="email" 
						    		placeholder="请输入邮箱" data-validate-length-range="0,20">
						    </div>
<!-- 						    <div class="col-sm-3"> -->
<!-- 						    	<label class="control-label" style="color: #ccc;">*必填项</label> -->
<!-- 						     </div> -->
						</div>
						
						<div class="form-group item">
						    <label for="phone" class="col-sm-3 control-label">账号状态：</label>
						    <div class="col-sm-6  control-label" style="text-align: left;">
						    	<input type="radio"   value="0" name="status" checked="checked" id="enableStatus" >启用
								<input type="radio"   value="1" name="status" id="disableStatus">禁用
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
		$("input[name='status']").change(function(){
			$("input[name='status']").removeAttr("checked");
			$(this).prop("checked",true);//添加checked属性
			$(this).attr("checked","checked");//让radio选中
		});
		
		$("#appUsers").DataTable({
		    ajax: {
		        url: domain+'admin/appUser/getAppUserList',
		        type: 'POST',
		        beforeSend :function(xmlHttp){ 
		            xmlHttp.setRequestHeader("If-Modified-Since","0"); 
		            xmlHttp.setRequestHeader("Cache-Control","no-cache");
		         },
		        cache:false,
		        data: function(d){$.extend(d,serializeObject($("#searchForm")))}
		    },
		    columns: [
                {"data":'userName'},    
		        {"data":'sexStr'},
		        {"data":'phone'},
		        {"data":'email'},
		        {"data":'majorName'},
		        {"data":'status',"render":optStatus},
		        {"data":'createTime'},
		        {"data":'id',"render":optRender}
		    ]
		});
		
		$("#searchForm input").keydown(function(event){
			if(event.keyCode == 13){
				search();
			}
		});
		
		//下拉触发
		$("#searchForm select").change(search);
	});
	
	/* status中的启用状态 status */
	function optStatus(data, type, row, meta){
		if(row.status==0){
			return "启用";
		}else{
			return "禁用";
		}
	}
	
	/*新增*/
	function addBtn(){
		$("#userForm")[0].reset();
		$("#modelTitle").text("新增APP用户信息");
		$('#appUserModal').modal("show");
	}
	
	/*编辑*/
	function modifyUser(index){
		var row = $('#appUsers').DataTable().rows().data()[index];
		/* var classifyList=null;
		$.ajax({
			type:"POST",
			url: domain+"admin/provincialCity/getProvincialCity?time="+(new Date()).getTime(),
			dataType:"json",
			async:false,
			success:function(result){
				classifyList=result.data.classifyList;
			}
		}); */
		$("#modelTitle").text("编辑APP用户信息");
		$("#id").val(row.id);
		$("#userName").val(row.userName);
		$("#phone2").val(row.phone);
		$("#email").val(row.email);
		
		$("#sex").val(row.sex); 
		
		if(row.sexStr == '男'){
			$("#sex").find("option[text='男']").attr("selected",true);
		}else if(row.sexStr == '女'){
			$("#sex").find("option[text='女']").attr("selected",true);
		}else{
			$("#sex").find("option[text='未知']").attr("selected",true);
		}
		
		$("#major").val(row.major);
		/* var classifys=classifyList;
		if(classifys.length>0){
			for(var i = 0;i<classifys.length;i++){
				if(row.major==classifys[i].id){
					$("#major").append("<option value='"+classifys[i].id+"' selected = 'selected' >"+classifys[i].name+"</option>");
				}else{
					$("#major").append("<option value='"+classifys[i].id+"' >"+classifys[i].name+"</option>");
				}
			}
		} */
		
		$("input[name='status']").removeAttr("checked");
		if(row.status==1){
			$("#disableStatus").attr("checked","checked");
			$("#disableStatus").prop("checked",true);
		}else{
			$("#enableStatus").attr("checked","checked");
			$("#enableStatus").prop("checked",true);
		}
		
		$('#appUserModal').modal("show");
	}
	
	/*保存按钮*/
	function saveUser(){
		
		if( !validator.checkAll( $("#userForm") ) ){
			return;
		}
		var id=$("#id").val();
		
		var url;
		if(null==id || id == 0){//新增
			url=domain+"admin/appUser/addAppUser?time="+(new Date()).getTime();
		}else{
			url=domain+"admin/appUser/updateAppUser?time="+(new Date()).getTime();
		}
		$.ajax({
			url: url,
			dataType: "json",
			data:$("#userForm").serialize(),
			success: function(result){
				if(result.status == "0"){
					swal({title:'',text:"保存成功"},function(){
						$('#appUserModal').modal('hide');
						searchCurrentPage();
					});
				}else{
					swal({title:'',text:result.msg,type:"error"});
				}
			}
		});
	}
	function optRender(data, type, row, meta){
		var but = '<a class="btn btn-info btn-xs" href="javascript:modifyUser('+meta.row+')"><i class="fa fa-pencil"></i>编辑</a>';
		if(row.status==1){
			but = but + '<a class="btn btn-primary btn-xs" href="javascript:enableUser('+row.id+')">启用</a>';
		}else if(row.status==0){
			but = but + '<a class="btn btn-primary btn-xs" href="javascript:disableUser('+row.id+')">禁用</a>';
		}
		
		but = but + '<a class="btn btn-danger btn-xs" href="javascript:resetPassword('+row.id+')">重置密码</a>';
		return but;
	}
	
	/*启用*/
	function enableUser(id){
		$.ajax({
			type:"POST",
			data:{
				userId:id
			},
			url: domain+"admin/appUser/enableAppUser?time="+(new Date()).getTime(),
			dataType: "json",
			success: function(result){
				if(result.status == "0"){
					swal({title:'',text:"启用成功"},function(){
						search();
					});
				}else{
					swal({title:'',text:result.msg,type:"error"});
				}
			}
		});
	}
	/*禁用*/
	function disableUser(id){
		$.ajax({
			type:"POST",
			data:{
				userId:id
			},
			url: domain+"admin/appUser/disableAppUser?time="+(new Date()).getTime(),
			dataType: "json",
			success: function(result){
				if(result.status == "0"){
					swal({title:'',text:"禁用成功"},function(){
						searchCurrentPage();
					});
				}else{
					swal({title:'',text:result.msg,type:"error"});
				}
			}
		});
	}
	/*重置密码*/
	function resetPassword(id){
		$.ajax({
			type:"POST",
			data:{
				userId:id
			},
			url: domain+"admin/appUser/resetAppUserPassword?time="+(new Date()).getTime(),
			dataType: "json",
			success: function(result){
				if(result.status == "0"){
					swal({title:'',text:"重置密码成功"},function(){
						searchCurrentPage();
					});
				}else{
					swal({title:'',text:result.msg,type:"error"});
				}
			}
		});
	}
	
	function search(){
		$('#appUsers').DataTable().ajax.reload(function(d){$.extend(d,serializeObject($("#searchForm")))}, true);;
	}
	function searchCurrentPage(){
		$('#appUsers').DataTable().ajax.reload(function(d){$.extend(d,serializeObject($("#searchForm")))}, false);;
	}
	function closeModal(){
		$('#appUserModal').modal("hide");
	}
	</script>
</body>
</html>