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
						<h2>航班管理</h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<form role="form" id="searchForm">
							<div class="container">
								<div class="row">
									<div class="col-lg-3">
										<input class="form-control input-sm" placeholder="航班号" name="map['flightNo']"/>
									</div>
									<div class="col-lg-3">
										<input class="form-control input-sm" placeholder="航班日期" name="map['flightDate']" id="flightDate"/>
									</div>
									<div class="col-lg-3">
										<select class="form-control input-sm" name="map['portId']">
											<option value="">--请选择--</option>
											<c:forEach items="${ports}" var="opt">
												<option value="${opt.value}">${opt.text}</option>
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
										<th class="myRowNumber"></th>
										<th>航班号</th>
										<th>关口</th>
										<th>航班时间</th>
										<!-- <th>航班状态</th> -->
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
	
	<div id="flightModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="false"
		aria-hidden="true">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="modelTitle">航班管理</h4>
				</div>
				<div class="modal-body">
					<form role="form" novalidate id="flightForm" class="form-horizontal">
						<div class="form-group item">
						    <label for="flightNo" class="col-sm-3 control-label">航班号：</label>
						    <div class="col-sm-6">
						    	<input type="hidden" name="id" id="flightId">
						    	<input type="text" class="form-control" id="flightNo" name="flightNo" 
						    		placeholder="航班号" required='required' data-validate-length-range="0,20">
						    </div>
						</div>
						<div class="form-group item">
						    <label for="flightDate" class="col-sm-3 control-label">航班日期：</label>
						    <div class="col-sm-6">
						    	<input type="text" class="form-control" id="flightDate2" name="flightDate" 
						    		placeholder="航班日期" required='required' pattern="date">
						    </div>
						</div>
						<div class="form-group item">
						    <label for="portId" class="col-sm-3 control-label">关口：</label>
						    <div class="col-sm-6">
						    	<select id="portId" name="portId" class="form-control" required='required'>
						    		<option value="">--请选择--</option>
						    		<c:forEach items="${ports}" var="opt">
										<option value="${opt.value}">${opt.text}</option>
									</c:forEach>
						    	</select>
						    </div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" onclick="closeModal();">取消</button>
					<button type="button" class="btn btn-success" onclick="saveOrUpdateFlight();">保存</button>
				</div>
			</div>
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
					<h4 class="modal-title" id="modelTitle">修改航班状态</h4>
				</div>
				<div class="modal-body">
					<form role="form" novalidate id="statusForm" class="form-horizontal">
						<div class="form-group item">
						    <label for="flightNo" class="col-sm-3 control-label">航班号：</label>
						    <div class="col-sm-6">
						    	<input type="hidden" name="id" id="flightId2">
						    	<input type="text" class="form-control" id="flightNo2" name="flightNo" disabled="disabled"
						    		placeholder="航班号" required='required' data-validate-length-range="0,20">
						    </div>
						</div>
						<div class="form-group item">
						    <label for="flightStatus" class="col-sm-3 control-label">状态：</label>
						    <div class="col-sm-6">
						    	<select id="flightStatus" name="flightStatus" class="form-control" required='required'>
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
	<%@ include file="/WEB-INF/common/script.jsp"%>
	<script>
	$(function(){
		$('#flightDate').daterangepicker(datePickerOpt,function(start, end, label) {
		    search();
		});
		$('#flightDate2').daterangepicker(datePickerOpt);
		
		var t = $("#users").DataTable({
		    ajax: {
		        url: ctx+'admin/flight/findFlights',
		        type: 'POST',
		        data: function(d){$.extend(d,serializeObject($("#searchForm")))}
		    },
		    columns: [
		        { "data":null,"render":rowNumberRender},
                { "data":'flightNo'},
		        { "data":'portName'},
		        { "data":'flightDate'},
		        /*{ "data":'flightStatus',"render":statusRender},*/
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
		//but = but + '<a class="btn btn-warning btn-xs" href="javascript:statusById('+meta.row+')"><i class="fa fa-paper-plane"></i>修改状态</a>';
		return but;
	}
	function statusRender(data, type, row, meta){
		if(row.flightStatus == "orderReceived"){
			return "订单已从网点接收";
		}else if(row.flightStatus == "orderReached"){
			return "订单已抵达仓库";
		}else if(row.flightStatus == "orderConfiged"){
			return "订单已配置货物";
		}else if(row.flightStatus == "flightConfiged"){
			return "货物已配置航班";
		}else if(row.flightStatus == "flighting"){
			return "航班飞往中国中";
		}else if(row.flightStatus == "clearancing"){
			return "中国机场清关中";
		}else if(row.flightStatus == "clearanced"){
			return "清关完成等待送货";
		}else if(row.flightStatus == "sending"){
			return "中国快递配送中";
		}
		return "";
	}
	
	function showDetail(index){
		var row = $('#users').DataTable().rows().data()[index];
		$("#flightForm").find("input,select").val("").parent().siblings(".alert").remove();
		$("#flightForm").find("input,select").parents(".item").removeClass("bad");
		$("#flightNo").val(row.flightNo);
		$("#flightId").val(row.id);
		$("#portId").val(row.portId);
		$("#flightDate2").val(row.flightDate);
		$('#flightModal').modal("show");
	}
	function add(){
		$("#flightForm").find("input,select").val("").parent().siblings(".alert").remove();
		$("#flightForm").find("input,select").parents(".item").removeClass("bad");
		$('#flightModal').modal("show");
	}
	function closeModal(){
		$('#flightModal').modal("hide");
	}
	
	function saveOrUpdateFlight(id){
		if( !validator.checkAll( $("#flightForm") ) ){
			return;
		}
		$.ajax({
			url: ctx+"admin/flight/saveOrUpdate?time="+(new Date()).getTime(),
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
	
	function statusById(index){
		var row = $('#users').DataTable().rows().data()[index];
		$("#statusForm").find("input,select").val("").parent().siblings(".alert").remove();
		$("#statusForm").find("input,select").parents(".item").removeClass("bad");
		
		$("#flightId2").val(row.id);
		$("#flightNo2").val(row.flightNo);
		$("#flightStatus").val(row.flightStatus);
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
			url: ctx+"admin/flight/updateStatus?time="+(new Date()).getTime(),
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
	</script>
</body>
</html>