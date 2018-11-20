<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="br.com.eventos.model.Reserva, br.com.eventos.model.Evento, br.com.eventos.model.Tema, br.com.eventos.model.Local, java.util.List, java.util.ArrayList"%>
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

</head>
<body>
	<form action="./ControllerReservaEvento" method="get">
		<%
			List<Reserva> listaReserva = (List<Reserva>) session.getAttribute("LISTA_RESERVA");

			if (listaReserva == null) {
				listaReserva = new ArrayList<Reserva>();
			} else {
				session.setAttribute("LISTA_RESERVA", null);
			}
		%>
	</form>
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
					<h1>Minhas Reservas</h1>

				<div class="container">
				
					<table class="table table-striped">
						<thead>
							<tr>
								<th>#</th>
								<th>Data Reserva</th>
								<th>Pagamento</th>
								<th>Status</th>
								<th>Evento</th>
							</tr>
						</thead>
						<tbody>
							<%
								if (listaReserva.size() > 0) {

									for (Reserva r : listaReserva) {
							%>
							<tr>
								<td><%=r.getIdReserva()%></td>
								<td><%=r.getData()%></td>
								<td><%=r.getModoPagamento()%></td>
								<td><%=r.getStatus()%></td>
								<td><%=r.getEvento().getDescricao()%></td>
							</tr>
					
							<%
								}
							%>
						</tbody>
					</table>
					
				</div>
				<%
					}
				%>

		</div>
		<!-- /#page-content-wrapper -->

	</div>
	<!-- /#wrapper -->

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