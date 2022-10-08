-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 09, 2022 at 01:42 AM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `quanlythongtinnv`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `SuaChucVu` (IN `pMaChucVu` INT, IN `pTenChucVu` VARCHAR(50))  UPDATE chucvu SET `tenChucVu` = pTenChucVu WHERE `maChucVu` = pMaChucVu$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `SuaPhongBan` (IN `pMaPhongBan` INT, IN `pTenPhongBan` VARCHAR(50))  UPDATE phongban SET `tenPhongBan` = pTenPhongBan WHERE `maPhongBan` = pMaPhongBan$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ThemChucVu` (IN `pTenChucVu` VARCHAR(50))  INSERT INTO chucvu(`tenChucVu`) VALUES (pTenChucVu)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ThemPhongBan` (IN `pTenPhongBan` VARCHAR(50))  INSERT INTO phongban(`tenPhongBan`) VALUES (pTenPhongBan)$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `XoaChucVu` (IN `pMaChucVu` INT)  DELETE FROM chucvu WHERE `maChucVu` = pMaChucVu$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `XoaPhongBan` (IN `pMaPhongBan` INT)  DELETE FROM phongban WHERE `maPhongBan` = pMaPhongBan$$

--
-- Functions
--
CREATE DEFINER=`root`@`localhost` FUNCTION `coPhongBan` (`pMaPhongBan` INT) RETURNS TINYINT(1) BEGIN
	DECLARE countPhongBan INT DEFAULT 0;
    SELECT COUNT(*) into countPhongBan FROM phongban WHERE maPhongBan = pMaPhongBan;
    IF countPhongBan > 0 THEN
    	RETURN true;
    ELSE 
    	RETURN false;
	END IF;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `chucvu`
--

CREATE TABLE `chucvu` (
  `maChucVu` int(11) NOT NULL,
  `tenChucVu` char(50) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `chucvu`
--

INSERT INTO `chucvu` (`maChucVu`, `tenChucVu`) VALUES
(1, 'Giám đốc'),
(2, 'Thư kí'),
(3, 'Nhân viên'),
(4, 'Bảo vệ'),
(5, 'Lao công'),
(6, 'Phó giám đốc'),
(7, 'Trưởng phòng'),
(8, 'Phó phòng');

-- --------------------------------------------------------

--
-- Table structure for table `nhanvien`
--

CREATE TABLE `nhanvien` (
  `maNhanVien` int(11) NOT NULL,
  `maChucVu` int(11) NOT NULL,
  `maPhongBan` int(11) NOT NULL,
  `hoTen` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `diaChi` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `sdt` char(10) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `nhanvien`
--

INSERT INTO `nhanvien` (`maNhanVien`, `maChucVu`, `maPhongBan`, `hoTen`, `diaChi`, `sdt`) VALUES
(1, 1, 5, 'Ông Tú Khanh', '132/14', '09678334'),
(2, 6, 5, 'Đỗ Duy Tâm', '51 3/2', '8989843879'),
(3, 1, 5, 'Ông Tú Khanh', '132/14', '09678334'),
(4, 6, 5, 'Đỗ Duy Tâm', '51 3/2', '887847939');

-- --------------------------------------------------------

--
-- Table structure for table `phongban`
--

CREATE TABLE `phongban` (
  `maPhongBan` int(11) NOT NULL,
  `tenPhongBan` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `phongban`
--

INSERT INTO `phongban` (`maPhongBan`, `tenPhongBan`) VALUES
(1, 'Nhân sự'),
(2, 'Kế toán'),
(3, 'Hành chính'),
(4, 'Marketing'),
(5, 'Ban giám đốc'),
(6, 'Kế hoạch tổng hợp');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chucvu`
--
ALTER TABLE `chucvu`
  ADD PRIMARY KEY (`maChucVu`);

--
-- Indexes for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD PRIMARY KEY (`maNhanVien`),
  ADD KEY `maChucVu` (`maChucVu`),
  ADD KEY `maPhongBan` (`maPhongBan`);

--
-- Indexes for table `phongban`
--
ALTER TABLE `phongban`
  ADD PRIMARY KEY (`maPhongBan`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `chucvu`
--
ALTER TABLE `chucvu`
  MODIFY `maChucVu` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `nhanvien`
--
ALTER TABLE `nhanvien`
  MODIFY `maNhanVien` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `phongban`
--
ALTER TABLE `phongban`
  MODIFY `maPhongBan` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `nhanvien`
--
ALTER TABLE `nhanvien`
  ADD CONSTRAINT `nhanvien_ibfk_1` FOREIGN KEY (`maChucVu`) REFERENCES `chucvu` (`maChucVu`),
  ADD CONSTRAINT `nhanvien_ibfk_2` FOREIGN KEY (`maPhongBan`) REFERENCES `phongban` (`maPhongBan`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
