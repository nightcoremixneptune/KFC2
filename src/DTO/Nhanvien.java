
package DTO;


/**
 *
 * @author Admin
 */
public class Nhanvien {
    String  id_nhanvien,hoNV,tenNV,phoneNV;
    int luong,status;
    
    public Nhanvien(String id_nhanvien, String hoNV, String tenNV, String phoneNV, int luong,int status) {
        this.id_nhanvien = id_nhanvien;
        this.hoNV = hoNV;
        this.tenNV = tenNV;
        this.phoneNV = phoneNV;
        this.luong = luong;
        this.status = status;
       

    }

    public String getId_nhanvien() {
        return id_nhanvien;
    }

    public String getHoNV() {
        return hoNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public String getPhoneNV() {
        return phoneNV;
    }

    public int getLuong() {
        return luong;
    }

    public int getStatus() {
        return status;
    }

    public void setId_nhanvien(String id_nhanvien) {
        this.id_nhanvien = id_nhanvien;
    }

    public void setHoNV(String hoNV) {
        this.hoNV = hoNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public void setPhoneNV(String phoneNV) {
        this.phoneNV = phoneNV;
    }

    public void setLuong(int luong) {
        this.luong = luong;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
   

   
}
