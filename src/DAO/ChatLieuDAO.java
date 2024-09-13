/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import entity.ChatLieu;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author huyvh
 */
public class ChatLieuDAO extends GennericDAO<ChatLieu, Long> {

    @Override
    protected String getTableName() {
        return "chat_lieu";
    }

    @Override
    protected ChatLieu mapRow(ResultSet rs) throws SQLException {
        ChatLieu chatLieu = new ChatLieu();
        chatLieu.setId(rs.getLong("id"));
        chatLieu.setTen(rs.getString("ten"));
        chatLieu.setTrangThai(rs.getString("trang_thai"));
        return chatLieu;
    }

    @Override
    protected String getInsertSql() {
        return "INSERT INTO chat_lieu (ten, trang_thai) VALUES (?, ?)"; // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void setInsertParameters(PreparedStatement ps, ChatLieu chatLieu) throws SQLException {
        ps.setString(1, chatLieu.getTen());
        ps.setString(2, chatLieu.getTrangThai());
    }

    @Override
    protected String getUpdateSql() {
        return "UPDATE chat_lieu SET ten = ?, trang_thai = ? WHERE id = ?";
    }

    @Override
    protected void setUpdateParameters(PreparedStatement ps, ChatLieu chatLieu) throws SQLException {
        ps.setString(1, chatLieu.getTen());
        ps.setString(2, chatLieu.getTrangThai());
        ps.setLong(3, chatLieu.getId());
    }

}
