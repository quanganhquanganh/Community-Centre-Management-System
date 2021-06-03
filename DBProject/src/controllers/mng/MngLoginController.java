/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.mng;

import java.sql.*;
import services.MysqlConnection;
import javax.swing.JOptionPane;


public class MngLoginController {
    private static boolean check = false;
    
    
    public static boolean getCheck(){
        return check;
    }
    public static void mngLogin(String mngUsername, String mngPassword) throws ClassNotFoundException, SQLException{
            try {
                    Connection conn = MysqlConnection.getMysqlConnection();
                    Statement st = conn.createStatement();
                    ResultSet rs = st.executeQuery("SELECT * FROM MANAGER");
                    
                    while(rs.next()){
                        if(rs.getString("MNGUSERNAME").equals(mngUsername) && rs.getString("MNGPASSWORD").equals(mngPassword))
                            check = true;
                    }
                    if(check){
                        JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
                    } else {
                        JOptionPane.showMessageDialog(null, "Sai tên đăng nhập hoặc mật khẩu!", "Warning", JOptionPane.WARNING_MESSAGE);
                    }   
        } catch(SQLException e){
        }
    }
}
