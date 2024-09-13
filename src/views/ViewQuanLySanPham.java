package views;

import DAO.ChatLieuDAO;
import DAO.KichThuocDAO;
import DAO.KieuDangDAO;
import DAO.MauSacDAO;
import DAO.SanPhamChiTietDAO;
import DAO.SanPhamDAO;
import constant.TrangThaiBienThe;
import entity.ChatLieu;
import entity.KichThuoc;
import entity.KieuDang;
import entity.MauSac;
import entity.SanPham;
import entity.SanPhamChiTiet;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

public class ViewQuanLySanPham extends javax.swing.JInternalFrame {

    private TrangThaiBienThe trangThaiBienThe;
    DefaultTableModel modelSanPham = new DefaultTableModel();
    DefaultTableModel modelProductDetail = new DefaultTableModel();

    SanPhamDAO sanPhamDAO = new SanPhamDAO();
    SanPhamChiTietDAO sanPhamChiTietDao = new SanPhamChiTietDAO();
    MauSacDAO mauSacDAO = new MauSacDAO();
    KichThuocDAO kichThuocDAO = new KichThuocDAO();
    ChatLieuDAO chatLieuDAO = new ChatLieuDAO();
    KieuDangDAO kieuDangDAO = new KieuDangDAO();
    // list danh sach cac doi tuong 
    List<SanPham> listSanPham = new ArrayList<>();
    List<SanPhamChiTiet> listProductDetail = new ArrayList<>();
    // thuoc tinh 
    List<MauSac> listMS = new ArrayList<>();
    List<KichThuoc> listKT = new ArrayList<>();
    List<ChatLieu> listCL = new ArrayList<>();
    List<KieuDang> listKD = new ArrayList<>();

    private Long idSanPham = null;
    private Long idProductDetail = null;

    public ViewQuanLySanPham() {
        initComponents();
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        BasicInternalFrameUI ui = (BasicInternalFrameUI) this.getUI();
        ui.setNorthPane(null);
        // gán modal cho table 
        modelSanPham = (DefaultTableModel) tbSanPham.getModel();
        modelProductDetail = (DefaultTableModel) tbProductDetail.getModel();
        // gán dữ liệu cho list sản phẩm
        listSanPham = sanPhamDAO.findAll();
        showTableSanPham(sanPhamDAO.findAll());

        // gán data cho combobox lọc và add 
        showAllComboBox();
    }

    // hiển thị dữ liệu lên bảng
    public void showTableSanPham(List<SanPham> list) {
        modelSanPham.setRowCount(0); // Xóa tất cả các hàng cũ trong bảng
        int index = 0;
        for (SanPham sp : list) {
            index++;
            modelSanPham.addRow(new Object[]{
                index,
                sp.getMa(),
                sp.getTen(),
                sp.getMoTa(),
                sp.getTrangThai()
            });
        }
    }

    public void showTableProductDetail(List<SanPhamChiTiet> list) {
        modelProductDetail.setRowCount(0); // Xóa tất cả các hàng cũ trong bảng
        int index = 0;

        // Định dạng số thành chuỗi tiền tệ Việt Nam
        //NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        for (SanPhamChiTiet detail : list) {
            // Tìm thông tin
            SanPham sp = sanPhamDAO.findById(detail.getIdSanPham()).get();
            MauSac ms = mauSacDAO.findById(detail.getIdMauSac()).get();
            KichThuoc kt = kichThuocDAO.findById(detail.getIdKichThuoc()).get();
            ChatLieu cl = chatLieuDAO.findById(detail.getIdChatLieu()).get();
            KieuDang kd = kieuDangDAO.findById(detail.getIdKieuDang()).get();

            // Thiết lập các giá trị hiển thị
            String ten = sp.getTen();
            String tenMS = ms.getTen();
            String tenKT = kt.getTen();
            String tenCL = cl.getTen();
            String tenKD = kd.getTen();
            lbTenSanPham.setText(ten);

            // Định dạng đơn giá thành VNĐ
            //String donGiaVND = currencyFormat.format(detail.getDonGia());
            index++;
            modelProductDetail.addRow(new Object[]{
                index,
                detail.getMa(),
                ten,
                detail.getSoLuong(),
                detail.getDonGia(), // Đơn giá được định dạng VNĐ
                tenMS,
                tenKT,
                tenCL,
                tenKD,
                detail.getTrangThai()
            });
        }
    }

    // hiển thị dữ liệu lên ô text
    private SanPham showSanPhamIndex(int index) {
        SanPham sp = listSanPham.get(index);

        txtTenSanPham.setText(sp.getTen());
        txtMaSanPham.setText(sp.getMa());
        txtMoTa.setText(sp.getMoTa());
        if (sp.getTrangThai().equalsIgnoreCase(trangThaiBienThe.HOAT_DONG.value)) {
            rdSanPhamHoatDong.setSelected(true);
            rdSanPhamKhongHoatDong.setSelected(false);
        } else {
            rdSanPhamHoatDong.setSelected(false);
            rdSanPhamKhongHoatDong.setSelected(true);
        }

        Long idSearch = sanPhamDAO.searchByName(sp.getTen()).getId();
        idSanPham = idSearch;
        return sp;
    }

    private SanPhamChiTiet showIndexProductDetail(int index) {
        SanPhamChiTiet detail = listProductDetail.get(index);
        //  find thông tin  

        SanPham sp = sanPhamDAO.findById(detail.getIdSanPham()).get();
        MauSac ms = mauSacDAO.findById(detail.getIdMauSac()).get();
        KichThuoc kt = kichThuocDAO.findById(detail.getIdKichThuoc()).get();
        ChatLieu cl = chatLieuDAO.findById(detail.getIdChatLieu()).get();
        KieuDang kd = kieuDangDAO.findById(detail.getIdKieuDang()).get();
        // set dữ liệu
        String ten = sp.getTen();
        String tenMS = ms.getTen();
        String tenKT = kt.getTen();
        String tenCL = cl.getTen();
        String tenKD = kd.getTen();
//        // Định dạng số thành chuỗi tiền tệ Việt Nam
//        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
//        // Định dạng đơn giá thành VNĐ
//        String donGiaVND = currencyFormat.format(detail.getDonGia());

        txtMaSPCT.setText(detail.getMa());
        txtSoLuongSPCT.setText(String.valueOf(detail.getSoLuong()));
        txtDonGiaSPCT.setText(String.valueOf(detail.getDonGia()));
        cbbMauSacSPCT.removeAllItems();
        cbbMauSacSPCT.addItem(tenMS);

        cbbKichThuocSPCT.removeAllItems();
        cbbKichThuocSPCT.addItem(tenKT);

        cbbChatLieuSPCT.removeAllItems();
        cbbChatLieuSPCT.addItem(tenCL);

        cbbKieuDangSPCT.removeAllItems();
        cbbKieuDangSPCT.addItem(tenKD);
        if (detail.getTrangThai().equals(TrangThaiBienThe.HOAT_DONG.value)) {
            rdHDDetail.setSelected(true);
            rdKHDDetail.setSelected(false);
        } else {
            rdHDDetail.setSelected(false);
            rdKHDDetail.setSelected(true);
        }

        // tìm id productdetail 
        SanPhamChiTiet spSearchByTen = sanPhamChiTietDao.findByMa(detail.getMa());
        idProductDetail = spSearchByTen.getId();

        return detail;
    }

    // dữ liệu để add 
    private SanPham dataSanPham() {
        String ma = txtMaSanPham.getText();
        if (ma.isEmpty()) {
            JOptionPane.showMessageDialog(this, "ten của bạn không được để trống !");
            return null;
        }
        String ten = txtTenSanPham.getText();
        if (ten.isEmpty()) {
            JOptionPane.showMessageDialog(this, "ma của bạn không được để trống !");
            return null;
        }
        String moTa = txtMoTa.getText();
        if (moTa.isEmpty()) {
            JOptionPane.showMessageDialog(this, "moTa của bạn không được để trống !");
            return null;
        }
        String trangThai;
        if (rdSanPhamHoatDong.isSelected()) {
            trangThai = trangThaiBienThe.HOAT_DONG.value;
        } else {
            trangThai = trangThaiBienThe.KHONG_HOAT_DONG.value;
        }
        SanPham sp = new SanPham(idSanPham, ma, ten, moTa, trangThai);
        return sp;
    }

    private SanPhamChiTiet dataProductDetail() {
        SanPhamChiTiet detail = new SanPhamChiTiet();
        String ma = txtMaSPCT.getText();
        if (ma.isEmpty()) {
            JOptionPane.showMessageDialog(null, "ma không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        // Validate số lượng
        String soLuong = txtSoLuongSPCT.getText().trim();
        if (soLuong.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Số lượng không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if (!soLuong.matches("\\d+")) {  // Chỉ cho phép ký tự số
            JOptionPane.showMessageDialog(null, "Số lượng phải là số và không được chứa ký tự đặc biệt", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        int soLuongInt = Integer.parseInt(soLuong);
        if (soLuongInt <= 1) {
            JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 1", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // Validate đơn giá
        String donGia = txtDonGiaSPCT.getText().trim();
        if (donGia.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Đơn giá không được để trống", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // Kiểm tra định dạng của đơn giá: chỉ cho phép số nguyên hoặc số thập phân
        if (!donGia.matches("\\d+(\\.\\d+)?")) {  // Cho phép số có phần thập phân
            JOptionPane.showMessageDialog(null, "Đơn giá phải là số hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        double donGiaDouble;
        try {
            donGiaDouble = Double.parseDouble(donGia);
            if (donGiaDouble <= 0) {
                JOptionPane.showMessageDialog(null, "Đơn giá phải lớn hơn 0", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Đơn giá không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // Validate combobox màu sắc
        int idMauSacInt = cbbMauSacSPCT.getSelectedIndex();
        if (idMauSacInt < 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn màu sắc", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        Long idMauSac = listMS.get(idMauSacInt).getId();

        // Validate combobox kích thước
        Integer idKichThuocInt = cbbKichThuocSPCT.getSelectedIndex();
        if (idKichThuocInt < 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn kích thước", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        Long idKichThuoc = listKT.get(idKichThuocInt).getId();

        // Validate combobox chất liệu
        Integer idChatLieuInt = cbbChatLieuSPCT.getSelectedIndex();
        if (idChatLieuInt < 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn chất liệu", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        Long idChatLieu = listCL.get(idChatLieuInt).getId();

        // Validate combobox kiểu dáng
        Integer idKieuDangInt = cbbKieuDangSPCT.getSelectedIndex();
        if (idKieuDangInt < 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn kiểu dáng", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        Long idKieuDang = listKD.get(idKieuDangInt).getId();

        // Validate trạng thái
        String trangThai;
        if (rdHDDetail.isSelected()) {
            trangThai = trangThaiBienThe.HOAT_DONG.value;
        } else if (rdKHDDetail.isSelected()) {
            trangThai = trangThaiBienThe.KHONG_HOAT_DONG.value;
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn trạng thái", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // Sau khi validate thành công, in ra thông tin sản phẩm
        System.out.println("Thông tin sản phẩm chi tiết:");
        System.out.println("Mã: " + ma);
        System.out.println("Số lượng: " + soLuongInt);
        System.out.println("Đơn giá: " + donGiaDouble);
        System.out.println("Màu sắc ID: " + idMauSac);
        System.out.println("Kích thước ID: " + idKichThuoc);
        System.out.println("Chất liệu ID: " + idChatLieu);
        System.out.println("Kiểu dáng ID: " + idKieuDang);
        System.out.println("Trạng thái: " + trangThai);

        // Thiết lập dữ liệu sản phẩm chi tiết
        detail.setMa(ma);
        detail.setSoLuong(soLuongInt);
        detail.setDonGia(donGiaDouble);
        detail.setIdMauSac(idMauSac);
        detail.setIdKichThuoc(idKichThuoc);
        detail.setIdChatLieu(idChatLieu);
        detail.setIdKieuDang(idKieuDang);
        detail.setTrangThai(trangThai);
        detail.setIdSanPham(idSanPham);

        return detail;
    }

    // show combobox
    private List showComboBoxMauSac(List<MauSac> list) {
        cbbMauSacSPCT.removeAllItems();
        for (MauSac detail : list) {
            cbbMauSacSPCT.addItem(detail.getTen());
        }
        return list;
    }

    private List showComboBoxKichThuoc(List<KichThuoc> list) {
        cbbKichThuocSPCT.removeAllItems();
        for (KichThuoc detail : list) {
            cbbKichThuocSPCT.addItem(detail.getTen());
        }
        return list;
    }

    private List showComboBoxChatLieu(List<ChatLieu> list) {
        cbbChatLieuSPCT.removeAllItems();
        for (ChatLieu detail : list) {
            cbbChatLieuSPCT.addItem(detail.getTen());
        }
        return list;
    }

    private List showComboBoxKieuDang(List<KieuDang> list) {
        cbbKieuDangSPCT.removeAllItems();
        for (KieuDang detail : list) {
            cbbKieuDangSPCT.addItem(detail.getTen());
        }
        return list;
    }

    // combobox lọc
    private List showComboBoxLocMauSac(List<MauSac> list) {
        cbbLocMauSacSPCT.removeAllItems();
        for (MauSac detail : list) {
            cbbLocMauSacSPCT.addItem(detail.getTen());
        }
        return list;
    }

    private List showComboBoxLocChatLieu(List<ChatLieu> list) {
        cbbLocChatLieuSPCT.removeAllItems();
        for (ChatLieu detail : list) {
            cbbLocChatLieuSPCT.addItem(detail.getTen());
        }
        return list;
    }

    private void showComboBoxLocTrangThaiDetail() {
        cbbLocTrangThaiSPCT.removeAllItems();
        cbbLocTrangThaiSPCT.addItem(TrangThaiBienThe.HOAT_DONG.value);
        cbbLocTrangThaiSPCT.addItem(TrangThaiBienThe.KHONG_HOAT_DONG.value);
    }

    private void showAllComboBox() {
        listMS = showComboBoxMauSac(mauSacDAO.findAll());
        listKT = showComboBoxKichThuoc(kichThuocDAO.findAll());
        listCL = showComboBoxChatLieu(chatLieuDAO.findAll());
        listKD = showComboBoxKieuDang(kieuDangDAO.findAll());
        showComboBoxLocMauSac(mauSacDAO.findAll());
        showComboBoxLocChatLieu(chatLieuDAO.findAll());
        showComboBoxLocTrangThaiDetail();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        backgroud = new javax.swing.JTabbedPane();
        pnSanPham = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSanPham = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtTenSanPham = new javax.swing.JTextField();
        txtMaSanPham = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        rdSanPhamHoatDong = new javax.swing.JRadioButton();
        rdSanPhamKhongHoatDong = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtSearchSanPham = new javax.swing.JTextField();
        cbbLocSanPhamTheoTrangThai = new javax.swing.JComboBox<>();
        btnSearchSanPham = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnSuaSanPham = new javax.swing.JButton();
        btnCleanInputSanPham = new javax.swing.JButton();
        btnThemSanPham = new javax.swing.JButton();
        pnSanPhamChiTiet = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        rdHDDetail = new javax.swing.JRadioButton();
        rdKHDDetail = new javax.swing.JRadioButton();
        cbbKichThuocSPCT = new javax.swing.JComboBox<>();
        cbbMauSacSPCT = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        cbbChatLieuSPCT = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        cbbKieuDangSPCT = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtSoLuongSPCT = new javax.swing.JTextField();
        txtDonGiaSPCT = new javax.swing.JTextField();
        lbTenSanPham = new javax.swing.JLabel();
        btnDisplayKD = new javax.swing.JButton();
        btnDisplayKT = new javax.swing.JButton();
        btnDisplayCL = new javax.swing.JButton();
        btnDisplayMS = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtMaSPCT = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        ipSearchSPCT = new javax.swing.JTextField();
        cbbLocMauSacSPCT = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        cbbLocTrangThaiSPCT = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        cbbLocChatLieuSPCT = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        btnUpdateProductDetail = new javax.swing.JButton();
        btnAddProductDetail = new javax.swing.JButton();
        btnImportExcell = new javax.swing.JButton();
        btnExportExcell = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbProductDetail = new javax.swing.JTable();
        btnLoadComboBox = new javax.swing.JButton();

        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        backgroud.setMinimumSize(new java.awt.Dimension(950, 650));

        pnSanPham.setBackground(new java.awt.Color(255, 255, 255));
        pnSanPham.setToolTipText("Quản Lý Sản Phẩm");
        pnSanPham.setPreferredSize(new java.awt.Dimension(991, 650));
        pnSanPham.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        jLabel1.setText("Quản Lý Sản Phẩm");
        pnSanPham.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(345, 6, -1, -1));

        tbSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã", "Tên", "Mô Tả", "Trạng Thái"
            }
        ));
        tbSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbSanPham);
        if (tbSanPham.getColumnModel().getColumnCount() > 0) {
            tbSanPham.getColumnModel().getColumn(0).setResizable(false);
            tbSanPham.getColumnModel().getColumn(0).setPreferredWidth(30);
            tbSanPham.getColumnModel().getColumn(1).setHeaderValue("Mã");
        }

        pnSanPham.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 400, 900, 187));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 204, 204), null));

        jLabel3.setText("Mã");

        jLabel4.setText("Tên Sản Phẩm");

        jLabel5.setText("Mô Tả");

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane2.setViewportView(txtMoTa);

        jLabel6.setText("Trạng Thái");

        buttonGroup1.add(rdSanPhamHoatDong);
        rdSanPhamHoatDong.setText("Hoạt Động");

        buttonGroup1.add(rdSanPhamKhongHoatDong);
        rdSanPhamKhongHoatDong.setText("Không Hoạt Động");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(50, 50, 50)
                        .addComponent(rdSanPhamHoatDong)))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rdSanPhamKhongHoatDong))
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(100, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(rdSanPhamHoatDong)
                                .addComponent(rdSanPhamKhongHoatDong))))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        pnSanPham.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 47, 900, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(0, 204, 204), null));

        jLabel7.setText("Tìm Mã Hoặc Tên");

        jLabel8.setText("Trạng Thái");

        cbbLocSanPhamTheoTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "HOẠT ĐỘNG", "KHÔNG HOẠT ĐỘNG" }));

        btnSearchSanPham.setText("Lọc");
        btnSearchSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchSanPhamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSearchSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel8)
                        .addGap(160, 160, 160))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbbLocSanPhamTheoTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearchSanPham)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSearchSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbLocSanPhamTheoTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSearchSanPham))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pnSanPham.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 500, 80));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        btnSuaSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/settings.png"))); // NOI18N
        btnSuaSanPham.setText("Sửa");
        btnSuaSanPham.setMaximumSize(new java.awt.Dimension(80, 30));
        btnSuaSanPham.setMinimumSize(new java.awt.Dimension(80, 40));
        btnSuaSanPham.setPreferredSize(new java.awt.Dimension(100, 35));
        btnSuaSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaSanPhamActionPerformed(evt);
            }
        });

        btnCleanInputSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/broom.png"))); // NOI18N
        btnCleanInputSanPham.setText("Clear");
        btnCleanInputSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCleanInputSanPhamActionPerformed(evt);
            }
        });

        btnThemSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/plus_1.png"))); // NOI18N
        btnThemSanPham.setText("Thêm");
        btnThemSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSanPhamActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnThemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSuaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addComponent(btnCleanInputSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCleanInputSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSuaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        pnSanPham.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 240, 340, 100));

        backgroud.addTab("Quản Lý Sản Phẩm", pnSanPham);

        pnSanPhamChiTiet.setBackground(new java.awt.Color(255, 255, 255));
        pnSanPhamChiTiet.setPreferredSize(new java.awt.Dimension(991, 650));

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 30)); // NOI18N
        jLabel2.setText("Sản Phẩm Chi Tiết");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 204, 204), 3, true));

        jLabel10.setText("Màu Sắc");

        jLabel11.setText("Kích Thước");

        jLabel12.setText("Trạng Thái");

        buttonGroup1.add(rdHDDetail);
        rdHDDetail.setText("Hoạt Động");

        buttonGroup1.add(rdKHDDetail);
        rdKHDDetail.setText("Không Hoạt Động");

        cbbKichThuocSPCT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbMauSacSPCT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel17.setText("Chất Liệu");

        cbbChatLieuSPCT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel18.setText("Kiểu Dáng");

        cbbKieuDangSPCT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel19.setText("Số Lượng");

        jLabel20.setText("Đơn Giá");

        lbTenSanPham.setFont(new java.awt.Font("Times New Roman", 1, 15)); // NOI18N
        lbTenSanPham.setForeground(new java.awt.Color(0, 153, 51));
        lbTenSanPham.setText(".....");

        btnDisplayKD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/plusmini.png"))); // NOI18N
        btnDisplayKD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisplayKDActionPerformed(evt);
            }
        });

        btnDisplayKT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/plusmini.png"))); // NOI18N
        btnDisplayKT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisplayKTActionPerformed(evt);
            }
        });

        btnDisplayCL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/plusmini.png"))); // NOI18N
        btnDisplayCL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisplayCLActionPerformed(evt);
            }
        });

        btnDisplayMS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/plusmini.png"))); // NOI18N
        btnDisplayMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisplayMSActionPerformed(evt);
            }
        });

        jLabel21.setText("Tên Sản Phẩm");

        jLabel9.setText("Mã");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(lbTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(txtMaSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(94, 94, 94)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jLabel20)
                    .addComponent(txtSoLuongSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDonGiaSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(cbbChatLieuSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDisplayCL))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(cbbMauSacSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDisplayMS))
                    .addComponent(jLabel10))
                .addGap(29, 29, 29)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(cbbKieuDangSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnDisplayKD))
                            .addComponent(jLabel18)
                            .addComponent(jLabel11))
                        .addGap(51, 51, 51)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(rdHDDetail)
                                .addGap(46, 46, 46))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(rdKHDDetail)
                                .addContainerGap())
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addContainerGap())))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(cbbKichThuocSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDisplayKT))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnDisplayMS)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel19)
                                .addComponent(jLabel21))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel17)
                                .addComponent(jLabel18)
                                .addComponent(jLabel12)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbbKieuDangSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnDisplayKD)
                                    .addComponent(lbTenSanPham)
                                    .addComponent(rdHDDetail, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbbChatLieuSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnDisplayCL)))
                            .addComponent(txtSoLuongSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDonGiaSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMaSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(0, 1, Short.MAX_VALUE)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(rdKHDDetail)
                                                .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING))
                                            .addComponent(jLabel10))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(cbbKichThuocSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cbbMauSacSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(btnDisplayKT))))))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 204, 204), 3, true));

        jLabel13.setText("Tìm Mã Hoặc Tên");

        jLabel14.setText("Lọc Trạng Thái");

        cbbLocMauSacSPCT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hoạt Động", "Không Hoạt Động" }));

        jLabel15.setText("Lọc Màu Sắc");

        cbbLocTrangThaiSPCT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hoạt Động", "Không Hoạt Động" }));

        jLabel16.setText("Lọc Chất Liệu");

        cbbLocChatLieuSPCT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hoạt Động", "Không Hoạt Động" }));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search.png"))); // NOI18N
        jButton1.setText("Search");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(ipSearchSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(cbbLocTrangThaiSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(17, 17, 17))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbbLocChatLieuSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbLocMauSacSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ipSearchSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbLocMauSacSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel16)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbLocTrangThaiSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbLocChatLieuSPCT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        btnUpdateProductDetail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/settings.png"))); // NOI18N
        btnUpdateProductDetail.setText("Sửa");
        btnUpdateProductDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateProductDetailActionPerformed(evt);
            }
        });

        btnAddProductDetail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/plus_1.png"))); // NOI18N
        btnAddProductDetail.setText("Thêm");
        btnAddProductDetail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProductDetailActionPerformed(evt);
            }
        });

        btnImportExcell.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/exportX32.png"))); // NOI18N
        btnImportExcell.setText("Nhập");
        btnImportExcell.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnImportExcell.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnImportExcell.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        btnExportExcell.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/importX32.png"))); // NOI18N
        btnExportExcell.setText("Xuất");
        btnExportExcell.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnExportExcell.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnExportExcell.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        tbProductDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã", "Tên", "Số Lượng", "Đơn Giá", "Màu Sắc", "Kích Thước", "Chất Liệu", "Kiểu Dáng", "Trạng Thái"
            }
        ));
        tbProductDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbProductDetailMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tbProductDetail);
        if (tbProductDetail.getColumnModel().getColumnCount() > 0) {
            tbProductDetail.getColumnModel().getColumn(0).setResizable(false);
            tbProductDetail.getColumnModel().getColumn(0).setPreferredWidth(30);
        }

        btnLoadComboBox.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/load-data.png"))); // NOI18N
        btnLoadComboBox.setText("Load ComboBox");
        btnLoadComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnSanPhamChiTietLayout = new javax.swing.GroupLayout(pnSanPhamChiTiet);
        pnSanPhamChiTiet.setLayout(pnSanPhamChiTietLayout);
        pnSanPhamChiTietLayout.setHorizontalGroup(
            pnSanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSanPhamChiTietLayout.createSequentialGroup()
                .addGroup(pnSanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnSanPhamChiTietLayout.createSequentialGroup()
                        .addGap(345, 345, 345)
                        .addComponent(jLabel2))
                    .addGroup(pnSanPhamChiTietLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(pnSanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnLoadComboBox)
                            .addGroup(pnSanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pnSanPhamChiTietLayout.createSequentialGroup()
                                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(60, 60, 60)
                                    .addGroup(pnSanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnAddProductDetail, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                        .addComponent(btnUpdateProductDetail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                                    .addGroup(pnSanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(btnImportExcell, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnExportExcell, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGap(46, 46, 46))
                                .addComponent(jScrollPane3)))))
                .addGap(101, 101, 101))
        );
        pnSanPhamChiTietLayout.setVerticalGroup(
            pnSanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnSanPhamChiTietLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(pnSanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnSanPhamChiTietLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(pnSanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAddProductDetail)
                            .addComponent(btnImportExcell, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnSanPhamChiTietLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnUpdateProductDetail)
                            .addComponent(btnExportExcell, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(12, 12, 12)
                .addComponent(btnLoadComboBox)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        backgroud.addTab("Sản Phẩm Chi Tiết", pnSanPhamChiTiet);

        getContentPane().add(backgroud);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSanPhamMouseClicked
        int row = tbSanPham.getSelectedRow();
        int check = tbSanPham.getSelectedRow();
        if (check == -1) {
            JOptionPane.showMessageDialog(this, "vui lòng chọn sản phẩm !");
            return;
        }
        if (evt.getButton() == MouseEvent.BUTTON1) {
            // showIndexSanPham(row);
            // System.out.println("id sản phẩm là : " + idSanPham);
            System.out.println("chuột trái click");
            showSanPhamIndex(row);
            System.out.println("ProductID : " + idSanPham);
        } else if (evt.getButton() == MouseEvent.BUTTON3) {
            int index = backgroud.indexOfComponent(pnSanPhamChiTiet);
            backgroud.setSelectedIndex(index);
            listProductDetail = sanPhamChiTietDao.findAllByIdSanPham(idSanPham);
            showTableProductDetail(listProductDetail);
            System.out.println("chuột phải click");
        }
    }//GEN-LAST:event_tbSanPhamMouseClicked

    private void btnUpdateProductDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateProductDetailActionPerformed
        SanPhamChiTiet spUpdate;
        if (dataProductDetail() != null) {
            spUpdate = dataProductDetail();
            spUpdate.setId(idProductDetail);
        } else {
            JOptionPane.showMessageDialog(null, "Vui Lòng điền đủ Thông tin cho san phẩm", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Check xem có dòng nào được chọn không
        int checkClickRow = tbProductDetail.getSelectedRow();
        if (checkClickRow == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng để sửa", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            int chon = JOptionPane.showConfirmDialog(this, "bạn cho muốn sửa không ?");
            if (chon == JOptionPane.OK_OPTION) {
                // Cập nhật dữ liệu trong cơ sở dữ liệu
                sanPhamChiTietDao.update(spUpdate);
                // Sau khi cập nhật, làm mới danh sách sản phẩm chi tiết
                listProductDetail = sanPhamChiTietDao.findAllByIdSanPham(idSanPham);
                showTableProductDetail(listProductDetail); // Hiển thị lại bảng với danh sách mới
            } else {
                return;
            }
        }
    }//GEN-LAST:event_btnUpdateProductDetailActionPerformed

    private void tbProductDetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbProductDetailMouseClicked
        showIndexProductDetail(tbProductDetail.getSelectedRow());
    }//GEN-LAST:event_tbProductDetailMouseClicked

    private void btnThemSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSanPhamActionPerformed
        if (dataSanPham() != null) {
            SanPham sp = dataSanPham();
            if (sp != null) {
                if (!sanPhamDAO.existsByName(sp.getTen())) // Tiến hành sửa
                {
                    JOptionPane.showMessageDialog(this, sanPhamDAO.create(sp));
                    // Cập nhật lại bảng sau khi sửa
                    showTableSanPham(sanPhamDAO.findAll());
                } else {
                    JOptionPane.showMessageDialog(this, "Tên : " + sp.getTen() + " => Đã Tồn Tại");
                }
            }
        } else {
            System.out.println("Thêm Thất bại! ");
        }
    }//GEN-LAST:event_btnThemSanPhamActionPerformed

    private void btnSuaSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaSanPhamActionPerformed
        if (dataSanPham() != null) {
            SanPham sp = dataSanPham();

            // Kiểm tra xem có dòng nào được chọn không
            int selectedRow = tbSanPham.getSelectedRow();
            if (selectedRow == -1) {
                // Nếu không có dòng nào được chọn, thông báo lỗi và không cho sửa
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một dòng để sửa!");
                return;
            }

            // Nếu đã chọn dòng, thực hiện tiếp tục thao tác sửa
            // sp.setId(idSanPham);  // Giả sử `idMauSac` lấy từ dòng đã chọn
            if (sp != null) {
                int chon = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa không?");
                if (chon == JOptionPane.OK_OPTION) {
                    if (!sanPhamDAO.existsByName(sp.getTen())) // Tiến hành sửa
                    {
                        System.out.println("spid : " + sp.getId());
                        JOptionPane.showMessageDialog(this, sanPhamDAO.update(sp));
                        // Cập nhật lại bảng sau khi sửa
                        showTableSanPham(sanPhamDAO.findAll());
                    } else {
                        JOptionPane.showMessageDialog(this, "Tên : " + sp.getTen() + " => Đã Tồn Tại");
                    }

                }
            } else {
                System.out.println("Sửa thất bại!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Dữ Liệu Trống");
        }
    }//GEN-LAST:event_btnSuaSanPhamActionPerformed

    private void btnCleanInputSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCleanInputSanPhamActionPerformed
        int chon = JOptionPane.showConfirmDialog(this, "bạn Có muốn clean không ?");
        if (chon == JOptionPane.OK_OPTION) {
            txtMaSanPham.setText("");
            txtTenSanPham.setText("");
            txtMoTa.setText("");
            txtSearchSanPham.setText("");
            showTableSanPham(sanPhamDAO.findAll());
        } else {
            return;
        }

    }//GEN-LAST:event_btnCleanInputSanPhamActionPerformed

    private void btnSearchSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchSanPhamActionPerformed
        String tenOrMa = txtSearchSanPham.getText();
        String status = cbbLocSanPhamTheoTrangThai.getSelectedItem().toString();
        if (tenOrMa.isEmpty() || status.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tìm thất bại vui lòng nhập thông tin !");
        } else {
            List<SanPham> list = sanPhamDAO.searchTenOrMaAndTrangThai(tenOrMa.trim(), status);
            listSanPham = list;
            showTableSanPham(listSanPham);
        }
    }//GEN-LAST:event_btnSearchSanPhamActionPerformed

    private void btnLoadComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadComboBoxActionPerformed
//        showComboBoxMauSac(mauSacDAO.findAll());
//        showComboBoxKichThuoc(kichThuocDAO.findAll());
//        showComboBoxChatLieu(chatLieuDAO.findAll());
//        showComboBoxKieuDang(kieuDangDAO.findAll());
        showAllComboBox();
    }//GEN-LAST:event_btnLoadComboBoxActionPerformed

    private void btnAddProductDetailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProductDetailActionPerformed

        SanPhamChiTiet productDetail = dataProductDetail();
        if (productDetail != null) {
            int chon = JOptionPane.showConfirmDialog(null, "Bạn Có Muốn Thêm Không ?");
            if (chon == JOptionPane.OK_OPTION) {
                // check mã tồn tại chưa : 
                boolean check = sanPhamChiTietDao.checkMaTonTai(productDetail.getMa());
                if (check) {
                    JOptionPane.showMessageDialog(null, "Mã Đã Tồn Tại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                sanPhamChiTietDao.create(productDetail);
                // Sau khi thêm mới, đảm bảo danh sách được làm mới hoàn toàn
                listProductDetail = sanPhamChiTietDao.findAllByIdSanPham(idSanPham);
                System.out.println("độ dại list là : " + listProductDetail.size());
                // Hiển thị lại bảng với danh sách mới
                showTableProductDetail(listProductDetail);
            } else {
                return;
            }
        }
    }//GEN-LAST:event_btnAddProductDetailActionPerformed

    private void btnDisplayCLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisplayCLActionPerformed
        ViewChatLieu view = new ViewChatLieu();
        view.setVisible(true);
    }//GEN-LAST:event_btnDisplayCLActionPerformed

    private void btnDisplayKDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisplayKDActionPerformed
        ViewKieuDang view = new ViewKieuDang();
        view.setVisible(true);
    }//GEN-LAST:event_btnDisplayKDActionPerformed

    private void btnDisplayMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisplayMSActionPerformed
        ViewMauSac view = new ViewMauSac();
        view.setVisible(true);
    }//GEN-LAST:event_btnDisplayMSActionPerformed

    private void btnDisplayKTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisplayKTActionPerformed
        ViewKichThuoc view = new ViewKichThuoc();
        view.setVisible(true);
    }//GEN-LAST:event_btnDisplayKTActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane backgroud;
    private javax.swing.JButton btnAddProductDetail;
    private javax.swing.JButton btnCleanInputSanPham;
    private javax.swing.JButton btnDisplayCL;
    private javax.swing.JButton btnDisplayKD;
    private javax.swing.JButton btnDisplayKT;
    private javax.swing.JButton btnDisplayMS;
    private javax.swing.JButton btnExportExcell;
    private javax.swing.JButton btnImportExcell;
    private javax.swing.JButton btnLoadComboBox;
    private javax.swing.JButton btnSearchSanPham;
    private javax.swing.JButton btnSuaSanPham;
    private javax.swing.JButton btnThemSanPham;
    private javax.swing.JButton btnUpdateProductDetail;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbChatLieuSPCT;
    private javax.swing.JComboBox<String> cbbKichThuocSPCT;
    private javax.swing.JComboBox<String> cbbKieuDangSPCT;
    private javax.swing.JComboBox<String> cbbLocChatLieuSPCT;
    private javax.swing.JComboBox<String> cbbLocMauSacSPCT;
    private javax.swing.JComboBox<String> cbbLocSanPhamTheoTrangThai;
    private javax.swing.JComboBox<String> cbbLocTrangThaiSPCT;
    private javax.swing.JComboBox<String> cbbMauSacSPCT;
    private javax.swing.JTextField ipSearchSPCT;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbTenSanPham;
    private javax.swing.JPanel pnSanPham;
    private javax.swing.JPanel pnSanPhamChiTiet;
    private javax.swing.JRadioButton rdHDDetail;
    private javax.swing.JRadioButton rdKHDDetail;
    private javax.swing.JRadioButton rdSanPhamHoatDong;
    private javax.swing.JRadioButton rdSanPhamKhongHoatDong;
    private javax.swing.JTable tbProductDetail;
    private javax.swing.JTable tbSanPham;
    private javax.swing.JTextField txtDonGiaSPCT;
    private javax.swing.JTextField txtMaSPCT;
    private javax.swing.JTextField txtMaSanPham;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtSearchSanPham;
    private javax.swing.JTextField txtSoLuongSPCT;
    private javax.swing.JTextField txtTenSanPham;
    // End of variables declaration//GEN-END:variables
}
