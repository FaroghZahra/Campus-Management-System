<%@page errorPage="Error.jsp" %> 

<html>
	<head>
	<title>Campus Management System</title>
        <link rel="stylesheet" href="style/style1.css">
	</head>
<body style="margin: 0px;">

<%
	if(session == null || session.getAttribute("UserType") == null){ %>
		<jsp:forward page="Student.jsp">
			<jsp:param name="message" value="session has expired login again" />
		</jsp:forward>
	<% } 
		
	else{
		Integer uid = (Integer)session.getAttribute("UserType");
		if(uid != 1){	%>
			<jsp:forward page="Student.jsp">
				<jsp:param name="message" value="login as Student first" />
			</jsp:forward>
		<% }
	}
%>

<div class="container">
	<div class = "buttons">
    <h1>Student Main</h1><br><br>
	<form action="ControllerServlet?username=<%=request.getAttribute("username")%>" method="post">
        <button type = "submit"	name = "action" value="ViewInfo">View Personal Information</button>
        <button type = "submit"	name = "action" value="ViewCourses">View Courses Assigned</button>
        <button type = "submit"	name = "action" value="UpdateInfo">Update Personal Info</button>
    </form>
	<br><br><a href="ControllerServlet?action=Logout" ><button>Logout</button></a><br>
	</div>
</div>
</body>
</html>
