package be.vdab.servlets.artikels;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import be.vdab.services.ArtikelService;
import be.vdab.services.ArtikelgroepService;

@WebServlet("/artikels/perartikelgroep.htm")
public class PerArtikelgroepServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/artikels/perartikelgroep.jsp";
	private final transient ArtikelgroepService  artikelgroepService
		= new ArtikelgroepService();
//	private final transient ArtikelService artikelService = 
//		new ArtikelService();
     
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("artikelgroepen", artikelgroepService.findAll());
		String idString = request.getParameter("id");
		if(idString != null && !idString.trim().isEmpty()) {
			artikelgroepService.read(Long.parseLong(idString))
				.ifPresent(artikelgroep ->
				//request.setAttribute("artikels", artikelService.findByArtikelgroep(artikelgroep)));
				request.setAttribute("artikelgroep", artikelgroep));
		}
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
}
