<%@page errorPage="Error.jsp" %> 

<html>
	<head>
	<title>Campus Management System</title>
        <link rel="stylesheet" href="style/style1.css">
	</head>
<body style="margin: 0px;">

<%
	if(session == null || session.getAttribute("UserType") == null){ %>
		<jsp:forward page="Admin.jsp">
			<jsp:param name="message" value="session has expired login again" />
		</jsp:forward>
	<% } 
		
	else{
		Integer uid = (Integer)session.getAttribute("UserType");
		if(uid != 0){	%>
			<jsp:forward page="Admin.jsp">
				<jsp:param name="message" value="login as Admin first" />
			</jsp:forward>
		<% }
	}
%>

<div class="container">
	<div class = "buttons">
     <h1>Admin Main</h1><br><br>
	<form   action="ControllerServlet" method="post">
        <button type = "submit"	name = "action" value="AddCourse">Add a Course</button>
        <button type = "submit"	name = "action" value="AssignCourse">Assign a Course</button>
        <button type = "submit"	name = "action" value="SearchCourse">Search a Course</button>
        <button type = "submit"	name = "action" value="DeleteCourse">Delete a Course</button>
        <button type = "submit"	name = "action" value="DeleteStudent">Delete a Student</button>
    </form>
	<br><br><a href="ControllerServlet?action=Logout" ><button>Logout</button></a><br>
	</div>
</div>
</body>
</html>
