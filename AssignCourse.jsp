<%@page errorPage="Error.jsp" %> 

<html>
	<head>
        <title>Campus Management System</title>
        <link rel="stylesheet" href="style/style3.css">
    </head>

	<body style="margin: 0px;">

<%
	if(session == null || session.getAttribute("UserType") == null) 
		response.sendRedirect("index.html");
	else{
		Integer uid = (Integer)session.getAttribute("UserType");
		if(uid != 0){
			response.sendRedirect("index.html");
		}
	}
%>

<div class = "container">
	<div class = "card">
		<div class = "inner-box" id="card">
			<h2>Assign a Course</h2>
			<form action="ControllerServlet" method="post">
					<h3>${message}</h3>
					<input type = "text" class = "input-box" placeholder="Name of Course" name = "Course" required></br>
					<input type = "text" class = "input-box" placeholder="Name of Teacher" name = "teacher" required></br>
					<input type = "number" class = "input-box" placeholder="Semester" name = "semester" required></br>
					<button type="submit" class="submit-btn" name = "action" value="AssignedCourseinDB">Submit</button>
			</form>
			<a href="AdminMain.jsp" ><button>Back to Admin Main</button></a>
		</div>
	</div>
	</div>
</body>
</html>