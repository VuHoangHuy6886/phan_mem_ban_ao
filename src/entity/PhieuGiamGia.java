package entity;

import java.time.LocalDateTime;

public class PhieuGiamGia {

    private Long id;
    private String ma;
    private String ten;
    private String moTa;
    private Double phanTramGiam;
    private Double giamToiDa;
    private LocalDateTime ngayBatDau;
    private LocalDateTime ngayKetThuc;
    private String trangThai;

    public PhieuGiamGia() {
    }

    public PhieuGiamGia(Long id, String ma, String ten, String moTa, Double phanTramGiam, Double giamToiDa, LocalDateTime ngayBatDau, LocalDateTime ngayKetThuc, String trangThai) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.moTa = moTa;
        this.phanTramGiam = phanTramGiam;
        this.giamToiDa = giamToiDa;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.trangThai = trangThai;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Double getPhanTramGiam() {
        return phanTramGiam;
    }

    public void setPhanTramGiam(Double phanTramGiam) {
        this.phanTramGiam = phanTramGiam;
    }

    public Double getGiamToiDa() {
        return giamToiDa;
    }

    public void setGiamToiDa(Double giamToiDa) {
        this.giamToiDa = giamToiDa;
    }

    public LocalDateTime getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(LocalDateTime ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public LocalDateTime getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(LocalDateTime ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

}
