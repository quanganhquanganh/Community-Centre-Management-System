/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author macbookpro
 */
public class ThongTinNguoSuDungModels {
    private String userUsername;
    private String userPassword;
    private String userFullname;
    private String userAddress;
    private String job;
    private String birthDay;
    private String signUpDate;
    private String phoneNumber;

    public String getUserUsername() {
        return userUsername;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserFullname() {
        return userFullname;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public String getJob() {
        return job;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public String getSignUpDate() {
        return signUpDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserFullname(String userFullname) {
        this.userFullname = userFullname;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public void setSignUpDate(String signUpDate) {
        this.signUpDate = signUpDate;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    
}
