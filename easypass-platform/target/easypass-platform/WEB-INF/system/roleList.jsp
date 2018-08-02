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
						<h2>角色管理</h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<div class="container">
							<div class="row">
								<div class="col-lg-6 col-md-6">
									<div class="row">
										<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
											<button type="button" class="btn btn-success btn-sm" onclick="openModal();">新增</button>
										</div>
									</div>
									<table id="roleTable" class="table table-bordered table-striped table-hover" cellspacing="0" width="100%">
										<thead>
											<tr class="mytableHead">
												<th style="width: 35%;">角色名称</th>
												<th style="width: 35%;">角色代码</th>
												<th style="width: 30%;">操作</th>
											</tr>
										</thead>
									</table>
								</div>
								<div class="col-lg-6 col-md-6">
									<div style="height: 10%;">
										<button type="button" class="btn btn-info btn-sm" onclick="selectAll();">全选</button>
										<button type="button" class="btn btn-default btn-sm" onclick="unSelectAll();">全不选</button>
										<button type="button" class="btn btn-primary btn-sm" onclick="saveRoleAuth();">保存权限</button>
									</div>
									<div id="tree" style="height: 90%;"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /page content -->
			<%@ include file="/WEB-INF/common/foot.jsp"%>
		</div>
	</div>
	<%@ include file="/WEB-INF/common/script.jsp"%>

	<div id="roleModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="false" aria-hidden="true">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="modelTitle">填写角色信息</h4>
				</div>
				<div class="modal-body">
					<form role="form" novalidate id="roleForm" class="form-horizontal">
						<div class="form-group item">
						    <label for="limit" class="col-sm-3 control-label">角色名称：</label>
						    <div class="col-sm-6">
						    	<input id="roleId" name="roleId" value="" type="hidden"/>
						    	<input type="text" class="form-control required" id="roleName" name="roleName" 
						    		placeholder="请输入角色名称" required='required' data-validate-length-range="0,10">
						    </div>
						</div>
						<div class="form-group item">
						    <label for="limit" class="col-sm-3 control-label">角色代码：</label>
						    <div class="col-sm-6">
						    	<input type="text" class="form-control required" id="roleCode" name="roleCode" 
						    		placeholder="请输入角色代码" data-validate-length-range="0,10">
						    </div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" onclick="closeModal();">取消</button>
					<button type="button" class="btn btn-success" onclick="saveRole();">保存</button>
				</div>
			</div>
		</div>
	</div>
	<script>
	$(function(){
		initTable();
		initTree();
	});
	function initTable(){
		var table = $("#roleTable").DataTable({
		    "ajax": {
		        url: ctx+'admin/systemRole/findRoles',
		        type: 'POST'
		    },
		    "columns": [
                { "data":'roleName'},    
		        { "data":'roleCode'},
		        { "data":'id',"render":optRender}
		    ],
		    "dom": "<'row'<'col-sm-12'tr>><'row'<'col-xs-12 col-sm-4'l><'col-xs-12 col-sm-8'p>>",
		    "select": true
		});
		table.on('select', onRoleSelect);
	}
	function optRender(data, type, row, meta){
		var but = '<a class="btn btn-info btn-xs" href="javascript:modify('+meta.row+')"><i class="fa fa-pencil"></i>编辑</a>';
		but = but + '<a class="btn btn-danger btn-xs" href="javascript:deleteById('+row.id+',\''+row.fullName+'\')"><i class="fa fa-remove"></i>删除</a>';
		return but;
	}
	function searchCurrentPage(){
		$('#roleTable').DataTable().ajax.reload(function(d){$.extend(d,serializeObject($("#searchForm")))}, false);;
	}
	
	
	function openModal(){
		$("#roleForm input").val("").parent().siblings(".alert").remove();
		$("#roleForm input").parents(".item").removeClass("bad");
		$('#roleModal').modal("show");
	}
	function modify(index){
		var row = $('#roleTable').DataTable().rows().data()[index];
		if(row != null && row.id != null){
			$("#roleId").val(row.id);
			$("#roleName").val(row.roleName);
			$("#roleCode").val(row.roleCode);
		}
		$('#roleModal').modal("show");
	}
	function closeModal(){
		$('#roleModal').modal("hide");
	}
	
	function saveRole(){
		if( !validator.checkAll( $("#roleForm") ) ){
			return;
		}
		$.ajax({
			"url": ctx + "admin/systemRole/saveRole?time="+ (new Date()).getTime(),
			"data": $("#roleForm").serialize(),
			"dataType": "json",
			"success": function(result){
				if(result.status == "0"){
					swal({title:'',text:"保存成功"},function(){
						$('#roleModal').modal('hide');
						searchCurrentPage();
					});
				}else{
					swal({title:'',text:result.msg,type:"error"});
				}
			}
		});
	}
	
	function deleteById(id){
		swal({ "title": "", "text": "确认想要删除该角色吗?" ,"type": "warning","showCancelButton":true},function(isConfirm){
			if(isConfirm){
				$.ajax({
					"url": ctx + "admin/systemRole/deleteRole?time="+ (new Date()).getTime()+"&id="+id,
					"dataType": "json",
					"success": function(result){
						if(result.status == "0"){
							searchCurrentPage();
						}else{
							swal({title:'',text:result.msg,type:"error"});
						}
					}
				});
			}
		});
	}
	
	function onRoleSelect(e, dt, type, indexes){
		var rowData = $('#roleTable').DataTable().rows().data()[indexes[0]];
        var roleId = rowData.id;
        
		unSelectAll();
		swal({"title":"","text":"正在处理……请稍后","showConfirmButton":false});
		$.ajax({
			url: ctx+"admin/systemRole/findRoleAuth?roleId="+roleId,
			dataType: "json",
			success: function(result){
				swal.close();
				var array = result.data.list;
				for(var i=0;i<array.length;i++){
					var nodes = $('#tree').treeview('getEnabled');
					for(var j=0;j<nodes.length;j++){
						if(array[i] == nodes[j].id){
							$('#tree').treeview('checkNode', [ nodes[j], { silent: true }]);
						}
					}
				}
			}
		});
	}
	
	function initTree(){
		$.ajax({
			"url": ctx + "admin/systemRole/findAuthTree?time="+ (new Date()).getTime(),
			"dataType": "json",
			"success": function(result){
				$('#tree').treeview({"data": result,"showCheckbox":true,"onNodeChecked":onAuthCheck,"onNodeUnchecked":onAuthUnCheck});
			}
		});
	}
	
	function selectAll(){
		$('#tree').treeview('checkAll', { silent: true });
	}
	
	function unSelectAll(){
		$('#tree').treeview('uncheckAll', { silent: true });
	}
	
	function onAuthCheck(event,node){
		var parent = $('#tree').treeview('getParent', node);
		if(parent && parent.id){
			$('#tree').treeview('checkNode', [ parent, { silent: true }]);
		}else{
			if(node.nodes){
				var children = node.nodes;
				for(var i=0;i<children.length;i++){
					$('#tree').treeview('checkNode', [ children[i].nodeId, { silent: true }]);
				}
			}
		}
	}
	
	function onAuthUnCheck(event,node){
		var parent = $('#tree').treeview('getParent', node);
		if(parent && parent.id){
			var siblings = $('#tree').treeview('getSiblings', node);
			var flag = true;
			for(var i=0;i<siblings.length;i++){
				if(siblings[i].state.checked){
					flag = false;
					break;
				}
			}
			if(flag){
				$('#tree').treeview('uncheckNode', [ parent, { silent: true }]);
			}
		}else{
			if(node.nodes){
				var children = node.nodes;
				for(var i=0;i<children.length;i++){
					$('#tree').treeview('uncheckNode', [ children[i].nodeId, { silent: true }]);
				}
			}
		}
	}
	
	function saveRoleAuth(){
		var nodes = $('#tree').treeview('getEnabled');
		var rows = $('#roleTable').DataTable().rows({selected: true}).data();
		if(rows.length == 0){
			swal("","请先选择角色","error");
			return;
		}
		var roleId = rows[0].id;
		var array = "";
		for(var i=0;i<nodes.length;i++){
			if(nodes[i].state.checked){
				array = array+ nodes[i].id+",";
			}
		}
		if(array != ""){
			array = array.substring(0,array.length-1);
		}
		swal({"title":"","text":"正在处理……请稍后","showConfirmButton":false});
		$.ajax({
			url: ctx+"admin/systemRole/saveAuth?id="+roleId,
			data: "array="+array,
			dataType: "json",
			success: function(result){
				if(result.status == "0"){
					swal({title:'',text:"保存成功"});
				}else{
					swal({title:'',text:result.msg,type:"error"});
				}
			}
		});
	}
	</script>
</body>
</html>