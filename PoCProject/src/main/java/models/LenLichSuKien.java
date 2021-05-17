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
public class LenLichSuKien {
    private String roomName;
    private String startTime;
    private String finishTime;
    private String eventName;
    private String eventDescriptions;
    private int fee;
    private int note;

    public LenLichSuKien() {
    }

    public LenLichSuKien(String roomName, String startTime, String finishTime, String eventName, String eventDescriptions, int fee, int note) {
        this.roomName = roomName;
        this.startTime = startTime;
        this.finishTime = finishTime;
        this.eventName = eventName;
        this.eventDescriptions = eventDescriptions;
        this.fee = fee;
        this.note = note;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getStartTime() {
        return startTime;
    }
    
    public String getFinishTime() {
        return finishTime;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDescriptions() {
        return eventDescriptions;
    }

    public int getFee() {
        return fee;
    }

    public int getNote() {
        return note;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventDescriptions(String eventDescriptions) {
        this.eventDescriptions = eventDescriptions;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public void setNote(int note) {
        this.note = note;
    }
    
}
