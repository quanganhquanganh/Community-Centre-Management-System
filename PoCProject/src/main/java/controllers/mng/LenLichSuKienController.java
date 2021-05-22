/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.mng;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import models.LenLichSuKien;
import services.MysqlConnection;
/**
 *
 * @author keplegarry
 */
public class LenLichSuKienController {
    public static void lenLich(LenLichSuKien lich){
        try{
            Connection conn = MysqlConnection.getMysqlConnection();
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT MAX(EVENTID) AS EVENTID FROM EVENTTABLE");
            int eventID;
            if (rs.next()) {
                eventID = rs.getInt("EVENTID") + 1;
            } else {
                eventID = 0;
            }
            // Xong phần lấy ra EventID
            PreparedStatement ps = conn.prepareStatement("insert into eventtable(RoomNAME, StartTime, FinishTime, EVENTNAME, EVENTDescription, Fee, EVENTID) values(?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, lich.getRoomName());
            ps.setString(2, lich.getStartTime());
            ps.setString(3, lich.getFinishTime());
            ps.setString(4, lich.getEventName());
            ps.setString(5, lich.getEventDescriptions());
            ps.setInt(6, lich.getFee());
            ps.setInt(7, eventID);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Đã thêm sự kiện");
            
        } catch(SQLException e){
            System.out.println(e);
            
        } catch(ClassNotFoundException e){
            
        }
    }
}
