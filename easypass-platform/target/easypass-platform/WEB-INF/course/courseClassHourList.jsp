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
						<h2>课时管理</h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						
						<form role="form" id="searchForm">
							<input type="hidden" name="courseId" id="courseId" value="${courseId}">
							<div class="container">
								<div class="row">
									<div class="col-lg-2">
										<input class="form-control input-sm" placeholder="课时名称" name="classHourName"/>
									</div>
									
									<div class="col-lg-2 ">
										<button type="button" class="btn btn-success btn-sm" onclick="addBtn();">新增</button>
										<button type="button" class="btn btn-primary btn-sm" onclick="search();">搜索</button>
									</div>
								</div>
							</div>
						</form>
						<div class="table-responsive">
							<table id="course" class="table table-bordered table-striped table-hover" 
							  cellspacing="0" width="100%">
								<thead>
									<tr class="mytableHead">
										<th>排序</th>
										<th>课时名称</th>
										<th>课时章节</th>
										<th>课时价格</th>
										<th>课时税额</th>
										<th>创建时间</th>
										<th>操作</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
					<button type="button" class="btn btn-info" onclick="back();">返回</button>
				</div>
			</div>
			<!-- /page content -->
			<%@ include file="/WEB-INF/common/foot.jsp"%>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/common/script.jsp"%>
	<script>
	$(function(){
		$("#course").DataTable({
		    ajax: {
		        url: ctx+'admin/courseClassHour/getCourseClassHourList',
		        type: 'POST',
		        beforeSend :function(xmlHttp){ 
		            xmlHttp.setRequestHeader("If-Modified-Since","0"); 
		            xmlHttp.setRequestHeader("Cache-Control","no-cache");
		         },
		        cache:false,
		        data: function(d){$.extend(d,serializeObject($("#searchForm")))}
		    },
		    columns: [
		    	{"data":'sort'},  
                {"data":'classHourName'},    
		        {"data":'lessonPeriod'},
		        {"data":'price'},
		        {"data":'onTax'},
		        {"data":'createTime'},
		        { "data":'id',"render":optRender}
		    ]
		});
		
		$("#searchForm input").keydown(function(event){
			if(event.keyCode == 13){
				search();
			}
		});
		
		$("#searchForm select").change(search);
		

	});
	var courseId = $("#courseId").val();
	
	/*新增*/
	function addBtn(){
		window.location.href= ctx+ 'admin/courseClassHour/toEditCourseClassHourPage?courseId='+courseId;
	}
	
	/*编辑*/
	function modifyClassHour(id){
		window.location.href= ctx+ 'admin/courseClassHour/toEditCourseClassHourPage?id='+id+'&courseId='+courseId;
	}
	
	/*返回上一级*/
	function back() {
        window.location = ctx+'admin/course/toCoursePage?time='+(new Date()).getTime();
    }
	
	function optRender(data, type, row, meta){
		var but = '<a class="btn btn-info btn-xs" href="javascript:modifyClassHour('+row.id+')"><i class="fa fa-pencil"></i>编辑</a>';
		but = but + '<a class="btn btn-danger btn-xs" href="javascript:deleteBtn('+row.id+')">删除</a>';
		return but;
	}
	/*删除*/
	function deleteBtn(id){
		swal({
			title:'',
			text:'你确定删除吗?',
			showCancelButton:true
		},function(isConfirm){
			if(isConfirm){
				$.ajax({
					type:"POST",
					data:{
						id:id
					},
					dataType:"json",
					url:ctx+"admin/courseClassHour/deleteCourseClassHourById",
					success:function(result){
						if(result.status==0){
							searchCurrentPage()
						}else{
							swal({
								title:"",
								text:result.msg,
								type:"error"
							});
						}
					}
				});
			}
		});
	}
	function search(){
		$('#course').DataTable().ajax.reload(function(d){$.extend(d,serializeObject($("#searchForm")))}, true);;
	}
	function searchCurrentPage(){
		$('#course').DataTable().ajax.reload(function(d){$.extend(d,serializeObject($("#searchForm")))}, false);;
	}

	</script>
</body>
</html>