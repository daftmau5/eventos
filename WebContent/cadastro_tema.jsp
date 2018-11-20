<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="br.com.eventos.model.Tema,  java.util.*"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
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
			if (confirm("Remove o tema com id " + id)) {
				$('#formTema').empty();
				$('#formTema').append('<input type="hidden" name="txtId" value="' + id + '"/>');
				$('#formTema').append('<input type="hidden" name="cmd" value="remover"/>');
				$('#formTema').submit();
			}
		}

		function editar( id ) {
			$('#formTema').empty();
			$('#formTema').append('<input type="hidden" name="txtId" value="' + id + '"/>');
			$('#formTema').append('<input type="hidden" name="cmd" value="editar"/>');
			$('#formTema').submit();
		}		
	</script>
</head>




<body>

<%
	String msg = (String)session.getAttribute("MENSAGEM");
	List<Tema> lista = (List<Tema>)session.getAttribute("LISTA");
	if(lista == null){
		lista = new ArrayList<Tema>();
	}else{
		session.setAttribute("LISTA", null);
	}
	if(msg!=null){
		session.setAttribute("MENSAGEM", null);
	}
	
	
	Tema temaAtual = (Tema)session.getAttribute("TEMA_ATUAL");
	if(temaAtual==null){
		temaAtual = new Tema();
		temaAtual.setDescricao("");
	}else{
		session.setAttribute("TEMA_ATUAL", null);
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
						<a href="./TemaController">Cadastrar</a>
					</ul>
				</li>
				<li>
					<h4 style="color: white;">Meu Perfil</h4>
					<ul>
						<a href="./ControllerUsuario">Alterar</a>
					</ul>
				</li>
			</ul>
		</div>
		<!-- /#sidebar-wrapper -->

		<!-- Page Content -->
		<a href="#menu-toggle" class="btn btn-secondary" id="menu-toggle">Menu</a>
		<div id="page-content-wrapper">
		<form id="formTema" action="./TemaController" method="post">
			<div class="container-fluid">
				<h1>Cadastro de Temas</h1>
				
				
				<%if(temaAtual.getIdTema()==0){ %>
				<!-- <div class="form-group">
    				<label for="txtId">Id</label>
    				<input type="text" class="form-control" id="txtId" name="txtId" value="<%=temaAtual.getIdTema()%>" readonly/>
  				</div>--><%}else{ %>
  				<div class="form-group">
    				<label for="txtId">Id</label>
    				<input type="text" class="form-control" id="txtId" name="txtId" value="<%=temaAtual.getIdTema()%>" readonly/>
  				</div><%} %>
  				
  				
				<div class="form-group">
					<label for="nome">Descrição:</label> 
					<input type="text" class="form-control" id="txtdescricao" name="txtdescricao"  value="<%=temaAtual.getDescricao() %>" placeholder="Digite a Descrição do tema">
				</div>
				
				<div class="form-group">
					<%if(temaAtual.getIdTema()==0){ %>
					<button type="submit" class="btn btn-primary" name="cmd" value="cadastrar">Cadastrar</button>
					<%}else{%>
						<button type="submit" class="btn btn-primary" name="cmd" value="salvar">Salvar</button>
					<%}%>
					<button type="submit" class="btn btn-primary" name="cmd" value="pesquisar">Pesquisar</button>
					<!-- button type="submit" class="btn btn-primary" name="cmd" value="limpar">Limpar</button>-->
				</div>	
		<%if (lista.size() > 0) {%>
		<div class="container">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Id</th>
						<th>Descricao</th>
					</tr>
				</thead>
				<tbody>
					<% for (Tema t : lista) { %>
					<tr>
						<td><%=t.getIdTema()%></td>
						<td><%=t.getDescricao() %></td>
						<td>
							<div class="form-group">
								<button type="button" class="btn btn-primary" onclick="remover(<%=t.getIdTema()%>);">Remover</button>
								<button type="button" class="btn btn-primary" onclick="editar(<%=t.getIdTema()%>);">Editar</button>
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