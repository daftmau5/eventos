Create Database eventos;

use eventos;

Create Table tbUsuario(
	idUsuario Int Not Null Auto_Increment,
	login varchar(20) Not Null,
	senha varchar(20) Not Null,
	cpf int Not Null,
	email varchar(40) Not Null,
	endereco varchar (60) Not Null,
	telefone varchar (20) Not Null,
	Primary Key (idUsuario)
);

Create Table tbFuncao(
	idFuncao Int Not Null Auto_Increment,
	organizador boolean default 0,
	cliente boolean default 0,
	adm boolean default 0,
	idUsuario int,
	Primary Key (idFuncao),
	Foreign Key(idUsuario) References tbUsuario(idUsuario)
);

Create Table tbAtracao(
	idAtracao Int Not Null Auto_Increment,
	nome varchar(30) Not Null,
	descricao varchar(50),
	Primary Key (idAtracao)
);

Create Table tbTema(
	idTema Int Not Null Auto_Increment,
	descricao varchar(50) Not Null,
	Primary Key (idTema)
);

Create Table tbLocal(
	idLocal Int Not Null Auto_Increment,
	nome varchar(30) Not Null,
	telefone char(20) Not Null,
	capacidade int Not Null,
	areaFumante boolean default 0,
	endereco varchar(50) Not Null,
	vip boolean default 0,
	Primary Key (idLocal)
);

Create Table tbAvaliacao(
	idAvaliacao Int Not Null Auto_Increment,
	nota int Not Null,
	descricao varchar(50),
	idLocal Int,
	Primary Key (idAvaliacao),
	Foreign Key (idLocal) References tbLocal(Idlocal)
);

Create Table tbEvento(
	idEvento Int Not Null Auto_Increment,
	idUsuario Int,
	idTema Int,
	idAtracao Int,
	dataEvento Date,
	descricao varchar(50),
	preco Double,
	Primary Key (idEvento),
	Foreign Key (idUsuario) References tbUsuario(idUsuario),
	Foreign Key (idTema) References tbTema(idTema),
	Foreign Key (idAtracao) References tbAtracao(idAtracao)
);

Create Table tbPagamento(
	idPagamento Int Not Null Auto_Increment,
	tipoPagamento varchar(10) Not Null,
	Primary Key (idPagamento)	
);

Create Table tbReserva(
	idReserva Int Not Null Auto_Increment,
	dataReserva Date,
	idPagamento Int,
	idEvento Int,
	idUsuario Int,
	status varchar(15),
	Primary Key (idReserva),
	Foreign Key (idPagamento) references tbPagamento(idPagamento),
	Foreign Key (idEvento) references tbEvento(idEvento),
	Foreign Key (idUsuario) references tbUsuario(idUsuario)
);