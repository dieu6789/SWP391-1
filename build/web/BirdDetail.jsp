<%-- 
    Document   : ProductDetail
    Created on : Jun 12, 2023, 5:00:41 PM
    Author     : ADMIN
--%>

<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="Utility.BirdDTO"%>
<%@page import="Utility.FeedbackDTO"%>
<%@page import="Utility.ProductListDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>BIFO Shop</title>
        <!--Link to CSS-->
        <link rel="stylesheet" href="css/ProductPage.css" />
        <link rel="stylesheet" href="css/bootstrap.css">
        <!--Link to Box Icons-->
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    </head>

    <body>
        <% List<BirdDTO> birdList = (List) session.getAttribute("SortedBird");
            BirdDTO birdDetail = birdList.get(0);
              String mail = (String) session.getAttribute("txtMail");
        %>
        <!-- Header -->
        <header>
            <!-- Nav -->
            <div class="nav container">
                <img src="images/logo-removebg-preview.png" href="#" class="logo"></img>
                <!-- Cart-Icon -->
                <ul>
                    <li><a href="index.html" class="header-title">Home</a></li>
                    <li><a href="ProductPage.jsp" class="header-title">Product</a></li>
                    <li><a href="bird.jsp" class="header-title">Bird</a></li>
                        <% if (mail == null) {%>
                    <li><a href="login.jsp" class="header-title">Login</a></li>
                        <%} else {%>
                    <li><a href="user_info.jsp" class="header-title">Profile</a></li>
                        <%}%>
                    <li><i class='bx bxs-cart-alt' id="cart-icon" data-quantity="0"></i></li>
                </ul>
                <!-- Cart -->
                <div class="cart">
                    <h2 class="cart-title">Your cart</h2>
                    <!-- Content -->
                    <div class="cart-content">

                    </div>
                    <!-- Total -->
                    <div class="total">
                        <div class="total-title">Total</div>
                        <div class="total-price">$0</div>
                    </div>
                    <!-- Buy Button -->
                    <button type="button" class="btn-buy">Buy Now</button>
                    <!-- Cart Close -->
                    <i class='bx bx-x' id="close-cart"></i>
                </div>
            </div>
        </header>

        <!-- Single Product Details  -->
        <section class="shop container single-product">
            <div id="Details" class="small-container">
                <div  class="row">
                    <div class="col-6">
                        <img src="<%= birdDetail.getBird_images()%>">
                    </div>
                    <div class="col-6">
                        <p>Bird / <%=birdDetail.getBird_name()%></p>
                        <h3>Species : <%=birdDetail.getSpecies()%></h3>
                        <h3>Food / Meal : <%=birdDetail.getDesc_order()%></h3>
                        <h3>Suggest Product : <%=birdDetail.getProduct_name()%></h3>
                         <a href="ProductDetail?keyword=<%=URLEncoder.encode(birdDetail.getProduct_name(), StandardCharsets.UTF_8.toString()) %>">
                                <h2 class="learn-more">Go To Product</h2>
                            </a>
                    </div>
                </div>
            </div>
        </section>
        <!-- Offer -->
        <div class="offer">
            <div class="small-container">
                <div class="row">
                    <div class="col-6">
                        <img src="images/body_bird_food1/bird_food5.png" class="offer-img">
                    </div>
                    <div class="col-6 offer-content">
                        <p>Exclusively Available on BIFO Shop</p>
                        <h1>ZUPREEM PURE FUN</h1>
                        <small>
                            Our ZuPreem Pure Fun is 39.9% more nutrious than other product which can only found on BIFO Shop
                            <p>Want to see more information about the product?</p>
                            <a href="" class="btn-productpage">See More &#8594;</a>
                        </small>
                    </div>
                </div>
            </div>
        </div>
        <!-- Brands -->
        <div class="brands">
            <div class="small-container">
                <div class="row">
                    <img src="images/paypal.png" class="brands">
                    <img src="images/visa.png" class="brands">
                    <img src="images/mastercard.png" class="brands">
                    <img src="images/coca-cola.png" class="brands">
                </div>
            </div>
        </div>
        <!-- Footer -->
        <div class="footer">
            <div class="container">
                <div class="row">
                    <div class="col-3 footer-content">
                        <h3>Download Our App</h3>
                        <p>Download App For Android and ios mobile phone.</p>
                        <div class="app-logo">
                            <img src="images/app-store.png" alt="">
                            <img src="images/play-store.png" alt="">
                        </div>
                    </div>
                    <div class="col-3 footer-content">
                        <img src="images/logo-removebg-preview.png">
                        <p>Our Purpose Is To Sustainably Make the Pleasure and Benefits of Bird Food to Many.</p>
                    </div>
                    <div class="col-3 footer-content">
                        <h3>Useful Links</h3>
                        <ul>
                            <li>Coupon</li>
                            <li>Blog Post</li>
                            <li>Return Policy</li>
                            <li>Join Affiliate</li>
                        </ul>
                    </div>
                    <div class="col-3 footer-content">
                        <h3>Follow Us</h3>
                        <ul>
                            <li>Facebook</li>
                            <li>Twitter</li>
                            <li>Instagram</li>
                            <li>Youtube</li>
                        </ul>
                    </div>
                </div>
                <hr>
                <p class="copyright">Copyright 2023 - BIFO Shop</p>
            </div>
        </div>
        <script src="js/main.js"></script>
    </body>

</html>
