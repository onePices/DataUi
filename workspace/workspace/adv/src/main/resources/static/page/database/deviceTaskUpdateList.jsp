<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no" />
  <title><spring:message code="sdkInterface.deviceTask.title" /></title>
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
            <li class="active"><h4><spring:message code="sdkInterface.deviceTask.title" />: </h4></li>
          </ol>
        </div>
        <div class="table-list">
          <div class="tab-btn clearfix">
            <div class="tab-btn-item left">
              <input type="text" class="form-control" placeholder="<spring:message code='sdkInterface.deviceHeartbeat.devCode'/>" id="devCode" onkeyup="value=keyUpValue(this)" onpaste="value=keyUpValue(this)" oncontextmenu="value=keyUpValue(this)">
            </div>
            <div class="tab-btn-item left">
              <button type="button" class="btn btn-default left" id="search-btn"><i class="fa fa-search fa-lg"></i><spring:message code="common.search" /></button>
            </div>
            <div class="tab-btn-item left">
              <button type="button" class="btn btn-default left" id="back-btn"><i class="fa fa-search fa-lg"></i><spring:message code="common.back" /></button>
            </div>
          </div>
          <div class="table-info-msgList">
            <table id="table"></table>
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
    var tableParam = {
      url: 'sdkNew/getDeviceTaskUpdateList',
      columns: [{                 
        field: 'taskLogId',
        title: '<spring:message code="sdkInterface.deviceTask.taskId"/>', 
        sortable:true
      },{
        field: 'devCode',
        title: '<spring:message code="sdkInterface.deviceTask.devCode"/>',
        sortable:true
      },{
        field: 'taskType',
        title: '<spring:message code="sdkInterface.deviceTask.taskType"/>',
        sortable:true,
		formatter:function(value,row,index){
            if(value==10){
              return "<spring:message code='taskType.openScreen'/>";
            }else if(value==40){
              return "<spring:message code='taskType.shutDown'/>";
            }else if(value==50){
              return "<spring:message code='taskType.restart'/>";
            }else if(value==80){
              return "<spring:message code='taskType.clock'/>";
            }else if(value==90){
              return "<spring:message code='taskType.setVolume'/>";
            }else if(value==100||value==101||value==102){
              return "<spring:message code='taskType.setBrightness'/>";
            }else if(value==110){
              return "<spring:message code='taskType.screenshot'/>";
            }else if(value==120){
              return "<spring:message code='taskType.log'/>";
            }else if(value==60){
              return "<spring:message code='taskType.upgrade.firmware'/>";
            }else if(value==70){
              return "<spring:message code='taskType.upgrade.software'/>";
            }else if(value==140){
              return "<spring:message code='taskType.release.sendChannel'/>";
            }else if(value==150){
              return "<spring:message code='taskType.release.sendShow'/>";
            }else if(value==130){
              return "<spring:message code='taskType.setStrategy'/>";
            }else if(value==20){
              return "<spring:message code='taskType.standby'/>";
            }else if(value==180){
              return "<spring:message code='taskType.play'/>";
            }else if(value==190){
              return "<spring:message code='taskType.stop'/>";
            }else if(value==121){
              return "<spring:message code='taskType.download'/>";
            }else if(value==160){
              return "<spring:message code='taskType.release.insertShow'/>";
            }else if(value==170){
              return "<spring:message code='taskType.release.subtitles'/>";
            }else if(value==30){
              return "<spring:message code='taskType.device.start'/>";
            }else if(value==135){
              return "<spring:message code='taskType.fence.task'/>";
            }else if(value==125){
              return "<spring:message code='taskType.delete.channel'/>";
            }else if(value==126){
              return "<spring:message code='taskType.delete.program'/>";
            }else if(value==127){
              return "<spring:message code='taskType.update.device.program'/>";
            }else if(value==128){
              return "<spring:message code='taskType.delete.region'/>";
            }else if(value==129){
              return "<spring:message code='taskType.update.device.fence'/>";
            }else if(value==65){
              return "<spring:message code='taskType.upgrade.model'/>";
            }else if(value==400){
              return "<spring:message code='taskType.clear.allFile'/>";
            }else if(value==210){
              return "<spring:message code='taskType.card.parameter'/>";
            }else if(value==230){
              return "<spring:message code='taskType.upgrade.systemUp'/>";
            }else if(value==240){
              return "<spring:message code='taskType.update.config'/>";
            }else if(value==260){
              return "<spring:message code='taskType.gps.button'/>";
            }else if(value==801){
              return "<spring:message code='taskType.signal.switching'/>";
			}else if(value==804){
	          return "<spring:message code='taskType.signal.LocalScreen'/>";
			}else if(value==810){
	          return "<spring:message code='taskType.signal.brightness'/>";
			}else if(value==802){
			  return "<spring:message code='taskType.signal.freezes'/>";
			}else if(value==803){
			  return "<spring:message code='taskType.signal.Black'/>";
			}else if(value==814){
		      return "<spring:message code='taskType.signal.pip'/>";
			}else if(value==845){
			  return "<spring:message code='taskType.signal.scenario'/>";
			}else if(value==277){
			  return "<spring:message code='taskType.signal.signalBack'/>";
			}else if (value == 265) {
              return "<spring:message code='taskType.program.on'/>";
            }else if (value == 266) {
              return "<spring:message code='taskType.program.off'/>";
            }else if(value == 280){
              return "<spring:message code='taskType.logInfo.onoff'/>";
            }else if(value == 285){
              return "<spring:message code='taskType.voice'/>";
            }else if(value == 829){
              return "<spring:message code='taskType.localChange' />";
            }else if(value == 255){
              return "<spring:message code='taskType.release.ChannelOndemand'/>";
            }else if(value == 288){
              return "<spring:message code='taskType.language.switch' />";
            }else if(value == 289){
              return "<spring:message code='taskType.card.lightManager' />";
            }else if(value == 290){
              return "<spring:message code='taskType.card.powerManager' />";
            }else if(value == 291){
              return "<spring:message code='taskType.gps.sync' />";
            }else if(value == 292){
              return "<spring:message code='taskType.devcie.sync' />";
            }else if(value == 293){
               return "<spring:message code='taskType.c2m.capture' />";
            }else if(value == 11){
               return "<spring:message code='taskType.c2m.timingSwitchPanel' />";
            }else if(value == 81){
               return "<spring:message code='taskType.c2m.atomicTime' />";
            }else if(value == 305){
               return "<spring:message code='taskType.video.Surveil' />";
            }else if(value == 330){
               return "<spring:message code='taskType.play.timeSwitch' />";
            }else if(value == 666){
               return "<spring:message code='taskType.play.NtpSyncSwitch' />";
            }else if(value == 75){
               return "<spring:message code='taskType.upgrade.logoUp' />";
            }else if(value == 315){
               return "<spring:message code='taskType.setAP.wifipassword' />";
            }else if(value == 278){
      		   return "<spring:message code='taskType.timing.signalSource' />";
      		}else if(value == 251){
              return "<spring:message code='taskType.same.asy.switch' />";
            } else if(value == 505){
              return "<spring:message code='taskType.release.updateData' />";
            } else if (value == 806) {
               return "<spring:message code='taskType.device.apn.setting' />";
            } else if (value == 807) {
               return "<spring:message code='taskType.device.apn.default' />";
            } else if (value == 808) {
               return "<spring:message code='taskType.device.apn.delete' />";
            } else if (value == 809) {
               return "<spring:message code='taskType.device.apn.saveOrUpdate' />";
            } else if (value == 811) {
               return "<spring:message code='taskType.device.time.setting' />";
            }}
	  },{
        field: 'devTaskStatusDis',
        title: '<spring:message code="sdkInterface.deviceTask.processingState" />',
        sortable:true
      },{
        field: 'taskPercent',
        title: '<spring:message code="sdkInterface.deviceTask.downloadProgress"/>',
        sortable:true
      },{
        field: 'taskDownloadRate',
        title: '<spring:message code="sdkInterface.deviceTask.downloadRate"/>',
        sortable:true
      },{
        field: 'updateBy',
        title: '<spring:message code="sdkInterface.deviceTask.updateBy"/>',
        sortable:true
      },{
        field: 'updateDate',
        title: '<spring:message code="sdkInterface.deviceTask.updateDate"/>',
        sortable:true,
		formatter: formatterDate
      },{
        field: 'taskRemarks',
        title: '<spring:message code="sdkInterface.deviceTask.taskDescribe"/>',
        sortable:true
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
      init(tableParam, '#table');
      //closePageLoadingMask();
      // 搜索
      $('#search-btn').on('click', function (){
        $('#table').bootstrapTable('refresh');
      });
      $('#back-btn').on('click', function (){
    	  history.go(-1);
        });
    });
  </script>
</body>
</html>










