/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;

import DTO.Nhanvien;
import ConnectMysql.MySQLConnect;
import GUI.AdminView;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class NhanvienDAO {
    private  MySQLConnect mySQL = new MySQLConnect();
    String addcrew;
    int adcrew;
    String sluong;

    public NhanvienDAO() {
        addcrew = this.addcrew;
    }
    
    public ArrayList<Nhanvien> list()
    {
        ArrayList<Nhanvien> nhanvienl = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM nhanvien ";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String id_nhanvien= rs.getString("id_nhanvien");
                String hoNV = rs.getString("hoNV");
                String tenNV = rs.getString("tenNV");              
                String phoneNV = rs.getString("phoneNV");
                int luong = rs.getInt("luong");
                int status = rs.getInt("status");                
                Nhanvien cr = new Nhanvien(id_nhanvien, hoNV, tenNV, phoneNV, luong, status);
                nhanvienl.add(cr);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(Nhanvien.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return nhanvienl;
    }

    public void set(Nhanvien nv) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE nhanvien SET ";
            sql += "hoNV='"+nv.getHoNV()+"', ";
            sql += "tenNV='"+nv.getTenNV()+"', ";
            sql += "phoneNV='"+nv.getPhoneNV()+"', ";
            sql += "luong='"+nv.getLuong()+"', ";
            sql += "status='"+nv.getStatus()+"' ";
            sql += " WHERE id_nhanvien='"+nv.getId_nhanvien()+"'";
            System.out.println(sql);
            
            mySQL.executeUpdate(sql);
    }
    
    /*
    public String getid(String id) throws SQLException
    {
        String sql = "SELECT * FROM nhanvien where id_nhanvien = '"+id+"' ";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                sluong= rs.getString("soluong");
            }
            return sluong;
    }
    */

    public void add(Nhanvien nv){
        
        MySQLConnect mySQL = new MySQLConnect();
         String sqlad = "INSERT INTO nhanvien (`id_nhanvien`,`hoNV`, `tenNV`, `phoneNV`, `luong`, `status`) VALUES (";
                sqlad += "'"+nv.getId_nhanvien()+"',";
                sqlad += "'"+nv.getHoNV()+"',";
                sqlad += "'"+nv.getTenNV()+"',";
                sqlad += "'"+nv.getPhoneNV()+"',";
                sqlad += "'"+nv.getLuong()+"',";              
                sqlad += "'1')";
         System.out.println(sqlad);
         mySQL.executeUpdate(sqlad);
    }
    
    public void delete(String getId_nhanvien)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE nhanvien SET status = 0 WHERE id_nhanvien='"+getId_nhanvien+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
    public void refesh(String getId_nhanvien)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE nhanvien SET status = 1 WHERE id_nhanvien='"+getId_nhanvien+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
}
