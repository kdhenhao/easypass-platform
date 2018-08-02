<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/common/head.jsp"%>
</head>
<body class="login" style="background-size: cover; background-repeat:no-repeat;">
	<div>
		<a class="hiddenanchor" id="signup"></a> 
		<a class="hiddenanchor" id="signin"></a>

		<div class="login_wrapper">
			<div class="animate form login_form">
				<section class="login_content">
					<form id="loginForm" action="${ctx}login" method="post">
						<h1>请登陆</h1>
						<div>
							<input type="text" class="form-control" placeholder="用户名" id="username" name="username" value="${username}"/>
						</div>
						<div>
							<input type="password" class="form-control" placeholder="密码" id="password" name="password"/>
						</div>
        				<div class="container">
        					<div class="row">
						        <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
						        	<input type="text" class="form-control" placeholder="验证码" id="identifyCode" name="identifyCode">
						        </div>
						        <div class="col-xs-4 col-xs-8 col-xs-8 col-xs-8">
						        	<img style="width:100%;height:34px;" id="code" alt="看不清？点一下"/>
						        </div>      
						    </div>
				        </div>
						<div>
							<a class="btn btn-default submit" href="javascript:void(0);" onclick="tryLogin();">登陆</a>
							<a class="btn btn-default submit" href="javascript:void(0);" onclick="">返回首页</a>
							<!-- <a class="reset_pass" href="#">忘记密码？</a> -->
						</div>
						<div class="clearfix"></div>
						<div class="separator">
							<!-- <p class="change_link">
								新用户? <a href="#signup" class="to_register"> 注册帐号 </a>
							</p> -->
							<c:if test="${!empty errorMsg}">
								<p>${errorMsg}</p>
							</c:if>
							<div class="clearfix"></div>
							<div>
								<h1>
									<i class="fa fa-plane"></i> easypass后台管理系统
								</h1>
								<p>©2017 All Rights Reserved.</p>
							</div>
						</div>
					</form>
				</section>
			</div>

			<div id="register" class="animate form registration_form">
				<section class="login_content">
					<form>
						<h1>注册</h1>
						<div>
							<input type="text" class="form-control" placeholder="用户名"
								required="" />
						</div>
						<div>
							<input type="email" class="form-control" placeholder="邮箱"
								required="" />
						</div>
						<div>
							<input type="password" class="form-control" placeholder="密码"
								required="" />
						</div>
						<div>
							<a class="btn btn-default submit" href="index.html">提交</a>
						</div>
						<div class="clearfix"></div>
						<div class="separator">
							<p class="change_link">
								已有帐号 ? <a href="#signin" class="to_register"> 登陆 </a>
							</p>
							<div class="clearfix"></div>
							<br />
							<div>
								<h1>
									<i class="fa fa-plane"></i> easypass后台管理系统
								</h1>
								<p>©2016 All Rights Reserved.</p>
							</div>
						</div>
					</form>
				</section>
			</div>
		</div>
	</div>
	<%@ include file="/WEB-INF/common/script.jsp"%>
	<script>
	$(function(){
		//验证码
		$("#code").attr("src", ctx + "identifyCode?uuid=" + (new Date()).getTime());
		$("#code").bind("click",function() {
			$(this).attr("src", ctx + "identifyCode?uuid=" + (new Date()).getTime());
		});
		//绑定回车登陆
		$("#identifyCode").bind('keypress',function(event){
            if(event.keyCode == "13") {
            	tryLogin();
            }
        });
	});
	function tryLogin(){
		if($("#username").val() == ""){
			swal("","用户名不能为空","warning");
			return;
		}
		if($("#password").val() == ""){
			swal("","密码不能为空","warning");
			return;
		}
		if($("#identifyCode").val() == ""){
			swal("","验证码不能为空","warning");
			return;
		}
		//清除菜单选择
		document.cookie = 'currentParMenu=;expires=-1; path=/'; 
		document.cookie = 'currentSubMenu=;expires=-1; path=/'; 
		document.cookie = "bodyClass=nav-md; path=/";
		$("#loginForm").submit();
	}
	function toWeb(){
		window.location = domain+"/easypass-platform";
	}
	</script>
</body>
</html>
