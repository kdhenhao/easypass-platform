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
						<h2>网点管理</h2>
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
										<th>网点名称</th>
										<th>网点负责人</th>
										<th>网点地址</th>
										<th>联系方式</th>
										<th>状态</th>
										<th style="width: 15%;">操作</th>
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
	
	<div id="pointModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="false"
		aria-hidden="true">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="modelTitle">网点管理</h4>
				</div>
				<div class="modal-body">
					<form role="form" novalidate id="pointForm" class="form-horizontal">
						<div class="form-group item">
						    <label for="pointName" class="col-sm-3 control-label">网点名称：</label>
						    <div class="col-sm-6">
						    	<input type="hidden" name="id" id="pointId">
						    	<input type="text" class="form-control" id="pointName" name="pointName" 
						    		placeholder="网点名称" required='required' data-validate-length-range="0,20">
						    </div>
						</div>
						<div class="form-group item">
						    <label for="pointCharger" class="col-sm-3 control-label">网点负责人：</label>
						    <div class="col-sm-6">
						    	<input type="text" class="form-control" id="pointCharger" name="pointCharger" 
						    		placeholder="网点负责人" required='required' data-validate-length-range="0,20">
						    </div>
						</div>
						<div class="form-group item">
						    <label for="pointAddress" class="col-sm-3 control-label">网点地址：</label>
						    <div class="col-sm-6">
						    	<input type="text" class="form-control" id="pointAddress" name="pointAddress" 
						    		placeholder="网点地址" required='required' data-validate-length-range="0,50">
						    </div>
						</div>
						<div class="form-group item">
						    <label for="pointName" class="col-sm-3 control-label">联系方式：</label>
						    <div class="col-sm-6">
						    	<input type="text" class="form-control optional" id="pointContact" name="pointContact" 
						    		placeholder="联系方式" data-validate-length-range="0,20">
						    </div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" onclick="closeModal();">取消</button>
					<button type="button" class="btn btn-success" onclick="saveOrUpdatePoint();">保存</button>
				</div>
			</div>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/common/script.jsp"%>
	<script>
	$(function(){
		var t = $("#users").DataTable({
		    ajax: {
		        url: ctx+'admin/endpoint/findPoints',
		        type: 'POST'
		    },
		    columns: [
		        { "data":null,"render":rowNumberRender},
                { "data":'pointName'},
                { "data":'pointCharger'},
		        { "data":'pointAddress'},
		        { "data":'pointContact'},
		        { "data":'status',"render":statusRender},
		        { "data":'id',"render":optRender}
		    ]
		});
	});
	function searchCurrentPage(){
		$('#users').DataTable().ajax.reload(function(d){$.extend(d,serializeObject($("#searchForm")))}, false);;
	}
	function optRender(data, type, row, meta){
		var but = '<a class="btn btn-info btn-xs" href="javascript:showDetail('+meta.row+')"><i class="fa fa-pencil"></i>编辑</a>';
		if(row.status == "0"){
			but = but + '<a class="btn btn-danger btn-xs" href="javascript:stopById('+row.id+')"><i class="fa fa-minus"></i>停用</a>';
		}else{
			but = but + '<a class="btn btn-warning btn-xs" href="javascript:startById('+row.id+')"><i class="fa fa-plus"></i>启用</a>';
		}
		return but;
	}
	function statusRender(data, type, row, meta){
		if(row.status == "0"){
			return "正常";
		}else{
			return "停用";
		}
	}
	
	function showDetail(index){
		var row = $('#users').DataTable().rows().data()[index];
		$("#pointForm input").val("").parent().siblings(".alert").remove();
		$("#pointForm input").parents(".item").removeClass("bad");
		
		$("#pointId").val(row.id);
		$("#pointName").val(row.pointName);
		$("#pointCharger").val(row.pointCharger);
		$("#pointAddress").val(row.pointAddress);
		$("#pointContact").val(row.pointContact);
		
		$('#pointModal').modal("show");
	}
	function addPort(){
		$("#pointForm input").val("").parent().siblings(".alert").remove();
		$("#pointForm input").parents(".item").removeClass("bad");
		$('#pointModal').modal("show");
	}
	function closeModal(){
		$('#pointModal').modal("hide");
	}
	function saveOrUpdatePoint(){
		if( !validator.checkAll( $("#pointForm") ) ){
			return;
		}
		$.ajax({
			url: ctx+"admin/endpoint/saveOrUpdate?time="+(new Date()).getTime(),
			dataType: "json",
			data:$("#pointForm").serialize(),
			success: function(result){
				if(result.status == "0"){
					swal({title:'',text:"保存成功"},function(){
						$('#pointModal').modal("hide");
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
			url: ctx+"admin/endpoint/startById?id="+id+"&time="+(new Date()).getTime(),
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
		swal({ "title": "", "text": "确定要停用该网点" ,"type": "warning","showCancelButton":true},function(isConfirm){
			if(isConfirm){
				$.ajax({
					url: ctx+"admin/endpoint/stopById?id="+id+"&time="+(new Date()).getTime(),
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