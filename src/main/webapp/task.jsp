<%@ page import="com.girish.bpm.Entity.TaskInstance" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<title>Task management</title>
</head>
<body BGCOLOR="LIGHTBLUE" >
<% String user = request.getParameter("user"); %>
<p><%= user %>'s Task</p>
<table border="1">
<tr>
<th>Task Name</th>
<th>Task Id</th>
<th>ProcessInstance Id</th>
<th>Owner Id</th>
<th>Action</th>
</tr>
<% for (TaskInstance task : (List<TaskInstance>)request.getAttribute("taskList")) { %>
<tr>
<td><%= task.getTaskId() %></td>
<td><%= task.getTaskInstanceId() %></td>
<td><%= task.getProcessInstanceId() %></td>
<td><%= task.getTaskOwnerId() %></td>
<% if(task.getTaskId()==1){ %>
<td><a href="task?user=<%= task.getTaskOwnerId() %>&taskId=<%= task.getTaskInstanceId() %>&cmd=complete">Escalate Case</a></td>
<% } %>
<% if(task.getTaskId()==2){ %>
<td><a href="task?user=<%= task.getTaskOwnerId() %>&taskId=<%= task.getTaskInstanceId() %>&cmd=complete">Resolve for Replacement</a></td>
<% } %>
<% if(task.getTaskId()==4){ %>
<td><a href="task?user=<%= task.getTaskOwnerId() %>&taskId=<%= task.getTaskInstanceId() %>&cmd=complete">AWB Details Done</a></td>
<td>
<br>AWB deatils: <input type="text" name="vov" />
</td>
<% } %>
<% if(task.getTaskId()==6){ %>
<td><a href="task?user=<%= task.getTaskOwnerId() %>&taskId=<%= task.getTaskInstanceId() %>&cmd=complete">Mark as Rev.Pick. done </a></td>
<% } %>
<% if(task.getTaskId()==7){ %>
<td><a href="task?user=<%= task.getTaskOwnerId() %>&taskId=<%= task.getTaskInstanceId() %>&cmd=complete">Mark as Delivered </a></td>
<% } %>
<% if(task.getTaskId()==8){ %>
<td><a href="task?user=<%= task.getTaskOwnerId() %>&taskId=<%= task.getTaskInstanceId() %>&cmd=complete">Mark as Replacement Done</a></td>
<% } %>
</tr>
<% } %>
</table>
</body>
</html>