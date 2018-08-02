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
			<div class="right_col" role="main" id="main">
				<div class="x_panel">
					<div class="x_title">
						<h2>新闻管理</h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<div class="table-responsive">
							<table id="campusNews" class="table table-bordered table-striped table-hover" 
							  cellspacing="0" width="100%">
								<thead>
									<tr class="mytableHead">
										<th>序号</th>
										<th>校区</th>
										<th>关联网址</th>
										<th>创建时间</th>
										<th>更新时间</th>
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
		$("#campusNews").DataTable({
		    ajax: {
		        url: ctx+'admin/campusNews/findNews',
		        type: 'POST',
		        beforeSend :function(xmlHttp){ 
		            xmlHttp.setRequestHeader("If-Modified-Since","0"); 
		            xmlHttp.setRequestHeader("Cache-Control","no-cache");
		         },
		        cache:false
		    },
		    columns: [
                {"data":null,"render":rowNumberRender,"width":'50'},   
                {"data":'campusName'},   
		        {"data":'campusUrl'},
		        {"data":'createTm'},
		        {"data":'modifyTm'},
		        { "data":'id',"render":optRender}
		    ]
		});
	});

	function optRender(data, type, row, meta){
		var but = '<a class="btn btn-info btn-xs" href="javascript:addOrUpdate('+meta.row+')"><i class="fa fa-pencil"></i>保存</a>';
		return but;
	}

	/* 保存或更新 */
	function addOrUpdate(index){
		var row = $('#campusNews').DataTable().rows().data()[index];
		var id=$("#id").val();
		var url;
		if(id==null  || id == 0){//新增
			url=domain+"admin/campusNews/addNews";
		}else{
			url=domain+"admin/campusNews/updateNews";
		}
		$.ajax({
			url: url,
			dataType: "json",
			data:{campusId : row.campusId,
				  campusUrl : row.campusUrl
				  },
			success: function(result){
				if(result.status == "0"){
					swal({title:'',text:"保存成功"});
				}else{
					swal({title:'',text:result.msg,type:"error"});
				}
			}
		});
	}

	</script>
</body>
</html>