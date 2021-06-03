package services;

import java.sql.*;

public class MysqlConnection {
    public static Connection getMysqlConnection() throws SQLException, ClassNotFoundException {
        String hostName = "remotemysql.com";
        String dbName = "iiaQ9zAlq6";
        String userName = "iiaQ9zAlq6";
        String password = "v4mWIb0D0d";
        //String hostName = "localhost";
        //String dbName = "DBProject";
        //String userName = "root";
        //String password = "25102001";
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
