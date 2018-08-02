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
						<h2>单页管理<small>单页修改-${brochure.title}</small></h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<div id="summernote">${brochure.content}</div>
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="text-align:center">
							<button type="button" class="btn btn-dark btn-sm" onclick="cancelEdit();">取消</button>
							<button type="button" class="btn btn-primary btn-sm" onclick="save();">保存</button>
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
		window.location = ctx + "admin/brochure/toBrochurePage";
	}

	function save() {
		var str = $('#summernote').summernote('code');
		if(str == "" || str == "<p><br></p>"){
			swal("","内容不能为空","error");
			return;
		}
		$.ajax({
		    type: "post",
		    url: ctx + "admin/brochure/updateBrochure",
		    data:{"id":"${brochure.id}","content":str},
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
	</script>
</body>
</html>