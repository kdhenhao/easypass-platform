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
						<h2>帮助管理</h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<form role="form" id="searchForm">
							<div class="container">
								
								<div class="row">
									<div class="col-lg-2">
										<input class="form-control input-sm" placeholder="帮助标题" name="helpHeader" id="helpHeader"/>
									</div>
									
									<div class="col-lg-2 ">
										<button type="button" class="btn btn-success btn-sm" onclick="addBtn();">新增</button>
										<button type="button" class="btn btn-primary btn-sm" onclick="search();">搜索</button>
									</div>
								</div>
							</div>
						</form>
						<div class="table-responsive">
							<table id="help" class="table table-bordered table-striped table-hover" 
							  cellspacing="0" width="100%">
								<thead>
									<tr class="mytableHead">
										<th>帮助标题</th>
										<th>帮助序号</th>
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
	<div id="helpModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="false" 
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
					<form role="form" novalidate id="helpForm" class="form-horizontal">
						<input type="hidden" class="form-control" id="id" name="id" >
						<div class="form-group item" >
						    <label for="helpHeader" class="col-sm-3 control-label">帮助标题：</label>
						    <div class="col-sm-6">
						    	<input type="text" class="form-control required" id="helpHeader" name="helpHeader" 
						    		placeholder="请输入帮助主题" required='required' data-validate-length-range="0,25">
						    </div>
						    <div class="col-sm-3">
						    	<label class="control-label" style="color: #ccc;">*必填项</label>
						     </div>
						</div>
						<div class="form-group item">
						    <label for="helpSeq" class="col-sm-3 control-label">帮助编号：</label>
						    <div class="col-sm-6  control-label" style="text-align:left;">
						    	<input type="number" class="form-control number required" id="helpSeq" name="helpSeq" 
						    		placeholder="请输入帮助主题" required='required' data-validate-length-range="0,25">
						    </div>
						    <div class="col-sm-3">
						    	<label class="control-label" style="color: #ccc;">*必填项</label>
						     </div>
						</div>
						<div class="form-group item" >
						   <label for="solution" class="col-sm-3 control-label">帮助详情：</label>
						    <div class="col-sm-6">
						    	<textarea  name="solution" id="solution" ></textarea>
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
	<%@ include file="/WEB-INF/common/script.jsp"%>
	<script>
	$(function(){
		$("#help").DataTable({
		    ajax: {
		        url: ctx+'admin/appHelp/getHelpList',
		        type: 'POST',
		        beforeSend :function(xmlHttp){ 
		            xmlHttp.setRequestHeader("If-Modified-Since","0"); 
		            xmlHttp.setRequestHeader("Cache-Control","no-cache");
		         },
		        cache:false,
		        data: function(d){$.extend(d,serializeObject($("#searchForm")))}
		    },
		    columns: [
                {"data":'helpHeader'},    
		        {"data":'helpSeq'},
		        {"data":'createTm'},
		        { "data":'id',"render":optRender},
		        {"data":'solution',"visible" : false}//隐藏列
		    ]
		});
		
		//文本编辑器
		 KindEditor.ready(function (K) {
           editor = K.create('#solution', {
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
	
	
	/*新增*/
	function addBtn(){
		$("#helpForm")[0].reset();
		$("#id").val(0);
		$("#helpForm #helpHeader").val("");
		$("#helpSeq").val("");
		editor.html("");
		$("#modelTitle").text("新增帮助信息");
		$('#helpModal').modal("show");
	}
	
	/*编辑*/
	function modifyMember(index){
		var row = $('#help').DataTable().rows().data()[index];
		//alert(row.solution);
		$("#id").val(row.id);
		$("#helpForm #helpHeader").val(row.helpHeader);
		$("#helpSeq").val(row.helpSeq);
		editor.html(row.solution);
		$("#modelTitle").text("编辑帮助信息");
		$('#helpModal').modal("show");
	}
	
	
	function optRender(data, type, row, meta){
		var but = '<a class="btn btn-info btn-xs" href="javascript:modifyMember('+meta.row+')"><i class="fa fa-pencil"></i>编辑</a>';
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
					url:ctx+"admin/appHelp/deleteHelpById",
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
		if( !validator.checkAll( $("#helpForm") ) ){
			return;
		}
		var id=$("#id").val();
		var url;
		if(null==id || id == 0){//新增
			url=domain+"admin/appHelp/addHelp?time="+(new Date()).getTime();
		}else{
			url=domain+"admin/appHelp/updateHelp?time="+(new Date()).getTime();
		}
		$.ajax({
			url: url,
			dataType: "json",
			data:$("#helpForm").serialize(),
			success: function(result){
				if(result.status == "0"){
					swal({title:'',text:"保存成功"},function(){
						$('#helpModal').modal('hide');
						searchCurrentPage();
					});
				}else{
					swal({title:'',text:result.msg,type:"error"});
				}
			}
		});
	}
	function search(){
		$('#help').DataTable().ajax.reload(function(d){$.extend(d,serializeObject($("#searchForm")))}, true);;
	}
	function searchCurrentPage(){
		$('#help').DataTable().ajax.reload(function(d){$.extend(d,serializeObject($("#searchForm")))}, false);;
	}
	function closeModal(){
		$('#helpModal').modal("hide");
	}
	</script>
</body>
</html>