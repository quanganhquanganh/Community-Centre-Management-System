package services;

import java.sql.*;

public class MysqlConnection {
    public static Connection getMysqlConnection() throws SQLException, ClassNotFoundException {
        //String hostName = "sql6.freemysqlhosting.net";
        //String dbName = "sql6415471";
        //String userName = "sql6415471";
        //String password = "z9UTUxedgt";
        String hostName = "localhost";
        String dbName = "dbproject";
        String userName = "root";
        String password = "25102001";
        System.out.print("Lol");
        return getMysqlConnection(hostName, dbName, userName, password);
    }
    
    public static Connection getMysqlConnection(String hostName, String dbName, String userName, String password) 
        throws SQLException, ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String connectionUrl = "jdbc:mysql://" + hostName + ":3306/" + dbName;
        Connection conn = DriverManager.getConnection(connectionUrl, userName, password);
        return conn;
    }
}
