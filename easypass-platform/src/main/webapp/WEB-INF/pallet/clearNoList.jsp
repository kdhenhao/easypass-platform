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
						<h2>惠州清关单号管理</h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<form role="form" id="searchForm">
							<div class="container">
								<div class="row">
									<div class="col-lg-3">
										<input class="form-control input-sm" placeholder="龙门运单号" name="orderNo"/>
									</div>
									<div class="col-lg-3">
										<input class="form-control input-sm" placeholder="清关单号" name="clearNo" id="clearNo"/>
									</div>
									<div class="col-lg-3">
										<select class="form-control input-sm" name="status">
											<option value="">--请选择--</option>
											<option value="0">未分配</option>
											<option value="1">已分配</option>
										</select>
									</div>
									<div class="col-lg-3">
										<button type="button" class="btn btn-success btn-sm" onclick="importExcel();">导入</button>
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
										<th>清关单号</th>
										<th>龙门运单号</th>
										<th>导入时间</th>
										<th>状态</th>
										<th>分配时间</th>
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
		var t = $("#users").DataTable({
		    ajax: {
		        url: ctx+'admin/clearNo/findClearNos',
		        type: 'POST',
		        data: function(d){$.extend(d,serializeObject($("#searchForm")))}
		    },
		    columns: [
		        { "data":null,"render":rowNumberRender},
                { "data":'clearNo'},
		        { "data":'orderNo'},
		        { "data":'importTm'},
		        { "data":'status',"render":statusRender},
		        { "data":'allocateTm'}
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

	function statusRender(data, type, row, meta){
		if(row.status == "0"){
			return "未分配";
		}else if(row.status == "1"){
			return "已分配";
		}
		return "";
	}
	
	function importExcel(){
		window.location = ctx+"admin/clearNo/toImportPage";
	}
	
	</script>
</body>
</html>