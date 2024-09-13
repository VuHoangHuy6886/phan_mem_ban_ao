-- Tạo database mới
CREATE DATABASE db_duan_ban_ao;
GO

-- Sử dụng database
USE db_duan_ban_ao;
GO

-- Tạo bảng nhan_vien
CREATE TABLE nhan_vien (
    id BIGINT PRIMARY KEY IDENTITY(1,1),
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
    id BIGINT PRIMARY KEY IDENTITY(1,1),
    ma VARCHAR(50) NOT NULL,
    ten NVARCHAR(255) NOT NULL,
    email VARCHAR(255),
    dia_chi NVARCHAR(255),
    so_dien_thoai VARCHAR(15)
);
GO

-- Tạo bảng hoa_don
CREATE TABLE hoa_don (
    id BIGINT PRIMARY KEY IDENTITY(1,1),
    ma VARCHAR(50) NOT NULL,
    id_khach_hang BIGINT,
    id_nhan_vien BIGINT,
    ngay_bat_dau DATETIME,
    ngay_ket_thuc DATETIME,
    tong_tien FLOAT,
    phuong_thuc_thanh_toan NVARCHAR(50),
    trang_thai NVARCHAR(50),
	id_phieu_giam_gia BIGINT
);
Go
-- Tạo bảng san_pham
CREATE TABLE san_pham (
    id BIGINT PRIMARY KEY IDENTITY(1,1),
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
    id BIGINT PRIMARY KEY IDENTITY(1,1),
    so_luong INT,
    don_gia float,
    id_mau_sac BIGINT,
    id_san_pham BIGINT,
    id_kich_thuoc BIGINT,
    id_chat_lieu BIGINT,
    id_kieu_dang BIGINT,
    trang_thai NVARCHAR(50)
);
GO

-- Tạo bảng hoa_don_chi_tiet
CREATE TABLE hoa_don_chi_tiet (
    id BIGINT PRIMARY KEY IDENTITY(1,1),
    id_hoa_don BIGINT,
    id_san_pham_chi_tiet BIGINT,
    so_luong INT,
    don_gia FLOAT
);
GO

-- Tạo bảng mau_sac
CREATE TABLE mau_sac (
    id BIGINT PRIMARY KEY IDENTITY(1,1),
    ten NVARCHAR(255),
    trang_thai NVARCHAR(50)
);
GO

-- Tạo bảng kich_thuoc
CREATE TABLE kich_thuoc (
    id BIGINT PRIMARY KEY IDENTITY(1,1),
    ten NVARCHAR(255),
    trang_thai NVARCHAR(50)
);
GO

-- Tạo bảng chat_lieu
CREATE TABLE chat_lieu (
    id BIGINT PRIMARY KEY IDENTITY(1,1),
    ten NVARCHAR(255),
    trang_thai NVARCHAR(50)
);
GO

-- Tạo bảng kieu_dang
CREATE TABLE kieu_dang (
    id BIGINT PRIMARY KEY IDENTITY(1,1),
    ten NVARCHAR(255),
    trang_thai NVARCHAR(50)
);
GO

-- Tạo bảng phieu_giam_gia
CREATE TABLE phieu_giam_gia (
    id BIGINT PRIMARY KEY IDENTITY(1,1),
    ma VARCHAR(50) NOT NULL,
    ten NVARCHAR(255) NOT NULL,
    mo_ta NVARCHAR(500),
    phan_tram_giam FLOAT NOT NULL,
    giam_toi_da FLOAT NOT NULL,
    ngay_bat_dau DATETIME NOT NULL,
    ngay_ket_thuc DATETIME NOT NULL,
    trang_thai NVARCHAR(50) NOT NULL
);
GO

-- Thêm khóa ngoại sau khi tạo bảng
-- Khóa ngoại bảng hoa_don
ALTER TABLE hoa_don
ADD 
		CONSTRAINT FK_HoaDon_KhachHang FOREIGN KEY (id_khach_hang) REFERENCES khach_hang(id),
		CONSTRAINT FK_HoaDon_NhanVien FOREIGN KEY (id_nhan_vien) REFERENCES nhan_vien(id),
		CONSTRAINT FK_HoaDon_PhieuGiamGia FOREIGN KEY (id_phieu_giam_gia) REFERENCES phieu_giam_gia(id);
GO

-- Khóa ngoại bảng san_pham_chi_tiet
ALTER TABLE san_pham_chi_tiet
ADD CONSTRAINT FK_SanPhamChiTiet_MauSac FOREIGN KEY (id_mau_sac) REFERENCES mau_sac(id),
    CONSTRAINT FK_SanPhamChiTiet_KichThuoc FOREIGN KEY (id_kich_thuoc) REFERENCES kich_thuoc(id),
    CONSTRAINT FK_SanPhamChiTiet_ChatLieu FOREIGN KEY (id_chat_lieu) REFERENCES chat_lieu(id), 
    CONSTRAINT FK_SanPhamChiTiet_SanPham FOREIGN KEY (id_san_pham) REFERENCES san_pham(id),
    CONSTRAINT FK_SanPhamChiTiet_KieuDang FOREIGN KEY (id_kieu_dang) REFERENCES kieu_dang(id);
GO

-- Khóa ngoại bảng hoa_don_chi_tiet
ALTER TABLE hoa_don_chi_tiet
ADD CONSTRAINT FK_HoaDonChiTiet_HoaDon FOREIGN KEY (id_hoa_don) REFERENCES hoa_don(id),
    CONSTRAINT FK_HoaDonChiTiet_SanPhamChiTiet FOREIGN KEY (id_san_pham_chi_tiet) REFERENCES san_pham_chi_tiet(id);
GO

-- Dữ liệu cho bảng nhan_vien
INSERT INTO nhan_vien (ma, ten, email, password, role, trang_thai) VALUES
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
('Black', 'Inactive'),
('Green', 'Active');
GO

-- Dữ liệu cho bảng kich_thuoc
INSERT INTO kich_thuoc (ten, trang_thai)
VALUES
('Small', 'Active'),
('Medium', 'Active'),
('Large', 'Inactive'),
('Extra Large', 'Active');
GO

-- Dữ liệu cho bảng chat_lieu
INSERT INTO chat_lieu (ten, trang_thai)
VALUES
('Cotton', 'Active'),
('Polyester', 'Active'),
('Leather', 'Inactive'),
('Wool', 'Active');
GO

-- Dữ liệu cho bảng kieu_dang
INSERT INTO kieu_dang (ten, trang_thai)
VALUES
('Casual', 'Active'),
('Formal', 'Active'),
('Sport', 'Inactive'),
('Business', 'Active');
GO

-- Dữ liệu cho bảng san_pham_chi_tiet
INSERT INTO san_pham_chi_tiet (so_luong, don_gia, id_mau_sac, id_kich_thuoc, id_chat_lieu, id_kieu_dang, trang_thai)
VALUES
(10, 250000, 1, 1, 1, 1, 'Active'),
(20, 500000, 2, 2, 2, 2, 'Active'),
(30, 1000000, 3, 3, 3, 3, 'Inactive');

-- Dữ liệu cho bảng hoa_don_chi_tiet
INSERT INTO hoa_don_chi_tiet (id_hoa_don, id_san_pham_chi_tiet, so_luong, don_gia)
VALUES
(1, 2, 2, 250000),
(2, 3, 1, 500000),
(3, 4, 3, 1000000);

-- Dữ liệu cho bảng phieu_giam_gia
INSERT INTO phieu_giam_gia (ma, ten, mo_ta, phan_tram_giam, giam_toi_da, ngay_bat_dau, ngay_ket_thuc, trang_thai)
VALUES
('PGG001', 'Summer Sale', 'Discount on summer products', 10, 500000, '2024-08-01', '2024-08-10', 'Active'),
('PGG002', 'Winter Sale', 'Discount on winter products', 20, 1000000, '2024-09-01', '2024-09-10', 'Active');

select * from hoa_don
select * from san_pham_chi_tiet
select *from hoa_don_chi_tiet