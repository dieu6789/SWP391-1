/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

/**
 *
 * @author ADMIN
 */
public class RevenueDTO {
   private String month;
   private int revenue;

    public RevenueDTO(String month, int revenue) {
        this.month = month;
        this.revenue = revenue;
    }

    public RevenueDTO() {
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }
}
