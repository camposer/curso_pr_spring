package es.indra.formacion.pr.spring.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import es.indra.formacion.pr.spring.to.Envoltorio;

@Controller
public class HolaMundoController {
	private String unMetodoAhi() {
		return "Hola Mundo!";
	}
	
	@RequestMapping("/holaMundo")
	public @ResponseBody String holaMundo() {
		return unMetodoAhi();
	}
	
	@RequestMapping("/holaMundo1")
	public @ResponseBody String holaMundo1(
			@RequestParam String nombre) {
		
		return "Hola Mundo! " + nombre;
	}

	@RequestMapping("/holaMundo2")
	public @ResponseBody String holaMundo2(HttpServletRequest req) {
		
		return "Hola Mundo! " + req.getParameter("nombre");
	}

	@RequestMapping("/holaMundo3")
	public void holaMundo3(
			HttpServletRequest req,
			HttpServletResponse res) throws IOException {
		
		PrintWriter pw = res.getWriter();
		pw.println("Hola Mundo! " + req.getParameter("nombre"));
		pw.flush();
	}
	
	@RequestMapping("/holaMundo4")
	public String holaMundo4(
			HttpServletRequest req,
			@RequestParam String nombre) {
		
		req.setAttribute("nombre", nombre);
		
		return "holaMundo.jsp"; // forward!!
	}

	@RequestMapping("/holaMundo5")
	public String holaMundo5(
			Model model,
			@RequestParam String nombre) {
		
		model.addAttribute("nombre", nombre);
		
		return "holaMundo.jsp"; // forward!!
	}

	@RequestMapping("/holaMundo6")
	public ModelAndView rodolfo(
			@RequestParam String nombre) {
		
		return new ModelAndView("holaMundo.jsp", "nombre", nombre);
	}
	
	@RequestMapping("/holaMundo7")
	public ModelAndView rodolfo1(
			@RequestParam String nombre) {
		
		Map<String, Object> model = new Hashtable<String, Object>();
		model.put("nombre", nombre);
		model.put("apellido", "cualquier cosa");
		
		return new ModelAndView("holaMundo.jsp", model);
	}

	@RequestMapping("/holaMundo8")
	public String pepe(
			Model model,
			Envoltorio env) {
		
		model.addAttribute("nombre", env.getNombre());
		
		return "holaMundo.jsp";
	}
	
	@RequestMapping("/holaMundo9")
	public String pepe1(
			Model model,
			Envoltorio env) {
		
		model.addAttribute("nombre", env.getNombre() + " " + env.getApellido());
		
		return "holaMundo.jsp";
	}

	@RequestMapping("/holaMundo10")
	public String conRedirect(
			HttpSession sesion,
			@RequestParam String nombre) {
		
		sesion.setAttribute("nombre", "ensession: " + nombre);
		
		return "redirect:holaMundo.jsp";
	}

	@RequestMapping("/holaMundo11")
	public String conRedirect() {
		return "redirect:holaMundo.do";
	}

	@RequestMapping("/holaMundo12")
	public String conForward() {
		return "holaMundo.do";
	}

	@RequestMapping("/holaMundo13")
	public void comoUnServlet(
			HttpServletRequest req,
			HttpServletResponse res) throws ServletException, IOException {
		
		req.setAttribute("nombre", req.getParameter("nombre"));
		
		req
			.getServletContext()
			.getRequestDispatcher("/holaMundo.jsp")
			.forward(req, res);
	}

}
