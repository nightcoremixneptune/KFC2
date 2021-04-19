/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DATA.ComboDAO;
import DTO.Combo;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class ComboBUS {
    public ArrayList<Combo> Combol ;
    public ComboBUS(int i)
    {
        listCombo();
    }
    
    public ComboBUS()
    {
        
    }
    public Combo get(String id_combo)
    {
        for(Combo cb : Combol )
        {
            if(cb.getId_combo().equals(id_combo))
            {
                return cb;
            }
        }
        return null;
    }
    public void listCombo()
    {
        ComboDAO comboDAO = new ComboDAO();
        Combol = new ArrayList<>();
        Combol = comboDAO.list();
    }
    public void addCOMBO(Combo a)
    {
        Combol.add(a);
        ComboDAO comboDAO = new ComboDAO();
        comboDAO.add(a);
    }

    public void deleteCOMBO(String id_combo)
    {
        for(Combo cb : Combol )
        {
            if(cb.getId_combo().equals(id_combo))
            {
                Combol.remove(cb);
                ComboDAO comboDAO = new ComboDAO();
                comboDAO.delete(id_combo);
                return;
            }
        }
    }
    public void refeshCOMBO(String id_combo)
    {
        for(Combo cb : Combol )
        {
            if(cb.getId_combo().equals(id_combo))
            {
                Combol.remove(cb);
                ComboDAO comboDAO = new ComboDAO();
                comboDAO.refesh(id_combo);
                return;
            }
        }
    }
    public void setCOMBO(Combo cb)
    {
        for(int i = 0 ; i < Combol.size() ; i++)
        {
            if(Combol.get(i).getId_combo().equals(cb.getId_combo()))
            {
                Combol.set(i, cb);
                ComboDAO crDAO = new ComboDAO();
                crDAO.set(cb);
                return;
            }
        }
    }
    public boolean check(String id_combo)
    {
        for(Combo cr : Combol)
        {
            if(cr.getId_combo().equals(id_combo))
            {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Combo> getList() {
        listCombo();
        return Combol;
    }

   
}