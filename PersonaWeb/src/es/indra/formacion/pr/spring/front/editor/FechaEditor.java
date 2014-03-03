package es.indra.formacion.pr.spring.front.editor;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class FechaEditor extends PropertyEditorSupport {
	/**
	 * Traduce lo que viene del request (que es un String)
	 * al tipo de dato correspondiente
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Date fecha = null;

		try {
			fecha = new SimpleDateFormat("yyyy-MM-dd").parse(text);
		} catch (ParseException e) {}
		 
		setValue(fecha);
	}
	
	/**
	 * Traduce del objeto a un string para posible enviar en
	 * algún momento al navegador 
	 */
	@Override
	public String getAsText() {
		if (getValue() != null && getValue() instanceof Date) 
			return getValue().toString();
		else 
			return "";
	}
}
