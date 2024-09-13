/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import entity.MauSac;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author huyvh
 */
public class MauSacDAO extends GennericDAO<MauSac, Long> {

    @Override
    protected String getTableName() {
        return "mau_sac";
    }

    @Override
    protected MauSac mapRow(ResultSet rs) throws SQLException {
        MauSac mauSac = new MauSac();
        mauSac.setId(rs.getLong("id"));
        mauSac.setTen(rs.getString("ten"));
        mauSac.setTrangThai(rs.getString("trang_thai"));
        return mauSac;
    }

    @Override
    protected String getInsertSql() {
        return "INSERT INTO mau_sac (ten, trang_thai) VALUES (?, ?)";
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, MauSac mauSac) throws SQLException {
        ps.setString(1, mauSac.getTen());
        ps.setString(2, mauSac.getTrangThai());
    }

    @Override
    protected String getUpdateSql() {
        return "UPDATE mau_sac SET ten = ?, trang_thai = ? WHERE id = ?";
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, MauSac mauSac) throws SQLException {
        ps.setString(1, mauSac.getTen());
        ps.setString(2, mauSac.getTrangThai());
        ps.setLong(3, mauSac.getId());
    }

}
