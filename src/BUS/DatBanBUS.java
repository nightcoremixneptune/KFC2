/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DATA.ComboDAO;
import DATA.DatBanDAO;
import DTO.Combo;
import DTO.DatBan;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class DatBanBUS {
    public ArrayList<DatBan> DatBanl = new ArrayList<DatBan>();
    public DatBanBUS(int i)
    {
        listDatBan();
    }
    
    public DatBanBUS()
    {
        
    }
    public DatBan get(String id_datban)
    {
        for(DatBan db : DatBanl )
        {
            if(db.getId_datban().equals(id_datban))
            {
                return db;
            }
        }
        return null;
    }
    public void listDatBan()
    {
        DatBanDAO DatBanDAO = new DatBanDAO();
        DatBanl = new ArrayList<>();
        DatBanl = DatBanDAO.list();
    }
    public void addDatBan(DatBan a)
    {
        DatBanl.add(a);
        DatBanDAO DatBanDAO = new DatBanDAO();
        DatBanDAO.add(a);
    }

    public void deleteDatBan(String id_datban)
    {
        for(DatBan db : DatBanl )
        {
            if(db.getId_datban().equals(id_datban))
            {
                DatBanl.remove(db);
                DatBanDAO DatBanDAO = new DatBanDAO();
                DatBanDAO.delete(id_datban);
                return;
            }
        }
    }
    public void refeshDatBan(String id_datban)
    {
        for(DatBan db : DatBanl )
        {
            if(db.getId_datban().equals(id_datban))
            {
                DatBanl.remove(db);
                DatBanDAO DatBanDAO = new DatBanDAO();
                DatBanDAO.delete(id_datban);
                return;
            }
        }
    }
    public void setDatBan(DatBan db)
    {
        for(int i = 0 ; i < DatBanl.size() ; i++)
        {
            if(DatBanl.get(i).getId_table().equals(db.getId_table()))
            {
                DatBanl.set(i, db);
                DatBanDAO DatBanDAO = new DatBanDAO();
                DatBanDAO.set(db);
                return;
            }
        }
    }
    public boolean check(String id_datban)
    {
        for(DatBan db : DatBanl)
        {
            if(db.getId_datban().equals(id_datban))
            {
                return true;
            }
        }
        return false;
    }

    public ArrayList<DatBan> getList() {
        listDatBan();
        return DatBanl;
    }
}
