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
public class RefundDTO {

    private int refundID;
    private int orderID;
    private String mail;
    private String reason;
    private Date date;

    public RefundDTO(int refundID, int orderID, String mail, String reason, Date date) {
        this.refundID = refundID;
        this.orderID = orderID;
        this.mail = mail;
        this.reason = reason;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getRefundID() {
        return refundID;
    }

    public void setRefundID(int refundID) {
        this.refundID = refundID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}
