<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="Utility.FavoriteDTO"%>
<%@page import="Utility.OrderDTO"%>
<%@page import="java.util.List"%>
<%@page import="Utility.UserDTO"%>
<%@page import="DAO.DAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />

        <title>Customer Info</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
            rel="stylesheet"
            />
        <link rel="stylesheet" href="css/user.css" />
        <link rel="stylesheet" href="css/popup.css" /><link rel="stylesheet" href="css/nicepage.css" media="screen">
        <link rel="stylesheet" href="css/ProductPage.css" media="screen">
        <script class="u-script" type="text/javascript" src="js/jquery.js" defer=""></script>
        <script class="u-script" type="text/javascript" src="js/nicepage.js" defer=""></script>
        <meta name="generator" content="Nicepage 5.10.10, nicepage.com">
        <link id="u-theme-google-font" rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:100,100i,300,300i,400,400i,500,500i,700,700i,900,900i|Open+Sans:300,300i,400,400i,500,500i,600,600i,700,700i,800,800i">


        <script type="application/ld+json">{
            "@context": "http://schema.org",
            "@type": "Organization",
            "name": "Site1",
            "logo": "images/logo-removebg-preview.png"
            }</script>
        <meta name="theme-color" content="#478ac9">
        <meta property="og:title" content="productList">
        <meta property="og:description" content="">
        <meta property="og:type" content="website">
        <meta data-intl-tel-input-cdn-path="intlTelInput/"></head>
</head>

<body> <header>
        <!-- Nav -->

        <div class="nav container">
            <img src="images/logo-removebg-preview.png" href="#" class="logo"></img>
            <!-- Cart-Icon -->
            <ul>
                <li><a href="index.html" class="header-title">Home</a></li>
                <li><a href="ProductPage.jsp" class="header-title">Product</a></li>
                <li><a href="bird.jsp" class="header-title">Bird</a></li>
                <li><a href="user_info.jsp" class="header-title">Profile</a></li>

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
    <%
        DAO sql = new DAO();
        UserDTO userDTO = (UserDTO) session.getAttribute("User_info");
        if (userDTO != null) {
    %>
    <!--dang link-->
    <div class="container">
        <div class="main-body">


            <!-- Add the tab navigation -->
            <section>     
                <ul class="nav nav-tabs mb-4" id="myTab" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" id="user-info-tab" data-toggle="tab" href="#user-info"
                           role="tab" aria-controls="user-info" aria-selected="true">User Information</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" id="favorite-products-tab" data-toggle="tab" href="#favorite-products"
                           role="tab" aria-controls="favorite-products" aria-selected="false">Favorite Product List</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="order-history-tab" data-toggle="tab" href="#order-history" role="tab"
                           aria-controls="order-history" aria-selected="false">Order History</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="refund-tab" data-toggle="tab" href="#refund" role="tab"
                           aria-controls="Refund-tab" aria-selected="false">Refund</a>
                    </li>

                </ul>
            </section>  
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="user-info" role="tabpanel"
                     aria-labelledby="user-info-tab">

                    <div class="row gutters-sm">
                        <div class="col-md-4 mb-3">
                            <div class="card">
                                <div class="card-body">
                                    <div class="d-flex flex-column align-items-center text-center">
                                        <div class="mt-3">
                                            <p class="text-secondary mb-1"><%= userDTO.getRole()%></p>
                                            <p class="text-muted font-size-sm">UserID : <%= userDTO.getUser_id()%>
                                            </p>
                                            <br/>
                                            <a class="btn btn-primary" href="UpdatePassword.jsp">Update Password</a>
                                            <form action="MainController" method="post">
                                                <br/>
                                                <input class="btn btn-success btn-sm mr-2"
                                                       type="submit" name="btAction" value="Logout" />
                                            </form>
                                            <br><br/>
                                            
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>

                        <div class="col-md-8">

                            <div class="card mb-3">
                                <form action="MainController" method="POST">
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col-sm-3">
                                                <h6 class="mb-0">Username</h6>
                                            </div>
                                            <div class="col-sm-9 text-secondary"><%= userDTO.getUsername()%></div>
                                        </div>
                                        <hr />

                                        <div class="row">
                                            <div class="col-sm-3">
                                                <h6 class="mb-0">Email</h6>
                                            </div>
                                            <div class="col-sm-9 text-secondary">
                                                <a href="/cdn-cgi/l/email-protection"
                                                   class="cf_email"
                                                   data-cfemail="fa9c938aba908f91978f92d49b96"><%= userDTO.getMail()%></a>
                                            </div>
                                        </div>
                                        <hr />
                                        <div class="row">
                                            <div class="col-sm-3">
                                                <h6 class="mb-0">Phone Number</h6>
                                            </div>
                                            <div class="col-sm-9 text-secondary">
                                                <%= userDTO.getPhone_number()%>

                                                <input type="number" name="txtPhoneNumber"
                                                       class="form-control form-control-sm mb-2"
                                                       value="<%= userDTO.getPhone_number()%>" required
                                                       style="display:none;"x>

                                            </div>
                                        </div>
                                        <hr />
                                        <div class="row">
                                            <div class="col-sm-3">
                                                <h6 class="mb-0">Address</h6>
                                            </div>
                                            <div class="col-sm-9 text-secondary">
                                                <%= userDTO.getAddress()%>

                                                <input type="text" name="txtAddress"
                                                       class="form-control form-control-sm mb-2"
                                                       value="<%= userDTO.getAddress()%>" required
                                                       style="display:none;">

                                            </div>
                                        </div>
                                        <hr />
                                        <div class="row">
                                            <div class="col-sm-12">
                                                <button type="button" id="Edit" class="btn btn-info" onclick="editInfo()">Edit</button>
                                                <button type="button" id="Cancel" class="btn btn-secondary btn-sm" onclick="cancelEdit()"
                                                        style="display:none;">Cancel</button>
                                                <br /><br />
                                                <input id="save" class="btn btn-success btn-sm mr-2"
                                                       style="display:none;" type="submit" name="btAction" value="Save" />
                                            </div>
                                        </div>
                                    </div>
                                </form>

                            </div>

                        </div>
                    </div>
                </div>

                <!-- Add the order history tab content -->
                <div class="tab-pane fade" id="order-history" role="tabpanel" aria-labelledby="order-history-tab">
                    <div class="row gutters-sm">
                        <div class="col-sm-12 mb-3">
                            <div class="card h-100">
                                <div class="card-body">
                                    <h5 class="card-title">Order History</h5>
                                    <div class="table-responsive">
                                        <table class="table table-hover">

                                            <thead>
                                                <tr>
                                                    <th>Order ID</th>
                                                    <th>Date</th>
                                                    <th>Total Amount</th>
                                                    <th>Status</th>
                                                    <th>Feedback</th>
                                                    <th>Cancel</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%  sql.orderHistory(userDTO.getUser_id());
                                                    List<OrderDTO> orders = sql.getListOrder();
                                                    if (orders != null && !orders.isEmpty()) {
                                                        for (OrderDTO order : orders) {%>
                                                <tr>

                                                    <td><%= order.getOrder_id()%></td>
                                                    <td><%= order.getOrder_date()%></td>
                                                    <td><%= order.getTotal_price()%>.000 VND</td>
                                                    <td><%= order.getStatus()%></td>
                                                    <td>
                                                        <% if (order.getStatus().equals("CONFIRMED")) {%>

                                                        <form action="MainController" method="post">
                                                            <input type="hidden" name="txtOrderID" value="<%=order.getOrder_id()%>">
                                                            <input type="submit" name="btAction" value="Feedback" class = "btn btn-primary">   
                                                        </form>

                                                        <%}%>
                                                    </td>
                                                    <td>
                                                        <% if (order.getStatus().equals("WAITING")) {%>

                                                        <form action="MainController" method="post">
                                                            <input type="hidden" name="txtOrderID" value="<%=order.getOrder_id()%>">
                                                            <input type="submit" name="btAction" value="Cancel" class = "btn btn-primary">

                                                        </form>

                                                        <%}%>
                                                    </td>                                              
                                                </tr>
                                                <% }
                                                } else { %>
                                                <tr>
                                                    <td colspan="4" class="text-center">No orders found.</td>
                                                </tr>
                                                <%}%>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Add the favorite products tab content -->
                <div class="tab-pane fade" id="favorite-products" role="tabpanel"
                     aria-labelledby="favorite-products-tab">
                    <div class="row gutters-sm">
                        <div class="col-sm-12 mb-3">
                            <div class="card h-100">
                                <div class="card-body">
                                    <h5 class="card-title">Favorite Products</h5>
                                    <div class="table-responsive">
                                        <table class="table table-hover">
                                            <thead>
                                                <tr>
                                                    <th>No.</th>
                                                    <th>Product ID</th>
                                                    <th>Product Name</th>
                                                    <th>Price</th>
                                                    <th></th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <% int count = 0;
                                                    sql.searchFavoriteProduct(userDTO.getUser_id());
                                                    List<FavoriteDTO> favorites = sql.getListFavorite();
                                                    if (favorites != null && !favorites.isEmpty()) {
                                                        for (FavoriteDTO favorite : favorites) {%>
                                                <tr>
                                                    <td><%= ++count%></td>
                                                    <td><%= favorite.getProduct_id()%></td>
                                                    <td> <a href="ProductDetail?keyword=<%=URLEncoder.encode(favorite.getProduct_name(), StandardCharsets.UTF_8.toString()) %>"><%= favorite.getProduct_name()%></a></td>
                                                    <td><%= favorite.getPrice()%>.000 VND</td>

                                                    <td>
                                                        <form action="MainController" method="post">
                                                            <input type="hidden" name="txtUserID" value="<%= userDTO.getUser_id()%>">
                                                            <input type="hidden" name="txtProductID" value="<%= favorite.getProduct_id()%>"/>
                                                            <input class="btn btn-success btn-sm mr-2" type="submit" name="btAction" value="Remove" />
                                                        </form>
                                                    </td>
                                                </tr>
                                                <% }
                                                } else { %>
                                                <tr>
                                                    <td colspan="4" class="text-center">No favorite product.</td>
                                                </tr>
                                                <%}%>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="tab-pane fade" id="refund" role="tabpanel"
                     aria-labelledby="Refund-tab">
                    <div class="row gutters-sm">
                        <div class="col-sm-12 mb-3">
                            <div class="card h-100">
                                <div class="card-body">
                                    <h5 class="card-title">Refund Order</h5>
                                    <div class="content">
                                        <h3>Reply will be sent to your Email</h3>
                                        <p>Notice : Only confirmed orders will be considered for refund</p>
                                        <br/>
                                        <form action="MainController" method="post">
                                            <label>Email : <%=userDTO.getMail()%></label>
                                            <input type="hidden" name="txtMail" value="<%=userDTO.getMail()%>">
                                            <br/>
                                            <label>Order ID</label>
                                            <input type="text" name="txtOrderID" min="0" step="1" placeholder="Please enter your Order ID" required>
                                            <label>Refund Reason</label>
                                            <textarea name="Reason"placeholder="Please let us know your refund reason..." required></textarea>
                                            <input class="SendRequest"type="submit" value="Send Request" name="btAction">
                                        </form>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script data-cfasync="false"
    src="/cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script>
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript">
                                                    // Function to show the form for editing phone number and hide the Edit button
                                                    function editInfo() {
                                                        document.getElementsByName("txtPhoneNumber")[0].style.display = "block";
                                                        document.getElementsByName("txtAddress")[0].style.display = "block";
                                                        document.getElementById("Edit").style.display = "none";
                                                        document.getElementById("Cancel").style.display = "inline-block";
                                                        document.querySelector('input[type="submit"][name="btAction"][value="Save"]').style.display = "inline-block";
                                                    }

                                                    // Function to cancel editing of phone number and show the Edit button
                                                    function cancelEdit() {
                                                        document.getElementsByName("txtPhoneNumber")[0].value = "<%= userDTO.getPhone_number()%>";
                                                        document.getElementsByName("txtAddress")[0].value = "<%= userDTO.getAddress()%>";
                                                        document.getElementsByName("txtPhoneNumber")[0].style.display = "none";
                                                        document.getElementsByName("txtAddress")[0].style.display = "none";
                                                         document.getElementById("Edit").style.display = "block";
                                                         document.getElementById("Cancel").style.display = "none";
                                                        document.querySelector('input[type="submit"][name="btAction"][value="Save"]').style.display = "none";
                                                    }

                                                    // Function to display the Save button on form submit
                                                    $("form").submit(function () {
                                                        $("#save").css("display", "inline-block");
                                                    });
    </script>
    <%} else {
            response.sendRedirect("login.jsp");
        }%>
        <script>
            <% String checkOrderStatus = (String) session.getAttribute("CheckOrderStatus");
        if (checkOrderStatus != null && checkOrderStatus.equals("FALSE") ){  %>
    alert("The Status of Order is not CONFIRMED or your Order ID is wrong");
     <%} session.removeAttribute("CheckOrderStatus");%>
        </script>
</body>

</html>
