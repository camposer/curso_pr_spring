package es.indra.formacion.pr.spring.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import es.indra.formacion.pr.persistence.model.Ordenador;
import es.indra.formacion.pr.persistence.model.Persona;
import es.indra.formacion.pr.spring.service.IOrdenadorService;
import es.indra.formacion.pr.spring.service.IPersonaService;

@Controller
@RequestMapping("/ordenador/*")
public class OrdenadorController {
	@Autowired
	private IPersonaService personaService;
	@Autowired
	private IOrdenadorService ordenadorService;	
	
	@RequestMapping(value={"principal", ""})
	public void principal(
			HttpServletRequest request,
			HttpServletResponse response) 
					throws ServletException, IOException {
		
		request.setAttribute("personas", personaService.obtenerPersonas());
		//request.setAttribute("ordenadores", ordenadorService.obtenerOrdenadoresOrdenadosPorSerial());
		request.setAttribute("ordenadores", ordenadorService.obtenerOrdenadores());
		
		request
			.getServletContext()
			.getRequestDispatcher("/jsp/ordenador/principal.jsp")
			.forward(request, response);
	}
	
	@RequestMapping("agregar")
	public void agregar(
			HttpServletRequest request,
			HttpServletResponse response) 
					throws ServletException, IOException {
		
		String nombre = request.getParameter("nombre");
		String serial = request.getParameter("serial");
		String spersonaId = request.getParameter("personaId");
		
		// TODO: Incluir validaciones!
		
		Persona p = new Persona();
		p.setId(Integer.parseInt(spersonaId));
		
		Ordenador o = new Ordenador();
		o.setNombre(nombre);
		o.setSerial(serial);
		o.setPersona(p);
		
		ordenadorService.agregarOrdenador(o);
		
		response.sendRedirect("principal.do");
	}
	
}
