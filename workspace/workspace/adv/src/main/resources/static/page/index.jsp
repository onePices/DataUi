<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
  	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no" />
	<title><spring:message code="sdkInterface.operation.title" /></title>
	<link rel="stylesheet" href="./css/inside.css">
</head>
<style type="text/css">
	.clearfix .select {
		padding: 8px 5px;
		border-radius: 10px;
	}
	.select {
		-webkit-writing-mode: horizontal-tb !important;
		text-rendering: auto;
		color: -internal-light-dark-color(black, white);
		letter-spacing: normal;
		word-spacing: normal;
		text-transform: none;
		text-indent: 0px;
		text-shadow: none;
		display: inline-block;
		text-align: start;
		-webkit-appearance: menulist;
		box-sizing: border-box;
		align-items: center;
		white-space: pre;
		-webkit-rtl-ordering: logical;
		background-color: -internal-light-dark-color(white, black);
		cursor: default;
		margin: 0em;
		font: 400 13.3333px Arial;
		border-radius: 0px;
		border-width: 1px;
		border-style: solid;
		border-color: rgb(169, 169, 169);
		border-image: initial;
	}
</style>
<body>
	<div class="clearfix">
		<div class="left"><h1><spring:message code="sdkInterface.operation.title" /></h1></div>
		<div class="right" style="margin: 25px 10px 0px 10px">
			<button type="button" id="layout" class="btn btn-orange"><spring:message code="logout.exit" /></button>
		</div>
		<div class="right" style="margin: 25px 10px 0px 10px">
			<button type="button" id="save" class="btn btn-blue" onclick="updatePassword()"><spring:message code="password.title" /></button>
		</div>
		<div class="right" style="margin: 25px 10px 0px 10px">
			<select class="select" id="select-lang" onchange="changeLang()">
				<!--<option value="CN"><spring:message code="login.selectLanguage" /></option>-->
				<option value="CN"><spring:message code="login.chinese" /></option>
				<option value="US"><spring:message code="login.english" /></option>
			</select>
		</div>
		<div class="right" style="margin: 25px 10px 0px 10px">
			<select class="select" id="select-setting" onchange="changeSetting()">
			    <option value="0"><spring:message code="setting.title" /></option>
				<option value="1"><spring:message code="setting.select" /></option>
			</select>
		</div>
	</div>
<hr>
<h3><a href="javascript:void(0);" onclick="deviceMain()" target="_blank"><spring:message code="sdkInterface.operation.selectDevice" /></a></h3>
<h3><a href="javascript:void(0);" onclick="deviceHeartBeatList()" target="_blank"><spring:message code="sdkInterface.operation.selectDeviceHeartbeat" /></a></h3>
<h3><a href="javascript:void(0);" onclick="deviceTaskUpdateList()" target="_blank"><spring:message code="sdkInterface.operation.selectTaskProgress" /></a></h3>
<h3><spring:message code="sdkInterface.operation.selectOperationType" />:</h3>
<div>
	<label><input name="sendUrl" id="url1" type="radio" checked="checked" value="lsPublishProgram.fgl" onclick="checkType()"/><spring:message code="sdkInterface.operation.nextShow" /></label>
	<label><input name="sendUrl" id="url2" type="radio" value="lsPublishTask.fgl" onclick="checkType()"/><spring:message code="sdkInterface.operation.nextTask" /></label>
	<label><input name="sendUrl" id="url3" type="radio" value="updateData.fgl" onclick="checkType()"/><spring:message code="sdkInterface.operation.updateDate" /></label>
	<label><input name="sendUrl" id="url4" type="radio" value="addMapDevice.fgl" onclick="checkType()"/><spring:message code="sdkInterface.operation.deviceBindingPicture" /></label>
</div>
<hr>
<h3><spring:message code="sdkInterface.operation.inputDate" />:</h3>
<div class="sdk">
	<div><spring:message code="sdkInterface.operation.devCode" />:&nbsp;&nbsp;<input name="devCode" id="devCode"  type="text"/></div>
	<div id="program" style="display:none">
		<div><spring:message code="sdkInterface.operation.programDate" />:<textarea name="strProgramList" id="strProgramList" rows="20" cols="260"></textarea></div>
	</div>
	<div id="task" style="display:none">
		<div><spring:message code="sdkInterface.operation.taskType" />:&nbsp;&nbsp;<input name="type" id="type"  type="text"/></div>
		<div><spring:message code="sdkInterface.operation.taskDate" />:<textarea name="content" id="content" rows="20" cols="260"></textarea></div>
	</div>
	<div id="data" style="display:none">
		<div><spring:message code="sdkInterface.operation.equipmentType" />:&nbsp;&nbsp;<input name="devType" id="devType"  type="text"/></div>
		<div><input name="devDataType" id="devDataType"  type="hidden" value="1"/></div>
		<div><spring:message code="sdkInterface.operation.data" />:<textarea name="dataInfo" id="dataInfo" rows="20" cols="260"></textarea></div>
	</div>
	<div id="map" style="display:none">
		<div><spring:message code="sdkInterface.operation.pictureName" />:<textarea name="imageName" id="imageName" rows="20" cols="260"></textarea></div>
	</div>
	<div>
		<input type="button" style="color:green;" value="<spring:message code='sdkInterface.operation.submit' />" onclick="submitData()"/>
	</div>
</div>
<script src="./js/jquery.min.js"></script>
<script src="./js/cryptoJS/crypto-js.js"></script>
<script src="./js/base.js"></script>
<script type="text/javascript">
	var desKey = '${DES_KEY}';
    var storagePosition = '${storagePosition}';
    var sendTaskUrl = '${sendTaskUrl}';
    
	function changeLang(){
		var langType = $('#select-lang').val();
		window.location.href = "./changeLang?langType="+langType;
	}
	
	function changeSetting(){
		var settType = $('#select-setting').val();
		window.location.href = "./configureServiceMain";
	}
	
    function deviceMain(){
    	if(storagePosition == 1){
    	   window.location.href = "./sdkNew/deviceMain";
    	}else if(storagePosition == 2){
    	   window.location.href = "./deviceListMain";
    	}
    }
    
    function deviceHeartBeatList(){
    	if(storagePosition == 1){
    	   window.location.href = "./sdkNew/deviceHeartBeatList";
    	}else if(storagePosition == 2){
    	   window.location.href = "./deviceHeartBeatListMain";
    	}
    }
    
    function deviceTaskUpdateList(){
    	if(storagePosition == 1){
    	   window.location.href = "./sdkNew/deviceTaskUpdateList";
    	}else if(storagePosition == 2){
    	   window.location.href = "./deviceTaskUpdateListMain";
    	}
    }
    
    function updatePassword(){
    	window.location.href = "./updatePasswordMain";
    }
    
	function checkType(){ 
		if($('#url1').prop('checked')){
			$('#program').show();
			$('#task').hide();
			$('#data').hide();
			$('#map').hide();
		}
		if($('#url2').prop('checked')){
			$('#task').show();
			$('#program').hide();
			$('#data').hide();
			$('#map').hide();
		}
		if($('#url3').prop('checked')){
			$('#data').show();
			$('#task').hide();
			$('#program').hide();
			$('#map').hide();
		}
		if($('#url4').prop('checked')){
			$('#map').show();
			$('#data').hide();
			$('#task').hide();
			$('#program').hide();
		}
	}
	function submitData(){
		var devCode = $('#devCode').val();
		//var url = "http://192.168.0.60:8080/ListenSdkService/sdkNew/" + $('input[name="sendUrl"]:checked').val();
		var url = sendTaskUrl + $('input[name="sendUrl"]:checked').val();
		if($('#url1').prop('checked')){
			var strProgramList = $('#strProgramList').val();
			console.log(strProgramList);
			if(strProgramList != "" && devCode != "" && url != ""){
				var params = "devCode="+devCode+"&strProgramList="+strProgramList;
				var result = baseAjax("POST",url,params);
				console.log(result);
				if(result.result == "fail"){
					layerOutAutoHid(result.response.errorText);
				}else if(result.result == "ok"){
					layerOutAutoHid(result.response.tips);
				}
			}else{
				layerOutAutoHid("<spring:message code='sdkInterface.operation.parameterEmpty' />")
			}
		}
		if($('#url2').prop('checked')){
			var type = $('#type').val();
			var content = $('#content').val();
			if(devCode != "" && url != "" && type != ""){
				var params = "devCode="+devCode+"&content="+content+"&type="+type;
				var result = baseAjax("POST",url,params);
				if(result.result == "fail"){
					layerOutAutoHid(result.response.errorText);
				}else if(result.result == "ok"){
					layerOutAutoHid(result.response.tips);
				}
			}else{
				layerOutAutoHid("<spring:message code='sdkInterface.operation.parameterEmpty' />")
			}
		}
		if($('#url3').prop('checked')){
			var devType = $('#devType').val();
			var devDataType = $('#devDataType').val();
			var dataInfo = $('#dataInfo').val();
			if(dataInfo != "" && devCode != "" && url != "" && dataInfo != "" && devType!= ""){
				var params = "devCode="+devCode+"&devType="+devType+"&devDataType="+devDataType+"&dataInfo="+dataInfo;
				var result = baseAjax("POST",url,params);
				if(result.result == "fail"){
					layerOutAutoHid(result.response.errorText);
				}else if(result.result == "ok"){
					layerOutAutoHid(result.response.tips);
				}
			}else{
				layerOutAutoHid("<spring:message code='sdkInterface.operation.parameterEmpty' />")
			}
		}
		if($('#url4').prop('checked')){
			var imageName = $('#imageName').val();
			if(imageName != "" && devCode != "" && url != ""){
				var params = "devCode="+devCode+"&imageName="+imageName;
				var result = baseAjax("POST",url,params);
				if(result.result == "fail"){
					layerOutAutoHid(result.response.errorText);
				}else if(result.result == "ok"){
					layerOutAutoHid(result.response.tips);
				}
			}else{
				layerOutAutoHid("<spring:message code='sdkInterface.operation.parameterEmpty' />")
			}
		}
	}
	
	checkType();
	
	function getUrlParam(paramname){
	      var reg = new RegExp("(^|&)" + paramname + "=([^&]*)(&|$)");
	      // 查询匹配 substr(1)删除? match()匹配
	      var s = window.location.search.substr(1).match(reg);
	      if (s != null) {
	        return unescape(s[2]); // unescape() 函数可对通过 escape() 编码的字符串进行解码。
	      }
	      return null;
	}
	
	function loadLanguage(){
		if('<spring:message code="page.language"/>' === 'CN'){
			$("#select-lang").val('CN');
		}else if('<spring:message code="page.language"/>' === 'US'){
			$("#select-lang").val('US');
		}
	}

	$(function (){
		//语言
		loadLanguage();
		
		$('#layout').on('click', function (){
			ajaxCallback('POST', 'sdkNew/logOut', {
				username: encryptByDES(localStorage.getItem("userName"), desKey)
			}, function (res){
				alert(11);
				if(res.flag === 1){
					localStorage.clear();
					layerOutAutoHid(res.msg);
					window.location.href = "/";
				}else if(res.flag === 0){
					layerOutAutoHid(res.msg);
					window.location.href ="/sdkOperationMain";
				}
			});
		});
	});
</script>
</body>
</html>
