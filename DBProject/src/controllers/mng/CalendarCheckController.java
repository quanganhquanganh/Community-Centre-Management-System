/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.mng;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JLabel;
import services.MysqlConnection;

/**
 *
 * @author Admin
 */
public class CalendarCheckController {
    private JLabel tongSuKien;
    private JLabel tongDonDk;

    public CalendarCheckController(JLabel tongSuKienArg, JLabel tongDonDkArg) {
        this.tongSuKien = tongSuKienArg;
        this.tongDonDk = tongDonDkArg;
    }
    
    public void setData() {
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT COUNT(*) AS tong FROM EVENTTABLE"
                         + " WHERE DATEDIFF(FINISHTIME, CURDATE()) > 0"
                         + " ORDER BY STARTTIME DESC";
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            this.tongSuKien.setText(String.valueOf(0));
            this.tongDonDk.setText(String.valueOf(0));
            while (rs.next()){
                this.tongSuKien.setText(String.valueOf(rs.getInt("tong")));
            }
            query = "SELECT COUNT(*) AS tong FROM REQUEST WHERE REQUESTID NOT IN (SELECT REQUESTID FROM APPROVE) ORDER BY STARTTIMEREQUEST DESC";
            preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            while (rs.next()){
                this.tongDonDk.setText(String.valueOf(rs.getInt("tong")));
            }
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
        }
    }

    public JLabel getTongSuKien() {
        return tongSuKien;
    }

    public void setTongSuKien(JLabel tongSuKienArg) {
        this.tongSuKien = tongSuKienArg;
    }

    public JLabel getTongDonDk() {
        return tongDonDk;
    }

    public void setTongHoKhau(JLabel tongDonDkArg) {
        this.tongDonDk = tongDonDkArg;
    }
}