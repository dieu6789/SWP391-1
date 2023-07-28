/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.UserServlet;

import DAO.DAO;
import Utility.ProductListDTO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
public class SortProductList extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, NamingException {
        response.setContentType("text/html;charset=UTF-8");
        DAO sql = new DAO();
        HttpSession session = request.getSession();
        
        String sort = request.getParameter("Sort");
        List<ProductListDTO> productLists;
        session.removeAttribute("SortedProductList");
        try {

            switch (sort) {
                case "SortPrice":
                    sql.sortProductByPrice();
                    productLists = sql.getListProduct();
                    session.setAttribute("SortedProductList", productLists);
                    break;
                case "SortPrice2" :
                    sql.sortProductByPrice2();
                    productLists = sql.getListProduct();
                    session.setAttribute("SortedProductList", productLists);
                    break;
                case "SortQuantity":
                    sql.sortProductByQuantity();
                    productLists = sql.getListProduct();
                    session.setAttribute("SortedProductList", productLists);
                    break;
               case "SortQuantity2":
                    sql.sortProductByQuantity2();
                    productLists = sql.getListProduct();
                    session.setAttribute("SortedProductList", productLists);
                    break;
                case "SortName2":
                    sql.sortProductByName2();
                    productLists = sql.getListProduct();
                    session.setAttribute("SortedProductList", productLists);
                    break;
                case "SortName":
                    sql.sortProductByName();
                    productLists = sql.getListProduct();
                    session.setAttribute("SortedProductList", productLists);
            }
        } finally {

            response.sendRedirect("ProductPage.jsp");
        }
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
            Logger.getLogger(SortProductList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(SortProductList.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(SortProductList.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(SortProductList.class.getName()).log(Level.SEVERE, null, ex);
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
