<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Verification Page</title>
        <link rel="stylesheet" href="css/VerifyPage.css"/>
    </head>

    <body>
        <%

            String status = (String) session.getAttribute("Status");
            if (status != null && status.equals("Verify")) {
                
        %>
        <div class="container">
            <header></header>
            <h4>Enter Verify Code</h4>
            <p>We have sent you verfication code via Email</p>
            <form action="MainController" method="POST">
                <div class="input-field">
                    <input type="number" name="code" />
                </div>
                <input id="button" type="submit" name="btAction" value="Verify"/>
            </form>
            <p>Cancel the registration <br> <a href="index.html">Return to homepage</a></p>
        </div>
        <% } else{%>
<%response.sendRedirect("index.html");}%>       
</body>
</html>