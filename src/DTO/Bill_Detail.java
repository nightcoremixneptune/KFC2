
package DTO;


/**
 *
 * @author Admin
 */
public class Bill_Detail{
    String id_hoadon,id_sp;
    int soluongsp,thanhtien;

    public Bill_Detail(String id_hoadon, String id_sp, int soluongsp, int thanhtien) {
        this.id_hoadon = id_hoadon;
        this.id_sp = id_sp;
        this.soluongsp = soluongsp;
        this.thanhtien = thanhtien;
    }

    public void setId_hoadon(String id_hoadon) {
        this.id_hoadon = id_hoadon;
    }

    public void setId_sp(String id_sp) {
        this.id_sp = id_sp;
    }

    public void setSoluongsp(int soluongsp) {
        this.soluongsp = soluongsp;
    }

    public void setThanhtien(int thanhtien) {
        this.thanhtien = thanhtien;
    }
    
    public String getId_hoadon() {
        return id_hoadon;
    }

    public String getId_sp() {
        return id_sp;
    }

    public int getSoluongsp() {
        return soluongsp;
    }

    public int getThanhtien() {
        return thanhtien;
    }
        

    }

    
    
    

    


