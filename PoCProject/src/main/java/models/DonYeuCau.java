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

public class DonYeuCau {
    private String userUsername;
    private String requestRoom;
    private String startTime;
    private String finishTime;
    private String requestDescription;
    private int requestID;

    public int getRequestID() {
        return requestID;
    }

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }
    
    
    public String getUserUsername() {
        return userUsername;
    }

    public String getRequestRoom() {
        return requestRoom;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public String getRequestDescription() {
        return requestDescription;
    }

    public void setUserUsername(String userUsername) {
        this.userUsername = userUsername;
    }

    public void setRequestRoom(String requestRoom) {
        this.requestRoom = requestRoom;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }
 
}
