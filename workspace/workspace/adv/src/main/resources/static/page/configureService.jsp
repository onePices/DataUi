<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="setting.title" /></title>
<link href="./css/bootstrap/bootstrap.min.css" rel="stylesheet" />
<link href="./css/inside.css" rel="stylesheet" />
</head>
<body>
    <div class="wrap">
	    <div class="set">
          <ol class="breadcrumb">
            <li class="active"><h4><spring:message code="setting.title" />: </h4></li>
          </ol>
        </div>
        <div class="container">
        	<div>
	        	<form>
	        		<div class="row">
	        			<div class="col-md-5">
						  	<div class="form-group">
			        			<label class="control-label"><spring:message code="setting.defaultSourcePath" /></label>
			        			<input type="text" class="form-control" id="defaultSourcePath" name="defaultSourcePath" placeholder="<spring:message code='setting.defaultSourcePath' />">
						  	</div>
	        			</div>
	        			<div class="col-md-5">
						  	<div class="form-group">
			        			<label class="control-label"><spring:message code="setting.defaultSendTaskUrl" /></label>
			        			<input type="text" class="form-control" id="defaultSendTaskUrl" name="defaultSendTaskUrl" placeholder="<spring:message code='setting.defaultSendTaskUrl' />">
						  	</div>
	        			</div>
	        		</div>
	        		<div class="row">
	        			<div class="col-md-5">
						  	<div class="form-group">
			        			<label class="control-label"><spring:message code="setting.defaultHeartbeatSwitch" /></label>
					     		<select class="form-control" id="defaultHeartbeatSwitch">
						          	<option value="1"><spring:message code="common.open" /></option>
							      	<option value="0"><spring:message code="common.shut" /></option>
						     	</select>
						  	</div>
	        			</div>
	        			<div class="col-md-5">
						  	<div class="form-group">
			        			<label class="control-label"><spring:message code="setting.defaultUpdatetaskSwitch" /></label>
						     	<select class="form-control" id="defaultUpdatetaskSwitch">
					          		<option value="1"><spring:message code="common.open" /></option>
							      	<option value="0"><spring:message code="common.shut" /></option>
						     	</select>
						  	</div>
	        			</div>
	        		</div>
	        		<div class="row">
	        			<div class="col-md-5">
						  	<div class="form-group">
			        			<label class="control-label"><spring:message code="setting.defaultStoragePosition" /></label>
						     	<select class="form-control" id="defaultStoragePosition">
						          	<option value="1">redis</option>
							      	<option value="2"><spring:message code="setting.defaultStoragePosition.database" /></option>
						     	</select>
						  	</div>
	        			</div>
	        			<div class="col-md-5">
						  	<div class="form-group">
			        			<label class="control-label"><spring:message code="setting.defaultHeartbeatSaveUrl" /></label>
						     	<input type="text" class="form-control" id="defaultHeartbeatSaveUrl" name="defaultHeartbeatSaveUrl" placeholder="<spring:message code='setting.defaultHeartbeatSaveUrl' />">
						  	</div>
	        			</div>
	        		</div>
	        		<div class="row">
	        			<div class="col-md-5">
						  	<div class="form-group">
			        			<label class="control-label"><spring:message code="setting.defaultTaskSaveUrl" /></label>
						     	<input type="text" class="form-control" id="defaultTaskSaveUrl" name="defaultTaskSaveUrl" placeholder="<spring:message code='setting.defaultTaskSaveUrl' />">
						  	</div>
	        			</div>
	        			<div class="col-md-5">
						  	<div class="form-group">
			        			<label class="control-label"><spring:message code="setting.defaultAlarmSaveUrl" /></label>
						     	<input type="text" class="form-control" id="defaultAlarmSaveUrl" name="defaultAlarmSaveUrl" placeholder="<spring:message code='setting.defaultAlarmSaveUrl' />">
						  	</div>
	        			</div>
	        		</div>
	        		<div class="row">
	        			<div class="col-md-5">
						  	<div class="form-group">
			        			<label class="control-label"><spring:message code="setting.defaultHeartbeatRecord" /></label>
						     	<input type="text" class="form-control" id="defaultHeartbeatRecord" name="defaultHeartbeatRecord" placeholder="<spring:message code='setting.defaultHeartbeatRecord' />">
						  	</div>
	        			</div>
	        			<div class="col-md-5">
						  	<div class="form-group">
			        			<label class="control-label"><spring:message code="setting.defaultUpdatetaskRecord" /></label>
						     	<input type="text" class="form-control" id="defaultUpdatetaskRecord" name="defaultUpdatetaskRecord" placeholder="<spring:message code='setting.defaultUpdatetaskRecord' />">
						  	</div>
	        			</div>
	        		</div>
	        		<div class="row">
	        			<div class="col-md-5"></div>
	        			<div class="col-md-5 configure-form-btn">
					  		<button type="button" class="btn btn-default btn-warning" id="configback-btn">
					  			<spring:message code="common.back" />
					  		</button>
					  		<button type="button" class="btn btn-default btn-primary" id="configsave-btn">
					  			<spring:message code="common.save" />
					  		</button>
	        			</div>
	        		</div>
				</form>
        	</div>
        </div>
	</div>
</body>
  <script src="./js/jquery.min.js"></script>
  <script src="./js/base.js"></script>
  
  <script>
      	function getConfigServiceList(){
			ajaxCallback('POST', 'user/getConfigureList', {}, function (res){
				 $("#defaultSourcePath").val(res.value[0].defaultSourcePath);
				 $("#defaultSendTaskUrl").val(res.value[0].defaultSendTaskUrl);
				 $("#defaultHeartbeatSaveUrl").val(res.value[0].defaultHeartbeatSaveUrl);
				 $("#defaultTaskSaveUrl").val(res.value[0].defaultTaskSaveUrl);
				 $("#defaultAlarmSaveUrl").val(res.value[0].defaultAlarmSaveUrl);
				 $("#defaultHeartbeatRecord").val(res.value[0].defaultHeartbeatRecord);
				 $("#defaultUpdatetaskRecord").val(res.value[0].defaultUpdatetaskRecord);
				 
				 //select 选择框
				 $("#defaultHeartbeatSwitch").val(res.value[0].defaultHeartbeatSwitch);
				 $("#defaultUpdatetaskSwitch").val(res.value[0].defaultUpdatetaskSwitch);
				 $("#defaultStoragePosition").val(res.value[0].defaultStoragePosition);
				 
			});
	 	}
	 
		function saveConfigServiceList(){
			var defaultSourcePath = $("#defaultSourcePath").val();
			    defaultSendTaskUrl = $("#defaultSendTaskUrl").val();
				defaultHeartbeatSaveUrl = $("#defaultHeartbeatSaveUrl").val();
				defaultTaskSaveUrl = $("#defaultTaskSaveUrl").val();
				defaultAlarmSaveUrl = $("#defaultAlarmSaveUrl").val();
				defaultHeartbeatRecord = $("#defaultHeartbeatRecord").val();
				defaultUpdatetaskRecord = $("#defaultUpdatetaskRecord").val();
				defaultHeartbeatSwitch = $("#defaultHeartbeatSwitch").val();
				defaultUpdatetaskSwitch = $("#defaultUpdatetaskSwitch").val();
				defaultStoragePosition = $("#defaultStoragePosition").val();
			ajaxCallback('POST', 'user/saveOrUpdateConfigure', {
				  defaultSourcePath:defaultSourcePath,
				  defaultSendTaskUrl:defaultSendTaskUrl,
				  defaultHeartbeatSaveUrl:defaultHeartbeatSaveUrl,
				  defaultTaskSaveUrl:defaultTaskSaveUrl,
				  defaultAlarmSaveUrl:defaultAlarmSaveUrl,
				  defaultHeartbeatRecord:defaultHeartbeatRecord,
				  defaultUpdatetaskRecord:defaultUpdatetaskRecord,
				  defaultHeartbeatSwitch:defaultHeartbeatSwitch,
				  defaultUpdatetaskSwitch:defaultUpdatetaskSwitch,
				  defaultStoragePosition:defaultStoragePosition
				}, function (res){
				   layerOutAutoHid(res.msg);
			});
		}
		
	    $(function(){
		  getConfigServiceList();
		   
		  $('#configsave-btn').on('click', function (){
	          saveConfigServiceList();
	      });
		  
		  $('#configback-btn').on('click', function (){
	    	  history.go(-1);
	      });
	    });

  </script>
</html>