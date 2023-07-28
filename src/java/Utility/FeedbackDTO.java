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
public class FeedbackDTO {
    private String username;
    private Date date_feedback;
    private String comment;

    public FeedbackDTO() {
    }

    public FeedbackDTO(String username, Date date_feedback, String comment) {
        this.username = username;
        this.date_feedback = date_feedback;
        this.comment = comment;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate_feedback() {
        return date_feedback;
    }

    public void setDate_feedback(Date date_feedback) {
        this.date_feedback = date_feedback;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    
}
