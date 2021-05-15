/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;

import DTO.Sanpham;
import ConnectMysql.MySQLConnect;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class SanphamDAO {
    private  MySQLConnect mySQL = new MySQLConnect();
    public SanphamDAO() {
    }
    public ArrayList<Sanpham> list()
    {
        ArrayList<Sanpham> dsma = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM sanpham";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String id_sp= rs.getString("id_sp");
                String tensp = rs.getString("tensp");
                int soluong = rs.getInt("soluong");              
                int dongia = rs.getInt("dongia");
                int id_loaisp = rs.getInt("id_loaisp");
                int status_sp = rs.getInt("status_sp");
                Sanpham sp = new Sanpham(id_sp, tensp, soluong, dongia, id_loaisp,status_sp);
                dsma.add(sp);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(Sanpham.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dsma;
    }

    public void set(Sanpham sp) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE sanpham SET ";
            sql += "tensp='"+sp.getTensp()+"', ";
            sql += "soluong='"+sp.getSoluong()+"', ";
            sql += "dongia='"+sp.getDongia()+"', ";
            sql += "id_loaisp='"+sp.getId_loaisp()+"', ";
            sql += "status_sp='"+sp.getStatus_sp()+"', ";
            sql += " WHERE id_sp='"+sp.getId_sp()+"'";
            System.out.println(sql);
            
            mySQL.executeUpdate(sql);
    }

    public void add(Sanpham sp) {
        MySQLConnect mySQL = new MySQLConnect();
         String sql = "INSERT INTO sanpham VALUES (";
                sql += "'"+sp.getId_sp()+"',";
                sql += "'"+sp.getTensp()+"',";
                sql += "'"+sp.getSoluong()+"',";
                sql += "'"+sp.getDongia()+"',";
                sql += "'"+sp.getId_loaisp()+"',";
                sql += "'1')";
         System.out.println(sql);
         mySQL.executeUpdate(sql);
    }
    
    public void delete(String MaMA)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE dish SET status_sp = 0 WHERE id_sp='"+MaMA+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
    public void refesh(String MaMA)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE dish SET status_sp = 1 WHERE id_sp='"+MaMA+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
}
