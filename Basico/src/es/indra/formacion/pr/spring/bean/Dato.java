package es.indra.formacion.pr.spring.bean;

public class Dato {
	private String contenido;
	
	public Dato(String contenido) {
		this.contenido = contenido;
		System.out.println("Creando Dato...");		
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
}
