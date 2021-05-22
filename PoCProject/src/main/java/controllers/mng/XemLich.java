/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.mng;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author keplegarry
 */
public class XemLich {
    public static void show(DefaultTableModel model){
        try{
            Connection conn =  services.MysqlConnection.getMysqlConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from eventtable order by starttime desc");
            while(rs.next()){  
                model.addRow(new Object[]{rs.getString("EVENTName"), rs.getString("StartTime"), rs.getString("FinishTime"), rs.getString("RoomNAME")});
            }
        } catch(SQLException e){
        } catch(ClassNotFoundException e){
        }
    }
    
    public static ArrayList<String> showDetail(DefaultTableModel model, int index){
        ArrayList<String> arr = new ArrayList<>();
        try{
            Connection conn =  services.MysqlConnection.getMysqlConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select * from eventTABLE");
            while(rs.next()){  
                if(rs.getString("EVENTName").equals(model.getValueAt(index, 0).toString()) && rs.getString("StartTime").equals(model.getValueAt(index, 1).toString())){                   
                    arr.add(rs.getString("EVENTName"));
                    arr.add(rs.getString("StartTime"));
                    arr.add(rs.getString("FinishTime"));
                    arr.add(rs.getString("RoomNAME"));
                    arr.add(rs.getString("EVENTDescription"));
                    arr.add(rs.getString("Fee"));
                    return arr;
                }
                
            }
        } catch(SQLException e){
        } catch(ClassNotFoundException e){
        }
        return null;
    }
}
