/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.user;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.XemLichSuDung;
import java.util.*;
import java.util.ArrayList;
import services.MysqlConnection;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author macbookpro
 */
public class XemLichSuDungNhaVanHoaController {

    private List<XemLichSuDung> lichSuDung = new ArrayList<XemLichSuDung>();

    public String[] layDanhSachPhong() {
        // Lấy ra danh sách các phòng hiện có để cho người dùng chọn lựa, tránh việc phòng do người dùng nhập vào không nằm trong danh sách.
        String[] result;
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            Statement statement = connection.createStatement();
            String queryFetchRoomNameList = "SELECT ROOMNAME FROM ROOM";
            String queryNumberOfRoom = "SELECT COUNT(*) AS SUM FROM ROOM";
            ResultSet rs1 = statement.executeQuery(queryNumberOfRoom);
            int numberOfRoom = 0;
            if (rs1.next()) {
                numberOfRoom = rs1.getInt("SUM");
            }
            ResultSet rs = statement.executeQuery(queryFetchRoomNameList);
            result = new String[numberOfRoom];
            int i = 0;
            while (rs.next()) {
                result[i++] = rs.getString("ROOMNAME");
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return result;
    }

    public List<XemLichSuDung> getLichSuDung(String roomName) {
        try {

            Connection connection = MysqlConnection.getMysqlConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT ROOMNAME, STARTTIME, FINISHTIME, EVENTNAME, EVENTID FROM EVENTTABLE WHERE ROOMNAME = '" + roomName + "'";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                XemLichSuDung lsd = new XemLichSuDung(); //Biến dùng để lưu tạm kết quả từ result sau đó thêm vào danh sách
                lsd.setEventName(rs.getString("eventname"));
                lsd.setFinishTime(rs.getString("finishtime"));
                lsd.setStartTime(rs.getString("starttime"));
                lsd.setRoomName(rs.getString("roomname"));
                lichSuDung.add(lsd);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lichSuDung;
    }

    public JTable setTableModel(List<XemLichSuDung> lichSuDung) {
        String data[][] = new String[lichSuDung.size()][4];
        int i = 0;
        for (XemLichSuDung getData : lichSuDung) {
            data[i][1] = getData.getEventName();
            data[i][0] = getData.getRoomName();
            data[i][2] = getData.getStartTime();
            data[i][3] = getData.getFinishTime();
            i++;
        }
        String columns[] = {"Phòng", "Tên Sự Kiện", "Thời  gian  bắt  đầu", "Thời  gian  kết  thúc"};        
        return new JTable(data, columns);
    }

    public static void main(String args[]) {
        JButton jButton1 = new JButton("Remove");
        
        XemLichSuDungNhaVanHoaController sample = new XemLichSuDungNhaVanHoaController();
        JFrame jFrame = new JFrame(" asdf");
        jFrame.setLayout(null);
        jFrame.add(jButton1);
        JTable jTable1 = (sample.setTableModel(sample.getLichSuDung("Hoi truong")));
        jTable1.setBounds(0, 0, 500, 500);
        jFrame.setSize(1000, 1000);        
        jFrame.add(jTable1);
        
        jFrame.setVisible(true);
        
        jButton1.setBounds(300, 300, 100, 100);
        jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ee) {
                jFrame.remove(jTable1);
                jFrame.repaint();
                jFrame.invalidate();
                jFrame.validate();
                XemLichSuDungNhaVanHoaController sample2 = new XemLichSuDungNhaVanHoaController();
                JTable jTable2 = sample2.setTableModel(sample2.getLichSuDung("Phong chuc nang 1"));
                jTable2.setBounds(10, 10, 500, 500);
                jFrame.add(jTable2);
            }
        });
        
        
        
    }
}
