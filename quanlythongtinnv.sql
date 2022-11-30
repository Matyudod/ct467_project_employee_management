-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 30, 2022 lúc 09:25 AM
-- Phiên bản máy phục vụ: 10.4.22-MariaDB
-- Phiên bản PHP: 8.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `quanlythongtinnv`
--
CREATE DATABASE IF NOT EXISTS `quanlythongtinnv` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `quanlythongtinnv`;

DELIMITER $$
--
-- Thủ tục
--
DROP PROCEDURE IF EXISTS `SuaChucVu`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SuaChucVu` (`p_maChucVu` INT, `p_tenChucVu` CHAR(50))  begin
	UPDATE chucvu SET maChucVu=p_maChucVu,  tenChucVu = p_tenChucVu WHERE chucvu.maChucVu = p_maChucVu;
end$$

DROP PROCEDURE IF EXISTS `suaNhanVien`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `suaNhanVien` (`p_maNhanVien` INT, `p_maChucVu` INT, `p_maPhongBan` INT, `p_hoTen` VARCHAR(50), `p_diaChi` VARCHAR(255), `p_sdt` CHAR(10))  begin
	UPDATE nhanvien SET maNhanVien=p_maNhanVien, maChucVu=p_maChucVu,  maPhongBan = p_maPhongBan, hoTen = p_hoTen, diaChi = p_diaChi, sdt = p_sdt WHERE nhanvien.maNhanVien = p_maNhanVien;
end$$

DROP PROCEDURE IF EXISTS `SuaPhongBan`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `SuaPhongBan` (`p_maPhongBan` INT, `p_tenPhongBan` VARCHAR(50))  begin
	UPDATE phongban SET maPhongBan=p_maPhongBan,  tenPhongBan = p_tenPhongBan WHERE maPhongBan = p_maPhongBan;
end$$

DROP PROCEDURE IF EXISTS `ThemChucVu`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ThemChucVu` (`sp_tenChucVu` CHAR(50))  BEGIN
	INSERT INTO chucvu VALUES (NULL, sp_tenChucVu);
end$$

DROP PROCEDURE IF EXISTS `themNhanVien`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `themNhanVien` (`fn_maChucVu` INT, `fn_maPhongBan` INT, `fn_hoTen` VARCHAR(50), `diaChi` VARCHAR(255), `sdt` CHAR(10))  BEGIN
	INSERT INTO nhanvien VALUES (NULL, fn_maChucVu, fn_maPhongBan, fn_hoTen, diaChi,sdt);
end$$

DROP PROCEDURE IF EXISTS `ThemPhongBan`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `ThemPhongBan` (`sp_tenPhongBan` VARCHAR(50))  BEGIN
	INSERT INTO phongban VALUES (NULL, sp_tenPhongBan);
end$$

DROP PROCEDURE IF EXISTS `timNhanVienTheoMaChucVu`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `timNhanVienTheoMaChucVu` (`fn_maChucVu` INT)  begin
	select * from nhanvien where maChucVu = fn_maChucVu;
end$$

DROP PROCEDURE IF EXISTS `timNhanVienTheoMaNV`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `timNhanVienTheoMaNV` (`fn_maNhanVien` INT)  begin
	select * from nhanvien where maNhanVien = fn_maNhanVien;
end$$

DROP PROCEDURE IF EXISTS `timNhanVienTheoTenNV`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `timNhanVienTheoTenNV` (`fn_tenNhanVien` VARCHAR(50))  begin
	select * from nhanvien where hoTen like concat('%', fn_tenNhanVien, '%');
end$$

DROP PROCEDURE IF EXISTS `XoaChucVu`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `XoaChucVu` (`p_maChucVu` INT)  begin
	delete from chucvu where maChucVu = p_maChucVu;
end$$

DROP PROCEDURE IF EXISTS `xoaNhanVien`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `xoaNhanVien` (`p_maNhanVien` INT)  begin
	delete from nhanvien where maNhanVien= p_maNhanVien;
end$$

DROP PROCEDURE IF EXISTS `XoaPhongBan`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `XoaPhongBan` (`p_maPhongBan` INT)  begin
	delete from phongban where maPhongBan = p_maPhongBan;
end$$

--
-- Các hàm
--
DROP FUNCTION IF EXISTS `coChucVu`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `coChucVu` (`fn_maChucVu` INT) RETURNS TINYINT(1) BEGIN
	declare tmp int default 0;
	SELECT COUNT(*) INTO tmp from chucvu WHERE `maChucVu`=fn_maChucVu;
    if tmp>0 then RETURN true;
    else RETURN false;
    end if;
end$$

DROP FUNCTION IF EXISTS `coPhongBan`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `coPhongBan` (`fn_maPhongBan` INT) RETURNS TINYINT(1) BEGIN
	declare tmp int default 0;
	SELECT COUNT(*) INTO tmp from phongban WHERE `maPhongBan`=fn_maPhongBan;
    if tmp>0 then RETURN true;
    else RETURN false;
    end if;
end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chucvu`
--

DROP TABLE IF EXISTS `chucvu`;
CREATE TABLE IF NOT EXISTS `chucvu` (
  `maChucVu` int(11) NOT NULL AUTO_INCREMENT,
  `tenChucVu` char(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`maChucVu`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `chucvu`
--

INSERT INTO `chucvu` (`maChucVu`, `tenChucVu`) VALUES
(1, 'Giám đốc'),
(2, 'Thư kí'),
(3, 'Nhân viên'),
(4, 'Bảo vệ'),
(5, 'Lao công'),
(6, 'Phó giám đốc'),
(7, 'Trưởng phòng'),
(9, 'kế toán');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
CREATE TABLE IF NOT EXISTS `nhanvien` (
  `maNhanVien` int(11) NOT NULL AUTO_INCREMENT,
  `maChucVu` int(11) NOT NULL,
  `maPhongBan` int(11) NOT NULL,
  `hoTen` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `diaChi` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `sdt` char(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`maNhanVien`),
  KEY `maChucVu` (`maChucVu`),
  KEY `maPhongBan` (`maPhongBan`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `nhanvien`
--

INSERT INTO `nhanvien` (`maNhanVien`, `maChucVu`, `maPhongBan`, `hoTen`, `diaChi`, `sdt`) VALUES
(1, 1, 2, 'Ông Tú Khanh', '132/14', '2346899'),
(3, 3, 2, 'khenh', '23ed', '23456'),
(4, 6, 5, 'Đỗ Duy Tâm', '51 3/2', '887847939'),
(7, 3, 2, 'khuhuh', '2đw', 'g22777'),
(9, 4, 5, 'Duy Tâm', '51/342', '234577');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phongban`
--

DROP TABLE IF EXISTS `phongban`;
CREATE TABLE IF NOT EXISTS `phongban` (
  `maPhongBan` int(11) NOT NULL AUTO_INCREMENT,
  `tenPhongBan` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`maPhongBan`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `phongban`
--

INSERT INTO `phongban` (`maPhongBan`, `tenPhongBan`) VALUES
(1, 'Nhân sự'),
(2, 'Kế toán'),
(3, 'Hành chính'),
(4, 'Marketing'),
(5, 'Hội đòng quản trị');

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD CONSTRAINT `nhanvien_ibfk_1` FOREIGN KEY (`maChucVu`) REFERENCES `chucvu` (`maChucVu`),
  ADD CONSTRAINT `nhanvien_ibfk_2` FOREIGN KEY (`maPhongBan`) REFERENCES `phongban` (`maPhongBan`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
