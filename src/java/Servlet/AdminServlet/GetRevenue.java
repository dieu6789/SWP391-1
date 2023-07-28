/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet.AdminServlet;

import DAO.DAO;
import Utility.RevenueDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
public class GetRevenue extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
     
            /* TODO output your page here. You may use following sample code. */
           DAO sql = new DAO();
           HttpSession session = request.getSession();
           
           int total_revenue = 0;
           int total_revenue_last_year = 0;
           int year = Integer.parseInt(request.getParameter("Year"));
           String temp =  request.getParameter("Year") + "," + (Integer.parseInt(request.getParameter("Year"))-1);
           session.removeAttribute("TotalRevenue");
           session.removeAttribute("AverageMonth");
           session.removeAttribute("Revenue");
           session.removeAttribute("Year");
           session.removeAttribute("ProfitRate");
           session.removeAttribute("AverageMonthRate");
           session.removeAttribute("YearChart");
           session.removeAttribute("YearRate");
           
           sql.revenuelist(year);
           List<RevenueDTO> revenue = sql.getListRevenue();
           String revenuePerMonth = "";
           for (int i = 0; i< revenue.size(); i++){
               revenuePerMonth =  revenuePerMonth + revenue.get(i).getRevenue() + ",";
               total_revenue = total_revenue + revenue.get(i).getRevenue();
           }
           
           revenue.clear();
           sql.revenuelist(year-1);
           revenue = sql.getListRevenue();
           for (int i = 0; i< revenue.size(); i++){
               total_revenue_last_year = total_revenue_last_year + revenue.get(i).getRevenue();
           }
           float profitRate= 100.0f * ( (total_revenue - total_revenue_last_year) / (float) total_revenue_last_year );
           float averageMonthRate = 100.0f * ( ( ( total_revenue / 12 ) - ( total_revenue_last_year / 12) )  / ( (float) total_revenue_last_year / 12) ) ;
           float temp2 = 100 * total_revenue / (float)( total_revenue_last_year + total_revenue ) ;
           String temp3 = temp2+"," + (100-temp2);
           session.setAttribute("Year",year);  
           session.setAttribute("ProfitRate", profitRate);
           session.setAttribute("AverageMonthRate", averageMonthRate);
           session.setAttribute("TotalRevenue",total_revenue);
           session.setAttribute("AverageMonth",total_revenue/12);
           session.setAttribute("Revenue", revenuePerMonth);
           session.setAttribute("YearChart",temp);
           session.setAttribute("YearRate",temp3);
           response.sendRedirect("AdminDashboard.jsp");
        
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
            Logger.getLogger(GetRevenue.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(GetRevenue.class.getName()).log(Level.SEVERE, null, ex);
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
