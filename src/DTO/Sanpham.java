
package DTO;


/**
 *
 * @author Admin
 */
public class Sanpham {
    String tensp,id_sp;
    int status_sp,soluong,dongia,id_loaisp;

    public Sanpham(String id_sp, String tensp, int soluong, int dongia,int id_loaisp, int status_sp) {
        this.id_sp = id_sp;
        this.tensp = tensp;
        this.soluong = soluong;
        this.dongia = dongia;
        this.id_loaisp = id_loaisp;
        this.status_sp = status_sp;
        
    }

    public String getTensp() {
        return tensp;
    }

    public String getId_sp() {
        return id_sp;
    }

    public int getStatus_sp() {
        return status_sp;
    }

    public int getSoluong() {
        return soluong;
    }

    public int getDongia() {
        return dongia;
    }

    public int getId_loaisp() {
        return id_loaisp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public void setId_sp(String id_sp) {
        this.id_sp = id_sp;
    }

    public void setStatus_sp(int status_sp) {
        this.status_sp = status_sp;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }

    public void setId_loaisp(int id_loaisp) {
        this.id_loaisp = id_loaisp;
    }

    
    
    


}
