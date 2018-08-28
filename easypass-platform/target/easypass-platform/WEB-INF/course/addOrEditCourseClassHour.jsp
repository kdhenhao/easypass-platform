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
						<h2>
							<c:if test="${courseClassHour.id !=null }">编辑课时信息</c:if>
							<c:if test="${courseClassHour.id ==null }">新增课时信息</c:if>
						</h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<form class="form-horizontal" novalidate="novalidate" role="form" enctype="multipart/form-data"  method="post" id="addCourClassHourForm" >
							<input type="hidden" name="id" id="id"  value="${courseClassHour.id}">
							<input type="hidden" name="courseId" id="courseId"  value="${courseId}">
							<input type="hidden" name="video"  value="${courseClassHour.video}">
							<div class="container">
								<div class="form-group">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">课时名称：</label>
									</div>
									<div class="col-lg-6">
										<input type="text" class="form-control" placeholder="请输入课时名称" name="classHourName" id="classHourName"  value="${courseClassHour.classHourName}" 
										required='required' data-validate-length="0,100">
									</div>
									<div class="col-lg-4">
										<label class="control-label" style="color: #ccc;">*必填项</label>
									</div>
								</div>
								<div class="form-group">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">课时章节：</label>
									</div>
									<div class="col-lg-6">
										<input type="text" class="form-control" placeholder="请输入课时章节" name="lessonPeriod" id="lessonPeriod"  value="${courseClassHour.lessonPeriod}" 
										required='required' data-validate-length="0,100" data-bv-stringlength-message="课时章节最大长度为100">
									</div>
									<div class="col-lg-4">
										<label class="control-label" style="color: #ccc;">*必填项</label>
									</div>
								</div>
								<div class="form-group">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">价格：</label>
									</div>
									<div class="col-lg-6">
										<input type="number" class="form-control number" placeholder="请输入课时价格" name="price" id="price"  value="${courseClassHour.price}" 
										required='required' data-bv-stringlength="true" >
									</div>
									<div class="col-lg-4">
										<label class="control-label" style="color: #ccc;">*必填项</label>
									</div>
								</div>
								
								<div class="form-group">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">苹果商店的价格：</label>
									</div>
									<div class="col-lg-6">
										<input type="number" class="form-control number" placeholder="请输入苹果商店的课时价格" name="priceIos" id="priceIos"  value="${courseClassHour.priceIos}" 
										required='required' data-bv-stringlength="true" >
									</div>
									<div class="col-lg-4">
										<label class="control-label" style="color: #ccc;">*必填项</label>
									</div>
								</div>
								
								<div class="form-group item">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">税额：</label>
									</div>
									<div class="col-lg-6">
										<input type="number" class="form-control number required" placeholder="请输入课时税额" name="onTax" id="onTax"  value="${courseClassHour.onTax}" 
										data-validate-length-range="0,6" >
									</div>
									<div class="col-lg-4">
										<label class="control-label" style="color: #ccc;">*必填项</label>
									</div>
								</div>
								
								<div class="form-group item">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">积分：</label>
									</div>
									<div class="col-lg-6">
										<input type="number" class="form-control number required" placeholder="请输入积分" name="points" id="points"  value="${courseClassHour.points}" 
										data-validate-length-range="0,6" >
									</div>
									<div class="col-lg-4">
										<label class="control-label" style="color: #ccc;">*必填项</label>
									</div>
								</div>
								
								<div class="form-group">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">对应苹果商品ID：</label>
									</div>
									<div class="col-lg-6">
										<input type="text" class="form-control " placeholder="请输入对应苹果商品ID" name="appleProductId" id="appleProductId"  value="${courseClassHour.appleProductId}" data-validate-length-range="0,100">
									</div>
									<div class="col-lg-4">
										<label class="control-label" style="color: #ccc;">*必填项</label>
									</div>
								</div>
								
								<div class="form-group">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">视频外部链接：</label>
									</div>
									<div class="col-lg-6">
										<input type="url" class="form-control url" placeholder="请输入视频外部链接" name="videoHttpUrl" id="videoHttpUrl"  value="${courseClassHour.videoHttpUrl}" 
										 data-bv-stringlength="true" >
									</div>
								</div>
								
								<div class="form-group item">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">排序：</label>
									</div>
									<div class="col-lg-6">
										<input type="text" class="form-control number"  placeholder="请输入排序序号" name="sort" id="sort"  value="${courseClassHour.sort}" data-validate-length-range="0,6" >
									</div>
									<div class="col-lg-4">
										<label class="control-label" style="color: #ccc;">（正序排序1、2、3...）</label>
									</div>
								</div>
								
								
<!-- 								<div class="form-group item"> -->
<!-- 									<div class="col-lg-2" style="text-align: right;"> -->
<!-- 		                            	<label class="control-label">视频封面图片：</label> -->
<!-- 		                            </div> -->
<!-- 		                            <div class="col-sm-6"> -->
<!-- 		                                <input class="form-control input-sm hidden" name="file" type="file" id="uploadImg" -->
<!-- 		                                       accept="image/jpg,image/jpeg,image/png" multiple="multiple" style="border: 0" -->
<!-- 		                                       onchange="fileUpload();"> -->
<!-- 		                                 <a class="btn btn-primary" href="javascript:viod(0)" onclick="$('#uploadImg').click()"><i class="fa fa-folder-open-o fa-lg"></i>选择..</a> -->
<!-- 		                            </div> -->
<!-- 		                        </div> -->
<!-- 								<div class="form-group item"> -->
<!-- 		                            <label class="col-lg-2"></label> -->
<%-- 		                            <input type="hidden" name="coverImg" id="coverImg" value="${courseClassHour.coverImg}"/> --%>
<!-- 		                            <div class="col-lg-6"> -->
<%-- 		                            	<c:if test="${not empty courseClassHour.coverImg}"> --%>
<%-- 		                            		<img id="adPic" src="${imgDomain}/${courseClassHour.coverImg}" width="200" height="150"/> --%>
<%-- 		                            	</c:if> --%>
<%-- 		                            	<c:if test="${ empty courseClassHour.coverImg}"> --%>
<%-- 		                            		<img id="adPic" src="${ctx}resources/images/default.jpeg" width="200" height="150"/> --%>
<%-- 		                            	</c:if> --%>
<!-- 		                            </div> -->
<!-- 		                        </div> -->
								
								<div class="form-group">
									<div class="col-lg-2" style="text-align: right;">
										<label class="control-label">课时视频：</label>
									</div>
								    <div class="col-lg-6" id="fileDiv">
								    	 <c:if test="${not empty courseClassHour.video}">
									    	 <video id="exampleVideo" class="video-js vjs-default-skin"  controls preload="none" width="530" height="240"
											      poster="${imgDomain}/${courseClassHour.video}"
											      data-setup="{}">
											    <source id="source1" src="${imgDomain}/${courseClassHour.video}" type='video/mp4' />
											    <!-- <track kind="captions" src="demo.captions.vtt" srclang="en" label="English"></track>Tracks need an ending tag thanks to IE9
											    <track kind="subtitles" src="demo.captions.vtt" srclang="en" label="English"></track>Tracks need an ending tag thanks to IE9 -->
											  </video>
										  </c:if>
										<c:if test="${ empty courseClassHour.video }">
								    		<input id="myFile" type="file" name="file" data-bv-notempty  data-bv-notempty-message="视频必须上传"/>
								    	</c:if>
								    	<c:if test="${not empty courseClassHour.video}">
								    		<input id="myFile" type="file" name="file"/>
								    	</c:if>
								    </div>
								    <div class="col-lg-4">
								    </div>
								</div>
								
								<div class="form-group item">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">播放次数：</label>
									</div>
									<div class="col-lg-6">
										<input type="number" class="form-control number required" placeholder="请输入播放次数" name="clickNum" id="clickNum"  value="${courseClassHour.clickNum}"  data-validate-length-range="0,11">
									</div>
									<div class="col-lg-4">
										<label class="control-label" style="color: #ccc;">*必填项</label>
									</div>
								</div>
								
								<div class="form-group item">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">购买次数：</label>
									</div>
									<div class="col-lg-6">
										<input type="number" class="form-control number required" placeholder="请输入购买次数" name="purchaseNum" id="purchaseNum"  value="${courseClassHour.purchaseNum}"  data-validate-length-range="0,11">
									</div>
									<div class="col-lg-4">
										<label class="control-label" style="color: #ccc;">*必填项</label>
									</div>
								</div>
								
								<div class="form-group item">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">真实播放次数：</label>
									</div>
									<div class="col-lg-6">
										<input type="number" class="form-control number required" placeholder="请输入真实播放次数" name="clickNumReal" id="clickNumReal"  disabled="disabled" value="${courseClassHour.clickNumReal}"  data-validate-length-range="0,11">
									</div>
								</div>
								
								<div class="form-group item">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">真实购买次数：</label>
									</div>
									<div class="col-lg-6">
										<input type="number" class="form-control number required" placeholder="请输入真实购买次数" name="purchaseNumReal" id="purchaseNumReal"  disabled="disabled" value="${courseClassHour.purchaseNumReal}"  data-validate-length-range="0,11">
									</div>
								</div>
								
								<div class="form-group">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">课时内容简介：</label>
									</div>
									<div class="col-lg-6">
										<textarea  name="content" id="content" >${courseClassHour.content}</textarea>
									</div>
								</div>
								<div class="form-group">
									<div class="col-lg-4" >
										<label class="control-label">&nbsp;</label>
									</div>
									<div class="col-lg-1" >
									</div>
									<div class="col-lg-1" >
										<input type="submit" class="form-control btn btn-success" id="save" value="保存"/>
									</div>
									<div class="col-lg-1" >
										<input type="button" class="form-control btn btn-default" value="取消" onclick="cancelBtn();"/>
									</div>
									<div class="col-lg-4">
										<label class="control-label" style="color: #ccc;" >&nbsp;</label>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
			<!-- /page content -->
			<%@ include file="/WEB-INF/common/foot.jsp"%>
		</div>
	</div>
	<%@ include file="/WEB-INF/common/script.jsp"%>
	<script type="text/javascript">
	$(function(){
		//文本编辑器
		 KindEditor.ready(function (K) {
            editor = K.create('#content', {
           	width: '600px',
			    height: '340px',
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
		
		//$("#addCourClassHourForm").bootstrapValidator();
		
		//0.初始化fileinput
	    var oFileInput = new FileInput();
	    oFileInput.Init("myFile",ctx+"admin/upload/uploadImg?dir=video");
	    
	    /*表单提交*/
		$("#addCourClassHourForm").submit(function(){
			var id = $("#id").val();
			var url="";
			if(id == null || id == 0){
				$("#addCourClassHourForm").attr("action",ctx+"admin/courseClassHour/addCourseClassHour");
			}else{
				$("#addCourClassHourForm").attr("action",ctx+"admin/courseClassHour/updateCourseClassHour");
			}
			/* var flag=false;
			//判断圈子名称是否已经存在
			$.ajax({
				type:"POST",
				url:domain+"admin/circle/getCircleByTitle",
				data:{
					title:$("#title").val(),
					id:id
				},
				dataType:"json",
				async:false,
				success:function(result){
					if(result.status==0){
						flag=true;
					}else{
						swal({title:'',text:result.msg,type:"error"});
					}
				}
			});
			return flag; */
		}); 
	
});
		
	//初始化fileinput
	var FileInput = function () {
		var oFile = new Object();
		//初始化fileinput控件（第一次初始化）
		oFile.Init = function(ctrlName, uploadUrl) {
			var control = $('#' + ctrlName);
			//初始化上传控件的样式
			control.fileinput({
				language: 'zh', //设置语言
				uploadUrl: uploadUrl, //上传的地址
				allowedFileTypes:['video'],
				allowedPreviewTypes:['video'],
				allowedFileExtensions: ['3gp', 'mp4', 'avi'],//接收的文件后缀
				showUpload: true, //是否显示上传按钮
				showCaption: false,//是否显示标题
				browseClass: "btn btn-primary", //按钮样式	 
				//dropZoneEnabled: false,//是否显示拖拽区域
				//minImageWidth: 50, //图片的最小宽度
				//minImageHeight: 50,//图片的最小高度
				//maxImageWidth: 200,//图片的最大宽度
				//maxImageHeight: 200,//图片的最大高度
				maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
				//minFileCount: 0,
				maxFileCount: 1, //表示允许同时上传的最大文件个数
				enctype: 'multipart/form-data',
				validateInitialCount:true,
				previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
				msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！"
			});
			//导入文件上传完成之后的事件
			$("#myFile").on("fileuploaded", function (event, data, previewId, index) {
				$("#fileDiv").append("<input type='hidden' class='video' value='"+data.response.data.img+"'/>");
				//$("#videoLength").val(data.response.data.length);
				//$("#videoSize").val(data.response.data.size);
				 /*给新上传的视频设置新地址*/
			    if($(".video")!=null && $(".video").val()!= null && $(".video").val()!=''){
					$("[name='video']").val($(".video").val());
				}
			});
		}
		return oFile;
	};
		
		/*取消*/
		function cancelBtn(){
			window.location.href= ctx+ 'admin/courseClassHour/toCourseClassHourPage?id='+$("#courseId").val();
		}
		
		
// 		function fileUpload() {
// 	        $.ajaxFileUpload({
// 	            type: "POST",
// 	            url: ctx + "admin/upload/uploadImg?dir=img",
// 	            secureuri: false,//是否启用安全提交，默认为false
// 	            fileElementId: 'uploadImg',//文件选择框的id属性
// 	            dataType: 'json',//服务器返回的格式
// 	            async: false,
// 	            success: function (result) {
// 	                var data = $.parseJSON(result);
// 	                if (data.status == 0) {
// 	                    $("#coverImg").val(data.data.img);
// 	                    $("#adPic").attr("src", data.data.src);
// 	                } else {
// 	                    swal( '', data.msg,  "error");
// 	                }
// 	            }
// 	        });
// 	    }
	</script>
</body>
</html>