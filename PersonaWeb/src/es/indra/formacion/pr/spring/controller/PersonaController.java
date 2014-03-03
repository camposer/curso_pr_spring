package es.indra.formacion.pr.spring.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.indra.formacion.pr.persistence.model.Ordenador;
import es.indra.formacion.pr.persistence.model.Persona;
import es.indra.formacion.pr.spring.editor.DobleEditor;
import es.indra.formacion.pr.spring.editor.FechaEditor;
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
		binder.registerCustomEditor(Date.class, fechaEditor); // Manejado por
																// Spring
		binder.registerCustomEditor(Double.class, new DobleEditor());
	}

	@RequestMapping(value = { "principal", "" })
	public String principal(Model model) {
		model.addAttribute("personas", personaService.obtenerPersonas());

		return "/jsp/persona/principal.jsp";
	}

	@RequestMapping("agregar")
	public String agregar(@RequestParam String nombre,
			@RequestParam String apellido, @RequestParam Date fechaNacimiento,
			@RequestParam Double altura, @RequestParam String ordenadorNombre,
			@RequestParam String ordenadorSerial) {

		// TODO: Incluir validaciones!

		try {
			Persona p = new Persona(nombre, apellido, fechaNacimiento, altura);

			// Construir lista de ordenadores!
			List<Ordenador> ordenadores = new ArrayList<Ordenador>();
			Ordenador o = new Ordenador();
			o.setNombre(ordenadorNombre);
			o.setSerial(ordenadorSerial);
			ordenadores.add(o);

			p.setOrdenadores(ordenadores);

			personaService.agregarPersona(p);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:principal.do";
	}

}
