package br.com.sematec.gerenciador.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.sematec.gerenciador.dao.UsuarioDAO;
import br.com.sematec.gerenciador.modelo.Usuario;

@WebServlet(urlPatterns = "/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PrintWriter writer = resp.getWriter();
		String email = req.getParameter("email");
		String senha = req.getParameter("senha");
		Usuario usuario = new UsuarioDAO().buscaPorEmailESenha(email, senha);
		if (usuario == null) {
			writer.println("<html><body>Usu�rio ou senha inv�lida</body></html>");
		} else {
			Cookie cookie = new Cookie("usuario.logado", email);
			resp.addCookie(cookie);
			writer.println("<html><body>Usu�rio logado: " + email
					+ "</body></html>");
		}
	}
}
