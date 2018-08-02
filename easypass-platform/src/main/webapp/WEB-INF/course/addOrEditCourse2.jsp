<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/common/head.jsp"%>

</head>
<body class="${cookie.bodyClass.value}">
	<div class="container body">
		<div class="main_container">
			<%@ include file="/WEB-INF/common/left.jsp"%>
			<%@ include file="/WEB-INF/common/top.jsp"%>
			<!-- page content -->
			<div class="right_col" role="main" id="main">
				<div class="x_panel">
					<div class="x_title">
						<h2>
							<c:if test="${course.id !=null }">编辑课程信息</c:if>
							<c:if test="${course.id ==null }">新增课程信息</c:if>
						</h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<form class="form-horizontal" role="form" novalidate enctype="multipart/form-data" method="post" id="addCourseForm" >
							<input type="hidden" name="id" id="id" value="${course.id}">
							<input type="hidden" name="photo" value="${course.photo}">
							<input type="hidden" name="coverImg" value="${course.coverImg}">
							<input type="hidden" name="dir" value="images">
							<input type="hidden" name="video" value="${course.video}">
							
							<div class="container">
								<div class="form-group item">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">课程名称：</label>
									</div>
									<div class="col-lg-6">
										<input type="text" class="form-control required" placeholder="请输入课程名称" name="courseName" id="courseName"  value="${course.courseName}" 
										required='required' data-validate-length-range="0,100">
									</div>
									<div class="col-lg-4">
										<label class="control-label" style="color: #ccc;">*必填项</label>
									</div>
								</div>
								<div class="form-group item">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">专业分类：</label>
									</div>
									<div class="col-lg-6">
										
										<select class="select2_multiple form-control required" multiple="multiple" name="classifyId" id="classifyId" required='required'>
											<c:if test="${classifyList!=null}">
												<c:forEach items="${classifyList}" var="classify">
													<option value="${classify.id}" >${classify.name}</option>
												</c:forEach>
											</c:if>
										</select>
									</div>
									<div class="col-lg-4">
										<label class="control-label" style="color: #ccc;">*必填项</label>
									</div>
								</div>
								<div class="form-group item item">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">年级：</label>
									</div>
									<div class="col-lg-6">
										<select  class="form-control required" name="grade" data-bv-notempty data-bv-notempty-message="年级必须选">
											<option value="">--请选择年级--</option>
								    		<option value="1" <c:if test="${course.grade==1}">selected="selected"</c:if>>大学一年级</option>
								    		<option value="2" <c:if test="${course.grade==2}">selected="selected"</c:if>>大学二年级</option>
								    		<option value="3" <c:if test="${course.grade==3}">selected="selected"</c:if>>大学三年级</option>
								    		<option value="4" <c:if test="${course.grade==4}">selected="selected"</c:if>>大学四年级</option>
										</select>
									</div>
									<div class="col-lg-4">
										<label class="control-label" style="color: #ccc;">*必填项</label>
									</div>
								</div>
								<div class="form-group item">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">TERM：</label>
									</div>
									<div class="col-lg-6">
										<select  class="form-control required" name="term" data-bv-notempty data-bv-notempty-message="term必须选">
											<option value="">--请选择TERM--</option>
								    		<option value="0" <c:if test="${course.term==0}">selected="selected"</c:if>>Summer</option>
								    		<option value="1" <c:if test="${course.term==1}">selected="selected"</c:if>>Fall</option>
								    		<option value="2" <c:if test="${course.term==2}">selected="selected"</c:if>>Winter</option>
										</select>
									</div>
									<div class="col-lg-4">
										<label class="control-label" style="color: #ccc;">*必填项</label>
									</div>
								</div>
								<div class="form-group item">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">标签：</label>
									</div>
									<div class="col-lg-6">
										<select  class="form-control required" name="tag" data-bv-notempty data-bv-notempty-message="标签必须选">
											<option value="">--请选择标签--</option>
											<option value="0" <c:if test="${course.tag==0}">selected="selected"</c:if>>视频课程</option>
								    		<option value="1" <c:if test="${course.tag==1}">selected="selected"</c:if>>预约课程</option>
								    		<option value="2" <c:if test="${course.tag==2}">selected="selected"</c:if>>学习小组</option>
										</select>
									</div>
									<div class="col-lg-4">
										<label class="control-label" style="color: #ccc;">*必填项</label>
									</div>
								</div>
								<div class="form-group item">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">校区：</label>
									</div>
									<div class="col-lg-6">
										
										<select class="select2_multiple form-control required" multiple="multiple" name="campusId" id="campusId" required='required'>
											<c:if test="${campusList!=null}">
												<c:forEach items="${campusList}" var="campus">
													<option value="${campus.id}">${campus.name}</option>
												</c:forEach>
											</c:if>
										</select>
									</div>
									<div class="col-lg-4">
										<label class="control-label" style="color: #ccc;">*必填项</label>
									</div>
								</div>
								<div class="form-group item">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">学分：</label>
									</div>
									<div class="col-lg-6"> <!-- data-notempty data-notempty-message="课程学分不能为空" -->
										<input type="number" class="form-control number required" data-validate-length-range="0,3" placeholder="请输入课程学分" name="credit" id="credit"  value="${course.credit}" 
										 >
									</div>
									<div class="col-lg-4">
										<label class="control-label" style="color: #ccc;">*必填项</label>
									</div>
								</div>
								<div class="form-group item">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">难度：</label>
									</div>
									<div class="col-lg-6">
										<input type="number" class="form-control number required"  placeholder="请输入课程难度(1-5)表示" name="difficulty" id="difficulty"  value="${course.difficulty}" data-validate-length-range="0,1" >
									</div>
									<div class="col-lg-4">
										<label class="control-label" style="color: #ccc;">*必填项</label>
									</div>
								</div>
								<div class="form-group item">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">课时：</label>
									</div>
									<div class="col-lg-6">
										<input type="number" class="form-control number required"  placeholder="请输入课程课时" name="classHour" id="classHour"  value="${course.classHour}" data-validate-length-range="0,5"
										>
									</div>
									<div class="col-lg-4">
										<label class="control-label" style="color: #ccc;">*必填项</label>
									</div>
								</div>
								<div class="form-group item">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">价格：</label>
									</div>
									<div class="col-lg-6">
										<input type="number" class="form-control number required" placeholder="请输入课程价格" name="price" id="price"  value="${course.price}" 
										data-validate-length-range="0,7" >
									</div>
									<div class="col-lg-4">
										<label class="control-label" style="color: #ccc;">*必填项</label>
									</div>
								</div>
								
								<div class="form-group item">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">苹果商店的价格：</label>
									</div>
									<div class="col-lg-6">
										<input type="number" class="form-control number required" placeholder="请输入苹果商店的课程价格" name="priceIos" id="priceIos"  value="${course.priceIos}" 
										data-validate-length-range="0,7" >
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
										<input type="number" class="form-control number required" placeholder="请输入课程税额" name="onTax" id="onTax"  value="${course.onTax}" 
										data-validate-length-range="0,6" >
									</div>
									<div class="col-lg-4">
										<label class="control-label" style="color: #ccc;">*必填项</label>
									</div>
								</div>
								
								<div class="form-group item">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">适合人群：</label>
									</div>
									<div class="col-lg-6">
										<input type="text" class="form-control" placeholder="请输入适合人群" name="forCrowd" id="forCrowd"  value="${course.forCrowd}"  data-validate-length-range="0,150">
									</div>
								</div>
								<div class="form-group item">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">学习目标：</label>
									</div>
									<div class="col-lg-6">
										<input type="text" class="form-control" placeholder="请输入学习目标" name="studyGoal" id="studyGoal"  value="${course.studyGoal}"  data-validate-length-range="0,150">
									</div>
								</div>
								
								<div class="form-group item">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">视频外部链接：</label>
									</div>
									<div class="col-lg-6">
										<input type="url" class="form-control url" placeholder="请输入视频名称" name="videoHttpUrl" id="videoHttpUrl"  value="${course.videoHttpUrl}"  data-bv-stringlength="true" data-validate-length-range="0,500" data-bv-stringlength-message="视频链接最大长度为500">
									</div>
									<div class="col-lg-4">
									</div>
								</div>
								
								<div class="form-group item">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">课程老师：</label>
									</div>
									<div class="col-lg-6">
										<input type="text" class="form-control required" placeholder="请输入课程老师" name="teacher" id="teacher"  value="${course.teacher}" data-validate-length-range="0,20">
									</div>
									<div class="col-lg-4">
										<label class="control-label" style="color: #ccc;">*必填项</label>
									</div>
								</div>
								<div class="form-group item">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">课程老师描述：</label>
									</div>
									<div class="col-lg-6">
										<input type="text" class="form-control "  placeholder="请输入课程老师描述" name="teacherDesc" id="teacherDesc"  value="${course.teacherDesc}" data-validate-length-range="0,150" >
									</div>
									<div class="col-lg-4">
									</div>
								</div>
<!-- 								<div class="form-group item"> -->
<!-- 									<div class="col-lg-2" style="text-align: right;"> -->
<!-- 									<label class="control-label">课程折扣：</label> -->
<!-- 									</div> -->
<!-- 									<div class="col-lg-6"> -->
<%-- 										<input type="number" class="form-control number required" data-validate-length-range="0,3" placeholder="请输入课程折扣(%)" name="offset" id="offset"  value="${course.offset}"  > --%>
<!-- 									</div> -->
<!-- 									<div class="col-lg-4"> -->
<!-- 										<label class="control-label" style="color: #ccc;">*必填项</label> -->
<!-- 									</div> -->
<!-- 								</div> -->
								
								<div class="form-group item">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">对应苹果商品ID：</label>
									</div>
									<div class="col-lg-6">
										<input type="text" class="form-control "  placeholder="请输入对应苹果商品ID" name="appleProductId" id="appleProductId"  value="${course.appleProductId}" data-validate-length-range="0,100" >
									</div>
									<div class="col-lg-4">
									</div>
								</div>
								
								<div class="form-group item">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">排序：</label>
									</div>
									<div class="col-lg-6">
										<input type="text" class="form-control number" placeholder="请输入排序序号" name="sort" id="sort" value="${course.sort}" data-validate-length-range="0,6" >
									</div>
									<div class="col-lg-4">
										<label class="control-label" style="color: #ccc;">（正序排序1、2、3...）</label>
									</div>
								</div>
							
								<div class="form-group item">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">内容难度：</label>
									</div>
									<div class="col-lg-6">
										<input type="number" class="form-control number required" placeholder="请输入内容难度" name="contentDifficulty" id="contentDifficulty"  value="${course.contentDifficulty}"  data-validate-length-range="0,4">
									</div>
									<div class="col-lg-4">
										<label class="control-label" style="color: #ccc;">*必填项</label>
									</div>
								</div>
								
								<div class="form-group item">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">作业难度：</label>
									</div>
									<div class="col-lg-6">
										<input type="number" class="form-control number required" placeholder="请输入作业难度" name="courseworkDifficulty" id="courseworkDifficulty"  value="${course.courseworkDifficulty}"  data-validate-length-range="0,4">
									</div>
									<div class="col-lg-4">
										<label class="control-label" style="color: #ccc;">*必填项</label>
									</div>
								</div>
								
								<div class="form-group item">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">考试难度：</label>
									</div>
									<div class="col-lg-6">
										<input type="number" class="form-control number required" placeholder="请输入考试难度" name="examDifficulty" id="examDifficulty"  value="${course.examDifficulty}"  data-validate-length-range="0,4">
									</div>
									<div class="col-lg-4">
										<label class="control-label" style="color: #ccc;">*必填项</label>
									</div>
								</div>
								
								<div class="form-group item">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">综合难度：</label>
									</div>
									<div class="col-lg-6">
										<input type="number" class="form-control number required" placeholder="请输入综合难度" name="overallDifficulty" id="overallDifficulty"  value="${course.overallDifficulty}"  data-validate-length-range="0,4">
									</div>
									<div class="col-lg-4">
										<label class="control-label" style="color: #ccc;">*必填项</label>
									</div>
								</div>
								
								<div class="form-group item">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">教授1：</label>
									</div>
									<div class="col-lg-6">
										<input type="text" class="form-control" placeholder="请输入教授1" name="prof1" id="prof1"  value="${course.prof1}"  data-validate-length-range="0,150">
									</div>
									<div class="col-lg-4">
										<label class="control-label" style="color: #ccc;">*必填项</label>
									</div>
								</div>
								
								<div class="form-group item">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">教授1难度：</label>
									</div>
									<div class="col-lg-6">
										<input type="number" class="form-control number required" placeholder="请输入教授1难度" name="prof1Difficulty" id="prof1Difficulty"  value="${course.prof1Difficulty}"  data-validate-length-range="0,4">
									</div>
									<div class="col-lg-4">
										<label class="control-label" style="color: #ccc;">*必填项</label>
									</div>
								</div>
								
								<div class="form-group item">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">教授2：</label>
									</div>
									<div class="col-lg-6">
										<input type="text" class="form-control" placeholder="请输入教授2" name="prof2" id="prof2"  value="${course.prof2}"  data-validate-length-range="0,150">
									</div>
									<div class="col-lg-4">
										<label class="control-label" style="color: #ccc;">*必填项</label>
									</div>
								</div>
								
								<div class="form-group item">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">教授2难度：</label>
									</div>
									<div class="col-lg-6">
										<input type="number" class="form-control number required" placeholder="请输入教授2难度" name="prof2Difficulty" id="prof2Difficulty"  value="${course.prof2Difficulty}"  data-validate-length-range="0,4">
									</div>
									<div class="col-lg-4">
										<label class="control-label" style="color: #ccc;">*必填项</label>
									</div>
								</div>
								
								<div class="form-group item">
									<div class="col-lg-2" style="text-align: right;" >
										<label class="control-label">课程封面图片：</label>
									</div>
									<div class="col-lg-4" style="height: 210px">
										<div class="col-lg-4" style="height: 100px;" id="imgDiv">
											<img alt="" src="
											<c:if test="${not empty course.photo}">${imgDomain}/${course.photo}</c:if>
											<c:if test="${empty course.photo}">${ctx}resources/images/default.jpeg</c:if>"  width="300" height="200" id="logoImg">
										</div><br>
									</div>
								</div>
								<div class="form-group item">
									<div class="col-lg-2">
										<label class="control-label"></label>
									</div>
									<div class="col-lg-4">
										<input type="file" id="logoFile"  name="file1" class="hidden"/>
										<a class="btn btn-primary" href="javascript:void(0)" onclick="$('#logoFile').click()"><i class="fa fa-folder-open fa-lg"></i>选择...</a>
									</div>
								</div>
								
								<div class="form-group item">
									<div class="col-lg-2" style="text-align: right;" >
										<label class="control-label">视频封面图片：</label>
									</div>
									<div class="col-lg-4" style="height: 210px">
										<div class="col-lg-4" style="height: 100px;" id="imgDiv2">
											<img alt="" src="
											<c:if test="${not empty course.coverImg}">${imgDomain}/${course.coverImg}</c:if>
											<c:if test="${empty course.coverImg}">${ctx}resources/images/default.jpeg</c:if>"  width="400" height="200" id="logoImg2">
										</div><br>
									</div>
								</div>
								<div class="form-group item">
									<div class="col-lg-2">
										<label class="control-label"></label>
									</div>
									<div class="col-lg-4">
										<input type="file" id="logoFile2"  name="file2" class="hidden"/>
										<a class="btn btn-primary" href="javascript:void(0)" onclick="$('#logoFile2').click()"><i class="fa fa-folder-open fa-lg"></i>选择...</a>
									</div>
								</div>
								
								<div class="form-group item">
									<div class="col-lg-2" style="text-align: right;">
										<label class="control-label">课程视频：</label>
									</div>
								    <div class="col-lg-6" id="fileDiv">
								    	 <c:if test="${not empty course.video}">
									    	 <video id="exampleVideo" class="video-js vjs-default-skin"  controls preload="none" width="530" height="240"
											      poster="${imgDomain}/${course.video}"
											      data-setup="{}">
											    <source id="source1" src="${imgDomain}/${course.video}" type='video/mp4' />
											    <!-- <track kind="captions" src="demo.captions.vtt" srclang="en" label="English"></track>Tracks need an ending tag thanks to IE9
											    <track kind="subtitles" src="demo.captions.vtt" srclang="en" label="English"></track>Tracks need an ending tag thanks to IE9 -->
											  </video>
										  </c:if>
										<c:if test="${ empty course.video }">
								    		<input id="myFile" type="file" name="file" data-bv-notempty  data-bv-notempty-message="视频必须上传"/>
								    	</c:if>
								    	<c:if test="${not empty course.video}">
								    		<input id="myFile" type="file" name="file"/>
								    	</c:if>
								    </div>
								    <div class="col-lg-4">
								    </div>
								</div>	
								
								<div class="form-group item">
									<div class="col-lg-2" style="text-align: right;">
									<label class="control-label">课程详情：</label>
									</div>
									<div class="col-lg-6">
										<textarea  name="courseDetail" id="courseDetail" >${course.courseDetail}</textarea>
									</div>
								</div>
								<div class="form-group item">
									<div class="col-lg-4" >
										<label class="control-label">&nbsp;</label>
									</div>
									<div class="col-lg-1" >
									</div>
									<div class="col-lg-1" >
										<!-- <input type="button" class="form-control btn btn-success" id="save" onclick="saveCourse();" value="保存"/> -->
										<input type=submit class="form-control btn btn-success" id="save"  value="保存"/>
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
            editor = K.create('#courseDetail', {
           	width: '800px',
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
		
		$("#logoFile").click(function(){
			new uploadPreview({ UpBtn: "logoFile", DivShow: "imgDiv", ImgShow: "logoImg" });
		});
		
		$("#logoFile2").click(function(){
			new uploadPreview({ UpBtn: "logoFile2", DivShow: "imgDiv2", ImgShow: "logoImg2" });
		});
		
		/*
		* 给下拉多选框赋值
		*/
		var clsId='${course.classifyId}';
		var arr=clsId.split(",");
		var sel=document.getElementById("classifyId");
		var len=sel.options.length;
		for(var i=0;i<len;i++){
			for(var j=0;j<len;j++){
				if(sel.options[j].value==arr[i]){
					sel.options[j].setAttribute("selected",true);
					break;
				}
			}
		}

		//$("#addCourseForm").bootstrapValidator();
		
		//0.初始化fileinput
	    var oFileInput = new FileInput();
	    oFileInput.Init("myFile",ctx+"admin/upload/uploadImg?dir=video");
	    
	    /*表单提交*/
		$("#addCourseForm").submit(function(){
			var id = $("#id").val();
			var url="";
			if( !validator.checkAll($("#addCourseForm"))){
				return;
			}
			if(id == null || id == 0){
				$("#addCourseForm").attr("action",ctx+"admin/course/addCourse?time="+(new Date()).getTime());
			}else{
				$("#addCourseForm").attr("action",ctx+"admin/course/updateCourse?time="+(new Date()).getTime());
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
				//maxFileSize: 10240,//单位为kb，如果为0表示不限制文件大小
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
		
	
	function saveCourse(){
    	var id = $("#id").val();
		var url="";
		if(id == null || id == 0){
			url=ctx+"admin/course/addCourse?time="+(new Date()).getTime();
		}else{
			url=ctx+"admin/course/updateCourse?time="+(new Date()).getTime();
		}
		/* alert(url);
		if( !validator.checkAll( $("#addCourseForm") ) ){
			return;
		} */
		
		swal({"title":"","text":"正在处理……请稍后","showConfirmButton":false});
		$.ajax({
			url: url,
			dataType: "json",
			data:$("#addCourseForm").serialize(),
			success: function(result){
				if(result.status == "0"){
					swal({title:'',text:"操作成功"},function(){
						//$('#userModal').modal('hide');
						//search();
						window.location.reload();//刷新页面
					});
				}else{
					swal({title:'',text:result.msg,type:"error"});
				}
			}
		});
	}
		/*取消*/
		function cancelBtn(){
			window.location.href= ctx+ 'admin/course/toCoursePage?time='+(new Date()).getTime();
		}
		
	</script>
</body>
</html>