/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.user;

import java.sql.*;
import java.util.ArrayList;
import models.TinhTrangYeuCau;
import services.MysqlConnection;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author macbookpro
 */
//Cần xem lại
/*
* Người dùng kiểm tra tình trạng của những yêu cầu mà mình đã gửi.
 */
public class XemTinhTrangYeuCauController {

    private List<TinhTrangYeuCau> tinhTrangYeuCau = new ArrayList<TinhTrangYeuCau>();

    public List<TinhTrangYeuCau> getTinhTrangYeuCau(String userUsername) {
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT REQUEST.REQUESTID,\n"
                    + "STARTTIMEREQUEST,\n"
                    + "FINISHTIMEREQUEST,\n"
                    + "REQUESTDESCRIPTIONS, STATE, NOTE \n"
                    + "FROM REQUEST\n"
                    + "LEFT JOIN \n"
                    + "APPROVE \n"
                    + "ON REQUEST.REQUESTID = APPROVE.REQUESTID\n"
                    + "WHERE REQUEST.USERUSERNAME = '" + userUsername + "'";
            ResultSet rs = statement.executeQuery(query);
            String stateTemp;
            String noteTemp;
            while (rs.next()) {
                TinhTrangYeuCau ttyc = new TinhTrangYeuCau(); // Biến tinhtrangyeucau dùng tạm để lấy kết quả từ rs ra sau đó thêm vào list
                ttyc.setFinishTimeRequest(rs.getString("FINISHTIMEREQUEST"));
                ttyc.setStartTimeRequest(rs.getString("STARTTIMEREQUEST"));
                ttyc.setRequestDesriptions(rs.getString("REQUESTDESCRIPTIONS"));
                ttyc.setRequestID(rs.getInt("REQUEST.REQUESTID"));                
                stateTemp = rs.getString("STATE");                
                if (stateTemp == null) {
                    ttyc.setState("Chưa được thông qua.");
                    ttyc.setNote("Quản lý chưa đọc yêu cầu.");
                } else {
                    ttyc.setState(stateTemp);    
                    ttyc.setNote(rs.getString("NOTE"));
                }
                tinhTrangYeuCau.add(ttyc);
            }
        } catch (Exception e) {
            System.out.println("Chưa thực hiện yêu cầu nào");
            return tinhTrangYeuCau;
        }

        return tinhTrangYeuCau;
    }

    public JTable hienThiTinhTrangYeuCau(List<TinhTrangYeuCau> tinhTrangYeuCau) {
        String data[][] = new String[tinhTrangYeuCau.size()][6];
        String columns[] = {"<html>Số hiệu yêu cầu<html>", "Thời gian bắt đầu", "Thời gian kết thúc", "Mô tả yêu cầu", "Tình trạng", "Lưu ý thêm"};
        int i = 0;
        int j;
        for (TinhTrangYeuCau getData : tinhTrangYeuCau) {            
            data[i][0] = "" + getData.getRequestID();
            data[i][1] = getData.getStartTimeRequest();
            data[i][2] = getData.getFinishTimeRequest();
            data[i][3] = getData.getRequestDesriptions();            
            data[i][4] = getData.getState();
            data[i++][5] = getData.getNote();
        }

        JTable jTable1 = new JTable(data, columns);
        return jTable1;
    }

    public static void main(String args[]) {
        XemTinhTrangYeuCauController tinhTrangYeuCau = new XemTinhTrangYeuCauController();
        JTable jTable1 = tinhTrangYeuCau.hienThiTinhTrangYeuCau(tinhTrangYeuCau.getTinhTrangYeuCau("nguyenvana"));
        JFrame jFrame1 = new JFrame();
        jFrame1.add(new JScrollPane(jTable1));
        jFrame1.setVisible(true);
    }
}
