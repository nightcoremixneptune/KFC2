/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATA;

import DTO.Crew;
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
public class CrewDAO {
    private  MySQLConnect mySQL = new MySQLConnect();
    String addcrew;
    int adcrew;

    public CrewDAO() {
        addcrew = this.addcrew;
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
    public void getid(String id) throws SQLException
    {
        String sql = "SELECT * FROM crew ";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String id_crew= rs.getString("id_crew");
    }
    }
    public String addcrew() throws SQLException
    {
        String sql = "SELECT * FROM crew ";
            ResultSet rs = mySQL.executeQuery(sql);
            int max = 0;
            while(rs.next())
            {
                int idcrew = Integer.parseInt(rs.getString("id_crew"));
                max = idcrew;
                
            }
            int adcrew = max + 1;
            addcrew = String.valueOf(adcrew);
            rs.close();
        return addcrew;
        
    }
    
    public String datetime()
    {
        String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        return timeStamp;
    }

    public void add(Crew cr){
        
        MySQLConnect mySQL = new MySQLConnect();
         String sqlad = "INSERT INTO crew (`id_crew`,`name_crew`, `phone`, `sex`, `img`, `salary`, `shift`, `position`, `status_crew`) VALUES (";
                sqlad += "'"+cr.getId_crew()+"',";
                sqlad += "'"+cr.getName_crew()+"',";
                sqlad += "'"+cr.getPhone()+"',";
                sqlad += "'"+cr.getSex()+"',";
                sqlad += "'"+cr.getImg()+"',";              
                sqlad += "'"+cr.getSalary()+"',";
                sqlad += "'"+cr.getShift()+"',";
                sqlad += "'"+cr.getPosition()+"',";
                sqlad += "'1')";
         System.out.println(sqlad);
         mySQL.executeUpdate(sqlad);
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
