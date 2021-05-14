/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author LENOVO
 */
public class Bill_Res {
    int id_hoadon,id_khach,id_nhanvien,id_khuyenmai,tongtien;
    String ngaylap;

    public Bill_Res(int id_hoadon, int id_khach, int id_nhanvien, int id_khuyenmai, String ngaylap ,int tongtien) {
        this.id_hoadon = id_hoadon;
        this.id_khach = id_khach;
        this.id_khuyenmai = id_khuyenmai;
        this.id_nhanvien = id_nhanvien;
        this.tongtien = tongtien;
        this.ngaylap = ngaylap;
    }

    public void setId_hoadon(int id_hoadon) {
        this.id_hoadon = id_hoadon;
    }

    public void setId_khach(int id_khach) {
        this.id_khach = id_khach;
    }

    public void setId_nhanvien(int id_nhanvien) {
        this.id_nhanvien = id_nhanvien;
    }

    public void setId_khuyenmai(int id_khuyenmai) {
        this.id_khuyenmai = id_khuyenmai;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public void setNgaylap(String ngaylap) {
        this.ngaylap = ngaylap;
    }

    public int getId_hoadon() {
        return id_hoadon;
    }

    public int getId_khach() {
        return id_khach;
    }

    public int getId_nhanvien() {
        return id_nhanvien;
    }

    public int getId_khuyenmai() {
        return id_khuyenmai;
    }

    public int getTongtien() {
        return tongtien;
    }

    public String getNgaylap() {
        return ngaylap;
    }
}
