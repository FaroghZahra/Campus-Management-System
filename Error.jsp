<%@page isErrorPage="true" %> 
<%@page import = "java.sql.SQLException" %> 

<html>
<head>
	<title>Campus Management System</title>
        <link rel="stylesheet" href="style/style4.css">
	</head>

    <body>
<div class="container">
<h2>Error Page</h2> 

<h3> Exception occured while interacting with the database</h3> 

<h3>The Error Message was <%= request.getAttribute("error") %></h3>
<h3 > Please Try Again Later! </h3> <br><br>
<center>
<a href="index.html" ><button>Back to Main Page</button></a>
</center>
</div>
</body>
</html>