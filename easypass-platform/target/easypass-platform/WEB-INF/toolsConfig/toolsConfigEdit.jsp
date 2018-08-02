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
						<h2>配置管理<small>配置修改-${toolsConfig.name}</small></h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<div >
							<textarea  name="content" id="content" >${toolsConfig.content}</textarea>
						</div>
						
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" style="text-align:center">
							<button type="button" class="btn btn-success" onclick="save();">保存</button>
							<button type="button" class="btn btn-default" onclick="cancelEdit();">取消</button>
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
		//文本编辑器
		 KindEditor.ready(function (K) {
             editor = K.create('#content', {
            	width: '1000px',
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
	});
	
	
	function cancelEdit() {
		window.location = ctx + "admin/toolsConfig/toolsConfigList";
	}

	function save() {
		var str = $('#content').val();
		if(str == ""){
			alert(str);
			swal("","内容不能为空","error");
			return;
		}
		$.ajax({
		    type: "post",
		    url: ctx+ "admin/toolsConfig/updateConfig",
		    data:{"id":"${toolsConfig.id}","content":str},
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