package es.indra.formacion.pr.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HolaMundoController {
	
	@RequestMapping("/holaMundo")
	public @ResponseBody String holaMundo() {
		String html = "<html>" +
				"<head></head>" +
				"<body>" +
				"Hola Mundo!" +
				"</body>" +
				"</html>";
		
		return html;
	}
}

/**
 * Iniciar servidor y ejecutar (no con IE):
 * http://localhost:8080/BasicoMvc/holaMundo.do
 */
