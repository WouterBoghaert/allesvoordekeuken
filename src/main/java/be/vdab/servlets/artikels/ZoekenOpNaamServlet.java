package be.vdab.servlets.artikels;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Artikel;
import be.vdab.services.ArtikelService;

@WebServlet("/artikels/zoekenopnaam.htm")
public class ZoekenOpNaamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/artikels/zoekenopnaam.jsp";
	private final transient ArtikelService artikelService = new ArtikelService();
     
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getQueryString() != null) {
			Map<String,String> fout = Collections.singletonMap("naam", "tik een naam");
			String naam = request.getParameter("naam");
			if(Artikel.isNaamValid(naam)) {
				request.setAttribute("artikels", artikelService.findByLikeName(naam));
			}
			else {
				request.setAttribute("fout", fout);
			}			
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}
