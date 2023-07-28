<%-- 
    Document   : ProductPage
    Created on : Jun 7, 2023, 6:41:19 PM
    Author     : ADMIN
--%>

<%@page import="Utility.BirdDTO"%>
<%@page import="java.util.List"%>
<%@page import="Utility.ProductListDTO"%>
<%@page import="DAO.DAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>BIFO Shop</title>
        <!--Link to CSS-->
        <link rel="stylesheet" href="css/ProductPage.css" />
        <link rel="stylesheet" href="css/bootstrap.css">
        <!--Link to Box Icons-->
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    </head>
    '
    <body>
        <!-- Java Scriplet ->
        <% DAO sql = new DAO();
            String bird_name = "";
            String mail = (String) session.getAttribute("txtMail");
            List<BirdDTO> birdLists = (List) session.getAttribute("SortBird");
            if (birdLists == null){
            sql.searchBird(bird_name);
            birdLists = sql.getListBird();
            }
        %>
        <!-- Java Scriplet -->



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
                    <li><i class='bx bxs-cart-alt' id="cart-icon"></i></li>
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
        <!-- Shop -->
        <section class="shop container">
            <h2 class="section-title">Birds</h2>
            <!-- Content -->
            <div>
                <form action="MainController" method="post">
                    <div class="search_wrap search_wrap_1">
                        <div class="search_box">
                            <input type="text" value="" name="txtBirdName" class="input" placeholder="search...">
                            <input type="submit" name="btAction" value="Search Bird Name" class="searchButton"/>
                        </div>
                    </div>
                   
                </form>
            </div>


            <div class="shop-content">
                <div class="row justify-content-center">
                    <%if (birdLists != null && !birdLists.isEmpty()) {
                              for (BirdDTO birdList : birdLists) {%>

                    <div class="col-md-4 col-sm-6 mb-4">
                        <!-- Box 1 -->
                        <div class="product-box">
                            <img src="<%= birdList.getBird_images()%>" alt="img" class="product-img">
                            <h2 class="product-title"><%= birdList.getBird_name()%></h2>
                            <br/>
                            <p class ="price">Food : <%= birdList.getProduct_name()%></p>
                            <p class = "product-text">
                                Species : <%=birdList.getSpecies()%>
                            </p>
                            <a href="BirdDetail?txtBirdId=<%=birdList.getBird_id()%>">
                                <h2 class="learn-more">Learn More</h2>
                            </a>
                           
                        </div>
                    </div>
                    <%}%>
                    <%}%>
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
                            <a href="" class="btn-offer">See More &#8594;</a>
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
                </div>
            </div>
        </div>
        <!-- Footer -->
        <div class="footer">
            <div class="container">
                <div class="row">
                    <div class="col-md-3 footer-content">
                        <h3>Download Our App</h3>
                        <p>Download App For Android and ios mobile phone.</p>
                        <div class="app-logo">
                            <img src="images/app-store.png" alt="">
                            <img src="images/play-store.png" alt="">
                        </div>
                    </div>
                    <div class="col-md-3 footer-content">
                        <img src="images/logo-removebg-preview.png">
                        <p>Our Purpose Is To Sustainably Make the Pleasure and Benefits of Bird Food to Many.</p>
                    </div>
                    <div class="col-md-3 footer-content">
                        <h3>Useful Links</h3>
                        <ul>
                            <li>Coupon</li>
                            <li>Blog Post</li>
                            <li>Return Policy</li>
                            <li>Join Affiliate</li>
                        </ul>
                    </div>
                    <div class="col-md-3 footer-content">
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
         <%if (birdLists != null){
        birdLists.clear();
        session.removeAttribute("SortBird");
    }
    %>
        <!-- Link to JS -->
        <script src="js/main.js"></script>
    </body>
</html>