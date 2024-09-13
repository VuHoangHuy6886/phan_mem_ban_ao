/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import entity.KichThuoc;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author huyvh
 */
public class KichThuocDAO extends GennericDAO<KichThuoc, Long> {

    @Override
    protected String getTableName() {
        return "kich_thuoc";
    }

    @Override
    protected KichThuoc mapRow(ResultSet rs) throws SQLException {
        KichThuoc kichThuoc = new KichThuoc();
        kichThuoc.setId(rs.getLong("id"));
        kichThuoc.setTen(rs.getString("ten"));
        kichThuoc.setTrangThai(rs.getString("trang_thai"));
        return kichThuoc;
    }

    @Override
    protected String getInsertSql() {
        return "INSERT INTO kich_thuoc (ten, trang_thai) VALUES (?, ?)";
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, KichThuoc kichThuoc) throws SQLException {
        ps.setString(1, kichThuoc.getTen());
        ps.setString(2, kichThuoc.getTrangThai());
    }

    @Override
    protected String getUpdateSql() {
        return "UPDATE kich_thuoc SET ten = ?, trang_thai = ? WHERE id = ?";
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, KichThuoc kichThuoc) throws SQLException {
        ps.setString(1, kichThuoc.getTen());
        ps.setString(2, kichThuoc.getTrangThai());
        ps.setLong(3, kichThuoc.getId());
    }

}
