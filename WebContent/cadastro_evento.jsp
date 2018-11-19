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


</head>
<body>

	<form action="./ControllerCadastroEvento" method="get">

		<%
			List<Tema> listaTema = (List<Tema>) session.getAttribute("LISTA_TEMA");
			List<Atracao> listaAtracao = (List<Atracao>) session.getAttribute("LISTA_ATRACAO");
			List<Local> listaLocal = (List<Local>) session.getAttribute("LISTA_LOCAL");

			if (listaTema == null) {
				listaTema = new ArrayList<Tema>();
			} else {
				session.setAttribute("LISTA_TEMA", null);
			}

			if (listaAtracao == null) {
				listaAtracao = new ArrayList<Atracao>();
			} else {
				session.setAttribute("LISTA_ATRACAO", null);
			}

			if (listaLocal == null) {
				listaLocal = new ArrayList<Local>();
			} else {
				session.setAttribute("LISTA_LOCAL", null);
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
			<form id="formEvento" action="./ControllerCadastroEvento"
				method="post">
				<div class="container-fluid">
					<h1>Cadastro de Eventos</h1>
					<div class="form-group">
						<label for="nome">Descrição:</label> <input type="text"
							class="form-control" name="txtDescricaoEvento"
							placeholder="Digite a Descrição do Evento">
					</div>

					<div class="form-group">
						<label for="sel1">Escolha um Tema:</label> <select
							class="form-control form-control-lg" name="idTema">
							<%
								for (Tema t : listaTema) {
							%>
							<option value="<%=t.getIdTema()%>"><%=t.getDescricao()%></option>

							<%
								}
							%>
						</select>
					</div>

					<div class="form-group">
						<label for="data-pagamento">Data:</label>
						<div class="input-group date" data-date-format="dd.mm.yyyy">
							<input type="text" class="form-control" placeholder="dd.mm.yyyy"
								name="txtDataEvento">
							<div class="input-group-addon">
								<span class="glyphicon glyphicon-th"></span>
							</div>
						</div>
					</div>
					<script
						src='http://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.1/js/bootstrap-datepicker.min.js'></script>
					<script>
     				$('.input-group.date').datepicker({format: "dd.mm.yyyy"});
    			</script>

					<div class="form-group">
						<label for="sel1">Escolha uma Atração:</label> <select
							class="form-control form-control-lg" name="idAtracao">
							<%
								for (Atracao a : listaAtracao) {
							%>
							<option value="<%=a.getIdAtracao()%>"><%=a.getNome()%></option>

							<%
								}
							%>
						</select>
					</div>


					<div class="form-group">
						<label for="sel1">Escolha um Local:</label> <select
							class="form-control form-control-lg" name="idLocal">
							<%
								for (Local l : listaLocal) {
							%>
							<option value="<%=l.getIdLocal()%>"><%=l.getNome()%></option>

							<%
								}
							%>
						</select>
					</div>
					<div class="form-group">
						<label for="preco">Preço:</label> <input id="preco" type="text"
							class="preco form-control" name="txtPrecoEvento" placeholder="R$" style="display:inline-block">
					</div>

					<div class="form-group">
						<button type="submit" class="btn btn-primary" name="cmd"
							value="cadastrar">Salvar</button>
						<button type="submit" class="btn btn-primary" name="cmd"
							value="limpar">Limpar</button>
					</div>
				</div>

			</form>

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