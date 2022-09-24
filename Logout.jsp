<%@page errorPage="Error.jsp" %> 

<html>
<body>
<% 	
	Integer uid = (Integer)session.getAttribute("UserType");
	session.invalidate(); 

	if(uid == 0){
%>
<jsp:forward page="Admin.jsp">
	<jsp:param name="message" value="session logged out" />
</jsp:forward>
<% 
	}else if(uid == 1){
%>
<jsp:forward page="Student.jsp">
	<jsp:param name="message" value="session logged out" />
</jsp:forward>
<% } %>
</body>
<html>