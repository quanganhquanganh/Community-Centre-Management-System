/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.mng;

import services.MysqlConnection;
import java.sql.*;
import models.LenLichSuKien;

/**
 *
 * @author macbookpro
 */
public class CapNhatLichChoNhaVanHoa {

    private String roomName;
    private String startTime;
    private String finishTime;
    private String eventName;
    private String eventDescriptions;
    private int fee;
    private int note;

    private LenLichSuKien lenLichSuKien = new LenLichSuKien(roomName, startTime, finishTime, eventName, eventDescriptions, fee, note);

    public boolean capNhatLichChoPhong(LenLichSuKien lenLichSuKien) {
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            // Lấy ra EventID cao nhất trong bảng EventTable
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT MAX(EVENTID) AS EVENTID FROM EVENTTABLE");
            int eventID;
            if (rs.next()) {
                eventID = rs.getInt("EVENTID") + 1;
            } else {
                eventID = 0;
            }
            // Xong phần lấy ra EventID
            String query = "INSERT INTO EVENTTABLE(EVENTID,\n"
                    + "EVENTNAME,\n"
                    + "STARTTIME ,\n"
                    + "FINISHTIME ,\n"
                    + "ROOMNAME ,\n"
                    + "EVENTDESCRIPTION ,\n"
                    + "FEE,\n"
                    + "NOTE) \n"
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstatement = connection.prepareStatement(query);
            pstatement.setInt(1, eventID);
            pstatement.setString(2, lenLichSuKien.getEventName());
            pstatement.setString(3, lenLichSuKien.getStartTime());
            pstatement.setString(4, lenLichSuKien.getFinishTime());
            pstatement.setString(5, lenLichSuKien.getRoomName());
            pstatement.setString(6, lenLichSuKien.getEventDescriptions());
            pstatement.setInt(7, lenLichSuKien.getFee());
            pstatement.setInt(8, lenLichSuKien.getNote());
            int i = pstatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
    
//    public static void main(String args[]) {
//        CapNhatLichChoNhaVanHoa capNhat = new CapNhatLichChoNhaVanHoa();
//        capNhat.capNhatLichChoPhong(new LenLichSuKien("Hoi truong", "2020-12-20 20:20:20", "2020-12-20 20:50:20", "Giao luu nghe thuat", "Co nguoi mua hat, co nguoi coi", 0, 1));
//    }
}
