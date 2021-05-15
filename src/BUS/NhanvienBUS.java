/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DTO.Nhanvien;
import DATA.NhanvienDAO;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class NhanvienBUS {
    public ArrayList<Nhanvien> Nhanvienl ;
    public String max; 
    public int max2;
    public String max3;
    public NhanvienBUS(int i)
    {
        listNhanvien();
    }
    
    public NhanvienBUS()
    {
        
    }
    public Nhanvien get(String id_nhanvien)
    {
        for(Nhanvien nv : Nhanvienl )
        {
            if(nv.getId_nhanvien().equals(id_nhanvien))
            {
                return nv;
            }
        }
        return null;
    }
    
    public void listNhanvien()
    {
        NhanvienDAO nhanvienDAO = new NhanvienDAO();
        Nhanvienl = new ArrayList<>();
        Nhanvienl = nhanvienDAO.list();
    }
    public void addNhanvien(Nhanvien nv)
    {
        Nhanvienl.add(nv);
        NhanvienDAO nhanvienDAO = new NhanvienDAO();
        nhanvienDAO.add(nv);
    }
    public String addid()
    {
        for(Nhanvien nv : Nhanvienl)
        {
            max = nv.getId_nhanvien();
        }
        max2 = 1 + Integer.parseInt(max);
        max3 = String.valueOf(max2);
        return max3;
    }
    

    public void deleteNhanvien(String id_nhanvien)
    {
        for(Nhanvien nv : Nhanvienl )
        {
            if(nv.getId_nhanvien().equals(id_nhanvien))
            {
                Nhanvienl.remove(nv);
                NhanvienDAO nhanvienDAO = new NhanvienDAO();
                nhanvienDAO.delete(id_nhanvien);
                return;
            }
        }
    }
    public void refeshCREW(String id_nhanvien)
    {
        for(Nhanvien nv : Nhanvienl )
        {
            if(nv.getId_nhanvien().equals(id_nhanvien))
            {
                Nhanvienl.remove(nv);
                NhanvienDAO nhanvienDAO = new NhanvienDAO();
                nhanvienDAO.refesh(id_nhanvien);
                return;
            }
        }
    }
    public void setCREW(Nhanvien nv)
    {
        for(int i = 0 ; i < Nhanvienl.size() ; i++)
        {
            if(Nhanvienl.get(i).getId_nhanvien().equals(nv.getId_nhanvien()))
            {
                Nhanvienl.set(i, nv);
                NhanvienDAO nhanvienDAO = new NhanvienDAO();
                nhanvienDAO.set(nv);
                return;
            }
        }
    }
    public boolean check(String id_nhanvien)
    {
        for(Nhanvien nv : Nhanvienl)
        {
            if(nv.getId_nhanvien().equals(id_nhanvien))
            {
                return true;
            }
        }
        return false;
    }
    public ArrayList<Nhanvien> search(String id_nhanvien)
    {
        ArrayList<Nhanvien> search = new ArrayList<>();
        id_nhanvien = id_nhanvien.isEmpty()?id_nhanvien = "": id_nhanvien;
        
        for(Nhanvien nv : Nhanvienl)
        {
            if( nv.getId_nhanvien().contains(id_nhanvien)) 
                
            {
                search.add(nv);
            }
        }
        return search;
    }
    
    public ArrayList<Nhanvien> searchHoNV(String HoNV)
    {
        ArrayList<Nhanvien> search = new ArrayList<>();
        HoNV = HoNV.isEmpty()?HoNV = "": HoNV;
        
        for(Nhanvien nv : Nhanvienl)
        {
            if( nv.getHoNV().contains(HoNV)) 
                
            {
                search.add(nv);
            }
        }
        return search;
    }
    public ArrayList<Nhanvien> getList() {
        listNhanvien();
        return Nhanvienl;
    }

   
}