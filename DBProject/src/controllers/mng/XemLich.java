/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.mng;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.table.DefaultTableModel;
import services.MysqlConnection;


public class XemLich {
    public static void show(DefaultTableModel model){
        try{
            Connection conn =  services.MysqlConnection.getMysqlConnection();
            Statement st = conn.createStatement();
            String query = "SELECT * FROM EVENTTABLE"
                         + " WHERE DATEDIFF(FINISHTIME, CURDATE()) > 0"
                         + " ORDER BY STARTTIME DESC";
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){  
                model.addRow(new Object[]{rs.getString("EVENTNAME"), rs.getString("STARTTIME"), rs.getString("FINISHTIME"), rs.getString("ROOMNAME")});
            }
        } catch(SQLException e){
            System.out.print(e);
        } catch(ClassNotFoundException e){
            System.out.print(e);
        }
    }
    
    public static int count() {
        int tong = -1;
        try{
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT COUNT(*) AS tong FROM EVENTTABLE"
                         + " WHERE DATEDIFF(FINISHTIME, CURDATE()) > 0"
                         + " ORDER BY STARTTIME DESC";
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                tong = rs.getInt("tong");
            }
            preparedStatement.close();
        } catch (Exception e) {
            System.out.print(e);
        }
        return tong;
    }
    
    public static ArrayList<String> showDetail(DefaultTableModel model, int index){
        ArrayList<String> arr = new ArrayList<>();
        try{
            Connection conn =  services.MysqlConnection.getMysqlConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM EVENTTABLE");
            while(rs.next()){  
                if(rs.getString("EVENTNAME").equals(model.getValueAt(index, 0).toString()) && rs.getString("StartTime").equals(model.getValueAt(index, 1).toString())){                   
                    arr.add(rs.getString("EVENTNAME"));
                    arr.add(rs.getString("STARTTIME"));
                    arr.add(rs.getString("FINISHTIME"));
                    arr.add(rs.getString("ROOMNAME"));
                    arr.add(rs.getString("EVENTDESCRIPTION"));
                    arr.add(rs.getString("FEE"));
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
            String query = "SELECT * FROM EVENTTABLE"
                         + " WHERE DATEDIFF(?, STARTTIME) > 0";
            ResultSet rs = st.executeQuery("SELECT * FROM EVENTTABLE");
            while(rs.next()){
                if(rs.getString("ROOMNAME").equals(room)){
                    String startTimeStr = rs.getString("STARTTIME");
                    String endTimeStr = rs.getString("FINISHTIME");
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

