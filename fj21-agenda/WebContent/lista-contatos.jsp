<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- Instancia a contatoDao -->
	<jsp:useBean id="dao" class="br.com.caelum.dao.ContatoDao" />

	<table>
		<caption>Contatos</caption>
		<thead>
			<tr>
				<th>Nome</th>
				<th>E-mail</th>
				<th>Endereço</th>
				<th>Data Nascimento</th>
			</tr>
		</thead>

		<tbody>
			<!-- Percorre todos os contatos da lista -->
			<c:forEach var="contato" items="${dao.lista}">
				<tr>
					<td>${contato.nome}</td>
					<td><c:choose>
							<c:when test="${not empty contato.email}">
								<a href="mailto:${contato.email}">${contato.email}</a>
							</c:when>
							<c:otherwise>
							E-mail não informado
						</c:otherwise>
						</c:choose></td>
					<td>${contato.endereco}</td>
					<td>${contato.dataNascimento.time}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>

<style>
table {
	border-spacing: 0.2em;
	border-collapse: separate;
}

thead {
	background-color: #999;
}

thead th {
	font-weight: bold;
	padding: 0.3em 1em;
	text-align: center;
}

td {
	padding: 0.3em;
}

tr:nth-child(2n) {
	background-color: #ccc;
}

td:last-child {
	text-align: center;
}

caption {
	padding: 0.5em;
}
</style>
</html>