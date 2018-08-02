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
						<h2>关口管理</h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<div class="container">
							<div class="row">
								<div class="col-lg-3 col-sm-3 col-xs-6">
									<button type="button" class="btn btn-success btn-sm" onclick="addPort();">新增</button>
								</div>
							</div>
						</div>
						<div class="table-responsive">
							<table id="users" class="table table-bordered table-striped table-hover" 
							  cellspacing="0" width="100%">
								<thead>
									<tr class="mytableHead">
										<th class="myRowNumber"></th>
										<th>关口名称</th>
										<th>状态</th>
										<th>修改人</th>
										<th>修改时间</th>
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
	
	<div id="portModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="false"
		aria-hidden="true">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="modelTitle">关口管理</h4>
				</div>
				<div class="modal-body">
					<form role="form" novalidate id="portForm" class="form-horizontal">
						<div class="form-group item">
						    <label for="oldPassword" class="col-sm-3 control-label">关口名称：</label>
						    <div class="col-sm-6">
						    	<input type="hidden" name="id" id="portId">
						    	<input type="text" class="form-control" id="portName" name="portName" 
						    		placeholder="关口名称" required='required' data-validate-length-range="0,20">
						    </div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" onclick="closeModal();">取消</button>
					<button type="button" class="btn btn-success" onclick="saveOrUpdatePort();">保存</button>
				</div>
			</div>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/common/script.jsp"%>
	<script>
	$(function(){
		var t = $("#users").DataTable({
		    ajax: {
		        url: ctx+'admin/port/findPorts',
		        type: 'POST'
		    },
		    columns: [
		        { "data":null,"render":rowNumberRender},
                { "data":'portName'},
                { "data":'status',"render":statusRender},
		        { "data":'modifyTm'},
		        { "data":'modifyBy'},
		        { "data":'id',"render":optRender}
		    ]
		});
	});
	function searchCurrentPage(){
		$('#users').DataTable().ajax.reload(function(d){$.extend(d,serializeObject($("#searchForm")))}, false);;
	}
	function optRender(data, type, row, meta){
		var but = '<a class="btn btn-info btn-xs" href="javascript:showDetail('+meta.row+')"><i class="fa fa-pencil"></i>编辑</a>';
		if(row.portStatus == "0"){
			but = but + '<a class="btn btn-danger btn-xs" href="javascript:stopById('+row.id+')"><i class="fa fa-minus"></i>停用</a>';
		}else{
			but = but + '<a class="btn btn-warning btn-xs" href="javascript:startById('+row.id+')"><i class="fa fa-plus"></i>启用</a>';
		}
		return but;
	}
	function statusRender(data, type, row, meta){
		if(row.portStatus == "0"){
			return "正常";
		}else{
			return "停用";
		}
	}
	
	function showDetail(index){
		var row = $('#users').DataTable().rows().data()[index];
		$("#portForm input").val("").parent().siblings(".alert").remove();
		$("#portForm input").parents(".item").removeClass("bad");
		$("#portName").val(row.portName);
		$("#portId").val(row.id);
		$('#portModal').modal("show");
	}
	function addPort(){
		$("#portForm input").val("").parent().siblings(".alert").remove();
		$("#portForm input").parents(".item").removeClass("bad");
		$('#portModal').modal("show");
	}
	function closeModal(){
		$('#portModal').modal("hide");
	}
	function saveOrUpdatePort(){
		if( !validator.checkAll( $("#portForm") ) ){
			return;
		}
		$.ajax({
			url: ctx+"admin/port/saveOrUpdate?time="+(new Date()).getTime(),
			dataType: "json",
			data:$("#portForm").serialize(),
			success: function(result){
				if(result.status == "0"){
					swal({title:'',text:"保存成功"},function(){
						$('#portModal').modal("hide");
						searchCurrentPage();
					});
				}else{
					swal({title:'',text:result.msg,type:"error"});
				}
			}
		});
	}

	function startById(id){
		$.ajax({
			url: ctx+"admin/port/startById?id="+id+"&time="+(new Date()).getTime(),
			dataType: "json",
			success: function(result){
				if(result.status == "0"){
					swal({title:'',text:"启用成功"},function(){
						searchCurrentPage();
					});
				}else{
					swal({title:'',text:result.msg,type:"error"});
				}
			}
		});
	}
	function stopById(id){
		swal({ "title": "", "text": "确定要停用该端口" ,"type": "warning","showCancelButton":true},function(isConfirm){
			if(isConfirm){
				$.ajax({
					url: ctx+"admin/port/stopById?id="+id+"&time="+(new Date()).getTime(),
					dataType: "json",
					success: function(result){
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
	</script>
</body>
</html>