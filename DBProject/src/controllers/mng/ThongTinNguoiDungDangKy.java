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
import javax.swing.table.DefaultTableModel;
import models.LenLichSuKien;
import models.TinhTrangYeuCau;
import services.MysqlConnection;


public class ThongTinNguoiDungDangKy {
    public static void print(DefaultTableModel model){
        try{
            Connection conn =  services.MysqlConnection.getMysqlConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM REQUEST WHERE REQUESTID NOT IN (SELECT REQUESTID FROM APPROVE) ORDER BY STARTTIMEREQUEST DESC");
            while(rs.next()){  
                model.addRow(new Object[]{rs.getString("requestid"), rs.getString("USERUSERNAME"), rs.getString("STARTTIMEREQUEST"), rs.getString("FINISHTIMEREQUEST"), rs.getString("REQUESTROOM"), rs.getString("requestdescriptions")});
            }
        } catch(SQLException e){
        } catch(ClassNotFoundException e){
        }
    }
    
    public static int count() {
        int tong = -1;
        try{
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT COUNT(*) AS tong FROM FROM REQUEST WHERE REQUESTID NOT IN (SELECT REQUESTID FROM APPROVE) ORDER BY STARTTIMEREQUEST DESC";
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
    
    public static void state(TinhTrangYeuCau tinhtrang){
        try{
            Connection conn =  services.MysqlConnection.getMysqlConnection();
            PreparedStatement ps = conn.prepareStatement("INSERT INTO APPROVE(MNGUSERNAME, REQUESTID, STATE, NOTE, FEE) VALUES(?, ?, ?, ?, ?)");
            ps.setString(1, "admin");
            ps.setString(3, tinhtrang.getState());
            ps.setInt(2, tinhtrang.getRequestID());
            ps.setString(4, tinhtrang.getNote());
            ps.setInt(5, tinhtrang.getFee());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Đã " + tinhtrang.getState());
        } catch(SQLException e){
        } catch(ClassNotFoundException e){
        }
    }
}
