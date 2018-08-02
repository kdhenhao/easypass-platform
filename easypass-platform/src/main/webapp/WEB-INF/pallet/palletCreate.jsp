<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/common/head.jsp"%>
<style>
.disabledButForLabel {
  cursor: not-allowed;
  pointer-events: none;
  opacity: .65;
  filter: alpha(opacity=65);
  box-shadow: none; }
.overWeight{
   color:red;
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
						<h2>
							托盘管理 <small>生成托盘</small>
						</h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<form role="form" class="form-horizontal form-label-left">
						<div class="form-group item">
							<label class="control-label col-md-2 col-sm-2 col-xs-12" 
								for="orderNo">订单号<span class="required">*</span>
							</label>
							<div class="col-md-4 col-sm-4 col-xs-12">
								<div class="input-group">
									<input type="hidden" id="orderId" name="id" />
									<input type="text" style="display:none"/>
									<input type="text" id="orderNo" name="orderNo" class="form-control optional">
									<span class="input-group-btn">
                                      	<button type="button" class="btn btn-primary" onclick="search()">加入</button>
                                    </span>
								</div>
							</div>
							<div class="col-md-4 col-sm-4 col-xs-12">
								<button type="button" class="disabledButForLabel btn btn-warning">未上传身份证</button>
								<button type="button" class="disabledButForLabel btn btn-danger">收件人重名</button>
							</div>
						</div>
						<div class="form-group item">
							<label class="control-label col-md-2 col-sm-2 col-xs-12" 
								for="commodities">托盘详情
							</label>
							<div class="col-md-8 col-sm-8 col-xs-12">
								<div class="x_panel">
									<div class="x_title">
										<ul class="nav navbar-left panel_toolbox">
											<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
										</ul>
										<div class="clearfix"></div>
									</div>
									<div class="x_content">
										<table id="orderTable" class="table table-bordered table-striped table-hover">
											<thead>
												<tr class="myTableHead">
													<th class="myRowNumber"></th>
													<th>订单编号</th>
													<th>收件人</th>
													<th>寄件人</th>
													<th>是否上传身份证</th>
													<th>操作</th>
												</tr>
											</thead>
											<tbody>
											</tbody>
										</table>
									</div>
								</div>
							</div>
						</div>
						<div class="ln_solid"></div>
						<div class="form-group item">
							<div class="col-md-2 col-sm-2 col-xs-6 col-md-offset-2 col-lg-offset-2">
								<button type="button" class="btn btn-success" onclick="createPallet();">生成托盘</button>
							</div>
							<div class="col-md-4 col-sm-4 col-xs-6">
								<h4>当前托盘总重量：<span id="weightSum">0</span>&nbsp;(kg)</h4>
							</div>
						</div>
						</form>
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
		$("#orderNo").keydown(function(event){
			if(event.keyCode == 13){
				search();
			}
		});
		$("#orderNo").focus();
	});
	function search(){
		var orderNo = $("#orderNo").val();
		$.ajax({
			"url": ctx + "admin/palletCreate/findByOrderNo?orderNo="+orderNo,
			"dataType":"json",
			"success":function(result){
				if(result.status == "1"){
					swal({title:"",text:"没有查询到对应订单",type:"warning"},function(){
						$("#orderNo").val("").focus();
					});
				}else{
					$("#orderNo").val("").focus();
					var row = result.data;
					rebuildTable(row);
					//滚动到顶部
					$('html, body').animate({scrollTop:0}, 'slow');
				}
			}
		});
	}
	var rows = [];
	function rebuildTable(row){
		//判断有没有
		var found = false;
		for(var i=0;i<rows.length;i++){
			if(rows[i].id == row.id){
				found = true;
				break;
			}
		}
		if(found){
			swal({title:"",text:"该订单已经添加过了",type:"warning"},function(){
				$("#orderNo").val("").focus();
			});
			return;
		}
		//添加到最后一行
		rows.push(row);
		var status = "";
		var className = "";
		if(row.hasPic == "0"){
			status = "未上传";
			className = "warning";
		}else{
			status = "已上传";
		}
		$("#orderTable tbody").append("<tr id='"+row.id+"' class='"+className+"'><td class='rn' weight='"+row.totalWeight+"'></td><td>"+row.orderNo+"</td><td class='receiverName'>"+row.receiverName+"</td><td>"+row.senderName+"</td><td>"+status+"</td><td><a class='btn btn-danger btn-xs' href='javascript:void(0);' onclick='deleteRow(this)'><i class='fa fa-minus'></i>删除</a></td></tr>");
		//检测是否有重名
		var names = {};
		for(var i=0;i<rows.length;i++){
			if(names[rows[i].receiverName] == null){
				names[rows[i].receiverName] = 1;
			}else{
				names[rows[i].receiverName] = names[rows[i].receiverName] + 1;
			}
		}
		for(var name in names){
			if(names[name] > 1){
				var tds = $(".receiverName");
				for(var i=0;i<tds.length;i++){
					if($(tds[i]).text() == name){
						$(tds[i]).parent().addClass("danger");
					}
				}
			}
		}
		//刷新序号
		refreashRownumber();
	}
	
	/*刷新序列号和总重量*/
	function refreashRownumber(){
		var tds = $("#orderTable .rn");
		var weight = 0;
		for(var i=0;i<tds.length;i++){
			$(tds[i]).html(i+1);
			if($.isNumeric($(tds[i]).attr("weight"))){
				weight = weight + Number($(tds[i]).attr("weight"));
			}
		}
		$("#weightSum").html(weight);
		if(weight >= 30){
			$("#weightSum").addClass("overWeight");
		}else{
			$("#weightSum").removeClass("overWeight");
		}
	}
	
	function deleteRow(obj){
		var index = $(obj).parents("tr").find(".rn").html();
		index = Number(index)-1;
		//删除全局变量
		rows[index] = null;
		var newRows = [];
		for(var i=0;i<rows.length;i++){
			if(rows[i] != null){
				newRows.push(rows[i]);
			}
		}
		rows = newRows;
		$(obj).parents("tr").remove();
		//刷新颜色
		$("#orderTable tr").removeClass("danger");
		var names = {};
		for(var i=0;i<rows.length;i++){
			if(names[rows[i].receiverName] == null){
				names[rows[i].receiverName] = 1;
			}else{
				names[rows[i].receiverName] = names[rows[i].receiverName] + 1;
			}
		}
		for(var name in names){
			if(names[name] > 1){
				var tds = $(".receiverName");
				for(var i=0;i<tds.length;i++){
					if($(tds[i]).text() == name){
						$(tds[i]).parent().addClass("danger");
					}
				}
			}
		}
		//刷新序号
		refreashRownumber();
	}
	
	function createPallet(){
		if($("#orderTable tbody").find("tr").length < 1){
			swal("","托盘中至少要有一个订单","error");
			return;
		}
		if($("#orderTable tr.danger").length > 0){
			swal("","托盘中有重名收件人，不能新增","error");
			return;
		}
		var rows = $("#orderTable tbody").find("tr");
		var orderIds = "";
		for(var i=0;i<rows.length;i++){
			orderIds = orderIds + $(rows[i]).attr("id");
			if(i != rows.length-1){
				orderIds = orderIds + ",";
			}
		}
		$.ajax({
			"url":ctx+"admin/palletCreate/createPallet",
			"data":"ids="+orderIds,
			"dataType":"json",
			"success":function(result){
				if(result.status == 0){
					swal({"title":"","text":"操作成功","type":"info"},function(){
						window.location = ctx + "admin/palletManage/toPalletManage";
					});
				}else{
					swal("",result.msg,"error");
				}
			}
		});
	}
	</script>
</body>
</html>