/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DATA.DishDAO;
import DTO.Dish;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class DishBUS {
    private ArrayList<Dish> Dishl ;
    public DishBUS(int i)
    {
        listDish();
    }
    
    public DishBUS()
    {
        
    }
    public Dish get(String id_dish)
    {
        for(Dish dh : Dishl )
        {
            if(dh.getId_dish().equals(id_dish))
            {
                return dh;
            }
        }
        return null;
    }
    public void listDish()
    {
        DishDAO DishDAO = new DishDAO();
        Dishl = new ArrayList<>();
        Dishl = DishDAO.list();
    }
    public void addMA(Dish a)
    {
        Dishl.add(a);
        DishDAO dishDAO = new DishDAO();
        dishDAO.add(a);
    }

    public void deleteMA(String id_dish)
    {
        for(Dish cr : Dishl )
        {
            if(cr.getId_dish().equals(id_dish))
            {
                Dishl.remove(cr);
                DishDAO dishDAO = new DishDAO();
                dishDAO.delete(id_dish);
                return;
            }
        }
    }
    public void refeshMA(String id_dish)
    {
        for(Dish cr : Dishl )
        {
            if(cr.getId_dish().equals(id_dish))
            {
                Dishl.remove(cr);
                DishDAO dishDAO = new DishDAO();
                dishDAO.refesh(id_dish);
                return;
            }
        }
    }
    public void setNV(Dish s)
    {
        for(int i = 0 ; i < Dishl.size() ; i++)
        {
            if(Dishl.get(i).getId_dish().equals(s.getId_dish()))
            {
                Dishl.set(i, s);
                DishDAO dishDAO = new DishDAO();
                dishDAO.set(s);
                return;
            }
        }
    }
    public boolean check(String id_dish)
    {
        for(Dish dh : Dishl)
        {
            if(dh.getId_dish().equals(id_dish))
            {
                return true;
            }
        }
        return false;
    }
    public ArrayList<Dish> search(String id_dish,String name_dish,String id_food,String food_consume)
    {
        ArrayList<Dish> search = new ArrayList<>();
        id_dish = id_dish.isEmpty()?id_dish = "": id_dish;
        name_dish = name_dish.isEmpty()?name_dish = "": name_dish;
        id_food = id_food.isEmpty()?id_food = "": id_food;
        food_consume = food_consume.isEmpty()?food_consume = "": food_consume;
        for(Dish dh : Dishl)
        {
            if( dh.getId_dish().contains(id_dish) && 
                dh.getName_dish().contains(name_dish) &&
                dh.getId_food().contains(id_food) &&
                dh.getFood_consume().contains(food_consume))
            {
                search.add(dh);
            }
        }
        return search;
    }
    public ArrayList<Dish> getList() {
        listDish();
        return Dishl;
    }
}