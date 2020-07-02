<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
  	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no" />
	<title><spring:message code="sdkInterface.operation.selectDevice" /></title>
</head>
<body>
<h1><spring:message code="sdkInterface.deviceInformation.title" />:</h1>
<hr>
<div class="sdk">
	<table>
	<c:choose>
		<c:when test="${devList != null || mapDevList != null}">
			<c:if test="${devList != null}">
				<th align="center" colspan="5"><spring:message code="sdkInterface.deviceInformation.codeAndMac" />:</th>
				<tr>
					<td style="width:300px"><spring:message code="sdkInterface.deviceInformation.deviceMac" /></td>
					<td style="width:300px"><spring:message code="sdkInterface.deviceInformation.deviceCode" /></td>
				</tr>
				<c:forEach items="${devList }" var="dev">
					<tr>
						<td>${dev.devMac}</td>
						<td>${dev.devCode}</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${mapDevList != null}">
				<th align="center" colspan="5"><spring:message code="sdkInterface.deviceInformation.codeAndImg" />:</th>
				<tr>
					<td style="width:200px"><spring:message code="sdkInterface.deviceInformation.deviceCode" /></td>
					<td style="width:300px"><spring:message code="sdkInterface.deviceInformation.imgName" /></td>
					<td style="width:200px"><spring:message code="sdkInterface.deviceInformation.bindingTime" /></td>
					<td style="width:200px"><spring:message code="sdkInterface.deviceInformation.updateDate" /></td>
					<td style="width:600px"><spring:message code="sdkInterface.deviceInformation.imgUrl" /></td>
				</tr>
				<c:forEach items="${mapDevList }" var="dev">
					<tr>
						<td>${dev.devCode}</td>
						<td>${dev.imageName}</td>
						<td>
							<jsp:useBean id="dateValue" class="java.util.Date"/>
							<jsp:setProperty name="dateValue" property="time" value="${dev.createDate}"/>
							<fmt:formatDate value='${dateValue}' pattern='yyyy-MM-dd HH:mm:ss'/>
						</td>
						<td>
							<jsp:useBean id="updateTime" class="java.util.Date"/>
							<jsp:setProperty name="updateTime" property="time" value="${dev.updateDate}"/>
							<fmt:formatDate value='${updateTime}' pattern='yyyy-MM-dd HH:mm:ss'/>
						</td>
						<td>${dev.imageUrl}</td>
					</tr>
				</c:forEach>
			</c:if>
		</c:when>
		<c:otherwise>
			<div><spring:message code="sdkInterface.deviceInformation.notDataDev" /></div>
		</c:otherwise>
	</c:choose>
	</table>
</div>
</body>
</html>
