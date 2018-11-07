<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="br.com.eventos.model.Tema, java.util.List, java.util.ArrayList"%>   
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

</head>

<body>
	<div id="wrapper">
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
	%>
		<!-- Sidebar -->
		<div id="sidebar-wrapper">
			<ul class="sidebar-nav">
				<li class="sidebar-brand">
					<h2 href="#" style="color: #6495ED;">E-Vents</h2>
				</li>
				<li>
					<h4 style="color: white;">Evento</h4>
					<ul>
						<a href="#">Buscar</a>
						<a href="#">Cadastrar</a>
						<a href="#">Reservas</a>
					</ul>
				</li>
				<li>
					<h4 style="color: white;">Local</h4>
					<ul>
						<a href="#">Buscar</a>
						<a href="#">Cadastrar</a>
					</ul>
				</li>
				<li>
					<h4 style="color: white;">Atração</h4>
					<ul>
						<a href="#">Buscar</a>
						<a href="#">Cadastrar</a>
					</ul>
				</li>
				<li>
					<h4 style="color: white;">Tema</h4>
					<ul>
						<a href="#">Buscar</a>
						<a href="#">Cadastrar</a>
					</ul>
				</li>
			</ul>
		</div>
		<!-- /#sidebar-wrapper -->

		<!-- Page Content -->
		<a href="#menu-toggle" class="btn btn-secondary" id="menu-toggle">Menu</a>
		<div id="page-content-wrapper">
		<form id="formEvento" action="./TemaController" method="post">
			<div class="container-fluid">
				<h1>Cadastro de Temas</h1>
				
				<div class="form-group">
					<label for="nome">Descrição:</label> 
					<input type="text" class="form-control" id="txtdescricao" name="txtdescricao" placeholder="Digite a Descrição do tema">
				</div>
				
				<div class="form-group">
					<button type="submit" class="btn btn-primary" name="cmd" value="cadastrar">Cadastrar</button>
					<button type="submit" class="btn btn-primary" name="cmd" value="pesquisar">Pesquisar</button>
					<button type="submit" class="btn btn-primary" name="cmd" value="limpar">Limpar</button>
				</div>	
				
				<div class="container">
				<table class="table table-stripped">
					<tbody>
						<%for(Tema t : lista){ %>
						<tr>
							<td><%=t.getIdTema() %>
							<td><%=t.getDescricao() %>
							
						</tr>
						<%} %>
					</tbody>
				
				</table>
							  
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