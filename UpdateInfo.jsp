<%@page errorPage="Error.jsp" %> 
<%@page import="MyPack.*" %> 
<html>
	<head>
	<title>Campus Management System</title>
    <link rel="stylesheet" href="style/style3.css">
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

<div class = "container">
	<div class = "card">
		<div class = "inner-box" id="card">
			<h2>Update Personal Info</h2>
			<form action="ControllerServlet" method="post">
					<h5>${message}</h5>
                            <input type="text" class = "input-box" placeholder="Name" name="username" required>
                            <input type="email" class = "input-box" placeholder="Email" name="email" required>
                            <input type="password" class = "input-box" placeholder="Password" name="password" required>
                            <input type="number" class = "input-box" placeholder="Semester" name="semester" required>
                            <input type="date" class = "input-box" placeholder="Date of Birth" name = "dob" required>
                            <input type="tel" class = "input-box" placeholder="Phone Number" name = "phone" required>
                            <button type="submit" class="submit-btn" name = "action" value="UpdateInfoInDB">Submit</button>
			</form>
			<a href="ControllerServlet?action=BacktoMain&username=<%=request.getAttribute("username")%>" ><button>Back to Student Main</button></a>
		</div>
	</div>
	</div>
</body>
</html>