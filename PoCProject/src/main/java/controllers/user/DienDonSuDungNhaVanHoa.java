/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.user;

import services.MysqlConnection;
import java.sql.*;
import models.DonYeuCau;

/**
 *
 * @author macbookpro
 */
public class DienDonSuDungNhaVanHoa {

    private int requestID;
    
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

    public boolean themDonYeuCau(String userUsername, String startTimeRequest, String finishTimeRequest, String requestDescriptions, String requestRoom) {
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            Statement statement = connection.createStatement();
            String queryFetchBigestRequestID = "SELECT MAX(REQUESTID) AS REQUESTID FROM REQUEST"; // Câu  lệnh  lấy ra RequestID lớn  nhất trong bảng
            ResultSet rs = statement.executeQuery(queryFetchBigestRequestID);            
            if (rs.next()) {
                requestID = rs.getInt("REQUESTID") + 1;            
            } else {
                requestID = 1;
            }
            
            String insertQuery = "INSERT INTO REQUEST(REQUESTID, STARTTIMEREQUEST, FINISHTIMEREQUEST, REQUESTDESCRIPTIONS, USERUSERNAME, REQUESTROOM) VALUES(?, ?, ?, ?, ?, ?)";
            PreparedStatement pstatement = connection.prepareStatement(insertQuery);
            pstatement.setInt(1, requestID);
            pstatement.setString(2, startTimeRequest);
            pstatement.setString(3, finishTimeRequest);
            pstatement.setString(4, requestDescriptions);
            pstatement.setString(5, userUsername);
            pstatement.setString(6, requestRoom);
            int i = pstatement.executeUpdate();
            rs.close();
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    } 
    
    public static void main(String args[]) {
        DienDonSuDungNhaVanHoa diendon = new DienDonSuDungNhaVanHoa();
        System.out.println(diendon.themDonYeuCau("nguyenvana", "2020:12:20 20:12:20", "20:12:20 20:59:59", "Muon phong de ngu", "Phong chuc nang 1"));
    }
}
