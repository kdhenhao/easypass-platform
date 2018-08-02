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
							<!-- <div class="x_title">
								<h2>系统字典管理</h2>
								<div class="clearfix"></div>
							</div> -->
							<div class="x_content">
								<div class="" role="tabpanel" data-example-id="togglable-tabs">
									<ul id="myTab" class="nav nav-tabs bar_tabs" role="tablist">
										<li role="presentation" class="active"><a
											href="#tab_content1" id="home-tab" role="tab"
											data-toggle="tab" aria-expanded="true">web首页参数设置</a></li>
										<li role="presentation" class=""><a href="#tab_content2"
											role="tab" id="profile-tab" data-toggle="tab"
											aria-expanded="false">德邦物流对接参数设置</a></li>
									</ul>
									<div id="myTabContent" class="tab-content">
										<div role="tabpanel" class="tab-pane fade active in"
											id="tab_content1" aria-labelledby="home-tab">
											<form role="form" id="dictForm" novalidate="novalidate"
												class="form-horizontal form-label-left">
												<div class="form-group item">
													<label class="control-label col-md-3 col-sm-3 col-xs-12"
														for="webAddress">总部地址<span class="required">*</span>
													</label>
													<div class="col-md-6 col-sm-6 col-xs-12">
														<input type="text" id="webAddress" name="webAddress"
															value="${webAddress}" required="required"
															data-validate-length-range="0,200" class="form-control">
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-md-3 col-sm-3 col-xs-12"
														for="webPhone">联系电话<span class="required">*</span>
													</label>
													<div class="col-md-6 col-sm-6 col-xs-12">
														<input type="text" id="webPhone" name="webPhone"
															value="${webPhone}" required="required"
															data-validate-length-range="0,200" class="form-control">
													</div>
												</div>
												<div class="form-group item">
													<label class="control-label col-md-3 col-sm-3 col-xs-12"
														for="webWechat">微信<span class="required">*</span>
													</label>
													<div class="col-md-6 col-sm-6 col-xs-12">
														<input type="text" id="webWechat" name="webWechat"
															value="${webWechat}" required="required"
															data-validate-length-range="0,200" class="form-control">
													</div>
												</div>
												<div class="form-group item">
													<label class="control-label col-md-3 col-sm-3 col-xs-12"
														for="webEmail">邮箱<span class="required">*</span>
													</label>
													<div class="col-md-6 col-sm-6 col-xs-12">
														<input type="text" id="webEmail" name="webEmail"
															value="${webEmail}" required="required"
															data-validate-length-range="0,200" class="form-control">
													</div>
												</div>
												<div class="form-group item">
													<label class="control-label col-md-3 col-sm-3 col-xs-12"
														for="webServiceTime">营业时间<span class="required">*</span>
													</label>
													<div class="col-md-6 col-sm-6 col-xs-12">
														<input type="text" id="webServiceTime" name="webServiceTime"
															value="${webServiceTime}" required="required"
															data-validate-length-range="0,200" class="form-control">
													</div>
												</div>
												<div class="form-group item">
													<label class="control-label col-md-3 col-sm-3 col-xs-12"
														for="webQQ">客服qq<span class="required">*</span>
													</label>
													<div class="col-md-6 col-sm-6 col-xs-12">
														<input type="text" id="webQQ" name="webQQ"
															value="${webQQ}" required="required"
															data-validate-length-range="0,200" class="form-control">
													</div>
												</div>
												<div class="form-group item">
													<label class="control-label col-md-3 col-sm-3 col-xs-12"
														for="webQrcode">二维码<span class="required">*</span>
													</label>
													<div class="col-md-6 col-sm-6 col-xs-12">
														<input type="hidden" name="webQrcode" id="webQrcode" value="${webQrcode}" /> <img
															id="qrImg" src="${imgDomain}/${webQrcode}"
															style="width: 150px; height: 150px;"> <input
															type="file" id="file" name="file" onchange="uploadAuto();" />
													</div>
												</div>
												<div class="ln_solid"></div>
												<div class="form-group">
													<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
														<button type="button" class="btn btn-success"
															onclick="save();">保存</button>
													</div>
												</div>
											</form>
										</div>
										<div role="tabpanel" class="tab-pane fade" id="tab_content2"
											aria-labelledby="profile-tab">
											<form role="form" id="depponForm" novalidate="novalidate"
												class="form-horizontal form-label-left">
												<div class="form-group item">
													<label class="control-label col-md-3 col-sm-3 col-xs-12"
														for="depponCompany">发件公司<span class="required">*</span>
													</label>
													<div class="col-md-6 col-sm-6 col-xs-12">
														<input type="text" id="depponCompany" name="depponCompany"
															value="${depponCompany}" required="required"
															data-validate-length-range="0,200" class="form-control">
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-md-3 col-sm-3 col-xs-12"
														for="depponPhone">发件人联系电话<span class="required">*</span>
													</label>
													<div class="col-md-6 col-sm-6 col-xs-12">
														<input type="text" id="depponPhone" name="depponPhone"
															value="${depponPhone}" required="required"
															data-validate-length-range="0,200" class="form-control">
													</div>
												</div>
												<div class="form-group item">
													<label class="control-label col-md-3 col-sm-3 col-xs-12"
														for="depponSender">发件人<span class="required">*</span>
													</label>
													<div class="col-md-6 col-sm-6 col-xs-12">
														<input type="text" id="depponSender" name="depponSender"
															value="${depponSender}" required="required"
															data-validate-length-range="0,200" class="form-control">
													</div>
												</div>
												<div class="form-group item">
													<label class="control-label col-md-3 col-sm-3 col-xs-12"
														for="depponAddress">发件地址<span class="required">*</span>
													</label>
													<div class="col-md-6 col-sm-6 col-xs-12">
														<input type="text" id="depponAddress" name="depponAddress"
															value="${depponAddress}" required="required"
															data-validate-length-range="0,200" class="form-control">
													</div>
												</div>
												<div class="ln_solid"></div>
												<div class="form-group">
													<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
														<button type="button" class="btn btn-success"
															onclick="saveDeppon();">保存</button>
													</div>
												</div>
											</form>
										</div>
									</div>
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
	<%@ include file="/WEB-INF/common/script.jsp"%>
	<script type="text/javascript"
		src="${ctx}resources/js/ajaxfileupload.js"></script>
	<script>
	function uploadAuto() {
		swal({"title":"","text":"正在处理……请稍后","showConfirmButton":false});
		$.ajaxFileUpload({
			url : ctx+'admin/upload/uploadImg?dir=images', //用于文件上传的服务器端请求地址
			secureuri : false, //是否需要安全协议，一般设置为false
			fileElementId : 'file', //文件上传域的ID
			dataType : 'json', //返回值类型 一般设置为json
			success : function(data, status) //服务器成功响应处理函数
			{
				swal.close();
				data = $.parseJSON(data);
				if(data.status == "0"){
					$("#qrImg").attr("src",data.data.src);
					$("#webQrcode").val(data.data.img)
				}else{
					swal({title:'',text:result.msg,type:"error"});
				}
			},
			error : function(data, status, e)//服务器响应失败处理函数
			{
				swal({title:'',text:result.msg,type:"error"});
			}
		})
	}
	function save(){
		if( !validator.checkAll( $("#dictForm") ) ){
			return;
		}
		if($("#webQrcode").val() == ""){
			swal({title:'',text:"请上传二维码图片",type:"error"});
		}
		swal({"title":"","text":"正在处理……请稍后","showConfirmButton":false});
		$.ajax({
			"url": ctx+"admin/dict/update",
			"data": $("#dictForm").serialize(),
			"dataType":"json",
			"success":function(result){
				if(result.status == "0"){
					swal({title:'',text:"保存成功",type:"info"});
				}else{
					swal({title:'',text:result.msg,type:"error"});
				}
			}
		});
	}
	function saveDeppon(){
		if( !validator.checkAll( $("#depponForm") ) ){
			return;
		}
		swal({"title":"","text":"正在处理……请稍后","showConfirmButton":false});
		$.ajax({
			"url": ctx+"admin/dict/updateDeppon",
			"data": $("#depponForm").serialize(),
			"dataType":"json",
			"success":function(result){
				if(result.status == "0"){
					swal({title:'',text:"保存成功",type:"info"});
				}else{
					swal({title:'',text:result.msg,type:"error"});
				}
			}
		});
	}
	</script>
</body>
</html>