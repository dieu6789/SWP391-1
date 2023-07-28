<%-- 
    Document   : ResetPassword
    Created on : Jun 5, 2023, 2:45:35 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Password</title>
        <link rel="stylesheet" href="css/VerifyPage.css"/>
    </head>
    <body>
             <% String status = (String) session.getAttribute("Status");
                 if (status == null) { %>
               <div class="container">
            <header></header>
            <h4>Verification Page</h4>
            <p>Please enter your email to receive code for resetting password</p>
            <form action="MainController" method="POST">
                <div class="input-field">
                    <input id ="Reset" type="text" name="txtMail" placeholder="Email"/>
                </div>
                <input id="button" type="submit" name="btAction" value="Reset">
            </form>
        </div>
        <%}%>
        <%if (status != null && status.equals("ForgetPassword")) {
            session.removeAttribute("ForgetPassword");
        %>
        <div class="container">
            <header></header>
            <h4>Enter Verify Code</h4>
            <p>We have sent you verification code via Email. Please Enter code and new password</p>
            <form action="MainController" method="POST">
                <div class="input-field">
                    <input type="number" name="code" placeholder="Verify Code "/><br/>
                    <input type="password" name="txtPassword" placeholder="New Password"/>
                </div>
                <input id="button" type="submit" name="btAction" value="Verify">
            </form>
        </div>

    <%}%>
    </body>
</html>
