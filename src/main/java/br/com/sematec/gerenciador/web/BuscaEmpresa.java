package br.com.sematec.gerenciador.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sematec.gerenciador.dao.EmpresaDAO;
import br.com.sematec.gerenciador.modelo.Empresa;

@WebServlet(urlPatterns = "/busca")
public class BuscaEmpresa extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		writer.println("<html>");
		writer.println("<body>");
		writer.println("Resultado da busca:<br/>");
		String filtro = req.getParameter("filtro");
		Collection<Empresa> empresas = new EmpresaDAO()
				.buscaPorSimilaridade(filtro);
		writer.println("<ul>");
		for (Empresa empresa : empresas) {
			writer.println("<li>" + empresa.getId() + ": " + empresa.getNome()
					+ "</li>");
		}
		writer.println("</ul>");
		writer.println("</body>");
		writer.println("</html>");
	}
}
