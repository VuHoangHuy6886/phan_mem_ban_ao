package entity;

public class SanPham {

    private Long id;
    private String ma;
    private String ten;
    private String moTa;
    private String trangThai;

    public SanPham() {
    }

    public SanPham(Long id, String ma, String ten, String moTa, String trangThai) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.moTa = moTa;
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

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "SanPham{" + "id=" + id + ", ma=" + ma + ", ten=" + ten + ", moTa=" + moTa + ", trangThai=" + trangThai + '}';
    }

}
