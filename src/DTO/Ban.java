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
public class Ban {
    String  id_crew,id_table,id_cart;
    int status_table;

    public Ban(String id_table,String id_crew, String id_cart,int status_table) {
        this.id_crew = id_crew;
        this.id_table = id_table;
        this.id_cart = id_cart;
        this.status_table = status_table;
    }

    public void setId_crew(String id_crew) {
        this.id_crew = id_crew;
    }

    public void setId_table(String id_table) {
        this.id_table = id_table;
    }

    public void setId_cart(String id_cart) {
        this.id_cart = id_cart;
    }

    public void setStatus_table(int status_table) {
        this.status_table = status_table;
    }

    public String getId_crew() {
        return id_crew;
    }

    public String getId_table() {
        return id_table;
    }

    public String getId_cart() {
        return id_cart;
    }

    public int getStatus_table() {
        return status_table;
    }

    
}
