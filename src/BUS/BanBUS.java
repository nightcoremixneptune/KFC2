/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DATA.BanDAO;
import DATA.DatBanDAO;
import DTO.Ban;
import DTO.DatBan;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class BanBUS {
    public ArrayList<Ban> Ban1 ;
    public BanBUS(int i)
    {
        listBan();
    }
    
    public BanBUS()
    {
        
    }
    public Ban get(String id_table)
    {
        for(Ban b : Ban1 )
        {
            if(b.getId_table().equals(id_table))
            {
                return b;
            }
        }
        return null;
    }
    public void listBan()
    {
        BanDAO BanDAO = new BanDAO();
        Ban1 = new ArrayList<>();
        Ban1 = BanDAO.list();
    }

    public void setBan(Ban b)
    {
        for(int i = 0 ; i < Ban1.size() ; i++)
        {
            if(Ban1.get(i).getId_table().equals(b.getId_table()))
            {
                Ban1.set(i, b);
                BanDAO BanDAO = new BanDAO();
                BanDAO.set(b);
                return;
            }
        }
    }
    public boolean check(String id_table)
    {
        for(Ban b : Ban1)
        {
            if(b.getId_table().equals(id_table))
            {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Ban> getList() {
        listBan();
        return Ban1;
    }
}
