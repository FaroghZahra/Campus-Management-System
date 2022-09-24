<%@page import="java.util.*" %>
<%@page import="MyPack.*" %> 
<%@page errorPage="Error.jsp" %> 

<html>
    <head>
	<title>Campus Management System</title>
    <link rel="stylesheet" href="style/style4.css">
	</head>
    
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

    <body style="margin: 0px;">
        <div class="container">
        <center>
        <h2> Courses </h2> 
        <h3> Following results meet your search criteria</h3> <br>
        
        <TABLE BORDER="1"> 
        
            <TR> 
            <TH> Course </TH> 
            <TH> Semester </TH> 
            <TH> Teacher </TH> 
            </TR> 
        
            <% 
            ArrayList CourseList = (ArrayList)request.getAttribute("list"); 
            CourseInfo course = null; 
            
            for(int i=0; i<CourseList.size(); i++) { 
            course = (CourseInfo)CourseList.get(i); %> 
            
            
            <TR> <TD> <%= course.getCourse()%> </TD>
            <TD> <%= course.getSemester()%> </TD>
            <TD> <%= course.getTeacher()%> </TD> 
            </TR> 
            
            <% 
            } 
            %> 

        </TABLE>
        <div class="buttons">
            <br><a href="AdminMain.jsp"><button>Back to Admin Main</button></a>
        </div>
    </center>
</div>
</body></html> 