<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/common/head.jsp"%>
<style>
table.dataTable tbody > tr > .selected {
  background-color: rgba(38, 185, 154, 0.4);
}
</style>
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
						<h2>网点订单管理</h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<form role="form" id="searchForm">
							<div class="container">
								<div class="row">
									<div class="col-lg-2">
										<input class="form-control input-sm" placeholder="订单号" name="map['orderNo']"/>
									</div>
									<div class="col-lg-2">
										<input class="form-control input-sm" placeholder="下单日期" id="createTm" name="map['createTm']"/>
									</div>
									<div class="col-lg-2">
										<select class="form-control input-sm" name="map['endpointId']">
											<option value="">--请选择--</option>
											<c:forEach items="${endpoints}" var="opt">
												<option value="${opt.id}">${opt.pointName}</option>
											</c:forEach>
										</select>
									</div>
									<div class="col-lg-2">
										<select class="form-control input-sm" name="map['endpointPay']">
											<option value="">--请选择--</option>
											<option value="0">未缴费</option>
											<option value="1">已缴费</option>
										</select>
									</div>
									<div class="col-lg-4">
										<button type="button" class="btn btn-success btn-sm" onclick="markPaied();">标记为缴费</button>
										<button type="button" class="btn btn-danger btn-sm" onclick="markUnPaied();">标记为未缴费</button>
										<button type="button" class="btn btn-primary btn-sm" onclick="search();">搜索</button>
									</div>
								</div>
							</div>
						</form>
						<div class="table-responsive">
							<table id="users" class="table table-bordered table-striped table-hover bulk_action" 
							  cellspacing="0" width="100%">
								<thead>
									<tr class="mytableHead">
										<th class="myRowNumber"><input type="checkbox" id="check-all" class="flat"></th>
										<th>订单号</th>
										<th>下单时间</th>
										<th>下单重量</th>
										<th>订单价格</th>
										<th>寄件人</th>
										<th>收件人</th>
										<th>网点</th>
										<th>状态</th>
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

	<%@ include file="/WEB-INF/common/script.jsp"%>
	<script>
	$(function(){
		$('#createTm').daterangepicker(datePickerOpt,function(start, end, label) {
		    search();
		});
		var t = $("#users").DataTable({
		    "ajax": {
		        url: ctx+'admin/pointOrder/findOrders',
		        type: 'POST',
		        data: function(d){$.extend(d,serializeObject($("#searchForm")))}
		    },
		    "columns": [
		        { "data":null,"render":checkBoxRender},
                { "data":'orderNo'},
		        { "data":'createTm'},
		        { "data":'totalWeight'},
		        { "data":'totalPrice'},
		        { "data":'senderName'},
		        { "data":'receiverName'},
		        { "data":'pointName'},
		        { "data":'endpointPay',"render":statusRender}
		    ],
		    "createdRow": function ( row, data, index ) {
	            $(row).find("input.flat").iCheck({
	                checkboxClass: 'icheckbox_flat-green',
	                radioClass: 'iradio_flat-green'
	            }).on('ifChecked', function () {
	                checkState = '';
	                $(this).parent().parent().parent().addClass('selected');
	                countChecked();
	            }).on('ifUnchecked', function () {
	                checkState = '';
	                $(this).parent().parent().parent().removeClass('selected');
	                countChecked();
	            });
	            $(row).find("td:gt(0)").on("click",function(){
	            	if($(row).hasClass("selected")){
	            		$(row).removeClass("selected");
	            		$(row).find("input.flat").iCheck('uncheck');
	            	}else{
	            		$(row).removeClass("selected");
	            		$(row).find("input.flat").iCheck('check');
	            	}
	            });
	        }
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
	function statusRender(data, type, row, meta){
		if(row.endpointPay == "0"){
			return "未缴费";
		}else{
			return "已缴费";
		}
	}
	function checkBoxRender(data, type, row, meta){
		return '<input type="checkbox" class="flat" name="table_records">';
	}

	function markPaied(){
		var rows = $('#users').DataTable().rows(".selected").data();
		if(rows.length == 0){
			swal("","请至少选择一条记录","warning");
			return;
		}
		var ids = "";
		for(var i=0;i<rows.length;i++){
			ids = ids + rows[i].id + ",";
		}
		swal({ "title": "", "text": "您选择了"+rows.length+"条记录，确定要标记为“已缴费”?" ,"type": "warning","showCancelButton":true},function(isConfirm){
			if(isConfirm){
				$.ajax({
					"url": ctx + "admin/pointOrder/markPaied?time="+ (new Date()).getTime()+"&id="+ids,
					"dataType": "json",
					"success": function(result){
						if(result.status == "0"){
							$("#check-all").iCheck("unCheck");
							searchCurrentPage();
						}else{
							swal({title:'',text:result.msg,type:"error"});
						}
					}
				});
			}
		});
	}
	function markUnPaied(id){
		var rows = $('#users').DataTable().rows(".selected").data();
		if(rows.length == 0){
			swal("","请至少选择一条记录","warning");
			return;
		}
		var ids = "";
		for(var i=0;i<rows.length;i++){
			ids = ids + rows[i].id + ",";
		}
		swal({ "title": "", "text": "您选择了"+rows.length+"条记录，确定要标记为“未缴费”?" ,"type": "warning","showCancelButton":true},function(isConfirm){
			if(isConfirm){
				$.ajax({
					"url": ctx + "admin/pointOrder/markUnPaied?time="+ (new Date()).getTime()+"&id="+ids,
					"dataType": "json",
					"success": function(result){
						if(result.status == "0"){
							$("#check-all").iCheck("unCheck");
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