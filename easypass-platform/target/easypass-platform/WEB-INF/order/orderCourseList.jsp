<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="/WEB-INF/common/head.jsp" %>
    <title>订单管理</title>
    <style>
        td.details-control {
            background: url('${ctx}/resources/images/details_open.png') no-repeat center center;
            cursor: pointer;
        }

        tr.shown td.details-control {
            background: url('${ctx}/resources/images/details_close.png') no-repeat center center;
        }

        .relative {
            position: relative;
        }
    </style>
</head>
<body class="${cookie.bodyClass.value}">
<div class="container body">
    <div class="main_container">
        <%@ include file="/WEB-INF/common/left.jsp" %>
        <%@ include file="/WEB-INF/common/top.jsp" %>
        <!-- page content -->
        <div class="right_col" role="main" id="main">
            <div class="x_panel">
                <div class="x_title">
                    <h2>订单管理</h2>
                    <div class="clearfix"></div>
                </div>
                <div class="x_content">
                    <form role="form" id="searchForm">
                        <div class="container">
                            <div class="row from-group">
                            	
                                <div class="col-sm-2">
                                   <!--  <input name="type" id="type" hidden="hidden" value="1"> -->
                                    <input name="map['orderNo']" id="orderNo" class="form-control input-sm" placeholder="订单号">
                                </div>
                                 <div class="col-sm-2">
                                    <input name="map['userName']" id="userName" class="form-control input-sm" placeholder="用户名">
                                </div>
                                <div class="col-sm-2">
                                    <select class="form-control input-sm" id="status" name="map['status']">
                                        <option value="">--请选择状态--</option>
                                        <option value="0">待支付</option>
                                        <option value="1">已支付</option>
<!--                                         <option value="2">待评价</option> -->
<!--                                         <option value="3">交易完成</option> -->
<!--                                         <option value="4">交易取消</option> -->
                                    </select>
                                </div>
                                <div class="col-sm-2">
                                    <input name="map['beginTime']" id="beginTime" class="form-control input-sm"
                                           placeholder="起始日期" readonly>
                                </div>
                                <div class="col-sm-2">
                                    <input name="map['endTime']" id="endTime" class="form-control input-sm" placeholder="截止日期" readonly>
                                </div>
                                
                                <div class="col-sm-2" >
                                    <button type="button" class="btn btn-primary btn-sm" onclick="search();">搜索</button>
                                </div>
                            </div>
                        </div>
                    </form>
                    <div class="table-responsive">
                        <table id="orderCourseTable" class="table table-bordered table-striped table-hover bulk_action"
                               cellspacing="0" width="100%">
                            <thead>
                            <tr class="myTableHead">
                           	 	<th></th>
                                <th class="myRowNumber">编号</th>
                                <th>订单号</th>
                                <th>用户名</th>
                                <th>邮箱</th>
                                <th>订单价格</th>
                                <th>联系方式</th>
                                <th>订单状态</th>
                                <th>下单日期</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!-- /page content -->
        <%@ include file="/WEB-INF/common/foot.jsp" %>
    </div>
</div>


<!-- 修改订单状态模态框（Modal） -->
<div class="modal fade" id="modifyOrderStatusModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="modifyOrderStatusModalTitle">
                    修改订单状态
                </h4>
            </div>
            <div class="modal-body">
                <form role="form" novalidate id="modifyOrderStatusForm" class="form-horizontal">
                    <div class="form-group item">
                        <label class="col-sm-3 control-label"> <span style="color:red;">*</span>修改后状态:</label>
                        <div class="col-sm-6">
                        	<input hidden="hidden" name="orderId" id="orderId">
                             <select class="form-control input-sm" id="orderStatus" name="orderStatus">
                                  <option value="">--请选择状态--</option>
                                  <option value="0">待支付</option>
                                  <option value="1">已支付</option>
<!--                                   <option value="2">待评价</option> -->
<!--                                   <option value="3">交易完成</option> -->
<!--                                   <option value="4">交易取消</option> -->
                              </select>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-info">
                    提交
                </button>
            </div>
        </div>/.modal-content
    </div>/.modal
</div>

<%@ include file="/WEB-INF/common/script.jsp" %>
<script>
    var curGoodsIndex;
    var curRow;

    $(function () {
        var orderCourseTable = $("#orderCourseTable").DataTable({
            ajax: {
                url: ctx + 'admin/orderCourse/findOrderCourseList?time=' + new Date(),
                type: 'POST',
                data: function (d) {
                    $.extend(d, serializeObject($("#searchForm")))
                }
            },
            columns: [
            	{
                    "className": 'details-control',
                    "orderable": false,
                    "data": null,
                    "defaultContent": ''
                },
                { "data":null,"render":rowNumberRender},
                {"data": 'orderNo'},
                {"data": 'userName'},
                {"data": 'email'},
                {"data": 'price'},
                {"data": 'phone'},
                {"data": 'orderStatus', "render": statusRender},
                {"data": 'createTime'},
                {"data": 'id', "render": optRender}
            ]
        });

        /* Formatting function for row details - modify as you need */
        function format(d) {
            var head = '<thead><tr class="mytableHead">' +
                    '<th>课程名称</th>' +
                    '<th>课时名称</th>' +
                    '<th>价格</th>' +
                    '<th>数量</th>' +
                    '</tr></thead>';
            var table = '';
            table = '<table id="product'+ curRow.id + '" class="table table-bordered table-striped table-hover"' +
                    'cellspacing="0" width="100%">' + head + '</table>';
            // `d` is the original data object for the row
            var panel = '<div class="panel panel-default">' +
                    '<div class="panel-heading">' +
                    '<h6 class="panel-title">订单项</h6>' +
                    '</div>' +
                    '<div class="panel-body">'
                    + table + '</div></div>';
            return panel;
        }

        // Add event listener for opening and closing details
        $('#orderCourseTable tbody').on('click', 'td.details-control', function () {
            var tr = $(this).closest('tr');
            var row = orderCourseTable.row(tr);
            curGoodsIndex = row.index();
            curRow = row.data();
            if (row.child.isShown()) {
                // This row is already open - close it
                row.child.hide();
                tr.removeClass('shown');
            }
            else {
                for (var m = 0; m < $('#orderCourseTable tr').length; m++) {
                    var tempRow = orderCourseTable.row($('#orderCourseTable tr:eq(' + m + ')'));
                    if (tempRow.child.isShown()) {
                        // This row is already open - close it
                        tempRow.child.hide();
                        $('#orderCourseTable tr:eq(' + m + ')').removeClass('shown');
                    }
                }
                // Open this row
                var rowData = row.data();
                row.child(format(rowData)).show();
                tr.addClass('shown');
                var columnModel = [
                    {"data": 'courseName'},
                    {"data": 'classHourName'},
                    {"data": 'price'},
                    {"data": 'quantity'}
                ];
                var productTable = $("#product"+ curRow.id).DataTable({
                    ajax: {
                        url: ctx + 'admin/orderCourse/findOrderCourseDetail?time=' + new Date() + '&orderNo=' + rowData.orderNo,
                        type: 'GET'
                    },
                    columns: columnModel
                });
            }
        });

        $("#searchForm input").keydown(function (event) {
            if (event.keyCode == 13) {
                search();
            }
        });
        $("#searchForm select").change(search);

        $('#beginTime').daterangepicker(datePickerOpt);
        $('#endTime').daterangepicker(datePickerOpt);
        
        $('#modifyOrderStatusModal button:last').click(function () {
            submitModifyOrderStatus();
        });

    });

  //提交修改订单状态
    function submitModifyOrderStatus() {
        if ($('#orderStatus').val()==''){
            swal({title:'',text:'请选择状态!',type:'error'});
            return false;
        }
        //var text = "确定将订单金额由" + $('#curOrderMoney').val() + '修改为' + parseFloat($('#modifiedOrderMoney').val()).toFixed(2) + '吗?';
        swal({
                    title: "修改订单状态",
                    text: '',
                    type: "warning",
                    showCancelButton: true,
                    closeOnConfirm: false,
                    showLoaderOnConfirm: true
                },
                function () {
                    $.ajax({
                        url: ctx + 'admin/orderCourse/updateStatus?time=' + new Date(),
                        type: 'POST',
                        dataType: 'json',
                        data: $('#modifyOrderStatusForm').serialize(),
                        success: function (result) {
                            if (result.status == 0) {
                                swal({title: '', text: result.msg, type: 'success'});
                                searchCurrentPage();
                                $('#modifyOrderStatusModal').modal('hide');
                            } else {
                                swal({title: '', text: result.msg, type: 'error'});
                            }
                        },
                        error: function (error) {
                            swal({title: '', text: error.statusText, type: 'error'});
                        }
                    })
                });
    }
  
    function statusRender(data, type, row, meta) {
        if (row.orderStatus == 0)
            return '待支付';
        if (row.orderStatus == 1)
            return '已支付';
//         if (row.orderStatus == 2)
//             return '待评价';
//         if (row.orderStatus == 3)
//             return '交易完成';
//         if (row.orderStatus == 4)
//             return '交易取消';
    }

    function dateRender(data, type, row, meta) {
        var time = new Date(row.createTime);
        return time.format("yyyy-MM-dd hh:mm:ss");
    }

    function optRender(data, type, row, meta) {
        var but = '';
        but = but + '<a class="btn btn-info btn-xs" href="javascript:modifyOrderStatus(' + meta.row + ')"><i class="fa fa-download"></i>修改状态</a>';
        //if (row.orderStatus == 3||row.orderStatus == 4) {
            but = but + '<a class="btn btn-danger btn-xs" href="javascript:deleteOrder(' + row.id + ',' + row.orderNo + ')"><i class="fa fa fa-trash-o"></i> 删除订单</a>';
        //}
          if (row.status == 1) {
            but = but + '<a class="btn btn-success btn-xs" href="javascript:updateFileStatus(' + row.id + ',0)"><i class="fa fa fa-trash-o"></i>资料已寄出</a>';
        }else{
        	 but = but + '<a class="btn btn-warning btn-xs" href="javascript:updateFileStatus(' + row.id + ',1)"><i class="fa fa fa-trash-o"></i>资料未寄出</a>';
        }
        
        return but;
    }
    
  	//打开订单状态
    function modifyOrderStatus(index) {
        $('#modifyOrderStatusForm')[0].reset();
        var row = $('#orderCourseTable').DataTable().rows().data()[index];
        $('#orderId').val(row.id);
        $('#orderStatus').val(row.orderStatus);
        $('#modifyOrderStatusModal').modal('show');
    }
  	
    function updateFileStatus(orderId,status) {
        swal({
               title: "修改资料状态",
               text: "确定修改资料状态?",
               type: "warning",
               showCancelButton: true,
               closeOnConfirm: false,
               showLoaderOnConfirm: true
           },
           function () {
               $.ajax({
                   url: ctx + 'admin/orderCourse/updateFileStatus',
                   dataType: 'json',
                   type:"POST",
					data:{
						orderId:orderId,
						status:status
					},
                   success: function (result) {
                       if (result.status == 0) {
                           swal({title: '', text: result.msg, type: 'success'});
                           search();
                       } else {
                           swal({title: '', text: result.msg, type: 'error'});
                       }
                   }
               })
           });
    }
    

    function deleteOrder(orderId,orderNo) {
        swal({
               title: "删除订单",
               text: "确定删除订单该订单?",
               type: "warning",
               showCancelButton: true,
               closeOnConfirm: false,
               showLoaderOnConfirm: true
           },
           function () {
               $.ajax({
                   url: ctx + 'admin/orderCourse/deleteOrderById',
                   dataType: 'json',
                   type:"POST",
					data:{
						orderId:orderId,
						orderNo:orderNo
					},
                   success: function (result) {
                       if (result.status == 0) {
                           swal({title: '', text: result.msg, type: 'success'});
                           search();
                       } else {
                           swal({title: '', text: result.msg, type: 'error'});
                       }
                   }
               })
           });
    }
    
    function search() {
        $('#orderCourseTable').DataTable().ajax.reload(function (d) {
            $.extend(d, serializeObject($("#searchForm")))
        }, true);
    }
    function searchCurrentPage() {
        $('#orderCourseTable').DataTable().ajax.reload(function (d) {
            $.extend(d, serializeObject($("#searchForm")))
        }, false);
    }
</script>
</body>
</html>