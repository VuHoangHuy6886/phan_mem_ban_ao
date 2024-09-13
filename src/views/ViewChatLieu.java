/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package views;

import DAO.ChatLieuDAO;
import constant.TrangThaiBienThe;
import entity.ChatLieu;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author huyvh
 */
public class ViewChatLieu extends javax.swing.JFrame {

    DefaultTableModel dtm = new DefaultTableModel();
    ChatLieuDAO service = new ChatLieuDAO();
    List<ChatLieu> list = new ArrayList<>();
    TrangThaiBienThe trangThaiBienThe;
    // khởi tạo biến id để sửa 
    private Long idChatLieu = null;

    public ViewChatLieu() {
        initComponents();
        dtm = (DefaultTableModel) tbMauSac.getModel();
        list = service.findAll();
        showTable(service.findAll());
        showIndex(0);
    }

    private void showTable(List<ChatLieu> list) {
        dtm.setRowCount(0);
        int index = 0;
        for (ChatLieu detail : list) {
            index++;
            dtm.addRow(new Object[]{
                index,
                detail.getTen(),
                detail.getTrangThai()
            });
        }
    }

    private ChatLieu showIndex(int index) {
        list = service.findAll();
        ChatLieu detail = list.get(index);
        txtTen.setText(detail.getTen());
        if (detail.getTrangThai().equalsIgnoreCase(trangThaiBienThe.HOAT_DONG.value)) {
            rdHD.setSelected(true);
            rdKHD.setSelected(false);
        } else {
            rdHD.setSelected(false);
            rdKHD.setSelected(true);
        }
        return detail;
    }

    // data
    public ChatLieu data() {
        String ten = txtTen.getText();
        if (ten.isEmpty()) {
            JOptionPane.showMessageDialog(this, "ten của bạn không được để trống !");
            return null;
        }
        String trangThai;
        if (rdHD.isSelected()) {
            trangThai = trangThaiBienThe.HOAT_DONG.value;
        } else {
            trangThai = trangThaiBienThe.KHONG_HOAT_DONG.value;
        }
        ChatLieu detail = new ChatLieu(Long.MIN_VALUE, ten, trangThai);
        return detail;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        rdHD = new javax.swing.JRadioButton();
        rdKHD = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbMauSac = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Màu Sắc");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 20)); // NOI18N
        jLabel1.setText("Chất Liệu");

        jLabel2.setText("Tên");

        jLabel3.setText("Trạng Thái");

        buttonGroup1.add(rdHD);
        rdHD.setText("Hoạt Động");

        buttonGroup1.add(rdKHD);
        rdKHD.setText("Không Hoạt Động");

        tbMauSac.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Tên", "Trạng Thái"
            }
        ));
        tbMauSac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMauSacMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbMauSac);

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/plus_1.png"))); // NOI18N
        btnAdd.setText("Thêm");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/settings.png"))); // NOI18N
        btnUpdate.setText("Sửa");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rdHD)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdKHD))
                            .addComponent(txtTen))
                        .addGap(86, 86, 86)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addComponent(jLabel1)))
                .addContainerGap(39, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnUpdate))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rdHD)
                            .addComponent(rdKHD))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tbMauSacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMauSacMouseClicked
        int index = tbMauSac.getSelectedRow();
        showIndex(index);
        idChatLieu = showIndex(index).getId();
        System.out.println("id ms : " + idChatLieu);
    }//GEN-LAST:event_tbMauSacMouseClicked

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (data() != null) {
            ChatLieu detail = data();
            if (detail != null) {
                if (!service.existsByName(detail.getTen())) // Tiến hành sửa
                {
                    JOptionPane.showMessageDialog(this, service.create(detail));
                    // Cập nhật lại bảng sau khi sửa
                    showTable(service.findAll());
                } else {
                    JOptionPane.showMessageDialog(this, "Tên : " + detail.getTen() + " => Đã Tồn Tại");
                }
            }
        } else {
            System.out.println("Thêm Thất bại! ");
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (data() != null) {
            ChatLieu detail = data();

            // Kiểm tra xem có dòng nào được chọn không
            int selectedRow = tbMauSac.getSelectedRow();
            if (selectedRow == -1) {
                // Nếu không có dòng nào được chọn, thông báo lỗi và không cho sửa
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để sửa!");
                return;
            }

            // Nếu đã chọn dòng, thực hiện tiếp tục thao tác sửa
            detail.setId(idChatLieu);  // Giả sử `idMauSac` lấy từ dòng đã chọn
            if (detail != null) {
                int chon = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa không?");
                if (chon == JOptionPane.OK_OPTION) {
                    if (!service.existsByName(detail.getTen())) // Tiến hành sửa
                    {
                        JOptionPane.showMessageDialog(this, service.update(detail));
                        // Cập nhật lại bảng sau khi sửa
                        showTable(service.findAll());
                    } else {
                        JOptionPane.showMessageDialog(this, "Tên : " + detail.getTen() + " => Đã Tồn Tại");
                    }

                }
            } else {
                System.out.println("Sửa thất bại!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Dữ Liệu Trống");
        }

    }//GEN-LAST:event_btnUpdateActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewChatLieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewChatLieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewChatLieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewChatLieu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewChatLieu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnUpdate;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JRadioButton rdHD;
    private javax.swing.JRadioButton rdKHD;
    private javax.swing.JTable tbMauSac;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables
}
