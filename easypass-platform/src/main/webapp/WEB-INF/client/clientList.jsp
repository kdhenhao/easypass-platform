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
						<h2>客户管理</h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<form role="form" id="searchForm">
							<div class="container">
								<div class="row">
									<div class="col-lg-3">
										<input class="form-control input-sm" placeholder="姓名" name="map['clientName']"/>
									</div>
									<div class="col-lg-3">
										<input class="form-control input-sm" placeholder="电话" name="map['phone']"/>
									</div>
									<div class="col-lg-3">
										<select class="form-control input-sm" name="map['role']">
											<option value="">--请选择--</option>
											<option value="receiver">收件人</option>
											<option value="sender">寄件人</option>
										</select>
									</div>
									<div class="col-lg-3">
										<button type="button" class="btn btn-success btn-sm" onclick="addSender();">新增寄件人</button>
										<button type="button" class="btn btn-success btn-sm" onclick="addReceiver();">新增收件人</button>
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
										<th>姓名</th>
										<th>角色</th>
										<th>电话</th>
										<th>地址</th>
										<th>身份证</th>
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
						    	<select id="provinceCode" name="provinceCode" class="form-control required" onchange="onProvinceChange();">
						    		<option value="">--请选择--</option>
						    		<c:forEach items="${provinces}" var="opt">
						    			<option value="${opt.areaCode}">${opt.areaName}</option>
						    		</c:forEach>
						    	</select>
						    	<input type="hidden" id="province2" name="province">
						    </div>
						    <div class="col-sm-2">
						    	<select id="cityCode" name="cityCode" class="form-control required" onchange="onCityChange();">
						    		<option value="">--请选择--</option>
						    	</select>
						    	<input type="hidden" id="city2" name="city">
						    </div>
						    <div class="col-sm-2">
						    	<select id="districtCode" name="districtCode" class="form-control required" onchange="onDistrictChange();">
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

	<%@ include file="/WEB-INF/common/script.jsp"%>
	<script>
	$(function(){
		var t = $("#users").DataTable({
		    ajax: {
		        url: ctx+'admin/client/findClients',
		        type: 'POST',
		        data: function(d){$.extend(d,serializeObject($("#searchForm")))}
		    },
		    columns: [
		        { "data":null,"render":rowNumberRender},
                { "data":'clientName'},
		        { "data":'role',"render":roleRender},
		        { "data":'phone'},
		        { "data":'address',"render":addressRender},
		        { "data":'hasPic',"render":picRender},
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
	function roleRender(data, type, row, meta){
		if(row.role == "sender"){
			return "寄件人";
		}else{
			return "收件人";
		}
	}
	function picRender(data, type, row, meta){
		if(row.hasPic == "0"){
			return "未上传";
		}else{
			return "已上传";
		}
	}
	function addressRender(data, type, row, meta){
		if(row.role == "sender"){
			return ifnull(row.province)+" "+ifnull(row.city)+" "+ifnull(row.district)+" "+ifnull(row.address);
		}else{
			if(row.province == row.city){
				return row.districtCode+" "+row.province+row.district+row.address;
			}else{
				return row.districtCode+" "+row.province+row.city+row.district+row.address;	
			}
		}
	}
	function ifnull(str){
		if(str == null || typeof(str) == "undefined"){
			return "";
		}
		return str;
	}
	function optRender(data, type, row, meta){
		var but = '<a class="btn btn-info btn-xs" href="javascript:showDetail('+meta.row+')"><i class="fa fa-pencil"></i>编辑</a>';
		but = but + '<a class="btn btn-danger btn-xs" href="javascript:deleteById('+row.id+')"><i class="fa fa-minus"></i>删除</a>';
		return but;
	}
	
	function showDetail(index){
		var row = $('#users').DataTable().rows().data()[index];
		if(row.role == "sender"){
			$("#senderForm").find("input,select").val("").parent().siblings(".alert").remove();
			$("#senderForm").find("input,select").parents(".item").removeClass("bad");
			
			$("#senderId").val(row.id);
			$("#clientName").val(row.clientName);
			$("#phone").val(row.phone);
			$("#address").val(row.address);
			$("#province").val(row.province);
			$("#city").val(row.city);
			$("#district").val(row.district);
			
			$('#senderModal').modal("show");	
		}else{
			$("#receiverForm").find("input,select").val("").parent().siblings(".alert").remove();
			$("#receiverForm").find("input,select").parents(".item").removeClass("bad");
			
			$("#receiverId").val(row.id);
			$("#clientName2").val(row.clientName);
			$("#phone2").val(row.phone);
			$("#address2").val(row.address);
			$("#province2").val(row.province);
			$("#provinceCode").val(row.provinceCode);
			onProvinceChange(function(){
				$("#city2").val(row.city);
				$("#cityCode").val(row.cityCode);
				onCityChange(function(){
					$("#district2").val(row.district);
					$("#districtCode").val(row.districtCode);
					onDistrictChange();
				});
			});
			
			$('#receiverModal').modal("show");
		}
		
	}
	
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
			url: ctx+"admin/client/saveOrUpdateSender?time="+(new Date()).getTime(),
			dataType: "json",
			data:$("#senderForm").serialize(),
			success: function(result){
				if(result.status == "0"){
					swal({title:'',text:"操作成功"},function(){
						$('#senderModal').modal("hide");
						searchCurrentPage();
					});
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
			url: ctx+"admin/client/saveOrUpdateReceiver?time="+(new Date()).getTime(),
			dataType: "json",
			data:$("#receiverForm").serialize(),
			success: function(result){
				if(result.status == "0"){
					swal({title:'',text:"操作成功"},function(){
						$('#receiverModal').modal("hide");
						searchCurrentPage();
					});
				}else{
					swal({title:'',text:result.msg,type:"error"});
				}
			}
		});
	}
	
	
	
	function onProvinceChange(callback){
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
	function onCityChange(callback){
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
	function onDistrictChange(){
		$("#district2").val($("#districtCode option:selected").text());
	}
	
	
	function deleteById(id){
		swal({ "title": "", "text": "确定要删除吗" ,"type": "warning","showCancelButton":true},function(isConfirm){
			if(isConfirm){
				$.ajax({
					url: ctx+"admin/client/deleteById?id="+id+"&time="+(new Date()).getTime(),
					dataType: "json",
					success: function(result){
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