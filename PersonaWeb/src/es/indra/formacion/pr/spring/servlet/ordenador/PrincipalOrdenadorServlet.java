package es.indra.formacion.pr.spring.servlet.ordenador;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import es.indra.formacion.pr.spring.service.IOrdenadorService;
import es.indra.formacion.pr.spring.service.IPersonaService;
import es.indra.formacion.pr.spring.util.Contexto;

/**
 * Servlet implementation class PrincipalOrdenadorServlet
 */
@WebServlet("/ordenador/Principal")
public class PrincipalOrdenadorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrincipalOrdenadorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		IPersonaService personaService = Contexto.getInstance().getBean("personaService", IPersonaService.class);
		IOrdenadorService ordenadorService = Contexto.getInstance().getBean("ordenadorService", IOrdenadorService.class);
		
		request.setAttribute("personas", personaService.obtenerPersonas());
		//request.setAttribute("ordenadores", ordenadorService.obtenerOrdenadoresOrdenadosPorSerial());
		request.setAttribute("ordenadores", ordenadorService.obtenerOrdenadores());
		
		getServletContext()
			.getRequestDispatcher("/jsp/ordenador/principal.jsp")
			.forward(request, response);
	}

}
