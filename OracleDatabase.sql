Create Table tbUsuario(
  idUsuario int,
  login char(20),
  senha char(20),
  cpf int,
  email char(40),
  endereco char(50),
  telefone char(20)
);

Create Table tbFuncao(
  idFuncao int,
  organizador char(1),
  cliente char(1),
  adm char(1),
  idUsuario int
);

Create Table tbAtracao(
  idAtracao int,
  nome char(30),
  descricao char(50)  
);

Create Table tbTema(
  idTema int,
  descricao char(50)
);

Create Table tbLocal(
  idLocal int,
  nome char(30),
  telefone char(20),
  capacidade int,
  areaFumante number(1) check(areaFumante in (1,0)),
  endereco char(50),
  vip number(1) check(vip in (1,0))
);

Create Table tbAvaliacao(
  idAvaliacao int,
  nota int,
  descricao char(50),
  idLocal int
);

Create Table tbEvento(
  idEvento int,
  idReserva int,
  idUsuario int,
  idTema int,
  idAtracao int,
  dataEvento date,
  descricao char(50),
  preco number(3,2)
  );
  
  Create Table tbPagamento(
    idPagamento int,
    tipoPagamento char(10)
  );
  
  Create Table tbReserva(
    idReserva int,
    dataReserva date,
    idPagamento int,
    idEvento int,
    idUsuario int,
    status char(15)
);

Create Sequence seq_tbUsuario;
Create Sequence seq_tbFuncao;
Create Sequence seq_tbAtracao;
Create Sequence seq_tbTema;
Create Sequence seq_tbLocal;
Create Sequence seq_tbAvaliacao;
Create Sequence seq_tbEvento;
Create Sequence seq_tbPagamento;
Create Sequence seq_tbReserva;

Alter Table tbUsuario Add Constraints pk_idUsuario Primary Key(idUsuario);
Alter Table tbFuncao Add Constraints pk_idFuncao Primary Key(idFuncao);
Alter Table tbAtracao Add Constraints pk_idAtracao Primary Key(idAtracao);
Alter Table tbTema Add Constraints pk_idTema Primary Key(idTema);
Alter Table tbLocal Add Constraints pk_idLocal Primary Key(idLocal);
Alter Table tbAvaliacao Add Constraints pk_idAvaliacao Primary Key(idAvaliacao);
Alter Table tbEvento Add Constraints pk_idEvento Primary Key(idEvento);
Alter Table tbPagamento Add Constraints pk_idPagamento Primary Key(idPagamento);
Alter Table tbReserva Add Constraints pk_idREserva Primary Key(idReserva);


ALTER TABLE tbFuncao ADD FOREIGN KEY (idUsuario) REFERENCES tbUsuario;
ALTER TABLE tbAvaliacao ADD FOREIGN KEY (idLocal) REFERENCES tbLocal;
ALTER TABLE tbReserva ADD FOREIGN KEY (idUsuario) REFERENCES tbUsuario;
ALTER TABLE tbReserva ADD FOREIGN KEY (idEvento) REFERENCES tbEvento;
ALTER TABLE tbEvento ADD FOREIGN KEY (idReserva) REFERENCES tbReserva;
ALTER TABLE tbEvento ADD FOREIGN KEY (idUsuario) REFERENCES tbUsuario;
ALTER TABLE tbEvento ADD FOREIGN KEY (idTema) REFERENCES tbTema;
ALTER TABLE tbEvento ADD FOREIGN KEY (idAtracao) REFERENCES tbAtracao
