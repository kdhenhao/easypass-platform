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
						<h2>公告管理</h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<div class="container">
							<div class="row">
								<div class="col-lg-3 col-sm-3 col-xs-6">
									<button type="button" class="btn btn-success btn-sm" onclick="add();">新增</button>
								</div>
							</div>
						</div>
						<div class="table-responsive">
							<table id="users" class="table table-bordered table-striped table-hover" 
							  cellspacing="0" width="100%">
								<thead>
									<tr class="mytableHead">
										<th class="myRowNumber"></th>
										<th>公告标题</th>
										<th>状态</th>
										<th>发布时间</th>
										<th>发布人</th>
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
		        url: ctx+'admin/news/findNews',
		        type: 'POST'
		    },
		    columns: [
		        { "data":null,"render":rowNumberRender},
                { "data":'title'},
                { "data":'isPublish',"render":statusRender},
		        { "data":'publishTm'},
		        { "data":'publishBy'},
		        { "data":'id',"render":optRender}
		    ]
		});
	});
	function searchCurrentPage(){
		$('#users').DataTable().ajax.reload(function(d){$.extend(d,serializeObject($("#searchForm")))}, false);;
	}
	function optRender(data, type, row, meta){
		var but = '<a class="btn btn-info btn-xs" href="javascript:showDetail('+meta.row+')"><i class="fa fa-pencil"></i>编辑</a>';
		but = but + '<a class="btn btn-danger btn-xs" href="javascript:deleteById('+row.id+')"><i class="fa fa-minus"></i>删除</a>';
		if(row.isPublish == "0"){
			but = but + '<a class="btn btn-warning btn-xs" href="javascript:publishById('+row.id+')"><i class="fa fa-paper-plane"></i>发布</a>';
		}
		return but;
	}
	function statusRender(data, type, row, meta){
		if(row.isPublish == "0"){
			return "草稿";
		}else{
			return "已发布";
		}
	}
	function showDetail(index,event){
		var row = $('#users').DataTable().rows().data()[index];
		window.location = ctx+"admin/news/toEditPage?id="+row.id
	}
	function add(){
		window.location = ctx+"admin/news/toEditPage";
	}
	function publishById(id){
		$.ajax({
			url: ctx+"admin/news/publishById?id="+id+"&time="+(new Date()).getTime(),
			dataType: "json",
			success: function(result){
				if(result.status == "0"){
					swal({title:'',text:"发布成功"},function(){
						searchCurrentPage();
					});
				}else{
					swal({title:'',text:result.msg,type:"error"});
				}
			}
		});
	}
	function deleteById(id){
		swal({ "title": "", "text": "确定要删除该公告" ,"type": "warning","showCancelButton":true},function(isConfirm){
			if(isConfirm){
				$.ajax({
					url: ctx+"admin/news/deleteById?id="+id+"&time="+(new Date()).getTime(),
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