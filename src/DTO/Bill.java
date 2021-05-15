
package DTO;


/**
 *
 * @author Admin
 */
public class Bill{
    int tongtien;
    String ngaylap, id_hoadon,id_khach,id_nhanvien,id_khuyenmai;

    public Bill(String id_hoadon, String id_khach, String id_nhanvien, String id_khuyenmai, String ngaylap ,int tongtien) {
        this.id_hoadon = id_hoadon;
        this.id_khach = id_khach;
        this.id_khuyenmai = id_khuyenmai;
        this.id_nhanvien = id_nhanvien;
        this.tongtien = tongtien;
        this.ngaylap = ngaylap;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public void setNgaylap(String ngaylap) {
        this.ngaylap = ngaylap;
    }

    public void setId_hoadon(String id_hoadon) {
        this.id_hoadon = id_hoadon;
    }

    public void setId_khach(String id_khach) {
        this.id_khach = id_khach;
    }

    public void setId_nhanvien(String id_nhanvien) {
        this.id_nhanvien = id_nhanvien;
    }

    public void setId_khuyenmai(String id_khuyenmai) {
        this.id_khuyenmai = id_khuyenmai;
    }

    public int getTongtien() {
        return tongtien;
    }

    public String getNgaylap() {
        return ngaylap;
    }

    public String getId_hoadon() {
        return id_hoadon;
    }

    public String getId_khach() {
        return id_khach;
    }

    public String getId_nhanvien() {
        return id_nhanvien;
    }

    public String getId_khuyenmai() {
        return id_khuyenmai;
    }

}