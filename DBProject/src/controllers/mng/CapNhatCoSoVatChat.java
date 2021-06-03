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
import javax.swing.JOptionPane;
import models.ThongTinCoSoVatChat;
import services.MysqlConnection;


public class CapNhatCoSoVatChat {
    public static ThongTinCoSoVatChat thongTinCoSoVatChat(String name, String room) {
        try{
            //System.out.print(name);
            //System.out.print(room);
            Connection conn = MysqlConnection.getMysqlConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM INFRASTRUCTURE");
            while(rs.next()){
                //System.out.print(rs);
                if(rs.getString("ITEMNAME").equals(name) && rs.getString("ROOMNAME").equals(room)){
                    return new ThongTinCoSoVatChat(name, rs.getString("ROOMNAME"), rs.getString("ITEMDESCRIPTION"), rs.getInt("AMOUNT"), rs.getString("LASTTIMEUPDATE"));
                }
                }
            } catch(SQLException e){
                System.out.print(e);
                }
            catch(ClassNotFoundException e){
                System.out.print(e);       
            } 
        return null;
    }
    
    public static void capNhatCSVC(String name, String room, String date, String description, int amount){
        try{
            Connection conn = MysqlConnection.getMysqlConnection();
            Statement st = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement("UPDATE INFRASTRUCTURE "
                    + "SET ITEMNAME=?, ITEMDESCRIPTION=?, AMOUNT=?, ROOMNAME=?, LASTTIMEUPDATE=?" 
                    + "WHERE ITEMNAME='" + name + "' AND ROOMNAME='" + room +"'");
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setInt(3, amount);
            ps.setString(4, room);
            ps.setString(5, date);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cập nhật thành công");

            } catch(SQLException e){
                System.out.print(e);
                }
            catch(ClassNotFoundException e){     
                System.out.print(e);  
            }
    }
    
    public static void themCSVC(String name, String room, String date, String description, int amount){
        try{
            Connection conn = MysqlConnection.getMysqlConnection();         
            Statement st = conn.createStatement();
//            String quyeryID = "SELECT "
            ResultSet rs = st.executeQuery("SELECT * FROM INFRASTRUCTURE");
            while(rs.next()){
                if(rs.getString("ITEMNAME").equals(name) && rs.getString("ROOMNAME").equals(room)){
                    JOptionPane.showMessageDialog(null, "Tên vật phẩm đã tồn tại", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }
            PreparedStatement ps = conn.prepareStatement("INSERT INTO INFRASTRUCTURE(ITEMNAME, ITEMDESCRIPTION, AMOUNT, ROOMNAME, LASTTIMEUPDATE) VALUES(?, ?, ?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setInt(3, amount);
            ps.setString(4, room);
            ps.setString(5, date);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cập nhật thành công");
        } catch(SQLException e){
            System.out.println(e);
                }
            catch(ClassNotFoundException e){
                System.out.print(e);       
            }
    }
}
