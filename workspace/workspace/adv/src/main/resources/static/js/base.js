// 异步
function ajaxCallback(method, url, params, callback){

	showLoading();

	$.ajax({
        cache: true,
        type: method,
        url:url,
        data:params,
        success: function(data) { 
          
        	if(data.flag === -1) {
        		layerOutAutoHid(data.msg);
        		window.location.href = '/ListenSdkService';
        	}else{
        		callback(data);
        	}
        },
        error: function(request) {
        	alert("请求失败！");
        },
  	    complete: function (XHR, TS) {

  	      hideLoading();
  	      
  	      XHR = null;

  	    }
    });

}
// 同步
function baseAjax(method,url,params){

	var ajaxGisBase='';

	$.ajax({
        cache: true,
        type: method,
        url:url,
        data:params,
        async: false,
        error: function(request) {
        	layerOutAutoHid("请求失败！");
        },
        success: function(data) { 
        	if(data.flag === -1) {
        		layerOutAutoHid(data.msg);
        		window.location.href = '/ListenSdkService';
        	}else{
        		ajaxGisBase = data; 
        	}
        }
    });
	
	return ajaxGisBase;
}

function layerOutAutoHid(info){
	
  	$('#layerOutAuto').remove();
  	$('.backdrop').remove();
	//移除页面有的自动弹出窗口
    var shtml =   '<div class="layerAuto fadeIn" id="layerOutAuto">'+
                    '<div class="info"><div class="close">X</div>'+
                      '<p class="cont"  style="color:red;">'+ info +'</p>'+
                   ' </div>'+
                '<div class="backdrop in"></div></div>'+
              '';

    $('body').append(shtml);
    $(".backdrop,.close").off().on('click',function(){
        $('#layerOutAuto').remove();
        $('.backdrop').remove();
    });

    //2秒隐藏 移除
    setTimeout(function(){
        $('#layerOutAuto').remove();
        $('.backdrop').remove();
    },2000);

}

// DES加密
function encryptByDES(message, key) {
  var keyHex = CryptoJS.enc.Utf8.parse(key);
  var encrypted = CryptoJS.DES.encrypt(message, keyHex, {
      mode : CryptoJS.mode.ECB,
      padding : CryptoJS.pad.Pkcs7
  });
  return encrypted.toString();
}

// 显示加载图标
function showLoading(){

    var shtml = '<div class="loadWrap" id="loading">' +
                  '<div class="loader-in">'+
                  '<div class="outer"></div>'+
                  '<div class="inner"></div>'+
                    '<div class="text">loading...</div>'+
                  '</div>'+
                '</div>';
    $("body").append(shtml);

}
// 显示加载图标
function hideLoading(){
    $('#loading').remove();
}

//校验表单
function validatorForm(obj,subBtn,field,successCallBack){
  $(obj).bootstrapValidator({
    container: 'popover',
    submitButtons: subBtn,
    feedbackIcons: {
      valid: 'glyphicon glyphicon-ok',
      invalid: 'glyphicon glyphicon-remove',
      validating: 'glyphicon glyphicon-refresh'
    },
    fields: field
  }).on('success.form.bv', successCallBack);
   
}
//
function keyUpValue(obj){
	return obj.value.replace(/[^\a-\z\A-\Z\-0-9\u4E00-\u9FA5\.\s]/g,'');
}
/**
 * 日期格式化 yyyy-MM-dd HH:mm:ss
 * 
 * @param {*}
 *            value
 * @param {*}
 *            row
 * @param {*}
 *            index
 */
function formatterDate (value, row, index){
  if(value){
    return GetDateTimeformat(value, 'YYYY-MM-DD HH:mm:ss');
  }else{
    return ''
  }
}

function GetDateTimeformat(time, format){
    if(format === 'YYYY-MM-DD HH:mm:ss'){
       format = 'llll';
    }else if(format === 'YYYY-MM-DD'){
       format = 'l';
    }
    return moment(time).format(format);
} 
/**
 * session超时检验
 */
function checkSessionDisplay(res){
	if(res.flag==-1){
    	layerOut(res.msg,function(){
            location.href="/ListenSdkService";
        });
    }
}
function init(param, Obj){
	  var paramDefault = {
	    sidePagination: 'server',                  // 服务器分页
	    method: 'get',                             // 请求方式
	    paginationLoop: false,
	    dataType: 'json',                          // 数据格式
	    pageNumber: 1,                             // table初始化时显示的页数
	    showHeader: true,
	    uniqueId: 'id',
	    openFixedHeight: false,   
	    dataField: 'rows',
	    url: '',                                  // 请求地址
	    data: '',
	    pagination: true,                         // 是否启用分页
	    pageList: [10,30,60,100],
	    pageSize: 10,                             // 每页的行数
	    striped: true,                           // 隔行变色
	    sortOrder: 'asc',
	    sortable: true,                           // 是否启用排序
	    sortName: '',
	    singleSelect: false,                      // 单选
	    silent: true,                             // 必须设置刷新事件
	    clickToSelect: true,                      // 点击行自动选择rediobox 和 checkbox
	    columns: [],                              // 表头数据
	    detailView: false,
	    detailFormatter: function (index, row){
	    },
	    responseHandler: function(res){          // 在ajax获取到数据，渲染表格之前，修改数据源
	      checkSessionDisplay(res);
	      return res.value; 
	    },      
	    queryParams: function (params) {
	      var param = {
	        pageNo: params.offset/params.limit+1,
	        pageSize: params.limit
	      };
	      return param;
	    },                                                   // 这里是在ajax发送请求的时候设置一些参数
	    onEditableSave: function (){
	    },
	    onDblClickRow: function (){},             // 双击行选择回调
	    onClickRow: function (){}                // 单击行选择回调
	  }
	  param = $.extend({}, paramDefault, param);
	 
	  if(!$(Obj).data('bootstrap.table')) {
	    $(Obj).bootstrapTable(param);
	  }else{
	    $(Obj).bootstrapTable('refresh')
	  }
}
/**
 * 页数跳转
 * 
 * @param {跳转按钮}
 *            objVal
 * @param {boostrapTable}
 *            obj
 */
function selectPage(objInp, obj){
  var pageNum = $(objInp).parent().find(".boostrapTablepageNum").val();
  if (pageNum) {
    $(obj).bootstrapTable('selectPage', parseInt(pageNum));
  }
}

$(function(){
  try{
    moment.locale(LOCALLANG);
  }catch(e){
  }
});
