create database produto;
use produto;

CREATE TABLE `Produto` (
  `id` int not null auto_increment,
  `codigo` int not null unique,
  `nome` varchar (50) not null,
  `categoria` varchar (50),
  `valor` float(10.2) not null,
  `quantidade` int not null,
  PRIMARY KEY (`id`)
);