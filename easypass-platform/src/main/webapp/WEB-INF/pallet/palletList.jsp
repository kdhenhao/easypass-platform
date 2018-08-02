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
						<h2>托盘管理</h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<form role="form" id="searchForm">
							<div class="container">
								<div class="row">
									<div class="col-lg-3">
										<input class="form-control input-sm" placeholder="托盘名称" name="palletName" id="palletName"/>
									</div>
									<div class="col-lg-3">
										<input class="form-control input-sm" placeholder="航班号" name="flightNo"/>
									</div>
									<div class="col-lg-3">
										<select class="form-control input-sm" name="status">
											<option value="">--请选择--</option>
											<option value="orderReceived">订单已从网点接收</option>
								    		<option value="orderReached">订单已抵达仓库</option>
								    		<option value="orderConfiged">订单已配置货物</option>
								    		<option value="flightConfiged">货物已配置航班</option>
								    		<option value="flighting">航班飞往中国中</option>
								    		<option value="clearancing">中国机场清关中</option>
								    		<option value="clearanced">清关完成等待送货</option>
								    		<option value="sending">中国快递配送中</option>
										</select>
									</div>
									<div class="col-lg-3">
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
										<th>托盘名称</th>
										<th>航班</th>
										<th>状态</th>
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
	
	<div id="statusModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="false"
		aria-hidden="true">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="modelTitle">修改托盘状态</h4>
				</div>
				<div class="modal-body">
					<form role="form" novalidate id="statusForm" class="form-horizontal">
						<div class="form-group item">
						    <label for="palletName2" class="col-sm-3 control-label">托盘名称：</label>
						    <div class="col-sm-6">
						    	<input type="hidden" name="id" id="palletId">
						    	<input type="text" class="form-control" id="palletName2" name="palletName" disabled="disabled"
						    		placeholder="托盘名称" >
						    </div>
						</div>
						<div class="form-group item">
						    <label for="palletStatus" class="col-sm-3 control-label">状态：</label>
						    <div class="col-sm-6">
						    	<select id="palletStatus" name="palletStatus" class="form-control required">
						    		<option value="">--请选择--</option>
						    		<option value="flightConfiged">货物已配置航班</option>
						    		<option value="flighting">航班飞往中国中</option>
						    		<option value="clearancing">中国机场清关中</option>
						    		<option value="clearanced">清关完成等待送货</option>
						    		<option value="sending">中国快递配送中</option>
						    	</select>
						    </div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" onclick="cancelStatus();">取消</button>
					<button type="button" class="btn btn-success" onclick="updateStatus();">保存</button>
				</div>
			</div>
		</div>
	</div>
	
	<div id="flightModal" class="modal fade" role="dialog" data-backdrop="false"
		aria-hidden="true">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="modelTitle">分配航班</h4>
				</div>
				<div class="modal-body">
					<form role="form" novalidate id="flightForm" class="form-horizontal">
						<div class="form-group item">
						    <label for="palletName" class="col-sm-3 control-label">托盘名称：</label>
						    <div class="col-sm-6">
						    	<input type="hidden" name="id" id="palletId3">
						    	<input type="text" class="form-control" id="palletName3" name="palletName" disabled="disabled"
						    		placeholder="托盘名称" >
						    </div>
						</div>
						<div class="form-group item">
						    <label for="flightId" class="col-sm-3 control-label">航班：</label>
						    <div class="col-sm-6">
						    	<input type="hidden" name="flightId" id="flightIdInput"/>
						    	<select id="flightId" class="form-control optional" tabindex="-1">
						    		<option value="">--请选择--</option>
						    	</select>
						    </div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" onclick="cancelFlight();">取消</button>
					<button type="button" class="btn btn-success" onclick="updateFlight();">保存</button>
				</div>
			</div>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/common/script.jsp"%>
	<script>
	$(function(){
		$('#palletName').daterangepicker(datePickerOpt,function(start, end, label) {
		    search();
		});
		
		var t = $("#users").DataTable({
		    ajax: {
		        url: ctx+'admin/palletManage/findPallets',
		        type: 'POST',
		        data: function(d){$.extend(d,serializeObject($("#searchForm")))}
		    },
		    columns: [
		        { "data":null,"render":rowNumberRender},
                { "data":'palletName'},
		        { "data":'flightNo'},
		        { "data":'status',"render":statusRender},
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
		var but = '<a class="btn btn-info btn-xs" href="javascript:showDetail('+meta.row+')"><i class="fa fa-pencil"></i>详情</a>';
		but = but + '<a class="btn btn-warning btn-xs" href="javascript:statusById('+meta.row+')"><i class="fa fa-share"></i>修改状态</a>';
		but = but + '<a class="btn btn-dark btn-xs" href="javascript:flightById('+meta.row+')"><i class="fa fa-paper-plane"></i>分配航班</a>';
		but = but + '<a class="btn btn-danger btn-xs" href="javascript:deleteById('+row.id+')"><i class="fa fa-minux"></i>删除</a>';
		return but;
	}
	function statusRender(data, type, row, meta){
		if(row.status == "orderReceived"){
			return "订单已从网点接收";
		}else if(row.status == "orderReached"){
			return "订单已抵达仓库";
		}else if(row.status == "orderConfiged"){
			return "订单已配置货物";
		}else if(row.status == "flightConfiged"){
			return "货物已配置航班";
		}else if(row.status == "flighting"){
			return "航班飞往中国中";
		}else if(row.status == "clearancing"){
			return "中国机场清关中";
		}else if(row.status == "clearanced"){
			return "清关完成等待送货";
		}else if(row.status == "sending"){
			return "中国快递配送中";
		}
		return "";
	}
	
	function statusById(index){
		var row = $('#users').DataTable().rows().data()[index];
		$("#statusForm").find("input,select").val("").parent().siblings(".alert").remove();
		$("#statusForm").find("input,select").parents(".item").removeClass("bad");
		
		$("#palletId").val(row.id);
		$("#palletName2").val(row.palletName);
		$("#palletStatus").val(row.status);
		$('#statusModal').modal("show");
	}
	function cancelStatus(){
		$('#statusModal').modal("hide");
	}
	
	function updateStatus(){
		if( !validator.checkAll( $("#statusForm") ) ){
			return;
		}
		$.ajax({
			url: ctx+"admin/palletManage/updateStatus?time="+(new Date()).getTime(),
			dataType: "json",
			data:$("#statusForm").serialize(),
			success: function(result){
				if(result.status == "0"){
					swal({title:'',text:"操作成功"},function(){
						$('#statusModal').modal("hide");
						searchCurrentPage();
					});
				}else{
					swal({title:'',text:result.msg,type:"error"});
				}
			}
		});
	}
	
	var flightInit = false;
	function flightById(index){
		var row = $('#users').DataTable().rows().data()[index];
		$("#flightForm").find("input,select").val("").parent().siblings(".alert").remove();
		$("#flightForm").find("input,select").parents(".item").removeClass("bad");
		
		$("#palletId3").val(row.id);
		$("#palletName3").val(row.palletName);
		$('#flightModal').modal("show");
		if(!flightInit){
			initSelect(row.flightNo);
			flightInit = true;
		}
	}
	function cancelFlight(){
		$('#flightModal').modal("hide");
	}
	function initSelect(flightNo){
		$('#flightId').select2({
			"ajax":{
				url: ctx+"admin/palletManage/findFlights",
		        dataType: 'json',
		        delay: 250,
		        cache: true,
		        processResults: function (data, params) {
		            params.page = params.page || 1;
		            return {
		              results: data.items,
		              pagination: {
		                more: (params.page * 30) < data.total_count
		              }
		            };
		          },
			},
			"escapeMarkup": function (markup) { return markup; },
		    "minimumInputLength": 1,
		    "templateResult": formatRepo,
		    "templateSelection": formatRepoSelection,
		    "language":"zh-CN"
		});
		$('#flightId').on('select2:select',onFlightSelect);
		if(flightNo != null && typeof(flightNo) != "undefined"){
			$("#select2-flightId-container").html(flightNo);
		}
	}
	function onFlightSelect(){
		var flightId = $("#flightId option:selected").attr("value");
		$("#flightIdInput").val(flightId);
	}
	function formatRepo(repo) {
		if (repo.loading){
			return repo.text;
		}
		repo.text = repo.flightNo; 
		var div = "<div><h2>"+repo.flightDate+" | "+repo.flightNo+"</h2>";
		div = div +repo.portName+"</div>";
		return div;
	}
	function formatRepoSelection(repo) {
		repo.text = repo.flightNo;
		return repo.flightNo;
	}
	
	function updateFlight(){
		if($("#flightIdInput").val() == ""){
			return;
		}
		$.ajax({
			url: ctx+"admin/palletManage/updateFlight?time="+(new Date()).getTime(),
			dataType: "json",
			data:$("#flightForm").serialize(),
			success: function(result){
				if(result.status == "0"){
					swal({title:'',text:"操作成功"},function(){
						$('#flightModal').modal("hide");
						searchCurrentPage();
					});
				}else{
					swal({title:'',text:result.msg,type:"error"});
				}
			}
		});
	}
	
	function showDetail(index){
		var row = $('#users').DataTable().rows().data()[index];
		window.location = ctx + "admin/palletManage/toDetailPage?id="+row.id;
	}
	
	function deleteById(id){
		swal({ "title": "", "text": "确认想要删除该托盘并退回订单" ,"type": "warning","showCancelButton":true},function(isConfirm){
			if(isConfirm){
				$.ajax({
					"url": ctx + "admin/palletManage/deleteById?time="+ (new Date()).getTime()+"&id="+id,
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
	</script>
</body>
</html>