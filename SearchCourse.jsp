<%@page errorPage="Error.jsp" %> 

<html>
	<head>
        <title>Campus Management System</title>
        <link rel="stylesheet" href="style/style3.css">
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

		<div class = "container">
			<div class = "card">
				<div class = "inner-box" id="card">
					<form action="ControllerServlet" method="post">
						<h2>Search Course</h2>
						<h3>${message}</h3>
						<h5>Enter Either Complete name of Course or Either part of it!</h5>
						<input type = "text" class = "input-box" placeholder="Name of Course" name = "Course" required></br>
						<button type="submit" class="submit-btn" name = "action" value="SearchCourseinDB">Submit</button>
					</form>
						<a href="ControllerServlet?action=AllRecords" ><button>View All Courses</button></a><br>
						<a href="AdminMain.jsp" ><button>Back to Admin Main</button></a>
					</div>
				</div>
			</div>
    </body>
</html>