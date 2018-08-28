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
					<form role="form" id="searchForm">
							 <div class="container">
								<div class="row">
									<div class="col-lg-3">
										<button type="button" class="btn btn-success btn-sm" onclick="addBtn();">新增</button>
									</div>
								</div>
							</div> 
						</form>
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

	<div id="addModalDiv" class="modal fade" tabindex="-1" role="dialog" data-backdrop="false"
		aria-hidden="true">
		<div class="modal-dialog modal-md">
			<div class="modal-content" style="width: 900px;">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="modelTitle">新增</h4>
				</div>
				<div class="modal-body">
					<form role="form" novalidate id="newsForm" class="form-horizontal">
						<div class="form-group item">
						     <div class="col-sm-6">
						    	 <input type="hidden" name="id" id="id"> 
						    </div>
						</div>
						
						<div class="form-group item">
							<label for="fullName2" class="col-sm-3 control-label" >关联校区：</label>
						    <div class="col-sm-6">
						    	<select class="select2_multiple form-control required" multiple="multiple" name="campusId" id="campusId" required='required'>
									<option value="">--请选择校区--</option>
									<c:if test="${campusList!=null}">
										<c:forEach items="${campusList}" var="campus">
											<option value="${campus.id}">${campus.name}</option>
										</c:forEach>
									</c:if>
								</select>
						    </div>
						    <label class="control-label" style="color: #ccc;">*必填项</label>
						</div>
						
						<div class="form-group item">
							<label for="fullName2" class="col-sm-3 control-label">校区URL：</label>
						    <div class="col-sm-6">
						    	<input type="text" class="form-control required"  name="campusUrl" id="campusUrl" 
						    		placeholder="校区URL" required='required'>
						    </div>
						    <label class="control-label" style="color: #ccc;">*必填项</label>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" onclick="closeModal();">取消</button>
					<button type="button" class="btn btn-success" onclick="saveModal();">保存</button>
				</div>
			</div>
		</div>
	</div>

	<%@ include file="/WEB-INF/common/script.jsp"%>
	<script>
	$(function(){
		$("#campusNews").DataTable({
		    ajax: {
		        url: ctx+'admin/campusNews/findNews',
		        type: 'POST',
		        data: function(d){$.extend(d,serializeObject($("#searchForm")))}
		    },
		    columns: [
                {"data":null,"render":rowNumberRender,"width":'50'},   
                {"data":'campusName'},   
		        {"data":'campusUrl'},
		        {"data":'createTime'},
		        {"data":'modifyTime'},
		        {"data":'id',"render":optRender}
		    ]
		});
		
		$("#searchForm input").keydown(function(event){
			if(event.keyCode == 13){
				search();
			}
		});
		
		$("#searchForm select").change(search);
	});
	
	function closeModal(){
		$('#addModalDiv').modal("hide");
	}

	var flag=0;
	/*新增*/
	function addBtn(){
		$("#modelTitle").html("新增");
		flag=0;
		$("#newsForm")[0].reset();
		$("#addModalDiv").modal("show");
	}
	
	/*编辑*/
	function modify(index){
		//清空表单
		$("#newsForm")[0].reset();
		flag=1;
		var row=$("#campusNews").DataTable().rows().data()[index];
		$("#modelTitle").html("编辑");
		$("#id").val(row.id);
		$("#campusId").val(row.campusId);
		$("#campusUrl").val(row.campusUrl);
		/* var imgStr=$("[name='img']").val();
		if(imgStr==null || imgStr==''){
			$("#imgDiv").append("<input type='hidden' id='img' name='img'/>");
		} */
		
		$("#addModalDiv").modal("show");
		
	}
	
	$.ajaxSetup({
		  async: false
	});
	
	/*保存*/
	function saveModal(){
		
		if( !validator.checkAll( $("#newsForm") ) ){
			return;
		}
		/* var imgStr=$("[name='img']").val();
		if(imgStr==null || imgStr==''){
			//$("#imgDiv").append("<input type='hidden' name='img' value='"+$("#img").val()+"'/>");
			$("#imgDiv").append("<input type='hidden' id='img' name='img'/>");
		} */
		var url="";
		if(flag==0){ 
			url=domain+"admin/campusNews/addNews";
		}else{
			url=domain+"admin/campusNews/updateNews";
		}
		$.ajax({
			type:"POST",
			url:url,
			dataType:"json",
			data:$("#newsForm").serialize(),
			success:function(result){
				if(result.status=="0"){
					 swal({
						title:'',
						text:"保存成功"
					},function(){
						$('#addModalDiv').modal('hide');
						$("#newsForm")[0].reset();
						//search();
						//searchCurrentPage();
						 window.location.reload();//刷新页面
					}); 
					
				}else{
					swal({
						title:'',
						text:result.msg
					});
				}
			}
		});
	}

	function optRender(data, type, row, meta){
		var but="";
		/*编辑*/
		but = but+'<a class="btn btn-info btn-xs" href="javascript:modify('+meta.row+')"><i class="fa fa-pencil"></i>编辑</a>';
 		/*删除*/
 		 but = but+'<a class="btn btn-danger btn-xs" href="javascript:deleteById('+row.id+')"><i class="fa fa-pencil"></i>删除</a>';
  	
		return but;
	}

	/*删除*/
	function deleteById(id){
		if(null!=id&&id!=0){
			swal({
				title:'',
				text:'你确定删除吗？',
				showCancelButton:true
			},function(isConfirm){
				if(isConfirm){
					$.ajax({
						type:"POST",
						data:{
							id:id
						},
						url:domain+"admin/campusNews/deleteNewsById",
						dataType:"json",
						success:function(result){
							if(result.status==0){
								searchCurrentPage();
							}else{
								swal({
									title:'',
									text:result.msg
								});
							}
						}
					});
				}
			} );
		}
	}
	
	function search(){
		$('#campusNews').DataTable().ajax.reload(function(d){$.extend(d,serializeObject($("#searchForm")))}, true);;
	}
	function searchCurrentPage(){
		$('#campusNews').DataTable().ajax.reload(function(d){$.extend(d,serializeObject($("#searchForm")))}, false);;
	}

	</script>
</body>
</html>