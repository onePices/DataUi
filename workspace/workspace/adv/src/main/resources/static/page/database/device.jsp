<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no" />
  <title><spring:message code="sdkInterface.operation.selectDevice" /></title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no"/>
  <meta name="keywords" content="">
  <meta name="description" content="">
  <meta name="author" content="">
  <meta name="format-detection" content="telephone=no" />
  <meta name="format-detection" content="email=no" />
  <meta name="format-detection" content="address=no">
  <!-- 启用360浏览器的极速模式(webkit) -->
  <meta name="renderer" content="webkit">
  <!-- 避免IE使用兼容模式 -->
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <!--[if lt IE 9]>
    <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->

  <!-- bootstrap -->
  <link href="./css/bootstrap/bootstrap.min.css" rel="stylesheet" />
  <!-- bootstrap-table组件引用-->
  <link href="./css/bootstrap/bootstrap-table.css" rel="stylesheet" />
  <!-- base  page  CSS -->
  <!-- <link href="./css/base.css" rel="stylesheet" /> -->
  <link href="./css/page/inside.css" rel="stylesheet" />
  <link href="./css/page/customer.css" rel="stylesheet" />
  <style type="text/css">	
	  .content{
		  z-index: 0;
		  width: auto;
		  margin-top: 10px;
		  margin-left: 0px;
		  -webkit-transition: margin-left .3s ease;
		  -moz-transition: margin-left .3s ease;
		  -o-transition: margin-left .3s ease;
		  -ms-transition: margin-left .3s ease;
		  transition: margin-left .3s ease;
	   }
	  .form-control {
		width: 100%;
		height: 34px;
		padding: 6px 12px;
		background-color: #fff;
		border: 1px solid #ccc;
		border-radius: 4px;
		-webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
		box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
		-webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
		-o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
		transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
	  }
      .table-list .tab-btn-item {
         position: relative;
         margin: 5px 4px;
      }
	  .left {
	     float: left;
	   } 	  
	   *, :after, :before {
	     box-sizing: border-box;
	  }
	  .breadcrumb>li, .pagination {
          display: inline-block;
       }
     .pull-right {
         float: right!important;
     }
	</style>
</head>
<body>
<div class="sdk">
   <div class="content">          
      <div class="container-fluid">
        <div class="set">
          <ol class="breadcrumb">
            <li class="active"><h4><spring:message code="sdkInterface.deviceInformation.title" />: </h4></li>
          </ol>
        </div>
        <div class="table-list">
          <div class="tab-btn clearfix">
            <div class="tab-btn-item left">
              <input type="text" class="form-control" placeholder="<spring:message code='sdkInterface.deviceHeartbeat.devCode'/>/<spring:message code='sdkInterface.deviceHeartbeat.devMac'/>" id="devCodeOrMac" onkeyup="value=keyUpValue(this)" onpaste="value=keyUpValue(this)" oncontextmenu="value=keyUpValue(this)">
            </div>
            <div class="tab-btn-item left">
              <button type="button" class="btn btn-default left" id="devsearch-btn"><i class="fa fa-search fa-lg"></i><spring:message code="common.search" /></button>
            </div>
            <div class="tab-btn-item left">
              <button type="button" class="btn btn-default left" id="devback-btn"><i class="fa fa-search fa-lg"></i><spring:message code="common.back" /></button>
            </div>
          </div>
          <div class="table-info-msgList">
            <table id="devtable"></table>
          </div>
        </div>
		<div class="set">
          <ol class="breadcrumb">
            <li class="active"><h4><spring:message code="sdkInterface.deviceInformation.mapTitle" />: </h4></li>
          </ol>
        </div>
		<div class="table-list">
          <div class="tab-btn clearfix">
            <div class="tab-btn-item left">
              <input type="text" class="form-control" placeholder="<spring:message code='sdkInterface.deviceHeartbeat.devCode'/>" id="devCode" onkeyup="value=keyUpValue(this)" onpaste="value=keyUpValue(this)" oncontextmenu="value=keyUpValue(this)">
            </div>
            <div class="tab-btn-item left">
              <button type="button" class="btn btn-default left" id="mapsearch-btn"><i class="fa fa-search fa-lg"></i><spring:message code="common.search" /></button>
            </div>
            <div class="tab-btn-item left">
              <button type="button" class="btn btn-default left" id="mapback-btn"><i class="fa fa-search fa-lg"></i><spring:message code="common.back" /></button>
            </div>
          </div>
          <div class="table-info-msgList">
            <table id="maptable"></table>
          </div>
        </div>
      </div>
    </div>
</div>
  <script src="./js/jquery.min.js"></script>
  <script src="./js/moment/moment.js"></script>
  <script src="./js/bootstrap/bootstrap.min.js"></script>
  <script src="./js/bootstrap-table/bootstrap-table.js"></script>
  <script src="./js/bootstrap-table/bootstrap-table-<spring:message code="page.language"/>-CN.js"></script>
  <script src="./js/jedate/jquery.jedate.js"></script>
  <script src="./js/base.js"></script>
  
  <script>
    var devtableParam = {
      url: 'sdkNew/getDeviceList',
      columns: [{                 
        field: 'devId',
        title: '<spring:message code="sdkInterface.deviceInformation.devId"/>', 
        sortable:true
      },{
        field: 'devCode',
        title: '<spring:message code="sdkInterface.deviceInformation.deviceCode"/>',
        sortable:true
      },{
        field: 'devMac',
        title: '<spring:message code="sdkInterface.deviceInformation.deviceMac"/>',
        sortable:true
      },{
        field: 'lastOnlineDate',
        title: '<spring:message code="sdkInterface.deviceInformation.lastOnlineDate"/>',
        sortable:true,
        formatter: formatterDate
      }],
      queryParams: function (params) {
        var param = {
          pageNo: params.offset/params.limit+1,
          pageSize: params.limit,
          devCodeOrMac: $('#devCodeOrMac').val().trim()
        };
        return param;
      }
	}
	
	var maptableParam = {
      url: 'sdkNew/getMapDevList',
      columns: [{                 
        field: 'devMapId',
        title: '<spring:message code="sdkInterface.deviceInformation.devMapId"/>', 
        sortable:true
      },{
        field: 'devCode',
        title: '<spring:message code="sdkInterface.deviceInformation.deviceCode"/>',
        sortable:true
      },{
        field: 'imageName',
        title: '<spring:message code="sdkInterface.deviceInformation.imgName"/>',
        sortable:true
      },{
        field: 'imageUrl',
        title: '<spring:message code="sdkInterface.deviceInformation.imgUrl"/>',
        sortable:true
      },{
        field: 'createDate',
        title: '<spring:message code="sdkInterface.deviceInformation.bindingTime"/>',
        sortable:true,
        formatter: formatterDate
      },{
        field: 'updateDate',
        title: '<spring:message code="sdkInterface.deviceInformation.updateDate"/>',
        sortable:true,
        formatter: formatterDate
      }],
      queryParams: function (params) {
        var param = {
          pageNo: params.offset/params.limit+1,
          pageSize: params.limit,
          devCode: $('#devCode').val().trim()
        };
        return param;
      }
	}
	
    $(function(){	
      moment.locale('<spring:message code="page.language"/>');
      //加载数据
      init(devtableParam, '#devtable');
      init(maptableParam, '#maptable');
      //closePageLoadingMask();
      
      // 搜索
	  $('#devsearch-btn').on('click', function (){
        $('#devtable').bootstrapTable('refresh');
      });
      $('#mapsearch-btn').on('click', function (){
        $('#maptable').bootstrapTable('refresh');
      });
      $('#devback-btn').on('click', function (){
    	  history.go(-1);
      });
	  $('#mapback-btn').on('click', function (){
    	  history.go(-1);
      });
    });
  </script>
</body>
</html>

