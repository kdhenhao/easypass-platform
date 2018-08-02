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
						<h2>商品管理</h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<form role="form" id="searchForm">
							<div class="container">
								<div class="row">
									<div class="col-lg-2">
										<input class="form-control input-sm" placeholder="商品名称" name="map['cnName']"/>
									</div>
									<div class="col-lg-2">
										<input class="form-control input-sm" placeholder="品牌" name="map['brand']"/>
									</div>
									<div class="col-lg-2">
										<input class="form-control input-sm" placeholder="条码" name="map['barcode']"/>
									</div>
									<div class="col-lg-2">
										<input class="form-control input-sm" placeholder="税号" name="map['taxNo']"/>
									</div>
									<div class="col-lg-4">
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
										<th>商品名称</th>
										<th>英文名称</th>
										<th>品牌</th>
										<th>条形码</th>
										<th>税码</th>
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
	
	<div id="commodityModal" class="modal fade" role="dialog" data-backdrop="false"
		aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="modelTitle">商品管理</h4>
				</div>
				<div class="modal-body">
					<form role="form" novalidate id="commodityForm" class="form-horizontal">
						<div class="form-group item">
						    <label for="cnName" class="col-sm-3 control-label">中文名称：</label>
						    <div class="col-sm-6">
						    	<input type="hidden" name="id" id="commodityId">
						    	<input type="text" class="form-control" id="cnName" name="cnName" 
						    		placeholder="中文名称" required='required' data-validate-length-range="0,50">
						    </div>
						</div>
						<div class="form-group item">
						    <label for="enName" class="col-sm-3 control-label">英文名称：</label>
						    <div class="col-sm-6">
						    	<input type="text" class="form-control" id="enName" name="enName" 
						    		placeholder="英文名称" required='required' data-validate-length-range="0,100">
						    </div>
						</div>
						<div class="form-group item">
						    <label for="brand" class="col-sm-3 control-label">品牌：</label>
						    <div class="col-sm-6">
						    	<input type="text" class="form-control" id="brand" name="brand"
						    		placeholder="品牌" required="required" data-validate-length-range="0,50">
						    </div>
						</div>
						<div class="form-group item">
						    <label for="grossWeight" class="col-sm-3 control-label">总重量(Kg)：</label>
						    <div class="col-sm-6">
						    	<input type="number" class="form-control" id="grossWeight" name="grossWeight"
						    		placeholder="总重量" required="required" data-validate-minmax="0,99999999">
						    </div>
						</div>
						<div class="form-group item">
						    <label for="netWeight" class="col-sm-3 control-label">净重(Kg)：</label>
						    <div class="col-sm-6">
						    	<input type="number" class="form-control" id="netWeight" name="netWeight"
						    		placeholder="净重" required="required" data-validate-minmax="0,99999999">
						    </div>
						</div>
						<div class="form-group item">
						    <label for="unit" class="col-sm-3 control-label">规格/单位：</label>
						    <div class="col-sm-3">
					    		<input type="text" class="form-control" id="unit" name="unit"
					    			placeholder="规格eg：200ml" required="required" data-validate-length-range="0,20">
						    </div>
						    <div class="col-sm-3">
					    		<select class="form-control required" id="counter" name="counter">
									<option value="">--请选择--</option>
									<c:forEach items="${counters}" var="opt">
										<option value="${opt.text}">${opt.text}</option>
									</c:forEach>
								</select>
						    </div>
						</div>
						<div class="form-group item">
						    <label for="price" class="col-sm-3 control-label">商品价格(AUD$)：</label>
						    <div class="col-sm-6">
						    	<input type="number" class="form-control optional" id="price" name="price"
						    		placeholder="商品价格(AUD$)" required="required" data-validate-minmax="0,99999999">
						    </div>
						</div>
						<div class="form-group item">
						    <label for="barcode" class="col-sm-3 control-label">条形码：</label>
						    <div class="col-sm-6">
						    	<input type="text" class="form-control optional" id="barcode" name="barcode"
						    		placeholder="条形码" required="required" data-validate-length-range="0,20">
						    </div>
						</div>
						<div class="form-group item">
						    <label for="taxNo" class="col-sm-3 control-label">税码：</label>
						    <div class="col-sm-6">
						    	<input type="hidden" value="" id="taxId" name="taxId">
						    	<select class="select2_single form-control required" id="taxNo" name="taxNo" tabindex="-1">
						    		<option value="">--请选择--</option>
						    		<c:forEach items="${taxes}" var="opt">
						    			<option value="${opt.taxNo}" taxId="${opt.id}">${opt.taxNo}${opt.taxName}</option>
						    		</c:forEach>
						    	</select>
						    </div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" onclick="closeModal();">取消</button>
					<button type="button" class="btn btn-success" onclick="saveOrUpdate();">保存</button>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/common/script.jsp"%>
	<script>
	var modalInit = false;
	$(function(){
		var t = $("#users").DataTable({
		    ajax: {
		        url: ctx+'admin/commodity/findCommodities',
		        type: 'POST',
		        data: function(d){$.extend(d,serializeObject($("#searchForm")))}
		    },
		    columns: [
		        { "data":null,"render":rowNumberRender},
                { "data":'cnName'},
		        { "data":'enName'},
		        { "data":'brand'},
		        { "data":'barcode'},
		        { "data":'taxNo'},
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
		//if(row.status == "0"){
		//	but = but + '<a class="btn btn-danger btn-xs" href="javascript:lockById('+row.id+')"><i class="fa fa-lock"></i>停用</a>';
		//}else{
		//	but = but + '<a class="btn btn-warning btn-xs" href="javascript:unLockById('+row.id+')"><i class="fa fa-unlock"></i>启用</a>';
		//}
		
		return but;
	}
	
	function showDetail(index){
		var row = $('#users').DataTable().rows().data()[index];
		$("#commodityForm").find("input,select").val("").parent().siblings(".alert").remove();
		$("#commodityForm").find("input,select").parents(".item").removeClass("bad");
		
		$("#commodityId").val(row.id);
		$("#cnName").val(row.cnName);
		$("#enName").val(row.enName);
		$("#brand").val(row.brand);
		$("#grossWeight").val(row.grossWeight);
		$("#netWeight").val(row.netWeight);
		$("#unit").val(row.unit);
		$("#counter").val(row.counter);
		$("#barcode").val(row.barcode);
		$("#price").val(row.price);
		$("#taxNo").val(row.taxNo);
		$("#taxId").val(row.taxId);
		
		$('#commodityModal').modal("show");
		if(!modalInit){
			modalInit = true;
			initSelect2();
		}
	}
	function add(){
		$("#commodityForm").find("input,select").val("").parent().siblings(".alert").remove();
		$("#commodityForm").find("input,select").parents(".item").removeClass("bad");
		
		$('#commodityModal').modal("show");
		if(!modalInit){
			modalInit = true;
			initSelect2();
		}
	}
	function closeModal(){
		$('#commodityModal').modal("hide");
	}
	
	function saveOrUpdate(){
		if( !validator.checkAll( $("#commodityForm") ) ){
			return;
		}
		$.ajax({
			url: ctx+"admin/commodity/saveOrUpdate?time="+(new Date()).getTime(),
			dataType: "json",
			data:$("#commodityForm").serialize(),
			success: function(result){
				if(result.status == "0"){
					swal({title:'',text:"操作成功"},function(){
						$('#commodityModal').modal("hide");
						searchCurrentPage();
					});
				}else{
					swal({title:'',text:result.msg,type:"error"});
				}
			}
		});
	}
	function onCounterChange(){
		var unit = $("#unit").val();
		if(unit.indexOf("/") >= 0){
			unit = unit.substring(0,$("#unit").val().lastIndexOf("/"))
		}
		if($("#counter").val() == ""){
			$("#unit").val(unit);
		}else{
			$("#unit").val(unit+"/"+$("#counter").val());	
		}
	}
	function initSelect2(){
		$('#taxNo').select2();
		$('#taxNo').on('select2:select', function (evt) {
			var opt = $("#taxNo option:selected");
			$("#taxId").val($(opt).attr("taxId"));
		});
		$("#counter").select2();
		$('#counter').on('select2:select',onCounterChange);
	}
	</script>
</body>
</html>