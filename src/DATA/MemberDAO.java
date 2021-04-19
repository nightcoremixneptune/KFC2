/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;

import DTO.Member;
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
public class MemberDAO {
    private  MySQLConnect mySQL = new MySQLConnect();
    public MemberDAO() {
    }
    public ArrayList<Member> list()
    {
        ArrayList<Member> dskh = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM member";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String id_member= rs.getString("id_member");
                String name_member = rs.getString("name_member");
                String phonenumber = rs.getString("phonenumber");              
                String brand_member = rs.getString("brand_member");
                int status_member = rs.getInt("status_member");
                Member kh = new Member(id_member, name_member, phonenumber, brand_member, status_member);
                dskh.add(kh);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(Member.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return dskh;
    }

    public void set(Member kh) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE member SET ";
            sql += "name_member='"+kh.getName_member()+"', ";
            sql += "phonenumber='"+kh.getPhonenumber()+"', ";
            sql += "brand_member='"+kh.getBrand_member()+"', ";
            sql += "status_member='"+kh.getStatus_member()+"', ";
            sql += " WHERE id_dish='"+kh.getId_member()+"'";
            System.out.println(sql);
            
            mySQL.executeUpdate(sql);
    }

    public void add(Member kh) {
        MySQLConnect mySQL = new MySQLConnect();
         String sql = "INSERT INTO member VALUES (";
                sql += "'"+kh.getId_member()+"',";
                sql += "'"+kh.getName_member()+"',";
                sql += "'"+kh.getPhonenumber()+"',";
                sql += "'"+kh.getBrand_member()+"',";
                sql += "'1')";
         System.out.println(sql);
         mySQL.executeUpdate(sql);
    }
    
    public void delete(String MaKH)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE member SET status_member = 0 WHERE id_member='"+MaKH+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
    public void refesh(String MaKH)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE member SET status_member = 1 WHERE id_member='"+MaKH+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
}
