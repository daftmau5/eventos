<%@page import="br.com.eventos.dao.impl.DAOAtracao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="br.com.eventos.model.Evento,br.com.eventos.model.Atracao,br.com.eventos.model.Tema,br.com.eventos.model.Local,java.util.List,java.util.ArrayList"%>   
<!DOCTYPE html>
<html lang="pt">
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
		function remover( id ) {
			if (confirm("Remove atracao com id " + id)) {
				$('#formBuscaAtracao').empty();
				$('#formBuscaAtracao').append('<input type="hidden" name="txtId" value="' + id + '"/>');
				$('#formBuscaAtracao').append('<input type="hidden" name="cmd" value="remover"/>');
				$('#formBuscaAtracao').submit();
			}
		}
		function editar( id ) {
			$('#formBuscaAtracao').empty();
			$('#formBuscaAtracao').append('<input type="hidden" name="txtId" value="' + id + '"/>');
			$('#formBuscaAtracao').append('<input type="hidden" name="cmd" value="editar"/>');
			$('#formBuscaAtracao').submit();
		}		
	</script>

</head>

<body>

	<%
		DAOAtracao ad = new DAOAtracao();
			String msg = (String)session.getAttribute("MENSAGEM");
			List<Atracao> lista = ad.listar(); /*Alterar para o pesquisar funcionar*/
			
			if (lista == null) {
		lista = new ArrayList<Atracao>();
			}else{
		session.setAttribute("LISTA", null);
			}
			
			Atracao atracaoEdit = (Atracao)session.getAttribute("ATRACAO_EDITAR");
			   if (atracaoEdit == null) { 
		   atracaoEdit = new Atracao();
			   } else { 
		   session.setAttribute("ATRACAO_EDITAR", null);
			   }
			
			if (msg != null) {
			   session.setAttribute("MENSAGEM", null);
	%>
			<h3 class="alert alert-danger"><%=msg%></h3>
	<% } %>
	
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
						<a href="./LocalController">Cadastrar</a>
					</ul>
				</li>
				<li>
					<h4 style="color: white;">Atração</h4>
					<ul>
						<a href="./ControlerBuscaAtracao">Buscar</a>
						<a href="./ControlerCadAtracao">Cadastrar</a>
					</ul>
				</li>
				<li>
					<h4 style="color: white;">Tema</h4>
					<ul>
						<a href="#">Buscar</a>
						<a href="./TemaController">Cadastrar</a>
					</ul>
				</li>
			</ul>
		</div>
		<!-- /#sidebar-wrapper -->

		<!-- Page Content -->
		<a href="#menu-toggle" class="btn btn-secondary" id="menu-toggle">Menu</a>
		<div id="page-content-wrapper">
		
		<form id="formBuscaAtracao" action="./ControlerBuscaAtracao" method="post">
			<div class="container-fluid">
				<h1>Buscar Atração</h1>
				
				<div class="form-group">
					<label for="nome">ID:</label> 
					<input type="text" class="form-control" name="txtId" value="<%=atracaoEdit.getIdAtracao()%>" readonly/>
				</div>
				
				<div class="form-group">
					<label for="nome">Nome:</label> 
					<input type="text" class="form-control" name="txtNomeAtracao" placeholder="Digite o Nome" value="<%=atracaoEdit.getNome()%>"/>
				</div>
				
				<div class="form-group">
					<label for="nome">Descrição:</label> 
					<input type="text" class="form-control" name="txtDescricaoAtracao" placeholder="Digite a Descrição" value="<%=atracaoEdit.getDescricao()%>" />
				</div>
				
				<div class="form-group">
					<button type="submit" class="btn btn-primary" name="cmd" value="pesquisar">Pesquisar</button>
					<button type="submit" class="btn btn-primary" name="cmd" value="atualizar">Atualizar</button>
				</div>
			</div>
				
			<%if (lista.size() > 0) {%>
				<div class="container">
					<table class="table table-striped">
						<thead>
							<tr>
								<th>Id</th>
								<th>Nome</th>
								<th>Descrição</th>
								<th>Função</th>
							</tr>
						</thead>
					<tbody>
						<% for (Atracao a : lista) { %>
							<tr>
								<td><%= a.getIdAtracao() %> </td>
								<td><%= a.getNome() %> </td>
								<td><%= a.getDescricao() %> </td>
								<td>
									<div class="form-group">
										<button type="button" class="btn btn-primary" onclick="remover(<%=a.getIdAtracao() %>);">Remover</button>
										<button type="button" class="btn btn-primary" onclick="editar(<%=a.getIdAtracao()%>);">Editar</button>
									</div>																		
								</td>
							</tr>
						<% } %>
					</tbody>
				</table>
				</div>			
			  <%} %>
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