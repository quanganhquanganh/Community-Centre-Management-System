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

/**
 *
 * @author keplegarry
 */
public class ThongTinNguoiDungDangKy {
    public static void print(DefaultTableModel model){
        try{
            Connection conn =  services.MysqlConnection.getMysqlConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from request where requestid not in (select requestid from approve) order by starttimerequest desc");
            while(rs.next()){  
                model.addRow(new Object[]{rs.getString("requestid"), rs.getString("userusername"), rs.getString("starttimerequest"), rs.getString("finishtimerequest"), rs.getString("REQUESTROOM"), rs.getString("requestdescriptions")});
            }
        } catch(SQLException e){
        } catch(ClassNotFoundException e){
        }
    }
    
    public static void state(TinhTrangYeuCau tinhtrang){
        try{
            Connection conn =  services.MysqlConnection.getMysqlConnection();
            PreparedStatement ps = conn.prepareStatement("insert into approve(mngusername, requestid, state, note, fee) values(?, ?, ?, ?, ?)");
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
