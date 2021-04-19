/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;

import ConnectMysql.MySQLConnect;
import DTO.Ban;
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
public class BanDAO {
    private  MySQLConnect mySQL = new MySQLConnect();
    public BanDAO() {
    }
    public ArrayList<Ban> list()
    {
        ArrayList<Ban> Ban1 = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM Ban ";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String id_table= rs.getString("id_table");
                String id_crew= rs.getString("id_crew");
                String id_cart= rs.getString("id_cart");
                int status_table =  rs.getInt("status_table");
                Ban b = new Ban(id_crew, id_table, id_cart, status_table );
                Ban1.add(b);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(Combo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return Ban1;
    }

    public void set(Ban b) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE ban SET ";
            sql += "id_crew='"+b.getId_crew()+"', ";
            sql += "id_cart='"+b.getId_cart()+"', ";
            sql += " WHERE id_table='"+b.getId_table()+"'";
            System.out.println(sql);
            
            mySQL.executeUpdate(sql);
    }

}
