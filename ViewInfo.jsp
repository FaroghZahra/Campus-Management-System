<%@page errorPage="Error.jsp" %> 
<%@page import="MyPack.*" %> 
<html>
	<head>
	<title>Campus Management System</title>
    <link rel="stylesheet" href="style/style4.css">
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
    <center>
        <h2>Student Information</h2><br>
        <h3> Following are student Attributes!</h3> <br>
     <% 
        StudentInfo s = (StudentInfo)request.getAttribute("info");
     %>
     <table border="1">
      
        <h5><tr><td>Student Name</td> <td><%= s.getName()  %></td></tr></h5>
        <h5><tr><td>Student Email</td> <td><%= s.getEmail()  %></td></tr></h5>
        <h5><tr><td>Student Password</td> <td><%= s.getPassword() %></td></tr></h5>
        <h5><tr><td>Student Semester</td> <td><%=  s.getSemester() %></td></tr></h5>
        <h5><tr><td>Student Date of Birth</td> <td><%=  s.getDob() %></td></tr></h5>
        <h5><tr><td>Student Phone</td> <td><%= s.getPhone() %></td></tr></h5>
    </table>

    <div class="buttons">
     <br><a href="ControllerServlet?action=BacktoMain&username=<%=request.getAttribute("username")%>" ><button>Back to Student Main</button></a>
    </div>
    </center> 
    </div>
    </body>
</html>     