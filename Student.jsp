<%@page errorPage="Error.jsp" %>

<html>
    <head>
        <title>Campus Management System</title>
        <link rel="stylesheet" href="style/style2.css">
    </head>

    <body style="margin: 0px;">
        <div class = "container">
            <div class = "card">
                <div class = "inner-box" id="card">
                    <div class = "card-front">
                        <h2>Log In Student</h2>
                        <h5>${message}</h5>
                        <h5>${param["message"]}</h5>
                        <form action = "ControllerServlet" method="post">
                            <input type="text" class = "input-box" placeholder="User Name" name="username" required>
                            <input type="password" class = "input-box" placeholder="Password" name="password" required>
                            <button type="submit" class="submit-btn" name = "action" value="loginStudent">Submit</button> 
                        </form>
                        <h6>Do not have a Account?</h6><button type="button" class = "btn" onclick="openSignUp()">Sign up</button>
                        <a href="index.html" ><button>Back to Main Page</button></a>
                    </div>
                    <div class = "card-back">
                        <h2>Sign Up Student</h2>
                        <form action="ControllerServlet" method="post" onsubmit="return validate()">
                            <input type="text" class = "input-box" placeholder="Name" name="username" required>
                            <input type="email" class = "input-box" placeholder="Email" name="email" required>
                            <input type="password" class = "input-box" placeholder="Password" name="password" id="pwd" required>
                            <input type="password" class = "input-box" placeholder="Confirm Password" id="confirmpwd" required>
                            <h6><div id = "write"  style="color: #ff0000"></div></h6>
                            <input type="number" class = "input-box" placeholder="Semester" name="semester" required>
                            <input type="date" class = "input-box" placeholder="Date of Birth" name = "dob" required>
                            <input type="tel" class = "input-box" placeholder="Phone Number" name = "phone" >
                            <button type="submit" class="submit-btn" name = "action" value="SignupStudent">Submit</button>
                        </form>
                        <h6>Already have an Account?</h6><button type="button" class = "btn" onclick="openLogin()">Log In</button></div>
                </div>
            </div>    
        </div>

        <script>
            var card= document.getElementById("card");
            function openSignUp(){
                card.style.transform = "rotateY(-180deg)";

            }
            function openLogin(){
                card.style.transform = "rotateY(0deg)";
                
            }

            function validate(){
                var password = document.getElementById("pwd").value;
                var confirmpassword = document.getElementById("confirmpwd").value;
                if(password != confirmpassword){
                    //alert("Password and confirm password do not match!");
                    document.getElementById("write").innerHTML = 'Password and confirm password do not match!';
                    return false;
                }
                return true;
            }

        </script>
    </body>
</html>