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
							惠州清关公司清关条码号导入
						</h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<form role="form" id="importForm" novalidate="novalidate"
							class="form-inline">
							<div class="form-group">
								<label class="control-label"
									for="webAddress">请选择excel<span class="required">*</span>
								</label>
								<input type="text" style="display:none;"/>
								<input type="file" id="fileInput" name="file" class="form-control input-sm" style="height:auto;">
							</div>
							<button type="button" class="btn btn-success btn-sm"
								onclick="uploadExcel();">导入</button>
							<button type="button" class="btn btn-dark btn-sm"
								onclick="returnPage();">返回</button>
						</form>
						<br/>
						<div class="table-responsive">
							<table id="users" class="table table-bordered table-striped table-hover" 
							  cellspacing="0" width="100%" style="display:none;">
								<thead>
									<tr class="mytableHead">
										<th class="myRowNumber"></th>
										<th>分运单号</th>
										<th>报关公司</th>
										<th>客户</th>
										<th>获取时间</th>
										<th>导入状态</th>
										<th>错误原因</th>
									</tr>
								</thead>
								<tbody>								
								</tbody>
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
	<script type="text/javascript" src="${ctx}resources/js/ajaxfileupload.js"></script>
	<script>
	var taskId;
	function uploadExcel(){
		var fileName = $("#fileInput").val();
		var fileType = fileName.substring(fileName.lastIndexOf(".")+1);
		if(fileType != "xls"){
			swal("","请选择excel格式的文件","error");
			return;
		}
		swal({"title":"","text":"正在导入……请稍后","showConfirmButton":false});
		$.ajaxFileUpload({
			url : ctx+'admin/clearNo/importExcel', //用于文件上传的服务器端请求地址
			secureuri : false, //是否需要安全协议，一般设置为false
			fileElementId : 'fileInput', //文件上传域的ID
			dataType : 'json', //返回值类型 一般设置为json
			success : function(data, status) {
				data = $.parseJSON(data);
				if(data.status == "0"){
					taskId = data.data.taskId;
					setInterval(updateStatus,1000);
				}else{
					swal({title:'',text:result.msg,type:"error"});
				}
			},
			error : function(data, status, e) {
				swal({title:'',text:result.msg,type:"error"});
			}
		})
	}
	function returnPage(){
		window.location = ctx + "admin/clearNo/toListPage";
	}
	function updateStatus(){
		$.ajax({
			url: ctx+"admin/clearNo/findStatus?taskId="+taskId+"&time="+(new Date()).getTime(),
			dataType: "json",
			success: function(result){
				if(result.data.flag == "true"){
					swal.close();
					getResult();
				}else{
					swal({"title":"","text":"已处理"+result.data.count+"条记录……请稍后","showConfirmButton":false});
				}
			}
		});
	}
	
	function getResult(){
		$.ajax({
			url: ctx+"admin/clearNo/findImportResult?taskId="+taskId+"&time="+(new Date()).getTime(),
			dataType: "json",
			success: function(result){
				if(result.data != null){
					rebuildTable(result.data.list);
				}
			}
		});
	}
	
	function rebuildTable(list){
		$("#users").show();
		$("#users tbody").empty();
		for(var i=0;i<list.length;i++){
			var row = list[i];
			var $tr = $("<tr></tr>");
			if(row.success == "1"){
				$tr.addClass("danger");
			}
			$tr.append("<td>"+(i+2)+"</td>");
			$tr.append("<td>"+nvl(row.clearNo)+"</td>");
			$tr.append("<td>"+nvl(row.clearCompany)+"</td>");
			$tr.append("<td>"+nvl(row.custom)+"</td>");
			$tr.append("<td>"+nvl(row.time)+"</td>");
			if(row.success == "1"){
				$tr.append("<td>导入失败</td><td>"+nvl(row.msg)+"</td>");
			}else{
				$tr.append("<td>导入成功</td><td></td>");
			}
			$("#users tbody").append($tr);
		}
	}
	function nvl(obj){
		if(obj == null || typeof(obj) == "undefined"){
			return "";
		}
		return obj;
	}
	</script>
</body>
</html>