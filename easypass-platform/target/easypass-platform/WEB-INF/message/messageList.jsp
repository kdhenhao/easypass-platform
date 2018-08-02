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
						<h2>推送消息管理</h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<form role="form" id="searchForm">
							<div class="container">
								
								<div class="row">
									<div class="col-lg-2">
										<input class="form-control input-sm" placeholder="推送标题" name="map['msgHeader']" id="msgHeader"/>
									</div>
									<div class="col-lg-2">
										<select id="msgType" name="map['msgType']" class="form-control input-sm">
											<option value="">--请选择--</option>
											<option value="0">系统消息</option>
											<option value="1">通知消息</option>
											<option value="2">公告</option>
											<option value="3">活动消息</option>
										</select>
									</div>
									<div class="col-lg-2">
									    <input type="text" id="startDate" name="map['startDate']"
											class="form-control input-sm" readonly="readonly" placeholder="起始日期">
									</div>
									<div class="col-lg-2">
										<input type="text" id="endDate" name="map['endDate']"
												class="form-control input-sm" readonly="readonly" placeholder="截止日期">
									</div>
									<div class="col-lg-2 ">
										<button type="button" class="btn btn-success btn-sm" onclick="addBtn();">新增</button>
										<button type="button" class="btn btn-primary btn-sm" onclick="search();">搜索</button>
									</div>
								</div>
							</div>
						</form>
						<div class="table-responsive">
							<table id="msg" class="table table-bordered table-striped table-hover" 
							  cellspacing="0" width="100%">
								<thead>
									<tr class="mytableHead">
										<th>编号</th>
										<th>推送类型</th>
										<th>推送标题</th>
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
	<!-- 新增和修改页面 -->
	<div id="msgModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="false" 
		aria-hidden="true" >
		<div class="modal-dialog modal-md" style="width: 900px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					
					<h4 class="modal-title" id="modelTitle">
						
					</h4>
				</div>
				<div class="modal-body">
					<form role="form"  id="msgForm" class="form-horizontal">
						<input type="hidden" class="form-control" id="id" name="id" >
						<div class="form-group item">
						    <label for="msgType" class="col-sm-3 control-label">推送类型：</label>
						    <div class="col-sm-6  control-label" style="text-align:left;">
						    	<select id="msgType" name="msgType" class="form-control required" required='required'>
									<option value="">--请选择--</option>
									<option value="0">系统消息</option>
									<option value="1">通知消息</option>
									<option value="2">公告</option>
									<option value="3">活动消息</option>
								</select>
						    </div>
						    <div class="col-sm-3">
						    	<label class="control-label" style="color: #ccc;">*必填项</label>
						     </div>
						</div>
						<div class="form-group item" >
						    <label for="msgHeader" class="col-sm-3 control-label">推送标题：</label>
						    <div class="col-sm-6">
						    	<input type="text" class="form-control required" id="msgHeader" name="msgHeader" 
						    		placeholder="请输入推送主题,少于五十字" required='required' data-validate-length-range="0,50">
						    </div>
						    <div class="col-sm-3">
						    	<label class="control-label" style="color: #ccc;">*必填项</label>
						     </div>
						</div>
						
						<div class="form-group item" >
						   <label for="msgDetail" class="col-sm-3 control-label">推送详情：</label>
						    <div class="col-sm-6">
						    	<textarea  name="msgDetail" id="msgDetail" ></textarea>
						    </div>
						</div>
						
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" onclick="closeModal();">取消</button>
					<button type="button" class="btn btn-success" onclick="saveOrUpdate();">保存</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 推送详情 -->
	<!--  <div id="msgDetailModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="false" 
		aria-hidden="true" style="height: 500px;">
		<div class="modal-dialog modal-md" >
			<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
						<h4 class="modal-title" id="modelTitle">推送详情</h4>
					</div>
					<div class="modal-body">
						<form role="form" novalidate id="commentForm" class="form-horizontal">
							<div class="form-group item">
								<label for="msgDetail" class="col-sm-3 control-label" >推送内容：</label>
								<div class="col-sm-6">
								    <textarea rows="10" cols="40" readonly="readonly" id="msgDetail1"></textarea>
								</div>
							</div>
						</form>
					</div>
			</div>
		</div>
	</div>-->
	<%@ include file="/WEB-INF/common/script.jsp"%>
	<script>
	$(function(){
		$("#msg").DataTable({
		    ajax: {
		        url: ctx+'admin/message/findPushes',
		        type: 'POST',
		        beforeSend :function(xmlHttp){ 
		            xmlHttp.setRequestHeader("If-Modified-Since","0"); 
		            xmlHttp.setRequestHeader("Cache-Control","no-cache");
		         },
		        cache:false,
		        data: function(d){$.extend(d,serializeObject($("#searchForm")))}
		    },
		    columns: [
                {"data":null,"render":rowNumberRender,"width":'50'},   
                {"data":'msgType',"render":typeRender},   
		        {"data":'msgHeader'},
		        {"data":'createTm'},
		        { "data":'id',"render":optRender},
		        { "data":'pushStatus',"visible" : false},//隐藏列  是否已推送
		        { "data":'msgDetail',"visible" : false}//隐藏列  包含消息内容
		    ]
		});
		
		//文本编辑器
		 KindEditor.ready(function (K) {
           editor = K.create('#msgDetail', {
          	width: '400px',
			    height: '400px',
			    urlType :'ctx',
              cssPath: ctx+ 'resources/js/editor/plugins/code/prettify.css',
              uploadJson:ctx + 'admin/upload/uploadManager',
              fileManagerJson:  ctx + 'admin/upload/fileManager',
              allowFileManager: true,
              resizeType : 0 ,
              afterCreate:function() {
              	this.sync();
            	},
            	afterBlur:function(){
            		this.sync();
            	}
          });
      });
		
		$("#searchForm input").keydown(function(event){
			if(event.keyCode == 13){
				search();
			}
		});
		
		$("#searchForm select").change(search);
		
		
	});
	
	function typeRender(data, type, row, meta){
		var tag = "<p>";
		if(data==0){
			tag = tag + "系统消息";
		}
		if(data==1){
			tag = tag + "通知消息";
		}
		if(data==2){
			tag = tag + "公告";
		}
		if(data==3){
			tag = tag + "活动消息";
		}
		tag = tag + "</p>";
		return tag;
	}
	
	/*新增*/
	function addBtn(){
		$("#msgForm")[0].reset();
		$("#id").val(0);
		
		editor.html("");
		$("#modelTitle").text("新增推送信息");
		$('#msgModal').modal("show");
	}
	
	/*编辑*/
	function modifyMember(index){
		var row = $('#msg').DataTable().rows().data()[index];
		$("#id").val(row.id);
		$("#msgForm #msgHeader").val(row.msgHeader);
		var options = $("#msgType option");
		//alert(options.length);
		$("#msgForm #msgType").val(row.msgType);
		editor.html(row.msgDetail);
		$("#modelTitle").text("编辑推送信息");
		$('#msgModal').modal("show");
	}
	
	function pushMsgBtn(id){
		swal({ "title": "", "text": "确定要推送该条消息吗?" ,"type": "warning","showCancelButton":true},function(isConfirm){
			if(isConfirm){
				$.ajax({
					url: ctx+"admin/message/updatePushStatus.htm?id="+id+"&time="+(new Date()).getTime(),
					dataType: "json",
					success: function(result){
						if(result.status == "0"){
							swal({title:'',text:"推送成功"},function(){
								searchCurrentPage();
							});
						}else{
							swal({title:'',text:result.msg,type:"error"});
						}
					}
				});
			}
		});
	}
	
	function optRender(data, type, row, meta){
		var but = '<a class="btn btn-info btn-xs" href="javascript:modifyMember('+meta.row+')"><i class="fa fa-pencil"></i>编辑</a>';
		/*but = but + '<a class="btn btn-info btn-xs" href="javascript:showDetailBtn('+meta.row+')">内容详情</a>';*/
		but = but + '<a class="btn btn-danger btn-xs" href="javascript:deleteBtn('+row.id+')">删除</a>';
		if(row.pushStatus==0){
			but = but + '<a class="btn btn-info btn-xs" href="javascript:pushMsgBtn('+row.id+')">推送</a>';
		}else{
			but = but + '<a class="btn btn-info btn-xs">已推送</a>';
		}
		
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
					url:ctx+"admin/message/deleteMsgById",
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
	/* 保存或更新 */
	function saveOrUpdate(){
		if( !validator.checkAll( $("#msgForm") ) ){
			return;
		}
		var id=$("#id").val();
		var url;
		if(null==id || id == 0){//新增
			url=domain+"admin/message/addPush?time="+(new Date()).getTime();
		}else{
			url=domain+"admin/message/updatePush?time="+(new Date()).getTime();
		}
		$.ajax({
			url: url,
			dataType: "json",
			data:$("#msgForm").serialize(),
			success: function(result){
				if(result.status == "0"){
					swal({title:'',text:"保存成功"},function(){
						$('#msgModal').modal('hide');
						searchCurrentPage();
					});
				}else{
					swal({title:'',text:result.msg,type:"error"});
				}
			}
		});
	}
	/*显示消息内容*/
	/*function showDetailBtn(index){
		var row = $('#msg').DataTable().rows().data()[index];
		var data = row.msgDetail;
		alert(typeof $(data).html());
		$("#msgDetail1").text($(data).html());
		$("#msgDetailModal").modal("show");
	}*/
	
	function search(){
		$('#msg').DataTable().ajax.reload(function(d){$.extend(d,serializeObject($("#searchForm")))}, true);;
	}
	function searchCurrentPage(){
		$('#msg').DataTable().ajax.reload(function(d){$.extend(d,serializeObject($("#searchForm")))}, false);;
	}
	function closeModal(){
		$('#msgModal').modal("hide");
	}
	</script>
</body>
</html>