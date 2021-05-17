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
public class ThongTinCoSoVatChat {
    private String itemName;
    private String roomName;
    private String itemDescriptions;
    private int amount;
    private String lastTimeUpDate;

    public ThongTinCoSoVatChat(String itemName, String roomName, String itemDescriptions, int amount, String lastTimeUpDate) {
        this.itemName = itemName;
        this.roomName = roomName;
        this.itemDescriptions = itemDescriptions;
        this.amount = amount;
        this.lastTimeUpDate = lastTimeUpDate;
    }
    
    public ThongTinCoSoVatChat() {
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setLastTimeUpDate(String lastTimeUpDate) {
        this.lastTimeUpDate = lastTimeUpDate;
    }

    public int getAmount() {
        return amount;
    }

    public String getLastTimeUpDate() {
        return lastTimeUpDate;
    }

    public String getItemName() {
        return itemName;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getItemDescriptions() {
        return itemDescriptions;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void setItemDescriptions(String itemDescriptions) {
        this.itemDescriptions = itemDescriptions;
    }
       
}
