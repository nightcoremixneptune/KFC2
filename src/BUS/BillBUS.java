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
    public Bill get(String id_hoadon)
    {
        for(Bill b : Billl )
        {
            if(b.getId_hoadon().equals(id_hoadon))
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
            if(Billl.get(i).getId_hoadon().equals(b.getId_hoadon()))
            {
                Billl.set(i, b);
                BillDAO billDAO = new BillDAO();
                billDAO.set(b);
                return;
            }
        }
    }
    public boolean check(String id_hoadon)
    {
        for(Bill b : Billl)
        {
            if(b.getId_hoadon().equals(id_hoadon))
            {
                return true;
            }
        }
        return false;
    }
    public ArrayList<Bill> search(String id_hoadon, String id_khach, String id_nhanvien, String id_khuyenmai, String ngaylap ,int tongtien)
    {
        ArrayList<Bill> search = new ArrayList<>();
        id_hoadon = id_hoadon.isEmpty()?id_hoadon = "": id_hoadon;
        id_khach = id_khach.isEmpty()?id_khach = "": id_nhanvien;
        id_nhanvien = id_nhanvien.isEmpty()?id_nhanvien = "": id_nhanvien;
        ngaylap = ngaylap.isEmpty()?ngaylap = "": ngaylap;

        for(Bill b : Billl)
        {
            if( b.getId_hoadon().contains(id_hoadon) && 
                b.getId_khach().contains(id_khach) &&
                b.getId_nhanvien().contains(id_nhanvien) &&
                b.getNgaylap().contains(ngaylap))
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