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
						<h2>广告管理</h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
					<form role="form" id="searchForm">
							 <div class="container">
								<div class="row">
									<div class="col-lg-2" >
											<select  class="form-control input-sm" name="status">
												<option value="">--请选择展示状态--</option>
												<option value="0">启用</option>
												<option value="1">禁用</option>
											</select>
									</div>
									<div class="col-lg-3">
										<button type="button" class="btn btn-success btn-sm" onclick="addBtn();">新增</button>
										<button type="button" class="btn btn-primary btn-sm" onclick="search();">搜索</button>
									</div>
								</div>
							</div> 
						</form>
						<div class="table-responsive">
							<table id="appBanner" class="table table-bordered table-striped table-hover" 
							  cellspacing="0" width="100%">
								<thead>
									<tr class="mytableHead">
										<!-- <th class="myRowNumber"></th> -->
										<th>标题</th>
										<th>图片</th>
										<th>创建时间</th>
										<th>展示状态</th>
										<th>校区</th>
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
					<form role="form" novalidate id="appBannerForm" class="form-horizontal">
						<div class="form-group item">
						     <div class="col-sm-6">
						    	 <input type="hidden" name="id" id="id"> 
						    </div>
						</div>
						
						<div class="form-group item">
							<label for="fullName2" class="col-sm-3 control-label">标题：</label>
						    <div class="col-sm-6">
						    	<input type="text" class="form-control required"  name="title" id="title" 
						    		placeholder="标题" required='required'>
						    </div>
						    <label class="control-label" style="color: #ccc;">*必填项</label>
						</div>
						<div class="form-group item">
							<label for="fullName2" class="col-sm-3 control-label">广告图片：</label>
							
							<div class="col-sm-6" id="imgDiv">
							   <input type="hidden" name="img" id="img" required="required"/>
							   <input type="file" multiple="multiple"  id="logoFile" name="file" onchange="fileUpload()" class="hidden"/>
								<a class="btn btn-primary" href="javascript:viod(0)" onclick="$('#logoFile').click()"><i class="fa fa-folder-open-o fa-lg"></i>选择..</a>
							</div>
						    
						     <label class="control-label" style="color: #ccc;">*必填项</label>
						</div>
						<div class="form-group item">
							<label class="col-sm-3 control-label"></label>
							<div class="col-sm-6">
							    <img  alt="" id="logoImg" src="${ctx}resources/images/default.jpeg" width="200" height="150"/>
						    </div>
						</div>
						
						<div class="form-group item">
							<label for="fullName2" class="col-sm-3 control-label" >关联课程：</label>
						    <div class="col-sm-6">
						    	
						    	<select class="form-control" name="courseId" id="courseId">
									<option value="">--请选择课程--</option>
									<c:if test="${courseList!=null}">
										<c:forEach items="${courseList}" var="course">
											<option value="${course.id}">${course.courseName}</option>
										</c:forEach>
									</c:if>
								</select>
								<label class="control-label" style="color: #c0cc;">（关联课程请选择，信息页面不要选）</label>
						    </div>
						    
						</div>
						
						<div class="form-group item">
							<label for="fullName2" class="col-sm-3 control-label" >关联校区：</label>
						    <div class="col-sm-6">
						    	<select name="campusId" required='required' class="form-control required"  id="campusId">
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
							<label for="fullName2" class="col-sm-3 control-label">内容：</label>
						    <div class="col-sm-6">
						    	<div>
									<textarea  name="content" id="content" ></textarea>
								</div>
						    </div>
						</div>
						<div class="form-group item">
							<label for="fullName2" class="col-sm-3 control-label">排序：</label>
						    <div class="col-sm-6">
						    	<input type="number" class="form-control number required" data-validate-length-range="1,11" name="sort" id="sort"
						    		placeholder="排序" required='required'>
						    </div>
						    <label class="control-label" style="color: #ccc;">*必填项</label>
						</div>
						<div class="form-group item">
							<label for="fullName2" class="col-sm-3 control-label" >展示状态：</label>
						    <div class="col-sm-6">
						    	<select name="status" required='required' class="form-control required"  id="status">
						    		<option value="">--请选择--</option>
						    		<option value="0">启用</option>
						    		<option value="1">禁用</option>
						    	</select>
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
	var editor=null;
	$(function(){
		//文本编辑器
		 KindEditor.ready(function (K) {
            editor = K.create('#content', {
           	width: '600px',
			    height: '540px',
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
		
		$("#appBanner").DataTable({
		    ajax: {
		        url: domain+'admin/appBanner/findAppBannerList',
		        type: 'POST',
		        data: function(d){$.extend(d,serializeObject($("#searchForm")))}
		    },
		    columns: [
			{"data" :'title'},
			{"data" :'img',"render":optImg},
			{"data" :'createTime'},
			{"data":'status',"render":optStatus},
			{"data":'campusName',"render":optCampus},
			{"data":'id',"render":optRender}
		    ]
		});
		
		$("#searchForm input").keydown(function(event){
			if(event.keyCode == 13){
				search();
			}
		});
		
		$("#searchForm select").change(search);
		
		/* var oFileInput = new FileInput();
	    oFileInput.Init("logoFile",domain+"/admin/upload/uploadImg?dir=img"); */
	});
	
	 /*  var FileInput=function(){
		var oFile=new Object();
		//初始化fileinput控件（第一次初始化）
		oFile.Init = function(ctrlName, uploadUrl) {
			var control = $('#' + ctrlName);
			//初始化上传控件的样式
			control.fileinput({
				language: 'zh', //设置语言
				uploadUrl: uploadUrl, //上传的地址
				allowedFileExtensions: ['jpg', 'gif', 'png'],//接收的文件后缀
				showUpload: true, //是否显示上传按钮
				showCaption: false,//是否显示标题
				browseClass: "btn btn-primary", //按钮样式	 
				//dropZoneEnabled: false,//是否显示拖拽区域
				//minImageWidth: 50, //图片的最小宽度
				//minImageHeight: 50,//图片的最小高度
				//maxImageWidth: 200,//图片的最大宽度
				//maxImageHeight: 200,//图片的最大高度
				//maxFileSize: 10240,//单位为kb，如果为0表示不限制文件大小
				//minFileCount: 0,
				maxFileCount: 1, //表示允许同时上传的最大文件个数
				enctype: 'multipart/form-data',
				validateInitialCount:true,
				previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
				msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
			});
			//导入文件上传完成之后的事件
			$("#logoFile").on("fileuploaded", function (event, data, previewId, index) {
				$("#img").val(data.response.data.img);
				
			});
		}
		return oFile;
	};  */
	function closeModal(){
		$('#addModalDiv').modal("hide");
	}

	var flag=0;
	/*新增*/
	function addBtn(){
		$("#modelTitle").html("新增");
		$("#logoImg").attr("src", ctx+"resources/images/default.jpeg");
		flag=0;
		$("#content").text(null);
		editor.html(null);
		$("#appBannerForm")[0].reset();
		$("#addModalDiv").modal("show");
	}
	/*编辑*/
	function modify(index,id){
		//清空表单
		$("#appBannerForm")[0].reset();
		flag=1;
		var row=$("#appBanner").DataTable().rows().data()[index];
		$("#modelTitle").html("编辑");
		$("#title").val(row.title);
		$("#sort").val(row.sort);
		
		$("#courseId").val(row.courseId);
		$("#campusId").val(row.campusId);
		
		$("#content").text(row.content);
		editor.html(row.content);
		$("#status").val(row.status);
		$("#id").val(row.id);
		$("#img").val(row.img);
		/* var imgStr=$("[name='img']").val();
		if(imgStr==null || imgStr==''){
			$("#imgDiv").append("<input type='hidden' id='img' name='img'/>");
		} */
		var logoImg=$("#logoImg").val();
		if(logoImg !=null){
			$("#img").removeAttr('required');
		}
		
		$("#logoImg").show();
		$("#logoImg").attr("src",imgDomain+"/"+row.img);
		$("#addModalDiv").modal("show");
		
	}
	
	$.ajaxSetup({
		  async: false
	});
	
	/*保存*/
	function saveModal(){
		
		if( !validator.checkAll( $("#appBannerForm") ) ){
			return;
		}
		/* var imgStr=$("[name='img']").val();
		if(imgStr==null || imgStr==''){
			//$("#imgDiv").append("<input type='hidden' name='img' value='"+$("#img").val()+"'/>");
			$("#imgDiv").append("<input type='hidden' id='img' name='img'/>");
		} */
		var url="";
		if(flag==0){ 
			url=domain+"admin/appBanner/addAppBanner";
		}else{
			url=domain+"admin/appBanner/updateAppBanner";
		}
		$.ajax({
			type:"POST",
			url:url,
			dataType:"json",
			data:$("#appBannerForm").serialize(),
			success:function(result){
				if(result.status=="0"){
					 swal({
						title:'',
						text:"保存成功"
					},function(){
						$('#addModalDiv').modal('hide');
						$("#appBannerForm")[0].reset();
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
 
	/* status中的启用状态 status */
	function optStatus(data, type, row, meta){
		if(row.status==0){
			return "启用";
		}else{
			return "禁用";
		}
	}
	
	/*LOGO图标*/
	function optImg(data,type,row,meta){
		if(row.img==null || row.img ==''){
			return "暂无图片";
		}else{
			return "<img width='100px' height='60px' src='"+imgDomain+"/"+row.img+"'/>";
		}
	}
	
	function optCampus(data,type,row,meta){
		if(row.campusName==null || row.campusName ==''){
			return "";
		}else{
			return row.campusName;
		}
	}

	 /* 操作界面中的启用,编辑*/
	function optRender(data, type, row, meta){
		var but="";
		/*编辑*/
		but = but+'<a class="btn btn-info btn-xs" href="javascript:modify('+meta.row+','+row.id+')"><i class="fa fa-pencil"></i>编辑</a>';
		if(row.status==0){
			but =but+ '<a class="btn btn-info btn-xs" href="javascript:updateStates('+row.id+',1)"><i class="fa fa-ban"></i>禁用</a>';
		}else if(row.status==1){
			but =but+ '<a class="btn btn-info btn-xs" href="javascript:updateStates('+row.id+',0)"><i class="fa fa-check"></i>启用</a>';
		}
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
						url:domain+"admin/appBanner/deleteAppBannerById",
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
	
	/* 启用states 状态*/
    function updateStates(id,status){
   	 if(status==0){
   		 swal({ "title": "", "text": "确定要启用？" ,"type": "warning","showCancelButton":true},function(isConfirm){
				if(isConfirm){
					$.ajax({
						url: domain+"admin/appBanner/updateAppBannerStatus?id="+id+"&status=0",
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
   	 }else{
   		 swal({ "title": "", "text": "确定要禁用？" ,"type": "warning","showCancelButton":true},function(isConfirm){
 				if(isConfirm){
 					$.ajax({
 						url: domain+"admin/appBanner/updateAppBannerStatus?id="+id+"&status=1",
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
	}
	/* 图片上传 */
    function fileUpload() {
        $.ajaxFileUpload({
            type: "POST",
            url: ctx + "admin/upload/uploadImg?dir=img",
            secureuri: false,//是否启用安全提交，默认为false
            fileElementId: 'logoFile',//文件选择框的id属性
            dataType: 'json',//服务器返回的格式
            async: false,
            success: function (result) {
                var data = $.parseJSON(result);
                if (data.status == 0) {
                    $("#img").val(data.data.img);
                    $("#logoImg").attr("src", data.data.src);
                } else {
                    swal( '', data.msg,  "error");
                }
            }
        });
    }
	
    function search(){
		$('#appBanner').DataTable().ajax.reload(function(d){$.extend(d,serializeObject($("#searchForm")))}, true);;
	}
	function searchCurrentPage(){
		$('#appBanner').DataTable().ajax.reload(function(d){$.extend(d,serializeObject($("#searchForm")))}, false);;
	}
	
	</script>
</body>
</html>