package pacote.bean;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(servletNames = { "Faces Servlet" })
public class ControleDeAcesso implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) arg0;
		HttpSession session = req.getSession();

		if (session.getAttribute("usuario") != null || req.getRequestURI().endsWith("login.xhtml")) {
			arg2.doFilter(arg0, arg1);
		} else {
			HttpServletResponse resp = (HttpServletResponse) arg1;
			resp.sendRedirect("login.xhtml");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
