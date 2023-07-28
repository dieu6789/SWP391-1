<%-- 
    Document   : login.jsp
    Created on : May 29, 2023, 2:54:41 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1">
    <title>BIFO shop</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="css/style_login.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
</head>
<body class="main-layout inner_page">
    <%  String role = (String) session.getAttribute("txtRole");
        if (role != null) {
            if (role.equals("CUSTOMER")) {
                response.sendRedirect("user_info.jsp");
            } else if (role.equals("ADMIN")) {
                response.sendRedirect("AdminDashboard.jsp");
            } else if (role.equals("STAFF")) {
                response.sendRedirect("product.jsp");
            }
        } else {%>
<html lang="en">
    <!-- loader  -->
    <div class="loader_bg">
        <div class="loader"><img src="images/loading.gif" alt="" /></div>
    </div>
    <!-- end loader -->
    <div id="mySidepanel" class="sidepanel">
        <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">×</a>
        <a href="index.html">Home</a>
        <a class="active" href="about.html">About</a>
        <a href="birds.html">Our products</a>
        <a href="items_page.html">Shop</a>
        <a href="contact.html">Contact</a>
    </div>
    <!-- header -->
    <header>
        <!-- header inner -->
        <div class="head-top">
            <div class="container-fluid" style="width: 100%; padding-right: 30px; padding-left: 30px; margin-top: auto; margin-bottom: auto;">
                <div class="row">
                    <div class="col-sm-3" style="padding: 0;">
                        <div class="logo">
                            <a href="index.html"><img src="images/logo-removebg-preview.png" /></a>
                        </div>
                    </div>
                    <div class="col-sm-9" style="padding: 0; margin-top: 35px;">
                        <ul class="email text_align_right">
                            <li class="d_none"><i class="fa fa-search" style="cursor: pointer;" aria-hidden="true"></i></li>
                            <li> <button class="openbtn" onclick="openNav()"><img src="images/menu_btn.png"></button></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </header>
    <!--end header-->
    <!--Loginform-->
    <form action="MainController" method="POST">
        <div class="box-form">
            <div class="left">
                <div class="overlay">
                    <h1>Welcome</h1>
                    <span>
                        <p>Login with social media</p>
                        <a href="#"><i class="fa fa-facebook" aria-hidden="true">Login with Facebook</i></a>
                        <a href="#"><i class="fa fa-twitter" aria-hidden="true"></i> Login with Twitter</a>
                    </span>
                </div>
            </div>


            <div class="right">
                <h5>Login</h5>
                <p>Don't have an account? <a href="signup1.jsp">Create Your Account</a> it takes less than a minute</p>
                <div class="inputs">
                    <input type="text" name="txtMail" placeholder="Mail" required>
                    <br>
                    <input type="password" name="txtPassword" placeholder="Password" required>
                </div>

                <br><br>


                <div class="remember-me--forget-password">

                    <label>
                        <input type="checkbox" checked="checked" name="item"/>
                        <span class="text-checkbox">Remember me</span>
                    </label>

                    <a href="ResetPassword.jsp">Forget password?</a>
                </div>
                <br>

                <input id="LoginButton" type="submit" name="btAction" value="Login">
                <p>Want to return to the homepage? <a href="index.html">Press Here</a></p>
            </div>        
        </div>
    </form>
    <script>
        <% String isValidLogin = (String) session.getAttribute("ValidLogin");
        if (isValidLogin != null && isValidLogin.equals("false")) {  %>
        alert("Invalid username or password");
        <% } session.removeAttribute("ValidLogin"); %>
    </script>
    <!--end loginform-->
    <!-- Javascript files-->
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
    <script src="js/jquery-3.0.0.min.js"></script>
    <script src="js/custom.js"></script>
    <script>
    </script>
    <%}%>
</body>

</html>