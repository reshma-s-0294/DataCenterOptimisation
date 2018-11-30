<%@page import="java.sql.Date"%>
<%@page import="com.ucc.dc.models.Task"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
 $(document).ready(function() {
        $(".add-row").click(function(){
        debugger;
            var name = $("#name").val();
            var deadline = $("#deadline").val();
            var markup = "<tr><td></td><td>" + name + "</td><td>" + deadline + "</td></tr>";
            $("table tbody").append(markup);
        });
        });
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Data Center Incoming Task and Processing</title>
</head>
<body>
<h1>Data Center Incoming Task and Processing</h1>

<form action="${pageContext.request.contextPath}/InsertTask" method="post">
	<input type="text" id="name" placeholder="Task Name" name="name">
	<input type="text" id="deadline" placeholder="Deadline" name="deadline">
	<input type="submit" value="Add Task" />
	</form>
<form action="${pageContext.request.contextPath}/InsertTask" method="get">
<input type="submit" value="Process added tasks" >

<input type="text" id="processingtasklabel" name="processingtasklabel" value='<%=request.getAttribute("processingtasklabel")%>'>

<table>

  <c:forEach items="${processedTasks}" var="processedTask">
    <tr>
    <td>${processedTask.taskName}</td>
    </tr>
  </c:forEach>
</table>


</form>


</body>
</html>
<%

%>
<style>
table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 100%;
}

td, th {
    border: 1px solid #dddddd;
    text-align: left;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #dddddd;
}
</style>

