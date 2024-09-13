-- Tạo database
CREATE DATABASE db_duan_ban_ao;
GO

-- Sử dụng database
USE db_duan_ban_ao;
GO

-- Tạo bảng nhan_vien
CREATE TABLE nhan_vien (
    id INT PRIMARY KEY IDENTITY(1,1),
    ma VARCHAR(50) NOT NULL,
    ten NVARCHAR(255) NOT NULL,
    email VARCHAR(255),
    password VARCHAR(255),
    role NVARCHAR(50),
    trang_thai NVARCHAR(50)
);
GO

-- Tạo bảng khach_hang
CREATE TABLE khach_hang (
    id INT PRIMARY KEY IDENTITY(1,1),
    ma VARCHAR(50) NOT NULL,
    ten NVARCHAR(255) NOT NULL,
    email VARCHAR(255),
    dia_chi NVARCHAR(255),
    so_dien_thoai VARCHAR(15)
);
GO

-- Tạo bảng hoa_don
CREATE TABLE hoa_don (
    id INT PRIMARY KEY IDENTITY(1,1),
    ma VARCHAR(50) NOT NULL,
    id_khach_hang INT,
    id_nhan_vien INT,
    ngay_bat_dau DATETIME,
    ngay_ket_thuc DATETIME,
    tong_tien FLOAT,
    phuong_thuc_thanh_toan NVARCHAR(50),
    trang_thai NVARCHAR(50),
    CONSTRAINT FK_HoaDon_KhachHang FOREIGN KEY (id_khach_hang) REFERENCES khach_hang(id),
    CONSTRAINT FK_HoaDon_NhanVien FOREIGN KEY (id_nhan_vien) REFERENCES nhan_vien(id)
);
GO

-- Tạo bảng san_pham
CREATE TABLE san_pham (
    id INT PRIMARY KEY IDENTITY(1,1),
    ma VARCHAR(50) NOT NULL,
    ten NVARCHAR(255) NOT NULL,
    mo_ta NVARCHAR(500),
    ngay_tao DATETIME,
    ngay_sua DATETIME,
    trang_thai NVARCHAR(50)
);
GO

-- Tạo bảng san_pham_chi_tiet
CREATE TABLE san_pham_chi_tiet (
    id INT PRIMARY KEY IDENTITY(1,1),
    so_luong INT,
    don_gia FLOAT,
    id_mau_sac INT,
    id_kich_thuoc INT,
    id_chat_lieu INT,
    id_kieu_dang INT,
    trang_thai NVARCHAR(50),
    CONSTRAINT FK_SanPhamChiTiet_MauSac FOREIGN KEY (id_mau_sac) REFERENCES mau_sac(id),
    CONSTRAINT FK_SanPhamChiTiet_KichThuoc FOREIGN KEY (id_kich_thuoc) REFERENCES kich_thuoc(id),
    CONSTRAINT FK_SanPhamChiTiet_ChatLieu FOREIGN KEY (id_chat_lieu) REFERENCES chat_lieu(id),
    CONSTRAINT FK_SanPhamChiTiet_KieuDang FOREIGN KEY (id_kieu_dang) REFERENCES kieu_dang(id)
);
GO

-- Tạo bảng hoa_don_chi_tiet
CREATE TABLE hoa_don_chi_tiet (
    id INT PRIMARY KEY IDENTITY(1,1),
    id_hoa_don INT,
    id_san_pham_chi_tiet INT,
    so_luong INT,
    don_gia FLOAT,
    CONSTRAINT FK_HoaDonChiTiet_HoaDon FOREIGN KEY (id_hoa_don) REFERENCES hoa_don(id),
    CONSTRAINT FK_HoaDonChiTiet_SanPhamChiTiet FOREIGN KEY (id_san_pham_chi_tiet) REFERENCES san_pham_chi_tiet(id)
);
GO

-- Tạo bảng mau_sac
CREATE TABLE mau_sac (
    id INT PRIMARY KEY IDENTITY(1,1),
    ten NVARCHAR(255),
    trang_thai NVARCHAR(50)
);
GO

-- Tạo bảng kich_thuoc
CREATE TABLE kich_thuoc (
    id INT PRIMARY KEY IDENTITY(1,1),
    ten NVARCHAR(255),
    trang_thai NVARCHAR(50)
);
GO

-- Tạo bảng chat_lieu
CREATE TABLE chat_lieu (
    id INT PRIMARY KEY IDENTITY(1,1),
    ten NVARCHAR(255),
    trang_thai NVARCHAR(50)
);
GO

-- Tạo bảng kieu_dang
CREATE TABLE kieu_dang (
    id INT PRIMARY KEY IDENTITY(1,1),
    ten NVARCHAR(255),
    trang_thai NVARCHAR(50)
);
GO
-- Dữ liệu cho bảng nhan_vien
INSERT INTO nhan_vien ( ma, ten, email, password, role, trang_thai) VALUES
('NV001', 'Vu Hoang Huy', 'huyvh04@gmail.com', '123', 'admin', 'active');

-- Dữ liệu cho bảng khach_hang
INSERT INTO khach_hang (ma, ten, email, dia_chi, so_dien_thoai) 
VALUES
('KH001', 'Pham Thi D', 'd.pham@example.com', '123 Le Loi, Hanoi', '0912345678'),
('KH002', 'Hoang Van E', 'e.hoang@example.com', '456 Tran Phu, HCMC', '0987654321'),
('KH003', 'Nguyen Thi F', 'f.nguyen@example.com', '789 Ly Thuong Kiet, Da Nang', '0901234567');

-- Dữ liệu cho bảng hoa_don
INSERT INTO hoa_don (ma, id_khach_hang, id_nhan_vien, ngay_bat_dau, ngay_ket_thuc, tong_tien, phuong_thuc_thanh_toan, trang_thai)
VALUES
('HD001', 1, 1, '2024-08-01', '2024-08-02', 1000000, 'Cash', 'Completed'),
('HD002', 2, 1, '2024-08-03', '2024-08-04', 2000000, 'Credit Card', 'Pending'),
('HD003', 3, 1, '2024-08-05', '2024-08-06', 1500000, 'Cash', 'Completed');

-- Dữ liệu cho bảng san_pham
INSERT INTO san_pham (ma, ten, mo_ta, ngay_tao, ngay_sua, trang_thai)
VALUES
('SP001', 'T-Shirt', 'A cool T-shirt', '2024-08-01', '2024-08-02', 'Available'),
('SP002', 'Jeans', 'Stylish blue jeans', '2024-08-03', '2024-08-04', 'Available'),
('SP003', 'Sneakers', 'Comfortable running shoes', '2024-08-05', '2024-08-06', 'Out of Stock');
-- Dữ liệu cho bảng mau_sac
INSERT INTO mau_sac (ten, trang_thai)
VALUES
('Red', 'Active'),
('Blue', 'Active'),
('Black', 'Inactive');

-- Dữ liệu cho bảng kich_thuoc
INSERT INTO kich_thuoc (ten, trang_thai)
VALUES
('Small', 'Active'),
('Medium', 'Active'),
('Large', 'Inactive');

-- Dữ liệu cho bảng chat_lieu
INSERT INTO chat_lieu (ten, trang_thai)
VALUES
('Cotton', 'Active'),
('Polyester', 'Active'),
('Leather', 'Inactive');

-- Dữ liệu cho bảng kieu_dang
INSERT INTO kieu_dang (ten, trang_thai)
VALUES
('Casual', 'Active'),
('Formal', 'Active'),
('Sport', 'Inactive');

-- Dữ liệu cho bảng san_pham_chi_tiet
INSERT INTO san_pham_chi_tiet (so_luong, don_gia, id_mau_sac, id_kich_thuoc, id_chat_lieu, id_kieu_dang, trang_thai)
VALUES
(10, 250000, 1, 1, 1, 1, 'Active'),
(20, 500000, 2, 2, 2, 2, 'Active'),
(30, 1000000, 3, 3, 3, 3, 'Inactive');

-- Dữ liệu cho bảng hoa_don_chi_tiet
INSERT INTO hoa_don_chi_tiet (id_hoa_don, id_san_pham_chi_tiet, so_luong, don_gia)
VALUES
(5, 2, 2, 250000),
(6, 3, 1, 500000),
(7, 4, 3, 1000000);


