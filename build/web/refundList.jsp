<%-- 
    Document   : product
    Created on : Jun 15, 2023, 9:17:37 PM
    Author     : ADMIN
--%>

<%@page import="Utility.RefundDTO"%>
<%@page import="java.util.List"%>
<%@page import="DAO.DAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>BIFO</title>
        <!-- <link rel="shortcut icon" type="image/png" href="../assets/images/logos/favicon.png" /> -->
        <link rel="stylesheet" href="css/styles.min.css" />
        <link rel="stylesheet" href="css/product.css">
        <link rel="stylesheet" href="css/popup.css">
    </head>

    <body>
            <%  String role = (String) session.getAttribute("txtRole");
        if (role != null) {
             if (role.equals("ADMIN") || role.equals("STAFF")) {
              %>
        <%DAO sql = new DAO();
            String mail = "";
            List<RefundDTO> refunds = (List) session.getAttribute("SortedRefundList");
            if (refunds == null) {
                sql.getRefundList(mail);
                refunds = sql.getListRefund();
                session.setAttribute("SortedRefundList", refunds);
            }%>
        <!--  Body Wrapper -->
        <div class="page-wrapper" id="main-wrapper" data-layout="vertical" data-navbarbg="skin6" data-sidebartype="full"
             data-sidebar-position="fixed" data-header-position="fixed">
            <!-- Sidebar Start -->
            <aside class="left-sidebar">
                <!-- Sidebar scroll-->
                <div>
                    <div class="brand-logo d-flex align-items-center justify-content-between">
                        <a href="index.html" class="text-nowrap logo-img">
                            <img src="images/logo-removebg-preview.png" width="180" alt="" />
                        </a>
                        <div class="close-btn d-xl-none d-block sidebartoggler cursor-pointer" id="sidebarCollapse">
                            <i class="ti ti-x fs-8"></i>
                        </div>
                    </div>
                    <!-- Sidebar navigation-->
                    <nav class="sidebar-nav scroll-sidebar" data-simplebar="">
                        <ul id="sidebarnav">

                            <li class="nav-small-cap">
                                <i class="ti ti-dots nav-small-cap-icon fs-4"></i>
                                <span class="hide-menu">UI COMPONENTS</span>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link" href="add-product.jsp" aria-expanded="false">
                                    <span>
                                        <i class="ti ti-file-description"></i>
                                    </span>
                                    <span class="hide-menu">Add Products</span>
                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link" href="dele-update.jsp" aria-expanded="false">
                                    <span>
                                        <i class="ti ti-file-description"></i>
                                    </span>
                                    <span class="hide-menu">Update & Delete</span>
                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link" href="product.jsp" aria-expanded="false">
                                    <span>
                                        <i class="ti ti-box"></i>
                                    </span>
                                    <span class="hide-menu">Product</span>
                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link" href="orderList.jsp" aria-expanded="false">
                                    <span>
                                        <i class="ti ti-box"></i>
                                    </span>
                                    <span class="hide-menu">Order List</span>
                                </a>
                            <li class="sidebar-item">
                                <a class="sidebar-link" href="refundList.jsp" aria-expanded="false">
                                    <span>
                                        <i class="ti ti-box"></i>
                                    </span>
                                    <span class="hide-menu">Refund Request List</span>
                                </a>

                            </li>
                            </li>
                    </nav>
                    <!-- End Sidebar navigation -->
                </div>
                <!-- End Sidebar scroll-->
            </aside>
            <!--  Sidebar End -->

            <!--  Main wrapper -->
            <div class="body-wrapper">

                <div class="app-container">
                    <div class="app-content">
                        <div class="app-content-header">
                            <h1 class="app-content-headerText">Refund Request List</h1>
                            <button class="mode-switch" title="Switch Theme">
                                <svg class="moon" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" width="24" height="24" viewBox="0 0 24 24">
                                <defs></defs>
                                <path d="M21 12.79A9 9 0 1111.21 3 7 7 0 0021 12.79z"></path>
                                </svg>
                            </button>
                            <form action ="MainController" method="post">
                                <input type='submit' name='btAction' value='Logout' class="app-content-headerButton">
                            </form>
                        </div>
                        <div class="app-content-actions">
                            <div class="app-content-actions-wrapper">   
                                <div class="filter-button-wrapper" style="display:none">
                                    <button class="action-button filter jsFilter"><span>Filter</span><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-filter"><polygon points="22 3 2 3 10 12.46 10 19 14 21 14 12.46 22 3"/></svg></button>
                                    <div class="filter-menu">
                                        <label>Category</label>
                                        <select>
                                            <option>All Categories</option>
                                            <option>Bird</option>                 
                                            <option>Food</option>
                                        </select>
                                        <label>Status</label>
                                        <select>
                                            <option>All Status</option>
                                            <option>Active</option>
                                            <option>Disabled</option>
                                        </select>
                                        <div class="filter-menu-buttons">
                                            <button class="filter-button reset">
                                                Reset
                                            </button>
                                            <button class="filter-button apply">
                                                Apply
                                            </button>
                                        </div>
                                    </div>
                                </div>
                                <button class="action-button list active" title="List View">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-list"><line x1="8" y1="6" x2="21" y2="6"/><line x1="8" y1="12" x2="21" y2="12"/><line x1="8" y1="18" x2="21" y2="18"/><line x1="3" y1="6" x2="3.01" y2="6"/><line x1="3" y1="12" x2="3.01" y2="12"/><line x1="3" y1="18" x2="3.01" y2="18"/></svg>
                                </button>
                                <button class="action-button grid" title="Grid View">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-grid"><rect x="3" y="3" width="7" height="7"/><rect x="14" y="3" width="7" height="7"/><rect x="14" y="14" width="7" height="7"/><rect x="3" y="14" width="7" height="7"/></svg>
                                </button>
                            </div>
                        </div>
                        <div class="products-area-wrapper tableView">

                            <div class="products-header">
                                <div class="product-cell image">Refund ID</div>
                                <div class="product-cell sales">Order ID</div>
                                <div class="product-cell stock">Email</div>
                                <div class="product-cell price">Request Date</div>
                                <div class="product-cell price">Reason</div>
                                <div class="product-cell price">Action</div>


                            </div>


                            <%if (refunds != null && !refunds.isEmpty()) {
                                for (RefundDTO refund : refunds) {%>

                            <div class="products-row">
                                <button class="cell-more-button">
                                    <svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-more-vertical"><circle cx="12" cy="12" r="1"/><circle cx="12" cy="5" r="1"/><circle cx="12" cy="19" r="1"/></svg>
                                </button>
                                <div class="product-cell category"><span class="cell-label">Refund:</span><%=refund.getRefundID()%></div>
                                <div class="product-cell sales"><span class="cell-label">Order ID:</span><%=refund.getOrderID()%></div>
                                <div class="product-cell stock"><span class="cell-label">Mail:</span><%=refund.getMail()%></div>
                                <div class="product-cell price"><span class="cell-label">Date:</span><%=refund.getDate()%></div>
                                <div class="product-cell price"><span class="cell-label">Reason:</span><%=refund.getReason()%></div>
                                <div class="product-cell price"><span class="cell-label">Reason:</span><a href="#divOne" class="Button">Handle Request</a></div>
                            </div>      
                            <%}%>
                            <%}%>
                        </div>
                    </div>

                </div>
            </div>
            <div class="overlay" id="divOne">
                <div class="wrapper">
                    <h2>Please give us your feedback</h2>
                    <a href="#" class="close">&times;</a>
                    <div class="content">
                        <div class="popup">
                            <form action="MainController" method="post">
                                <label>Request ID</label>
                                <input type="text" name="RequestID" placeholder="Enter Request ID" required>
                                <label>Reply</label>
                                <textarea name="Reply" placeholder="Enter Reply"></textarea>
                                <input class="Button" type="submit" value="Reply" name="btAction"><br/><br/>
                                <input class="Button" type="submit" value="Ignore" name="btAction">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <script src="js/libs/jquery/dist/jquery.min.js"></script>
            <script src="js/libs/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
            <script src="js/sidebarmenu.js"></script>
            <script src="js/app.min.js"></script>
            <script src="js/libs/apexcharts/dist/apexcharts.min.js"></script>
            <script src="js/libs/simplebar/dist/simplebar.js"></script>
            <script src="js/dashboard.js"></script>
            <script src="js/admin.js"></script>
            <%if (refunds != null) {
                    refunds.clear();
                    session.removeAttribute("SortedRefundList");
                }
            %>
            <% }}else {
                response.sendRedirect("login.jsp");
                }%>
    </body>

</html>