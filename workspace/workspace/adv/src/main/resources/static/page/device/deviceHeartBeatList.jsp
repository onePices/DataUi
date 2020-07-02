<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
  	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no" />
	<title><spring:message code="sdkInterface.deviceHeartbeat.title" /></title>
</head>
<body>
<h1><spring:message code="sdkInterface.deviceHeartbeat.title" />:</h1>
<hr>
<div class="sdk">
	<table>	
		<c:choose>
			<c:when test="${onlineDevList != null}">
				<c:if test="${onlineDevList != null}"> 
					<tr>
						<td style="width:300px"><spring:message code="sdkInterface.deviceHeartbeat.devMac" /></td>
						<td style="width:300px"><spring:message code="sdkInterface.deviceHeartbeat.devCode" /></td>
						<td style="width:300px"><spring:message code="sdkInterface.deviceHeartbeat.devTime" /></td>
					</tr>
					<c:forEach items="${onlineDevList }" var="dev">
						<tr>
							<td>${dev.devMac}</td>
							<td>${dev.devCode}</td>
							<td>${dev.lastOnlineTime}</td>
						</tr>
					</c:forEach>
				</c:if> 
			</c:when>
			<c:otherwise>
				<div><spring:message code="sdkInterface.deviceHeartbeat.notDataHeart" /></div>
			</c:otherwise>
		</c:choose>
	</table>
</div>
</body>
</html>