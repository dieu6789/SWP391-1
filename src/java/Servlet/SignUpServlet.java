/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import DAO.DAO;
import Utility.EmailUtility;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
public class SignUpServlet extends HttpServlet {

    private String host = "smtp.gmail.com";
    private String port = "587";
    private String user = "bmossystem@gmail.com";
    private String pass = "yhtegccgzzmptrzq";

    
    private String url = "";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, MessagingException {
        response.setContentType("text/html;charset=UTF-8");
        Random rand = new Random();
        DAO sql = new DAO();
        HttpSession session = request.getSession();

        String username = request.getParameter("txtUsername");
        String mail = request.getParameter("txtMail");
        String password = request.getParameter("txtPassword");
        String confirmPassword = request.getParameter("txtConfirmPassword");
        String phoneNumber = request.getParameter("txtPhone");
        String address = request.getParameter("txtAddress");

        int code = rand.nextInt(900000) + 100000; // 6-digit code
        if (password.equals(confirmPassword) && sql.uniqueMail(mail) == true) {
            // Set session attribute for verification code
            session.setAttribute("txtVerifyCode", Integer.toString(code));
            session.setAttribute("txtUsername", username);
            session.setAttribute("txtPassword", password);
            session.setAttribute("txtMail", mail);
            session.setAttribute("txtPhone", phoneNumber);
            session.setAttribute("txtAddress", address);
            session.setAttribute("Status", "Verify");
            // Send verification code to user's email
            String subject = "Verification Mail";
            String content = "The Verify Code for register is " + Integer.toString(code);
            EmailUtility.sendEmail(host, port, user, pass, mail, subject,
                    content);

            // Forward user to verification page
            url = "verify.jsp";
        } else {
            if (sql.uniqueMail(mail) == false) {
                session.setAttribute("ExistMail", "true");
            }
            if (!password.equals(confirmPassword)) {
                session.setAttribute("WrongConfirmPassword", "true");
            }
            url = "signup1.jsp";
        }
        response.sendRedirect(url);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SignUpServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(SignUpServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SignUpServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(SignUpServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
