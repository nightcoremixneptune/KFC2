/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Admin
 */
public class DatBan {
    String  id_crew,id_table,name_customer,TimeDatBan,TimeNhanBan,id_datban;
    int phone_customer,status_datban;

    public DatBan(String id_datban,String id_crew, String id_table, String name_customer, int phone_custumer,String TimeDatBan,String TimeNhanBan,int status_datban) {
        this.id_crew = id_crew;
        this.id_table = id_table;
        this.name_customer = name_customer;
        this.phone_customer = phone_custumer;
        this.TimeDatBan = TimeDatBan;
        this.TimeNhanBan = TimeNhanBan;
        this.id_datban = id_datban;
        this.status_datban = status_datban;
       
    }

    public void setStatus_datban(int status_datban) {
        this.status_datban = status_datban;
    }

    public int getStatus_datban() {
        return status_datban;
    }

    public void setId_datban(String id_datban) {
        this.id_datban = id_datban;
    }

    public String getId_datban() {
        return id_datban;
    }
    
    public void setTimeDatBan(String TimeDatBan) {
        this.TimeDatBan = TimeDatBan;
    }

    public void setTimeNhanBan(String TimeNhanBan) {
        this.TimeNhanBan = TimeNhanBan;
    }

    public String getTimeDatBan() {
        return TimeDatBan;
    }

    public String getTimeNhanBan() {
        return TimeNhanBan;
    }

    public String getId_crew() {
        return id_crew;
    }

    public String getId_table() {
        return id_table;
    }

    public String getName_customer() {
        return name_customer;
    }

    public int getPhone_customer() {
        return phone_customer;
    }

    public void setId_crew(String id_crew) {
        this.id_crew = id_crew;
    }

    public void setId_table(String id_table) {
        this.id_table = id_table;
    }

    public void setName_customer(String name_customer) {
        this.name_customer = name_customer;
    }

    public void setPhone_customer(int phone_customer) {
        this.phone_customer = phone_customer;
    }

   
   
}
