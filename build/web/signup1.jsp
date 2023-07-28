<%-- 
    Document   : signup1
    Created on : Jun 3, 2023, 8:05:00 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Register Form</title>
    <link rel="stylesheet" href="css/signup_1.css" />
    </head>
    <body>
        <div class="container">
          <h1>Register</h1>
          <form action="MainController" method ="POST">
            
            <div class="form-control">
                <input type="text" id="username" class="input" name="txtUsername" required/>
              <label for="" class="label">Username</label>
              <span></span>
              <small></small>
            </div>

            <div class="form-control">
              <input type="email" id="email" class="input" name="txtMail"  required/>
              <label for="" class="label">Email</label>
              <span></span>
              <small></small>
            </div>

            <div class="form-control">
              <input type="password" id="password" class="input" name="txtPassword" required=/>
              <label for="" class="label">Password</label>
              <span></span>
              <small></small>
            </div>

            <div class="form-control">
              <input type="password" id="password2" class="input" name="txtConfirmPassword"  required/>
              <label for="" class="label">Confirm Password</label>
              <span></span>
              <small></small>
            </div>
              <div class="form-control">
              <input type="text" id="password2" class="input" name="txtPhone"  required/>
              <label for="" class="label">Phone Number</label>
              <span></span>
              <small></small>
            </div>
              <div class="form-control">
              <input type="text" id="password2" class="input" name="txtAddress" required/>
              <label for="" class="label">Address</label>
              <span></span>
              <small></small>
            </div>
            <input type="submit" class="submitBtn" name="btAction" value="Sign Up">
             
            <p>Want to return to the homepage? <a href="index.html">Press Here</a></p>
          </form>
           
        </div>
    <script>
    <% String checkMail = (String) session.getAttribute("ExistMail");
        if (checkMail != null && checkMail.equals("true") ){  %>
    alert("Mail Existed ");
     <%} session.removeAttribute("ExistMail");%>
   </script>
    <script>
    <% String wrongConfirmPassword = (String) session.getAttribute("WrongConfirmPassword");
        if (wrongConfirmPassword != null && wrongConfirmPassword.equals("true") ){  %>
    alert("The password does not match to Confirm Password ");
     <%} session.removeAttribute("WrongConfirmPassword");%>
   </script>
        <script src="app.js"></script>
      </body>
</html>