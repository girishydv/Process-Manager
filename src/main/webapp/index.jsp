<html>
<head>
<title>ProcessManager</title>
</head>
<body BGCOLOR="LIGHTBLUE" >
<p>ProcessManager</p>
<p><%= request.getAttribute("message") == null ? "" : request.getAttribute("message") %></p>
<ul>
<li><a href="startProcess.jsp">Start Replacement Process</a></li>
<li><a href="task?user=girish.yadav@homeshop18.com&cmd=list">TroubleShoot Desk Task</a></li>
<li><a href="task?user=anuj.paul@homeshop18.com&cmd=list">Escalation Desk Task</a></li>
<li><a href="task?user=amit.chandra@homeshop18.com&cmd=list">Courier Desk Task 1</a></li>
<li><a href="task?user=samarth.srivastava@homeshop18.com&cmd=list">Courier Desk Task 2</a></li>
<li><a href="task?user=harvijay.gagneja@homeshop18.com&cmd=list">Resolution Desk Task</a></li>
</ul>
</body>
</html>