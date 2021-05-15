/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.BillBUS;
import BUS.Bill_DetailBUS;
import BUS.NhanvienBUS;
import DATA.NhanvienDAO;
import DTO.Bill;
import DTO.Bill_Detail;
import DTO.Nhanvien;
import DTO.Nhanvien;
import com.mysql.jdbc.PreparedStatement;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.table.DefaultTableModel;



/**
 *
 * @author Admin
 */
public class AdminView extends javax.swing.JFrame {
    public NhanvienBUS nhanvienBUS = new NhanvienBUS();
    public BillBUS billBus = new BillBUS();
    public Bill_DetailBUS bill_detailBus = new Bill_DetailBUS();
    public DefaultTableModel model;
    public DefaultComboBoxModel boxdate;
    public DefaultComboBoxModel boxdate1;
    public DefaultComboBoxModel boxdate2;
    public DefaultComboBoxModel boxmounth;
    public DefaultComboBoxModel boxyear;
    int a =0;
    String b;
    String sluong;
    NhanvienDAO crd = new NhanvienDAO();
  

    /**
     * Creates new form AdminView
     */
    
    Border emptyBorder = BorderFactory.createEmptyBorder();
    CardLayout CardLayout; 
    
    public AdminView() {
        initComponents();
        CardLayout = (CardLayout)(pnlCards.getLayout());
        Component [] components = this.getContentPane().getComponents();
        for (Component component : components)
        {
            if (component instanceof JButton)
            {
                ((JButton) component).setUI(new BasicButtonUI());
            }
        }
        Chepdulieu();
        Chepdulieudate();
        ChepdulieuSales();
        
    }

    public void changeMainInfo(){
      
    }
    
    public String getDate(JComboBox model){
        String date_get = model.getSelectedItem().toString();
        String date1_get = date_get.substring(date_get.lastIndexOf("-") + 1);
        return date1_get;
    }
    public String getDate_data(String data){
        String date_data_get = data;
        String date_data = date_data_get.substring(date_data_get.lastIndexOf("-") + 1);
        return date_data;
    }
    
    public String getMounth(JComboBox model){
        String date_get = model.getSelectedItem().toString();
        String mounth1_get = date_get.substring(date_get.indexOf("-") + 1 , date_get.lastIndexOf("-"));
  
        return mounth1_get;
    }
    
    public String getMounth_data(String data){
        String date_data_get = data;
        String mounth_data = date_data_get.substring(date_data_get.indexOf("-") + 1 , date_data_get.lastIndexOf("-"));
        return mounth_data;
    }
    
    public String getYear(JComboBox model){
        String date_get = model.getSelectedItem().toString();
        String year1_get = date_get.substring(0, date_get.indexOf("-"));
        
        return year1_get;
    }
    
    public String getYear_data(String data){
        String date_data_get = data;
        String year_data = date_data_get.substring(0, date_data_get.indexOf("-"));
        return year_data;
    }
    
    
    public void outModel(DefaultTableModel model , ArrayList<Nhanvien> nv, int a) // Xuất ra Table từ ArrayList
    {
        model = (DefaultTableModel) tblCrew.getModel();
        Vector data;
        model.setRowCount(0);
        for(Nhanvien c: nv)
        {
            if(c.getStatus() == a)
            {
            data = new Vector();
            data.add(c.getId_nhanvien());
            data.add(c.getHoNV());
            data.add(c.getTenNV());
            data.add(c.getPhoneNV());
            data.add(c.getLuong());
            data.add(c.getStatus());

            model.addRow(data);
            }
        }
        tblCrew.setModel(model);
        model.fireTableDataChanged();
        
    }
    //lay ngay
    public void date(ArrayList<Bill> bill)
    {
         boxdate = new DefaultComboBoxModel();
         boxdate1 = new DefaultComboBoxModel();
         boxmounth = new DefaultComboBoxModel();
         boxyear = new DefaultComboBoxModel();
         String date = "";
         for(Bill b: bill)
        {
            //lay ngay trong database
            String date_data = b.getId_combo();
            if(!date.equals(date_data))    
            boxdate.addElement(date_data);
            date = date_data;
        }
         jcomboDate.setModel(boxdate);
         
         String date1 = "";
         for(Bill b: bill)
        {
            //lay ngay trong database
            String date_data = b.getId_combo();
            if(!date1.equals(date_data))    
            boxdate1.addElement(date_data);
            date1 = date_data;
        }
         jComboDate1.setModel(boxdate1);
         
         String mounth = "";
         for(Bill b: bill)
        {
            //lay ngay trong database
            String mounth_data_get = b.getId_combo();
            String mounth_data = mounth_data_get.substring(0, mounth_data_get.indexOf("-") + 3);
            if(!mounth.equals(mounth_data))    
            boxmounth.addElement(mounth_data);
            mounth = mounth_data;
        }
        jComboMounth.setModel(boxmounth);
        String year = "";
         for(Bill b: bill)
        {
            //lay ngay trong database
            String year_data_get = b.getId_combo();
            String year_data = getYear_data(b.getId_combo());
            if(!year.equals(year_data))    
            boxyear.addElement(year_data);
            year = year_data;
        }
        jComboYear.setModel(boxyear);
        JComboYearQui.setModel(boxyear);
        
        
    }
    public void date2(ArrayList<Bill> bill){
        boxdate2 = new DefaultComboBoxModel();
        String date2 = "";
         for(Bill b: bill)
        {
            //lay ngay trong database
            String date_data_get = b.getId_combo();
            String year_data = getYear_data(b.getId_combo());
            String date_data = getDate_data(b.getId_combo());
            String mounth_data = getMounth_data(b.getId_combo());
            int a1 = Integer.parseInt(date_data);
            int b1 = Integer.parseInt(mounth_data);
            int c1 = Integer.parseInt(year_data);
            //lay ngay tren ngay 1
            String year1_get = getYear(jComboDate1);
            String date1_get = getDate(jComboDate1);
            String mounth1_get = getMounth(jComboDate1);
            int a2 = Integer.parseInt(date1_get);
            int b2 = Integer.parseInt(mounth1_get);
            int c2 = Integer.parseInt(year1_get);
            if(c1 > c2)
            {
                if(!date2.equals(date_data_get))    
                boxdate2.addElement(date_data_get);
                date2 = date_data_get;
                
            }
            else if(b1 > b2)
            {
                if(!date2.equals(date_data_get))    
                boxdate2.addElement(date_data_get);
                date2 = date_data_get;     
            }
            else if(b1 == b2)
            {
                if(a1 > a2)
                {
                    if(!date2.equals(date_data_get))    
                    boxdate2.addElement(date_data_get);
                    date2 = date_data_get;
                }
            }
            
        }
         jComboDate2.setModel(boxdate2);
         
         
    }
    //tinh tong tien
    public void tongtien(ArrayList<Bill> bill){
        //lay ngay tren combox ngay
        int tien = 0;
        for(Bill b: bill)
        {
            //lay ngay trong database
            String date_data_get = b.getId_combo();
            String year_data = getYear_data(b.getId_combo());
            String date_data = getDate_data(b.getId_combo());
            String mounth_data = getMounth_data(b.getId_combo());
            //kiem tra ngay trong database == ngay trong comboxngay
            String year1_get = getYear(jcomboDate);
            String date1_get = getDate(jcomboDate);
            String mounth1_get = getMounth(jcomboDate);    //
            if(date1_get.equals(date_data) && mounth1_get.equals(mounth_data) && year1_get.equals(year_data))
            {
            tien = tien + b.getSum();
            }
        }
        String tongtien = String.valueOf(tien);
        JTongTienNgay.setText(tongtien+"đ");
        //tien thang
        String mounth = jComboMounth.getSelectedItem().toString();
        int tien_mounth = 0;
        for(Bill b: bill)
        {
            //lay ngay trong database
            String mounth_data_get = b.getId_combo();
            String mounth_data = mounth_data_get.substring(0, mounth_data_get.indexOf("-") + 3);
            //kiem tra ngay trong database == ngay trong comboxngay
            if(mounth.equals(mounth_data))
            {
            tien_mounth = tien_mounth + b.getSum();
            }
        }
        String tongtien_mounth = String.valueOf(tien_mounth);
        JTongTienThang.setText(tongtien_mounth+"đ");
        //tien theo nam
        String year = jComboYear.getSelectedItem().toString();
        String year_get = JComboYearQui.getSelectedItem().toString();
        int tien_year = 0;
        int tien_qui = 0;
        for(Bill b: bill)
        {
            //lay ngay trong database
            String year_data_get = b.getId_combo();
            String year_data = getYear_data(b.getId_combo());
            String mounth_data = getMounth_data(b.getId_combo());
            int b1 = Integer.parseInt(mounth_data);
            //kiem tra ngay trong database == ngay trong comboxngay
            if(year.equals(year_data))
            {
            tien_year = tien_year + b.getSum();
            }
            //tinh tong tien dua tren quý
            if(year_get.equals(year_data))
            {
                if(b1 < 4  && jComboQui.getSelectedItem().equals("1"))
                    tien_qui = tien_qui + b.getSum();
                if(b1 > 3 && b1 < 7   && jComboQui.getSelectedItem().equals("2"))
                    tien_qui = tien_qui + b.getSum();
                if(b1 > 6 && b1 < 10   && jComboQui.getSelectedItem().equals("3"))
                    tien_qui = tien_qui + b.getSum();
                if(b1 > 9   && jComboQui.getSelectedItem().equals("4"))
                    tien_qui = tien_qui + b.getSum();
            }
        }
        JTongTienQui.setText(tien_qui+"đ");
        String tongtien_year = String.valueOf(tien_year);
        JTongTienNam.setText(tongtien_year+"đ");
        
        //tong tien theo khoang ngay 
        int tongtien_khoangngay = 0;
         for(Bill b: bill)
        {
            //lay ngay trong database
            String date_data_get = b.getId_combo();
            String date_data = getDate_data(b.getId_combo());
            String mounth_data = getMounth_data(b.getId_combo());
            int a1 = Integer.parseInt(date_data);
            int b1 = Integer.parseInt(mounth_data);
            //lay ngay tren ngay 1
            String date1_get = getDate(jComboDate1);
            String mounth1_get = getMounth(jComboDate1);
            int a2 = Integer.parseInt(date1_get);
            int b2 = Integer.parseInt(mounth1_get);
            //lay ngay tren ngay 2
            String date1_2_get = getDate(jComboDate2);
            String mounth2_get = getMounth(jComboDate2);
            int a3 = Integer.parseInt(date1_2_get);
            int b3 = Integer.parseInt(mounth2_get);
            if(b1 == b2 && b1 == b3)
            {
                if(a1 >= a2 && a1 <= a3)
                {
                    tongtien_khoangngay = tongtien_khoangngay + b.getSum();
                }    
            }
            else if(b1 >= b2 && b1 < b3)
            {
                if(a1 >= a2)
                tongtien_khoangngay = tongtien_khoangngay + b.getSum();
            }
            else if(b1 > b2 && b1 <= b3)
            {
                if(a1 <= a3)
                tongtien_khoangngay = tongtien_khoangngay + b.getSum();
            }
            JTongTienKhoangNgay.setText(tongtien_khoangngay +"đ");
            
        }
    }
    public void MonSale(ArrayList<Bill> bill,ArrayList<Bill_Detail> bill_Detail)
    {
        //lay ngay de so sanh
        String dateget = jcomboDate.getSelectedItem().toString();
        String date = dateget.substring(dateget.lastIndexOf("-") + 1); 
        int id_mon = 0;
        int count = 0;
        for(Bill b: bill)
        {
            //lay ngay trong database
            String date_data_get = b.getId_combo();
            String year_data = getYear_data(b.getId_combo());
            String date_data = getDate_data(b.getId_combo());
            String mounth_data = getMounth_data(b.getId_combo());
            //kiem tra ngay trong database == ngay trong comboxngay
            String year1_get = getYear(jcomboDate);
            String date1_get = getDate(jcomboDate);
            String mounth1_get = getMounth(jcomboDate);    //
            if(date1_get.equals(date_data) && mounth1_get.equals(mounth_data) && year1_get.equals(year_data))
            {
               for(Bill_Detail bdetail: bill_Detail)
               {
                   //lay id cua hoadon va chitiethoa don so sanh
                   if(b.getId_bill().equals(bdetail.getId_bill()))
                   {
                       //lay id mon an
                       int slmon = bdetail.getQuantity_combo();
                       if(id_mon != slmon)
                       count++;
                       id_mon = slmon;
                   }
               }
            }
        }
        JTongMonNgay.setText(String.valueOf(count)+" Món");
        
        String mounth = jComboMounth.getSelectedItem().toString();   
        int count_mounth = 0;
        int id_mon_thang = 0;
        for(Bill b: bill)
        {
            //lay ngay trong database
            String mounth_data_get = b.getId_combo();
            String mounth_data = mounth_data_get.substring(0, mounth_data_get.indexOf("-") + 3);
            //so sanh ngay trong database va comboxngay
            if(mounth.equals(mounth_data))
            {
               for(Bill_Detail bdetail: bill_Detail)
               {
                   //lay id cua hoadon va chitiethoa don so sanh
                   if(b.getId_bill().equals(bdetail.getId_bill()))
                   {
                       //lay id mon an
                       int slmon = bdetail.getQuantity_combo();
                       if(id_mon_thang != slmon)
                       count_mounth++;
                       id_mon_thang = slmon;
                   }
               }
            }
        }
        jTongMonThang.setText(String.valueOf(count_mounth)+" Món");
        
        String year = jComboYear.getSelectedItem().toString();   
        int count_year = 0;
        int id_mon_nam = 0;
        for(Bill b: bill)
        {
            //lay ngay trong database
            String year_data_get = b.getId_combo();
            String year_data = getYear_data(b.getId_combo());
            //so sanh ngay trong database va comboxngay
            if(year.equals(year_data))
            {
               for(Bill_Detail bdetail: bill_Detail)
               {
                   //lay id cua hoadon va chitiethoa don so sanh
                   if(b.getId_bill().equals(bdetail.getId_bill()))
                   {
                       //lay id mon an
                       int slmon = bdetail.getQuantity_combo();
                       if(id_mon_nam != slmon)
                       count_year++;
                       id_mon_nam = slmon;
                   }
               }
            }
        }
        jTongMonNam.setText(String.valueOf(count_year)+" Món");
    }
    public void sl() throws SQLException
    {
        model = (DefaultTableModel) tblCrew.getModel();
        a = model.getRowCount();
        System.out.println(a);
        for (int i=0;i<a;i++){
            b = (String) model.getValueAt(i, 0);
            String asd = crd.getid(b);
            System.out.print(b);
            System.out.print("  ");
            System.out.println(asd);
        }
        
        
    }

    public void Chepdulieu() // Chép ArrayList lên table
    {
       
        if(nhanvienBUS.getList()== null)nhanvienBUS.listNhanvien();
        ArrayList<Nhanvien> nv = nhanvienBUS.getList();
//        model.setRowCount(0);
        outModel(model,nv,1);
    }
    public void Chepdulieudate(){
        if(billBus.getList()== null)billBus.listBill();
        ArrayList<Bill> bill = billBus.getList();
        date(bill);    
        date2(bill);
    }
    public void ChepdulieuSales(){
        if(billBus.getList()== null)billBus.listBill();
        ArrayList<Bill> bill = billBus.getList();
        if(bill_detailBus.getList()== null)bill_detailBus.listBill_Detail();
        ArrayList<Bill_Detail> bildetail = bill_detailBus.getList();
        tongtien(bill);
        MonSale(bill, bildetail);
    }
    public void chepdulieudate2(){
        if(billBus.getList()== null)billBus.listBill();
        ArrayList<Bill> bill = billBus.getList();
        date2(bill);    
    }

    public void Chepdulieuxoa() // Chép ArrayList lên table
    {
       
        if(nhanvienBUS.getList()== null)nhanvienBUS.listNhanvien();
        ArrayList<Nhanvien> nv = nhanvienBUS.getList();
//        model.setRowCount(0);
        outModel(model,nv,0);
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPasswordField1 = new javax.swing.JPasswordField();
        jSplitPane2 = new javax.swing.JSplitPane();
        MenuAdminView = new javax.swing.JPanel();
        btnCrew = new javax.swing.JButton();
        btnStatistic = new javax.swing.JButton();
        btnFood = new javax.swing.JButton();
        btnWarehouse = new javax.swing.JButton();
        btnSale = new javax.swing.JButton();
        btnCustomer = new javax.swing.JButton();
        btnSupplier = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        pnlCards = new javax.swing.JPanel();
        pnlCrewCard = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        btnAddcrew1 = new javax.swing.JButton();
        btnEditcrew1 = new javax.swing.JButton();
        btnDeletecrew1 = new javax.swing.JButton();
        btnRefeshCrew1 = new javax.swing.JButton();
        btnNewCrew2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCrew = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtId_crew = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtSalary = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtName_crew = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        lbShift = new javax.swing.JLabel();
        cmPosition = new javax.swing.JComboBox<>();
        cmSex = new javax.swing.JComboBox<>();
        cmShift = new javax.swing.JComboBox<>();
        btnMiniaddcrew = new javax.swing.JButton();
        btnAddcrew = new javax.swing.JButton();
        txtSearchId_crew = new javax.swing.JTextField();
        txtSearchName_crew = new javax.swing.JTextField();
        txtSearchSex = new javax.swing.JTextField();
        txtSearchPosition = new javax.swing.JTextField();
        txtSearchPhone = new javax.swing.JTextField();
        txtSearchShift = new javax.swing.JTextField();
        txtSearchSalary = new javax.swing.JTextField();
        txtSearchStatus_crew = new javax.swing.JTextField();
        btnEditcrew = new javax.swing.JButton();
        btnDeletecrew = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnRefeshCrew = new javax.swing.JButton();
        btnNewCrew = new javax.swing.JButton();
        pnlStatCard = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel9 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        btnAddcrew2 = new javax.swing.JButton();
        btnEditcrew2 = new javax.swing.JButton();
        btnDeletecrew2 = new javax.swing.JButton();
        btnRefeshCrew2 = new javax.swing.JButton();
        btnNewCrew1 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        JTongMonNgay = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jTongMonNam = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jComboDate1 = new javax.swing.JComboBox<>();
        jLabel52 = new javax.swing.JLabel();
        jComboQui = new javax.swing.JComboBox<>();
        jcomboDate = new javax.swing.JComboBox<>();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jComboDate2 = new javax.swing.JComboBox<>();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jComboMounth = new javax.swing.JComboBox<>();
        jComboYear = new javax.swing.JComboBox<>();
        jLabel69 = new javax.swing.JLabel();
        jComboCrew = new javax.swing.JComboBox<>();
        JTongTienNgay = new javax.swing.JLabel();
        JTongTienThang = new javax.swing.JLabel();
        jTongMonThang = new javax.swing.JLabel();
        JTongTienNam = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        JComboYearQui = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        JTongTienKhoangNgay = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        JTongTienNhanVien = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        JTongTienQui = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
        pnlDishCard = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jLabel59 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jTextField24 = new javax.swing.JTextField();
        jTextField25 = new javax.swing.JTextField();
        jTextField26 = new javax.swing.JTextField();
        jTextField27 = new javax.swing.JTextField();
        jTextField28 = new javax.swing.JTextField();
        jTextField29 = new javax.swing.JTextField();
        jTextField30 = new javax.swing.JTextField();
        jTextField31 = new javax.swing.JTextField();
        btnAddcrew3 = new javax.swing.JButton();
        btnEditcrew3 = new javax.swing.JButton();
        btnDeletecrew3 = new javax.swing.JButton();
        btnRefeshCrew3 = new javax.swing.JButton();
        btnNewCrew3 = new javax.swing.JButton();
        pnlStorageCard = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable9 = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        jTextField47 = new javax.swing.JTextField();
        jTextField48 = new javax.swing.JTextField();
        jTextField49 = new javax.swing.JTextField();
        jTextField50 = new javax.swing.JTextField();
        btnAddcrew5 = new javax.swing.JButton();
        btnEditcrew5 = new javax.swing.JButton();
        btnDeletecrew5 = new javax.swing.JButton();
        btnRefeshCrew5 = new javax.swing.JButton();
        btnNewCrew5 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTable8 = new javax.swing.JTable();
        btnAddcrew4 = new javax.swing.JButton();
        btnEditcrew4 = new javax.swing.JButton();
        btnDeletecrew4 = new javax.swing.JButton();
        btnRefeshCrew4 = new javax.swing.JButton();
        btnNewCrew4 = new javax.swing.JButton();
        jTextField22 = new javax.swing.JTextField();
        jTextField23 = new javax.swing.JTextField();
        jTextField51 = new javax.swing.JTextField();
        jTextField52 = new javax.swing.JTextField();
        pnlSupplierCard = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jTextField4 = new javax.swing.JTextField();
        jTextField32 = new javax.swing.JTextField();
        jTextField33 = new javax.swing.JTextField();
        jTextField34 = new javax.swing.JTextField();
        btnAddcrew6 = new javax.swing.JButton();
        btnEditcrew6 = new javax.swing.JButton();
        btnDeletecrew6 = new javax.swing.JButton();
        btnRefeshCrew6 = new javax.swing.JButton();
        btnNewCrew6 = new javax.swing.JButton();
        pnlSaleCard = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jTextField35 = new javax.swing.JTextField();
        jTextField36 = new javax.swing.JTextField();
        jTextField37 = new javax.swing.JTextField();
        jTextField38 = new javax.swing.JTextField();
        btnAddcrew7 = new javax.swing.JButton();
        btnEditcrew7 = new javax.swing.JButton();
        btnDeletecrew7 = new javax.swing.JButton();
        btnRefeshCrew7 = new javax.swing.JButton();
        btnNewCrew7 = new javax.swing.JButton();
        pnlMemberCard = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable10 = new javax.swing.JTable();
        jTextField39 = new javax.swing.JTextField();
        jTextField40 = new javax.swing.JTextField();
        jTextField41 = new javax.swing.JTextField();
        jTextField42 = new javax.swing.JTextField();
        jButton24 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();

        jPasswordField1.setText("jPasswordField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane2.setDividerLocation(170);
        jSplitPane2.setDividerSize(1);
        jSplitPane2.setAutoscrolls(true);
        jSplitPane2.setMinimumSize(new java.awt.Dimension(200, 102));
        jSplitPane2.setOpaque(false);
        jSplitPane2.setPreferredSize(new java.awt.Dimension(1200, 750));

        MenuAdminView.setBackground(new java.awt.Color(102, 102, 102));
        MenuAdminView.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        MenuAdminView.setPreferredSize(new java.awt.Dimension(200, 750));
        MenuAdminView.setRequestFocusEnabled(false);
        MenuAdminView.setVerifyInputWhenFocusTarget(false);

        btnCrew.setBackground(new java.awt.Color(204, 204, 204));
        btnCrew.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCrew.setForeground(new java.awt.Color(255, 255, 255));
        btnCrew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/staffbutton.png"))); // NOI18N
        btnCrew.setText("NHÂN VIÊN");
        btnCrew.setBorder(null);
        btnCrew.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCrew.setPreferredSize(new java.awt.Dimension(150, 40));
        btnCrew.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/staffbuttonpressed.png"))); // NOI18N
        btnCrew.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/staffbuttonhover.png"))); // NOI18N
        btnCrew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrewActionPerformed(evt);
            }
        });

        btnStatistic.setBackground(new java.awt.Color(204, 204, 204));
        btnStatistic.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnStatistic.setForeground(new java.awt.Color(255, 255, 255));
        btnStatistic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/statisticbutton.png"))); // NOI18N
        btnStatistic.setText("  THỐNG KÊ");
        btnStatistic.setBorder(null);
        btnStatistic.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnStatistic.setPreferredSize(new java.awt.Dimension(150, 40));
        btnStatistic.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/statisticbuttonpressed.png"))); // NOI18N
        btnStatistic.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/statisticbuttonhover.png"))); // NOI18N
        btnStatistic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatisticActionPerformed(evt);
            }
        });

        btnFood.setBackground(new java.awt.Color(204, 204, 204));
        btnFood.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnFood.setForeground(new java.awt.Color(255, 255, 255));
        btnFood.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/foodbutton.png"))); // NOI18N
        btnFood.setText("MÓN ĂN");
        btnFood.setBorder(null);
        btnFood.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnFood.setPreferredSize(new java.awt.Dimension(150, 40));
        btnFood.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/foodbuttonpressed.png"))); // NOI18N
        btnFood.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/foodbuttonhover.png"))); // NOI18N
        btnFood.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFoodActionPerformed(evt);
            }
        });

        btnWarehouse.setBackground(new java.awt.Color(204, 204, 204));
        btnWarehouse.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnWarehouse.setForeground(new java.awt.Color(255, 255, 255));
        btnWarehouse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/warehousebutton.png"))); // NOI18N
        btnWarehouse.setText("KHO");
        btnWarehouse.setBorder(null);
        btnWarehouse.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnWarehouse.setPreferredSize(new java.awt.Dimension(150, 40));
        btnWarehouse.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/warehousepressed.png"))); // NOI18N
        btnWarehouse.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/warehousebuttonhover.png"))); // NOI18N
        btnWarehouse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnWarehouseActionPerformed(evt);
            }
        });

        btnSale.setBackground(new java.awt.Color(204, 204, 204));
        btnSale.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSale.setForeground(new java.awt.Color(255, 255, 255));
        btnSale.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/salebutton.png"))); // NOI18N
        btnSale.setText("SALE");
        btnSale.setBorder(null);
        btnSale.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSale.setPreferredSize(new java.awt.Dimension(150, 40));
        btnSale.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/salebuttonpressed.png"))); // NOI18N
        btnSale.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/salebuttonhover.png"))); // NOI18N
        btnSale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaleActionPerformed(evt);
            }
        });

        btnCustomer.setBackground(new java.awt.Color(204, 204, 204));
        btnCustomer.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnCustomer.setForeground(new java.awt.Color(255, 255, 255));
        btnCustomer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/customerbutton.png"))); // NOI18N
        btnCustomer.setText("       KHÁCH HÀNG");
        btnCustomer.setBorder(null);
        btnCustomer.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCustomer.setPreferredSize(new java.awt.Dimension(150, 40));
        btnCustomer.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/customerbuttonpressed.png"))); // NOI18N
        btnCustomer.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/customerbuttonhover.png"))); // NOI18N
        btnCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCustomerActionPerformed(evt);
            }
        });

        btnSupplier.setBackground(new java.awt.Color(204, 204, 204));
        btnSupplier.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSupplier.setForeground(new java.awt.Color(255, 255, 255));
        btnSupplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/supplierbutton.png"))); // NOI18N
        btnSupplier.setText("           NHÀ CUNG CẤP");
        btnSupplier.setBorder(null);
        btnSupplier.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSupplier.setPreferredSize(new java.awt.Dimension(150, 40));
        btnSupplier.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/supplierbuttonpressed.png"))); // NOI18N
        btnSupplier.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/supplierbuttonhover.png"))); // NOI18N
        btnSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSupplierActionPerformed(evt);
            }
        });

        jTextField2.setText("jTextField2");

        javax.swing.GroupLayout MenuAdminViewLayout = new javax.swing.GroupLayout(MenuAdminView);
        MenuAdminView.setLayout(MenuAdminViewLayout);
        MenuAdminViewLayout.setHorizontalGroup(
            MenuAdminViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuAdminViewLayout.createSequentialGroup()
                .addGroup(MenuAdminViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MenuAdminViewLayout.createSequentialGroup()
                        .addComponent(btnCrew, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnStatistic, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFood, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnWarehouse, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSale, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        MenuAdminViewLayout.setVerticalGroup(
            MenuAdminViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MenuAdminViewLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(MenuAdminViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCrew, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnStatistic, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnFood, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnWarehouse, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSale, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(251, Short.MAX_VALUE))
        );

        jSplitPane2.setLeftComponent(MenuAdminView);

        pnlCards.setBackground(new java.awt.Color(255, 255, 255));
        pnlCards.setLayout(new java.awt.CardLayout());

        pnlCrewCard.setBackground(new java.awt.Color(255, 255, 255));
        pnlCrewCard.setPreferredSize(new java.awt.Dimension(900, 765));

        jTabbedPane1.setBackground(new java.awt.Color(0, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jPanel3.setBackground(new java.awt.Color(51, 204, 255));

        jPanel6.setBackground(new java.awt.Color(51, 204, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255), 3));

        jLabel11.setText("jLabel11");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addContainerGap(140, Short.MAX_VALUE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Tài Khoản", "Tên Nhân Viên", "Tài Khoản", "Mật Khẩu", "Chức Vụ"
            }
        ));
        jTable1.setRowHeight(30);
        jScrollPane2.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel12.setText("IMG");
        jLabel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        btnAddcrew1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnAddcrew1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/addbutton.png"))); // NOI18N
        btnAddcrew1.setText("THÊM");
        btnAddcrew1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAddcrew1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/addbuttonpressed.png"))); // NOI18N
        btnAddcrew1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/addbuttonhover.png"))); // NOI18N
        btnAddcrew1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddcrew1ActionPerformed(evt);
            }
        });

        btnEditcrew1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnEditcrew1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/editbutton.png"))); // NOI18N
        btnEditcrew1.setText("SỬA");
        btnEditcrew1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEditcrew1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/editbuttonpressed.png"))); // NOI18N
        btnEditcrew1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/editbuttonhover.png"))); // NOI18N
        btnEditcrew1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditcrew1ActionPerformed(evt);
            }
        });

        btnDeletecrew1.setBackground(new java.awt.Color(255, 0, 0));
        btnDeletecrew1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnDeletecrew1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/deletebutton.png"))); // NOI18N
        btnDeletecrew1.setText("XÓA");
        btnDeletecrew1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDeletecrew1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/deletebuttonpressed.png"))); // NOI18N
        btnDeletecrew1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/deletebuttonhover.png"))); // NOI18N
        btnDeletecrew1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletecrew1ActionPerformed(evt);
            }
        });

        btnRefeshCrew1.setBackground(new java.awt.Color(255, 51, 255));
        btnRefeshCrew1.setText("KHÔI PHỤC");
        btnRefeshCrew1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefeshCrew1ActionPerformed(evt);
            }
        });

        btnNewCrew2.setText("LÀM MỚI");
        btnNewCrew2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewCrew2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1015, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnRefeshCrew1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDeletecrew1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditcrew1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnAddcrew1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(176, 176, 176))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 741, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(87, 87, 87))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNewCrew2, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNewCrew2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnRefeshCrew1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAddcrew1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEditcrew1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDeletecrew1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Quản Lý Tài Khoản", jPanel3);

        jPanel1.setBackground(new java.awt.Color(51, 204, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tblCrew.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Nhân Viên", "Tên Nhân Viên", "Giới tính", "Loại Nhân Viên", "Số Điện Thoại", "Ca Làm", "Lương", "img"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, true, false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblCrew.setRowHeight(30);
        tblCrew.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblCrewMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCrew);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 1010, 160));

        jPanel5.setBackground(new java.awt.Color(51, 204, 255));
        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 255), 2, true));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Mã Nhân Viên");

        txtId_crew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtId_crewActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Chức vụ");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Lương");

        txtSalary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSalaryActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Tên Nhân Viên");

        txtName_crew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtName_crewActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Số Điện Thoại");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Giới Tính");

        lbShift.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbShift.setText("Ca Làm Việc");

        cmPosition.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "thu ngân", "phục vụ", "đầu bếp" }));

        cmSex.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ" }));

        cmShift.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3" }));
        cmShift.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmShiftActionPerformed(evt);
            }
        });

        btnMiniaddcrew.setText("...");
        btnMiniaddcrew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMiniaddcrewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(68, 68, 68)
                        .addComponent(txtSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtId_crew, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                            .addComponent(cmPosition, 0, 0, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtName_crew, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmSex, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPhone))))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(lbShift)
                        .addGap(18, 18, 18)
                        .addComponent(cmShift, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMiniaddcrew, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtId_crew, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtName_crew, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbShift)
                    .addComponent(cmShift, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cmPosition, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel3)
                    .addComponent(cmSex, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMiniaddcrew))
                .addGap(22, 22, 22))
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 660, 180));

        btnAddcrew.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnAddcrew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/addbutton.png"))); // NOI18N
        btnAddcrew.setText("THÊM");
        btnAddcrew.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAddcrew.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/addbuttonpressed.png"))); // NOI18N
        btnAddcrew.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/addbuttonhover.png"))); // NOI18N
        btnAddcrew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddcrewActionPerformed(evt);
            }
        });
        jPanel1.add(btnAddcrew, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 480, 189, 80));

        txtSearchId_crew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchId_crewActionPerformed(evt);
            }
        });
        txtSearchId_crew.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchId_crewKeyReleased(evt);
            }
        });
        jPanel1.add(txtSearchId_crew, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 125, 39));
        jPanel1.add(txtSearchName_crew, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, 130, 39));
        jPanel1.add(txtSearchSex, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 270, 130, 39));
        jPanel1.add(txtSearchPosition, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 270, 121, 39));
        jPanel1.add(txtSearchPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 270, 130, 39));
        jPanel1.add(txtSearchShift, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 270, 121, 39));
        jPanel1.add(txtSearchSalary, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 270, 130, 39));
        jPanel1.add(txtSearchStatus_crew, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 270, 130, 39));

        btnEditcrew.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnEditcrew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/editbutton.png"))); // NOI18N
        btnEditcrew.setText("SỬA");
        btnEditcrew.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEditcrew.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/editbuttonpressed.png"))); // NOI18N
        btnEditcrew.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/editbuttonhover.png"))); // NOI18N
        btnEditcrew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditcrewActionPerformed(evt);
            }
        });
        jPanel1.add(btnEditcrew, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 480, 189, 78));

        btnDeletecrew.setBackground(new java.awt.Color(255, 0, 0));
        btnDeletecrew.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnDeletecrew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/deletebutton.png"))); // NOI18N
        btnDeletecrew.setText("XÓA");
        btnDeletecrew.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDeletecrew.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/deletebuttonpressed.png"))); // NOI18N
        btnDeletecrew.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/deletebuttonhover.png"))); // NOI18N
        btnDeletecrew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletecrewActionPerformed(evt);
            }
        });
        jPanel1.add(btnDeletecrew, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 480, 189, 78));

        jLabel6.setText(".");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 287, 121, -1));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("image");
        jLabel9.setToolTipText("");
        jLabel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 310, 180));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/searchbutton.png"))); // NOI18N
        jButton1.setText("     TÌM KIẾM");
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/searchbuttonpressed.png"))); // NOI18N
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/searchbuttonhover.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 480, 189, 78));

        btnRefeshCrew.setBackground(new java.awt.Color(255, 51, 255));
        btnRefeshCrew.setText("KHÔI PHỤC");
        btnRefeshCrew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefeshCrewActionPerformed(evt);
            }
        });
        jPanel1.add(btnRefeshCrew, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 189, 78));

        btnNewCrew.setText("LÀM MỚI");
        btnNewCrew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewCrewActionPerformed(evt);
            }
        });
        jPanel1.add(btnNewCrew, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 210, 220, 50));

        jTabbedPane1.addTab("Quản Lý Nhân Viên", jPanel1);

        javax.swing.GroupLayout pnlCrewCardLayout = new javax.swing.GroupLayout(pnlCrewCard);
        pnlCrewCard.setLayout(pnlCrewCardLayout);
        pnlCrewCardLayout.setHorizontalGroup(
            pnlCrewCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        pnlCrewCardLayout.setVerticalGroup(
            pnlCrewCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)
        );

        pnlCards.add(pnlCrewCard, "pnlCard1");

        pnlStatCard.setBackground(new java.awt.Color(255, 255, 255));

        jPanel9.setBackground(new java.awt.Color(51, 204, 255));

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable4.setRowHeight(30);
        jScrollPane5.setViewportView(jTable4);

        btnAddcrew2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnAddcrew2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/addbutton.png"))); // NOI18N
        btnAddcrew2.setText("THÊM");
        btnAddcrew2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAddcrew2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/addbuttonpressed.png"))); // NOI18N
        btnAddcrew2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/addbuttonhover.png"))); // NOI18N
        btnAddcrew2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddcrew2ActionPerformed(evt);
            }
        });

        btnEditcrew2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnEditcrew2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/editbutton.png"))); // NOI18N
        btnEditcrew2.setText("SỬA");
        btnEditcrew2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEditcrew2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/editbuttonpressed.png"))); // NOI18N
        btnEditcrew2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/editbuttonhover.png"))); // NOI18N
        btnEditcrew2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditcrew2ActionPerformed(evt);
            }
        });

        btnDeletecrew2.setBackground(new java.awt.Color(255, 0, 0));
        btnDeletecrew2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnDeletecrew2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/deletebutton.png"))); // NOI18N
        btnDeletecrew2.setText("XÓA");
        btnDeletecrew2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDeletecrew2.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/deletebuttonpressed.png"))); // NOI18N
        btnDeletecrew2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/deletebuttonhover.png"))); // NOI18N
        btnDeletecrew2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletecrew2ActionPerformed(evt);
            }
        });

        btnRefeshCrew2.setBackground(new java.awt.Color(255, 51, 255));
        btnRefeshCrew2.setText("KHÔI PHỤC");
        btnRefeshCrew2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefeshCrew2ActionPerformed(evt);
            }
        });

        btnNewCrew1.setText("LÀM MỚI");
        btnNewCrew1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewCrew1ActionPerformed(evt);
            }
        });

        jPanel12.setBackground(new java.awt.Color(51, 204, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 204), 3));

        jLabel10.setText("jLabel10");

        jTextField1.setText("jTextField1");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel22.setText("jLabel22");

        jTextField5.setText("jTextField5");

        jLabel33.setText("jLabel33");

        jTextField11.setText("jTextField11");

        jLabel34.setText("jLabel34");

        jLabel35.setText("jLabel35");

        jLabel36.setText("jLabel36");

        jTextField16.setText("jTextField16");

        jTextField17.setText("jTextField17");

        jTextField18.setText("jTextField18");

        jLabel37.setText("jLabel37");

        jLabel38.setText("jLabel38");

        jLabel51.setText("jLabel51");

        jTextField19.setText("jTextField19");

        jTextField20.setText("jTextField20");

        jTextField21.setText("jTextField21");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel22)
                    .addComponent(jLabel33))
                .addGap(30, 30, 30)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(208, 208, 208)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel34)
                    .addComponent(jLabel35)
                    .addComponent(jLabel36))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel51))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel38))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                        .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(216, 216, 216)
                        .addComponent(jLabel37)))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(220, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel37)
                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel38)
                    .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel36)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel51)
                    .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(btnRefeshCrew2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(604, 604, 604))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(btnDeletecrew2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49)
                        .addComponent(btnEditcrew2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAddcrew2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnNewCrew1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1057, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNewCrew1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAddcrew2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEditcrew2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDeletecrew2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnRefeshCrew2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane2.addTab("Nhập Hàng", jPanel9);

        jPanel10.setBackground(new java.awt.Color(51, 204, 255));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Ngày lập phiếu", "Tên khách", "Khuyến mãi", "Tổng tiền"
            }
        ));
        jTable2.setRowHeight(30);
        jScrollPane3.setViewportView(jTable2);

        jTextField13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField13ActionPerformed(evt);
            }
        });

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel39.setText("...");

        jLabel41.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel41.setText("Tổng cộng");

        jPanel18.setBackground(new java.awt.Color(51, 204, 255));
        jPanel18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255), 3));

        jLabel28.setBackground(new java.awt.Color(0, 0, 0));
        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setText("Doanh Số Bán Theo Ngày");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("Đã Bán Được:");

        JTongMonNgay.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        JTongMonNgay.setText("...");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel23.setText("Tổng Tiền Thu Được:");

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel31.setText("Từ Ngày");

        jLabel29.setBackground(new java.awt.Color(0, 0, 0));
        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setText("Doanh Số Bán Theo Tháng");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("Đã Bán Được:");

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel24.setText("Tổng Tiền Thu Được:");

        jLabel30.setBackground(new java.awt.Color(0, 0, 0));
        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setText("Doanh Số Bán Theo Năm");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Đã Bán Được:");

        jTongMonNam.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTongMonNam.setText("...");

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel32.setText("Tổng Tiền Thu Được:");

        jComboDate1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboDate1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboDate1ItemStateChanged(evt);
            }
        });

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel52.setText("Đến Ngày");

        jComboQui.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));
        jComboQui.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboQuiItemStateChanged(evt);
            }
        });
        jComboQui.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboQuiActionPerformed(evt);
            }
        });

        jcomboDate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcomboDate.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcomboDateItemStateChanged(evt);
            }
        });
        jcomboDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcomboDateActionPerformed(evt);
            }
        });

        jLabel65.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel65.setText("Chọn Tháng");

        jLabel66.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel66.setText("Quý");

        jComboDate2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboDate2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboDate2ItemStateChanged(evt);
            }
        });
        jComboDate2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboDate2ActionPerformed(evt);
            }
        });

        jLabel67.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel67.setText("Chọn Năm");

        jLabel68.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel68.setText("Chọn Ngày");

        jComboMounth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboMounth.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboMounthItemStateChanged(evt);
            }
        });
        jComboMounth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboMounthActionPerformed(evt);
            }
        });

        jComboYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboYearActionPerformed(evt);
            }
        });

        jLabel69.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel69.setText("Chọn Nhân Viên");

        jComboCrew.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        JTongTienNgay.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JTongTienNgay.setText("....");

        JTongTienThang.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JTongTienThang.setText("......");

        jTongMonThang.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jTongMonThang.setText(".......");

        JTongTienNam.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        JTongTienNam.setText(".....");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(153, 153, 0));
        jLabel8.setText("Thống kê trong khoảng ngày ");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(153, 153, 0));
        jLabel16.setText("Thống kê Theo nhân viên");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(153, 153, 0));
        jLabel17.setText("Thống kê Theo Quí");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setText("chọn năm:");

        JComboYearQui.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        JComboYearQui.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JComboYearQuiItemStateChanged(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("Tổng Tiền thu được:");

        JTongTienKhoangNgay.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        JTongTienKhoangNgay.setText(".....");

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel21.setText("Tổng tiền thu được:");

        JTongTienNhanVien.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        JTongTienNhanVien.setText("......");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel26.setText("Tổng tiền thu được:");

        JTongTienQui.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        JTongTienQui.setText("......");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel28)
                        .addGap(240, 240, 240))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JTongTienNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jcomboDate, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboDate1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(2, 2, 2)
                                .addComponent(JTongMonNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel8)
                            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel18Layout.createSequentialGroup()
                                    .addComponent(jLabel19)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(JTongTienKhoangNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel18Layout.createSequentialGroup()
                                    .addComponent(jLabel52)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jComboDate2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel18Layout.createSequentialGroup()
                                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel29)
                                            .addGroup(jPanel18Layout.createSequentialGroup()
                                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTongMonThang, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(169, 169, 169))
                                    .addGroup(jPanel18Layout.createSequentialGroup()
                                        .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(175, 175, 175)))
                                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel30)
                                    .addGroup(jPanel18Layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel18Layout.createSequentialGroup()
                                                .addComponent(jLabel15)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTongMonNam, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel18Layout.createSequentialGroup()
                                                .addComponent(jLabel32)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(JTongTienNam, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel18Layout.createSequentialGroup()
                                                .addComponent(jLabel18)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(JComboYearQui, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel18Layout.createSequentialGroup()
                                                .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jComboQui, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(JTongTienQui, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboMounth, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(193, 193, 193)
                                .addComponent(jLabel67)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jComboYear, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JTongTienThang, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jLabel69)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboCrew, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel18Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JTongTienNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel68)
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jcomboDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel65)
                        .addComponent(jComboMounth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel67)
                        .addComponent(jComboYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel24)
                        .addComponent(JTongTienThang))
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel23)
                        .addComponent(JTongTienNgay))
                    .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel32)
                        .addComponent(JTongTienNam)))
                .addGap(6, 6, 6)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jTongMonNam))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(JComboYearQui, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel66)
                            .addComponent(jComboQui, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel13)
                                .addComponent(JTongMonNgay))
                            .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel14)
                                .addComponent(jTongMonThang)))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel16))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(jComboDate1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel69)
                            .addComponent(jComboCrew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel52)
                            .addComponent(jComboDate2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(JTongTienKhoangNgay)))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(JTongTienNhanVien)))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel26)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JTongTienQui)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jLabel53.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel53.setText("Tổng cộng tiền");

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel54.setText("...");

        jButton15.setText("chi tiết");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField15)
                        .addGap(14, 14, 14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1009, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel41)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel39)
                                .addGap(107, 107, 107)
                                .addComponent(jLabel53)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel54)))
                        .addGap(78, 78, 78))))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 959, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton15)
                .addGap(157, 157, 157))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41)
                    .addComponent(jLabel39)
                    .addComponent(jLabel53)
                    .addComponent(jLabel54))
                .addContainerGap(200, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Chi Tiết Giao Dịch", jPanel10);

        javax.swing.GroupLayout pnlStatCardLayout = new javax.swing.GroupLayout(pnlStatCard);
        pnlStatCard.setLayout(pnlStatCardLayout);
        pnlStatCardLayout.setHorizontalGroup(
            pnlStatCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        pnlStatCardLayout.setVerticalGroup(
            pnlStatCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlStatCardLayout.createSequentialGroup()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 807, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnlCards.add(pnlStatCard, "pnlCard2");

        pnlDishCard.setBackground(new java.awt.Color(51, 204, 255));

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable5.setRowHeight(30);
        jScrollPane6.setViewportView(jTable5);

        jLabel59.setText("IMG");
        jLabel59.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255), 3));

        jLabel55.setText("jLabel55");

        jLabel56.setText("jLabel56");

        jLabel57.setText("jLabel57");

        jLabel58.setText("jLabel58");

        jLabel60.setText("jLabel60");

        jLabel61.setText("jLabel61");

        jLabel62.setText("jLabel62");

        jLabel63.setText("jLabel63");

        jLabel64.setText("jLabel64");

        jTextField3.setText("jTextField3");

        jTextField24.setText("jTextField24");

        jTextField25.setText("jTextField25");

        jTextField26.setText("jTextField26");

        jTextField27.setText("jTextField27");

        jTextField28.setText("jTextField28");

        jTextField29.setText("jTextField29");

        jTextField30.setText("jTextField30");

        jTextField31.setText("jTextField31");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel56)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel57)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel55)
                        .addGap(32, 32, 32)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(52, 52, 52)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel61)
                        .addGap(30, 30, 30)
                        .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel58)
                            .addComponent(jLabel60))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel62)
                    .addComponent(jLabel63)
                    .addComponent(jLabel64))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(jLabel58)
                    .addComponent(jLabel62)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel56)
                            .addComponent(jLabel60)
                            .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel63)
                            .addComponent(jTextField30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel57)
                            .addComponent(jLabel61)
                            .addComponent(jTextField28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel64)
                            .addComponent(jTextField31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnAddcrew3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnAddcrew3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/addbutton.png"))); // NOI18N
        btnAddcrew3.setText("THÊM");
        btnAddcrew3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAddcrew3.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/addbuttonpressed.png"))); // NOI18N
        btnAddcrew3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/addbuttonhover.png"))); // NOI18N
        btnAddcrew3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddcrew3ActionPerformed(evt);
            }
        });

        btnEditcrew3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnEditcrew3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/editbutton.png"))); // NOI18N
        btnEditcrew3.setText("SỬA");
        btnEditcrew3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEditcrew3.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/editbuttonpressed.png"))); // NOI18N
        btnEditcrew3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/editbuttonhover.png"))); // NOI18N
        btnEditcrew3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditcrew3ActionPerformed(evt);
            }
        });

        btnDeletecrew3.setBackground(new java.awt.Color(255, 0, 0));
        btnDeletecrew3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnDeletecrew3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/deletebutton.png"))); // NOI18N
        btnDeletecrew3.setText("XÓA");
        btnDeletecrew3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDeletecrew3.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/deletebuttonpressed.png"))); // NOI18N
        btnDeletecrew3.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/deletebuttonhover.png"))); // NOI18N
        btnDeletecrew3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletecrew3ActionPerformed(evt);
            }
        });

        btnRefeshCrew3.setBackground(new java.awt.Color(255, 51, 255));
        btnRefeshCrew3.setText("KHÔI PHỤC");
        btnRefeshCrew3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefeshCrew3ActionPerformed(evt);
            }
        });

        btnNewCrew3.setText("LÀM MỚI");
        btnNewCrew3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewCrew3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlDishCardLayout = new javax.swing.GroupLayout(pnlDishCard);
        pnlDishCard.setLayout(pnlDishCardLayout);
        pnlDishCardLayout.setHorizontalGroup(
            pnlDishCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDishCardLayout.createSequentialGroup()
                .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDishCardLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDishCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDishCardLayout.createSequentialGroup()
                        .addGap(0, 768, Short.MAX_VALUE)
                        .addComponent(btnNewCrew3, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDishCardLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRefeshCrew3, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDeletecrew3, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditcrew3, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAddcrew3, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        pnlDishCardLayout.setVerticalGroup(
            pnlDishCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlDishCardLayout.createSequentialGroup()
                .addGroup(pnlDishCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(46, 46, 46)
                .addComponent(btnNewCrew3, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlDishCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlDishCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnDeletecrew3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEditcrew3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAddcrew3, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnRefeshCrew3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35))
        );

        pnlCards.add(pnlDishCard, "pnlCard3");

        pnlStorageCard.setBackground(new java.awt.Color(255, 255, 255));

        jPanel11.setBackground(new java.awt.Color(51, 204, 255));

        jTable9.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane10.setViewportView(jTable9);

        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 204), 3));

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 188, Short.MAX_VALUE)
        );

        jTextField47.setText("jTextField47");

        jTextField48.setText("jTextField48");

        jTextField49.setText("jTextField49");

        jTextField50.setText("jTextField50");

        btnAddcrew5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnAddcrew5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/addbutton.png"))); // NOI18N
        btnAddcrew5.setText("THÊM");
        btnAddcrew5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAddcrew5.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/addbuttonpressed.png"))); // NOI18N
        btnAddcrew5.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/addbuttonhover.png"))); // NOI18N
        btnAddcrew5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddcrew5ActionPerformed(evt);
            }
        });

        btnEditcrew5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnEditcrew5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/editbutton.png"))); // NOI18N
        btnEditcrew5.setText("SỬA");
        btnEditcrew5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEditcrew5.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/editbuttonpressed.png"))); // NOI18N
        btnEditcrew5.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/editbuttonhover.png"))); // NOI18N
        btnEditcrew5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditcrew5ActionPerformed(evt);
            }
        });

        btnDeletecrew5.setBackground(new java.awt.Color(255, 0, 0));
        btnDeletecrew5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnDeletecrew5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/deletebutton.png"))); // NOI18N
        btnDeletecrew5.setText("XÓA");
        btnDeletecrew5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDeletecrew5.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/deletebuttonpressed.png"))); // NOI18N
        btnDeletecrew5.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/deletebuttonhover.png"))); // NOI18N
        btnDeletecrew5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletecrew5ActionPerformed(evt);
            }
        });

        btnRefeshCrew5.setBackground(new java.awt.Color(255, 51, 255));
        btnRefeshCrew5.setText("KHÔI PHỤC");
        btnRefeshCrew5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefeshCrew5ActionPerformed(evt);
            }
        });

        btnNewCrew5.setText("LÀM MỚI");
        btnNewCrew5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewCrew5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane10)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addComponent(btnRefeshCrew5, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDeletecrew5, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEditcrew5, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAddcrew5, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnNewCrew5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addComponent(jTextField50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 190, Short.MAX_VALUE)
                .addComponent(jTextField49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(193, 193, 193)
                .addComponent(jTextField48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(193, 193, 193)
                .addComponent(jTextField47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(195, 195, 195))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(btnNewCrew5, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAddcrew5, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEditcrew5, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDeletecrew5, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(btnRefeshCrew5, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Yêu Cầu Nhập Hàng", jPanel11);

        jPanel8.setBackground(new java.awt.Color(51, 204, 255));

        jPanel15.setBackground(new java.awt.Color(51, 204, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 204), 3));

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );

        jTable8.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane9.setViewportView(jTable8);

        btnAddcrew4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnAddcrew4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/addbutton.png"))); // NOI18N
        btnAddcrew4.setText("THÊM");
        btnAddcrew4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAddcrew4.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/addbuttonpressed.png"))); // NOI18N
        btnAddcrew4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/addbuttonhover.png"))); // NOI18N
        btnAddcrew4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddcrew4ActionPerformed(evt);
            }
        });

        btnEditcrew4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnEditcrew4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/editbutton.png"))); // NOI18N
        btnEditcrew4.setText("SỬA");
        btnEditcrew4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEditcrew4.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/editbuttonpressed.png"))); // NOI18N
        btnEditcrew4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/editbuttonhover.png"))); // NOI18N
        btnEditcrew4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditcrew4ActionPerformed(evt);
            }
        });

        btnDeletecrew4.setBackground(new java.awt.Color(255, 0, 0));
        btnDeletecrew4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnDeletecrew4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/deletebutton.png"))); // NOI18N
        btnDeletecrew4.setText("XÓA");
        btnDeletecrew4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDeletecrew4.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/deletebuttonpressed.png"))); // NOI18N
        btnDeletecrew4.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/deletebuttonhover.png"))); // NOI18N
        btnDeletecrew4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletecrew4ActionPerformed(evt);
            }
        });

        btnRefeshCrew4.setBackground(new java.awt.Color(255, 51, 255));
        btnRefeshCrew4.setText("KHÔI PHỤC");
        btnRefeshCrew4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefeshCrew4ActionPerformed(evt);
            }
        });

        btnNewCrew4.setText("LÀM MỚI");
        btnNewCrew4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewCrew4ActionPerformed(evt);
            }
        });

        jTextField22.setText("jTextField22");

        jTextField23.setText("jTextField23");

        jTextField51.setText("jTextField51");

        jTextField52.setText("jTextField52");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 1035, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(btnRefeshCrew4, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeletecrew4, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditcrew4, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddcrew4, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                        .addComponent(btnNewCrew4, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(168, 168, 168)
                .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(191, 191, 191)
                .addComponent(jTextField51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(192, 192, 192)
                .addComponent(jTextField52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82)
                .addComponent(btnNewCrew4, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAddcrew4, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEditcrew4, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnDeletecrew4, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnRefeshCrew4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Kho", jPanel8);

        javax.swing.GroupLayout pnlStorageCardLayout = new javax.swing.GroupLayout(pnlStorageCard);
        pnlStorageCard.setLayout(pnlStorageCardLayout);
        pnlStorageCardLayout.setHorizontalGroup(
            pnlStorageCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1048, Short.MAX_VALUE)
            .addGroup(pnlStorageCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlStorageCardLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        pnlStorageCardLayout.setVerticalGroup(
            pnlStorageCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 786, Short.MAX_VALUE)
            .addGroup(pnlStorageCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlStorageCardLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pnlCards.add(pnlStorageCard, "pnlCard4");

        pnlSupplierCard.setBackground(new java.awt.Color(51, 204, 255));

        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 204), 3));

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable6.setRowHeight(30);
        jScrollPane7.setViewportView(jTable6);

        jTextField4.setText("jTextField4");

        jTextField32.setText("jTextField32");

        jTextField33.setText("jTextField33");

        jTextField34.setText("jTextField34");

        btnAddcrew6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnAddcrew6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/addbutton.png"))); // NOI18N
        btnAddcrew6.setText("THÊM");
        btnAddcrew6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAddcrew6.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/addbuttonpressed.png"))); // NOI18N
        btnAddcrew6.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/addbuttonhover.png"))); // NOI18N
        btnAddcrew6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddcrew6ActionPerformed(evt);
            }
        });

        btnEditcrew6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnEditcrew6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/editbutton.png"))); // NOI18N
        btnEditcrew6.setText("SỬA");
        btnEditcrew6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEditcrew6.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/editbuttonpressed.png"))); // NOI18N
        btnEditcrew6.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/editbuttonhover.png"))); // NOI18N
        btnEditcrew6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditcrew6ActionPerformed(evt);
            }
        });

        btnDeletecrew6.setBackground(new java.awt.Color(255, 0, 0));
        btnDeletecrew6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnDeletecrew6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/deletebutton.png"))); // NOI18N
        btnDeletecrew6.setText("XÓA");
        btnDeletecrew6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDeletecrew6.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/deletebuttonpressed.png"))); // NOI18N
        btnDeletecrew6.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/deletebuttonhover.png"))); // NOI18N
        btnDeletecrew6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletecrew6ActionPerformed(evt);
            }
        });

        btnRefeshCrew6.setBackground(new java.awt.Color(255, 51, 255));
        btnRefeshCrew6.setText("KHÔI PHỤC");
        btnRefeshCrew6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefeshCrew6ActionPerformed(evt);
            }
        });

        btnNewCrew6.setText("LÀM MỚI");
        btnNewCrew6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewCrew6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSupplierCardLayout = new javax.swing.GroupLayout(pnlSupplierCard);
        pnlSupplierCard.setLayout(pnlSupplierCardLayout);
        pnlSupplierCardLayout.setHorizontalGroup(
            pnlSupplierCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane7)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSupplierCardLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSupplierCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSupplierCardLayout.createSequentialGroup()
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(191, 191, 191)
                        .addComponent(jTextField33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(192, 192, 192)
                        .addComponent(jTextField34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(198, 198, 198))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSupplierCardLayout.createSequentialGroup()
                        .addGap(0, 172, Short.MAX_VALUE)
                        .addComponent(btnRefeshCrew6, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDeletecrew6, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEditcrew6, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAddcrew6, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(59, 59, 59))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSupplierCardLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnNewCrew6, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlSupplierCardLayout.setVerticalGroup(
            pnlSupplierCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSupplierCardLayout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btnNewCrew6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addGroup(pnlSupplierCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlSupplierCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSupplierCardLayout.createSequentialGroup()
                        .addGroup(pnlSupplierCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAddcrew6, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEditcrew6, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDeletecrew6, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSupplierCardLayout.createSequentialGroup()
                        .addComponent(btnRefeshCrew6, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36))))
        );

        pnlCards.add(pnlSupplierCard, "pnlCard5");

        pnlSaleCard.setBackground(new java.awt.Color(51, 204, 255));

        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255), 3));

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 203, Short.MAX_VALUE)
        );

        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable7.setRowHeight(30);
        jScrollPane8.setViewportView(jTable7);

        jTextField35.setText("jTextField35");

        jTextField36.setText("jTextField36");

        jTextField37.setText("jTextField37");

        jTextField38.setText("jTextField38");

        btnAddcrew7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnAddcrew7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/addbutton.png"))); // NOI18N
        btnAddcrew7.setText("THÊM");
        btnAddcrew7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAddcrew7.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/addbuttonpressed.png"))); // NOI18N
        btnAddcrew7.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/addbuttonhover.png"))); // NOI18N
        btnAddcrew7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddcrew7ActionPerformed(evt);
            }
        });

        btnEditcrew7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnEditcrew7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/editbutton.png"))); // NOI18N
        btnEditcrew7.setText("SỬA");
        btnEditcrew7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEditcrew7.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/editbuttonpressed.png"))); // NOI18N
        btnEditcrew7.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/editbuttonhover.png"))); // NOI18N
        btnEditcrew7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditcrew7ActionPerformed(evt);
            }
        });

        btnDeletecrew7.setBackground(new java.awt.Color(255, 0, 0));
        btnDeletecrew7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnDeletecrew7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/deletebutton.png"))); // NOI18N
        btnDeletecrew7.setText("XÓA");
        btnDeletecrew7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDeletecrew7.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/deletebuttonpressed.png"))); // NOI18N
        btnDeletecrew7.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/deletebuttonhover.png"))); // NOI18N
        btnDeletecrew7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletecrew7ActionPerformed(evt);
            }
        });

        btnRefeshCrew7.setBackground(new java.awt.Color(255, 51, 255));
        btnRefeshCrew7.setText("KHÔI PHỤC");
        btnRefeshCrew7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefeshCrew7ActionPerformed(evt);
            }
        });

        btnNewCrew7.setText("LÀM MỚI");
        btnNewCrew7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewCrew7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlSaleCardLayout = new javax.swing.GroupLayout(pnlSaleCard);
        pnlSaleCard.setLayout(pnlSaleCardLayout);
        pnlSaleCardLayout.setHorizontalGroup(
            pnlSaleCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane8)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSaleCardLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnNewCrew7, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSaleCardLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSaleCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSaleCardLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnRefeshCrew7, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeletecrew7, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditcrew7, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAddcrew7, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSaleCardLayout.createSequentialGroup()
                        .addComponent(jTextField38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 172, Short.MAX_VALUE)
                        .addComponent(jTextField37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(193, 193, 193)
                        .addComponent(jTextField36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(189, 189, 189)
                        .addComponent(jTextField35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(192, 192, 192))))
        );
        pnlSaleCardLayout.setVerticalGroup(
            pnlSaleCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSaleCardLayout.createSequentialGroup()
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnNewCrew7, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(pnlSaleCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSaleCardLayout.createSequentialGroup()
                        .addGroup(pnlSaleCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlSaleCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlSaleCardLayout.createSequentialGroup()
                                .addGroup(pnlSaleCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnAddcrew7, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnEditcrew7, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnDeletecrew7, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(8, 8, 8))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlSaleCardLayout.createSequentialGroup()
                                .addComponent(btnRefeshCrew7, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(pnlSaleCardLayout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jTextField36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        pnlCards.add(pnlSaleCard, "pnlCard6");

        pnlMemberCard.setBackground(new java.awt.Color(51, 204, 255));

        jPanel17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 204), 3));

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 195, Short.MAX_VALUE)
        );

        jTable10.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane11.setViewportView(jTable10);

        jTextField39.setText("jTextField39");

        jTextField40.setText("jTextField40");

        jTextField41.setText("jTextField41");

        jTextField42.setText("jTextField42");

        jButton24.setText("jButton24");

        jButton29.setText("jButton29");

        jButton30.setText("jButton30");

        jButton31.setText("jButton31");

        javax.swing.GroupLayout pnlMemberCardLayout = new javax.swing.GroupLayout(pnlMemberCard);
        pnlMemberCard.setLayout(pnlMemberCardLayout);
        pnlMemberCardLayout.setHorizontalGroup(
            pnlMemberCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane11, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlMemberCardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextField39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(174, 174, 174)
                .addComponent(jTextField40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(193, 193, 193)
                .addComponent(jTextField41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 196, Short.MAX_VALUE)
                .addComponent(jTextField42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(183, 183, 183))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMemberCardLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton31)
                .addGap(101, 101, 101)
                .addComponent(jButton30)
                .addGap(132, 132, 132)
                .addComponent(jButton29)
                .addGap(146, 146, 146)
                .addComponent(jButton24)
                .addGap(71, 71, 71))
        );
        pnlMemberCardLayout.setVerticalGroup(
            pnlMemberCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMemberCardLayout.createSequentialGroup()
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlMemberCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMemberCardLayout.createSequentialGroup()
                        .addGroup(pnlMemberCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMemberCardLayout.createSequentialGroup()
                        .addGroup(pnlMemberCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(pnlMemberCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMemberCardLayout.createSequentialGroup()
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(pnlMemberCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton24)
                            .addComponent(jButton30)
                            .addComponent(jButton31))
                        .addGap(60, 60, 60))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMemberCardLayout.createSequentialGroup()
                        .addComponent(jButton29)
                        .addGap(39, 39, 39))))
        );

        pnlCards.add(pnlMemberCard, "pnlCard7");

        jSplitPane2.setRightComponent(pnlCards);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1230, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCrewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrewActionPerformed
       CardLayout.show(pnlCards, "pnlCard1");
    }//GEN-LAST:event_btnCrewActionPerformed

    private void btnAddcrewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddcrewActionPerformed
        ADDcrew ad = new ADDcrew();
        ad.setVisible(true);
        ad.pack();
        ad.setLocationRelativeTo(null);
        ad.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        try {
            sl();
        } catch (SQLException ex) {
            Logger.getLogger(AdminView.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }//GEN-LAST:event_btnAddcrewActionPerformed

    private void btnStatisticActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatisticActionPerformed
          CardLayout.show(pnlCards, "pnlCard2");
    }//GEN-LAST:event_btnStatisticActionPerformed

    private void btnFoodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFoodActionPerformed
        // TODO add your handling code here:
        CardLayout.show(pnlCards, "pnlCard3");
    }//GEN-LAST:event_btnFoodActionPerformed

    private void btnWarehouseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnWarehouseActionPerformed
        // TODO add your handling code here:
        CardLayout.show(pnlCards, "pnlCard4");
    }//GEN-LAST:event_btnWarehouseActionPerformed

    private void tblCrewMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblCrewMouseClicked
        // TODO add your handling code here:
        
        model = (DefaultTableModel) tblCrew.getModel();
        int Row = tblCrew.getSelectedRow();
        if(Row == -1)
        {
           
        }
        else
        {
            
            txtId_crew.setText(tblCrew.getModel().getValueAt(Row, 0).toString());
            txtName_crew.setText(tblCrew.getModel().getValueAt(Row, 1).toString());
            cmSex.setSelectedItem(tblCrew.getModel().getValueAt(Row, 2).toString());
            cmPosition.setSelectedItem(tblCrew.getModel().getValueAt(Row, 3).toString());
            txtPhone.setText(tblCrew.getModel().getValueAt(Row, 4).toString());
            cmShift.setSelectedItem(tblCrew.getModel().getValueAt(Row, 5).toString());
            txtSalary.setText(tblCrew.getModel().getValueAt(Row, 6).toString());
            jLabel9.setText(tblCrew.getModel().getValueAt(Row, 7).toString());
            
            
           
        }
    }//GEN-LAST:event_tblCrewMouseClicked

    private void txtId_crewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtId_crewActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtId_crewActionPerformed

    private void btnSaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaleActionPerformed
       CardLayout.show(pnlCards, "pnlCard6");
    }//GEN-LAST:event_btnSaleActionPerformed

    private void btnCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCustomerActionPerformed
        CardLayout.show(pnlCards, "pnlCard7");
    }//GEN-LAST:event_btnCustomerActionPerformed

    private void txtSalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSalaryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSalaryActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSupplierActionPerformed
                CardLayout.show(pnlCards, "pnlCard5");
    }//GEN-LAST:event_btnSupplierActionPerformed

    private void btnDeletecrewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletecrewActionPerformed
        model = (DefaultTableModel) tblCrew.getModel();
        int Row = tblCrew.getSelectedRow();
        if(Row == -1)
        {
           JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng để xóa");
           return;
        }
        else
        {
            int reply = JOptionPane.showConfirmDialog(rootPane, "bạn muốn xóa không");
            if (reply == JOptionPane.YES_OPTION)
            {
           String id = tblCrew.getModel().getValueAt(Row, 0).toString();
           nhanvienBUS.deleteNhanvien(id);
           
                outModel(model,nhanvienBUS.getList(),1);
            }
            
        }        
    }//GEN-LAST:event_btnDeletecrewActionPerformed

    private void btnRefeshCrewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefeshCrewActionPerformed
        // TODO add your handling code here:
        CrewRefeshGUI re = new CrewRefeshGUI();
        re.setVisible(true);
        re.pack();
        re.setLocationRelativeTo(null);
        re.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
    }//GEN-LAST:event_btnRefeshCrewActionPerformed

    private void btnNewCrewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewCrewActionPerformed
        // TODO add your handling code here:
       
        
        Chepdulieu();
        
        
        
            
    }//GEN-LAST:event_btnNewCrewActionPerformed

    private void btnEditcrewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditcrewActionPerformed
        model = (DefaultTableModel) tblCrew.getModel();
        int Row = tblCrew.getSelectedRow();
        if(Row == -1)
        {
           JOptionPane.showMessageDialog(null, "Bạn chưa chọn dòng để sửa");
           return;
        }
        else
        {
            String id_nhanvien = txtId_crew.getText();
            String hoNV = txtName_crew.getText();
            String tenNV = cmSex.getSelectedItem().toString();
            String phoneNV = cmPosition.getSelectedItem().toString();
            int luong = Integer.parseInt(txtPhone.getText());
            Nhanvien nv = new Nhanvien(id_nhanvien, hoNV, tenNV, phoneNV, luong, 1);
            int reply = JOptionPane.showConfirmDialog(rootPane, "bạn muốn sửa không");
            if (reply == JOptionPane.YES_OPTION)
            {
                nhanvienBUS.setCREW(nv);
                outModel(model,nhanvienBUS.getList(),1);
            }
            
        }            
            
           
            
    }//GEN-LAST:event_btnEditcrewActionPerformed

    private void cmShiftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmShiftActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmShiftActionPerformed

    private void btnMiniaddcrewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMiniaddcrewActionPerformed
        // TODO add your handling code here:
        ADDcrew ad = new ADDcrew();
        ad.setVisible(true);
        ad.pack();
        ad.setLocationRelativeTo(null);
        ad.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_btnMiniaddcrewActionPerformed

    private void btnAddcrew1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddcrew1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddcrew1ActionPerformed

    private void btnEditcrew1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditcrew1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditcrew1ActionPerformed

    private void btnDeletecrew1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletecrew1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeletecrew1ActionPerformed

    private void btnRefeshCrew1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefeshCrew1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRefeshCrew1ActionPerformed

    private void jTextField13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField13ActionPerformed

    private void btnAddcrew2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddcrew2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddcrew2ActionPerformed

    private void btnEditcrew2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditcrew2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditcrew2ActionPerformed

    private void btnDeletecrew2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletecrew2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeletecrew2ActionPerformed

    private void btnRefeshCrew2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefeshCrew2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRefeshCrew2ActionPerformed

    private void btnNewCrew1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewCrew1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNewCrew1ActionPerformed

    private void btnNewCrew2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewCrew2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNewCrew2ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void btnAddcrew3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddcrew3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddcrew3ActionPerformed

    private void btnEditcrew3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditcrew3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditcrew3ActionPerformed

    private void btnDeletecrew3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletecrew3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeletecrew3ActionPerformed

    private void btnRefeshCrew3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefeshCrew3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRefeshCrew3ActionPerformed

    private void btnNewCrew3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewCrew3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNewCrew3ActionPerformed

    private void btnAddcrew4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddcrew4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddcrew4ActionPerformed

    private void btnEditcrew4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditcrew4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditcrew4ActionPerformed

    private void btnDeletecrew4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletecrew4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeletecrew4ActionPerformed

    private void btnRefeshCrew4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefeshCrew4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRefeshCrew4ActionPerformed

    private void btnNewCrew4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewCrew4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNewCrew4ActionPerformed

    private void btnAddcrew5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddcrew5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddcrew5ActionPerformed

    private void btnEditcrew5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditcrew5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditcrew5ActionPerformed

    private void btnDeletecrew5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletecrew5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeletecrew5ActionPerformed

    private void btnRefeshCrew5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefeshCrew5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRefeshCrew5ActionPerformed

    private void btnNewCrew5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewCrew5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNewCrew5ActionPerformed

    private void btnAddcrew6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddcrew6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddcrew6ActionPerformed

    private void btnEditcrew6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditcrew6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditcrew6ActionPerformed

    private void btnDeletecrew6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletecrew6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeletecrew6ActionPerformed

    private void btnRefeshCrew6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefeshCrew6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRefeshCrew6ActionPerformed

    private void btnNewCrew6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewCrew6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNewCrew6ActionPerformed

    private void btnAddcrew7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddcrew7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddcrew7ActionPerformed

    private void btnEditcrew7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditcrew7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditcrew7ActionPerformed

    private void btnDeletecrew7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletecrew7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeletecrew7ActionPerformed

    private void btnRefeshCrew7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefeshCrew7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRefeshCrew7ActionPerformed

    private void btnNewCrew7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewCrew7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNewCrew7ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton15ActionPerformed

    private void txtSearchId_crewKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchId_crewKeyReleased
        
        outModel(model,nhanvienBUS.search(txtSearchId_crew.getText()),1);
    }//GEN-LAST:event_txtSearchId_crewKeyReleased

    private void txtSearchId_crewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchId_crewActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchId_crewActionPerformed

    private void txtName_crewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtName_crewActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtName_crewActionPerformed

    private void jcomboDateItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcomboDateItemStateChanged
        // TODO add your handling code here:
        ChepdulieuSales();
    }//GEN-LAST:event_jcomboDateItemStateChanged

    private void jcomboDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcomboDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcomboDateActionPerformed

    private void jComboQuiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboQuiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboQuiActionPerformed

    private void jComboYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboYearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboYearActionPerformed

    private void jComboMounthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboMounthActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboMounthActionPerformed

    private void jComboMounthItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboMounthItemStateChanged
        // TODO add your handling code here:
        ChepdulieuSales();
    }//GEN-LAST:event_jComboMounthItemStateChanged

    private void jComboDate2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboDate2ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_jComboDate2ActionPerformed

    private void jComboDate1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboDate1ItemStateChanged
        // TODO add your handling code here:
        chepdulieudate2();
        ChepdulieuSales();
    }//GEN-LAST:event_jComboDate1ItemStateChanged

    private void jComboDate2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboDate2ItemStateChanged
        // TODO add your handling code here:
        ChepdulieuSales();
    }//GEN-LAST:event_jComboDate2ItemStateChanged

    private void jComboQuiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboQuiItemStateChanged
        // TODO add your handling code here:
        ChepdulieuSales();
    }//GEN-LAST:event_jComboQuiItemStateChanged

    private void JComboYearQuiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JComboYearQuiItemStateChanged
        // TODO add your handling code here:
        ChepdulieuSales();
    }//GEN-LAST:event_JComboYearQuiItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminView().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> JComboYearQui;
    private javax.swing.JLabel JTongMonNgay;
    private javax.swing.JLabel JTongTienKhoangNgay;
    private javax.swing.JLabel JTongTienNam;
    private javax.swing.JLabel JTongTienNgay;
    private javax.swing.JLabel JTongTienNhanVien;
    private javax.swing.JLabel JTongTienQui;
    private javax.swing.JLabel JTongTienThang;
    private javax.swing.JPanel MenuAdminView;
    private javax.swing.JButton btnAddcrew;
    private javax.swing.JButton btnAddcrew1;
    private javax.swing.JButton btnAddcrew2;
    private javax.swing.JButton btnAddcrew3;
    private javax.swing.JButton btnAddcrew4;
    private javax.swing.JButton btnAddcrew5;
    private javax.swing.JButton btnAddcrew6;
    private javax.swing.JButton btnAddcrew7;
    private javax.swing.JButton btnCrew;
    private javax.swing.JButton btnCustomer;
    private javax.swing.JButton btnDeletecrew;
    private javax.swing.JButton btnDeletecrew1;
    private javax.swing.JButton btnDeletecrew2;
    private javax.swing.JButton btnDeletecrew3;
    private javax.swing.JButton btnDeletecrew4;
    private javax.swing.JButton btnDeletecrew5;
    private javax.swing.JButton btnDeletecrew6;
    private javax.swing.JButton btnDeletecrew7;
    private javax.swing.JButton btnEditcrew;
    private javax.swing.JButton btnEditcrew1;
    private javax.swing.JButton btnEditcrew2;
    private javax.swing.JButton btnEditcrew3;
    private javax.swing.JButton btnEditcrew4;
    private javax.swing.JButton btnEditcrew5;
    private javax.swing.JButton btnEditcrew6;
    private javax.swing.JButton btnEditcrew7;
    private javax.swing.JButton btnFood;
    private javax.swing.JButton btnMiniaddcrew;
    private javax.swing.JButton btnNewCrew;
    private javax.swing.JButton btnNewCrew1;
    private javax.swing.JButton btnNewCrew2;
    private javax.swing.JButton btnNewCrew3;
    private javax.swing.JButton btnNewCrew4;
    private javax.swing.JButton btnNewCrew5;
    private javax.swing.JButton btnNewCrew6;
    private javax.swing.JButton btnNewCrew7;
    private javax.swing.JButton btnRefeshCrew;
    private javax.swing.JButton btnRefeshCrew1;
    private javax.swing.JButton btnRefeshCrew2;
    private javax.swing.JButton btnRefeshCrew3;
    private javax.swing.JButton btnRefeshCrew4;
    private javax.swing.JButton btnRefeshCrew5;
    private javax.swing.JButton btnRefeshCrew6;
    private javax.swing.JButton btnRefeshCrew7;
    private javax.swing.JButton btnSale;
    private javax.swing.JButton btnStatistic;
    private javax.swing.JButton btnSupplier;
    private javax.swing.JButton btnWarehouse;
    private javax.swing.JComboBox<String> cmPosition;
    private javax.swing.JComboBox<String> cmSex;
    private javax.swing.JComboBox<String> cmShift;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JComboBox<String> jComboCrew;
    private javax.swing.JComboBox<String> jComboDate1;
    private javax.swing.JComboBox<String> jComboDate2;
    private javax.swing.JComboBox<String> jComboMounth;
    private javax.swing.JComboBox<String> jComboQui;
    private javax.swing.JComboBox<String> jComboYear;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable10;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTable jTable7;
    private javax.swing.JTable jTable8;
    private javax.swing.JTable jTable9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JTextField jTextField33;
    private javax.swing.JTextField jTextField34;
    private javax.swing.JTextField jTextField35;
    private javax.swing.JTextField jTextField36;
    private javax.swing.JTextField jTextField37;
    private javax.swing.JTextField jTextField38;
    private javax.swing.JTextField jTextField39;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField40;
    private javax.swing.JTextField jTextField41;
    private javax.swing.JTextField jTextField42;
    private javax.swing.JTextField jTextField47;
    private javax.swing.JTextField jTextField48;
    private javax.swing.JTextField jTextField49;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField50;
    private javax.swing.JTextField jTextField51;
    private javax.swing.JTextField jTextField52;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel jTongMonNam;
    private javax.swing.JLabel jTongMonThang;
    private javax.swing.JComboBox<String> jcomboDate;
    private javax.swing.JLabel lbShift;
    private javax.swing.JPanel pnlCards;
    private javax.swing.JPanel pnlCrewCard;
    private javax.swing.JPanel pnlDishCard;
    private javax.swing.JPanel pnlMemberCard;
    private javax.swing.JPanel pnlSaleCard;
    private javax.swing.JPanel pnlStatCard;
    private javax.swing.JPanel pnlStorageCard;
    private javax.swing.JPanel pnlSupplierCard;
    private javax.swing.JTable tblCrew;
    private javax.swing.JTextField txtId_crew;
    private javax.swing.JTextField txtName_crew;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtSalary;
    private javax.swing.JTextField txtSearchId_crew;
    private javax.swing.JTextField txtSearchName_crew;
    private javax.swing.JTextField txtSearchPhone;
    private javax.swing.JTextField txtSearchPosition;
    private javax.swing.JTextField txtSearchSalary;
    private javax.swing.JTextField txtSearchSex;
    private javax.swing.JTextField txtSearchShift;
    private javax.swing.JTextField txtSearchStatus_crew;
    // End of variables declaration//GEN-END:variables
}
