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
public class TinhTrangYeuCau {
    private String state;
    private int fee;
    private String note;
    private int requestID;
    private String startTimeRequest;
    private String finishTimeRequest;
    private String requestDesriptions;            

    public void setRequestID(int requestID) {
        this.requestID = requestID;
    }

    public void setStartTimeRequest(String startTimeRequest) {
        this.startTimeRequest = startTimeRequest;
    }

    public void setFinishTimeRequest(String finishTimeRequest) {
        this.finishTimeRequest = finishTimeRequest;
    }

    public void setRequestDesriptions(String requestDesriptions) {
        this.requestDesriptions = requestDesriptions;
    }

    public int getRequestID() {
        return requestID;
    }

    public String getStartTimeRequest() {
        return startTimeRequest;
    }

    public String getFinishTimeRequest() {
        return finishTimeRequest;
    }

    public String getRequestDesriptions() {
        return requestDesriptions;
    }

    public TinhTrangYeuCau() {
    }

    
    
    public TinhTrangYeuCau(String state, String note) {
        this.state = state;
        this.note = note;
    }
    
    public TinhTrangYeuCau(String state, String note, int requestid, String starttime, String finishtime, int fee){
        this.state = state;
        this.note = note;
        this.requestID = requestid;
        this.startTimeRequest = starttime;
        this.finishTimeRequest = finishtime;
        this.fee = fee;
    }

    public String getState() {
        return state;
    }

    public String getNote() {
        return note;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    public int getFee(){
        return fee;
    }
}
