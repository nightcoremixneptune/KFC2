/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DATA.BillDAO;
import DTO.Bill;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class BillBUS {
    private ArrayList<Bill> Billl ;
    public BillBUS(int i)
    {
        listBill();
    }
    
    public BillBUS()
    {
        
    }
    public Bill get(String id_bill)
    {
        for(Bill b : Billl )
        {
            if(b.getId_bill().equals(id_bill))
            {
                return b;
            }
        }
        return null;
    }
    public void listBill()
    {
        BillDAO billDAO = new BillDAO();
        Billl = new ArrayList<>();
        Billl = billDAO.list();
    }
    public void addBILL(Bill a)
    {
        Billl.add(a);
        BillDAO billDAO = new BillDAO();
        billDAO.add(a);
    }

    /*public void deleteTP(String id_bill)
    {
        for(Bill b : Billl )
        {
            if(b.getId_bill().equals(id_bill))
            {
                Billl.remove(b);
                BillDAO billDAO = new BillDAO();
                billDAO.delete(id_bill);
                return;
            }
        }
    }*/
    
/*    public void refeshBill(String id_bill)
    {
        for(Bill b : Billl )
        {
            if(b.getId_bill().equals(id_bill))
            {
                Billl.remove(b);
                BillDAO billDAO = new BillDAO();
                billDAO.refesh(id_bill);
                return;
            }
        }
    }*/
    
    public void setTP(Bill b)
    {
        for(int i = 0 ; i < Billl.size() ; i++)
        {
            if(Billl.get(i).getId_bill().equals(b.getId_bill()))
            {
                Billl.set(i, b);
                BillDAO billDAO = new BillDAO();
                billDAO.set(b);
                return;
            }
        }
    }
    public boolean check(String id_bill)
    {
        for(Bill b : Billl)
        {
            if(b.getId_bill().equals(id_bill))
            {
                return true;
            }
        }
        return false;
    }
    public ArrayList<Bill> search(String id_bill,String id_combo,String id_crew,String id_sale,int sum)
    {
        ArrayList<Bill> search = new ArrayList<>();
        id_bill = id_bill.isEmpty()?id_bill = "": id_bill;
        id_combo = id_combo.isEmpty()?id_combo = "": id_combo;
        id_crew = id_crew.isEmpty()?id_crew = "": id_crew;
        id_sale = id_sale.isEmpty()?id_sale = "": id_sale;

        for(Bill b : Billl)
        {
            if( b.getId_bill().contains(id_bill) && 
                b.getId_combo().contains(id_combo) &&
                b.getId_crew().contains(id_crew) &&
                b.getId_sale().contains(id_sale))
            {
                search.add(b);
            }
        }
        return search;
    }
    public ArrayList<Bill> getList() {
        listBill();
        return Billl;
    }
}