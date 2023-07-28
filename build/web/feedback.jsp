<%-- 
    Document   : feedback
    Created on : Jul 10, 2023, 11:14:40 PM
    Author     : ADMIN
--%>

<%@page import="java.util.List"%>
<%@page import="Utility.GetProductToFeedbackDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Feedback Page</title>
        <link rel="stylesheet" href="css/feedback.css">
    </head>
    <% List<GetProductToFeedbackDTO> dtos = (List<GetProductToFeedbackDTO>) session.getAttribute("Feedback");%>
    <body>
        <div class="container">
            <h4>Feedback for Products</h4>
            <form action="MainController" method="Post">
                <div class="product-row">
                    <% if (dtos != null && !dtos.isEmpty()) {
                            for (GetProductToFeedbackDTO dto : dtos) {%>
                    <div class="product-item">
                        <img src="<%=dto.getImages()%>" alt="Product 1">
                        <input type="hidden" name="txtProduct" value="<%=dto.getProduct_name()%>">
                        <textarea name="Feedback" class="feedback-box" placeholder="Type your feedback here"></textarea>
                    </div>
                    <%}
                        }%>

                </div>
                          <input type="submit" name="btAction" value="Send Feedback" id="button">
            </form>
          
        </div>
    </body>
</html>
