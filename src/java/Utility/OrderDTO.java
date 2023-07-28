/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import java.sql.Date;

/**
 *
 * @author ADMIN
 */
public class OrderDTO {
    private int order_id;
    private int user_id;
 
    private Date order_date;
    private float total_price;
    private String status;


    public OrderDTO() {
    }

    public OrderDTO(int order_id, int user_id, Date order_date, float total_price, String status) {
        this.order_id = order_id;
        this.user_id = user_id;

        this.order_date = order_date;
        this.total_price = total_price;
        this.status = status;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public float getTotal_price() {
        return total_price;
    }

    public void setTotal_price(float total_price) {
        this.total_price = total_price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
