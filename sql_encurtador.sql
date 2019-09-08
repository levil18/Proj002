-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 08-Set-2019 às 22:46
-- Versão do servidor: 5.7.27-log
-- versão do PHP: 7.2.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

use `encurtador`;
--
-- Banco de dados: `encurtador`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `urls`
--

CREATE TABLE `urls` (
  `hash` varchar(16) NOT NULL,
  `original` varchar(512) NOT NULL,
  `usuario` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `urls`
--

INSERT INTO `urls` (`hash`, `original`, `usuario`) VALUES
('3tpg6', 'google.com.br', 1),
('40R8xR', 'www.programmergate.com', 1),
('tZmAHL', 'br.godaddy.com', 1),
('UyYOpo', 'www.w3schools.com', 1),
('V5nEOC', 'coderanch.com/t', 1),
('wKoB5t', 'kodejava.org', 1),
('wPyfmt', 'channel9.msdn.com', 1),
('zpnns8a', 'www.primevideo.com', 1);

-- --------------------------------------------------------

--
-- Estrutura da tabela `usuarios`
--

CREATE TABLE `usuarios` (
  `Id_Usr` int(10) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `urls`
--
ALTER TABLE `urls`
  ADD PRIMARY KEY (`hash`);

--
-- Índices para tabela `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`Id_Usr`);
COMMIT;
