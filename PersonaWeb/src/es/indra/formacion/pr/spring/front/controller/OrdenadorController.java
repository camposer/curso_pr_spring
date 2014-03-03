package es.indra.formacion.pr.spring.front.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import es.indra.formacion.pr.persistence.model.Ordenador;
import es.indra.formacion.pr.persistence.model.Persona;
import es.indra.formacion.pr.spring.front.editor.NumeroEditor;
import es.indra.formacion.pr.spring.front.form.OrdenadorForm;
import es.indra.formacion.pr.spring.service.IOrdenadorService;
import es.indra.formacion.pr.spring.service.IPersonaService;

@Controller
@RequestMapping("/ordenador/*")
public class OrdenadorController {
	@Autowired
	private IPersonaService personaService;
	@Autowired
	private IOrdenadorService ordenadorService;	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Integer.class, new NumeroEditor(Integer.class));
	}
	
	@RequestMapping(value={"principal", ""})
	public String principal(Model model) {
		
		model.addAttribute("personas", personaService.obtenerPersonas());
		model.addAttribute("ordenadores", ordenadorService.obtenerOrdenadores());
		
		return "forward:/jsp/ordenador/principal.jsp";
	}
	
	@RequestMapping("agregar")
	public String agregar(OrdenadorForm ordenadorForm) {
		
		// TODO: Incluir validaciones!
		
		Persona p = new Persona();
		p.setId(ordenadorForm.getPersonaId());
		
		Ordenador o = new Ordenador();
		o.setNombre(ordenadorForm.getNombre());
		o.setSerial(ordenadorForm.getSerial());
		o.setPersona(p);
		
		ordenadorService.agregarOrdenador(o);
		
		return "redirect:principal.do";
	}
	
}
