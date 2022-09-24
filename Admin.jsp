<%@page errorPage="Error.jsp" %> 

<html>
    <head>
        <title>Campus Management System</title>
        <link rel="stylesheet" href="style/style3.css">
    </head>

    <body style="margin: 0px;">
        <div class = "container">
            <div class = "card">
                <div class = "inner-box" id="card">
                        <h2>Log In Admin</h2>
                        <form action = "ControllerServlet" method="post">
							<h5>${message}</h5>
							<h5>${param["message"]}</h5>
                            <input type="text" class = "input-box" placeholder="User Name" name="username" required>
                            <input type="password" class = "input-box" placeholder="Password" name="password" required>
                            <button type="submit" class="submit-btn" name = "action" value="loginAdmin">Submit</button>	
                        </form>
                        <a href="index.html" ><button>Back to Main Page</button></a>
                </div>
            </div>    
        </div>
    </body>
</html>