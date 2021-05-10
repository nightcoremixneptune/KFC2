/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DTO.Crew;
import DATA.CrewDAO;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class CrewBUS {
    public ArrayList<Crew> Crewl ;
    public CrewBUS(int i)
    {
        listCrew();
    }
    
    public CrewBUS()
    {
        
    }
    public Crew get(String id_crew)
    {
        for(Crew cr : Crewl )
        {
            if(cr.getId_crew().equals(id_crew))
            {
                return cr;
            }
        }
        return null;
    }
    public void listCrew()
    {
        CrewDAO crewDAO = new CrewDAO();
        Crewl = new ArrayList<>();
        Crewl = crewDAO.list();
    }
    public void addCREW(Crew a)
    {
        Crewl.add(a);
        CrewDAO crewDAO = new CrewDAO();
        crewDAO.add(a);
    }

    public void deleteCREW(String id_crew)
    {
        for(Crew cr : Crewl )
        {
            if(cr.getId_crew().equals(id_crew))
            {
                Crewl.remove(cr);
                CrewDAO crewDAO = new CrewDAO();
                crewDAO.delete(id_crew);
                return;
            }
        }
    }
    public void refeshCREW(String id_crew)
    {
        for(Crew cr : Crewl )
        {
            if(cr.getId_crew().equals(id_crew))
            {
                Crewl.remove(cr);
                CrewDAO crewDAO = new CrewDAO();
                crewDAO.refesh(id_crew);
                return;
            }
        }
    }
    public void setCREW(Crew s)
    {
        for(int i = 0 ; i < Crewl.size() ; i++)
        {
            if(Crewl.get(i).getId_crew().equals(s.getId_crew()))
            {
                Crewl.set(i, s);
                CrewDAO crDAO = new CrewDAO();
                crDAO.set(s);
                return;
            }
        }
    }
    public boolean check(String id_crew)
    {
        for(Crew cr : Crewl)
        {
            if(cr.getId_crew().equals(id_crew))
            {
                return true;
            }
        }
        return false;
    }
    public ArrayList<Crew> search(String id_crew)
    {
        ArrayList<Crew> search = new ArrayList<>();
        id_crew = id_crew.isEmpty()?id_crew = "": id_crew;
        
        for(Crew cr : Crewl)
        {
            if( cr.getId_crew().contains(id_crew)) 
                
            {
                search.add(cr);
            }
        }
        return search;
    }
    public ArrayList<Crew> getList() {
        listCrew();
        return Crewl;
    }

   
}