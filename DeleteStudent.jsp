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
                <h2>Delete Student</h2>
                <h3>${message}</h3>
                <input type = "email" class = "input-box" placeholder="Email of Student to Delete" name = "Student" required></br>
                <button type="submit" class="submit-btn" name = "action" value="DeleteStudentinDB">Submit</button>
            </form>
                <a href="AdminMain.jsp" ><button>Back to Admin Main</button></a>
            </div>
        </div>
    </div>
</body>
</html>