/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
public class MainController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final String signUpServlet = "SignUpServlet";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String action = request.getParameter("btAction");
        HttpSession session = request.getSession();
        String url = "index.html";
        try {
            /* TODO output your page here. You may use following sample code. */
            if (action == null) {
                url = "";
            } else if (action.equals("Login")) {
                url = "Login";
            } else if (action.equals("Send Mail")) {
                url = "EmailSending";
            } else if (action.equals("Sign Up")) {
                url = signUpServlet;
            } else if (action.equals("Verify")) {
                url = "VerifyCode";
            } else if (action.equals("Logout")) {
                url = "LogoutServlet";
            } else if (action.equals("Reset")) {
                url = "ForgetPassword";
            } else if (action.equals("Update Password")) {
                url = "UpdatePassword";
            } else if (action.equals("Save")) {
                url = "UpdateInformation";
            } else if (action.equals("Remove")) {
                url = "RemoveFavoriteProduct";
            } else if (action.equals("Sort")) {
                url = "SortProductList";
            } else if (action.equals("Favorite")) {
                url = "AddToFavorite";
            } else if (action.equals("Search")) {
                url = "Search";
            } else if (action.equals("Search Bird Name")) {
                url = "SearchBird";
            } else if (action.equals("Payment in Cash")) {
                url = "Checkout";
            } else if (action.equals("Pay by Paypal")) {
                url = "AuthorizePaymentServlet";
            } else if (action.equals("Cancel")) {
                url = "CancelOrder";
            } else if (action.equals("Add Product")) {
                url = "AddProduct";
            } else if (action.equals("Delete Product")) {
                url = "DeleteProduct";
            } else if (action.equals("Update Product")) {
                url = "UpdateProduct";
            } else if (action.equals("Add Staff Account")) {
                url = "AddStaffAccount";
            } else if (action.equals("Delete Staff")) {
                url = "DeleteStaff";
            }else if (action.equals("Search Product")) {
                url = "Search1";
            }else if (action.equals("Search Order")) {
                url = "SearchOrder";
            }else if (action.equals("Select Year")) {
                url = "GetRevenue";
            }else if (action.equals("Feedback")) {
                url = "Feedback";
            }else if (action.equals("Send Feedback")) {
                url = "SendFeedback";
            }else if (action.equals("Send Request")) {
                url = "SendRequest";
            }else if (action.equals("Reply") || action.equals("Ignore")){
                url = "HandleRequest";
                session.setAttribute("Button",action);
            }else if (action.equals("Search Account")) {
                url = "SearchAccount";
            }
            

            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } finally {
            out.close();
        };
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
        processRequest(request, response);
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
        processRequest(request, response);
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
