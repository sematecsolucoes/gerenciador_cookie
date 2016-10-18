package br.com.sematec.gerenciador.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(urlPatterns = "/*")
public class FiltroDeAuditoria implements Filter {
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

@Override
public void doFilter(ServletRequest request, ServletResponse response,
		FilterChain chain) throws IOException, ServletException {
	HttpServletRequest req = (HttpServletRequest) request;
	HttpServletResponse resp = (HttpServletResponse) response;
	Cookie cookie = new Cookies(req.getCookies()).getUsuarioLogado();
	String usuario = "<deslogado>";
	if (cookie != null) {
		usuario = cookie.getValue();
		cookie.setMaxAge(10 * 60);
		resp.addCookie(cookie);
	}
	System.out.println("Usuario " + usuario + " acessando a URI "
			+ req.getRequestURI());
	chain.doFilter(request, response);
}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
}
