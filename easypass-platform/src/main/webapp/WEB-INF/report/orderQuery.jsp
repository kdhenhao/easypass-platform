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
						<h2>订单统计</h2>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<form role="form" id="searchForm">
							<div class="container">
								<div class="row">
									<div class="col-lg-3">
										<input class="form-control input-sm" placeholder="订单编号" id="orderNo" name="map['orderNo']"/>
									</div>
									<div class="col-lg-5">
									<div class="input-group input-inline-sm" >
										<span class="input-group-addon" >开始时间</span>
										<input type="text" class="form-control" name="startTime" readOnly id="timeMin">
										<span class="input-group-addon">至</span>
										<input type="text" class="form-control"  name="endTime" readOnly id="timeMax" >
									</div>
									</div>
									<div class="col-lg-3">						
										<button type="button" class="btn btn-primary btn-sm" onclick="search();">搜索</button>
										<button type="button" class="btn btn-primary btn-sm" onclick="clearDatas();">清空</button>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-3">
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
										<th>创建时间</th>
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
         
		/*  $("#timeMin").val(t1);
		 $("#timeMax").val(t1); */
	    $("#timeMin").daterangepicker(datePickerOpt);
		$("#timeMax").daterangepicker(datePickerOpt);
		
		var t = $("#users").DataTable({
		    ajax: {
		        url: ctx+'admin/orderQuery/findOrderQuerys',
		        type: 'POST',
		        data: function(d){$.extend(d,serializeObject($("#searchForm")))}
		    },
		    columns: [
		        { "data":null,"render":rowNumberRender},               
		        { "data":'orderNo'},
		        { "data":'createTime'},
		        { "data":'price',
		        	"render":function (data, type, row) {
	                    row.price = Number(data).toFixed(2);
	                    return row.price;
	                 }	
		        }, 
		        { "data":'payWay'},
		         
		        
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
	
	function clearDatas(){
	   	$("#orderNo").val('');
    	$("#timeMin").val('');
    	$("#timeMax").val('');
    	$("#totalPrice").val('');
    	searchCurrentPage();
	}
	function totalPrice(){
	 	var startTime = $("#timeMin").val();
		var endTime	= $("#timeMax").val();
		var orderNo	= $("#orderNo").val();
	$.ajax({
	    url: ctx+'admin/orderQuery/findTotalPrice?startTime='+startTime+"&endTime="+endTime+"&orderNo="+orderNo,
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