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
					<ul>
						<a href="/">Log-In</a>
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
				<h1>Cadastre-se</h1>
				<div class="form-group">
					<label for="login">Login:</label> 
					<input type="text" class="form-control" name="txtLogin" placeholder="Digite um nome de usuário">
				</div>
				<div class="form-group">
					<label for="senha">Senha:</label> 
					<input type="password" class="form-control" name="txtSenha" placeholder="Digite uma senha">
				</div>
				<div class="form-group">
					<label for="cpf">CPF:</label> 
					<input type="text" class="form-control" name="txtCPF">
				</div>
				<div class="form-group">
					<label for="email">E-Mail:</label> 
					<input type="email" class="form-control" name="txtEmail" placeholder="Digite um email">
				</div>
				<div class="form-group">
					<label for="endereco">Endereço:</label> 
					<input type="text" class="form-control" name="txtEndereco" placeholder="Digite um endereço">
				</div>
				<div class="form-group">
					<label for="telefone">Telefone:</label> 
					<input type="text" class="form-control" name="txtTelefone" placeholder="Digite um telefone">
				</div>
				<div class="form-group">
					<button type="submit" class="btn btn-primary" name="cmd" value="cadastrar">Cadastrar</button>
					<button type="submit" class="btn btn-primary" name="cmd" value="cancelar">Cancelar</button>
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