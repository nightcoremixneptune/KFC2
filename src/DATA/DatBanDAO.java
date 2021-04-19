/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;

import ConnectMysql.MySQLConnect;
import DTO.Combo;
import DTO.DatBan;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class DatBanDAO {
    private  MySQLConnect mySQL = new MySQLConnect();
    public DatBanDAO() {
    }
    public ArrayList<DatBan> list()
    {
        ArrayList<DatBan> DatBanl = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM datban ";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String id_datban = rs.getString("id_datban");
                String id_crew= rs.getString("id_crew");
                String id_table= rs.getString("id_table");
                String name_customer= rs.getString("name_customer");
                int phone_customer =  rs.getInt("phone_customer");
                String TimeDatBan= rs.getString("TimeDatBan");
                String TimeNhanBan = rs.getString("TimeNhanBan");
                int status_datban =  rs.getInt("status_datban");
                DatBan db = new DatBan(id_datban,id_crew, id_table, name_customer, phone_customer, TimeDatBan, TimeNhanBan,status_datban);
                DatBanl.add(db);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(Combo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return DatBanl;
    }

    public void set(DatBan db) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE datban SET ";
            sql += "id_crew='"+db.getId_crew()+"', ";
            sql += "name_customer='"+db.getName_customer()+"', ";
            sql += "phone_customer='"+db.getPhone_customer()+"', ";
            sql += "TimeDatBan='"+db.getTimeDatBan()+"', ";
            sql += "TimeNhanBan='"+db.getTimeNhanBan()+"', ";         
            sql += " WHERE id_table='"+db.getId_table()+"'";
            System.out.println(sql);
            
            mySQL.executeUpdate(sql);
    }

    public void add(DatBan db) {
        MySQLConnect mySQL = new MySQLConnect();
         String sql = "INSERT INTO datban VALUES (";  
                sql += "'"+db.getId_datban()+"',";
                sql += "'"+db.getId_crew()+"',";
                sql += "'"+db.getId_table()+"',";
                sql += "'"+db.getName_customer()+"',";
                sql += "'"+db.getPhone_customer()+"',";
                sql += "'"+db.getTimeDatBan()+"',";
                sql += "'"+db.getTimeNhanBan()+"',";
                sql += "'1')";
         System.out.println(sql);
         mySQL.executeUpdate(sql);
    }
    
    public void delete(String getId_datban)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE datban SET status_datban = 0 WHERE id_datban='"+getId_datban+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
    public void refesh(String getId_datban)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE datban SET status_datban = 1 WHERE id_datban='"+getId_datban+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
}
