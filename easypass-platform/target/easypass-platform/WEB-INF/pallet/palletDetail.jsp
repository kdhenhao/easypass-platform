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
button,
.buttons,
.btn,
.modal-footer .btn + .btn {
  margin-bottom: 0; }
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
						<h2>托盘管理<small>托盘详情</small></h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<div class="table-responsive">
							<table id="users" class="table table-bordered table-striped table-hover" 
							  cellspacing="0" width="100%">
								<thead>
									<tr class="mytableHead">
										<th class="myRowNumber"></th>
										<th>订单号</th>
										<th>收件人</th>
										<th>替换人</th>
										<th>身份证状态</th>
										<th>航班</th>
										<th>状态</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${orders}" var="order" varStatus="step">
										<tr class="<c:if test="${order.hasPic eq '0'}">warning</c:if>">
											<td class='rn' weight='${order.totalWeight}'>${step.index + 1}</td>
											<td>${order.orderNo}</td>
											<td class="receiverName" realName="<c:if test="${order.hasPic eq '2'}">${order.fakeReceiverName}</c:if><c:if test="${order.hasPic ne '2'}">${order.receiverName}</c:if>">${order.receiverName}</td>
											<td>${order.fakeReceiverName}</td>
											<td>
												<c:if test="${order.hasPic eq '0'}">未上传</c:if>
												<c:if test="${order.hasPic eq '1'}">已上传</c:if>
												<c:if test="${order.hasPic eq '2'}">已替换</c:if>
											</td>
											<td>${order.flightNo}</td>
											<td>
												<c:if test="${order.status eq 'orderReceived'}">订单已从网点接收</c:if>
												<c:if test="${order.status eq 'orderReached'}">订单已抵达仓库</c:if>
												<c:if test="${order.status eq 'orderConfiged'}">订单已配置货物</c:if>
												<c:if test="${order.status eq 'flightConfiged'}">货物已配置航班</c:if>
												<c:if test="${order.status eq 'flighting'}">航班飞往中国中</c:if>
												<c:if test="${order.status eq 'clearancing'}">中国机场清关中</c:if>
												<c:if test="${order.status eq 'clearanced'}">清关完成等待送货</c:if>
												<c:if test="${order.status eq 'sending'}">中国快递配送中</c:if>
											</td>
											<td>
												<a class="btn btn-danger btn-xs" href="javascript:void(0);" onclick="deleteOrder(${order.id});"><i class="fa fa-minus"></i>删除</a>
												<c:if test="${order.hasPic eq '0'}">
												<a class="btn btn-info btn-xs" href="javascript:void(0);" onclick="replaceIdCard(${order.id})"><i class="fa fa-exchange"></i>替换</a>
												</c:if>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div class="container">
							<div class="row">
								<div class="col-md-1 col-xs-12">
									<button type="button" class="btn btn-primary btn-sm" onclick="openModal();">增加快件</button>
								</div>
								<div class="col-md-3 col-xs-12">
									<h4>当前托盘总重量：<span id="weightSum">0</span>&nbsp;(kg)</h4>
								</div>
								<div class="col-md-3 col-xs-12">
									<h4>剩余可用惠州清关单号：<span id="clearNoSum">${clearNoSum}</span>&nbsp;(个)</h4>
								</div>
								<div class="col-md-5 col-xs-12" style="text-align: right;">
									<button type="button" class="disabledButForLabel btn btn-warning btn-sm">未上传身份证</button>
									<button type="button" class="disabledButForLabel btn btn-danger btn-sm">收件人重名</button>
								</div>
							</div>
							<div class="row">
								<div class="col-lg-12">
									<button type="button" class="btn btn-primary btn-sm" onclick="sendSmsAll();">一键短信提醒未上传身份证用户</button>
									<button type="button" class="btn btn-info btn-sm" onclick="replaceAll();">一键替换未上传身份证</button>
									<button type="button" class="btn btn-info btn-sm" onclick="allocateClearNo();">一键分配清关单号</button>
									<button type="button" class="btn btn-primary btn-sm" onclick="downLoadBarcode();">下载惠州打印面单</button>
									<div class="btn-group">
										<button data-toggle="dropdown" 
											id="dropdownMenu1" type="button" aria-expanded="false"
											class="btn btn-primary btn-sm dropdown-toggle" >广州清关数据下载 <span class="caret"></span>
					                    </button>
					                    <ul role="menu" class="dropdown-menu" aria-labelledby="dropdownMenu1">
					                      <li><a href="javascript:void(0);" onclick="downLoadCouriers();">报关数据</a> </li>
					                      <li><a href="javascript:void(0);" onclick="downLoadItems();">物品清单</a> </li>
					                      <li><a href="javascript:void(0);" onclick="downLoadInvoice();">发票文件</a> </li>
					                      <li><a href="javascript:void(0);" onclick="downLoadDeclaration();">个人声明</a> </li>
					                      <li><a href="javascript:void(0);" onclick="downLoadIds();">身份证</a> </li>
					                      <li><a href="javascript:void(0);" onclick="downLoadCustomers();">面单</a> </li>
					                      <li><a href="javascript:void(0);" onclick="downLoadBatch();">批量下载</a> </li>
					                    </ul>
					                 </div>
					                 <button type="button" class="btn btn-primary btn-sm" onclick="downloadDeppon();">下载德邦大客户系统导入文件</button>
					                 <button type="button" class="btn btn-primary btn-sm" onclick="downloadYuantong();">下载圆通导入文件</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /page content -->
			<%@ include file="/WEB-INF/common/foot.jsp"%>
		</div>
	</div>
	
	<div id="orderModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="false"
		aria-hidden="true">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="modelTitle">添加订单</h4>
				</div>
				<div class="modal-body">
					<form role="form" novalidate id="portForm" class="form-horizontal">
						<div class="form-group item">
						    <label for="orderNo" class="col-sm-3 control-label">订单编号：</label>
						    <div class="col-sm-6">
						    	<input type="text" style="display: none;">
						    	<input type="text" class="form-control optional" id="orderNo" name="orderNo" 
						    		placeholder="订单编号">
						    </div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" onclick="closeModal();">取消</button>
					<button type="button" class="btn btn-success" onclick="addOrder();">添加</button>
				</div>
			</div>
		</div>
	</div>
	<form id="downForm">
		<input type="hidden" name="id" value="${id}"/>
	</form>
	<%@ include file="/WEB-INF/common/script.jsp"%>
	<script>
	$(function(){
		//检查重名
		checkName();
		//计算总重量
		checkWeight();
		$("#orderModal").on("shown.bs.modal",function(){
			$("#orderNo").val("").focus();
		});
		$("#orderNo").keydown(function(event){
			if(event.keyCode == 13){
				addOrder();
			}
		});
	});
	function checkName(){
		var rows = $("#users .receiverName");
		var names = {};
		for(var i=0;i<rows.length;i++){
			var name = $(rows[i]).attr("realName");
			if(names[name] == null){
				names[name] = 1;
			}else{
				names[name] = names[name] + 1;
			}
		}
		for(var name in names){
			if(names[name] > 1){
				var tds = $("#users .receiverName");
				for(var i=0;i<tds.length;i++){
					if($(tds[i]).attr("realName") == name){
						$(tds[i]).parent().addClass("danger");
					}
				}
			}
		}
	}
	function checkWeight(){
		var tds = $("#users .rn");
		var weight = 0;
		for(var i=0;i<tds.length;i++){
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
	function openModal(){
		$("#orderModal").modal("show");
	}
	function closeModal(){
		$("#orderModal").modal("hide");
	}
	function addOrder(){
		if($("#orderNo").val()==""){
			swal("","请输入订单号","error");
			return;
		}
		$.ajax({
			"url":ctx+"admin/palletManage/addOrder?id=${id}&orderNo="+$("#orderNo").val(),
			"dataType":"json",
			"success":function(result){
				if(result.status == "0"){
					window.location = ctx+"admin/palletManage/toDetailPage?id=${id}";
				}else{
					swal("",result.msg,"error");
				}
			}
		});
	}
	function deleteOrder(id){
		swal({ "title": "", "text": "确定要移除该订单" ,"type": "warning","showCancelButton":true},function(isConfirm){
			if(isConfirm){
				$.ajax({
					url: ctx+"admin/palletManage/deleteOrder?id="+id+"&time="+(new Date()).getTime(),
					dataType: "json",
					success: function(result){
						if(result.status == "0"){
							window.location = ctx+"admin/palletManage/toDetailPage?id=${id}";
						}else{
							swal({title:'',text:result.msg,type:"error"});
						}
					}
				});
			}
		});
	}
	
	function replaceIdCard(orderId){
		swal({ "title": "", "text": "确定要随机替换身份证" ,"type": "warning","showCancelButton":true},function(isConfirm){
			if(isConfirm){
				$.ajax({
					url: ctx+"admin/palletManage/replaceIdCard?id="+orderId+"&time="+(new Date()).getTime(),
					dataType: "json",
					success: function(result){
						if(result.status == "0"){
							window.location = ctx+"admin/palletManage/toDetailPage?id=${id}";
						}else{
							swal({title:'',text:result.msg,type:"error"});
						}
					}
				});
			}
		});
	}
	function replaceAll(){
		swal({ "title": "", "text": "确定要随机替换所有未上传的身份证" ,"type": "warning","showCancelButton":true},function(isConfirm){
			if(isConfirm){
				$.ajax({
					url: ctx+"admin/palletManage/replaceAll?id=${id}&time="+(new Date()).getTime(),
					dataType: "json",
					success: function(result){
						if(result.status == "0"){
							window.location = ctx+"admin/palletManage/toDetailPage?id=${id}";
						}else{
							swal({title:'',text:result.msg,type:"error"},function(){
								window.location = ctx+"admin/palletManage/toDetailPage?id=${id}";
							});
						}
					}
				});
			}
		});
	}
	function sendSmsAll(){
		swal({ "title": "", "text": "确定要批量发送短信" ,"type": "warning","showCancelButton":true},function(isConfirm){
			if(isConfirm){
				$.ajax({
					url: ctx+"admin/palletManage/sendSmsAll?id=${id}&time="+(new Date()).getTime(),
					dataType: "json",
					success: function(result){
						if(result.status == "0"){
							swal({title:'',text:"发送成功",type:"error"});
						}else{
							swal({title:'',text:result.msg,type:"error"});
						}
					}
				});
			}
		});
	}
	function downLoadIds(){
		if($("#users tr.danger").length > 0 || $("#users tr.warning").length > 0){
			swal({ "title": "", "text": "还有重名或未上传身份证的订单，确定开始下载吗" ,"type": "warning","showCancelButton":true},function(isConfirm){
				if(isConfirm){
					$("#downForm").attr("action",ctx+"admin/palletManage/downLoadIds");
					$("#downForm").submit();
				}
			});
		}else{
			$("#downForm").attr("action",ctx+"admin/palletManage/downLoadIds");
			$("#downForm").submit();
		}
	}
	function downLoadItems(){
		if($("#users tr.danger").length > 0 || $("#users tr.warning").length > 0){
			swal({ "title": "", "text": "还有重名或未上传身份证的订单，确定开始下载吗" ,"type": "warning","showCancelButton":true},function(isConfirm){
				if(isConfirm){
					$("#downForm").attr("action",ctx+"admin/palletManage/downLoadItems");
					$("#downForm").submit();
				}
			});
		}else{
			$("#downForm").attr("action",ctx+"admin/palletManage/downLoadItems");
			$("#downForm").submit();
		}
	}
	function downLoadDeclaration(){
		if($("#users tr.danger").length > 0 || $("#users tr.warning").length > 0){
			swal({ "title": "", "text": "还有重名或未上传身份证的订单，确定开始下载吗" ,"type": "warning","showCancelButton":true},function(isConfirm){
				if(isConfirm){
					$("#downForm").attr("action",ctx+"admin/palletManage/downDeclaration");
					$("#downForm").submit();
				}
			});
		}else{
			$("#downForm").attr("action",ctx+"admin/palletManage/downDeclaration");
			$("#downForm").submit();
		}
	}
	function downLoadCouriers(){
		if($("#users tr.danger").length > 0 || $("#users tr.warning").length > 0){
			swal({ "title": "", "text": "还有重名或未上传身份证的订单，确定开始下载吗" ,"type": "warning","showCancelButton":true},function(isConfirm){
				if(isConfirm){
					$("#downForm").attr("action",ctx+"admin/palletManage/downLoadCouriers");
					$("#downForm").submit();
				}
			});
		}else{
			$("#downForm").attr("action",ctx+"admin/palletManage/downLoadCouriers");
			$("#downForm").submit();
		}
	}
	function downLoadCustomers(){
		if($("#users tr.danger").length > 0 || $("#users tr.warning").length > 0){
			swal({ "title": "", "text": "还有重名或未上传身份证的订单，确定开始下载吗" ,"type": "warning","showCancelButton":true},function(isConfirm){
				if(isConfirm){
					$("#downForm").attr("action",ctx+"admin/palletManage/downLoadCustomers");
					$("#downForm").submit();
				}
			});
		}else{
			$("#downForm").attr("action",ctx+"admin/palletManage/downLoadCustomers");
			$("#downForm").submit();
		}
	}
	function downLoadInvoice(){
		if($("#users tr.danger").length > 0 || $("#users tr.warning").length > 0){
			swal({ "title": "", "text": "还有重名或未上传身份证的订单，确定开始下载吗" ,"type": "warning","showCancelButton":true},function(isConfirm){
				if(isConfirm){
					$("#downForm").attr("action",ctx+"admin/palletManage/downLoadInvoice");
					$("#downForm").submit();
				}
			});
		}else{
			$("#downForm").attr("action",ctx+"admin/palletManage/downLoadInvoice");
			$("#downForm").submit();
		}
	}
	function downLoadBatch(){
		if($("#users tr.danger").length > 0 || $("#users tr.warning").length > 0){
			swal({ "title": "", "text": "还有重名或未上传身份证的订单，确定开始下载吗" ,"type": "warning","showCancelButton":true},function(isConfirm){
				if(isConfirm){
					$("#downForm").attr("action",ctx+"admin/palletManage/downLoadBatch");
					$("#downForm").submit();
				}
			});
		}else{
			$("#downForm").attr("action",ctx+"admin/palletManage/downLoadBatch");
			$("#downForm").submit();
		}
	}
	function downloadDeppon(){
		if($("#users tr.danger").length > 0 || $("#users tr.warning").length > 0){
			swal({ "title": "", "text": "还有重名或未上传身份证的订单，确定开始下载吗" ,"type": "warning","showCancelButton":true},function(isConfirm){
				if(isConfirm){
					$("#downForm").attr("action",ctx+"admin/palletManage/downLoadDeppon");
					$("#downForm").submit();
				}
			});
		}else{
			$("#downForm").attr("action",ctx+"admin/palletManage/downLoadDeppon");
			$("#downForm").submit();
		}
	}
	function downloadYuantong(){
		if($("#users tr.danger").length > 0 || $("#users tr.warning").length > 0){
			swal({ "title": "", "text": "还有重名或未上传身份证的订单，确定开始下载吗" ,"type": "warning","showCancelButton":true},function(isConfirm){
				if(isConfirm){
					$("#downForm").attr("action",ctx+"admin/palletManage/downloadYuantong");
					$("#downForm").submit();
				}
			});
		}else{
			$("#downForm").attr("action",ctx+"admin/palletManage/downloadYuantong");
			$("#downForm").submit();
		}
	}
	function allocateClearNo(){
		swal({ "title": "", "text": "确定要给所有订单分配清关单号" ,"type": "warning","showCancelButton":true},function(isConfirm){
			if(isConfirm){
				$.ajax({
					url: ctx+"admin/palletManage/allocateClearNo?id=${id}&time="+(new Date()).getTime(),
					dataType: "json",
					success: function(result){
						if(result.status == "0"){
							swal({title:'',text:"分配成功",type:"info"});
							$("#clearNoSum").html(result.data.count);
						}else{
							swal({title:'',text:result.msg,type:"error"});
						}
					}
				});
			}
		});
	}
	function downLoadBarcode(){
		if($("#users tr.danger").length > 0 || $("#users tr.warning").length > 0){
			swal({ "title": "", "text": "还有重名或未上传身份证的订单，确定开始下载吗" ,"type": "warning","showCancelButton":true},function(isConfirm){
				if(isConfirm){
					checkClearNo();
				}
			});
		}else{
			checkClearNo();
		}
	}
	function checkClearNo(){
		$.ajax({
			url: ctx+"admin/palletManage/checkClearNo?id=${id}&time="+(new Date()).getTime(),
			dataType: "json",
			success: function(result){
				if(result.status == "0"){
					doDownLoadBarcode();
				}else{
					swal({title:'',text:result.msg,type:"error"});
				}
			}
		});
	}
	function doDownLoadBarcode(){
		$("#downForm").attr("action",ctx+"admin/palletManage/downloadBarcode");
		$("#downForm").submit();
	}
	</script>
</body>
</html>