<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="br.com.eventos.model.Evento, br.com.eventos.model.Atracao, br.com.eventos.model.Tema, br.com.eventos.model.Local, java.util.List, java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
<title>E-Vents</title>
<!-- Bootstrap core CSS -->
<link href="front/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- Custom styles for this template -->
<link href="front/css/simple-sidebar.css" rel="stylesheet">

<script>


function efetua_pagamento( id ) {
	if (confirm("Deseja realizar uma reserva para esse evento ? ")) {
		$('#formReserva').empty();
		$('#formReserva').append('<input type="hidden" name="idEvento" value="' + id + '"/>');
		$('#formReserva').append('<input type="hidden" name="cmd" value="efetua_pagamento"/>');
		$('#formReserva').submit();
	}
}

</script>


</head>
<body>

	<%
		Evento eventoAtual = (Evento) session.getAttribute("EVENTO_ATUAL");
		if (eventoAtual == null) {
			eventoAtual = new Evento();
		} else {
			session.setAttribute("EVENTO_ATUAL", null);
		}
	%>
	<%
		String msg = (String) session.getAttribute("MENSAGEM");

		if (msg != null) {
			session.setAttribute("MENSAGEM", null);
	%>
	<h3 class="alert alert-danger"><%=msg%></h3>
	<%
		}
	%>

	<div id="wrapper">

		<!-- Sidebar -->
		<div id="sidebar-wrapper">
			<ul class="sidebar-nav">
				<li class="sidebar-brand">
					<h2 href="#" style="color: #6495ED;">E-Vents</h2>
				</li>
				<li>
					<h4 style="color: white;">Evento</h4>
					<ul>
						<a href="./ControllerPesquisaEvento">Buscar</a>
						<a href="./ControllerCadastroEvento">Cadastrar</a>
						<a href="#">Reservas</a>
					</ul>
				</li>
				<li>
					<h4 style="color: white;">Local</h4>
					<ul>
						<a href="#">Buscar</a>
						<a href="./cadastro_local.jsp">Cadastrar</a>
					</ul>
				</li>
				<li>
					<h4 style="color: white;">Atração</h4>
					<ul>
						<a href="#">Buscar</a>
						<a href="./cadastro_atracao.jsp">Cadastrar</a>
					</ul>
				</li>
				<li>
					<h4 style="color: white;">Tema</h4>
					<ul>
						<a href="#">Buscar</a>
						<a href="./cadastro_tema.jsp">Cadastrar</a>
					</ul>
				</li>
			</ul>
		</div>

		<!-- Page Content -->
		<a href="#menu-toggle" class="btn btn-secondary" id="menu-toggle">Menu</a>
		<div id="page-content-wrapper">
				<div class="container-fluid">
					<h1>Detalhes de Eventos</h1>
					<h2>
						Descrição: <span class="label label-info"><%=eventoAtual.getDescricao()%></span>
					</h2>
					<h2>
						Tema: <span class="label label-info"><%=eventoAtual.getTema().getDescricao()%></span>
					</h2>
					<h2>
						Atração: <span class="label label-info"><%=eventoAtual.getAtracao().getNome()%></span>
						<span class="label label-info"><%=eventoAtual.getAtracao().getDescricao()%></span>
					</h2>
					<h2>
						Local: <span class="label label-info"><%=eventoAtual.getLocal().getNome()%></span>
						<span class="label label-info"><%=eventoAtual.getLocal().getEndereco()%></span>
						<span class="label label-info"><%=eventoAtual.getLocal().getCapacidade()%>
							pessoas</span>
					</h2>
					<h2>
						Telefone: <span class="label label-info"><%=eventoAtual.getLocal().getTelefone()%></span>
					</h2>
					<h2>
						Data: <span class="label label-info"><%=eventoAtual.getData()%></span>
					</h2>
					<h2>
						Preço: <span class="label label-info">R$ <%=eventoAtual.getPreco()%></span>
					</h2>

					<div class="form-group">
						<button type="button" class="btn btn-primary" data-toggle="modal"
							data-target="#exampleModal">Quero Participar!</button>
						<button type="submit" class="btn btn-primary" name="cmd"
							value="voltar">Voltar</button>
					</div>
				</div>

		</div>
		<!-- /#page-content-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<form id="formReserva" action="./ControllerReservaEvento"
				method="post">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h2 class="modal-title" id="exampleModalLabel">Pagamento</h2>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body">
					<h2>
						Cód. Evento: <span class="label label-info"><%=eventoAtual.getIdEvento()%></span>
					</h2>
					<h2>
						Descrição: <span class="label label-info"><%=eventoAtual.getDescricao()%></span>
					</h2>
					<h2>
						Valor: <span class="label label-info"><%=eventoAtual.getPreco()%></span>
					</h2>
				
					<select class="form-control form-control-lg" name="formaPagamento">
						<option value='Crédito'>Crédito</option>
						<option value="Débito">Débito</option>
						<option value="Boleto">Boleto</option>
					</select>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Cancelar</button>
					<button type="button" class="btn btn-primary" onclick="efetua_pagamento(<%=eventoAtual.getIdEvento()%>);">Efetuar Pagamento</button>
				</div>
			</div>
		</div>
	</form>
		
	</div>

	<!-- Bootstrap core JavaScript -->
	<script src="front/vendor/jquery/jquery.min.js"></script>
	<script src="front/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Menu Toggle Script -->
	<script>
		$("#menu-toggle").click(function(e) {
			e.preventDefault();
			$("#wrapper").toggleClass("toggled");
		});
	</script>

</body>
</html>