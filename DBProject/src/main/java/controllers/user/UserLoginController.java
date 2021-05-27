/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.user;
import services.MysqlConnection;
import java.sql.*;
/**
 *
 * @author dungtinhhd123
 */

/*
* Điều khiển đăng nhập
*/
public class UserLoginController {   
    public boolean login(String userUsername, String userPassword)  throws SQLException, ClassNotFoundException {
        Connection connection = MysqlConnection.getMysqlConnection();
        Statement stm = connection.createStatement();
        String query = "SELECT USERUSERNAME, USERPASSWORD FROM USERTAB WHERE USERUSERNAME = '" + userUsername + "'";
        ResultSet rs = stm.executeQuery(query);
        while (rs.next()) {
            if (userPassword.equals(rs.getString("USERPASSWORD"))){
                new CurrentUserUsername().setCurrentUserUsrename(userUsername);
                connection.close();
                return true;
            };
        } 
        connection.close();
        return false;
    }
    public boolean checker(String userUsername, String userPassword)  throws SQLException, ClassNotFoundException {
        Connection connection = MysqlConnection.getMysqlConnection();
        Statement stm = connection.createStatement();
        String query = "SELECT USERUSERNAME,USERPASSWORD from USERTAB";
        ResultSet rs = stm.executeQuery(query);
        while(rs.next())
        {
            if(userUsername.equals(rs.getString("USERUSERNAME"))){
                connection.close();
                    return false; 
            }; 
        }
        connection.close();
        
        return true;
    }
    public void save_(String userUsername, String userPassword)  throws SQLException, ClassNotFoundException {
      
        try {
            Connection connection1 = MysqlConnection.getMysqlConnection();
            Statement stm1 = connection1.createStatement();
            String query1 = "INSERT INTO USERTAB(soCMT, USERUSERNAME, USERPASSWORD, FULLNAME, ADDRESS, SIGNUPDATE, JOB, BIRTHDAY, PHONENUMBER) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            //System.out.println(query1);
            PreparedStatement rs1 = connection1.prepareStatement(query1);
            rs1.setString(1, "NULL");
            rs1.setString(2, userUsername);
            rs1.setString(3, userPassword);
            rs1.setString(4, "NULL");
            rs1.setString(5, "NULL");
            rs1.setString(6, "2021-05-23 00:00:00");
            rs1.setString(7, "NULL");
            rs1.setString(8, "2021-05-23");
            rs1.setString(9, "00000000");
            //System.out.println(rs1);
            rs1.executeUpdate();
            rs1.close();
            connection1.close();
        } catch (Exception e) {System.out.println(e);}    
    }
    public void reset_pass(String userUsername, String userPassword) throws SQLException, ClassNotFoundException{
        try{Connection connection = MysqlConnection.getMysqlConnection();
        Statement stm = connection.createStatement();
        String query = "UPDATE USERTAB set USERPASSWORD ='"+ userPassword +"' WHERE USERUSERNAME = '" + userUsername + "'";
        PreparedStatement rs = connection.prepareStatement(query);
        rs.executeUpdate();
        rs.close();
        connection.close();}catch (Exception e) {System.out.println(e);}
    }
    public static void main(String args[]){
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            UserLoginController user = new UserLoginController();
            if (user.login("nguyenvana", "root")) {
                System.out.println("Login succeed!");
            } else {
                System.out.println("Failed!");
            };
        } catch (Exception e) {System.out.println(e);}        
    }
    
}


