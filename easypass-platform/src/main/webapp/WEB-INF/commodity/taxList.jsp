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
						<h2>税号管理</h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<form role="form" id="searchForm">
							<div class="container">
								<div class="row">
									<div class="col-lg-3">
										<input class="form-control input-sm" placeholder="税号" name="map['taxNo']"/>
									</div>
									<div class="col-lg-3">
										<input class="form-control input-sm" placeholder="品名/规格" name="map['taxName']"/>
									</div>
									<div class="col-lg-3">
										<select class="form-control input-sm" name="map['level']">
											<option value="">--请选择--</option>
											<option value="0">一级</option>
											<option value="1">二级</option>
											<option value="2">三级</option>
											<option value="3">四级</option>
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
										<th class="myRowNumber"></th>
										<th>税号</th>
										<th>品名/规格</th>
										<th>单位</th>
										<th>计量单位代码</th>
										<th>完税价格</th>
										<th>税率</th>
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
	
	<div id="taxModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="false"
		aria-hidden="true">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="modelTitle">税号管理</h4>
				</div>
				<div class="modal-body">
					<form role="form" novalidate id="taxForm" class="form-horizontal">
						<div class="form-group item">
						    <label for="taxNo" class="col-sm-3 control-label">税号：</label>
						    <div class="col-sm-6">
						    	<input type="hidden" name="id" id="taxId">
						    	<input type="text" class="form-control" id="taxNo" name="taxNo" 
						    		placeholder="税号" required='required' data-validate-length="8">
						    </div>
						</div>
						<div class="form-group item">
						    <label for="taxName" class="col-sm-3 control-label">品名/规格：</label>
						    <div class="col-sm-6">
						    	<input type="text" class="form-control" id="taxName" name="taxName" 
						    		placeholder="品名/规格" required='required' data-validate-length-range="0,20">
						    </div>
						</div>
						<div class="form-group item">
						    <label for="taxUnit" class="col-sm-3 control-label">单位：</label>
						    <div class="col-sm-6">
						    	<input type="text" class="form-control optional" id="taxUnit" name="taxUnit"
						    		placeholder="单位" data-validate-length-range="0,20">
						    </div>
						</div>
						<div class="form-group item">
						    <label for="taxUnitCode" class="col-sm-3 control-label">计量单位代码：</label>
						    <div class="col-sm-6">
						    	<input type="text" class="form-control optional" id="taxUnitCode" name="taxUnitCode"
						    		placeholder="计量单位代码" data-validate-length-range="0,20">
						    </div>
						</div>
						<div class="form-group item">
						    <label for="taxPrice" class="col-sm-3 control-label">价格：</label>
						    <div class="col-sm-6">
						    	<input type="number" class="form-control optional" id="taxPrice" name="taxPrice"
						    		placeholder="价格" data-validate-minmax="0,99999999">
						    </div>
						</div>
						<div class="form-group item">
						    <label for="taxPercent" class="col-sm-3 control-label">税率：</label>
						    <div class="col-sm-6">
						    	<input type="text" class="form-control optional" id="taxPercent" name="taxPercent"
						    		placeholder="税率"  data-validate-length-range="0,20">
						    </div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" onclick="closeModal();">取消</button>
					<button type="button" class="btn btn-success" onclick="saveOrUpdateTax();">保存</button>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/common/script.jsp"%>
	<script>
	$(function(){
		var t = $("#users").DataTable({
		    ajax: {
		        url: ctx+'admin/tax/findTaxs',
		        type: 'POST',
		        data: function(d){$.extend(d,serializeObject($("#searchForm")))}
		    },
		    columns: [
		        { "data":null,"render":rowNumberRender},
                { "data":'taxNo'},
		        { "data":'taxName'},
		        { "data":'taxUnit'},
		        { "data":'taxUnitCode'},
		        { "data":'taxPrice'},
		        { "data":'taxPercent'},
		        { "data":'id',"render":optRender}
		    ]
		});
		//绑定事件
		$("#searchForm input").keydown(function(event){
			if(event.keyCode == 13){
				search();
			}
		});
		$("#searchForm select").change(search);
	});
	function search(){
		$('#users').DataTable().ajax.reload(function(d){$.extend(d,serializeObject($("#searchForm")))}, true);;
	}
	function searchCurrentPage(){
		$('#users').DataTable().ajax.reload(function(d){$.extend(d,serializeObject($("#searchForm")))}, false);;
	}
	function optRender(data, type, row, meta){
		var but = '<a class="btn btn-info btn-xs" href="javascript:showDetail('+meta.row+')"><i class="fa fa-pencil"></i>编辑</a>';
		if(row.status == "0"){
			but = but + '<a class="btn btn-danger btn-xs" href="javascript:lockById('+row.id+')"><i class="fa fa-lock"></i>停用</a>';
		}else{
			but = but + '<a class="btn btn-warning btn-xs" href="javascript:unLockById('+row.id+')"><i class="fa fa-unlock"></i>启用</a>';
		}
		
		return but;
	}
	
	function showDetail(index){
		var row = $('#users').DataTable().rows().data()[index];
		$("#taxForm").find("input,select").val("").parent().siblings(".alert").remove();
		$("#taxForm").find("input,select").parents(".item").removeClass("bad");
		
		$("#taxId").val(row.id);
		$("#taxNo").val(row.taxNo);
		$("#taxName").val(row.taxName);
		$("#taxUnit").val(row.taxUnit);
		$("#taxUnitCode").val(row.taxUnitCode);
		$("#taxPrice").val(row.taxPrice);
		$("#taxPercent").val(row.taxPercent);
		$('#taxModal').modal("show");
	}
	function add(){
		$("#taxForm").find("input,select").val("").parent().siblings(".alert").remove();
		$("#taxForm").find("input,select").parents(".item").removeClass("bad");
		
		$('#taxModal').modal("show");
	}
	function closeModal(){
		$('#taxModal').modal("hide");
	}
	
	function saveOrUpdateTax(){
		if( !validator.checkAll( $("#taxForm") ) ){
			return;
		}
		$.ajax({
			url: ctx+"admin/tax/saveOrUpdate?time="+(new Date()).getTime(),
			dataType: "json",
			data:$("#taxForm").serialize(),
			success: function(result){
				if(result.status == "0"){
					swal({title:'',text:"操作成功"},function(){
						$('#taxModal').modal("hide");
						searchCurrentPage();
					});
				}else{
					swal({title:'',text:result.msg,type:"error"});
				}
			}
		});
	}
	
	function lockById(id){
		swal({ "title": "", "text": "确认想要停用该税号吗?" ,"type": "warning","showCancelButton":true},function(isConfirm){
			if(isConfirm){
				$.ajax({
					"url": ctx + "admin/tax/lockById?time="+ (new Date()).getTime()+"&id="+id,
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
	function unLockById(id){
		$.ajax({
			"url": ctx + "admin/tax/unLockById?time="+ (new Date()).getTime()+"&id="+id,
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
	</script>
</body>
</html>