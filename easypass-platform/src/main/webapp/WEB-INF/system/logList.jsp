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
						<h2>操作日志</h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<form role="form" id="searchForm">
							<div class="container">
								<div class="row">
									<div class="col-lg-3">
										<select id="optType" name="optType" class="form-control input-sm">
											<option value="">--请选择--</option>
											<option value="系统管理">系统管理</option>
											<option value="课程管理">课程管理</option>
											<option value="课时管理">课时管理</option>
											<option value="专业管理">专业管理</option>
											<option value="订单管理">订单管理</option>
											<option value="用户管理">用户管理</option>
											<option value="评论管理">评论管理</option>
											<option value="使用帮助管理">使用帮助管理</option>
											<option value="推送消息管理">推送消息管理</option>
										</select>
									</div>
									<div class="col-lg-3">
										<input class="form-control input-sm" placeholder="管理员名称" name="username"/>
									</div>
									<div class="col-lg-2">
									    <input type="text" id="startDate" name="startDate" readonly="readonly"
											class="form-control input-sm" placeholder="起始日期">
									</div>
									<div class="col-lg-2">
										<input type="text" id="endDate" name="endDate" readonly="readonly"
												class="form-control input-sm" placeholder="截止日期">
									</div>
									<div class="col-lg-2">
										<button type="button" class="btn btn-primary btn-sm" onclick="search();">搜索</button>
									</div>
								</div>
							</div>
						</form>
						<div class="table-responsive">
							<table id="logTable" class="table table-bordered table-striped table-hover"
								cellspacing="0" width="100%">
								<thead>
									<tr class="mytableHead">
										<th>模块名称</th>
										<th>操作账号</th>
										<th>帐号名称</th>
										<th>操作描述</th>
										<th>操作时间</th>
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
			$('#startDate').daterangepicker(datePickerOpt);
			$('#endDate').daterangepicker(datePickerOpt);
			$("#logTable").DataTable({
			    ajax: {
			        url: ctx+'admin/log/findLogs',
			        type: 'POST',
			        data: function(d){$.extend(d,serializeObject($("#searchForm")))}
			    },
			    columns: [
	                { "data":'operateModel'},    
			        { "data":'username'},
			        { "data":'fullName',},
			        { "data":'message'},
			        { "data":'operateDate'}
			    ]
			});
			//绑定事件
			$("#searchForm input").keydown(function(event){
				if(event.keyCode == 13){
					search();
				}
			});
			$("#searchForm select").change(search);
			$("#search").click(search);
		});
		function search(){
			$('#logTable').DataTable().ajax.reload(function(d){$.extend(d,serializeObject($("#searchForm")))}, true);;
		}
	</script>
</body>
</html>