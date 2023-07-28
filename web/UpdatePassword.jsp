<%-- 
    Document   : UpdatePassword
    Created on : Jun 2, 2023, 11:00:54 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Update Password</title>
    <link rel="stylesheet" href="css/VerifyPage.css" />
    </head>
    <body>
        <% String mail = (String) session.getAttribute("txtMail");
        if (mail != null){ %>
      <div class="container">
            <header></header>
            <h4>Update Password</h4>
            <p>Please enter your new password</p>
            <form action="MainController" method="POST">
                <div class="input-field">
                    <input id ="Reset" type="password" name="txtNewPassword" placeholder="New Password"/><br/>
                    <input id ="Reset" type="password" name="txtConfirmNewPassword" placeholder="Confirm New Password"/>
                </div>
                <input id="button" type="submit" name="btAction" value="Update Password">
            </form>
        </div>
    <%} else {
    response.sendRedirect("login.jsp");
}%>
        <script src="app.js"></script>
      </body>
</html>
