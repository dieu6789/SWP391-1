<%-- 
    Document   : staff
    Created on : May 30, 2023, 11:17:00 AM
    Author     : ADMIN
--%>

<%@page import="java.util.List"%>
<%@page import="DAO.DAO"%>
<%@page import="Utility.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>BIFO</title>
        <link rel="stylesheet" href="css/styles.min.css" />
        <link rel="stylesheet" href="css/accountcontroller.css">
    </head>
    <body>
        <%  String role = (String) session.getAttribute("txtRole");
            if (role != null) {
                if (role.equals("ADMIN")) {
        %>
        <%
            DAO sql = new DAO();
            String mail = "";
            List<UserDTO> userDTO = (List) session.getAttribute("SortedAccountList");
            if (userDTO == null) {
                sql.searchAllUser(mail);
                userDTO = sql.getListAccount();
                session.setAttribute("SortedAccountList", userDTO);
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
                                <span class="hide-menu">Home</span>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link" href="AdminDashboard.jsp" aria-expanded="false">
                                    <span>
                                        <i class="ti ti-layout-dashboard"></i>
                                    </span>
                                    <span class="hide-menu">Dashboard</span>
                                </a>
                            </li>
                            <li class="nav-small-cap">
                                <i class="ti ti-dots nav-small-cap-icon fs-4"></i>
                                <span class="hide-menu">UI COMPONENTS</span>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link" href="AccountList.jsp" aria-expanded="false">
                                    <span>
                                        <i class="ti ti-user"></i>
                                    </span>
                                    <span class="hide-menu">Account List</span>
                                </a>
                            </li>
                            <li class="sidebar-item">
                                <a class="sidebar-link" href="Add-DeleAccount.jsp" aria-expanded="false">
                                    <span>
                                        <i class="ti ti-file-description"></i>
                                    </span>
                                    <span class="hide-menu">Add Staff Account</span>
                                </a>
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
                            <h1 class="app-content-headerText">Account List</h1>
                            <button class="mode-switch" title="Switch Theme">
                                <svg class="moon" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" width="24" height="24" viewBox="0 0 24 24">
                                <defs></defs>
                                <path d="M21 12.79A9 9 0 1111.21 3 7 7 0 0021 12.79z"></path>
                                </svg>
                            </button>
                            <!-- <button class="app-content-headerButton">Add Product</button> -->
                        </div>
                        <div class="app-content-actions">
                            <form action ="MainController" method="Post">
                                <input class="search-bar" placeholder="Search by mail" type="text" name="txtMail">
                                <input type="submit" name="btAction" value="Search Account" class ="Button">
                            </form>
                            <div class="app-content-actions-wrapper">
                                <div class="filter-button-wrapper" style="display:none;">
                                    <button class="action-button filter jsFilter"><span>Filter</span><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-filter"><polygon points="22 3 2 3 10 12.46 10 19 14 21 14 12.46 22 3"/></svg></button>
                                    <div class="filter-menu">
                                        <label>Category</label>
                                        <select>
                                            <option>All</option>
                                            <option>Staff</option>                 
                                            <option>User</option>
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
                                <div class="product-cell image">Account ID</div>
                                <div class="product-cell category">Username</div>
                                <div class="product-cell status-cell">Email</div>
                                <div class="product-cell sales">Address</div>
                                <div class="product-cell stock">Phone Number</div>
                                <div class="product-cell stock">Role</div>
                                <div class="product-cell stock"></div>
                            </div>
                            <%if (userDTO != null && !userDTO.isEmpty()) {
                                    for (UserDTO users : userDTO) {%>
                            <% if (!users.getRole().equals("ADMIN")) {%>
                            <div 
                                class="products-row">
                                <div class="product-cell category"><span class="cell-label">Account ID:</span > <%= users.getUser_id()%></div>
                                <div class="product-cell category"><span class="cell-label">Username:</span><%=users.getUsername()%></div>
                                <div class="product-cell sales"><span class="cell-label">Email:</span><%=users.getMail()%></div>
                                <div class="product-cell sales"><span class="cell-label">Address:</span><%=users.getAddress()%></div>
                                <div class="product-cell stock"><span class="cell-label">Phone Number:</span><%=users.getPhone_number()%></div>
                                <div class="product-cell price"><span class="cell-label">Role:</span><%=users.getRole()%></div>
                                <div class="product-cell price"><span class="cell-label">Delete:</span> <span class="cell-label"></span>
                                    <% if (users.getRole().equals("STAFF")) {%>
                                    <button id="order-status" class="Button"><a href = "DeleteStaff?txtUserID=<%=users.getUser_id()%>">Delete</a></button>
                                    <%}%>
                                </div>
                            </div>
                            <%}
                                    }
                                }%>
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
            <%if (userDTO != null) {
                    userDTO.clear();
                    session.removeAttribute("SortedAccountList");
                }
            %>
            <% }
                } else {
                    response.sendRedirect("login.jsp");
            }%>
    </body>
</html>
