/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DATA.StorageDAO;
import DTO.Storage;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class StorageBUS {
    private ArrayList<Storage> Storagel ;
    public StorageBUS(int i)
    {
        listStorage();
    }
    
    public StorageBUS()
    {
        
    }
    public Storage get(String id_food)
    {
        for(Storage tp : Storagel )
        {
            if(tp.getId_food().equals(id_food))
            {
                return tp;
            }
        }
        return null;
    }
    public void listStorage()
    {
        StorageDAO StorageDAO = new StorageDAO();
        Storagel = new ArrayList<>();
        Storagel = StorageDAO.list();
    }
    public void addTP(Storage a)
    {
        Storagel.add(a);
        StorageDAO storageDAO = new StorageDAO();
        storageDAO.add(a);
    }

    public void deleteTP(String id_food)
    {
        for(Storage tp : Storagel )
        {
            if(tp.getId_food().equals(id_food))
            {
                Storagel.remove(tp);
                StorageDAO storageDAO = new StorageDAO();
                storageDAO.delete(id_food);
                return;
            }
        }
    }
    public void refeshTP(String id_food)
    {
        for(Storage tp : Storagel )
        {
            if(tp.getId_food().equals(id_food))
            {
                Storagel.remove(tp);
                StorageDAO storageDAO = new StorageDAO();
                storageDAO.refesh(id_food);
                return;
            }
        }
    }
    public void setTP(Storage s)
    {
        for(int i = 0 ; i < Storagel.size() ; i++)
        {
            if(Storagel.get(i).getId_food().equals(s.getId_food()))
            {
                Storagel.set(i, s);
                StorageDAO storageDAO = new StorageDAO();
                storageDAO.set(s);
                return;
            }
        }
    }
    public boolean check(String id_food)
    {
        for(Storage tp : Storagel)
        {
            if(tp.getId_food().equals(id_food))
            {
                return true;
            }
        }
        return false;
    }
    public ArrayList<Storage> search(String id_food,String brand_food,String quantity_food,String expiry_date)
    {
        ArrayList<Storage> search = new ArrayList<>();
        id_food = id_food.isEmpty()?id_food = "": id_food;
        brand_food = brand_food.isEmpty()?brand_food = "": brand_food;
        quantity_food = quantity_food.isEmpty()?quantity_food = "": quantity_food;
        expiry_date = expiry_date.isEmpty()?expiry_date = "": expiry_date;
        for(Storage tp : Storagel)
        {
            if( tp.getId_food().contains(id_food) && 
                tp.getBrand_food().contains(brand_food) &&
                tp.getQuantity_food().contains(quantity_food) &&
                tp.getExpiry_date().contains(expiry_date))
            {
                search.add(tp);
            }
        }
        return search;
    }
    public ArrayList<Storage> getList() {
        listStorage();
        return Storagel;
    }
}