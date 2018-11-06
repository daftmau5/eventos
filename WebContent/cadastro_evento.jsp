<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="br.com.eventos.model.Evento, br.com.eventos.model.Atracao, br.com.eventos.model.Tema, br.com.eventos.model.Local, java.util.List, java.util.ArrayList"%>   
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

</head>

<body>

	<%  String msg = (String)session.getAttribute("MENSAGEM");
		List<Tema> listaTema = (List<Tema>)session.getAttribute("LISTA_TEMA");
		List<Atracao> listaAtracao = (List<Atracao>)session.getAttribute("LISTA_ATRACAO");
		List<Local> listaLocal  = (List<Local>)session.getAttribute("LISTA_LOCAL");
		if(listaTema == null){
			listaTema = new ArrayList<Tema>();
		} else {
			session.setAttribute("LISTA_TEMA", null);
		}
		
		if(listaAtracao == null){
			listaAtracao = new ArrayList<Atracao>();
		} else {
			session.setAttribute("LISTA_ATRACAO", null);
		}
		
		if(listaLocal == null){
			listaLocal = new ArrayList<Local>();
		} else {
			session.setAttribute("LISTA_LOCAL", null);
		}
		
		if(msg != null){
			session.setAttribute("MENSAGEM", null);
	%>
		<h3 class="alert alert-danger"><%=msg%></h3>
	<%  } %>
	
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
		<form id="formEvento" action="./Controller" method="post">
			<div class="container-fluid">
				<h1>Cadastro de Eventos</h1>
				<div class="form-group">
					<label for="nome">Nome:</label> 
					<input type="text" class="form-control" id="txtNomeEvento" placeholder="Digite o Nome do Evento">
				</div>
				<div class="form-group">
					<label for="nome">Descri��o:</label> 
					<input type="text" class="form-control" id="txtDescricaoEvento" placeholder="Digite a Descrição do Evento">
				</div>
				
				<div class="form-group">
				  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				    Escolha um Tema
				  </button>
				  <div class="dropdown-menu" name="idTema" aria-labelledby="dropdownMenuButton">
				    <% for(Tema t : listaTema){  %>
				    <a class="dropdown-item" value="<%=t.getIdTema()%>"><%=t.getDescricao()%></a>
				    <% } %>
				  </div>
				</div>

		        <div class="form-group">
		            <label for="data-pagamento">Data:</label>
		            <div class="input-group date" data-date-format="dd.mm.yyyy">
		               <input  type="text" class="form-control" placeholder="dd.mm.yyyy" id="txtDataEvento">
		               <div class="input-group-addon" >
		                    <span class="glyphicon glyphicon-th"></span>
		                </div>
		             </div>
		        </div>
            	<script src='http://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.6.1/js/bootstrap-datepicker.min.js'></script>
            	<script>
     				$('.input-group.date').datepicker({format: "dd.mm.yyyy"});
    			</script>
    			
    			<div class="form-group">
				  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				    Escolha uma Atra��o
				  </button>
				  <div class="dropdown-menu" name="idAtracao" aria-labelledby="dropdownMenuButton">
				    <% for(Atracao a : listaAtracao){  %>
				    <a class="dropdown-item" value="<%=a.getIdAtracao()%>"><%=a.getNome()%></a>
				    <% } %>
				  </div>
				</div>
			 
			   <div class="form-group">
				  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				    Escolha um Local
				  </button>
				  <div class="dropdown-menu" name="idLocal" aria-labelledby="dropdownMenuButton">
				    <% for(Local l : listaLocal){  %>
				    <a class="dropdown-item" value="<%=l.getIdLocal()%>"><%=l.getNome()%></a>
				    <% } %>
				  </div>
				</div>
				  
				<div class="form-group">
					<label for="nome">Pre�o:</label> 
					<input type="text" class="form-control" id="txtPrecoEvento" placeholder="R$">
				</div>
				
				<div class="form-group">
					<button type="submit" class="btn btn-primary" name="cmd" value="cadastrar">Cadastrar</button>
					<button type="submit" class="btn btn-primary" name="cmd" value="limpar">Limpar</button>
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