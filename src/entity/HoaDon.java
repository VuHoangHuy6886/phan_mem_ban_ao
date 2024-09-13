package entity;

import java.time.LocalDateTime;

public class HoaDon {

    private Long id;
    private String ma;
    private Long idKhachHang;
    private Long idNhanVien;
    private LocalDateTime ngayBatDau;
    private LocalDateTime ngayKetThuc;
    private Double tongTien;
    private String phuongThucThanhToan;
    private Long idPhieuGiamGia;
    private Integer trangThai;

    public HoaDon() {
    }

    public HoaDon(Long id, String ma, Long idKhachHang, Long idNhanVien, LocalDateTime ngayBatDau, LocalDateTime ngayKetThuc, Double tongTien, String phuongThucThanhToan, Long idPhieuGiamGia, Integer trangThai) {
        this.id = id;
        this.ma = ma;
        this.idKhachHang = idKhachHang;
        this.idNhanVien = idNhanVien;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.tongTien = tongTien;
        this.phuongThucThanhToan = phuongThucThanhToan;
        this.idPhieuGiamGia = idPhieuGiamGia;
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

    public Long getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(Long idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public Long getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(Long idNhanVien) {
        this.idNhanVien = idNhanVien;
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

    public Double getTongTien() {
        return tongTien;
    }

    public void setTongTien(Double tongTien) {
        this.tongTien = tongTien;
    }

    public String getPhuongThucThanhToan() {
        return phuongThucThanhToan;
    }

    public void setPhuongThucThanhToan(String phuongThucThanhToan) {
        this.phuongThucThanhToan = phuongThucThanhToan;
    }

    public Long getIdPhieuGiamGia() {
        return idPhieuGiamGia;
    }

    public void setIdPhieuGiamGia(Long idPhieuGiamGia) {
        this.idPhieuGiamGia = idPhieuGiamGia;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

}
