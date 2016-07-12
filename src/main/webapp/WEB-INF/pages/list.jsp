<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Quartz Manager</title>

<style type="text/css">
body {
	text-align: center;
	margin: 0 auto;
}

table {
	font-family: verdana, arial, sans-serif;
	font-size: 11px;
	color: #333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
	width: 96%;
	margin: 0 auto;
}

thead {color:green}

table tr:HOVER {
	background-color: silver;
}

table td {
	border-width: 1px;
	text-align: left;
	border-style: solid;
	border-color: #666666;
}

table tr.ERROR {
	color: red;
}

table tr .message {
	color: red;
}

form {
	margin-left: 100px;
}

form div {
	margin-bottom: 10px;
}

form label {
	display: inline-block;
	width: 200px;
}

</style>
<script type="text/javascript" 
	src="<%=request.getContextPath() %>/resources/js/jquery/jquery-1.11.1.js"></script>
<script type="text/javascript">
</script>
</head>

<body>
<div>
<h4 style="color: red;">${errMsg}</h4>
<div>
	<h1>New Job</h1>
	<form style="text-align: left;" action="add">
		
	<div>
		<!-- Job Start -->
		<label>
		Job Group*:
		<input id="jobGroup" name="jobGroup" value="${data.jobGroup}" />
		</label>
		
		<label>
		Job Name:
		<input id="jobName" name="jobName" value="${data.jobName}" placeholder="default is name of job class" />
		</label>
		
		<label>
		Job Description:
		<input id="jobDescription" name="jobDescription" value="${data.jobDescription }" />
		</label>
		
		<label for="jobName">Target Job*:
		<select id="jobClass" name="jobClass">
		<c:forEach items="${registedJobs}" var="registedJob">
			<option value="${registedJob}" <c:if test="${data.jobClass == registedJob}">selected</c:if>>${registedJob}</option>
		</c:forEach>
		</select>
		</label>
		<!-- Job End -->
	</div>
		
	<div>
		<!-- Trigger Start -->
		<label>
		Trigger Group:
		<input id="triggerGroup" name="triggerGroup" value="${data.triggerGroup}" placeholder="default is group of the job" />
		</label>
		
		<label>
		Trigger Name:
		<input id="triggerName" name="triggerName" value="${data.triggerName}" placeholder="default is name of the job" />
		</label>
		
		<label>
		Trigger Description:
		<input id="triggerDescription" name="triggerDescription" value="${data.triggerDescription}" />
		</label>
		
		<label>
		Cron Expression*:
		<input id="cronExpression" name="cronExpression" value="${data.cronExpression}" />
		</label>
		
		<label>
		Start Time:
		<input id="startTime" name="startTime" value="${data.startTime}" placeholder="yyyy-MM-dd HH:mm:ss" />
		</label>
		<!-- Trigger End -->
	</div>
		
	<div>
		<input type="submit" value="添加" />
	</div>
	</form>
</div>

<div>
<h1>Running Jobs</h1>
<table>
<thead>
	<tr>
	<td>Job Group</td>
	<td>Job Name</td>
	<td>Job Description</td>
	<td>targetObject</td>
	
	<td>Trigger Group</td>
	<td>Trigger Name</td>
	<td>Trigger State</td>
	<td>Trigger Description</td>
	<td>cronExpression</td>
	<td>startTime</td>
	<td>previousFireTime</td>
	
	<td>manage</td>
	</tr>
</thead>
<c:forEach items="${list}" var="l" varStatus="status">
<c:if test="${l.triggers.size() == 0}">
	<tr>
		<td>${l.group}</td>
		<td>${l.name}</td>
		<td>${l.description}</td>
		<td>${l.targetObject}</td>
		
		<td>${trigger.group}</td>
		<td>${trigger.name}</td>
		<td>${trigger.state}</td>
		<td>${trigger.description}</td>
		<td>${trigger.cronExpression}</td>
		<td>${trigger.startTime}</td>
		<td>${trigger.previousFireTime}</td>
		
		<td>
		<a href="edit?jobGroup=${l.group}&jobName=${l.name}&triggerGroup=${trigger.group}&triggerName=${trigger.name}">修改</a>
		|
		<c:if test="${trigger.state == '正常'}"><a href="pauseTrigger?triggerGroup=${trigger.group}&triggerName=${trigger.name}">暂停</a></c:if>
		<c:if test="${trigger.state == '暂停'}"><a href="resumeTrigger?triggerGroup=${trigger.group}&triggerName=${trigger.name}">运行</a></c:if>
		<c:if test="${trigger.state == '已完成'}">已完成</c:if>
		<c:if test="${trigger.state == '异常'}">异常</c:if>
		<c:if test="${trigger.state == '阻塞'}">阻塞</c:if>
		<c:if test="${trigger.state == '已删除'}">已删除</c:if>
		|<a href="unscheduleJob?triggerGroup=${trigger.group}&triggerName=${trigger.name}" title="解除trigger">解除</a>
		|<a href="deleteJob?jobGroup=${job.group}&jobName=${job.name}" title="删除job">删除</a>
		</td>
	</tr>
</c:if>
<c:if test="${l.triggers.size() != 0}">
	<c:forEach items="${l.triggers}" var="trigger" varStatus="tStatus">
	<tr>
		<td>${l.group}</td>
		<td>${l.name}</td>
		<td>${l.description}</td>
		<td>${l.targetObject}</td>
		
		<td>${trigger.group}</td>
		<td>${trigger.name}</td>
		<td>${trigger.state}</td>
		<td>${trigger.description}</td>
		<td>${trigger.cronExpression}</td>
		<td>${trigger.startTime}</td>
		<td>${trigger.previousFireTime}</td>
		
		<td>
		<a href="edit?jobGroup=${l.group}&jobName=${l.name}&triggerGroup=${trigger.group}&triggerName=${trigger.name}">修改</a>
		|
		<c:if test="${trigger.state == '正常'}"><a href="pauseTrigger?triggerGroup=${trigger.group}&triggerName=${trigger.name}">暂停</a></c:if>
		<c:if test="${trigger.state == '暂停'}"><a href="resumeTrigger?triggerGroup=${trigger.group}&triggerName=${trigger.name}">运行</a></c:if>
		<c:if test="${trigger.state == '已完成'}">已完成</c:if>
		<c:if test="${trigger.state == '异常'}">异常</c:if>
		<c:if test="${trigger.state == '阻塞'}">阻塞</c:if>
		<c:if test="${trigger.state == '已删除'}">已删除</c:if>
		|<a href="unscheduleJob?triggerGroup=${trigger.group}&triggerName=${trigger.name}" title="解除trigger">解除</a>
		|<a href="deleteJob?jobGroup=${job.group}&jobName=${job.name}" title="删除job">删除</a>
		</td>
	</tr>
	</c:forEach>
</c:if>
</c:forEach>
</table>
</div>
</div>
</body>
</html>