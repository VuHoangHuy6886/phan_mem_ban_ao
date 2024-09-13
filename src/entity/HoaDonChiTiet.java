package entity;

public class HoaDonChiTiet {

    private Long id;
    private Long idHoaDon;
    private Long idSanPhamChiTiet;
    private Integer soLuong;
    private Double donGia;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(Long id, Long idHoaDon, Long idSanPhamChiTiet, Integer soLuong, Double donGia) {
        this.id = id;
        this.idHoaDon = idHoaDon;
        this.idSanPhamChiTiet = idSanPhamChiTiet;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(Long idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public Long getIdSanPhamChiTiet() {
        return idSanPhamChiTiet;
    }

    public void setIdSanPhamChiTiet(Long idSanPhamChiTiet) {
        this.idSanPhamChiTiet = idSanPhamChiTiet;
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

}
