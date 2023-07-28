<%-- 
    Document   : checkout
    Created on : Jun 18, 2023, 6:01:54 PM
    Author     : ADMIN
--%>

<%@page import="Utility.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- custom css file link  -->
        <link rel="stylesheet" href="css/check.css">



    </head>
    <body>
        <%         UserDTO userDTO = (UserDTO) session.getAttribute("User_info");
        %>

        <div class="container">

            <form action="MainController" method="POST">

                <div class="row">

                    <div class="col">
                        <%if (userDTO != null) {%>
                        <h3 class="title">Billing address</h3>

                        <div class="inputBox">
                            <span>Full name :</span>
                            <input type="text" name="txtUsername" placeholder="john deo" value="<%=userDTO.getUsername()%>">
                        </div>
                        <div class="inputBox">
                            <span>Email :</span>
                            <input type="email" name="txtMail" placeholder="example@example.com" value="<%=userDTO.getMail()%>"> 
                        </div>
                        <div class="inputBox">
                            <span>Address :</span>
                            <input type="text" name="txtAddress"placeholder="room - street - locality" value="<%=userDTO.getAddress()%>">
                        </div>
                        <div class="inputBox">
                            <span>Phone Number :</span>
                            <input type="text" name="txtPhoneNumber" value="<%=userDTO.getPhone_number()%>">
                        </div>
                        <%} else {%>
                        <h3 class="title">Billing address</h3>

                        <div class="inputBox">
                            <span>Full name :</span>
                            <input type="text" name="txtUsername" placeholder="john deo">
                        </div>
                        <div class="inputBox">
                            <span>Email :</span>
                            <input type="email" name="txtMail"placeholder="example@example.com">
                        </div>
                        <div class="inputBox">
                            <span>Address :</span>
                            <input type="text" name="txtAddress"placeholder="room - street - locality">
                        </div>
                        <div class="inputBox">
                            <span>Phone Number :</span>
                            <input type="text" name="txtPhoneNumber">
                        </div>
                        <%}%>

                    </div>

                    

                    <div class="col cart-col">

                        <!-- cart details container -->
                        <div id="products" class="cart-details">
                            <h3 class="title">Cart details</h3>
                            <ul id="productList"></ul>
                            <label for="total">Total:</label>
                            <input type="text" id="total" name="total" readonly>
                            <br/>
                            <label for="subtotal">Subtotal:</label>
                            <input type="text" id="subtotal" name="subtotal" readonly>
                             <label for="tax">Tax ( 2% of Subtotal ): </label>
                            <input type="text" id="tax" name="tax" readonly>
                        </div>

                    </div>
                       
                </div>

                <input id="clearButton" type="submit" name="btAction" value="Payment in Cash" class="submit-btn"> 
                <input id="clearButton2" type="submit" name="btAction" value="Pay by Paypal" class="submit-btn"> 
                <br/>
                <br/>
                <a href="ProductPage.jsp" class="submit-btn">Return</a>
                <a id="clearHref" href="ProductPage.jsp" class="submit-btn">Cancel</a>
            </form>

        </div>    
        <script>
            var products = JSON.parse(localStorage.getItem("products"));
            var productsDiv = document.getElementById("products");

            for (var i = 0; i < products.length; i++) {
                var product = products[i];

                var productDiv = document.createElement("div");
                var title = document.createElement("input");
                var img = document.createElement("img");
                var price = document.createElement("input");
                var quantity = document.createElement("input");

                title.type = "text";
                title.name = "title";
                title.value = product.title;
                title.readOnly = true;

                img.src = product.imgSrc;
                img.readonly = true;

                price.type = "text";
                price.name = "price";
                price.value = String(product.price);
                price.readOnly = true;
                
                quantity.type = "text";
                quantity.name = "quantity";
                quantity.value = String(product.quantity);
                quantity.readOnly = true;
                
                
                productDiv.appendChild(title);
                productDiv.appendChild(img);
                productDiv.appendChild(price);
                productDiv.appendChild(quantity);
                productsDiv.appendChild(productDiv);
            }
            var subtotal = localStorage.getItem("cartTotal");
            document.getElementById('total').value = "$" + subtotal*1.02 ;
            document.getElementById('subtotal').value = "$" + subtotal*1 ;
            document.getElementById('tax').value = "$" + subtotal*0.02 ;
        </script>
        
        <script>
            const payOnReceiveRadio = document.getElementById("pay-on-receive");
            const cardPaymentRadio = document.getElementById("card-payment");
            const cardDetailsDiv = document.getElementById("card-details");

// Hide card payment details initially
            cardDetailsDiv.style.display = "none";

// Add event listener for payment radio buttons
            payOnReceiveRadio.addEventListener("change", () => {
                if (payOnReceiveRadio.checked) {
                    cardDetailsDiv.style.display = "none";
                }
            });

            cardPaymentRadio.addEventListener("change", () => {
                if (cardPaymentRadio.checked) {
                    cardDetailsDiv.style.display = "block";
                } else {
                    cardDetailsDiv.style.display = "none";
                }
            });
        </script>
        <script>
            function clearLocalStorage() {
                // Clear the local storage
                localStorage.clear();
                subtotal = "";
            }

// Assuming you have a button in your HTML with the id "clearButton"
            var clearButton = document.getElementById("clearButton");
            clearButton.addEventListener("click", clearLocalStorage);
            var clearButton = document.getElementById("clearButton2");
            clearButton.addEventListener("click", clearLocalStorage);
            document.querySelector('#clearHref').addEventListener('click', function () {
                localStorage.clear();
                subtotal = "";
            });
        </script>
    </body>
</html>