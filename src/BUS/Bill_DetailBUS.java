/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DATA.Bill_DetailDAO;
import DTO.Bill_Detail;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Bill_DetailBUS {
    private ArrayList<Bill_Detail> Bill_detaill ;
    public Bill_DetailBUS(int i)
    {
        listBill_Detail();
    }
    
    public Bill_DetailBUS()
    {
        
    }
    public Bill_Detail get(String id_bill)
    {
        for(Bill_Detail bd : Bill_detaill )
        {
            if(bd.getId_bill().equals(id_bill))
            {
                return bd;
            }
        }
        return null;
    }
    public void listBill_Detail()
    {
        Bill_DetailDAO bill_DetailDAO = new Bill_DetailDAO();
        Bill_detaill = new ArrayList<>();
        Bill_detaill = bill_DetailDAO.list();
    }
    public void addBILL(Bill_Detail a)
    {
        Bill_detaill.add(a);
        Bill_DetailDAO bill_DetailDAO = new Bill_DetailDAO();
        bill_DetailDAO.add(a);
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
    
    public void setTP(Bill_Detail bd)
    {
        for(int i = 0 ; i < Bill_detaill.size() ; i++)
        {
            if(Bill_detaill.get(i).getId_bill().equals(bd.getId_bill()))
            {
                Bill_detaill.set(i, bd);
                Bill_DetailDAO bill_DetailDAO = new Bill_DetailDAO();
                bill_DetailDAO.set(bd);
                return;
            }
        }
    }
    public boolean check(String id_bill)
    {
        for(Bill_Detail b : Bill_detaill)
        {
            if(b.getId_bill().equals(id_bill))
            {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Bill_Detail> getList() {
        listBill_Detail();
        return Bill_detaill;
    }
}