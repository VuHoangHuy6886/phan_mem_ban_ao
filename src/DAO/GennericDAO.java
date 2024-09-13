/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class GennericDAO<T, ID> implements GennericDAOInterface<T, ID> {

    // Phương thức để lấy kết nối đến cơ sở dữ liệu
    protected Connection getConnection() throws SQLException {
        return dbconnect.JdbcUtil.getConnection(); // Thay đổi tùy theo cấu hình kết nối của bạn
    }

    // Phương thức tìm tất cả các bản ghi
    public List<T> findAll() {
        List<T> list = new ArrayList<>();
        String sql = "SELECT * FROM " + getTableName(); // getTableName là phương thức trừu tượng để lấy tên bảng

        try (Connection con = getConnection(); Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(mapRow(rs)); // mapRow là phương thức trừu tượng để ánh xạ dữ liệu vào đối tượng
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Phương thức tìm một bản ghi theo ID
    public Optional<T> findById(ID id) {
        String sql = "SELECT * FROM " + getTableName() + " WHERE id = ?";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    // Phương thức thêm một bản ghi mới
    public String create(T entity) {
        String sql = getInsertSql(); // getInsertSql là phương thức trừu tượng để lấy câu lệnh SQL INSERT
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            setInsertParameters(ps, entity); // setInsertParameters là phương thức trừu tượng để thiết lập tham số
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Thêm Nhập Thành Công";
    }

    // Phương thức cập nhật một bản ghi
    public String update(T entity) {
        String sql = getUpdateSql(); // getUpdateSql là phương thức trừu tượng để lấy câu lệnh SQL UPDATE
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            setUpdateParameters(ps, entity); // setUpdateParameters là phương thức trừu tượng để thiết lập tham số
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Cập Nhập Thành Công";
    }

    public boolean existsByName(String name) {
        String sql = "SELECT COUNT(*) FROM " + getTableName() + " WHERE ten = ?  ";
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1); // Lấy giá trị đầu tiên là số lượng bản ghi thỏa mãn điều kiện
                    return count > 0; // Nếu số lượng > 0, thì tồn tại đối tượng có tên và mã này
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    // Các phương thức trừu tượng cần được triển khai trong các lớp con
    protected abstract String getTableName();

    protected abstract T mapRow(ResultSet rs) throws SQLException;

    protected abstract String getInsertSql();

    protected abstract void setInsertParameters(PreparedStatement ps, T entity) throws SQLException;

    protected abstract String getUpdateSql();

    protected abstract void setUpdateParameters(PreparedStatement ps, T entity) throws SQLException;
}
