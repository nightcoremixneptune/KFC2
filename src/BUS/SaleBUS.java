/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;
import ConnectMysql.MySQLConnect;
import DTO.Sale;
import java.util.ArrayList;
import DATA.SaleDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author thienan
 */
public class SaleBUS {
    public ArrayList<Sale> sale ;
    public SaleBUS(int i)
    {
        saleList();
    }
    
    public SaleBUS()
    {
        
    }
    public Sale get(String saleId)
    {
        for(Sale s : sale )
        {
            if(s.getSaleId().equals(saleId))
            {
                return s;
            }
        }
        return null;
    }
    public void saleList()
    {
        SaleDAO saleDAO = new SaleDAO();
        sale = new ArrayList<>();
        sale = saleDAO.list();
    }
    public void addSale(Sale s)
    {
        sale.add(s);
        SaleDAO saleDAO = new SaleDAO();
        saleDAO.add(s);
    }

    public void deleteSale(String saleId)
    {
        for(Sale s : sale )
        {
            if(s.getSaleId().equals(saleId))
            {
                sale.remove(s);
                SaleDAO saleDAO = new SaleDAO();
                saleDAO.delete(saleId);
                return;
            }
        }
    }
    public void refeshSale(String saleId)
    {
        for(Sale s : sale )
        {
            if(s.getSaleId().equals(saleId))
            {
                sale.remove(s);
                SaleDAO saleDAO = new SaleDAO();
                saleDAO.refesh(saleId);
                return;
            }
        }
    }
    public void setSale(Sale s)
    {
        for(int i = 0 ; i < sale.size() ; i++)
        {
            if(sale.get(i).getSaleId().equals(s.getSaleId()))
            {
                sale.set(i, s);
                SaleDAO sDAO = new SaleDAO();
                sDAO.set(s);
                return;
            }
        }
    }
    public boolean check(String saleId)
    {
        for(Sale s : sale)
        {
            if(s.getSaleId().equals(saleId))
            {
                return true;
            }
        }
        return false;
    }
    public ArrayList<Sale> search(String saleId,String saleEvent,String dishId)
    {
        ArrayList<Sale> search = new ArrayList<>();
        saleId = saleId.isEmpty()?saleId = "": saleId;
        saleEvent = saleEvent.isEmpty()?saleEvent = "": saleEvent;
        dishId = dishId.isEmpty()?dishId = "": dishId;
        for(Sale s : sale)
        {
            if( s.getSaleId().contains(saleId) && 
                s.getSaleEvent().contains(saleEvent) &&
                s.getDishId().contains(dishId))
            {
                search.add(s);
            }
        }
        return search;
    }
    public ArrayList<Sale> getList() {
        saleList();
        return sale;
    }

   
}  