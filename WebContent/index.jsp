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
 <input type="text" id="name" placeholder="Task Name">
        <input type="text" id="deadline" placeholder="Deadline">
    	<input type="button" class="add-row" value="Add Task">
<form action="guru_register" method="post">
<table>
  <tr>
    <th>Task Id</th>
    <th>Task</th>
    <th>Deadline</th>
  </tr>
  <tbody>
  <tr>
    <td >1</td>
    <td contenteditable="true">Task 1</td>
    <td contenteditable="true">10 ms</td>
  </tr>
  </tbody>
  <tbody>
  <tr>
    <td contenteditable="true">2</td>
    <td contenteditable="true">Task 2</td>
    <td contenteditable="true">11 ms</td>
  </tr>
  </tbody>
  <tbody>
  <tr>
    <td>3</td>
    <td contenteditable="true">Task 3</td>
    <td contenteditable="true">15ms</td>
  </tr>
  </tbody>
  <tbody>
  <tr>
    <td>4</td>
    <td contenteditable="true">Task 4</td>
    <td contenteditable="true">2 ms</td>
  </tr>
  </tbody>
  <tbody>
  <tr>
    <td>5</td>
    <td contenteditable="true">Task 5</td>
    <td contenteditable="true">8 ms</td>
  </tr>
  </tbody>
  <tbody>
  <tr>
    <td>6</td>
    <td contenteditable="true">Task 6</td>
    <td contenteditable="true">7 ms</td>
  </tr>
  </tbody>
</table>
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

