package be.vdab.servlets.artikels;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import be.vdab.entities.Artikel;
import be.vdab.entities.FoodArtikel;
import be.vdab.entities.NonFoodArtikel;
import be.vdab.services.ArtikelService;
import be.vdab.util.StringUtils;

@WebServlet("/artikels/toevoegen.htm")
public class ToevoegenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VIEW = "/WEB-INF/JSP/artikels/toevoegen.jsp";
	private static final String REDIRECT_URL = "%s/artikels/zoekopnummer.htm?id=%d";
	private final transient ArtikelService artikelService = new ArtikelService();
//    private final transient FoodArtikelService foodArtikelService = new FoodArtikelService();
//    private final transient NonFoodArtikelService nonFoodArtikelService = new NonFoodArtikelService();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(VIEW).forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,String> fouten = new HashMap<>();
		String naam = request.getParameter("naam");
		Artikel artikel = null;
		if(!Artikel.isNaamValid(naam)) {
			fouten.put("naam", "verplicht!");
		}
		String aankoopprijsString = request.getParameter("aankoopprijs");
		BigDecimal aankoopprijs = null;
		if(StringUtils.isBigDecimal(aankoopprijsString)) {
			aankoopprijs = new BigDecimal(aankoopprijsString);
			if(!Artikel.isAankoopprijsValid(aankoopprijs)) {
				fouten.put("aankoopprijs", "aankoopprijs moet minstens 0.01 zijn!");
			}
		}
		else {
			fouten.put("aankoopprijs", "aankoopprijs moet minstens 0.01 zijn!");
		}
		String verkoopprijsString = request.getParameter("verkoopprijs");
		BigDecimal verkoopprijs = null;
		if(StringUtils.isBigDecimal(verkoopprijsString)) {
			verkoopprijs = new BigDecimal(verkoopprijsString);
			if(!Artikel.isVerkoopprijsValid(verkoopprijs,aankoopprijs)) {
				fouten.put("verkoopprijs", "verkoopprijs moet groter zijn dan aankoopprijs!");
			}			
		}
		else {
			fouten.put("verkoopprijs", "verkoopprijs moet groter zijn dan aankoopprijs!");
		}
		String soort = request.getParameter("soort");
		if(soort == null) {
			fouten.put("soort", "u moet een keuze maken");
		}
		else {
			switch(soort) {
			case "F":
				String houdbaarheid = request.getParameter("houdbaarheid");
				if(houdbaarheid != null && !houdbaarheid.trim().isEmpty()) {
					if(FoodArtikel.isHoudbaarheidValid(Integer.parseInt(houdbaarheid))) {
						if(fouten.isEmpty()) {
							artikel = 
								new FoodArtikel(naam, aankoopprijs, verkoopprijs, Integer.parseInt(houdbaarheid));
						}
					}
					else {
						fouten.put("houdbaarheid", "houdbaarheid moet positief zijn!");
					}
				}
				else {
					fouten.put("houdbaarheid", "houdbaarheid is verplicht!");
				}
				break;
			case "NF":
				String garantie = request.getParameter("garantie");
				if(garantie != null && !garantie.trim().isEmpty()) {
					if(NonFoodArtikel.isGarantieValid(Integer.parseInt(garantie))) {
						if(fouten.isEmpty()) {
							artikel = 
								new NonFoodArtikel(naam, aankoopprijs, verkoopprijs, Integer.parseInt(garantie));
						}
					}
					else {
						fouten.put("garantie", "garantie moet positief zijn!");
					}
				}
				else {
					fouten.put("garantie", "garantie is verplicht!");
				}
				break;
			default:
				fouten.put("soort", "u moet een geldige keuze maken!");
			}
		}
		if(fouten.isEmpty()) {
			artikelService.create(artikel);
			response.sendRedirect(response.encodeRedirectURL(String.format(
				REDIRECT_URL, request.getContextPath(), artikel.getId())));
		}
		else {
			request.setAttribute("fouten", fouten);
			request.getRequestDispatcher(VIEW).forward(request, response);
		}
	}
}
