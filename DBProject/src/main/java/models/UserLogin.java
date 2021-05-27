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
public class UserLogin {
    private String useUsername;
    private String userPassword;
    
    public UserLogin() {
    }
    
    public UserLogin(String useUsername, String userPassword) {
        this.useUsername = useUsername;
        this.userPassword = userPassword;
    }

    public String getUseUsername() {
        return useUsername;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUseUsername(String useUsername) {
        this.useUsername = useUsername;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

}
