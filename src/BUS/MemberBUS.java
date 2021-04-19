/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DATA.MemberDAO;
import DTO.Member;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class MemberBUS {
    private ArrayList<Member> Memberl ;
    public MemberBUS(int i)
    {
        listMember();
    }
    
    public MemberBUS()
    {
        
    }
    public Member get(String id_member)
    {
        for(Member kh : Memberl )
        {
            if(kh.getId_member().equals(id_member))
            {
                return kh;
            }
        }
        return null;
    }
    public void listMember()
    {
        MemberDAO MemberDAO = new MemberDAO();
        Memberl = new ArrayList<>();
        Memberl = MemberDAO.list();
    }
    public void addKH(Member a)
    {
        Memberl.add(a);
        MemberDAO memberDAO = new MemberDAO();
        memberDAO.add(a);
    }

    public void deleteKH(String id_member)
    {
        for(Member kh : Memberl )
        {
            if(kh.getId_member().equals(id_member))
            {
                Memberl.remove(kh);
                MemberDAO memberDAO = new MemberDAO();
                memberDAO.delete(id_member);
                return;
            }
        }
    }
    public void refeshKH(String id_member)
    {
        for(Member kh : Memberl )
        {
            if(kh.getId_member().equals(id_member))
            {
                Memberl.remove(kh);
                MemberDAO memberDAO = new MemberDAO();
                memberDAO.refesh(id_member);
                return;
            }
        }
    }
    public void setNV(Member s)
    {
        for(int i = 0 ; i < Memberl.size() ; i++)
        {
            if(Memberl.get(i).getId_member().equals(s.getId_member()))
            {
                Memberl.set(i, s);
                MemberDAO memberDAO = new MemberDAO();
                memberDAO.set(s);
                return;
            }
        }
    }
    public boolean check(String id_member)
    {
        for(Member kh : Memberl)
        {
            if(kh.getId_member().equals(id_member))
            {
                return true;
            }
        }
        return false;
    }
    public ArrayList<Member> search(String id_member,String name_member,String phonenumber,String brand_member)
    {
        ArrayList<Member> search = new ArrayList<>();
        id_member = id_member.isEmpty()?id_member = "": id_member;
        name_member = name_member.isEmpty()?name_member = "": name_member;
        phonenumber = phonenumber.isEmpty()?phonenumber = "": phonenumber;
        brand_member = brand_member.isEmpty()?brand_member = "": brand_member;
        for(Member kh : Memberl)
        {
            if( kh.getId_member().contains(id_member) && 
                kh.getName_member().contains(name_member) &&
                kh.getPhonenumber().contains(phonenumber) &&
                kh.getBrand_member().contains(brand_member))
            {
                search.add(kh);
            }
        }
        return search;
    }
    public ArrayList<Member> getList() {
        listMember();
        return Memberl;
    }
}