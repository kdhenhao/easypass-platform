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
						<h2>单页管理</h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<div class="table-responsive">
							<table id="users" class="table table-bordered table-striped table-hover" 
							  cellspacing="0" width="100%">
								<thead>
									<tr class="mytableHead">
										<th class="myRowNumber"></th>
										<th>单页名称</th>
										<th>修改时间</th>
										<th>修改人</th>
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
	<%@ include file="/WEB-INF/common/script.jsp"%>
	<script>
	$(function(){
		var t = $("#users").DataTable({
		    ajax: {
		        url: ctx+'admin/brochure/findBrochures',
		        type: 'POST'
		    },
		    columns: [
		        { "data":null,"render":rowNumberRender},
                { "data":'title'},    
		        { "data":'modifyTm'},
		        { "data":'modifyBy'},
		        { "data":'id',"render":optRender}
		    ]
		});
	});
	function optRender(data, type, row, meta){
		var but = '<a class="btn btn-info btn-xs" href="javascript:showDetail('+meta.row+')"><i class="fa fa-pencil"></i>编辑</a>';
		return but;
	}
	function showDetail(index,event){
		var row = $('#users').DataTable().rows().data()[index];
		window.location = ctx+"admin/brochure/toEditPage?id="+row.id
	}
	</script>
</body>
</html>