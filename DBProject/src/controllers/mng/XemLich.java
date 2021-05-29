/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.mng;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.table.DefaultTableModel;


public class XemLich {
    public static void show(DefaultTableModel model){
        try{
            Connection conn =  services.MysqlConnection.getMysqlConnection();
            Statement st = conn.createStatement();
            String query = "select * from eventtable"
                         + " WHERE DATEDIFF(FINISHTIME, CURDATE()) > 0"
                         + " order by starttime desc";
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){  
                model.addRow(new Object[]{rs.getString("EVENTName"), rs.getString("StartTime"), rs.getString("FinishTime"), rs.getString("RoomNAME")});
            }
        } catch(SQLException e){
            System.out.print(e);
        } catch(ClassNotFoundException e){
            System.out.print(e);
        }
    }
    
    public static ArrayList<String> showDetail(DefaultTableModel model, int index){
        ArrayList<String> arr = new ArrayList<>();
        try{
            Connection conn =  services.MysqlConnection.getMysqlConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from eventTABLE");
            while(rs.next()){  
                if(rs.getString("EVENTName").equals(model.getValueAt(index, 0).toString()) && rs.getString("StartTime").equals(model.getValueAt(index, 1).toString())){                   
                    arr.add(rs.getString("EVENTName"));
                    arr.add(rs.getString("StartTime"));
                    arr.add(rs.getString("FinishTime"));
                    arr.add(rs.getString("RoomNAME"));
                    arr.add(rs.getString("EVENTDescription"));
                    arr.add(rs.getString("Fee"));
                    return arr;
                }
                
            }
        } catch(SQLException e){
        } catch(ClassNotFoundException e){
        }
        return null;
    }
    public static boolean checkAvailable(String room, LocalDateTime startTime, LocalDateTime endTime) {
        try{
            Connection conn =  services.MysqlConnection.getMysqlConnection();
            Statement st = conn.createStatement();
            String query = "select * from eventTABLE"
                         + " WHERE DATEDIFF(?, STARTTIME) > 0";
            ResultSet rs = st.executeQuery("select * from eventTABLE");
            while(rs.next()){
                if(rs.getString("RoomNAME").equals(room)){
                    String startTimeStr = rs.getString("StartTime");
                    String endTimeStr = rs.getString("FinishTime");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    
                    LocalDateTime db_startTime = LocalDateTime.parse(startTimeStr, formatter);
                    LocalDateTime db_endTime = LocalDateTime.parse(endTimeStr, formatter);
                    System.out.println(db_startTime);System.out.println(db_endTime);  
                    if(((startTime.compareTo(db_startTime) > 0) && (startTime.compareTo(db_endTime) < 0))
                        || ((endTime.compareTo(db_endTime) < 0) && (endTime.compareTo(db_startTime) > 0))
                        || ((endTime.compareTo(db_endTime) >= 0) && (startTime.compareTo(db_startTime) <= 0))){
                        return false;
                    }
                }
            }
        } catch(SQLException e){
            System.out.print(e);
        } catch(ClassNotFoundException e){
        }
        return true;
    }
}

