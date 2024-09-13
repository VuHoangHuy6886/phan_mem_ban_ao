package entity;

public class KhachHang {

    private Long id;
    private String ma;
    private String ten;
    private String email;
    private String diaChi;
    private String soDienThoai;

    public KhachHang() {
    }

    public KhachHang(Long id, String ma, String ten, String email, String diaChi, String soDienThoai) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.email = email;
        this.diaChi = diaChi;
        this.soDienThoai = soDienThoai;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

}
