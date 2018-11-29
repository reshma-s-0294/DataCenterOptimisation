<%@page import="java.sql.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Data Center Incoming Task and Processing</title>
</head>
<body>
<h1>Data Center Incoming Task and Processing</h1>

<form action="${pageContext.request.contextPath}/InsertTask" method="post">
 <input type="text" id="name" placeholder="Task Name">
        <input type="text" id="deadline" placeholder="Deadline">
    	<input type="button" class="add-row" value="Add Task">

<input type="submit" value="Submit" /></form>
</body>
</html>

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

