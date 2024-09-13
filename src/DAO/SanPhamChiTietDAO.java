package DAO;

import entity.SanPhamChiTiet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author huyvh
 */
public class SanPhamChiTietDAO extends GennericDAO<SanPhamChiTiet, Long> {

    @Override
    protected String getTableName() {
        return "san_pham_chi_tiet";
    }

    @Override
    protected SanPhamChiTiet mapRow(ResultSet rs) throws SQLException {
        SanPhamChiTiet sanPham = new SanPhamChiTiet();
        sanPham.setId(rs.getLong("id"));
        sanPham.setMa(rs.getString("ma"));
        sanPham.setSoLuong(rs.getInt("so_luong"));
        sanPham.setDonGia(rs.getDouble("don_gia"));
        sanPham.setIdMauSac(rs.getLong("id_mau_sac"));
        sanPham.setIdKichThuoc(rs.getLong("id_kich_thuoc"));
        sanPham.setIdChatLieu(rs.getLong("id_chat_lieu"));
        sanPham.setIdKieuDang(rs.getLong("id_kieu_dang"));
        sanPham.setIdSanPham(rs.getLong("id_san_pham"));
        sanPham.setTrangThai(rs.getString("trang_thai"));
        return sanPham;
    }

    // Thêm sản phẩm chi tiết
    @Override
    protected String getInsertSql() {
        return "INSERT INTO san_pham_chi_tiet (ma, so_luong, don_gia, id_mau_sac, id_kich_thuoc, id_chat_lieu, id_kieu_dang, trang_thai, id_san_pham) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, SanPhamChiTiet sanPham) throws SQLException {
        ps.setString(1, sanPham.getMa());
        ps.setInt(2, sanPham.getSoLuong());
        ps.setDouble(3, sanPham.getDonGia());
        ps.setLong(4, sanPham.getIdMauSac());
        ps.setLong(5, sanPham.getIdKichThuoc());
        ps.setLong(6, sanPham.getIdChatLieu());
        ps.setLong(7, sanPham.getIdKieuDang());
        ps.setString(8, sanPham.getTrangThai());
        ps.setLong(9, sanPham.getIdSanPham());
    }

    // Sửa sản phẩm chi tiết
    @Override
    protected String getUpdateSql() {
        return "UPDATE san_pham_chi_tiet SET ma = ?, so_luong = ?, don_gia = ?, id_mau_sac = ?, id_kich_thuoc = ?, id_chat_lieu = ?, id_kieu_dang = ?, trang_thai = ?, id_san_pham = ? WHERE id = ?";
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, SanPhamChiTiet sanPham) throws SQLException {
        ps.setString(1, sanPham.getMa());
        ps.setInt(2, sanPham.getSoLuong());
        ps.setDouble(3, sanPham.getDonGia());
        ps.setLong(4, sanPham.getIdMauSac());
        ps.setLong(5, sanPham.getIdKichThuoc());
        ps.setLong(6, sanPham.getIdChatLieu());
        ps.setLong(7, sanPham.getIdKieuDang());
        ps.setString(8, sanPham.getTrangThai());
        ps.setLong(9, sanPham.getIdSanPham());
        ps.setLong(10, sanPham.getId());
    }

    // Tìm tất cả sản phẩm chi tiết theo id sản phẩm
    public List<SanPhamChiTiet> findAllByIdSanPham(Long idSanPham) {
        List<SanPhamChiTiet> list = new ArrayList<>();
        String sql = "SELECT * FROM san_pham_chi_tiet WHERE id_san_pham = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, idSanPham);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Tìm sản phẩm chi tiết theo id
    public SanPhamChiTiet findByMa(String ma) {
        SanPhamChiTiet sanPham = null;
        String sql = "SELECT * FROM san_pham_chi_tiet WHERE ma = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ma);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                sanPham = mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sanPham;
    }
    // check mã tồn tại chưa
    public boolean checkMaTonTai(String ma) {
        String sql = "SELECT COUNT(*) FROM san_pham_chi_tiet WHERE ma = ?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ma);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1); // Lấy số lượng kết quả trả về
                return count > 0; // Nếu count > 0, nghĩa là mã đã tồn tại
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Mặc định trả về false nếu có lỗi hoặc không tìm thấy
    }

}
