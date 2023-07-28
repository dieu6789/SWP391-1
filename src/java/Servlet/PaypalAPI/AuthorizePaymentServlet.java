package Servlet.PaypalAPI;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import DAO.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Utility.PaypalTransactionDTO;
import com.paypal.base.rest.PayPalRESTException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
public class AuthorizePaymentServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public AuthorizePaymentServlet() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO sql = new DAO();
        HttpSession session = request.getSession();
        String[] products = request.getParameterValues("title");
        String[] quantities = request.getParameterValues("quantity");
        String productList = quantities[0] + " " + products[0];
        String username = request.getParameter("txtUsername");
        String mail = request.getParameter("txtMail");
        String phone_number = request.getParameter("txtPhoneNumber");
        String address = request.getParameter("txtAddress");
        String url = "";
        boolean valid = true;
        for (int i = 0; i < products.length; i++) {

            try {
                if (sql.getProductQuantity(products[i]) < Integer.parseInt(quantities[i])) {
                    session.setAttribute("InvalidQuantity", "TRUE");
                    url = "ProductPage.jsp";
                    valid = false;
                }
            } catch (SQLException ex) {
                Logger.getLogger(AuthorizePaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (username == "" || mail == "" || phone_number == "" || address == "") {
            session.setAttribute("LackOfInformation", "true");
            valid = false;
            url = "ProductPage.jsp";
        }
        if (valid) {
            for (int i = 1; i < products.length; i++) {
                productList = productList + " | " + quantities[i] + " " + products[i];
            }

            try {
                if (sql.getID(mail) > 0) {
                    session.setAttribute("USERNAME", username);
                    session.setAttribute("MAIL", mail);
                    session.setAttribute("USERID", sql.getID(mail));
                    session.setAttribute("PHONE_NUMBER", phone_number);
                    session.setAttribute("ADDRESS", address);
                    session.setAttribute("QUANTITY", quantities);
                    session.setAttribute("PRODUCT", products);
                } else {
                    session.setAttribute("USERNAME", username);
                    session.setAttribute("MAIL", mail);
                    session.setAttribute("USERID", 1);
                    session.setAttribute("PHONE_NUMBER", phone_number);
                    session.setAttribute("ADDRESS", address);
                    session.setAttribute("QUANTITY", quantities);
                    session.setAttribute("PRODUCT", products);
                }
            } catch (SQLException ex) {
                Logger.getLogger(AuthorizePaymentServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            String subtotal = request.getParameter("subtotal").substring(1, request.getParameter("subtotal").length());
            String tax = request.getParameter("tax").substring(1, request.getParameter("tax").length());
            String total = request.getParameter("total").substring(1, request.getParameter("total").length());

            PaypalTransactionDTO orderDetail = new PaypalTransactionDTO(productList, subtotal, "0", tax, total);

            try {
                PaymentServices paymentServices = new PaymentServices();
                url = paymentServices.authorizePayment(orderDetail);

            } catch (PayPalRESTException ex) {
                request.setAttribute("errorMessage", ex.getMessage());
                ex.printStackTrace();
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
        response.sendRedirect(url);
    }
}
