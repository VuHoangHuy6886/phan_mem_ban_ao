
package entity;

public class NhanVien {

    private Long id;
    private String ma;
    private String ten;
    private String email;
    private String password;
    private String role;
    private Integer trangThai;

    public NhanVien() {
    }

    public NhanVien(Long id, String ma, String ten, String email, String password, String role, Integer trangThai) {
        this.id = id;
        this.ma = ma;
        this.ten = ten;
        this.email = email;
        this.password = password;
        this.role = role;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

}
