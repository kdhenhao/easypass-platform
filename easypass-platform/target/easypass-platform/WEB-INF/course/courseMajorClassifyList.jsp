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
						<h2>专业分类管理</h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<form role="form" id="searchForm">
							<div class="container">
								<div class="row">
									<div class="col-lg-2" >
										<input class="form-control input-sm" placeholder="校区名称" name="campusName"/> 
									</div>
									<div class="col-lg-2" >
										<input class="form-control input-sm" placeholder="一级专业名称" name="name"/> 
									</div>
									<div class="col-lg-2 ">
										<button type="button" class="btn btn-success btn-sm" onclick="addBtn();">新增</button>
										<button type="button" class="btn btn-primary btn-sm" onclick="search();">搜索</button> 
									</div>
								</div>
							</div>
						</form>
						<div class="table-responsive">
							<table id="courseMajorManage" class="table table-bordered table-striped table-hover" 
							  cellspacing="0" width="100%">
								<thead>
									<tr class="mytableHead">
										<th class="myRowNumber">编号</th>
										<th>校区</th>
										<th>专业名称</th>
										<th>封面图片</th>
										<th>排序</th>
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
	
	<div id="courseMajorModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="false" aria-hidden="true" >
		<div class="modal-dialog modal-md" style="width: 700px;">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="modelTitle">新增专业分类</h4>
				</div>
				<div class="modal-body">
					<form role="form" novalidate id="userForm" class="form-horizontal">
						<div class="form-group item">
						     <div class="col-sm-6">
						    	 <input type="hidden" name="id" id="id"> 
						    </div>
						</div>
						 <div class="form-group item">
						    <label for="password2" class="col-sm-3 control-label">专业名称：</label>
						    <div class="col-sm-6">
						    		<input placeholder="专业名称" name="name" 
						    		class="form-control required" required='required' id="majorName" data-validate-length-range="0,25"/>
						    		
						    </div>
						    <div class="col-sm-3">
						    	<label class="control-label" style="color: #ccc;">*必填项</label>
						     </div>
						</div>
							<div class="form-group item">
									<label class="col-sm-3 control-label">校区：</label>
									<div class="col-lg-6">
										<select class="select2_multiple form-control required" multiple="multiple" name="campusId" id="campusId" required='required'>
											<c:if test="${campusList!=null}">
												<c:forEach items="${campusList}" var="campus">
													<option value="${campus.id}" <c:if test="${campus.id==classify.campusId}">selected="selected"</c:if>>${campus.name}</option>
												</c:forEach>
											</c:if>
										</select>
									</div>
									<div class="col-lg-4">
										<label class="control-label" style="color: #ccc;">*必填项</label>
									</div>
								</div>
						<div class="form-group item">
                            <label class="col-sm-3 control-label">点击上传图片：</label>
                            <div class="col-sm-6">
                                <input class="form-control input-sm hidden" name="file" type="file" id="uploadImg"
                                       accept="image/jpg,image/jpeg,image/png" multiple="multiple" style="border: 0"
                                       onchange="fileUpload();">
                                 <a class="btn btn-primary" href="javascript:viod(0)" onclick="$('#uploadImg').click()"><i class="fa fa-folder-open-o fa-lg"></i>选择..</a>
                            </div>
                        </div>
						<div class="form-group item">
                            <label class="col-sm-3 control-label"></label>
                            <input type="hidden" name="img" id="img" required="required"/>
                            <div class="col-sm-6">
                                <img id="adPic" src="${ctx}resources/images/default.jpeg" width="200" height="150"/>
                            </div>
                        </div>
                        
						<!-- <div class="form-group item">
							<label for="fullName2" class="col-sm-3 control-label">专业封面图片：</label>
							<div class="col-lg-3">
								<input type="hidden" name="img" id="img" required="required"/>
								<input type="file" id="logoFile" name="file1" />
							</div>
							<div class="col-lg-3" style="height: 100px;" id="imgDiv">
								<img alt="" src=""  width="100px" height="100px" id="logoImg">
							</div>
							<div class="col-lg-4">
								<label class="control-label" style="color: #ccc;">*必填项</label>
							</div>
							<input type="file" class="file-loading" id="logoFile" name="file"/>
						    <div class="col-sm-6" id="imgDiv">
						    	<input type="hidden" name="img" id="img" required="required"/>
						    	<img  src="" alt="" id="logoImg" width="100px" height="100px"/>
						    	
						    </div>
						     <label class="control-label" style="color: #ccc;">*必填项</label>
						</div> -->
						
						<div class="form-group item">
						    <label for="sort" class="col-sm-3 control-label">排序序号：</label>
						    <div class="col-sm-6">
						    		<input placeholder="请输入数字" name="sort" 
						    		onkeyup="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}" 
						    		onafterpaste="if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}"
						    		class="form-control required" required='required' id="sort" data-validate-length-range="0,11"/>
						    </div>
						     <div class="col-sm-3">
						    	<label class="control-label" style="color: #ccc;">*必填项</label>
						     </div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" onclick="closeModal();">取消</button>
					<button type="button" class="btn btn-success" onclick="saveUser();">保存</button>
				</div>
			</div>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/common/script.jsp"%>
	<script>
	$(function(){
		$("#courseMajorManage").DataTable({
		    ajax: {
		        url: ctx+'admin/courseMajorClassify/find',
		        type: 'POST',
		        data: function(d){$.extend(d,serializeObject($("#searchForm")))}
		    },
		    columns: [
		    	{ "data":null,"render":rowNumberRender},
		    	{ "data":'campusName'}, 
                { "data":'name'}, 
                { "data" :'img',"render":optImg},
                { "data":'sort'}, 
		        { "data":'id',"render":optRender}
		    ]
		});
		
		$("#searchForm input").keydown(function(event){
			if(event.keyCode == 13){
				search();
			}
		});
		$("#searchForm select").change(search);
		
		/* $("#logoFile").click(function(){
			new uploadPreview({ UpBtn: "logoFile", DivShow: "imgDiv", ImgShow: "logoImg" });
		}); */
		
		//var oFileInput = new FileInput();
	    //oFileInput.Init("logoFile",domain+"/admin/upload/uploadImg?dir=img");
	});
	
	
	
	/* var FileInput=function(){
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
				/* var imgStr=$("[name='img']").val();
				if(imgStr==null || imgStr==''){
					$("#imgDiv").append("<input type='hidden' name='img' id='img' value='"+data.response.data.img+"'/>");
				}else {
					$("#logoImg").attr("src",imgDomain+"/"+data.response.data.img);
					$("#img").val(data.response.data.img);
				} */
				/* $("#img").val(data.response.data.img);
				
			});
		}
		return oFile;
	}; */ 
	
	/*LOGO图标*/
	function optImg(data,type,row,meta){
		if(row.img==null || row.img ==''){
			return "暂无图片";
		}else{
			return "<img width='100px' height='60px' src='"+imgDomain+"/"+row.img+"'/>";
		}
	}
	
	/*新增*/
	function addBtn(){
		$("#modelTitle").html("新增专业分类");
		$("#adPic").attr("src",ctx+"resources/images/default.jpeg");
		
		//$("#userForm input").val("").parent().siblings(".alert").remove();
		//$("#userForm input").parents(".item").removeClass("bad");
		$("#userForm")[0].reset();
		$("#courseMajorModal").modal("show");
	}
	
	/*编辑(修改)*/
	 function modify(index){
		$("#userForm")[0].reset();
		
		var row = $('#courseMajorManage').DataTable().rows().data()[index];
		$("#modelTitle").html("编辑专业分类");
		$("#majorName").val(row.name);
		$("#campusId").val(row.campusId);
		$("#sort").val(row.sort);
		$("#id").val(row.id);
		
		/* var logoImg=$("#logoImg").val();
		if(logoImg !=null){
			$("#img").removeAttr('required');
		}
		
		$("#logoImg").show();
		$("#logoImg").attr("src",imgDomain+"/"+row.img); */
		
		$("#adPic").attr("src", imgDomain +"/"+ row.img);
        $("#img").val(row.img);
        
		$('#courseMajorModal').modal("show");
	}
	
	 $.ajaxSetup({
		 async: false
     });
	
	/*保存按钮*/
	function saveUser(){
		if( !validator.checkAll( $("#courseMajorModal") ) ){
			return;
		}
		//var zz= /^[\u4e00-\u9fa5\\w]+$/;
		var name=$('#majorName').val();
		//if(zz.test(name)){
			$.ajax({
				url: ctx+"admin/courseMajorClassify/update",
				dataType: "json",
				data:$("#userForm").serialize(),
				success: function(result){
					if(result.status == "0"){
						swal({title:'',text:"保存成功"},function(){
							$('#courseMajorModal').modal('hide');
							search();
						});
					}else{
						swal({title:'',text:result.msg,type:"error"});
					}
				}
			});
		//}else{
		//	swal({
		//		title:'',
		//		text:'请输入汉字',
		//		type:'error'
		//	});
		//}
		
	}
	function optRender(data, type, row, meta){
		var but = '<a class="btn btn-info btn-xs" href="javascript:modify('+meta.row+')"><i class="fa fa-pencil"></i>编辑</a>';

		but = but + '<a class="btn btn-danger btn-xs" href="javascript:deleteById('+row.id+')">删除</a>';
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
						url:domain+"admin/courseMajorClassify/deleteById",
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
	
	function fileUpload() {
        $.ajaxFileUpload({
            type: "POST",
            url: ctx + "admin/upload/uploadImg?dir=img",
            secureuri: false,//是否启用安全提交，默认为false
            fileElementId: 'uploadImg',//文件选择框的id属性
            dataType: 'json',//服务器返回的格式
            async: false,
            success: function (result) {
                var data = $.parseJSON(result);
                if (data.status == 0) {
                    $("#img").val(data.data.img);
                    $("#adPic").attr("src", data.data.src);
                } else {
                    swal( '', data.msg,  "error");
                }
            }
        });
    }
	
	function search(){
		$('#courseMajorManage').DataTable().ajax.reload(function(d){$.extend(d,serializeObject($("#searchForm")))}, true);;
	}
	function searchCurrentPage(){
		$('#courseMajorManage').DataTable().ajax.reload(function(d){$.extend(d,serializeObject($("#searchForm")))}, false);;
	}
	function closeModal(){
		$('#courseMajorModal').modal("hide");
	}
	
	</script>
</body>
</html>