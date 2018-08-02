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
						<h2>
							订单管理 <small>修改重量</small>
						</h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<form role="form" id="orderForm" novalidate="novalidate" class="form-horizontal form-label-left">
						<div class="form-group item">
							<label class="control-label col-md-3 col-sm-3 col-xs-12" 
								for="orderNo">订单号<span class="required">*</span>
							</label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<div class="input-group">
									<input type="hidden" id="orderId" name="id" />
									<input type="text" style="display:none"/>
									<input type="text" id="orderNo" name="orderNo" required="required"
									class="form-control">
									<span class="input-group-btn">
                                      	<button type="button" class="btn btn-primary" onclick="search()">搜索</button>
                                    </span>
								</div>
							</div>
						</div>
						<div class="form-group item">
							<label class="control-label col-md-3 col-sm-3 col-xs-12" 
								for="orderNo">订单号
							</label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<input type="text" id="orderNo2" name="orderNo" disabled="disabled"
									class="form-control">
							</div>
						</div>
						<div class="form-group item">
							<label class="control-label col-md-3 col-sm-3 col-xs-12" 
								for="orderNo">收件人
							</label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<input type="text" id="receiverName" disabled="disabled"
									class="form-control">
							</div>
						</div>
						<div class="form-group item">
							<label class="control-label col-md-3 col-sm-3 col-xs-12" 
								for="orderNo">寄件人
							</label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<input type="text" id="senderName" disabled="disabled"
									class="form-control">
							</div>
						</div>
						<div class="form-group item">
							<label class="control-label col-md-3 col-sm-3 col-xs-12" 
								for="orderNo">重量
							</label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<input type="number" id="totalWeight" name="totalWeight" required="required" data-validate-minmax="0,99999999"
									class="form-control">
							</div>
						</div>
						<div class="ln_solid"></div>
						<div class="form-group item">
							<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
								<button type="button" class="btn btn-success" onclick="updateWeight();">保存</button>
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
			"url": ctx + "admin/orderWeight/findByOrderNo?orderNo="+orderNo,
			"dataType":"json",
			"success":function(result){
				if(result.status == "1"){
					swal("","没有查询到对应订单","warning");
					$("#orderId").val("");
					$("#orderNo2").val("");
					$("#receiverName").val("");
					$("#senderName").val("");
					$("#totalWeight").val("");
				}else{
					var result = result.data;
					$("#orderId").val(result.id);
					$("#orderNo2").val(result.orderNo);
					$("#receiverName").val(result.receiverName+"  "+result.receiverProvince+result.receiverCity+result.receiverDistrict+result.receiverAddress);
					$("#senderName").val(result.senderName+"  "+result.senderProvince+result.senderCity+result.senderDistrict+result.senderAddress);
					$("#totalWeight").val(result.totalWeight);
				}
			}
		});
	}
	function updateWeight(){
		if($("#orderId").val() == ""){
			swal("","请先选择订单","error");
			return;
		}
		if( !validator.checkAll( $("#orderForm") ) ){
			return;
		}
		$.ajax({
			"url":ctx+"admin/orderWeight/updateWeight",
			"data":$("#orderForm").serialize(),
			"dataType":"json",
			"success":function(result){
				if(result.status == 0){
					swal({"title":"","text":"操作成功","type":"info"},function(){
						window.location = ctx + "admin/orderWeight/toWeightPage";
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