CREATE DATABASE QL_KARAOKE_NICE
USE QL_KARAOKE_NICE

--use master
--DROP DATABASE QL_KARAOKE_NICE
/*
1. Tạo KH
2. DichVU_NuocUong
3. DichVu_MonAn
4. LoaiPhong
5. Phong
6. TaiKhoan
7. NhanVien
8. HoaDon
9.ChiTietDichVu
10. ChiTietHoaDon
*/

CREATE TABLE KhachHang
(
	maKhachHang NVARCHAR(20) PRIMARY KEY not null,
	tenKhachHang NVARCHAR(50) not null,
	soCMND NVARCHAR(20) not null,
	soDT NVARCHAR(20) not null
)
--DROP TABLE KhachHang

CREATE TABLE SanPham
(
	maSanPham NVARCHAR(20) PRIMARY KEY not null,
	tenSanPham  NVARCHAR(50) not null,
	loaiSanPham NVARCHAR(50) not null,
	donGia MONEY not null
)
--DROP TABLE SanPham

CREATE TABLE LoaiPhong
(
	maLoaiPhong NVARCHAR(20) not null PRIMARY KEY,
	tenLoaiPhong  NVARCHAR(20) not null,
	soCho INT not null,
	donGia MONEY not null
	)
--DROP TABLE  LoaiPhong

CREATE TABLE Phong
(
	maPhong NVARCHAR(20) PRIMARY KEY not null ,
	maLoaiPhong NVARCHAR(20) not null FOREIGN KEY(maLoaiPhong) REFERENCES LoaiPhong(maLoaiPhong),
	tenPhong  NVARCHAR(20) not null,
	trangThai NVARCHAR(20) not null,
	lau INT not null,
	)
--DROP TABLE Phong

CREATE TABLE NhanVien
(
	maNhanVien NVARCHAR(20) not null PRIMARY KEY ,	
	tenNhanVien NVARCHAR(50) not null , 
	diaChi NVARCHAR(50) not null,
	soDT NVARCHAR(12) not null,
	chucVu NVARCHAR(50) not null ,
	gioiTinh NVARCHAR(5) not null,
	mucLuong MONEY not null
	)
--DROP TABLE NhanVien

CREATE TABLE TaiKhoan
(
	tenDangNhap NVARCHAR(20) not null PRIMARY KEY REFERENCES NhanVien(maNhanVien),
	matKhau NVARCHAR(50)not null
	)
--DROP TABLE TaiKhoan

CREATE TABLE HoaDon
(
	maHoaDon NVARCHAR(20) not null PRIMARY KEY,
	maKhachHang NVARCHAR(20) not null FOREIGN KEY(MaKhachHang) REFERENCES KhachHang(maKhachHang),
	maNhanVien NVARCHAR(20) not null FOREIGN KEY(MaNhanVien) REFERENCES NhanVien(maNhanVien),
	gioBatDau DATETIME not null,
	gioKetThuc DATETIME not null,
	trangThai NVARCHAR(20) not null,
	thanhTien MONEY not null
	)
--DROP TABLE HoaDon

CREATE TABLE ChiTietHoaDon
(
	maHoaDon NVARCHAR(20) not null FOREIGN KEY(maHoaDon) REFERENCES HoaDon(maHoaDon),
	maPhong NVARCHAR(20) not null FOREIGN KEY(maPhong) REFERENCES Phong(maPhong),
	soLuong INT not null
	)
--DROP TABLE ChiTietHoaDon
CREATE TABLE ChiTietSanPham
(
	maHoaDon NVARCHAR(20) not null FOREIGN KEY(maHoaDon) REFERENCES HoaDon(maHoaDon),
	maSanPham NVARCHAR(20) not null FOREIGN KEY(maSanPham) REFERENCES sanPham(maSanPham),
	soLuong INT not null
	)
--DROP TABLE ChiTietSanPham