package es.indra.formacion.pr.spring.front.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import es.indra.formacion.pr.persistence.model.Ordenador;
import es.indra.formacion.pr.persistence.model.Persona;
import es.indra.formacion.pr.spring.front.editor.NumeroEditor;
import es.indra.formacion.pr.spring.front.editor.FechaEditor;
import es.indra.formacion.pr.spring.front.form.PersonaForm;
import es.indra.formacion.pr.spring.service.IPersonaService;

@Controller
@RequestMapping("/persona/*")
public class PersonaController {
	@Autowired
	private IPersonaService personaService;
	@Autowired
	private FechaEditor fechaEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, fechaEditor); // Manejado por Spring
		binder.registerCustomEditor(Double.class, new NumeroEditor(Double.class));
	}

	@RequestMapping(value = { "principal", "" })
	public String principal(Model model) {
		model.addAttribute("personas", personaService.obtenerPersonas());

		return "/jsp/persona/principal.jsp";
	}

	@RequestMapping("agregar")
	public String agregar(PersonaForm personaForm) {

		// TODO: Incluir validaciones!

		try {
			Persona p = new Persona(
					personaForm.getNombre(), 
					personaForm.getApellido(), 
					personaForm.getFechaNacimiento(), 
					personaForm.getAltura());

			// Construir lista de ordenadores!
			List<Ordenador> ordenadores = new ArrayList<Ordenador>();
			Ordenador o = new Ordenador();
			o.setNombre(personaForm.getOrdenadorNombre());
			o.setSerial(personaForm.getOrdenadorSerial());
			ordenadores.add(o);

			p.setOrdenadores(ordenadores);

			personaService.agregarPersona(p);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:principal.do";
	}

}
