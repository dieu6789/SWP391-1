/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utility;

import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
public class UserDTO implements Serializable {
    private int user_id;
    private String username;
    private String mail;
    private String address;
    private String phone_number;
    private String role;

    public UserDTO() {
    }

    public UserDTO(int user_id, String username, String mail, String address, String phone_number, String role) {
        this.user_id = user_id;
        this.username = username;
        this.mail = mail;
        this.address = address;
        this.phone_number = phone_number;
        this.role = role;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
}
