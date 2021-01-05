/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author HAU28
 */
public class Account implements Serializable{
    //private Map<String,String> account;
    private String user;
    private String pass;
    private String yourClass;

    public Account(String user, String pass, String yourClass) {
        this.user = user;
        this.pass = pass;
        this.yourClass = yourClass;
    }

    public Account() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getYourClass() {
        return yourClass;
    }

    public void setYourClass(String yourClass) {
        this.yourClass = yourClass;
    }

    @Override
    public String toString() {
        return "CUser{" + "user=" + user + ", pass=" + pass + ", yourClass=" + yourClass + '}';
    }
    
    
    
    

   
    
    
}
