/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.mng;

import services.MysqlConnection;
import models.DonYeuCau;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author macbookpro
 */
public class PheDuyetYeuCauNguoiDung {

    private List<DonYeuCau> listDonYeuCau = new ArrayList<DonYeuCau>();

    public List<DonYeuCau> getDanhSachChuaPheDuyet() {
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT FINISHTIMEREQUEST, STARTTIMEREQUEST, \n"
                    + " REQUESTROOM, USERUSERNAME, REQUESTDESCRIPTIONS, \n"
                    + " REQUEST.REQUESTID AS REQUESTID FROM REQUEST LEFT JOIN APPROVE \n"
                    + "ON REQUEST.REQUESTID = APPROVE.REQUESTID WHERE APPROVE.STATE IS NULL";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                DonYeuCau temp = new DonYeuCau(); // Biến lưu tạm giá trị lấy trong rs ra để sau đó thêm vào listDonYeuCau.
                temp.setFinishTime(rs.getString("FINISHTIMEREQUEST"));
                temp.setStartTime(rs.getString("STARTTIMEREQUEST"));
                temp.setRequestRoom(rs.getString("REQUESTROOM"));
                temp.setUserUsername(rs.getString("USERUSERNAME"));
                temp.setRequestDescription(rs.getString("REQUESTDESCRIPTIONS"));
                temp.setRequestID(rs.getInt("REQUESTID"));
                listDonYeuCau.add(temp);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return listDonYeuCau;
    }

    public List<DonYeuCau> getListDonYeuCau() {
        return listDonYeuCau;
    }

    public JTable danhSachChuaPheDuyetTable(List<DonYeuCau> listDonYeuCau) {
        int row = listDonYeuCau.size();
        String data[][] = new String[listDonYeuCau.size()][6];
        int i = 0;
        for (DonYeuCau donYeuCau : listDonYeuCau) {
            data[i][0] = "" + donYeuCau.getRequestID();
            data[i][1] = donYeuCau.getUserUsername();
            data[i][2] = donYeuCau.getStartTime();
            data[i][3] = donYeuCau.getFinishTime();
            data[i][4] = donYeuCau.getRequestRoom();
            data[i++][5] = donYeuCau.getRequestDescription();
        }

        // Tên các cột của bảng danh sách các yêu cầu
        String columns[] = {"Số hiệu yêu cầu", "TĐN người dùng", "Thời gian bắt đầu", "Thời gian kết thúc", "Phòng yêu cầu", "Mô tả yêu cầu"};

        JTable jTable =  new JTable(data, columns) {           
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        return jTable;
    }

    public static void main(String args[]) {
        PheDuyetYeuCauNguoiDung pheDuyetYeuCau = new PheDuyetYeuCauNguoiDung();
        //        for (DonYeuCau show : pheDuyetYeuCau.getDanhSachChuaPheDuyet()) {
        //            System.out.println(show.getUserUsername());
        //            System.out.println(show.getStartTime());
        //            System.out.println(show.getFinishTime());
        //            System.out.println(show.getRequestRoom());
        //            System.out.println(show.getRequestDescription());
        //            System.out.println(show.getRequestID());         
        //        }
        //        
        //        pheDuyetYeuCau.pheDuyetYeuCau(6);
        //        
        JFrame jFrame = new JFrame();
        jFrame.setLayout(null);
        jFrame.setVisible(true);
        JTable jTable1 = pheDuyetYeuCau.danhSachChuaPheDuyetTable(pheDuyetYeuCau.getDanhSachChuaPheDuyet());
        jTable1.setBounds(10, 10, 500, 500);
        JScrollPane jScrollPane1 = new JScrollPane(jTable1);
        jScrollPane1.setBounds(100, 100, 500, 300);
        jFrame.add(jScrollPane1);
        jFrame.setSize(1000, 1000);
        
        

    }

    /* 
     * Tìm xem trong danh sách các yêu cầu chưa phê duyệt, lấy ra yêu cầu có REQUESTID = requestID,
     * phê duyệt yêu cầu này và đưa nó ra khỏi danh sách chờ
     */
    public void loaiBoYeuCauDaPheDuyet(int requestID) {
        List<DonYeuCau> temp = getDanhSachChuaPheDuyet();
        for (DonYeuCau pheDuyet : temp) {
            if (pheDuyet.getRequestID() == requestID) {
                temp.remove(pheDuyet);
                break;
            }
        }
    }

    public boolean ketQuaPheDuyet(int requestID, String state, String mngUsername, String note, int fee) {
        try {
            if (!state.equals("Để sau")) {
                Connection connection = MysqlConnection.getMysqlConnection();
                String prequery = "INSERT INTO APPROVE(MNGUSERNAME,\n"
                        + "REQUESTID,\n"
                        + "STATE ,\n"
                        + "NOTE ,\n"
                        + "FEE) \n"
                        + " VALUES(?, ?, ?, ?, ?)";
                PreparedStatement pstatement = connection.prepareStatement(prequery);
                pstatement.setString(1, mngUsername);
                pstatement.setInt(2, requestID);
                pstatement.setString(3, state);
                pstatement.setString(4, note);
                pstatement.setInt(5, fee);
                pstatement.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
}
