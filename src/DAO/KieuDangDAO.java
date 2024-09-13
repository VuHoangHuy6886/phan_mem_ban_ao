/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import entity.ChatLieu;
import entity.KichThuoc;
import entity.KieuDang;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author huyvh
 */
public class KieuDangDAO extends GennericDAO<KieuDang, Long> {

    @Override
    protected String getTableName() {
        return "kieu_dang";
    }

    @Override
    protected KieuDang mapRow(ResultSet rs) throws SQLException {
        KieuDang kieuDang = new KieuDang();
        kieuDang.setId(rs.getLong("id"));
        kieuDang.setTen(rs.getString("ten"));
        kieuDang.setTrangThai(rs.getString("trang_thai"));
        return kieuDang;
    }

    @Override
    protected String getInsertSql() {
        return "INSERT INTO kieu_dang (ten, trang_thai) VALUES (?, ?)";
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, KieuDang kieuDang) throws SQLException {
        ps.setString(1, kieuDang.getTen());
        ps.setString(2, kieuDang.getTrangThai());
    }

    @Override
    protected String getUpdateSql() {
        return "UPDATE kieu_dang SET ten = ?, trang_thai = ? WHERE id = ?";
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, KieuDang kieuDang) throws SQLException {
        ps.setString(1, kieuDang.getTen());
        ps.setString(2, kieuDang.getTrangThai());
        ps.setLong(3, kieuDang.getId());
    }

}
