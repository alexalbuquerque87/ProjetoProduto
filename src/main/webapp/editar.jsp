<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Produtos - Editar</title>
</head>
<body>
	<h1>Editar produto</h1>
	<form name="frmCadastro" action="update">
		<table>
			<tr>
				<td>ID*:</td>
				<td><input type="text" name="id" readonly 
				value="<%out.print(request.getAttribute("id"));%>"></td>
			</tr>
			<tr>
				<td>Código*:</td>
				<td><input type="text" name="codigo"
				value="<%out.print(request.getAttribute("codigo"));%>"></td>
			</tr>
			<tr>
				<td>Nome*:</td>
				<td><input type="text" name="nome" 
				value="<%out.print(request.getAttribute("nome"));%>"></td>
			</tr>
			<tr>
				<td>Categoria:</td>
				<td><input type="text" name="categoria"
				value="<%out.print(request.getAttribute("categoria"));%>"></td>
			</tr>
			<tr>
				<td>Valor*:</td>
				<td><input type="text" name="valor" 
				value="<%out.print(request.getAttribute("valor"));%>"></td>
			</tr>
			<tr>
				<td>Quantidade*:</td>
				<td><input type="text" name="quantidade" 
				value="<%out.print(request.getAttribute("quantidade"));%>"></td>
			</tr>
		</table>
		<input type="button" value="Salvar" onclick="validar()">
	</form>
	<p>
		<a href="home">Sair</a>

		<script charset="utf-8" src="scripts/validador.js"></script>
</body>
</html>