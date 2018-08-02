<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="col-md-3 left_col menu_fixed">
	<div class="left_col scroll-view">
		<div class="navbar nav_title" style="border: 0;">
			<a href="javascript:void(0);" class="site_title">
				<i class="fa fa-plane"></i>
				<span>easypass管理系统</span>
			</a>
		</div>
		<div class="clearfix"></div>
		<!-- menu profile quick info -->
		<%-- <div class="profile">
			<div class="profile_pic">
				<img src="${imgDomain}/${sessionScope.sessionUser.hospitalLogo}" class="img-circle profile_img">
			</div>
			<div class="profile_info">
				<span>${sessionScope.sessionUser.hospitalName}</span>
				<h2>${sessionScope.sessionUser.departmentName}</h2>
				<!-- <span>0239482</span>
				<h2>lsdjf</h2> -->
			</div>
		</div> --%>
		<div class="clearfix"></div>
		<!-- /menu profile quick info -->
		<!-- sidebar menu -->
		<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
			<div class="menu_section">
				<ul class="nav side-menu">
					<c:forEach items="${menus}" var="parentMenu">
						<shiro:hasPermission name="${parentMenu.id}">
						<li>
							<a><i class="fa ${parentMenu.img}"></i> ${parentMenu.authName} <span class="fa fa-chevron-down"></span></a>
							<ul class="nav child_menu" <c:if test="${cookie.bodyClass.value eq 'nav-md' && cookie.currentParMenu.value eq parentMenu.id}">style="display:block;"</c:if>>
								<c:forEach items="${parentMenu.children}" var="childMenu">
									<shiro:hasPermission name="${childMenu.id}">
										<li <c:if test="${cookie.currentSubMenu.value eq childMenu.id}">class="current-page"</c:if>><a href="${ctx}${childMenu.href}" data-subMenu="${childMenu.id}" data-parMenu="${parentMenu.id}" onclick="setMenuCookie(this);">${childMenu.authName}</a></li> 
									</shiro:hasPermission>
								</c:forEach>
							</ul>
						</li>
						</shiro:hasPermission>
					</c:forEach>
				</ul>
			</div>
		</div>
		<!-- /sidebar menu -->

		<!-- /menu footer buttons 
            <div class="sidebar-footer hidden-small">
              <a data-toggle="tooltip" data-placement="top" title="Settings">
                <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="FullScreen">
                <span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="Lock">
                <span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="Logout">
                <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
              </a>
            </div>-->
		<!-- /menu footer buttons -->
	</div>
</div>
<script>
function setMenuCookie(obj){
	document.cookie = 'currentParMenu=' + $(obj).attr("data-parMenu")+"; path=/"; 
	document.cookie = 'currentSubMenu=' + $(obj).attr("data-subMenu")+"; path=/"; 
}
</script>