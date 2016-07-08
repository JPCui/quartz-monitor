<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

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
</style>
<script type="text/javascript" 
	src="<%=request.getContextPath() %>/resources/js/jquery/jquery-1.11.1.js"></script>
<script type="text/javascript">
function displayStack(id) {
	var stackId = "#table-"+id+"-stack";
	console.log($(stackId).css("display"));
	if($(stackId).is(":visible")) {
		$(stackId).css("display", "none");
	} else {
		$(stackId).css("display", "");
	}
	
}
</script>
</head>

<body>
<h1>Jobs</h1>
<div>
<c:forEach items="${list}" var="l" varStatus="status">
<table>
	<thead>
		<tr>
		<td>group</td>
		<td>name</td>
		<td>description</td>
		<td>targetObject</td>
		</tr>
	</thead>
	<tr id="table-${status.index}" onclick="javascript:displayStack('${status.index}');">
		<td>
		<a href="#table-${status.index}">[${l.group}]</a>
		</td>
		<td>${l.name}</td>
		<td>${l.description}</td>
		<td>${l.targetObject}</td>
	</tr>
</table>
<table id="table-${status.index}-stack" style="display: none;">
	<tr>
		<td>&nbsp;</td>
		<td>group</td>
		<td>name</td>
		<td>state</td>
		<td>description</td>
		<td>cronExpression</td>
		<td>startTime</td>
		<td>previousFireTime</td>
	</tr>
	<c:forEach items="${l.triggers}" var="trigger" varStatus="tStatus">
	<tr>
		<td>${tStatus.index}</td>
		<td>${trigger.group}</td>
		<td>${trigger.name}</td>
		<td>${trigger.state}</td>
		<td>${trigger.description}</td>
		<td>${trigger.cronExpression}</td>
		<td>${trigger.startTime}</td>
		<td>${trigger.previousFireTime}</td>
	</tr>
	</c:forEach>
</table>
<br>
</c:forEach>
</div>

</body>
</html>