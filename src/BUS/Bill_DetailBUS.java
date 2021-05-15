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
    public Bill_Detail get(String id_hoadon)
    {
        for(Bill_Detail bd : Bill_detaill )
        {
            if(bd.getId_hoadon().equals(id_hoadon))
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
    
    /*
    public void setTP(Bill_Detail b)
    {
        for(int i = 0 ; i < Bill_detaill.size() ; i++)
        {
            if(Bill_detaill.get(i).getId_hoadon().equals(b.getId_hoadon()))
            {
                Bill_detaill.set(i, b);
                Bill_DetailDAO billDAO = new Bill_DetailDAO();
                Bill_DetailDAO.set(b);
                return;
            }
        }
    }
    */
    
    public boolean check(String id_hoadon)
    {
        for(Bill_Detail bd : Bill_detaill)
        {
            if(bd.getId_hoadon().equals(id_hoadon))
            {
                return true;
            }
        }
        return false;
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
    


    public ArrayList<Bill_Detail> getList() {
        listBill_Detail();
        return Bill_detaill;
    }
}