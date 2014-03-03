package es.indra.formacion.pr.spring.editor;

import java.beans.PropertyEditorSupport;

public class DobleEditor extends PropertyEditorSupport {
	/**
	 * Traduce lo que viene del request (que es un String)
	 * al tipo de dato correspondiente
	 */
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Double doble = null;

		try {
			doble = Double.parseDouble(text);
		} catch (NumberFormatException e) {}
		 
		setValue(doble);
	}
	
	/**
	 * Traduce del objeto a un string para posible enviar en
	 * algún momento al navegador 
	 */
	@Override
	public String getAsText() {
		if (getValue() != null && getValue() instanceof Double) 
			return getValue().toString();
		else 
			return "";
	}
}
