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
			<div class="right_col" role="main">
				<div class="row">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="x_panel">
							<div class="x_title">
								<h2>
									订单管理 <small>修改订单</small>
								</h2>
								<div class="clearfix"></div>
							</div>
							<div class="x_content">
								<form role="form" id="orderForm" novalidate="novalidate" class="form-horizontal form-label-left">
									<div class="form-group item">
										<label class="control-label col-md-2 col-sm-2 col-xs-12" 
											for="orderNo">订单号<span class="required">*</span>
										</label>
										<div class="col-md-8 col-sm-8 col-xs-12">
											<input type="hidden" name="id" value="${order.id}"/>
											<input type="text" id="orderNo" name="orderNo" value="${order.orderNo}" required="required" data-validate-length-range="0,20"
												class="form-control">
										</div>
									</div>
									<div class="form-group item">
										<label class="control-label col-md-2 col-sm-2 col-xs-12"
											for="senderName">寄件人信息<span class="required">*</span>
										</label>
										<div class="col-md-4 col-sm-4 col-xs-6">
											<div class="input-group">
												<input type="hidden" id="senderNameInput" name="senderName" value="${order.senderName}"/>
												<select class="form-control" id="senderName">
										    	</select>
                            					<span class="input-group-btn">
                                              		<button type="button" class="btn btn-success" onclick="addSender();">新增</button>
                                          		</span>
											</div>
										</div>
										<div class="col-md-4 col-sm-4 col-xs-6">
											<input type="text" id="senderPhone" value="${order.senderPhone}" name="senderPhone" required="required" data-validate-length-range="0,20"
												class="form-control" placeholder="联系电话">
										</div>
									</div>
									<div class="form-group item">
										<label class="control-label col-md-2 col-sm-2 col-xs-12" 
											for="senderProvince">
										</label>
										<div class="col-md-2 col-sm-2 col-xs-6">
											<input type="text" id="senderProvince" value="${order.senderProvince}" name="senderProvince" required="required" data-validate-length-range="0,20"
												class="form-control" placeholder="省">
										</div>
										<div class="col-md-2 col-sm-2 col-xs-6">
											<input type="text" id="senderCity" name="senderCity" value="${order.senderCity}" required="required" data-validate-length-range="0,20"
												class="form-control" placeholder="市">
										</div>
										<div class="col-md-2 col-sm-2 col-xs-6">
											<input type="text" id="senderDistrict" name="senderDistrict" value="${order.senderDistrict}" required="required" data-validate-length-range="0,20"
												class="form-control" placeholder="区">
										</div>
										<div class="col-md-2 col-sm-2 col-xs-6">
											<input type="text" id="senderAddress" name="senderAddress" value="${order.senderAddress}" required="required" data-validate-length-range="0,20"
												class="form-control" placeholder="详细地址">
										</div>
									</div>
									
									<div class="form-group item">
										<label class="control-label col-md-2 col-sm-2 col-xs-12"
											for="receiverName">收件人信息<span class="required">*</span>
										</label>
										<div class="col-md-4 col-sm-4 col-xs-6">
											<div class="input-group">
												<input type="hidden" id="receiverNameInput" name="receiverName" value="${order.receiverName}"/>
												<select class="form-control" id="receiverName">
										    	</select>
                            					<span class="input-group-btn">
                                              		<button type="button" class="btn btn-success" onclick="addReceiver();">新增</button>
                                          		</span>
											</div>
										</div>
										<div class="col-md-4 col-sm-4 col-xs-6">
											<input type="text" id="receiverPhone" value="${order.receiverPhone}" name="receiverPhone" required="required" data-validate-length-range="0,20"
												class="form-control" placeholder="联系电话">
										</div>
									</div>
									<div class="form-group item">
										<label class="control-label col-md-2 col-sm-2 col-xs-12" 
											for="receiverProvince">
										</label>
										<div class="col-md-2 col-sm-2 col-xs-6">
											<input type="hidden" id="receiverProvinceInput" name="receiverProvince" value="${order.receiverProvince}"/>
											<select id="receiverProvince" name="receiverProvinceCode" class="form-control required" onchange="onProvinceChange();">
												<option value="">--请选择--</option>
												<c:forEach items="${provinces}" var="opt">
													<option value="${opt.areaCode}" <c:if test="${order.receiverProvinceCode eq opt.areaCode}">selected="selected"</c:if>>${opt.areaName}</option>
												</c:forEach>
											</select>
										</div>
										<div class="col-md-2 col-sm-2 col-xs-6">
											<input type="hidden" id="receiverCityInput" name="receiverCity" value="${order.receiverCity}"/>
											<select id="receiverCity" name="receiverCityCode" class="form-control required"
												onchange="onCityChange();">
												<option value="">--请选择--</option>
												<c:forEach items="${citys}" var="opt">
													<option value="${opt.areaCode}" <c:if test="${order.receiverCityCode eq opt.areaCode}">selected="selected"</c:if>>${opt.areaName}</option>
												</c:forEach>
											</select>
										</div>
										<div class="col-md-2 col-sm-2 col-xs-6">
											<input type="hidden" id="receiverDistrictInput" name="receiverDistrict" value="${order.receiverDistrict}"/>
											<select id="receiverDistrict" name='receiverDistrictCode' 
												class="form-control required" onchange="onDistrictChange();">
												<option value="">--请选择--</option>
												<c:forEach items="${districts}" var="opt">
													<option value="${opt.areaCode}" <c:if test="${order.receiverDistrictCode eq opt.areaCode}">selected="selected"</c:if>>${opt.areaName}</option>
												</c:forEach>
											</select>
										</div>
										<div class="col-md-2 col-sm-2 col-xs-6">
											<input type="text" id="receiverAddress" value="${order.receiverAddress}" name="receiverAddress" required="required" data-validate-length-range="0,20"
												class="form-control" placeholder="详细地址">
										</div>
									</div>
									<div class="form-group item">
										<label class="control-label col-md-2 col-sm-2 col-xs-12" 
											for="commodities">商品信息
										</label>
										<div class="col-md-8 col-sm-8 col-xs-12">
											<div class="x_panel">
												<div class="x_title">
													<ul class="nav navbar-left panel_toolbox">
														<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
														<li><a onclick="openCommodityModal();"><i class="fa fa-plus"></i></a></li>
													</ul>
													<div class="clearfix"></div>
												</div>
												<div class="x_content">
													<table id="commodityTable" class="table table-bordered table-striped table-hover">
														<thead>
															<tr class="myTableHead">
																<th>名称</th>
																<th>英文名称</th>
																<th>品牌</th>
																<th>数量</th>
																<th>操作</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach items="${commodities}" var="opt">
																<tr updateId="${opt.id}" commodityId="${opt.commodityId}">
																	<td>${opt.cnName}</td>
																	<td>${opt.enName}</td>
																	<td>${opt.brand}</td>
																	<td><a class='btn btn-info btn-xs' href='javascript:void(0);' onclick='countMinus(this);'><i class='fa fa-minus'></i></a><span class='count'>${opt.count}</span>&nbsp;<a class='btn btn-info btn-xs' href='javascript:void(0);' onclick='countPlus(this);'><i class='fa fa-plus'></i></a></td>
																	<td><a class='btn btn-danger btn-xs' href='javascript:void(0);' onclick='deleteCommodity(this);'>删除</a></td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</div>
											</div>
										</div>
									</div>
									<div class="form-group item">
										<label class="control-label col-md-2 col-sm-2 col-xs-12" 
											for="endpointId">网点<span class="required">*</span>
										</label>
										<div class="col-md-8 col-sm-8 col-xs-12">
											<select id="endpointId" name="endpointId" class="form-control required">
												<option value="">--请选择--</option>
												<c:forEach items="${endpoints}" var="opt">
													<option value="${opt.id}" <c:if test="${order.endpointId eq opt.id}">selected="selected"</c:if>>${opt.pointName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group item">
										<label class="control-label col-md-2 col-sm-2 col-xs-12" 
											for="totalWeight">重量<span class="required">*</span>
										</label>
										<div class="col-md-8 col-sm-8 col-xs-12">
											<input type="number" id="totalWeight" value="${order.totalWeight}" name="totalWeight" required="required" data-validate-minmax="0,99999999"
												class="form-control" placeholder="重量">
										</div>
									</div>
									<div class="form-group item">
										<label class="control-label col-md-2 col-sm-2 col-xs-12" 
											for="totalPrice">价格<span class="required">*</span>
										</label>
										<div class="col-md-8 col-sm-8 col-xs-12">
											<input type="number" id="totalPrice" name="totalPrice" value="${order.totalPrice}" required="required" data-validate-minmax="0,99999999"
												class="form-control" placeholder="价格">
										</div>
									</div>
									<div class="form-group item">
										<label class="control-label col-md-2 col-sm-2 col-xs-12" 
											for="payMethod">付款方式<span class="required">*</span>
										</label>
										<div class="col-md-8 col-sm-8 col-xs-12">
											<select id="payMethod" name="payMethod" class="form-control required">
												<option value="">--请选择--</option>
												<option value="card" <c:if test="${order.payMethod eq 'card'}">selected="selected"</c:if>>刷卡</option>
												<option value="cash" <c:if test="${order.payMethod eq 'cash'}">selected="selected"</c:if>>现金</option>
											</select>
											<input type="hidden" id="commodityIdsInput" name="commodityIds"/>
						    				<input type="hidden" id="countsInput" name="counts"/>
						    				<input type="hidden" id="updateIds" name="updateIds"/>
										</div>
									</div>
									<div class="ln_solid"></div>
									<div class="form-group">
										<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
											<button type="button" class="btn btn-dark" onclick="back();">返回</button>
											<button type="button" class="btn btn-success" onclick="updateOrder();">保存</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /page content -->
			<%@ include file="/WEB-INF/common/foot.jsp"%>
		</div>
	</div>
	<!-- 新增寄件人 -->
	<div id="senderModal" class="modal fade" role="dialog" data-backdrop="false"
		aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="modelTitle">寄件人管理</h4>
				</div>
				<div class="modal-body">
					<form role="form" novalidate id="senderForm" class="form-horizontal">
						<div class="form-group item">
						    <label for="clientName" class="col-sm-3 control-label">客户姓名：</label>
						    <div class="col-sm-6">
						    	<input type="hidden" name="id" id="senderId">
						    	<input type="text" class="form-control" id="clientName" name="clientName" 
						    		placeholder="客户姓名" required='required' data-validate-length-range="0,10">
						    </div>
						</div>
						<div class="form-group item">
						    <label for="phone" class="col-sm-3 control-label">电话：</label>
						    <div class="col-sm-6">
						    	<input type="text" class="form-control" id="phone" name="phone" 
						    		placeholder="电话" required='required' data-validate-length-range="0,20">
						    </div>
						</div>
						<div class="form-group item">
						    <label for="province" class="col-sm-3 control-label">省市区：</label>
						    <div class="col-sm-2">
						    	<input type="text" class="form-control" id="province" name="province">
						    </div>
						    <div class="col-sm-2">
						    	<input type="text" class="form-control" id="city" name="city">
						    </div>
						    <div class="col-sm-2">
						    	<input type="text" class="form-control optional" id="district" name="district">
						    </div>
						</div>
						<div class="form-group item">
						    <label for="address" class="col-sm-3 control-label">详细地址：</label>
						    <div class="col-sm-6">
						    	<input type="text" class="form-control" id="address" name="address"
						    		placeholder="详细地址" required="required" data-validate-length-range="0,50">
						    </div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" onclick="closeSenderModal();">取消</button>
					<button type="button" class="btn btn-success" onclick="saveOrUpdateSender();">保存</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 新增收件人 -->
	<div id="receiverModal" class="modal fade" role="dialog" data-backdrop="false"
		aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="modelTitle">收件人管理</h4>
				</div>
				<div class="modal-body">
					<form role="form" novalidate id="receiverForm" class="form-horizontal">
						<div class="form-group item">
						    <label for="clientName" class="col-sm-3 control-label">客户姓名：</label>
						    <div class="col-sm-6">
						    	<input type="hidden" name="id" id="receiverId">
						    	<input type="text" class="form-control" id="clientName2" name="clientName" 
						    		placeholder="客户姓名" required='required' data-validate-length-range="0,10">
						    </div>
						</div>
						<div class="form-group item">
						    <label for="phone" class="col-sm-3 control-label">电话：</label>
						    <div class="col-sm-6">
						    	<input type="text" class="form-control" id="phone2" name="phone" 
						    		placeholder="电话" required='required' data-validate-length-range="0,20">
						    </div>
						</div>
						<div class="form-group item">
						    <label for="province" class="col-sm-3 control-label">省市区：</label>
						    <div class="col-sm-2">
						    	<select id="provinceCode" name="provinceCode" class="form-control required" onchange="onProvinceChange2();">
						    		<option value="">--请选择--</option>
						    		<c:forEach items="${provinces}" var="opt">
						    			<option value="${opt.areaCode}">${opt.areaName}</option>
						    		</c:forEach>
						    	</select>
						    	<input type="hidden" id="province2" name="province">
						    </div>
						    <div class="col-sm-2">
						    	<select id="cityCode" name="cityCode" class="form-control required" onchange="onCityChange2();">
						    		<option value="">--请选择--</option>
						    	</select>
						    	<input type="hidden" id="city2" name="city">
						    </div>
						    <div class="col-sm-2">
						    	<select id="districtCode" name="districtCode" class="form-control required" onchange="onDistrictChange2();">
						    		<option value="">--请选择--</option>
						    	</select>
						    	<input type="hidden" id="district2" name="district">
						    </div>
						</div>
						<div class="form-group item">
						    <label for="address" class="col-sm-3 control-label">详细地址：</label>
						    <div class="col-sm-6">
						    	<input type="text" class="form-control" id="address2" name="address"
						    		placeholder="详细地址" required="required" data-validate-length-range="0,50">
						    </div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" onclick="closeReceiverModal();">取消</button>
					<button type="button" class="btn btn-success" onclick="saveOrUpdateReceiver();">保存</button>
				</div>
			</div>
		</div>
	</div>
	
	<div id="commodityChooseModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="false"
		aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" onclick="closeChooseModal();"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="modelTitle">选择商品</h4>
				</div>
				<div class="modal-body">
					<form id="searchForm">
					<div class="row">
						<div class="col-lg-3">
							<input type="hidden" name="taxNo"/>
							<input class="form-control input-sm" placeholder="名称" name="cnName"/>
						</div>
						<div class="col-lg-3">
							<input class="form-control input-sm" placeholder="品牌" name="brand"/>
						</div>
						<div class="col-lg-3">
							<input class="form-control input-sm" placeholder="条码号" name="barcode"/>
						</div>
						<div class="col-lg-3">
							<button type="button" class="btn btn-success btn-sm" onclick="openAddCommodityModal();">新增</button>
							<button type="button" class="btn btn-primary btn-sm" onclick="search();">搜索</button>
						</div>
					</div>
					</form>
					<div class="row">
						<table id="beds" class="table table-bordered table-striped table-hover" 
						  cellspacing="0" width="100%">
							<thead>
								<tr class="myTableHead">
									<th></th>
									<th>中文名称</th>
									<th>英文名称</th>
									<th>品牌</th>
									<th>条码号</th>
									<th>操作</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 新增商品 -->
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
					<button type="button" class="btn btn-default" onclick="closeCommodityModal();">取消</button>
					<button type="button" class="btn btn-success" onclick="saveOrUpdateCommodity();">保存</button>
				</div>
			</div>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/common/script.jsp"%>
	<script>
	var showCommodifyAddWin = false;
	$(function(){
		var $senderName = $('#senderName').select2({
			"ajax":{
				url: ctx+"admin/orderCreate/findSenders",
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
		$('#senderName').on('select2:select',onSenderSelect);
		$('#receiverName').select2({
			"ajax":{
				url: ctx+"admin/orderCreate/findReceivers",
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
		$('#receiverName').on('select2:select',onReceiverSelect);
		//选中
		$("#select2-senderName-container").html("${order.senderName}");
		$("#select2-receiverName-container").html("${order.receiverName}");
		//$("#senderName").empty().append("<option value='-1'>${order.senderName}</option>");
		//$senderName.select2().trigger('change');
		
		$("#commodityModal").on("shown.bs.modal",function(){
			//新增商品窗口关闭后打开 商品选择
			if(!modalInit){
				modalInit = true;
				initSelect2();
			}
		});
		$("#commodityModal").on("hidden.bs.modal",function(){
			//新增商品窗口关闭后打开 商品选择
			$("#commodityChooseModal").modal("show");
		});
		$("#commodityChooseModal").on("hidden.bs.modal",function(){
			if(showCommodifyAddWin){
				$("#commodityModal").modal("show");
			}
		});
	});
	function formatRepo(repo) {
		if (repo.loading){
			return repo.text;
		}
		repo.text = repo.clientName; 
		var div = "<div><h2>"+repo.clientName+" | "+repo.phone+"</h2>"
		div = div + repo.province+" "+repo.city+" "+repo.district+" "+repo.address+"</div>";
		return div;
	}
	function formatRepoSelection(repo) {
		repo.text = repo.clientName;
		return repo.clientName;
	}

	function onSenderSelect(){
		var senderId = $("#senderName option:selected").attr("value");
		if(senderId == ""){
			$("#senderNameInput").val("");
			$("#senderPhone").val("");
			$("#senderProvince").val("");
			$("#senderCity").val("");
			$("#senderDistrict").val("");
			$("#senderAddress").val("");
		}else{
			doOnSenderSelect(senderId);
		}
	}
	function doOnSenderSelect(senderId){
		$.ajax({
			"url":ctx+"admin/orderCreate/findClientById?id="+senderId,
			"dataType":"json",
			"success":function(result){
				$("#senderNameInput").val(result.clientName);
				$("#senderPhone").val(result.phone);
				$("#senderProvince").val(result.province);
				$("#senderCity").val(result.city);
				$("#senderDistrict").val(result.district);
				$("#senderAddress").val(result.address);
			}
		});	
	}
	function onReceiverSelect(){
		var receiverId = $("#receiverName option:selected").attr("value");
		if(receiverId == ""){
			$("#receiverNameInput").val("");
			$("#receiverPhone").val("");
			$("#receiverProvince").val("");
			$("#receiverProvinceInput").val("");
			$("#receiverCity").val("");
			$("#receiverCityInput").val("");
			$("#receiverDistrictInput").val("");
			$("#receiverDistrict").val("");
			$("#receiverAddress").val("");
		}else{
			doOnReceiverSelect(receiverId);
		}
	}
	function doOnReceiverSelect(receiverId){
		$.ajax({
			"url":ctx+"admin/orderCreate/findClientById?id="+receiverId,
			"dataType":"json",
			"success":function(result){
				$("#receiverNameInput").val(result.clientName);
				$("#receiverPhone").val(result.phone);
				$("#receiverProvince").val(result.provinceCode);
				onProvinceChange(function(){
					$("#receiverCity").val(result.cityCode);
					onCityChange(function(){
						$("#receiverDistrict").val(result.districtCode);
						onDistrictChange();
					});
				});
				$("#receiverAddress").val(result.address);
			}
		});	
	}
	
	function onProvinceChange(callback){
		$("#receiverProvinceInput").val($("#receiverProvince option:selected").text());
		var province = $("#receiverProvince").val();
		$("#receiverCity").empty().append("<option value=''>--请选择--</option>");
		$("#receiverDistrict").empty().append("<option value=''>--请选择--</option>");
		if(province == null || province == ""){
			return;
		}
		$.ajax({
			url : ctx + "admin/area/findByParent?parent="+province,
			dataType : 'json',
			success : function(result) {
				for(var i=0;i<result.length;i++){
					$("#receiverCity").append("<option value='" + result[i].areaCode+ "'>"
							+ result[i].areaName + "</option>");
				}
				if(callback != null){
					callback();
				}
			}
		});
	}
	function onCityChange(callback){
		$("#receiverCityInput").val($("#receiverCity option:selected").text());
		var city = $("#receiverCity").val();
		$("#receiverDistrict").empty().append("<option value=''>--请选择--</option>");
		if(city == null || city == ""){
			return;
		}
		$.ajax({
			url : ctx + "admin/area/findByParent?parent="+city,
			dataType : 'json',
			success : function(result) {
				for(var i=0;i<result.length;i++){
					$("#receiverDistrict").append("<option value='" + result[i].areaCode+ "'>"
							+ result[i].areaName + "</option>");
				}
				if(callback != null){
					callback();
				}
			}
		});
	}
	function onDistrictChange(){
		$("#receiverDistrictInput").val($("#receiverDistrict option:selected").text());
	}
	//添加 
	function addSender(){
		$("#senderForm").find("input,select").val("").parent().siblings(".alert").remove();
		$("#senderForm").find("input,select").parents(".item").removeClass("bad");
		
		$('#senderModal').modal("show");
	}
	function closeSenderModal(){
		$('#senderModal').modal("hide");
	}
	function addReceiver(){
		$("#receiverForm").find("input,select").val("").parent().siblings(".alert").remove();
		$("#receiverForm").find("input,select").parents(".item").removeClass("bad");
		
		$('#receiverModal').modal("show");
	}
	function closeReceiverModal(){
		$('#receiverModal').modal("hide");
	}
	function saveOrUpdateSender(){
		if( !validator.checkAll( $("#senderForm") ) ){
			return;
		}
		$.ajax({
			url: ctx+"admin/orderCreate/saveOrUpdateSender?time="+(new Date()).getTime(),
			dataType: "json",
			data:$("#senderForm").serialize(),
			success: function(result){
				if(result.status == "0"){
					$('#senderModal').modal("hide");
					//选中select2
					$("#senderName").empty().append("<option value='"+result.data.id+"'>"+result.data.clientName+"</option>");
					$("#senderName").select2().val(result.data.id).trigger('change');
					doOnSenderSelect(result.data.id);
				}else{
					swal({title:'',text:result.msg,type:"error"});
				}
			}
		});
	}
	function saveOrUpdateReceiver(){
		if( !validator.checkAll( $("#receiverForm") ) ){
			return;
		}
		$.ajax({
			url: ctx+"admin/orderCreate/saveOrUpdateReceiver?time="+(new Date()).getTime(),
			dataType: "json",
			data:$("#receiverForm").serialize(),
			success: function(result){
				if(result.status == "0"){
					$('#receiverModal').modal("hide");
					$("#receiverName").empty().append("<option value='"+result.data.id+"'>"+result.data.clientName+"</option>");
					$("#receiverName").select2().val(result.data.id).trigger('change');
					doOnReceiverSelect(result.data.id);
				}else{
					swal({title:'',text:result.msg,type:"error"});
				}
			}
		});
	}
	function onProvinceChange2(callback){
		$("#province2").val($("#provinceCode option:selected").text());
		var province = $("#provinceCode").val();
		$("#cityCode").empty().append("<option value=''>--请选择--</option>");
		$("#districtCode").empty().append("<option value=''>--请选择--</option>");
		if(province == null || province == ""){
			return;
		}
		$.ajax({
			url : ctx + "admin/area/findByParent?parent="+province,
			dataType : 'json',
			success : function(result) {
				for(var i=0;i<result.length;i++){
					$("#cityCode").append("<option value='" + result[i].areaCode+ "'>"
							+ result[i].areaName + "</option>");
				}
				if(callback != null){
					callback();
				}
			}
		});
	}
	function onCityChange2(callback){
		$("#city2").val($("#cityCode option:selected").text());
		var city = $("#cityCode").val();
		$("#districtCode").empty().append("<option value=''>--请选择--</option>");
		if(city == null || city == ""){
			return;
		}
		$.ajax({
			url : ctx + "admin/area/findByParent?parent="+city,
			dataType : 'json',
			success : function(result) {
				for(var i=0;i<result.length;i++){
					$("#districtCode").append("<option value='" + result[i].areaCode+ "'>"
							+ result[i].areaName + "</option>");
				}
				if(callback != null){
					callback();
				}
			}
		});
	}
	function onDistrictChange2(){
		$("#district2").val($("#districtCode option:selected").text());
	}
	
	
	//商品相关
	var tableInit = false;
	function openCommodityModal(){
		$("#commodityChooseModal").modal("show");
		if(tableInit){
			$("#searchForm input").val("");
			search();
		}else{
			tableInit = true;
			initTable();
		}
	}
	function closeChooseModal(){
		showCommodifyAddWin = false;
		$("#commodityChooseModal").modal("hide");
	}
	function initTable(){
		$("#beds").DataTable({
		    ajax: {
		        url: ctx+'admin/orderCreate/findCommodities',
		        type: 'POST',
		        data: function(d){$.extend(d,serializeObject($("#searchForm")))}
		    },
		    columns: [
				{ "data":null,"render":rowNumberRender},
                { "data": "cnName"},
                { "data": "enName"},
                { "data": "brand" },
                { "data": "barcode"},
                { "data": "id","render":optRender}
		    ]
		});
	}
	function search(){
		$('#beds').DataTable().ajax.reload(function(d){$.extend(d,serializeObject($("#searchForm")))}, true);;
	}
	function optRender(data, type, row, meta){
		return '<a class="btn btn-primary btn-xs" href="javascript:addCommoToOrder('+meta.row+')"><i class="fa fa-check"></i>添加</a>';
	}
	var modalInit = false;
	function openAddCommodityModal(){
		$("#commodityForm").find("input,select").val("").parent().siblings(".alert").remove();
		$("#commodityForm").find("input,select").parents(".item").removeClass("bad");
		showCommodifyAddWin = true;
		$('#commodityChooseModal').modal("hide");
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
	function closeCommodityModal(){
		$('#commodityModal').modal("hide");
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
	function saveOrUpdateCommodity(){
		if( !validator.checkAll( $("#commodityForm") ) ){
			return;
		}
		$.ajax({
			url: ctx+"admin/orderCreate/saveOrUpdateCommodity?time="+(new Date()).getTime(),
			dataType: "json",
			data:$("#commodityForm").serialize(),
			success: function(result){
				if(result.status == "0"){
					swal({title:'',text:"操作成功"},function(){
						$('#commodityModal').modal("hide");
						search();
					});
				}else{
					swal({title:'',text:result.msg,type:"error"});
				}
			}
		});
	}
	
	
	function addCommoToOrder(index){
		var row = $('#beds').DataTable().rows().data()[index];
		var trs = $("#commodityTable tbody").find("tr");
		var found = false;
		for(var i=0;i<trs.length;i++){
			if($(trs[i]).attr("commodityId") == row.id){
				found = true;
				//相同产品 数量加一
				var count = Number($(trs[i]).find(".count").html());
				$(trs[i]).find(".count").html(count+1)
			}
		}
		if(!found){
			//添加新行
			$("#commodityTable tbody").append("<tr updateId='' commodityId='"+row.id+"'><td>"+row.cnName+"</td><td>"+row.enName+"</td><td>"+row.brand+"</td><td><a class='btn btn-info btn-xs' href='javascript:void(0);' onclick='countMinus(this);'><i class='fa fa-minus'></i></a><span class='count'>"+1+"</span>&nbsp;<a class='btn btn-info btn-xs' href='javascript:void(0);' onclick='countPlus(this);'><i class='fa fa-plus'></i></a></td><td><a class='btn btn-danger btn-xs' href='javascript:void(0);' onclick='deleteCommodity(this);'>删除</a></td></tr>");
		}
	}
	
	function countPlus(obj){
		var count = Number($(obj).prev().html());
		$(obj).prev().html(count + 1);
	}
	function countMinus(obj){
		var count = Number($(obj).next().html());
		if(count == 1){
			$(obj).parents("tr").remove();
		}else{
			$(obj).next().html(count - 1);
		}
	}
	function deleteCommodity(obj){
		$(obj).parents("tr").remove();
	}
	
	
	//保存
	function updateOrder(){
		if( !validator.checkAll( $("#orderForm") ) ){
			return;
		}
		var trs = $("#commodityTable tbody").find("tr");
		if(trs.length == 0){
			swal("","请至少添加一个商品","warning")
			return;
		}
		var updateIds = "";
		var commodityIds = "";
		var counts = "";
		for(var i=0;i<trs.length;i++){
			var updateid = $(trs[i]).attr("updateId");
			if(updateid == ""){
				updateid = " ";
			}
			updateIds = updateIds + updateid + ",";
			commodityIds = commodityIds + $(trs[i]).attr("commodityId") +",";
			counts = counts + Number($(trs[i]).find(".count").html())+",";
		}
		$("#commodityIdsInput").val(commodityIds);
		$("#countsInput").val(counts);
		$("#updateIds").val(updateIds);
		$.ajax({
			url: ctx+"admin/orderManage/updateOrder?time="+(new Date()).getTime(),
			dataType: "json",
			data:$("#orderForm").serialize(),
			success: function(result){
				if(result.status == "0"){
					swal({title:'',text:"操作成功"},function(){
						back();
					});
				}else{
					swal({title:'',text:result.msg,type:"error"});
				}
			}
		});
	}
	function back(){
		window.location = ctx+"admin/orderManage/toOrderPage";
	}
	</script>
</body>
</html>