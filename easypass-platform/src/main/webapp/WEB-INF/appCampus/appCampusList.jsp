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
						<h2>校区管理</h2>
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
							<table id="appCampus" class="table table-bordered table-striped table-hover" 
							  cellspacing="0" width="100%">
								<thead>
									<tr class="mytableHead">
										<!-- <th class="myRowNumber"></th> -->
										<th>校区名称</th>
										<th>LOGO图片</th>
										<th>背景图片</th>
										<th>创建时间</th>
										<th>修改时间</th>
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
					<form role="form" novalidate id="appCampusForm" class="form-horizontal">
						<div class="form-group item">
						     <div class="col-sm-6">
						    	 <input type="hidden" name="id" id="id"> 
						    </div>
						</div>
						
						<div class="form-group item">
							<label for="fullName2" class="col-sm-3 control-label">校区名称：</label>
						    <div class="col-sm-6">
						    	<input type="text" class="form-control required"  name="name" id="name" 
						    		placeholder="标题" required='required'>
						    </div>
						    <label class="control-label" style="color: #ccc;">*必填项</label>
						</div>
						<div class="form-group item">
							<label for="fullName2" class="col-sm-3 control-label">LOGO图片：</label>
							
							<div class="col-sm-6" id="imgDiv1">
							   <input type="hidden" name="logoImg" id="logoImg" required="required"/>
							   <input type="file" multiple="multiple"  id="logoFile" name="file" onchange="fileUpload1()" class="hidden"/>
								<a class="btn btn-primary" href="javascript:viod(0)" onclick="$('#logoFile').click()"><i class="fa fa-folder-open-o fa-lg"></i>选择..</a>
							</div>
						    
						     <label class="control-label" style="color: #ccc;">*必填项</label>
						</div>
						<div class="form-group item">
							<label class="col-sm-3 control-label"></label>
							<div class="col-sm-6">
							    <img  alt="" id="logoImg1" src="${ctx}resources/images/default.jpeg" width="200" height="150"/>
						    </div>
						</div>
						
						<div class="form-group item">
							<label for="fullName2" class="col-sm-3 control-label">背景图片：</label>
							
							<div class="col-sm-6" id="imgDiv2">
							   <input type="hidden" name="backgroundImg" id="backgroundImg" required="required"/>
							   <input type="file" multiple="multiple"  id="backgroundFile" name="backgroundFile" onchange="fileUpload2()" class="hidden"/>
								<a class="btn btn-primary" href="javascript:viod(0)" onclick="$('#backgroundFile').click()"><i class="fa fa-folder-open-o fa-lg"></i>选择..</a>
							</div>
						    
						     <label class="control-label" style="color: #ccc;">*必填项</label>
						</div>
						<div class="form-group item">
							<label class="col-sm-3 control-label"></label>
							<div class="col-sm-6">
							    <img  alt="" id="backgroundImg1" src="${ctx}resources/images/default.jpeg" width="200" height="150"/>
						    </div>
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
		$("#appCampus").DataTable({
		    ajax: {
		        url: domain+'admin/appCampus/findAppCampusList',
		        type: 'POST',
		        data: function(d){$.extend(d,serializeObject($("#searchForm")))}
		    },
		    columns: [
			{"data" :'name'},
			{"data" :'logoImg',"render":optImg},
			{"data" :'backgroundImg',"render":bkgImg},
			{"data" :'createTime'},
			{"data" :'modifyTime'},
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
		$("#logoImg1").attr("src", ctx+"resources/images/default.jpeg");
		$("#backgroundImg1").attr("src", ctx+"resources/images/default.jpeg");
		flag=0;
		$("#appCampusForm")[0].reset();
		$("#addModalDiv").modal("show");
	}
	/*编辑*/
	function modify(index,id){
		//清空表单
		$("#appCampusForm")[0].reset();
		flag=1;
		var row=$("#appCampus").DataTable().rows().data()[index];
		$("#modelTitle").html("编辑");
		$("#name").val(row.name);
		$("#id").val(row.id);
		$("#logoImg").val(row.logoImg);
		$("#backgroundImg").val(row.backgroundImg);
		/* var imgStr=$("[name='img']").val();
		if(imgStr==null || imgStr==''){
			$("#imgDiv").append("<input type='hidden' id='img' name='img'/>");
		} */
		var logoImg1=$("#logoImg1").val();
		if(logoImg1 !=null){
			$("#logoImg").removeAttr('required');
		}
		
		$("#logoImg1").show();
		$("#logoImg1").attr("src",imgDomain+"/"+row.logoImg);
		$("#addModalDiv").modal("show");
		
		var backgroundImg1=$("#backgroundImg1").val();
		if(backgroundImg1 !=null){
			$("#backgroundImg").removeAttr('required');
		}
		
		$("#backgroundImg1").show();
		$("#backgroundImg1").attr("src",imgDomain+"/"+row.backgroundImg);
		$("#addModalDiv").modal("show");
		
	}
	
	$.ajaxSetup({
		  async: false
	});
	
	/*保存*/
	function saveModal(){
		
		if( !validator.checkAll( $("#appCampusForm") ) ){
			return;
		}
		/* var imgStr=$("[name='img']").val();
		if(imgStr==null || imgStr==''){
			//$("#imgDiv").append("<input type='hidden' name='img' value='"+$("#img").val()+"'/>");
			$("#imgDiv").append("<input type='hidden' id='img' name='img'/>");
		} */
		var url="";
		if(flag==0){ 
			url=domain+"admin/appCampus/addAppCampus";
		}else{
			url=domain+"admin/appCampus/updateAppCampus";
		}
		$.ajax({
			type:"POST",
			url:url,
			dataType:"json",
			data:$("#appCampusForm").serialize(),
			success:function(result){
				if(result.status=="0"){
					 swal({
						title:'',
						text:"保存成功"
					},function(){
						$('#addModalDiv').modal('hide');
						$("#appCampusForm")[0].reset();
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
	
	/*LOGO图标*/
	function optImg(data,type,row,meta){
		if(row.logoImg==null || row.logoImg ==''){
			return "暂无图片";
		}else{
			return "<img width='100px' height='60px' src='"+imgDomain+"/"+row.logoImg+"'/>";
		}
	}
	
	/*背景图标*/
	function bkgImg(data,type,row,meta){
		if(row.backgroundImg==null || row.backgroundImg ==''){
			return "暂无图片";
		}else{
			return "<img width='100px' height='60px' src='"+imgDomain+"/"+row.backgroundImg+"'/>";
		}
	}

	 /* 操作界面中的启用,编辑*/
	function optRender(data, type, row, meta){
		var but="";
		/*编辑*/
		but = but+'<a class="btn btn-info btn-xs" href="javascript:modify('+meta.row+','+row.id+')"><i class="fa fa-pencil"></i>编辑</a>';
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
						url:domain+"admin/appCampus/deleteAppCampusById",
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

	/* logo图片上传 */
    function fileUpload1() {
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
                    $("#logoImg").val(data.data.logoImg);
                    $("#logoImg1").attr("src", data.data.src);
                } else {
                    swal( '', data.msg,  "error");
                }
            }
        });
    }
	
    /* 背景图片上传 */
    function fileUpload2() {
        $.ajaxFileUpload({
            type: "POST",
            url: ctx + "admin/upload/uploadImg?dir=img",
            secureuri: false,//是否启用安全提交，默认为false
            fileElementId: 'backgroundFile',//文件选择框的id属性
            dataType: 'json',//服务器返回的格式
            async: false,
            success: function (result) {
                var data = $.parseJSON(result);
                if (data.status == 0) {
                    $("#backgroundImg").val(data.data.backgroundImg);
                    $("#backgroundImg1").attr("src", data.data.src);
                } else {
                    swal( '', data.msg,  "error");
                }
            }
        });
    }
	
    function search(){
		$('#appCampus').DataTable().ajax.reload(function(d){$.extend(d,serializeObject($("#searchForm")))}, true);;
	}
	function searchCurrentPage(){
		$('#appCampus').DataTable().ajax.reload(function(d){$.extend(d,serializeObject($("#searchForm")))}, false);;
	}
	
	</script>
</body>
</html>