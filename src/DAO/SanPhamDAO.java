
package DAO;

import entity.SanPham;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SanPhamDAO extends GennericDAO<SanPham, Long> {

    // Phương thức để lấy kết nối đến cơ sở dữ liệu
    protected Connection getConnection() throws SQLException {
        return dbconnect.JdbcUtil.getConnection(); // Thay đổi tùy theo cấu hình kết nối của bạn
    }

    @Override
    protected String getTableName() {
        return "san_pham";
    }

    @Override
    protected SanPham mapRow(ResultSet rs) throws SQLException {
        SanPham sanPham = new SanPham();
        sanPham.setId(rs.getLong("id"));
        sanPham.setMa(rs.getString("ma"));
        sanPham.setTen(rs.getString("ten"));
        sanPham.setMoTa(rs.getString("mo_ta"));
        sanPham.setTrangThai(rs.getString("trang_thai"));
        return sanPham;
    }

    @Override
    protected String getInsertSql() {
        return "INSERT INTO san_pham (ma,ten,mo_ta, trang_thai) VALUES (?, ?, ?, ?)";
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, SanPham sanPham) throws SQLException {
        ps.setString(1, sanPham.getMa());
        ps.setString(2, sanPham.getTen());
        ps.setString(3, sanPham.getMoTa());
        ps.setString(4, sanPham.getTrangThai());
    }

    @Override
    protected String getUpdateSql() {
        return "UPDATE san_pham SET ma = ?, ten = ?,mo_ta = ?, trang_thai = ? WHERE id = ?";
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, SanPham sanPham) throws SQLException {
        ps.setString(1, sanPham.getMa());
        ps.setString(2, sanPham.getTen());
        ps.setString(3, sanPham.getMoTa());
        ps.setString(4, sanPham.getTrangThai());
        ps.setLong(5, sanPham.getId());
    }

    public List<SanPham> searchTenOrMaAndTrangThai(String tenHoacMa, String trangThai) {
        List<SanPham> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM san_pham WHERE 1=1");

        // Điều kiện tìm kiếm theo tên hoặc mã nếu không null hoặc rỗng
        if (tenHoacMa != null && !tenHoacMa.trim().isEmpty()) {
            sql.append(" AND (ten LIKE ? OR ma LIKE ?)");
        }

        // Điều kiện lọc theo trạng thái nếu không null
        if (trangThai != null && !trangThai.trim().isEmpty()) {
            sql.append(" AND trang_thai = ?");
        }

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql.toString())) {

            int parameterIndex = 1;

            // Gán giá trị cho tham số tìm kiếm tên hoặc mã
            if (tenHoacMa != null && !tenHoacMa.trim().isEmpty()) {
                ps.setString(parameterIndex++, "%" + tenHoacMa + "%");
                ps.setString(parameterIndex++, "%" + tenHoacMa + "%");
            }

            // Gán giá trị cho tham số trạng thái
            if (trangThai != null && !trangThai.trim().isEmpty()) {
                ps.setString(parameterIndex++, trangThai);
            }

            // Thực thi truy vấn
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // Map dữ liệu từ ResultSet vào đối tượng SanPham
                    SanPham sanPham = mapRow(rs);
                    list.add(sanPham);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public SanPham searchByName(String name) {
        String sql = "SELECT * FROM " + getTableName() + " WHERE ten = ? ";
        SanPham sanPham = new SanPham();
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    sanPham = mapRow(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sanPham;
    }

}
