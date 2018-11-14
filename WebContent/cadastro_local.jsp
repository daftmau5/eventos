<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="br.com.eventos.model.Local, java.util.*"%>   
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
			if (confirm("Remove o tema com id " + id)) {
				$('#formLocal').empty();
				$('#formLocal').append('<input type="hidden" name="txtId" value="' + id + '"/>');
				$('#formLocal').append('<input type="hidden" name="cmd" value="remover"/>');
				$('#formLocal').submit();
			}
		}

		function editar( id ) {
			$('#formLocal').empty();
			$('#formLocal').append('<input type="hidden" name="txtId" value="' + id + '"/>');
			$('#formLocal').append('<input type="hidden" name="cmd" value="editar"/>');
			$('#formLocal').submit();
		}		
	</script>
</head>

<body>

	<div id="wrapper">
<%
	String msg = (String)session.getAttribute("MENSAGEM");
	List<Local> lista = (List<Local>)session.getAttribute("LISTA");
	if(lista == null){
		lista = new ArrayList<Local>();
	}else{
		session.setAttribute("LISTA", null);
	}
	if(msg!=null){
		session.setAttribute("MENSAGEM", null);
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
						<a href="./PesquisaEvento">Buscar</a>
						<a href="./CadastroEvento">Cadastrar</a>
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
		<form id="formLocal" action="./LocalController" method="post">
			<div class="container-fluid">
				<h1>Cadastro de Locais</h1>
				<div class="form-group">
					<label for="nome">Nome:</label> 
					<input type="text" class="form-control" id="txtNomeLocal" name="txtNomeLocal" placeholder="Digite o Nome do local">
				</div>
				<div class="form-group">
					<label for="nome">Telefone:</label> 
					<input type="text" class="form-control" id="txtTelefone" name="txtTelefone" placeholder="Digite o telefone:">
				</div>

				<div class="form-group">
					<label for="nome">Capacidade:</label> 
					<input type="text" class="form-control" id="txtCapacidade" name="txtCapacidade" placeholder="Digite a capacidade do ambiente">
				</div>

				<div class="form-check form-check-inline">
  				<input class="form-check-input" type="radio" name="txtAreaFumante" id="txtAreaFumante" value="choc">
  				<label class="form-check-label" for="txtCobertura">Fumante</label>
			</div>				
			<div class="form-check form-check-inline">
  				<input class="form-check-input" type="radio" name="txtAreaFumante" id="txtAreaFumante" value="mor">
  				<label class="form-check-label" for="txtAreaFumante">Não fumante</label>
			</div>										
			
			<div class="form-group">
					<label for="nome">Endereço:</label> 
					<input type="text" class="form-control" id="txtEndereco" name="txtEndereco" placeholder="Digite o endereco">
				</div>


				<div class="form-check form-check-inline">
  				<input class="form-check-input" type="radio" name="txtAreaFumante" id="txtAreaFumante" value="choc">
  				<label class="form-check-label" for="txtVip">Vip</label>
			</div>				
			<div class="form-check form-check-inline">
  				<input class="form-check-input" type="radio" name="txtNaoVip" id="txtNaoVip" value="mor">
  				<label class="form-check-label" for="txtNaoVip">Não vip</label>
			</div>		
				
				  	<div class="form-group">
					<button type="submit" class="btn btn-primary" name="cmd" value="cadastrar">Cadastrar</button>
					<button type="submit" class="btn btn-primary" name="cmd" value="pesquisar">Pesquisar</button>
					<!-- button type="submit" class="btn btn-primary" name="cmd" value="limpar">Limpar</button>-->
				</div>	
				
					
		      </div>	
			  
 			</form>
			
			<div class="container">
				<table class="table table-stripped">
					<thead>
					<tr>
						<th>Id</th>
						<th>Nome</th>
						<th>Telefone</th>
						<th>Capacidade</th>
						<th>Area para fumantes</th>
						<th>Endereco</th>						
						<th>Area Vip</th>
					</tr>
				</thead>
					
					<tbody>
						<%for(Local l : lista){ %>
						<tr>
							<td><%=l.getIdLocal()%></td>
							<td><%=l.getNome() %></td>
							<td><%=l.getTelefone() %></td>
							<td><%=l.getCapacidade() %></td>
							<td><%=l.isAreaFumante() %></td>
							<td><%=l.getEndereco() %></td>
							<td><%=l.isVip()%></td>
							<td>
								<div class="form-group">
									<button type="button" class="btn btn-primary" onclick="remover(<%=l.getIdLocal()%>);">Remover</button>
									<button type="button" class="btn btn-primary" onclick="editar(<%=l.getIdLocal()%>);">Editar</button>
								</div>	
							</td>
												
						</tr>
						<%} %>
					</tbody>
				
				</table>
						
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