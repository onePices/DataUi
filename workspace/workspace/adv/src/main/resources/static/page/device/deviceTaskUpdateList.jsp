<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
  	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1.0,maximum-scale=1.0, user-scalable=no" />
	<title><spring:message code="sdkInterface.deviceTask.title" /></title>
</head>
<body>
<h1><spring:message code="sdkInterface.deviceTask.title" />:</h1>
<hr>
<div class="sdk">
	<table>	
		<c:choose>
			<c:when test="${taskUpdateList != null}">
				<c:if test="${taskUpdateList != null}"> 
					<tr> 
						<td style="width:300px"><spring:message code="sdkInterface.deviceTask.taskId" /></td>
						<td style="width:300px"><spring:message code="sdkInterface.deviceTask.devCode" /></td>
						<!-- <td style="width:300px">设备型号</td> -->
						<td style="width:300px"><spring:message code="sdkInterface.deviceTask.taskType" /> </td> 
						<td style="width:300px"><spring:message code="sdkInterface.deviceTask.processingState" /></td>
						<td style="width:300px"><spring:message code="sdkInterface.deviceTask.downloadProgress" /></td>
						<td style="width:300px"><spring:message code="sdkInterface.deviceTask.downloadRate" /></td>
						<td style="width:300px"><spring:message code="sdkInterface.deviceTask.updateBy" /></td>
						<td style="width:300px"><spring:message code="sdkInterface.deviceTask.updateDate" /></td>
						<td style="width:300px"><spring:message code="sdkInterface.deviceTask.taskDescribe" /></td>
					</tr>
					<c:forEach items="${taskUpdateList}" var="task">
						<tr>
							<td>${task.taskId}</td>
							<td>${task.devCode}</td>
					        <%-- <td>${task.devType}</td> --%>
							<td>${task.taskType}</td>
							<%-- <td>${task.taskStatus}</td> --%>
							<td>${task.devTaskStatusDis}</td>
							<td>${task.taskPercent}</td>
							<td>${task.taskDownloadRate}</td>
							<td>${task.updateBy}</td>
							<td>${task.strUpdateDateTime}</td>
							<td>${task.taskRemarks}</td>
						</tr>
					</c:forEach>
				</c:if> 
			</c:when>
			<c:otherwise>
				<div><spring:message code="sdkInterface.deviceTask.notDataTask" /></div>
			</c:otherwise>
		</c:choose>
	</table>
</div>
</body>
</html>
