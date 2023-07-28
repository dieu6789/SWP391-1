package Servlet.UserServlet;

import DAO.DAO;
import java.io.*;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.*;
import javax.servlet.http.*;

public class VerifyCode extends HttpServlet {

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        DAO sql = new DAO();
        Random rand = new Random();
        int user_id = 0;
        try {
            do {
                user_id = rand.nextInt(900000) + 100000;
            } while (sql.uniqueID(user_id) == false);
        } catch (SQLException ex) {
            Logger.getLogger(VerifyCode.class.getName()).log(Level.SEVERE, null, ex);
        }

        String code = request.getParameter("code");
        String expectedCode = (String) session.getAttribute("txtVerifyCode");
        String status = (String) session.getAttribute("Status");

        if (code.equals(expectedCode)) {
            if (status.equals("Verify")) {
                try {
                    // Verification successful, create user account

                    String username = (String) session.getAttribute("txtUsername");
                    String password = (String) session.getAttribute("txtPassword");
                    String email = (String) session.getAttribute("txtMail");
                    String phoneNumber = (String) session.getAttribute("txtPhone");
                    String address = (String) session.getAttribute("txtAddress");

                    boolean result = sql.insertAccount(user_id, username, password, email); // TODO: Add code to insert user details into database
                    sql.insertProfile(user_id, username, email, address, phoneNumber, "CUSTOMER");
                    if (result) {
                        response.sendRedirect("birds.html");
                        session.invalidate();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(VerifyCode.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (status.equals("ForgetPassword")) {
                try {
                    String password = request.getParameter("txtPassword");
                    String mail = (String) session.getAttribute("txtMail");
                    boolean result = sql.updatePassword(mail, password);
                    if (result) {
                        response.sendRedirect("index.html");
                    }
                    session.invalidate();
                } catch (SQLException ex) {
                    Logger.getLogger(VerifyCode.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            // Verification failed, redirect back to verification page
            RequestDispatcher rd = request.getRequestDispatcher("verify.jsp");
            rd.forward(request, response);
        }

    }
}
