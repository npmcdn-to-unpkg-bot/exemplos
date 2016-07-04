package br.com.caelum.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.dao.ContatoDao;
import br.com.caelum.modelo.Contato;

@WebServlet("/adicionaContato")
public class AdicionaContato extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		
		String nome = req.getParameter("nome");
		String email = req.getParameter("email");
		String endereco = req.getParameter("endereco");

		String dataNascEmTexto = req.getParameter("dataNasc");
		Calendar dataNasc = null;

		try {
			Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dataNascEmTexto);
			dataNasc = Calendar.getInstance();
			dataNasc.setTime(date);
		} catch (ParseException e) {
			out.println("Erro ao converter");
			return;
		}
		
		Contato contato = new Contato(nome, email, endereco, dataNasc);
		
		ContatoDao dao = new ContatoDao();
		dao.adiciona(contato);
		
		out.print("Contato " + contato.getNome() + " adicionado com sucesso");

	}

}
