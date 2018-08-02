<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>

<%@ include file="/WEB-INF/common/head.jsp"%>

</head>
<body class="nav-md">
	<div class="container body">
		<div class="main_container">
			<%@ include file="/WEB-INF/common/left.jsp"%>
			<%@ include file="/WEB-INF/common/top.jsp"%>
			<!-- page content -->
			<!--  
			<div class="right_col" role="main" id="main">
				<div class="x_panel">
					<div class="x_title">
						<h2>配置管理<small>配置修改-${toolsConfig.name}</small></h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<div >
							<textarea  name="content" id="content" >${toolsConfig.content}</textarea>
						</div>
						
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="text-align:center">
							<button type="button" class="btn btn-success" onclick="save();">保存</button>
							<button type="button" class="btn btn-default" onclick="cancelEdit();">取消</button>
						</div>
					</div>
				</div>
				
				
				
				
				
			</div> -->
			
					<div class="right_col" role="main" id="main">
				<div class="x_panel">
					<div class="x_title">
						<h2>配置管理<small>配置修改-${appAboutUs.name}</small></h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<form class="form-horizontal" role="form" novalidate enctype="multipart/form-data" method="post" id="updateAboutUsForm" >
								<input type="hidden" name="id" id="id" value="${appAboutUs.id}">
								<input type="hidden" name="logoImg" id="logoImg" value="${appAboutUs.logoImg}">
								<input type="hidden" name="qrCodeImg" id="qrCodeImg" value="${appAboutUs.qrCodeImg}">
								<input type="hidden" name="dir"  value="images">
								
								<div style="display:none;" class="form-group item">
									<div class="col-lg-2" style="text-align: right;" >
										<label class="control-label">logo：</label>
									</div>
									<div class="col-lg-4" style="height: 210px">
										<div class="col-lg-4" style="height: 100px;" id="logoImgDiv">
											<img alt="" src="
											<c:if test="${not empty appAboutUs.logoImg}">${imgDomain}/${appAboutUs.logoImg}</c:if>
											<c:if test="${empty appAboutUs.logoImg}">${ctx}resources/images/default.jpeg</c:if>"  width="300" height="200" id="logoImgs">
										</div><br>
									</div>
								</div>
								<div style="display: none;" class="form-group item">
									<div class="col-lg-2">
										<label class="control-label"></label>
									</div>
									<div class="col-lg-4">
										<input type="file" id="logoFile"  name="file1" class="hidden"/>
										<a class="btn btn-primary" href="javascript:void(0)" onclick="$('#logoFile').click()"><i class="fa fa-folder-open fa-lg"></i>选择...</a>
									</div>
								</div>
								
								<div class="form-group item">
									<div class="col-lg-2" style="text-align: right;" >
										<label class="control-label">二维码：</label>
									</div>
									<div class="col-lg-4" style="height: 210px">
										<div class="col-lg-4" style="height: 100px;" id="qrCodeImgDiv">
											<img alt="" src="
											<c:if test="${not empty appAboutUs.qrCodeImg}">${imgDomain}/${appAboutUs.qrCodeImg}</c:if>
											<c:if test="${empty appAboutUs.qrCodeImg}">${ctx}resources/images/default.jpeg</c:if>"  width="300" height="200" id="qrCodeImgs">
										</div><br>
									</div>
								</div>
								<div class="form-group item">
									<div class="col-lg-2">
										<label class="control-label"></label>
									</div>
									<div class="col-lg-4">
										<input type="file" id="logoFile2"  name="file2" onchange="fileUpload();" class="hidden"/>
										<a class="btn btn-primary" href="javascript:void(0)" onclick="$('#logoFile2').click()"><i class="fa fa-folder-open fa-lg"></i>选择...</a>
									</div>
									
								</div>
								
								<div class="form-group item">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">电话：</label>
									</div>
									<div class="col-lg-6">
										<%-- <input type="tel" class="form-control "  placeholder="请输入电话" name="telephone" id="telephone"  value="${appAboutUs.telephone}" data-validate-length-range="0,100" > --%>
										
										<input type="text" class="form-control optional required" id="telephone" name="telephone" value="${appAboutUs.telephone}" pattern="tel" placeholder="请输入电话" >
									</div>
									
									
									
									<div class="col-lg-4">
									</div>
								</div>
								<div class="form-group item">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">微信公众号：</label>
									</div>
									<div class="col-lg-6">
										<input type="text" class="form-control optional required"  placeholder="请输入微信公众号" name="wechatPublicNumber" id="wechatPublicNumber"  value="${appAboutUs.wechatPublicNumber}" data-validate-length-range="0,30" >
									</div>
									<div class="col-lg-4">
									</div>
								</div>
								<div class="form-group item">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">Email：</label>
									</div>
									<div class="col-lg-6">
										<input type="email" class="form-control optional required"  placeholder="请输入email" name="email" id="email" value="${appAboutUs.email}" data-validate-length-range="0,100" >
									</div>
									<div class="col-lg-4">
									</div>
								</div>
								<div style="display: none;" class="form-group item">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">Description：</label>
									</div>
									<div class="col-lg-6">
										<textarea rows="10" cols="100" placeholder="请输入description" name="description" id="description">${appAboutUs.description}</textarea>
									</div>
									<div class="col-lg-4">
									</div>
									
								</div>
								<div class="form-group item">
									<div class="col-lg-4" >
										<label class="control-label">&nbsp;</label>
									</div>
									<div class="col-lg-1" >
									</div>
									<div class="col-lg-1" >
										<input type="submit" class="form-control btn btn-success" id="save" value="保存"/>
									</div>
									<div class="col-lg-1" >
										<input type="button" class="form-control btn btn-default" value="取消" onclick="cancelEdit();"/>
									</div>
								</div>
<!-- 							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="text-align:center"> -->
<!-- 								<button type="button" class="btn btn-success" onclick="save();">保存</button> -->
<!-- 								<input type=submit class="form-control btn btn-success" id="save"  value="保存"/> -->
<!-- 								<button type="button" class="btn btn-default" onclick="cancelEdit();">取消</button> -->
<!-- 							</div> -->
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
		
		$("#logoFile2").click(function(){
			new uploadPreview({ UpBtn: "logoFile2", DivShow: "qrCodeImgDiv", ImgShow: "qrCodeImgs" });
		});
	});
	
	
	$("#updateAboutUsForm").submit(function(){
		 if( !validator.checkAll( $("#updateAboutUsForm") ) ){
			return;
		} 
		
		$("#updateAboutUsForm").attr("action",ctx+"admin/toolsConfig/updateAboutUs?time="+(new Date()).getTime());
		
		
	});  
	
	function cancelEdit() {
		window.location = ctx + "admin/toolsConfig/toolsConfigList";
	}

	function save() {
		var str = $('#content').val();
		if(str == ""){
			alert(str);
			swal("","内容不能为空","error");
			return;
		}
		$.ajax({
		    type: "post",
		    url: ctx+ "admin/toolsConfig/updateConfig",
		    data:{"id":"${toolsConfig.id}","content":str},
		    dataType: 'json',
		    success: function(result) {
		        if (result.status == 0) {
		            swal({"title":"", "text":"保存成功","type":"info"}, cancelEdit);
		        } else {
		        	swal({"title":"", "text":result.msg,"type":"error"});
		        }
		    }
		});
	}
	</script>
</body>
</html>