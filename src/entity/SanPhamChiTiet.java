/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author huyvh
 */
public class SanPhamChiTiet {

    private Long id;
    private String ma;
    private Integer soLuong;
    private Double donGia;
    private Long idSanPham;
    private Long idMauSac;
    private Long idKichThuoc;
    private Long idChatLieu;
    private Long idKieuDang;
    private String trangThai;

    public SanPhamChiTiet() {
    }

    public SanPhamChiTiet(Long id, String ma, Integer soLuong, Double donGia, Long idSanPham, Long idMauSac, Long idKichThuoc, Long idChatLieu, Long idKieuDang, String trangThai) {
        this.id = id;
        this.ma = ma;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.idSanPham = idSanPham;
        this.idMauSac = idMauSac;
        this.idKichThuoc = idKichThuoc;
        this.idChatLieu = idChatLieu;
        this.idKieuDang = idKieuDang;
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

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Double getDonGia() {
        return donGia;
    }

    public void setDonGia(Double donGia) {
        this.donGia = donGia;
    }

    public Long getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(Long idSanPham) {
        this.idSanPham = idSanPham;
    }

    public Long getIdMauSac() {
        return idMauSac;
    }

    public void setIdMauSac(Long idMauSac) {
        this.idMauSac = idMauSac;
    }

    public Long getIdKichThuoc() {
        return idKichThuoc;
    }

    public void setIdKichThuoc(Long idKichThuoc) {
        this.idKichThuoc = idKichThuoc;
    }

    public Long getIdChatLieu() {
        return idChatLieu;
    }

    public void setIdChatLieu(Long idChatLieu) {
        this.idChatLieu = idChatLieu;
    }

    public Long getIdKieuDang() {
        return idKieuDang;
    }

    public void setIdKieuDang(Long idKieuDang) {
        this.idKieuDang = idKieuDang;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "SanPhamChiTiet{" + "id=" + id + ", ma=" + ma + ", soLuong=" + soLuong + ", donGia=" + donGia + ", idSanPham=" + idSanPham + ", idMauSac=" + idMauSac + ", idKichThuoc=" + idKichThuoc + ", idChatLieu=" + idChatLieu + ", idKieuDang=" + idKieuDang + ", trangThai=" + trangThai + '}';
    }

}
