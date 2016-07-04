<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.*, br.com.caelum.dao.*, br.com.caelum.modelo.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
</style>
</head>
<body>
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
			<%
				ContatoDao dao = new ContatoDao();
				List<Contato> contatos = dao.getLista();

				for (Contato c : contatos) {
			%>
			<tr>
				<td><%=c.getNome()%></td>
				<td><%=c.getEmail()%></td>
				<td><%=c.getEndereco()%></td>
				<%
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
				%>
				<td><%=format.format(c.getDataNascimento().getTime())%></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>

</body>
</html>