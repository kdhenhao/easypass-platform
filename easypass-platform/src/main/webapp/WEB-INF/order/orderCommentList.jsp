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
						<h2>订单评论管理</h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<form role="form" id="searchForm">
							<div class="container">
								<div class="row">
									<div class="col-lg-2">
										<input class="form-control input-sm" placeholder="评论人用户名" name="map['userName']"/>
									</div>
									<div class="col-lg-2" >
										<input class="form-control input-sm" placeholder="课程名称" name="map['courseName']">
									</div>
									<div class="col-lg-2">
										<input class="form_datetime form-control input-sm" readonly="readonly" placeholder="评论时间" id="createTime" name="map['createTime']">
									</div>
									<!-- <div class="col-lg-2">
										<select name="commentStatus" class="form-control input-sm">
											<option value="">评论状态</option>
											<option value="0">待审核</option>
											<option value="1">已通过</option>
											<option value="2">未通过</option>
										</select>
									</div> -->
									<div class="col-lg-2 ">
										<button type="button" class="btn btn-primary btn-sm" onclick="search();">搜索</button>
									</div>
								</div>
							</div>
						</form>
						<div class="table-responsive">
							<table id="comments" class="table table-bordered table-striped table-hover" 
							  cellspacing="0" width="100%">
								<thead>
									<tr class="mytableHead">
										<th class="myRowNumber">编号</th>
										<th>评论人用户名</th>
										<th>评论人联系方式</th>
										<th>评论内容</th>
										<th>评论课程名称</th>
										<th>评分时间</th>
										<!-- <th>评论状态</th> -->
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
	
	<div id="schoolCommentModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="false"
		aria-hidden="true" style="height: 500px;">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="modelTitle">编辑评论状态</h4>
				</div>
				<div class="modal-body">
					<form role="form" novalidate id="commentForm" class="form-horizontal">
						<div class="form-group item">
						    <label for="fullName2" class="col-sm-3 control-label">评论状态：</label>
						    <div class="col-sm-6">
						    	<input type="hidden" value="" name="id" id="commentId">
						    	<input type="hidden" name="schoolId" id="schoolId"/>
						    	<input type="hidden" name="starLevel" id="starLevel"/>
						    	<select name="status" id="status" class="form-control input-sm" required="required" >
									<option value="">评论状态</option>
									<option value="0">待审核</option>
									<option value="1">已通过</option>
									<option value="2">未通过</option>
								</select>
						    </div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" onclick="closeBtn();">取消</button>
					<button type="button" class="btn btn-success" onclick="saveBtn();">保存</button>
				</div>
			</div>
		</div>
	</div>
	
	<div id="detailModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="false"
		aria-hidden="true" style="height: 500px;">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="modelTitle">订单评论详情</h4>
				</div>
				<div class="modal-body">
					<form role="form" novalidate id="commentForm" class="form-horizontal">
						
						<div class="form-group item">
						    <label for="fullName2" class="col-sm-3 control-label" >评论内容：</label>
						    <div class="col-sm-6">
						    	<textarea rows="10" cols="40" readonly="readonly" id="content"></textarea>
						    	<!-- <div class="control-label" style="text-align: left;" id="content">这是评论内容这是评论内容</div> -->
						    </div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/common/script.jsp"%>
	<script>
	$(function(){
		$("#comments").DataTable({
		    ajax: {
		        url: ctx+'admin/orderComment/findOrderCommentList',
		        type: 'POST',
		        data: function(d){$.extend(d,serializeObject($("#searchForm")))}
		    },
		    columns: [
		    	{ "data":null,"render":rowNumberRender},
                { "data":'userName'},    
		        { "data":'phone'},
		        {"data":'content'},
		        {"data":'courseName'},
		        {"data":'createTime'},
		        /* {"data":'status',"render":optStatus}, */
		        { "data":'id',"render":optRender}
		    ]
		});
		
		$("#searchForm input").keydown(function(event){
			if(event.keyCode == 13){
				search();
			}
		});
		$("#searchForm select").change(search);
		
		/*日期控件*/
		$("#createTime").datetimepicker({
			minView: "month",
			language:  'zh-CN',
			format: 'yyyy-mm-dd',
			todayBtn:  1,
			autoclose: 1
		});
	});

	
	function optRender(data, type, row, meta){
		//var but = '<a class="btn btn-info btn-xs" href="javascript:modify('+meta.row+')"><i class="fa fa-pencil"></i>编辑</a>';
		var but = '';
		but = but + '<a class="btn btn-info btn-xs" href="javascript:showDetail('+row.id+','+meta.row+')">查看详情</a>';
		but = but + '<a class="btn btn-danger btn-xs" href="javascript:del('+row.id+')">删除</a>';
		return but;
	}
	
	function del(id){
		swal({
			title:"",
			text:"你确定删除吗？",
			type:"warning",
			showCancelButton:true
		},function(isConfirm){
			if(isConfirm){
				$.ajax({
					type:"POST",
					data:{
						id:id
					},
					dataType:"json",
					url:ctx+"admin/orderComment/deleteOrderCommentById",
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
	
	function showDetail(id,index){
		var row = $('#comments').DataTable().rows().data()[index];
		$("#content").text(row.contentDetail);
		$("#detailModal").modal("show");
	}
	
	function modify(index){
		var row = $('#comments').DataTable().rows().data()[index];
		$("#commentId").val(row.id);
		$("#status").val(row.status);
		$("#schoolId").val(row.schoolId);
		$("#starLevel").val(row.starLevel);
		$("#schoolCommentModal").modal("show");
	}
	
	function optStatus(data,type,row,meta){
		if(row.status==1){
			return "已通过";
		}else if(row.status==0){
			return "待审核";
		}else if(row.status==2){
			return "未通过";
		}else {
			return "";
		}
	}
	function search(){
		$('#comments').DataTable().ajax.reload(function(d){$.extend(d,serializeObject($("#searchForm")))}, true);;
	}
	function searchCurrentPage(){
		$('#comments').DataTable().ajax.reload(function(d){$.extend(d,serializeObject($("#searchForm")))}, false);;
	}
	/*保存*/
	function saveBtn(){
		if(!validator.checkAll($("#commentForm") )){
			return;
		}
		$.ajax({
			type:"POST",
			data:$("#commentForm").serialize(),
			dataType:"json",
			url:ctx+"/admin/schoolComment/modifySchoolComment",
			success:function(result){
				if(result.status=="0"){
					$('#schoolCommentModal').modal("hide");
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
	/*取消*/
	function closeBtn(){
		$('#schoolCommentModal').modal("hide");
	}
	
	</script>
</body>
</html>