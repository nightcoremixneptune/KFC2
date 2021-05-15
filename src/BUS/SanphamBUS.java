/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DATA.SanphamDAO;
import DTO.Sanpham;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class SanphamBUS {
    private ArrayList<Sanpham> Sanphaml ;
    public SanphamBUS(int i)
    {
        listSanpham();
    }
    
    public SanphamBUS()
    {
        
    }
    public Sanpham get(String id_sp)
    {
        for(Sanpham sp : Sanphaml )
        {
            if(sp.getId_sp().equals(id_sp))
            {
                return sp;
            }
        }
        return null;
    }
    public void listSanpham()
    {
        SanphamDAO sanphamDAO = new SanphamDAO();
        Sanphaml = new ArrayList<>();
        Sanphaml = sanphamDAO.list();
    }
    public void addMA(Sanpham sp)
    {
        Sanphaml.add(sp);
        SanphamDAO sanphamDAO = new SanphamDAO();
        sanphamDAO.add(sp);
    }

    public void deleteMA(String id_sp)
    {
        for(Sanpham sp : Sanphaml )
        {
            if(sp.getId_sp().equals(id_sp))
            {
                Sanphaml.remove(sp);
                SanphamDAO sanphamDAO = new SanphamDAO();
                sanphamDAO.delete(id_sp);
                return;
            }
        }
    }
    public void refeshMA(String id_sp)
    {
        for(Sanpham sp : Sanphaml )
        {
            if(sp.getId_sp().equals(id_sp))
            {
                Sanphaml.remove(sp);
                SanphamDAO sanphamDAO = new SanphamDAO();
                sanphamDAO.refesh(id_sp);
                return;
            }
        }
    }
    public void setMA(Sanpham sp)
    {
        for(int i = 0 ; i < Sanphaml.size() ; i++)
        {
            if(Sanphaml.get(i).getId_sp().equals(sp.getId_sp()))
            {
                Sanphaml.set(i, sp);
                SanphamDAO sanphamDAO = new SanphamDAO();
                sanphamDAO.set(sp);
                return;
            }
        }
    }
    public boolean check(String id_sp)
    {
        for(Sanpham sp : Sanphaml)
        {
            if(sp.getId_sp().equals(id_sp))
            {
                return true;
            }
        }
        return false;
    }
    public ArrayList<Sanpham> search(String id_sp)
    {
        ArrayList<Sanpham> search = new ArrayList<>();
        id_sp = id_sp.isEmpty()?id_sp = "": id_sp;
        
        for(Sanpham sp : Sanphaml)
        {
            if( sp.getId_sp().contains(id_sp))
            {
                search.add(sp);
            }
        }
        return search;
    }
    public ArrayList<Sanpham> getList() {
        listSanpham();
        return Sanphaml;
    }
}