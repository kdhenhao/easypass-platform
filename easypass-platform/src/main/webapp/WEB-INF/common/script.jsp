<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- jQuery -->
<script src="${ctx}resources/vendors/jquery/dist/jquery.min.js"></script>
<!-- Bootstrap -->
<script src="${ctx}resources/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- Bootstrap validator-->
<script src="${ctx}resources/vendors/bootstrap/dist/js/bootstrapValidator.js"></script>
<script src="${ctx}resources/vendors/bootstrap/dist/js/bootstrapValidator.min.js"></script>
<!-- FastClick -->
<script src="${ctx}resources/vendors/fastclick/lib/fastclick.js"></script>
<!-- NProgress -->
<script src="${ctx}resources/vendors/nprogress/nprogress.js"></script>
<!-- bootstrap-progressbar -->
<script src="${ctx}resources/vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
<!-- iCheck -->
<script src="${ctx}resources/vendors/iCheck/icheck.min.js"></script>
<!--ajaxfileupload -->
<script type="text/javascript" src="${ctx}resources/js/ajaxfileupload.js"></script>
<!-- sweetalert -->
<script src="${ctx}resources/vendors/sweetalert/sweetalert.min.js"></script>
<!-- Datatables -->
<script src="${ctx}resources/vendors/datatables.net/js/jquery.dataTables.min.js"></script>
<script src="${ctx}resources/vendors/datatables.net-bs/js/dataTables.bootstrap.min.js"></script>
<script src="${ctx}resources/vendors/datatables.net-buttons/js/dataTables.buttons.min.js"></script>
<script src="${ctx}resources/vendors/datatables.net-buttons-bs/js/buttons.bootstrap.min.js"></script>
<script src="${ctx}resources/vendors/datatables.net-buttons/js/buttons.flash.min.js"></script>
<script src="${ctx}resources/vendors/datatables.net-buttons/js/buttons.html5.min.js"></script>
<script src="${ctx}resources/vendors/datatables.net-buttons/js/buttons.print.min.js"></script>
<script src="${ctx}resources/vendors/datatables.net-fixedheader/js/dataTables.fixedHeader.min.js"></script>
<script src="${ctx}resources/vendors/datatables.net-keytable/js/dataTables.keyTable.min.js"></script>
<script src="${ctx}resources/vendors/datatables.net-responsive/js/dataTables.responsive.min.js"></script>
<script src="${ctx}resources/vendors/datatables.net-responsive-bs/js/responsive.bootstrap.js"></script>
<script src="${ctx}resources/vendors/datatables.net-scroller/js/dataTables.scroller.min.js"></script>
<script src="${ctx}resources/vendors/datatables.net-select/js/dataTables.select.min.js"></script>
<!-- validator -->
<script src="${ctx}resources/vendors/validator/validator.js"></script>
<!-- moment -->
<script src="${ctx}resources/vendors/moment/moment.min.js"></script>
<!-- datepicker -->
<script src="${ctx}resources/vendors/datepicker/daterangepicker.js"></script>
<!-- timepicker -->
<script src="${ctx}resources/vendors/bootstrap-timepicker/js/bootstrap-timepicker.min.js"></script>
<!-- tree -->
<script src="${ctx}resources/vendors/bootstrap-treeview/bootstrap-treeview.min.js"></script>
<!-- pjax -->
<script src="${ctx}resources/js/jquery.pjax.js"></script>
<!-- Custom Theme Scripts -->
<script src="${ctx}resources/js/custom.js"></script>
<script src="${ctx}resources/js/uploadPreview.js"></script>
<!-- 富文本 -->
<script src="${ctx}resources/vendors/summernote/summernote.min.js"></script>
<script src="${ctx}resources/vendors/summernote/lang/summernote-zh-CN.min.js"></script>
<!-- select2 -->
<script src="${ctx}resources/vendors/select2/js/select2.full.min.js"></script>
<script src="${ctx}resources/vendors/select2/js/i18n/zh-CN.js"></script>
<!-- 菜单溢出 -->
<script src="${ctx}resources/vendors/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.js"></script>
<script src="${ctx}resources/vendors/jQuery-Smart-Wizard/js/jquery.smartWizard.js"></script>

<script src="${ctx}resources/js/jquery.mCustomScrollbar.js"></script>

<!--<script src="${ctx}resources/js/bootstrap-fileinput/css/fileinput.min.css"></script>-->
<script src="${ctx}resources/js/bootstrap-fileinput/js/fileinput.min.js"></script>
<script src="${ctx}resources/js/bootstrap-fileinput/js/locales/zh.js"></script>


<link rel="stylesheet" href="${ctx}resources/js/editor/themes/default/default.css" />
<link rel="stylesheet" href="${ctx}resources/js/editor/plugins/code/prettify.css" />

<script src="${ctx}resources/js/editor/kindeditor.js"></script>
<script src="${ctx}resources/js/editor/lang/zh_CN.js"></script>

<script src="${ctx}resources/js/editor/plugins/code/prettify.js"></script>
    
<!--<script  src="${ctx}resources/js/datetimepicker/css/bootstrap-datetimepicker.min.css"></script>   -->

<link href="${ctx}resources/js/datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
 
<script  src="${ctx}resources/js/datetimepicker/js/boottimestrap-datepicker.js"></script>
<script  src="${ctx}resources/js/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>    
<!-- 相册-->
<link href="${ctx}resources/css/simple.slide.css" rel="stylesheet">
<script src="${ctx}resources/js/simple.slide.min.js"></script> 
<!-- 播放视频 -->
<link href="${ctx}resources/css/video-js.css" rel="stylesheet">
<script src="${ctx}resources/js/video.js"></script> 



<script>
  var ctx = "${ctx}";
  var domain = "${ctx}";
  var imgDomain = "${imgDomain}";
  $(function(){
	  //全局ajax异常处理
	  $.ajaxSetup({
		  type: "POST",
		  error: globalErrorFun
	  });
	  //swal设置
	  swal.setDefaults({ confirmButtonClass: 'btn-success',animation:"false",confirmButtonText:"确定",cancelButtonText:"取消"});
	  // Disable search and ordering by default
	  $.extend( $.fn.dataTable.defaults, {
	      "searching": false,
	      "ordering":  false,
	      "serverSide": true,
	      "dom": "<'row'<'col-sm-12'tr>><'row'<'col-xs-12 col-sm-5'l><'col-xs-12 col-sm-2 center'i><'col-xs-12 col-sm-5'p>>",
	      "language" : {
	    	  "loadingRecords" : "正在加载，请稍候",
	    	  "processing" : "正在处理，请稍候",
              "lengthMenu" : "每页显示 _MENU_ 条记录",
              "emptyTable" : "没有查询到数据",
              "zeroRecords" : "没有查询到数据",
              "info" : "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
              "infoEmpty" : "没有数据",
              "infoFiltered": "(从 _MAX_ 条数据中检索)",
              "zeroRecords" : "没有检索到数据",
               "search" : "名称:",
              "paginate" : {
              "first" : "首页",
              "previous" : "前一页",
              "next" : "后一页",
              "last" : "尾页"
              }
	      }
	  });
	  //表单验证拓展
	  $.extend(validator.message,{
			    invalid         : '不正确的输入',
			    empty           : '此处不能为空',
			    min             : '输入长度太短',
			    max             : '输入长度太长',
			    number_min      : '数字太小',
			    number_max      : '数字太大',
			    url             : '不正确的url地址',
			    number          : '不正确的数字',
			    email           : '不正确的邮件',
			    email_repeat    : 'emails do not match',
			    password_repeat : '两次密码输入不一致',
			    no_match        : '不匹配',
			    complete        : '输入长度错误',
			    select          : '请选择'
			});
      // validate a field on "blur" event, a 'select' on 'change' event & a '.reuired' classed multifield on 'keyup':
      $('form')
        .on('blur', 'input.required, select.required', validator.checkField)
        .on('change', 'select.required', validator.checkField)
        .on('keypress', 'input[required][pattern]', validator.keypress);
      $('.multi.required').on('keyup blur', 'input', function() {
        validator.checkField.apply($(this).siblings().last()[0]);
      });
      //密码框居中
      //forceCenter("#passwordModal");
      //保持菜单不变，
      //$(document).pjax('.pjaxload', '#main');
      //$(document).on('pjax:send', function() {
    //	  alert($("#main").html());
      //});
  });
  function globalErrorFun(){
	  swal.close();
  }
  var datePickerOpt = {
  	  "drops":"up",
      "singleDatePicker": true,
      "calender_style": "picker_2",
      "format": "YYYY-MM-DD",
      "showDropdowns":true,
      "locale": {
          "daysOfWeek": [
              "日",
              "一",
              "二",
              "三",
              "四",
              "五",
              "六"
          ],
          "monthNames": [
              "一月",
              "二月",
              "三月",
              "四月",
              "五月",
              "六月",
              "七月",
              "八月",
              "九月",
              "十月",
              "十一月",
              "十二月"
          ],
          "firstDay": 1
      }
   };
  /**
   * 将form表单中带name属性的dom节点系列化成object xz
   */
  function serializeObject(form) {
  	var o = {};
  	$.each(form.serializeArray(), function(index) {
  		if (o[this["name"]]) {
  			o[this["name"]] = o[this["name"]] + "," + this["value"];
  		} else {
  			o[this["name"]] = this["value"];
  		}
  	});
  	return o;
  };
  /*datatables创建 rowNumber */
  function rowNumberRender(data, type, row, meta) {
    var setting = meta.settings;
    var rowNumber = setting._iDisplayStart + (meta.row + 1);
	return rowNumber;
  }
</script>