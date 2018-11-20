<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="br.com.eventos.model.Usuario, java.util.List, java.util.ArrayList"%>   
<!DOCTYPE html>
<html lang="pt">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>E-Vents</title>

<script type="text/javascript">
$("#txtCPF").keydown(function(){
    try {
        $("#txtCPF").unmask();
    } catch (e) {}

    var tamanho = $("#cpfcnpj").val().length;

    if(tamanho < 11){
        $("#txtCPF").mask("999.999.999-99");
    } else if(tamanho >= 11){
        $("#txtCPF").mask("99.999.999/9999-99");
    }

    // ajustando foco
    var elem = this;
    setTimeout(function(){
        // mudo a posição do seletor
        elem.selectionStart = elem.selectionEnd = 10000;
    }, 0);
    // reaplico o valor para mudar o foco
    var currentValue = $(this).val();
    $(this).val('');
    $(this).val(currentValue);
});
</script>
<%
Usuario usuarioAtual = (Usuario)session.getAttribute("USUARIO_LOGADO");
%>
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
		<form id="formUsuario" action="./ControllerUsuario" method="post">
			<div class="container-fluid">
				<h1>Altere seu cadastro</h1>
				<input type="text" name="txtIdUsuario" readonly hidden="true" value="<%=usuarioAtual.getIdUsuario()%>">
				<div class="form-group">
					<label for="login">Login:</label> 
					<input type="text" class="form-control" name="txtLogin" readonly placeholder="Digite um nome de usuário" value="<%=usuarioAtual.getLogin()%>">
				</div>
				<div class="form-group">
					<label for="senha">Senha:</label> 
					<input type="password" class="form-control" name="txtSenha" placeholder="Digite uma senha" value="<%=usuarioAtual.getSenha()%>">
				</div>
				<div class="form-group">
					<label for="cpf">CPF:</label> 
					<input type="text" class="form-control" name="txtCPF" value="<%=usuarioAtual.getCPF()%>">
				</div>
				<div class="form-group">
					<label for="email">E-Mail:</label> 
					<input type="email" class="form-control" name="txtEmail" placeholder="Digite um email" value="<%=usuarioAtual.getEmail()%>">
				</div>
				<div class="form-group">
					<label for="endereco">Endereço:</label> 
					<input type="text" class="form-control" name="txtEndereco" placeholder="Digite um endereço" value="<%=usuarioAtual.getEndereco()%>">
				</div>
				<div class="form-group">
					<label for="telefone">Telefone:</label> 
					<input type="text" class="form-control" name="txtTelefone" placeholder="Digite um telefone" value="<%=usuarioAtual.getTelefone()%>">
				</div>
				<div class="form-group">
					<button type="submit" class="btn btn-primary" name="cmd" value="editar">Alterar</button>
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