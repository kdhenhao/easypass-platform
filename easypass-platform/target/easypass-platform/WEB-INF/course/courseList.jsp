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
						<h2>课程管理</h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<form role="form" id="searchForm">
							<div class="container">
								<div class="row">
									<div class="col-lg-2">
										<input class="form-control input-sm" placeholder="课程名称" name="courseName"/>
									</div>
									<div class="col-lg-2" >
										<select class="form-control input-sm"  name="classifyId">
								    		<option value="">--请选择专业--</option>
								    		<c:if test="${classifyList!=null}">
												<c:forEach items="${classifyList}" var="classify">
													<option value="${classify.id}" >${classify.name}</option>
												</c:forEach>
											</c:if>
								    	</select>
									</div>
									<div class="col-lg-2" >
										<select class="form-control input-sm"  name="grade">
								    		<option value="">--请选择年级--</option>
								    		<option value="1">大学一年级</option>
								    		<option value="2">大学二年级</option>
								    		<option value="3">大学三年级</option>
								    		<option value="4">大学四年级</option>
								    	</select>
									</div>
									<div class="col-lg-2" >
										<select class="form-control input-sm"  name="term">
								    		<option value="">--请选择term--</option>
								    		<option value="0">Summer</option>
								    		<option value="1">Fall</option>
								    		<option value="2">Winter</option>
								    	</select>
									</div>
									<div class="col-lg-2" >
										<select class="form-control input-sm"  name="tag">
								    		<option value="">--请选择tag--</option>
								    		<option value="0">视频课程</option>
								    		<option value="1">预约课程</option>
								    		<option value="2">学习小组</option>
								    	</select>
									</div>
									<div class="col-lg-2" >
										<select class="form-control input-sm"  name="campusId" id="campusId">
								    		<option value="">--请选择校区--</option>
								    		<c:if test="${campusList!=null}">
												<c:forEach items="${campusList}" var="campus">
													<option value="${campus.id}">${campus.name}</option>
												</c:forEach>
											</c:if>
								    	</select>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-2">
										<input class="form-control input-sm" placeholder="课程老师" name="teacher"/>
									</div>
									<div class="col-lg-2" >
										<input class="form-control input-sm" readonly="readonly" placeholder="开始时间" name="beginTime" id="beginTime"/>
									</div>
									<div class="col-lg-2" >
										<input class="form-control input-sm" readonly="readonly" placeholder="截至时间" name="endTime" id="endTime"/>
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
										<th>课程名称</th>
										<th>专业</th>
										<th>IOS价格</th>
										<th>价格</th>
										<th>年级</th>
										<th>term</th>
										<th>tag</th>
										<th>校区</th>
										<th>创建时间</th>
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
		$("#course").DataTable({
		    ajax: {
		        url: ctx+'admin/course/getCourseList',
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
                {"data":'courseName'},    
		        {"data":'classifyName'},
		        {"data":'priceIos'},
		        {"data":'price'},
		        {"data":'gradeName'},
		        {"data":'termName'},
		        {"data":'tagName'}, 
		        {"data":'campusName',"render":optCampus},
		        {"data":'createTime'},
		        { "data":'id',"render":optRender},
		        { "data":'tag',"visible" : false}//隐藏列
		    ]
		});
		
		$("#searchForm input").keydown(function(event){
			if(event.keyCode == 13){
				search();
			}
		});
		
		$("#searchForm select").change(search);
		
		  /*初始化一个日历控件*/
		$("#beginTime").datetimepicker({
			minView: "month",
			language:  'zh-CN',
			format: 'yyyy-mm-dd',
			todayBtn:  1,
			autoclose: 1
		});
		$("#endTime").datetimepicker({
			minView: "month",
			language:  'zh-CN',
			format: 'yyyy-mm-dd',
			todayBtn:  1,
			autoclose: 1
		});  
	});
	
	
	/*新增*/
	function addBtn(){
		window.location.href= ctx+ 'admin/course/toEditCoursePage';
	}
	
	/*编辑*/
	function modifyMember(id){
		window.location.href= ctx+ 'admin/course/toEditCoursePage?id='+id;
	}
	
	/*课时列表*/
	function listClassHour(id){
		window.location.href= ctx+ 'admin/courseClassHour/toCourseClassHourPage?id='+id;
	}
	
	function optRender(data, type, row, meta){
		var but = '<a class="btn btn-info btn-xs" href="javascript:modifyMember('+row.id+')"><i class="fa fa-pencil"></i>编辑</a>';
		if(row.tag!=1){
			but = but + '<a class="btn btn-info btn-xs" href="javascript:listClassHour('+row.id+')">课时列表</a>';
		};
		
		but = but + '<a class="btn btn-danger btn-xs" href="javascript:deleteBtn('+row.id+')">删除</a>';
		
		return but;
	}
	
	function optCampus(data,type,row,meta){
		if(row.campusName==null || row.campusName ==''){
			return "";
		}else{
			return row.campusName;
		}
	}
	
	/*删除*/
	function deleteBtn(id){
		swal({
			title:'',
			text:'你确定删除该课程吗，相应的课程下面的课时会一并删除?',
			showCancelButton:true
		},function(isConfirm){
			if(isConfirm){
				$.ajax({
					type:"POST",
					data:{
						id:id
					},
					dataType:"json",
					url:ctx+"admin/course/deleteCourseById",
					success:function(result){
						if(result.status==0){
							searchCurrentPage();
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