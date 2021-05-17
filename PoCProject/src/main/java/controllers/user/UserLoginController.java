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


