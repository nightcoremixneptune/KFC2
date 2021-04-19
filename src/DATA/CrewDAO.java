/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;

import DTO.Crew;
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
public class CrewDAO {
    private  MySQLConnect mySQL = new MySQLConnect();
    public CrewDAO() {
    }
    public ArrayList<Crew> list()
    {
        ArrayList<Crew> crewl = new ArrayList<>();
        try {
           
            String sql = "SELECT * FROM crew ";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String id_crew= rs.getString("id_crew");
                String name_crew = rs.getString("name_crew");
                String phone = rs.getString("phone");              
                String sex = rs.getString("sex");
                String img = rs.getString("img");
                int salary = rs.getInt("salary");
                String shift = rs.getString("shift");
                String position = rs.getString("position");
                int status_crew = rs.getInt("status_crew");                
                Crew cr = new Crew(id_crew, name_crew, phone, sex, img, salary, shift, position, status_crew);
                crewl.add(cr);
            }
            rs.close();
            mySQL.disConnect();
            
        } catch (SQLException ex) {
            Logger.getLogger(Crew.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return crewl;
    }

    public void set(Crew cr) {
            MySQLConnect mySQL = new MySQLConnect();
            String sql = "UPDATE crew SET ";
            sql += "name_crew='"+cr.getName_crew()+"', ";
            sql += "phone='"+cr.getPhone()+"', ";
            sql += "sex='"+cr.getSex()+"', ";
            sql += "img='"+cr.getImg()+"', ";
            sql += "salary='"+cr.getSalary()+"', ";
            sql += "shift='"+cr.getShift()+"', ";
            sql += "position='"+cr.getPosition()+"', ";
            sql += "status_crew='"+cr.getStatus_crew()+"' ";
            sql += " WHERE id_crew='"+cr.getId_crew()+"'";
            System.out.println(sql);
            
            mySQL.executeUpdate(sql);
    }

    public void add(Crew cr) {
        MySQLConnect mySQL = new MySQLConnect();
         String sql = "INSERT INTO crew VALUES (";
                sql += "'"+cr.getId_crew()+"',";
                sql += "'"+cr.getName_crew()+"',";
                sql += "'"+cr.getPhone()+"',";
                sql += "'"+cr.getSex()+"',";
                sql += "'"+cr.getImg()+"',";              
                sql += "'"+cr.getSalary()+"',";
                sql += "'"+cr.getShift()+"',";
                sql += "'"+cr.getPosition()+"',";
                sql += "'1')";
         System.out.println(sql);
         mySQL.executeUpdate(sql);
    }
    
    public void delete(String getId_crew)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE crew SET status_crew = 0 WHERE id_crew='"+getId_crew+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
    public void refesh(String getId_crew)
    {
        MySQLConnect mySQL = new MySQLConnect();
        String sql = "UPDATE crew SET status_crew = 1 WHERE id_crew='"+getId_crew+"'";
        mySQL.executeUpdate(sql);
        System.out.println(sql);
    }
}
