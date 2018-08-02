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
						<h2>课程课时统计</h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<form role="form" id="searchForm">
							<div class="container">
								<div class="row">
			
									  <div class="col-sm-2">
						    	<select id="provinceCode"  class="form-control required" onchange="onProvinceChange();">
						    		<option value="">--请选择--</option>
						    		<c:forEach items="${course}" var="opt">
						    			<option value="${opt.id}">${opt.name}</option>
						    		</c:forEach>
						    	</select>
						    	<input type="hidden" id="province2" name="map['courseName']">
						    </div>
						    <div class="col-sm-2">
						    	<select id="cityCode"  class="form-control required" onchange="onCityChange();">
						    		<option value="">--请选择--</option>
						    		
						    	</select>
						    	<input type="hidden" id="city2" name="map['courseHourName']">
						    </div>
									
									
										<input type="hidden" class="form-control input-sm" placeholder="1代表课程" id="courseFlag" name="map['courseFlag']" value=" "/>
									 
									 <div class="col-lg-4">
									<div class="input-group input-inline-sm" >
										<span class="input-group-addon" >开始时间</span>
										<input type="text" class="form-control" name="beginTime" readOnly id="timeMin">
										<span class="input-group-addon">至</span>
										<input type="text" class="form-control"  name="endTime" readOnly id="timeMax" >
									</div>
									</div>
									
								<div class="col-lg-2">						
										<button type="button" class="btn btn-primary btn-sm" onclick="search();">搜索</button>
										<button type="button" class="btn btn-primary btn-sm" onclick="clearDatas();">清空</button>
								</div>	
								<div class="row">
									
									
								</div>
								<div class="col-lg-2">
										<input class="form-control input-sm" placeholder="总销售额" id="totalPrice" readOnly value="${totalPrice}"/>
								</div>
									
								</div>
								 
							</div>
							
						</form>
						<div class="table-responsive">
							<table id="users" class="table table-bordered table-striped table-hover" 
							  cellspacing="0" width="100%">
								<thead>
									<tr class="mytableHead">
										<th class="myRowNumber"></th>										
										<th>订单编号</th>
										<th>课程名称</th>
										<th>课时名称</th>
										<th>支付时间</th>
										<th>价格</th>		
										<th>支付方式</th>
										
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


	<%@ include file="/WEB-INF/common/script.jsp"%>
	<script>
	$(function(){
		
	
		
		var oDate = new Date(); //实例一个时间对象；
      	var year = oDate.getFullYear();   
       	var month = oDate.getMonth()+1;   
        var day = oDate.getDate();
        var month = month<10?"0"+month:month;
        var day = month<10?"0"+day:day;
        var t1 =year+"-"+month+"-"+day;
        
        $("#courseFlag").val(1);
		 
	    $("#timeMin").daterangepicker(datePickerOpt);
		$("#timeMax").daterangepicker(datePickerOpt);
		var t = $("#users").DataTable({
			
		    ajax: {
		        url: ctx+'admin/CourseStatistics/findCourseStatistics',
		        type: 'POST',
		        data: function(d){$.extend(d,serializeObject($("#searchForm")))}
		    },
		    columns: [
		        { "data":null,"render":rowNumberRender},               
		        { "data":'orderNo'},
		        { "data":'courseName'},
		        { "data":'courseHourName'},
		        { "data":'payTime'},
		        { "data":'price',
		          "render":function (data, type, row) {
                    row.price = Number(data).toFixed(2);
                    return row.price;
                   }
		        }, 
		        { "data":'payWay'}
		        
		    ]
		});
		//绑定事件
		$("#searchForm input").keydown(function(event){
			if(event.keyCode == 13){
				search();
			}
		});
		$("#searchForm select").change(search);
	});
	function search(){
		
		$('#users').DataTable().ajax.reload(function(d){$.extend(d,serializeObject($("#searchForm")))}, true);;
			totalPrice();
		}
	
	function searchCurrentPage(){
		$('#users').DataTable().ajax.reload(function(d){$.extend(d,serializeObject($("#searchForm")))}, false);;
	}
	function ifnull(str){
		if(str == null || typeof(str) == "undefined"){
			return "";
		}
		return str;
	}
	function optRender(data, type, row, meta){
		var but = '<a class="btn btn-info btn-xs" href="javascript:showDetail('+meta.row+')"><i class="fa fa-pencil"></i>编辑</a>';
		/* but = but + '<a class="btn btn-danger btn-xs" href="javascript:deleteById('+row.id+')"><i class="fa fa-minus"></i>删除</a>'; */
		return but;
	}
	
	
	/* 清空 */
	function clearDatas(){
		$("#timeMin").val('');
    	$("#timeMax").val('');
    	$("#city2").val('');
     	//$("#cityCode option:selected").text("--请选择--");
     	$("#cityCode").empty().append("<option value=''>--请选择--</option>");
		$("#province2").val('');
    	$("#totalPrice").val('');
    	$("#provinceCode").each(function(){     
    		$(this).find("option").eq(0).prop("selected",true);   
    	}); 
    	
    	search();
    	 
	}
	
	
	
	/* 课程选择  */
	function onProvinceChange(){
		var temp =$("#provinceCode option:selected").text();
		if(temp=="--请选择--"){
			var courseId = null;
			$("#province2").val("");
			$("#city2").val("");
			$("#courseFlag").val(1);
		}else{
			var courseId = $("#provinceCode option:selected").val();
			$("#province2").val($("#provinceCode option:selected").text());
		}
		
		
		$("#cityCode").empty().append("<option value=''>--请选择--</option>");
		if(courseId == null || courseId == ""){
			return;
		}		
		$.ajax({
		
			url : ctx + "admin/CourseStatistics/findCourseHourNameForSelect?courseId="+courseId,
			dataType : 'json',
			success : function(result) {
			
				for(var i=0;i<result.length;i++){
					$("#cityCode").append("<option value='" + result[i].id+ "'>"
							+ result[i].name + "</option>");
				}
				if(callback != null){
					callback();
				}
			}
		});
		
	}
	/* 课时选择  */
	function onCityChange(){
		var temp =$("#cityCode option:selected").text();
		if(temp=="--请选择--"){
			$("#city2").val("");
			$("#courseFlag").val(1);
		}else{
			$("#city2").val($("#cityCode option:selected").text());
			$("#courseFlag").val(0);
		}
	}
	/* 计算销售总额  */
	function totalPrice(){
	 	var beginTime = $("#timeMin").val();
		var endTime	= $("#timeMax").val();
		var orderNo	= $("#orderNo").val();
		var courseFlag = $("#courseFlag").val();
		
		var temp =$("#provinceCode option:selected").text();
		var courseName;
		if(temp=="--请选择--"){
			courseName = "";
		}else{
			courseName = temp;
		}
		var courseHourName;
		var temp1 =$("#cityCode option:selected").text();
		if(temp1=="--请选择--"){
			courseHourName = "";
			
		}else{
			courseHourName = temp1;
			
		}
		
		$.ajax({
		    url: ctx+'admin/CourseStatistics/findTotalPrice?beginTime='+beginTime+"&endTime="+endTime+"&orderNo="+orderNo+"&courseFlag="+courseFlag+"&courseName="+courseName+"&courseHourName="+courseHourName,
		    type: 'POST',
		    data: function(d){$.extend(d,serializeObject($("#searchForm")))},
		    success:function(data) { 
	        			$("#totalPrice").val(data);
	                },    	        
		});
	}
	
	
	
	
	</script>
</body>
</html>