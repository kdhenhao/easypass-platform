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
						<h2>公告管理<small>公告修改</small></h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<form role="form" id="searchForm" novalidate class="form-horizontal">
							<div class="form-group item">
								<label for="fullName2" class="col-lg-1 col-sm-1 col-xs-12 control-label">公告标题：</label>
					    		<div class="col-lg-8 col-sm-8 col-xs-12">
					    			<input type="text" class="form-control" id="title" name="title" value="${brochure.title}"
					    				placeholder="公告标题" required='required' data-validate-length-range="0,20">
					    		</div>
							</div>
						</form>
						<div id="summernote" class="col-lg-9">${brochure.content}</div>
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="text-align:center">
							<button type="button" class="btn btn-dark btn-sm" onclick="cancelEdit();">取消</button>
							<c:if test="${empty brochure}">
								<button type="button" class="btn btn-primary btn-sm" onclick="save();">保存草稿</button>
								<button type="button" class="btn btn-primary btn-sm" onclick="saveAndPublish();">发布</button>
							</c:if>
							<c:if test="${!empty brochure}">
								<c:if test="${brochure.isPublish eq '0'}">
									<button type="button" class="btn btn-primary btn-sm" onclick="save();">保存草稿</button>
									<button type="button" class="btn btn-primary btn-sm" onclick="saveAndPublish();">发布</button>
								</c:if>
								<c:if test="${brochure.isPublish eq '1'}">
									<button type="button" class="btn btn-primary btn-sm" onclick="save();">保存</button>
								</c:if>
							</c:if>
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
		$('#summernote').summernote({
		    lang: 'zh-CN',
		    height: 350,
		    minHeight: 350,
		    maxHeight: null,
		    focus: true ,
		    callbacks: {  
	            "onImageUpload": function(files) {
	                img = sendFile(files[0]);  
	        	}  
	    	}  
		});
	});
	function sendFile(file) {  
	    data = new FormData();  
	    data.append("file", file);  
	    console.log(data);  
	    $.ajax({  
	        data: data,
	        dataType:'json',
	        type: "POST",  
	        url: ctx+"admin/upload/uploadImg?dir=images",  
	        cache: false,  
	        contentType: false,  
	        processData: false,  
	        success: function(result) {
	        	var url = result.data.src;
	            $("#summernote").summernote('insertImage', url, '');
	        }  
	    });  
	}  
	
	function cancelEdit() {
		window.location = ctx + "admin/news/toNewsPage";
	}

	function save() {
		if( !validator.checkAll( $("#searchForm") ) ){
			return;
		}
		var str = $('#summernote').summernote('code');
		if(str == "" || str == "<p><br></p>"){
			swal("","内容不能为空","error");
			return;
		}
		$.ajax({
		    type: "post",
		    url: ctx + "admin/news/saveOrupdateNews",
		    data:{"id":"${brochure.id}","content":str,"title":$("#title").val()},
		    dataType: 'json',
		    success: function(result) {
		        if (result.status == 0) {
		            swal({"title":"", "text":"保存成功","type":"info"}, cancelEdit);
		        } else {
		        	swal({"title":"", "text":result.msg,"type":"error"});
		        }
		    }
		});
	}
	function saveAndPublish() {
		if( !validator.checkAll( $("#searchForm") ) ){
			return;
		}
		var str = $('#summernote').summernote('code');
		if(str == "" || str == "<p><br></p>"){
			swal("","内容不能为空","error");
			return;
		}
		$.ajax({
		    type: "post",
		    url: ctx + "admin/news/publishOrupdateNews",
		    data:{"id":"${brochure.id}","content":str,"title":$("#title").val()},
		    dataType: 'json',
		    success: function(result) {
		        if (result.status == 0) {
		            swal({"title":"", "text":"发布成功","type":"info"}, cancelEdit);
		        } else {
		        	swal({"title":"", "text":result.msg,"type":"error"});
		        }
		    }
		});
	}
	</script>
</body>
</html>