
package entity;

public class MauSac {

    private Long id;
    private String ten;
    private String trangThai;

    public MauSac() {
    }

    public MauSac(Long id, String ten, String trangThai) {
        this.id = id;
        this.ten = ten;
        this.trangThai = trangThai;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "MauSac{" + "ten=" + ten + ", trangThai=" + trangThai + '}';
    }

}
