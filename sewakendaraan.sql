-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 04, 2020 at 09:49 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.5

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sewakendaraan`
--

-- --------------------------------------------------------

--
-- Table structure for table `penyewa`
--

CREATE TABLE `penyewa` (
  `nama` varchar(128) NOT NULL,
  `noKtp` char(20) NOT NULL,
  `alamat` varchar(255) NOT NULL,
  `noHp` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `penyewa`
--

INSERT INTO `penyewa` (`nama`, `noKtp`, `alamat`, `noHp`) VALUES
('Miqdad', '24873483743', 'Bandung', '08982376482'),
('Baihaqi Suhaeri', '3284738743', 'jakarta', '083898017587'),
('Baihaqi Suhaeri', '38473843', 'jakarta', '0838398017587'),
('Amar', '384738473', 'Lenteng', '08787865453'),
('Baihaqi Suhaeri', '834738743', 'Jakarta', '083898017587'),
('Baihaqi Suhaeri', '8374834793', 'Jakarta', '08398017587');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `kodeTf` varchar(128) NOT NULL,
  `nama` varchar(128) NOT NULL,
  `noKtp` char(20) NOT NULL,
  `namaKendaraan` varchar(128) NOT NULL,
  `durasiSewa` int(20) NOT NULL,
  `hargaSewa` int(20) NOT NULL,
  `tanggalSewa` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`kodeTf`, `nama`, `noKtp`, `namaKendaraan`, `durasiSewa`, `hargaSewa`, `tanggalSewa`) VALUES
('24873376482', 'Miqdad', '24873483743', 'Mobil Avanza', 2, 1000000, '2020-10-10'),
('3847865453', 'Amar', '384738473', 'Motor Beat', 4, 680000, '2020-12-21'),
('83748017587', 'Baihaqi Suhaeri', '8374834793', 'Mobil Avanza', 3, 1500000, '2020-08-02');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `penyewa`
--
ALTER TABLE `penyewa`
  ADD PRIMARY KEY (`noKtp`);

--
-- Indexes for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD PRIMARY KEY (`kodeTf`),
  ADD KEY `noKtp` (`noKtp`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `transaksi`
--
ALTER TABLE `transaksi`
  ADD CONSTRAINT `transaksi_ibfk_1` FOREIGN KEY (`noKtp`) REFERENCES `penyewa` (`noKtp`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
