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
						<h2>订单管理</h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<form role="form" id="searchForm">
							<div class="container">
								<div class="row">
									<div class="col-lg-3">
										<input class="form-control input-sm" placeholder="订单号" name="map['orderNo']"/>
									</div>
									<div class="col-lg-3">
										<input class="form-control input-sm" placeholder="下单日期" id="createTm" name="map['createTm']"/>
									</div>
									<div class="col-lg-3">
										<select class="form-control input-sm" name="map['endpointId']">
											<option value="">--请选择--</option>
											<c:forEach items="${endpoints}" var="opt">
												<option value="${opt.id}">${opt.pointName}</option>
											</c:forEach>
										</select>
									</div>
									<div class="col-lg-3">
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
										<th class="myRowNumber"></th>
										<th>订单号</th>
										<th>下单时间</th>
										<th>下单重量</th>
										<th>订单价格</th>
										<th>寄件人</th>
										<th>收件人</th>
										<th>网点</th>
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

	<%@ include file="/WEB-INF/common/script.jsp"%>
	<script>
	$(function(){
		$('#createTm').daterangepicker(datePickerOpt,function(start, end, label) {
		    search();
		});
		var t = $("#users").DataTable({
		    "ajax": {
		        url: ctx+'admin/orderManage/findOrders',
		        type: 'POST',
		        data: function(d){$.extend(d,serializeObject($("#searchForm")))}
		    },
		    "columns": [
		        { "data":null,"render":rowNumberRender},
                { "data":'orderNo'},
		        { "data":'createTm'},
		        { "data":'totalWeight'},
		        { "data":'totalPrice'},
		        { "data":'senderName'},
		        { "data":'receiverName'},
		        { "data":'pointName'},
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
		var but = '<a class="btn btn-info btn-xs" href="javascript:showDetail('+row.id+')"><i class="fa fa-pencil"></i>编辑</a>';
		return but;
	}
	function showDetail(id){
		window.location = ctx + "admin/orderManage/toEditPage?id="+id+"&time="+(new Date()).getTime();
	}

	</script>
</body>
</html>