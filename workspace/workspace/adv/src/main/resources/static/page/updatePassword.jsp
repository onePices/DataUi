<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><spring:message code="password.title" /></title>
</head>
  <link rel="stylesheet" href="./css/inside.css">
<style type="text/css">
   .section{
	 text-align: center; /*让div内部文字居中*/
     background-color: #fff;
     border-radius: 20px;
     width: 395px;
     height: 350px;
     margin: auto;
     position: absolute;
     margin-top: 2%;
     margin-left: 40%
	}
	.toptitle{
	  text-align: center;
	  font-size:35px;
	  margin-top: 1%;
	  margin-bottom: 8%;
	}
	.itemform-group{
	   float: left;
	}
	.title{
	   float: left;
	}
	.input{
	   float: left;
	   margin-left:10px;
	}
	.inputq{
	   float: left;
	   margin-left:25px;
	}
	.icon-star{
	   color:red;
	}
	.form-control {
	    width: 225px;
	    height: 10px;
	    padding: 15px 12px;
	    margin-bottom:10px;
	    background-color: #fff;
	    border: 1px solid #ccc;
	    border-radius: 4px;
	    -webkit-box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
	    box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
	    -webkit-transition: border-color ease-in-out .15s,-webkit-box-shadow ease-in-out .15s;
	    -o-transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
	    transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
    }
    .btn {
		display: inline-block;
		padding: 6px 12px;
		margin-bottom: 0;
		font-size: 14px;
		font-weight: 400;
		line-height: 1.42857143;
		text-align: center;
		white-space: nowrap;
		vertical-align: middle;
		-ms-touch-action: manipulation;
		touch-action: manipulation;
		cursor: pointer;
		-webkit-user-select: none;
		-moz-user-select: none;
		-ms-user-select: none;
		user-select: none;
		border: 1px solid transparent;
		border-radius: 4px;
   }
   .btn-default {
	   color: #333;
	   background-color: #fff;
	   border-color: #ccc;
  }
  .btn-primary {
     color: #fff;
     background-color: #337ab7;
     border-color: #2e6da4;
  }
</style>
<body>
  <div class="section">
    <div class="content">          
      <div class="container-fluid">
        <div class="toptitle"><spring:message code="password.title" /></div>
        <div class="table-list">
          <div class="formForgetWorld">
            <form class="form-horizontal" id="reset-pwd-form">
              <div class="itemform-group">
                <div class="title"><spring:message code="password.oldPwd" />:</div>
                <div class="inputq">
                  <input type="password" class="form-control" id="reset-pwd-old" name="oldPwd" placeholder="<spring:message code='password.oldPwd' />">
                  <i class="icon-star">*</i>
                </div>
              </div>
              <div class="itemform-group">
                <div class="title"><spring:message code="password.newPwd" />:</div>
                <div class="inputq">
                  <input type="password" class="form-control" id="reset-pwd-password" name="newpassword" placeholder="<spring:message code='password.newPwd' />">
                  <i class="icon-star">*</i>
                </div>
              </div>
              <div class="itemform-group">
                <div class="title"><spring:message code="password.confirmPwd" />:</div>
                <div class="input">
                  <input type="password" class="form-control" id="again-pwd" name="passwordagain" placeholder="<spring:message code='password.confirmPwd' />">
                  <i class="icon-star">*</i>
                </div>
               </div>
              <div class="forget-btn" style="float: left;">
                <button style="width: 60px;margin:0px 25px" type="reset" id="reset" onclick="cleanInfo()" class="btn btn-default"><spring:message code="password.reset" /></button>
                <button style="width: 60px;margin:0px 25px" type="button" id="save" class="btn btn-primary"><spring:message code="password.save" /></button>
                <button style="width: 60px;margin:0px 25px" type="button" id="go-back" class="btn btn-orange"><spring:message code="password.return" /></button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</body>
<script src="./js/jquery.min.js"></script>
<script src="./js/base.js"></script>
<script src="./js/cryptoJS/crypto-js.js"></script>
<script type="text/javascript">

    var desKey = '${DES_KEY}',
    pwdReg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])[A-Za-z\d]{8,16}$/;
    
	$(function(){

    $('#go-back').on('click', function (){

      history.back();

    });

    $('#save').on('click', function (){

      var oldPwd = $('#reset-pwd-old').val(),
          newPwd = $('#reset-pwd-password').val(),
          againPwd = $('#again-pwd').val();

      if(oldPwd === ''){
        layerOutAutoHid('<spring:message code="password.old.notNull" />');
        return
      }

      if(newPwd === ''){
        layerOutAutoHid('<spring:message code="password.new.notNull" />');
        return
      }

      if(againPwd === ''){
        layerOutAutoHid('<spring:message code="password.confirm.notNull" />');
        return
      }

      if(newPwd !== againPwd){
        layerOutAutoHid('<spring:message code="password.newConfirm.notNull" />');
        return
      }

      if(!pwdReg.test(newPwd)){
        layerOutAutoHid('<spring:message code="password.limit" />');
        return
      }

      ajaxCallback('POST', 'user/updatePassword', {
        oldPassword: encryptByDES(oldPwd, desKey),
        newPassword: encryptByDES(newPwd, desKey)
      }, function (res){
        if(res.flag === 1){
            localStorage.clear();
            layerOutAutoHid(res.msg);
            window.location.href = "/ListenSdkService";
        }else if(res.flag === 0){
        	layerOutAutoHid(res.msg);
        	window.location.href = "/updatePasswordMain";
        }
        
      });
      
    });
    
  });
</script>
</html>