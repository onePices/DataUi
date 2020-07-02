<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE>
<html>
<head>
  	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no" />
	<title><spring:message code="platform.enname" /></title>
	<link rel="stylesheet" href="./css/inside.css">
	<style>
		*{
			padding: 0;
			margin: 0;
		}
	</style>
</head>
<body onkeydown="keyLogin();">
	<div class="login-wrap">
		<div class="header clearfix">
		  <a class="navbar-brand" href="javascript:">
		  <i class="logo left">
              <img src="./images/logo.png" alt="">
          </i>
		  <div class="logo left">
			 <spring:message code="platform.enname" />
		  </div>
		  </a>
		<div class="right">
			<select class="select" id="select-lang" onchange="changeLang()">
				<option value="CN"><spring:message code="login.selectLanguage" /></option>
				<option value="CN"><spring:message code="login.chinese" /></option>
				<option value="US"><spring:message code="login.english" /></option>
			</select>
		</div>
		</div>
		<div class="login-form">
			<div class="login-title">
			    <spring:message code="login.title" />
			</div>
			<div class="cont">
				<div class="item">
					<input type="text" name="user" id="login-user" placeholder="<spring:message code='login.username'/>">
			    </div>
				<div class="item">
					<input type="password" name="password" id="login-pwd" placeholder="<spring:message code='login.password'/>">
				</div>
				<div class="item">
					<button type="button" class="btn btn-orange" id="login"><spring:message code="login.title"/></button>
				</div>
			</div>
		</div>
	</div>
	<script src="./js/jquery.min.js"></script>
	<script src="./js/base.js"></script>
	<script src="./js/cryptoJS/crypto-js.js"></script>
	<script>
		// localStorage.setItem("lastname", "Smith");
		var key = '${DES_KEY}';

		function changeLang(){
			var langType = $('#select-lang').val();
			window.location.href = "changeLang?langType="+langType;
		}
		
		function keyLogin(){ //回车键的键值为13
			if (event.keyCode==13){  
			    login();
			}
        }
	    
		function login(){
			var username = $('#login-user').val(),
				userpwd = $('#login-pwd').val();
			if(username === ''){
				return layerOutAutoHid('<spring:message code="login.userName.notNull"/>')
			}
			if(userpwd === ''){
				return layerOutAutoHid('<spring:message code="login.passWord.notNull"/>')
			}
			ajaxCallback('POST', 'sdkNew/loginIn', {
				username: encryptByDES(username, key),
				password: encryptByDES(userpwd, key)
			}, function (res){
				if(res.flag === 1){
					window.location.href = res.value;
				}
			});
		}
		
		$(function (){
			$('#login').on('click', function (){
				var username = $('#login-user').val(),
					userpwd = $('#login-pwd').val();
				if(username === ''){
					return layerOutAutoHid('<spring:message code="login.userName.notNull"/>')
				}
				if(userpwd === ''){
					return layerOutAutoHid('<spring:message code="login.passWord.notNull"/>')
				}
				ajaxCallback('POST', 'sdkNew/loginIn', {
					username: encryptByDES(username, key),
					password: encryptByDES(userpwd, key)
				}, function (res){
					if(res.flag === 1){
						window.location.href = res.value;
					}
				});
			});
		});
	</script>
</body>
</html>