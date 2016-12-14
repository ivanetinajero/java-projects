-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Dec 14, 2016 at 02:43 PM
-- Server version: 5.5.40-0ubuntu0.14.04.1
-- PHP Version: 7.0.10-2+deb.sury.org~trusty+1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sistema-bascula`
--

-- --------------------------------------------------------

--
-- Table structure for table `Categoria`
--

CREATE TABLE `Categoria` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(250) NOT NULL,
  `imagen` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Categoria`
--

INSERT INTO `Categoria` (`id`, `nombre`, `descripcion`, `imagen`) VALUES
(1, 'Carnes', 'Todo tipo de Carnes', ''),
(2, 'Frutas', 'Diferentes frutas', ''),
(3, 'Verduras', 'Verduras', ''),
(4, 'Lacteos', 'Lacteos', '');

-- --------------------------------------------------------

--
-- Table structure for table `Producto`
--

CREATE TABLE `Producto` (
  `id` int(11) NOT NULL,
  `descripcion` varchar(250) NOT NULL,
  `precio` double NOT NULL,
  `imagen` varchar(100) NOT NULL,
  `idCategoria` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Producto`
--

INSERT INTO `Producto` (`id`, `descripcion`, `precio`, `imagen`, `idCategoria`) VALUES
(1, 'Bistec de diezmillo de res', 40, '', 1),
(2, 'Chuleta de res', 20, '', 1),
(3, 'Filete de res', 9, '', 1),
(4, 'Hígado de res', 31, '', 1),
(5, 'Milanesa de res', 23, '', 1),
(6, 'Panza de res', 45, '', 1),
(7, 'Res', 44, '', 1),
(8, 'Retazo hueso de res', 29, '', 1),
(9, 'Ternera', 24, '', 1),
(10, 'Cabeza de cerdo', 40, '', 1),
(11, 'Carnes molida de cerdo', 12, '', 1),
(12, 'Carnes molida de res', 11, '', 1),
(13, 'Cerdo', 28, '', 1),
(14, 'Chuleta ahumada de cerdo', 9, '', 1),
(15, 'Espinazo de cerdo', 6, '', 1),
(16, 'Lomo de cerdo', 65, '', 1),
(17, 'Milanesa de cerdo', 35, '', 1),
(18, 'Pata de cerdo', 11, '', 1),
(19, 'Pierna ce cerdo entera/ pernil', 4, '', 1),
(20, 'Pavo', 20, '', 1),
(21, 'Pechuga de pollo', 4, '', 1),
(22, 'Pierna de pollo', 8, '', 1),
(23, 'Pollo', 45, '', 1),
(24, 'Pollo entero', 4, '', 1),
(25, 'Guayaba', 21, '', 2),
(26, 'Jamaica', 38, '', 2),
(27, 'Mamey', 9, '', 2),
(28, 'Mandarina', 10, '', 2),
(29, 'Mango', 15, '', 2),
(30, 'Manzana', 54, '', 2),
(31, 'Melón', 29, '', 2),
(32, 'Naranja', 6, '', 2),
(33, 'Papaya', 22, '', 2),
(34, 'Pera', 8, '', 2),
(35, 'Piña', 46, '', 2),
(36, 'Plátano', 25, '', 2),
(37, 'Acelga', 4, '', 3),
(38, 'Aguacate', 42, '', 3),
(39, 'Brócoli', 39, '', 3),
(40, 'Calabaza', 15, '', 3),
(41, 'Cebolla', 34, '', 3),
(42, 'Chayote', 14, '', 3),
(43, 'Chícharos', 51, '', 3),
(44, 'Chile fresco', 12, '', 3),
(45, 'Chiles', 53, '', 3),
(46, 'Chiles gueros', 14, '', 3),
(47, 'Col', 48, '', 3),
(48, 'Coliflor', 18, '', 3),
(49, 'Ejote', 19, '', 3),
(50, 'Espinacas', 52, '', 3),
(51, 'Jitomate', 18, '', 3),
(52, 'Lechuga', 7, '', 3),
(53, 'Nopal', 9, '', 3),
(54, 'Pepino', 10, '', 3),
(55, 'Rábano', 19, '', 3),
(56, 'Huevo', 12, '', 4),
(57, 'Crema', 12, '', 4),
(58, 'Crema batida', 35, '', 4),
(59, 'Mantequilla', 19, '', 4),
(60, 'Queso canasto', 32, '', 4),
(61, 'Queso chihuahua', 13, '', 4),
(62, 'Queso cotija', 14, '', 4),
(63, 'Queso doble crema', 38, '', 4),
(64, 'Queso manchego', 30, '', 4),
(65, 'Queso panela', 46, '', 4),
(66, 'Queso sierra', 22, '', 4),
(67, 'Yoghurt', 43, '', 4);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Categoria`
--
ALTER TABLE `Categoria`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `Producto`
--
ALTER TABLE `Producto`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_ProCat` (`idCategoria`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Categoria`
--
ALTER TABLE `Categoria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `Producto`
--
ALTER TABLE `Producto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=68;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `Producto`
--
ALTER TABLE `Producto`
  ADD CONSTRAINT `fk_ProCat` FOREIGN KEY (`idCategoria`) REFERENCES `Categoria` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
