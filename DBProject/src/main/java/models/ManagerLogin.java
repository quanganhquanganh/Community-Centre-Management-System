/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author dungtinhhd123
 */
public class ManagerLogin {
    private String mngUsername;
    private String mngPassword;

    public ManagerLogin() {
    }

    public ManagerLogin(String mngUsername, String mngPassword) {
        this.mngUsername = mngUsername;
        this.mngPassword = mngPassword;
    }

    public String getMngUsername() {
        return mngUsername;
    }

    public String getMngPassword() {
        return mngPassword;
    }

    public void setMngUsername(String mngUsername) {
        this.mngUsername = mngUsername;
    }

    public void setMngPassword(String mngPassword) {
        this.mngPassword = mngPassword;
    }
    
}
